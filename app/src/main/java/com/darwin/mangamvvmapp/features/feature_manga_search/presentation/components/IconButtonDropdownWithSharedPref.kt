package com.darwin.mangamvvmapp.features.feature_manga_search.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.service.PreferencesManager

@Composable
fun IconButtonDropdownWithSharedPref() {
    val context = LocalContext.current // Get the current context
    val preferencesManager = PreferencesManager(context)

    // State variables
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember {
        mutableStateOf(preferencesManager.getSelectedOption() ?: "Select an option")
    }

    Column() {
        // Display the selected option

        // IconButton to trigger the dropdown
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Dropdown Menu"
            )
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            val options = listOf(Constants.MANGANATO,Constants.KUNMANGA)
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = option
                        preferencesManager.saveSelectedOption(option) // Save selected option
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            color = if (selectedOption == option) MaterialTheme.colorScheme.onBackground else  MaterialTheme.colorScheme.inverseOnSurface
                        )
                    }
                )
            }
        }
    }
}