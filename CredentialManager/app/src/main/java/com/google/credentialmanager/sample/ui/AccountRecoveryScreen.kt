package com.google.credentialmanager.sample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
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


private const val TAG = "AccountRecoveryScreen"

@Composable
fun AccountRecoveryScreen(
    onAccountRecoveryButtonClicked: () -> Unit,
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
            TextHeader(text = stringResource(R.string.account_recovery))
        }
        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFFF0EFF0),
                )
                .height(200.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = "",
                label = { Text(stringResource(R.string.email_label)) },
                onValueChange = { },
                shape = RoundedCornerShape(15.dp),
            )

            Spacer(Modifier.padding(20.dp))
            ShrineButton(
                onClick = onAccountRecoveryButtonClicked,
                Modifier.widthIn(min = 280.dp)
            ) { Text(text = stringResource(R.string.send_reset)) }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun AccountRecoveryWithPasskeyScreenPreview() {
    CredentialManagerTheme {
        AccountRecoveryScreen(
            onAccountRecoveryButtonClicked = {},
        )
    }
}
