package com.hvk.app.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator

data class BottomMessageScreen(
    val message: String,
    val wrapContent: Boolean = true
) : AndroidScreen() {

    @Composable
    override fun Content() {
        LifecycleEffect(
            onStarted = { Log.d("Navigator", "Start screen #$message") },
            onDisposed = { Log.d("Navigator", "Dispose screen #$message") },
        )

        val bot = LocalBottomSheetNavigator.current

        Row(
            modifier = Modifier
                .run {
                    if (wrapContent) padding(vertical = 16.dp).wrapContentHeight()
                    else fillMaxSize()
                }
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )

            Button(
                onClick = { bot.hide() },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(text = "Tamam")
            }
        }
    }
}