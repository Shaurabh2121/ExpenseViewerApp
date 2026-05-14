package com.example.expenseviewerapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetExample() {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 150.dp,
        sheetContent = {
            // LazyColumn inside the bottom sheet
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp) // Giving it a height so it doesn't take full screen immediately if not needed
            ) {
                items(20) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(if (index % 2 == 0) Color.LightGray.copy(alpha = 0.3f) else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Item ${index + 1}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        // Main Screen Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Main Content Area",
                modifier = Modifier.padding(top = 32.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
