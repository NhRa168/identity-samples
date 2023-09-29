package com.google.credentialmanager.sample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.credentialmanager.sample.R
import com.google.credentialmanager.sample.ui.common.ShrineButton
import com.google.credentialmanager.sample.ui.common.TextHeader
import com.google.credentialmanager.sample.ui.theme.CredentialManagerTheme

private const val TAG = "onSignUpWithPasskeyScreen"

@Composable
fun SignUpWithPasskeyScreen(
    onRegisterButtonClicked: () -> Unit = {},
    onSignUpWithOtherMethodClicked: () -> Unit = {}
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
        ) {
            TextHeader(text = stringResource(R.string.create_account))
            Column(
                modifier = Modifier
                    .background(
                        color = Color(0xFFFAF2F1))
                    .fillMaxWidth()
                    .padding(25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = "",
                    label = { Text(stringResource(R.string.name_label)) },
                    onValueChange = { },
                )

                Spacer(Modifier.padding(20.dp))

                TextField(
                    value = "",
                    label = { Text(stringResource(R.string.email_label)) },
                    onValueChange = { },
                )

                Spacer(Modifier.padding(20.dp))

                ShrineButton(
                    onClick = { onRegisterButtonClicked() },
                    color = Color(0xFFDFBFC0)
                ) {
                    Text(text = stringResource(R.string.sign_up_passkey)) }

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = stringResource(R.string.or))

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = stringResource(R.string.other_options),
                    modifier = Modifier.clickable(
                        onClick = { onSignUpWithOtherMethodClicked() }))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpWithPasskeyScreenPreview() {
    CredentialManagerTheme {
        SignUpWithPasskeyScreen(
            onRegisterButtonClicked = {->},
            onSignUpWithOtherMethodClicked = {->}
        )
    }
}