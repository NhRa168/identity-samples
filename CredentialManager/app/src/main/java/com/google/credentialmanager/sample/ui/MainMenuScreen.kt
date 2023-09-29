package com.google.credentialmanager.sample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.credentialmanager.sample.R
import com.google.credentialmanager.sample.ui.common.ShrineButton
import com.google.credentialmanager.sample.ui.common.TextHeader
import com.google.credentialmanager.sample.ui.theme.CredentialManagerTheme


private const val TAG = "MainMenuScreen"

@Composable
fun MainMenuScreen(
    onGoToTheAppClicked: () -> Unit,
    onSettingsButtonClicked: () -> Unit,
    onHelpButtonClicked: () -> Unit,
    onSignOutButtonClicked: () -> Unit,
){
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
            TextHeader(text = stringResource(R.string.main_menu))
        }
        Spacer(modifier = Modifier.padding(30.dp))

        ShrineButton(
            onClick = onGoToTheAppClicked) {
            Text(text = ("Go to the App")) }

        ShrineButton(
            onClick = onSettingsButtonClicked,
        ) { Text(stringResource(R.string.settings)) }

        ShrineButton(
            onClick = onHelpButtonClicked,
        ) { Text(stringResource(R.string.help)) }

        ShrineButton(
            onClick = onSignOutButtonClicked,
        ) { Text(stringResource(R.string.sign_out)) }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuScreenPreview() {
    CredentialManagerTheme {
        MainMenuScreen(
            onGoToTheAppClicked = {},
            onSettingsButtonClicked = {},
            onHelpButtonClicked = {},
            onSignOutButtonClicked = {},
        )
    }
}