package com.darwin.mangamvvmapp.navigation.utils

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title :String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val hasNews:Boolean,
)
