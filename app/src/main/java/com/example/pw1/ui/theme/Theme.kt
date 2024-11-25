package com.example.pw1.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

// Importing the necessary Material functions for light and dark color schemes
import androidx.compose.material.lightColors
import androidx.compose.material.darkColors

// Define the light and dark color schemes
private val LightColors = lightColors(
    primary = PrimaryColor,         // Primary color
    primaryVariant = PrimaryVariant, // Darker shade of primary color
    secondary = SecondaryColor      // Secondary color
)

private val DarkColors = darkColors(
    primary = PrimaryColor,         // Primary color for dark theme
    primaryVariant = PrimaryVariant, // Darker shade of primary for dark theme
    secondary = SecondaryColor      // Secondary color for dark theme
)

@Composable
fun MoodTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Automatically check system theme preference
    content: @Composable () -> Unit            // The composable content that the theme will apply to
) {
    // Determine whether to use the light or dark color scheme
    val colors = if (darkTheme) DarkColors else LightColors

    // Wrap the content with MaterialTheme which applies the theme globally
    MaterialTheme(
        colors = colors,  // Apply the selected color scheme
        typography = Typography,  // Use the Typography defined elsewhere
        shapes = Shapes,  // Use the Shapes defined elsewhere
        content = content  // This is the composable content to which the theme will be applied
    )
}
