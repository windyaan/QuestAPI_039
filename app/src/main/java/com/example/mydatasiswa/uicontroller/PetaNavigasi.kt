package com.example.mydatasiswa.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mydatasiswa.view.DestinasiHome
import com.example.mydatasiswa.view.HomeScreen

@Composable
fun DataSiswaApp(navController: NavHostController = rememberNavController(),
                 modifier: Modifier){
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController:NavHostController,
    modifier: Modifier = Modifier
){
    NavHost (navController = navController, startDestination = DestinasiHome.route,
        modifier = Modifier){
        composable(DestinasiHome.route){
            HomeScreen()
        }
    }
}