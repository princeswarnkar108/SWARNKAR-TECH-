package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = Sky500,
    secondary = Sky100,
    tertiary = AccentYellow,
    background = Slate900,
    surface = Slate800,
    onPrimary = Color.White,
    onSecondary = Sky100,
    onBackground = Slate50,
    onSurface = Slate50,
    outline = Slate800,
    surfaceVariant = Slate800,
    onSurfaceVariant = Slate400
  )

private val LightColorScheme =
  lightColorScheme(
    primary = Sky500,
    secondary = Sky100,
    tertiary = AccentYellow,
    background = Slate50,             // #F8FAFC
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Sky600,
    onBackground = Slate800,          // #1E293B
    onSurface = Slate800,
    outline = Blue50,
    surfaceVariant = Sky50,
    onSurfaceVariant = Slate500
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Disabling dynamic colors by default to ensure our highly polished Vibrant Palette colors are drawn on all devices
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
