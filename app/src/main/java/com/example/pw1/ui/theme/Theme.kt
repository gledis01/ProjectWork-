package com.example.pw1.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

// Importing the necessary Material functions for light and dark color schemes
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

// Define the light and dark color schemes
private val LightColors = lightColorScheme(
    primary = PrimaryColor,         // Primary color
    onPrimary = PrimaryVariant, // Darker shade of primary color
    secondary = SecondaryColor      // Secondary color
)

private val DarkColors = darkColorScheme(
    primary = PrimaryColor,         // Primary color for dark theme
    onPrimary = PrimaryVariant, // Darker shade of primary for dark theme
    secondary = SecondaryColor      // Secondary color for dark theme
)
// Define custom shapes
val CustomShapes = Shapes(
    small = RoundedCornerShape(4.dp),   // Small components (e.g., Chips)
    medium = RoundedCornerShape(8.dp), // Medium components (e.g., Cards)
    large = RoundedCornerShape(16.dp)  // Large components (e.g., Dialogs)
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
        colorScheme = colors,  // Apply the selected color scheme
        typography = Typography,  // Use the Typography defined elsewhere
        shapes = CustomShapes,  // Use the Shapes defined elsewhere
        content = content  // This is the composable content to which the theme will be applied
    )
}
