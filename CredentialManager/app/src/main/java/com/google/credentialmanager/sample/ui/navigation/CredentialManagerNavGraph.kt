/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.credentialmanager.sample.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.credentialmanager.sample.R
import com.google.credentialmanager.sample.ui.AccountRecoveryScreen
import com.google.credentialmanager.sample.ui.AuthenticationRoute
import com.google.credentialmanager.sample.ui.HelpScreen
import com.google.credentialmanager.sample.ui.HomeRoute
import com.google.credentialmanager.sample.ui.LearnMoreScreen
import com.google.credentialmanager.sample.ui.MainMenuScreen
import com.google.credentialmanager.sample.ui.NewHomeScreen
import com.google.credentialmanager.sample.ui.OtherOptionsScreen
import com.google.credentialmanager.sample.ui.PlaceholderScreen
import com.google.credentialmanager.sample.ui.SettingsScreen
import com.google.credentialmanager.sample.ui.ShrineAppScreen
import com.google.credentialmanager.sample.ui.SignInWithPasskeyScreen
import com.google.credentialmanager.sample.ui.SignInWithPasswordScreen
import com.google.credentialmanager.sample.ui.SignOutScreen
import com.google.credentialmanager.sample.ui.SignUpWithPasskeyScreen
import com.google.credentialmanager.sample.ui.SignUpWithPasswordScreen
import com.google.credentialmanager.sample.ui.SplashScreen
import com.google.credentialmanager.sample.ui.viewmodel.AuthenticationViewModel
import com.google.credentialmanager.sample.ui.viewmodel.HomeViewModel
import com.google.credentialmanager.sample.ui.viewmodel.PasskeysSignedSRoute
import com.google.credentialmanager.sample.ui.viewmodel.SplashViewModel

//This Navigation Graph is handling to and fro from Authentication to Contacts screens.
enum class ApplicationScreen(@StringRes val title: Int){
    AccountRecovery(title = R.string.account_recovery),
    Help(title = R.string.help),
    Home(title = R.string.home),
    LearnMore(title = R.string.learn_more),
    MainMenu(title = R.string.main_menu),
    OtherOptions(title = R.string.other_options),
    Placeholder(title = R.string.todo),
    Settings(title = R.string.settings),
    ShrineApp(title = R.string.app_name),
    SignInWithPasskey(title = R.string.sign_in),
    SignInWithPassword(title = R.string.sign_in),
    SignOut(title = R.string.sign_out),
    SignUpWithPasskey(title = R.string.sign_up),
    SignUpWithPassword(title = R.string.sign_up),
}

