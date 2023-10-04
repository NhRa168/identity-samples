package com.google.credentialmanager.sample.ui.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CreatePublicKeyCredentialRequest
import androidx.credentials.CreatePublicKeyCredentialResponse
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.security.SecureRandom

private const val TAG = "AuthViewModel"

data class AppState(
    val justOpened: Boolean = true,
    val isSignedIn: Boolean = false,
    val currentEmail: String = "",
    val displayName: String = "",
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val loginErrorMessage: String = "",
    val wasJustCancelled: Boolean = false,
    val isFirstTextboxLaunch: Boolean = true,
)

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    var appState by mutableStateOf(AppState())

    private companion object {
        const val RC_SIGN_IN = 123
    }

    private val _isSignedIn = MutableLiveData<Boolean>()

    val isSignedIn: LiveData<Boolean> get() = _isSignedIn

    private val credentialManager by lazy {
        CredentialManager.create(application)
    }
    private val signedInPasswordCredential = MutableStateFlow<PasswordCredential?>(null)

    // This method should be refactored to remove Activity from ViewModel. Just want to test CredMan call for now.
    fun signUpWithPasskey(activity: Activity, scope: CoroutineScope) {
        scope.launch {
            if(createPasskey(activity)) {
                Toast.makeText( getApplication<Application>().applicationContext, "Passkey created", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText( getApplication<Application>().applicationContext, "Passkey creation failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private suspend fun createPasskey(activity: Activity): Boolean {

        val request = CreatePublicKeyCredentialRequest(fetchRegistrationJsonFromServer())
        var response: CreatePublicKeyCredentialResponse? = null
        try {
            response = credentialManager.createCredential(
                activity,
                request
            ) as CreatePublicKeyCredentialResponse
        } catch (e: CreateCredentialException) {
            //TODO: handlePasskeyFailure(e)
            throw e
        }
        if (response is CreatePublicKeyCredentialResponse) {
            Log.i("TAG", "createPasskey response json ${response.registrationResponseJson}")
                //sendRegistrationResponse(result.registrationResponseJson, response.cookie!!)
            return true
        }
        return false
    }

    private fun fetchRegistrationJsonFromServer(): String {

        val jsonResponse = """
            {
                "challenge": "<challenge>",
                "rp": {
                    "id": "com.example.prototype",
                    "name": "CredMan App Test"
                },
                "pubKeyCredParams": [
                    {
                        "type": "public-key",
                        "alg": -7
                    },
                    {
                        "type": "public-key",
                        "alg": -257
                    }
                ],
                "authenticatorSelection": {
                    "authenticatorAttachment": "platform",
                    "residentKey": "required"
                },
                "user": {
                       "id": "<userId>",
                       "name": "<userName>",
                       "displayName": "<userDisplayName>"
                   }
            }

        """.trimIndent()

    return jsonResponse.replace("<userId>", getEncodedUserId())
        .replace("<userName>", "test1")
        .replace("<userDisplayName>", "test2")
        .replace("<challenge>",  getEncodedChallenge())
    }

    private fun getEncodedUserId(): String {
        val random = SecureRandom()
        val bytes = ByteArray(64)
        random.nextBytes(bytes)
        return Base64.encodeToString(
            bytes,
            Base64.NO_WRAP or Base64.URL_SAFE or Base64.NO_PADDING
        )
    }

    private fun getEncodedChallenge(): String {
        val random = SecureRandom()
        val bytes = ByteArray(32)
        random.nextBytes(bytes)
        return Base64.encodeToString(
            bytes,
            Base64.NO_WRAP or Base64.URL_SAFE or Base64.NO_PADDING
        )
    }

    fun signInWithPasskey(){}

    fun signInWithSavedCredential(activity: Activity) {
        viewModelScope.launch {
            try {
                val passwordCredential = getCredential(activity) ?: return@launch

                val signInSuccess = true
                // sign in logic using the returned password credential

                if (signInSuccess) {
                    //Indicate to the UI that we're now signed in.
                    signedInPasswordCredential.value = passwordCredential
                }
            }
            catch (e: Exception) {
                Log.e(TAG, "signInWithSavedCredential error", e)
            }
        }
    }

    private suspend fun getCredential(context: Context): PasswordCredential? {
        try {
            val getCredRequest = GetCredentialRequest(
                listOf(GetPasswordOption())
            )

            //pick saved credential
            val credentialResponse = credentialManager.getCredential(
                request = getCredRequest,
                context = context,
            )

            //Return credential
            return credentialResponse.credential as? PasswordCredential
        }

        catch (e: GetCredentialCancellationException) {
            return null
        }
        catch (e: NoCredentialException) {
            return null
        }
        catch (e: GetCredentialException) {
            Log.e(TAG, "GetCredentialException", e)
            throw e
        }
    }

    private suspend fun saveCredential(context: Context, username: String, password: String) {
        try {
            credentialManager.createCredential(
                request = CreatePasswordRequest(username, password),
                context = context,
            )
            Log.v(TAG, "createCredential success")

            signedInPasswordCredential.value = PasswordCredential(username, password)
        }
        catch (e: CreateCredentialCancellationException) {
            Log.v(TAG, "Cancelled")
        }
        catch (e: CreateCredentialException) {
            Log.v(TAG, "CreateCredentialException error", e)
        }
    }

    fun signIn(activity: Activity) {
        Log.d("TAG", "Sign-In")
        // Simulating a delay of 3 seconds using coroutines
        viewModelScope.launch {
            // Pause for 3 seconds
            delay(3000)
            // Update the isSignedIn value after the delay
            _isSignedIn.value = true
        }
    }

    fun signOut() {
        signedInPasswordCredential.value = null
    }
}