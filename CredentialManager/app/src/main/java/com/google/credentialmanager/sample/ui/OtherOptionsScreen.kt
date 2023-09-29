package com.google.credentialmanager.sample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.credentialmanager.sample.R
import com.google.credentialmanager.sample.ui.common.PasskeyInfo
import com.google.credentialmanager.sample.ui.common.ShrineButton
import com.google.credentialmanager.sample.ui.common.TextHeader
import com.google.credentialmanager.sample.ui.theme.CredentialManagerTheme
import com.google.credentialmanager.sample.ui.theme.light_button


private const val TAG = "OtherOptionsScreen"

@Composable
fun OtherOptionsScreen(
    onSignUpWithPasskeyButtonClicked: () -> Unit = {},
    onSignUpWithPasswordButtonClicked: () -> Unit = {},
    onSignUpWithPhoneButtonClicked: () -> Unit = {},
    onLearnMoreClicked: () -> Unit = {},
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextHeader(text = stringResource(R.string.other_options))
        PasskeyInfo(onLearnMoreClicked)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShrineButton(
                onClick = onSignUpWithPasskeyButtonClicked,
            ) { Text(text = stringResource(R.string.sign_up_passkey)) }

                ShrineButton(
                    color = light_button,
                    onClick = onSignUpWithPasswordButtonClicked,
                ) {
                    Text(text = stringResource(R.string.sign_up_password)) }

                ShrineButton(
                    color = light_button,
                    onClick = onSignUpWithPhoneButtonClicked,
                ) {
                    Text(text = stringResource(R.string.sign_up_phone)) }
            }
        }
    }
@Preview(showBackground = true)
@Composable
fun OtherOptionsScreenPreview() {
    CredentialManagerTheme {
        OtherOptionsScreen()
    }
}