@Composable
fun CredentialManagerNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = CredManAppDestinations.AUTH_ROUTE,
    navigateToLogin: () -> Unit,
    navigateToHome: (Boolean) -> Unit,
    navigateToCreateAccount: () -> Unit
    ) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(CredManAppDestinations.AUTH_ROUTE) {
            val authViewModel = hiltViewModel<AuthenticationViewModel>()
            AuthenticationRoute(
                navigateToHome = navigateToHome,
                navigateToCreateAccount = navigateToCreateAccount,
                viewModel = authViewModel
            )
        }
        composable(CredManAppDestinations.PASSKEYS_ROUTE) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            PasskeysSignedSRoute(
                navigateToLogin = navigateToLogin,
                viewModel = homeViewModel
            )
        }
        composable(CredManAppDestinations.HOME_ROUTE) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeRoute(
                navigateToLogin = navigateToLogin,
                viewModel = homeViewModel
            )
        }
        composable(CredManAppDestinations.SPLASH_ROUTE) {
            val splashViewModel = hiltViewModel<SplashViewModel>()
            SplashScreen(
                splashViewModel = splashViewModel,
                navController
            )
        }

        composable(route = ApplicationScreen.AccountRecovery.name) {
            AccountRecoveryScreen(
                onAccountRecoveryButtonClicked = {
                    navController.navigate(ApplicationScreen.Placeholder.name)
                },
            )
        }
        composable(route = ApplicationScreen.Help.name) {
            HelpScreen()
        }

        composable(route = ApplicationScreen.Home.name) {
            NewHomeScreen(
                onSignInRequest = {
                    navController.navigate(ApplicationScreen.SignInWithPasskey.name)
                },
                onRegisterRequest = {
                    navController.navigate(ApplicationScreen.SignUpWithPasskey.name)
                },
                onRegisterResponse = {},
                proceedButtonClicked = {
                    navController.navigate(ApplicationScreen.MainMenu.name)
                },
            )
        }
        composable(route = ApplicationScreen.LearnMore.name) {
            LearnMoreScreen()
        }

        composable(route = ApplicationScreen.MainMenu.name) {
            MainMenuScreen(
                onGoToTheAppClicked = {
                    navController.navigate(ApplicationScreen.ShrineApp.name)
                },
                onSettingsButtonClicked = {
                    navController.navigate(ApplicationScreen.Settings.name)
                },
                onHelpButtonClicked = {
                    navController.navigate(ApplicationScreen.Help.name)
                },
                onSignOutButtonClicked = {
                    navController.navigate(ApplicationScreen.SignOut.name)
                },
            )
        }
        composable(route = ApplicationScreen.OtherOptions.name) {
            OtherOptionsScreen(
                onSignUpWithPasskeyButtonClicked = {
                    //authViewModel.signUpWithPasskey(activity,scope)
                    navController.navigate(ApplicationScreen.Placeholder.name)
                },
                onSignUpWithPasswordButtonClicked = {
                    navController.navigate(ApplicationScreen.Placeholder.name)
                },
                onSignUpWithPhoneButtonClicked = {
                    navController.navigate(ApplicationScreen.Placeholder.name)
                },
                onLearnMoreClicked = {
                    navController.navigate(ApplicationScreen.LearnMore.name)
                }
            )
        }

        composable(route = ApplicationScreen.Placeholder.name) {
            PlaceholderScreen()
        }

        composable(route = ApplicationScreen.Settings.name) {
            SettingsScreen(
                onCreatePasskeyClicked = {
                    navController.navigate(ApplicationScreen.Placeholder.name)
                },
                onChangePasswordClicked = {
                    navController.navigate(ApplicationScreen.Placeholder.name)
                },
                onHelpClicked = {
                    navController.navigate(ApplicationScreen.Help.name)
                }
            )
        }

        composable(route = ApplicationScreen.ShrineApp.name) {
            ShrineAppScreen()
        }

        composable(route = ApplicationScreen.SignInWithPasskey.name) {
            SignInWithPasskeyScreen(
                onSignInWithPasskeyClicked = {
                    //authViewModel.signInWithPasskey(activity,scope)
                    navController.navigate(ApplicationScreen.MainMenu.name)
                },
                onTroubleClicked = {
                    navController.navigate(ApplicationScreen.AccountRecovery.name)
                }
            )
        }

        composable(route = ApplicationScreen.SignInWithPassword.name) {
            SignInWithPasswordScreen(
                onSignInButtonClicked = {
                    navController.navigate(ApplicationScreen.Placeholder.name)
                },
                onSignUpButtonClicked = {
                    navController.navigate(ApplicationScreen.SignUpWithPasskey.name)
                },
            )
        }

        composable(route = ApplicationScreen.SignOut.name) {
            SignOutScreen()
        }

        composable(route = ApplicationScreen.SignUpWithPasskey.name) {
            SignUpWithPasskeyScreen(
                onRegisterButtonClicked = {
                    //authViewModel.signUpWithPasskey(activity,scope)
                    navController.navigate(ApplicationScreen.Placeholder.name)
                },
                onSignUpWithOtherMethodClicked = {
                    //authViewModel.signUpWithPasskey(activity, scope)
                    //navController.navigate(ApplicationScreen.SignUpWithPassword.name)
                    navController.navigate(ApplicationScreen.OtherOptions.name)
                }
            )
        }

        composable(route = ApplicationScreen.SignUpWithPassword.name) {
            SignUpWithPasswordScreen(
                onSignUpWithPasswordButtonClicked = {
                    navController.navigate(ApplicationScreen.Placeholder.name)
                },
            )
        }
    }
}