package com.google.credentialmanager.sample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CreatePublicKeyCredentialResponse
import com.google.credentialmanager.sample.R
import com.google.credentialmanager.sample.ui.common.LogoHeading
import com.google.credentialmanager.sample.ui.common.ShrineButton
import com.google.credentialmanager.sample.ui.theme.CredentialManagerTheme
import com.google.credentialmanager.sample.ui.theme.light_button


private const val TAG = "HomeScreen"

@Composable
fun NewHomeScreen(
    onSignInRequest: () -> Unit,
    onRegisterRequest: () -> Unit,
    onRegisterResponse: (CreatePublicKeyCredentialResponse) -> Unit,
    proceedButtonClicked: () -> Unit,
    ) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeading()
        Spacer(modifier = Modifier.padding(20.dp))
        ShrineButton(
            onClick = { onSignInRequest() }) {
            Text(text = stringResource(id = R.string.sign_in))
        }

        ShrineButton(
            color = light_button,
            onClick = { onRegisterRequest() }) {
            Text(text = stringResource(id = R.string.sign_up))
        }

        ShrineButton(
            onClick = { proceedButtonClicked() }) {
            Text(text = "Proceed without Signing In")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun NewHomeScreenPreview(){
    CredentialManagerTheme {
        NewHomeScreen(
            onSignInRequest  = {},
            onRegisterRequest = {},
            onRegisterResponse = {},
            proceedButtonClicked = {},
        )
    }
}