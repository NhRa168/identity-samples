package com.google.credentialmanager.sample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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


private const val TAG = "SignInWithPasskeyScreen"

@Composable
fun SignInWithPasskeyScreen(
    onSignInWithPasskeyClicked: () -> Unit,
    onTroubleClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column() {
            TextHeader(text = stringResource(R.string.sign_in))
        }
        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFFF0EFF0),
                )
                .height(250.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = "",
                label = { Text("Username") },
                onValueChange = { },
            )
            Spacer(Modifier.padding(20.dp))
            ShrineButton(
                onClick = { onSignInWithPasskeyClicked() })
            { Text(text = stringResource(R.string.sign_in_passkey)) }

            Spacer(Modifier.padding(10.dp))
            Text(
                text = stringResource(R.string.trouble),
                modifier = Modifier.clickable(onClick = onTroubleClicked))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignInWithPasskeyScreenPreview(){
    CredentialManagerTheme {
        SignInWithPasskeyScreen(
            onSignInWithPasskeyClicked = {},
            onTroubleClicked = {}
        )
    }
}