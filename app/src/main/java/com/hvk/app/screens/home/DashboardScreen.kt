package com.hvk.app.screens.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.hvk.app.screens.home.tabs.HomeTab
import com.hvk.app.screens.home.tabs.PersonTab
import com.hvk.app.screens.home.tabs.SettingsTab


private val tabs = listOf(
    "Tab1" to Icons.Default.Home,
    "Tab2" to Icons.Default.Person,
    "Tab3" to Icons.Default.Settings,
)


class DashboardScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            TabNavigator(
                tab = HomeTab,
            ) {
                val navigator = LocalNavigator.currentOrThrow
                println("<> level : ${navigator.level}")
                Scaffold(
                    bottomBar = {
                        if (navigator.level == 2)
                            BottomAppBar(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .graphicsLayer {
                                        shape = RoundedCornerShape(15.dp)
                                        clip = true
                                    },
                            ) {
                                TabNavigationItems(tab = HomeTab)
                                TabNavigationItems(tab = PersonTab)
                                TabNavigationItems(tab = SettingsTab)
                            }
                    },
                ) {
                    println(it)
                    CurrentTab()
                }
            }
        }
    }
}


@Composable
fun RowScope.TabNavigationItems(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                tab.options.icon!!,
                contentDescription = tab.options.title,
            )
        },
    )
}
