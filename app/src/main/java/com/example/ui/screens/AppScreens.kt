package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.util.SoundEffects
import com.example.data.SubjectProgress
import com.example.data.UserProfile
import com.example.viewmodel.HomeTeacherViewModel

// Custom logo canvas drawn
@Composable
fun HomeTeacherLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(160.dp).testTag("app_logo")) {
        val w = size.width
        val h = size.height

        // Outer circular educational halo
        drawCircle(
            color = Color(0xFFE3F2FD),
            radius = w * 0.46f,
            center = Offset(w / 2, h / 2)
        )
        drawCircle(
            color = Color(0xFFBBDEFB),
            radius = w * 0.38f,
            center = Offset(w / 2, h / 2)
        )

        // Draw Cover of book
        val coverPath = Path().apply {
            moveTo(w * 0.20f, h * 0.68f)
            quadraticTo(w * 0.38f, h * 0.58f, w * 0.50f, h * 0.68f)
            quadraticTo(w * 0.62f, h * 0.58f, w * 0.80f, h * 0.68f)
            lineTo(w * 0.80f, h * 0.42f)
            quadraticTo(w * 0.62f, h * 0.32f, w * 0.50f, h * 0.42f)
            quadraticTo(w * 0.38f, h * 0.32f, w * 0.20f, h * 0.42f)
            close()
        }
        drawPath(path = coverPath, color = Color(0xFF0288D1)) // Sky Blue Cover

        // White Pages
        val leftPage = Path().apply {
            moveTo(w * 0.23f, h * 0.64f)
            quadraticTo(w * 0.38f, h * 0.55f, w * 0.49f, h * 0.63f)
            lineTo(w * 0.49f, h * 0.43f)
            quadraticTo(w * 0.38f, h * 0.35f, w * 0.23f, h * 0.43f)
            close()
        }
        drawPath(path = leftPage, color = Color.White)

        val rightPage = Path().apply {
            moveTo(w * 0.77f, h * 0.64f)
            quadraticTo(w * 0.62f, h * 0.55f, w * 0.51f, h * 0.63f)
            lineTo(w * 0.51f, h * 0.43f)
            quadraticTo(w * 0.62f, h * 0.35f, w * 0.77f, h * 0.43f)
            close()
        }
        drawPath(path = rightPage, color = Color.White)

        // Graduation cap symbol
        val capPath = Path().apply {
            moveTo(w * 0.50f, h * 0.16f) // Top corner
            lineTo(w * 0.72f, h * 0.25f) // Right corner
            lineTo(w * 0.50f, h * 0.34f) // Bottom corner
            lineTo(w * 0.28f, h * 0.25f) // Left corner
            close()
        }
        drawPath(path = capPath, color = Color(0xFF263238)) // Slate Black Cap

        // Cap Bottom Base
        val baseLeft = w * 0.38f
        val baseRight = w * 0.62f
        val baseTop = h * 0.27f
        val baseBottom = h * 0.34f
        drawRect(
            color = Color(0xFF263238),
            topLeft = Offset(baseLeft, baseTop),
            size = Size(baseRight - baseLeft, baseBottom - baseTop)
        )

        // Tassel (Golden)
        drawLine(
            color = Color(0xFFFFB300),
            start = Offset(w * 0.50f, h * 0.25f),
            end = Offset(w * 0.74f, h * 0.30f),
            strokeWidth = 5f
        )
        drawCircle(
            color = Color(0xFFFFB300),
            radius = 7f,
            center = Offset(w * 0.74f, h * 0.30f)
        )
    }
}

// 1. Splash Screen
@Composable
fun SplashScreen(
    onStartLearningClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            // Top Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 40.dp)
            ) {
                Text(
                    text = "🏠 Home Teacher",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0EA5E9),
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = (-0.5).sp
                    ),
                    modifier = Modifier.testTag("app_title")
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your Friendly Digital Tutor",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            // Center Logo
            HomeTeacherLogo()

            // Bottom Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                Text(
                    text = "“Learn Daily, Grow Slowly, Become Strongly.” ✨",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFF1E293B),
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp).testTag("motto_text")
                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = onStartLearningClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0EA5E9)),
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("start_learning_button")
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Start Learning",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Start Icon",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

// 2. Login Screen
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: HomeTeacherViewModel,
    modifier: Modifier = Modifier
) {
    var isPhoneLoginMode by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("princestmd2008@gmail.com") } // Prepopulate user details
    var name by remember { mutableStateOf("Prince") }
    var phone by remember { mutableStateOf("+91 9999999999") }
    var otpSms by remember { mutableStateOf("") }
    var otpSent by remember { mutableStateOf(false) }
    var showGoogleSheet by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
        ) {
            Text(
                text = "Welcome to Home Teacher! 🔐",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1E293B),
                    letterSpacing = (-0.5).sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp).testTag("login_header")
            )

            // Wrap Form content inside a beautiful white Card representing brand panel
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEFF6FF)),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(24.dp), clip = false)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!isPhoneLoginMode) {
                        Text(
                            text = "Create Profile & Learn",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = Color(0xFF1E293B),
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Full Name", color = Color(0xFF64748B)) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF0EA5E9),
                                unfocusedBorderColor = Color(0xFFE2E8F0)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth().testTag("name_field"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "User Icon", tint = Color(0xFF0EA5E9)) }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Gmail Address", color = Color(0xFF64748B)) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF0EA5E9),
                                unfocusedBorderColor = Color(0xFFE2E8F0)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth().testTag("email_field"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon", tint = Color(0xFF0EA5E9)) }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                if (name.isNotBlank() && email.isNotBlank()) {
                                    viewModel.loginOrSignUp(name, email, "")
                                    onLoginSuccess()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0EA5E9)),
                            shape = RoundedCornerShape(32.dp),
                            modifier = Modifier.fillMaxWidth().height(52.dp).testTag("email_login_submit_btn")
                        ) {
                            Text("Create Profile & Continue ➡", fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color(0xFFF1F5F9))

                        Spacer(modifier = Modifier.height(8.dp))

                        // One-click Gmail Single Sign On simulator Card button and font
                        Button(
                            onClick = { showGoogleSheet = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8FAFC)),
                            shape = RoundedCornerShape(32.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                            modifier = Modifier.fillMaxWidth().height(52.dp).testTag("gmail_sso_btn")
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "Gmail Logo",
                                    tint = Color(0xFFEA4335)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Sign in with Google",
                                    color = Color(0xFF475569),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        TextButton(onClick = { isPhoneLoginMode = true }) {
                            Text("Or use Phone OTP Login Instead ➡", color = Color(0xFF0284C7), fontWeight = FontWeight.Bold)
                        }

                    } else {
                        // Phone Number OTP Flow
                        Text(
                            text = "Phone Number OTP Login 📱",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = Color(0xFF1E293B),
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            label = { Text("Phone Number", color = Color(0xFF64748B)) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF0EA5E9),
                                unfocusedBorderColor = Color(0xFFE2E8F0)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            modifier = Modifier.fillMaxWidth().testTag("phone_field"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Phone", tint = Color(0xFF0EA5E9)) }
                        )

                        if (otpSent) {
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedTextField(
                                value = otpSms,
                                onValueChange = { otpSms = it },
                                label = { Text("Enter 6-Digit SMS OTP", color = Color(0xFF64748B)) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFFF59E0B),
                                    unfocusedBorderColor = Color(0xFFE2E8F0)
                                ),
                                placeholder = { Text("123456") },
                                shape = RoundedCornerShape(16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth().testTag("otp_field"),
                                singleLine = true,
                                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "OTP", tint = Color(0xFFF59E0B)) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Simulated Active SMS OTP: '123456'",
                                color = Color(0xFF64748B),
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        if (!otpSent) {
                            Button(
                                onClick = {
                                    if (phone.isNotBlank()) {
                                        otpSent = true
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0EA5E9)),
                                shape = RoundedCornerShape(32.dp),
                                modifier = Modifier.fillMaxWidth().height(52.dp).testTag("send_otp_btn")
                            ) {
                                Text("Send Verification OTP SMS", fontWeight = FontWeight.Bold)
                            }
                        } else {
                            Button(
                                onClick = {
                                    if (otpSms == "123456" || otpSms.isNotBlank()) {
                                        viewModel.loginOrSignUp("Tutor Hero", "teacher@example.com", phone)
                                        onLoginSuccess()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF59E0B)),
                                shape = RoundedCornerShape(32.dp),
                                modifier = Modifier.fillMaxWidth().height(52.dp).testTag("verify_otp_btn")
                            ) {
                                Text("Verify OTP & Continue ➡", fontWeight = FontWeight.Bold)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        TextButton(onClick = {
                            isPhoneLoginMode = false
                            otpSent = false
                            otpSms = ""
                        }) {
                            Text("⬅ Go Back to Profile Sign Up", color = Color(0xFF0284C7), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }

    // Google One Tap Native SSO Bottom sheet modal simulator Dialogue
    if (showGoogleSheet) {
        AlertDialog(
            onDismissRequest = { showGoogleSheet = false },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showGoogleSheet = false }) {
                    Text("Cancel", color = Color(0xFF64748B))
                }
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccountCircle, "Google logo", tint = Color(0xFFEA4335))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sign in with Google", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            },
            text = {
                Column {
                    Text("Choose an active Google account to proceed to Home Teacher app:", color = Color(0xFF64748B))
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showGoogleSheet = false
                                viewModel.loginOrSignUp("Prince", "princestmd2008@gmail.com", "")
                                onLoginSuccess()
                            },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF0EA5E9)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("P", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("Prince", fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                                Text("princestmd2008@gmail.com", fontSize = 12.sp, color = Color(0xFF64748B))
                            }
                        }
                    }
                }
            }
        )
    }
}

// 3. Home Screen (Subject Selection)
@Composable
fun HomeScreen(
    onSubjectSelected: (String) -> Unit,
    onProfileClicked: () -> Unit,
    viewModel: HomeTeacherViewModel,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.userProfile.collectAsStateWithLifecycle()
    val mathProg by viewModel.mathProgress.collectAsStateWithLifecycle()
    val englishProg by viewModel.englishProgress.collectAsStateWithLifecycle()
    val hindiProg by viewModel.hindiProgress.collectAsStateWithLifecycle()

    val mathUnlocked = mathProg.count { it.isUnlocked }
    val englishUnlocked = englishProg.count { it.isUnlocked }
    val hindiUnlocked = hindiProg.count { it.isUnlocked }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
                        clip = false
                    )
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .background(Color.White)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Namaste, ${profile?.name ?: "Learner"}! 👋",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E293B)
                            )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Icon(Icons.Default.Star, contentDescription = "Stars", tint = Color(0xFFFCD34D), modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${profile?.totalStars ?: 0} Stars",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold, color = Color(0xFF0284C7))
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "•  ${profile?.rank ?: "Scholar"}",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium, color = Color(0xFF64748B))
                            )
                        }
                    }

                    // Avatar button matching design: w-10 h-10 border-2 boundary
                    IconButton(
                        onClick = onProfileClicked,
                        modifier = Modifier
                            .size(44.dp)
                            .testTag("profile_avatar_btn")
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(Color(0xFFF0F9FF))
                                .border(2.dp, Color(0xFFE0F2FE), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = (profile?.name ?: "P").take(1).uppercase(),
                                color = Color(0xFF0284C7),
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            VibrantBottomBar(
                currentTab = "home",
                onHomeClick = {},
                onStatsClick = onProfileClicked,
                onSettingsClick = onProfileClicked
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            // Daily Streak Tracker Row styled as active/white boundary in palette
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEFF6FF)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .testTag("streak_banner")
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Welcome back, ${profile?.name ?: "Learner"}!",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFF64748B),
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(text = "🔥", fontSize = 20.sp)
                            Text(
                                text = "${profile?.streakDays ?: 1} Day Streak!",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E293B)
                                )
                            )
                        }
                    }

                    Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "PROGRESS",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF94A3B8),
                            letterSpacing = 1.sp
                        )
                        // Progress bar row matching the HTML theme
                        val totalCompleted = mathUnlocked + englishUnlocked + hindiUnlocked
                        val totalPossible = 20 // 8 + 6 + 6
                        val progressFraction = (totalCompleted.toFloat() / totalPossible.toFloat()).coerceIn(0.15f, 1.0f)
                        Box(
                            modifier = Modifier
                                .width(96.dp)
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFFF1F5F9))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(progressFraction)
                                    .background(Color(0xFF0EA5E9))
                            )
                        }
                    }
                }
            }

            // Picks Header Section
            Text(
                text = "PICK A SUBJECT",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF94A3B8),
                    letterSpacing = 1.5.sp
                ),
                modifier = Modifier.padding(bottom = 12.dp, start = 4.dp)
            )

            // Math Gradient card (Sky color)
            HomeSubjectButton(
                title = "Mathematics",
                subtitle = "Level $mathUnlocked: Division",
                emoji = "➕",
                gradient = Brush.linearGradient(listOf(Color(0xFF0EA5E9), Color(0xFF0284C7))),
                tag = "math_button",
                onClick = { onSubjectSelected("Math") }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // English Gradient card (Indigo color)
            HomeSubjectButton(
                title = "English Section",
                subtitle = "Level $englishUnlocked: Grammar",
                emoji = "✍️",
                gradient = Brush.linearGradient(listOf(Color(0xFF6366F1), Color(0xFF4F46E5))),
                tag = "english_button",
                onClick = { onSubjectSelected("English") }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Hindi Gradient card (Rose color)
            HomeSubjectButton(
                title = "हिन्दी (Hindi)",
                subtitle = "Level $hindiUnlocked: Vyakaran",
                emoji = "📝",
                gradient = Brush.linearGradient(listOf(Color(0xFFF43F5E), Color(0xFFE11D48))),
                tag = "hindi_button",
                onClick = { onSubjectSelected("Hindi") }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Short motivational bottom helper
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF1F5F9)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = "Tips",
                        tint = Color(0xFFFCD34D),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tips: Earn 5+ correct queries to unlock future levels!",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun HomeSubjectButton(
    title: String,
    subtitle: String,
    emoji: String,
    gradient: Brush,
    tag: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(32.dp),
                ambientColor = Color(0x220EA5E9),
                spotColor = Color(0x110EA5E9)
            )
            .clip(RoundedCornerShape(32.dp))
            .background(gradient)
            .clickable { onClick() }
            .testTag(tag)
    ) {
        // Decorative design element in subject button
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            drawCircle(
                color = Color.White.copy(alpha = 0.06f),
                radius = h * 0.75f,
                center = Offset(w * 0.95f, h * 0.8f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Left icon block: 56.dp container with white opacity backdrop
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = emoji,
                        fontSize = 28.sp
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            fontSize = 18.sp,
                            letterSpacing = (-0.3).sp
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White.copy(alpha = 0.85f),
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
            
            // Right Side: Forward Arrow Circle
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Arrow navigation icon",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun VibrantBottomBar(
    currentTab: String,
    onHomeClick: () -> Unit,
    onStatsClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                clip = false
            ),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(horizontal = 24.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                label = "HOME",
                iconEmoji = "🏠",
                isActive = currentTab == "home",
                onClick = onHomeClick
            )

            BottomNavItem(
                label = "STATS",
                iconEmoji = "🏆",
                isActive = currentTab == "stats",
                onClick = onStatsClick
            )

            BottomNavItem(
                label = "SETTINGS",
                iconEmoji = "⚙️",
                isActive = currentTab == "settings",
                onClick = onSettingsClick
            )
        }
    }
}

@Composable
fun RowScope.BottomNavItem(
    label: String,
    iconEmoji: String,
    isActive: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .clickable { onClick() }
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Active bar indicator
        Box(
            modifier = Modifier
                .width(36.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(if (isActive) Color(0xFF0EA5E9) else Color.Transparent)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = iconEmoji, fontSize = 22.sp)
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = if (isActive) Color(0xFF0EA5E9) else Color(0xFF94A3B8)
        )
    }
}

// 4. Subject Levels Screen
@Composable
fun LevelsScreen(
    subject: String,
    onLevelSelected: (Int) -> Unit,
    onBackClicked: () -> Unit,
    viewModel: HomeTeacherViewModel,
    modifier: Modifier = Modifier
) {
    val progressFlow = when (subject.lowercase()) {
        "math" -> viewModel.mathProgress
        "english" -> viewModel.englishProgress
        "hindi" -> viewModel.hindiProgress
        else -> viewModel.mathProgress
    }
    val levels by progressFlow.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                        clip = false
                    )
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(Color.White)
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF1E293B)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$subject Course Levels 🎯",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        ),
                        modifier = Modifier.testTag("levels_header")
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Progress as you learn. Earn stars by completing quizzes correctly!",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF64748B)),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize().padding(bottom = 16.dp)
            ) {
                items(levels) { level ->
                    LevelCardItem(
                        level = level,
                        onClick = {
                            if (level.isUnlocked) {
                                viewModel.startQuiz(subject, level.levelIndex)
                                onLevelSelected(level.levelIndex)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LevelCardItem(
    level: SubjectProgress,
    onClick: () -> Unit
) {
    val isEasy = level.difficulty.lowercase() == "easy"
    val isMedium = level.difficulty.lowercase() == "medium"
    val badgeColors = when {
        isEasy -> Pair(Color(0xFF10B981), Color(0xFFECFDF5)) // Emerald
        isMedium -> Pair(Color(0xFFF59E0B), Color(0xFFFFFBEB)) // Amber
        else -> Pair(Color(0xFFF43F5E), Color(0xFFFFF1F2)) // Rose
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = level.isUnlocked) { onClick() }
            .testTag("level_item_${level.levelIndex}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (level.isUnlocked) Color.White else Color(0xFFF1F5F9).copy(alpha = 0.5f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (level.isUnlocked) Color(0xFFEFF6FF) else Color(0xFFE2E8F0)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                // Circular Index badge
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(if (level.isUnlocked) Color(0xFFE0F2FE) else Color(0xFFF1F5F9)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${level.levelIndex}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = if (level.isUnlocked) Color(0xFF0284C7) else Color(0xFF94A3B8)
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = level.levelName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (level.isUnlocked) Color(0xFF1E293B) else Color(0xFF64748B)
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Difficulty Badge
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(badgeColors.second)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = level.difficulty,
                                color = badgeColors.first,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        }

                        // Star displays
                        if (level.isUnlocked) {
                            Spacer(modifier = Modifier.width(12.dp))
                            Row {
                                repeat(3) { starIdx ->
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Star badge indicator",
                                        tint = if (starIdx < level.starsScore) Color(0xFFFCD34D) else Color(0xFFE2E8F0),
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Lock / Play Action Button
            if (level.isUnlocked) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF0F9FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Start Lesson",
                        tint = Color(0xFF0EA5E9),
                        modifier = Modifier.size(22.dp)
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Level locked icon",
                    tint = Color(0xFF94A3B8),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

// 5. Quiz Screen
@Composable
fun QuizScreen(
    onBackClicked: () -> Unit,
    onQuizFinished: () -> Unit,
    viewModel: HomeTeacherViewModel,
    modifier: Modifier = Modifier
) {
    val subject by viewModel.currentSubject.collectAsStateWithLifecycle()
    val levelIndex by viewModel.currentLevelIndex.collectAsStateWithLifecycle()
    val questions by viewModel.quizQuestions.collectAsStateWithLifecycle()
    val qIndex by viewModel.currentQuestionIndex.collectAsStateWithLifecycle()
    val selectedOption by viewModel.selectedOption.collectAsStateWithLifecycle()
    val showFeedback by viewModel.showFeedback.collectAsStateWithLifecycle()
    val isCorrect by viewModel.isCorrect.collectAsStateWithLifecycle()
    val activeScore by viewModel.score.collectAsStateWithLifecycle()
    val completed by viewModel.quizCompleted.collectAsStateWithLifecycle()

    // Safety fallback
    if (questions.isEmpty() || qIndex >= questions.size) {
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFF0EA5E9))
        }
        return
    }

    val currentQuestion = questions[qIndex]

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onBackClicked) {
                            Icon(Icons.Default.Close, contentDescription = "Exit Quiz", tint = Color(0xFF64748B))
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$subject • Level $levelIndex",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        )
                    }

                    // Score Tracker tag
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF0F9FF))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Check, "Score tally icon", tint = Color(0xFF0EA5E9), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Score: $activeScore/10",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0284C7),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Level progress decoration line matching Vibrant design
            val percentage = (qIndex.toFloat() / questions.size.toFloat())
            LinearProgressIndicator(
                progress = { percentage },
                color = Color(0xFF0EA5E9),
                trackColor = Color(0xFFE0F2FE),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .testTag("quiz_progress_bar")
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "QUESTION ${qIndex + 1} OF ${questions.size}",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF94A3B8),
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Beautiful clean question container card
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEFF6FF)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 3.dp, shape = RoundedCornerShape(24.dp), clip = false)
                    .padding(bottom = 24.dp)
                    .testTag("question_card")
            ) {
                Text(
                    text = currentQuestion.questionText,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        lineHeight = 28.sp
                    ),
                    modifier = Modifier.padding(24.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Options List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                currentQuestion.options.forEach { option ->
                    val isSelected = selectedOption == option
                    val optionBorderColor = if (isSelected) Color(0xFF0EA5E9) else Color(0xFFE2E8F0)
                    val optionBgColor = if (isSelected) Color(0xFFF1F5F9) else Color.White

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !showFeedback) { viewModel.selectOption(option) }
                            .testTag("option_$option"),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = optionBgColor),
                        border = androidx.compose.foundation.BorderStroke(if (isSelected) 2.dp else 1.dp, optionBorderColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { if (!showFeedback) viewModel.selectOption(option) },
                                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF0EA5E9))
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E293B)
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action section at bottom
            if (!showFeedback) {
                Button(
                    onClick = { viewModel.submitAnswer() },
                    enabled = selectedOption != null,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0EA5E9)),
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(bottom = 12.dp)
                        .testTag("submit_answer_button")
                ) {
                    Text("Check Answer", fontWeight = FontWeight.Bold)
                }
            } else {
                // Feedback Panel Layout
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .testTag("feedback_card"),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isCorrect) Color(0xFFECFDF5) else Color(0xFFFFF1F2)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isCorrect) Color(0xFFA7F3D0) else Color(0xFFFECDD3))
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Error,
                                contentDescription = if (isCorrect) "Correct" else "Wrong",
                                tint = if (isCorrect) Color(0xFF047857) else Color(0xFFBE123C),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isCorrect) "Excellent! Correct Answer 🎉" else "Oops! That's not correct.",
                                fontWeight = FontWeight.Bold,
                                color = if (isCorrect) Color(0xFF047857) else Color(0xFFBE123C)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isCorrect) currentQuestion.explanation else "Try again! ${currentQuestion.explanation}",
                            fontSize = 13.sp,
                            color = Color(0xFF475569)
                        )
                        Spacer(modifier = Modifier.height(14.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            if (!isCorrect) {
                                Button(
                                    onClick = { viewModel.retryQuestion() },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF43F5E)),
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier.testTag("retry_answer_button")
                                ) {
                                    Icon(Icons.Default.Refresh, "Retry logo icon", tint = Color.White)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Retry Question", fontWeight = FontWeight.Bold)
                                }
                            } else {
                                Button(
                                    onClick = {
                                        if (qIndex + 1 >= questions.size) {
                                            onQuizFinished()
                                        } else {
                                            viewModel.nextQuestion()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier.testTag("next_question_button")
                                ) {
                                    Text(
                                        text = if (qIndex + 1 >= questions.size) "Finish Quiz ⮕" else "Next Question ⮕",
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// 6. Result Screen
@Composable
fun ResultScreen(
    onBackToLevels: () -> Unit,
    viewModel: HomeTeacherViewModel,
    modifier: Modifier = Modifier
) {
    val quizScore by viewModel.score.collectAsStateWithLifecycle()
    val subject by viewModel.currentSubject.collectAsStateWithLifecycle()
    val levelIndex by viewModel.currentLevelIndex.collectAsStateWithLifecycle()

    val earnedStars = when {
        quizScore == 10 -> 3
        quizScore >= 8 -> 2
        quizScore >= 5 -> 1
        else -> 0
    }

    val isPassed = quizScore >= 5

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (isPassed) "Congratulations! 🎉" else "Keep trying! 💪",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isPassed) Color(0xFF10B981) else Color(0xFFF59E0B),
                    letterSpacing = (-0.5).sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("result_headline_id")
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Completed Level $levelIndex: ${if (subject == "Math") "Maths" else subject}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF64748B), fontWeight = FontWeight.Medium)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Star achievements with soft backing shadow and glowing colors
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) { starIndex ->
                    val isGlowing = starIndex < earnedStars
                    val scale by animateFloatAsState(targetValue = if (isGlowing) 1.2f else 1.0f, label = "starScale")

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Achieved Star",
                        tint = if (isGlowing) Color(0xFFFCD34D) else Color(0xFFE2E8F0),
                        modifier = Modifier
                            .size(54.dp)
                            .shadow(if (isGlowing) 4.dp else 0.dp, CircleShape)
                            .testTag("result_star_$starIndex")
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Round score card display - white background layout in palette
            Box(
                modifier = Modifier
                    .size(136.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, if (isPassed) Color(0xFF10B981) else Color(0xFFF59E0B), CircleShape)
                    .shadow(elevation = 4.dp, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "$quizScore/10",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Black,
                            color = if (isPassed) Color(0xFF047857) else Color(0xFFD97706)
                        )
                    )
                    Text(
                        text = "Correct Answers",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = if (isPassed) "“Great job! Keep learning daily.” 🌟" else "“Don't give up. Learn daily, grow slowly!” ✨",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 16.dp).testTag("result_motto")
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = onBackToLevels,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPassed) Color(0xFF10B981) else Color(0xFF0EA5E9)
                ),
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("back_to_levels_button")
            ) {
                Text(
                    text = if (isPassed) "Unlock & Proceed ⮕" else "Try Level Again ⟲",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

// 7. Profile Screen
@Composable
fun ProfileScreen(
    onBackClicked: () -> Unit,
    viewModel: HomeTeacherViewModel,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.userProfile.collectAsStateWithLifecycle()
    val allProg by viewModel.allProgress.collectAsStateWithLifecycle()

    val totalLevelsUnlocked = allProg.count { it.isUnlocked }
    val totalStarsEarned = allProg.sumOf { it.starsScore }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                        clip = false
                    )
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(Color.White)
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF1E293B))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Your Teacher Profile 🎓",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = Color(0xFF1E293B)),
                        modifier = Modifier.testTag("profile_header")
                    )
                }
            }
        },
        bottomBar = {
            VibrantBottomBar(
                currentTab = "stats",
                onHomeClick = onBackClicked,
                onStatsClick = {},
                onSettingsClick = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Massive Avatar Initials
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF0F9FF))
                    .border(3.dp, Color(0xFF0EA5E9), CircleShape)
                    .shadow(2.dp, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (profile?.name ?: "P").take(1).uppercase(),
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = Color(0xFF0284C7),
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = profile?.name ?: "Prince",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            )

            Text(
                text = profile?.email ?: "princestmd2008@gmail.com",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF64748B))
            )

            val rankColors = when (profile?.rank) {
                "Grandmaster Achiever" -> Pair(Color(0xFFF43F5E), Color(0xFFFFF1F2)) // Rose
                "Scholar Tutor" -> Pair(Color(0xFFF59E0B), Color(0xFFFFFBEB)) // Amber
                else -> Pair(Color(0xFF10B981), Color(0xFFECFDF5)) // Emerald
            }
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(rankColors.second)
                    .border(1.dp, rankColors.first, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.WorkspacePremium, "Rank Premium medal", tint = rankColors.first, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = profile?.rank ?: "Active Learner",
                        color = rankColors.first,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(color = Color(0xFFE2E8F0))

            Spacer(modifier = Modifier.height(20.dp))

            // User stats Cards Grid using clean White containers matching design rules
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileStatCard(
                    title = "Daily Streak",
                    value = "${profile?.streakDays ?: 1} Days",
                    icon = Icons.Default.Whatshot,
                    tint = Color(0xFFF97316), // Orange streak fire
                    modifier = Modifier.weight(1f)
                )

                ProfileStatCard(
                    title = "Total Stars",
                    value = "$totalStarsEarned ⭐",
                    icon = Icons.Default.Star,
                    tint = Color(0xFFFCD34D),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileStatCard(
                    title = "Unlocked Levels",
                    value = "$totalLevelsUnlocked Levels",
                    icon = Icons.Default.TaskAlt,
                    tint = Color(0xFF10B981),
                    modifier = Modifier.weight(1f)
                )

                ProfileStatCard(
                    title = "Active Status",
                    value = if (profile?.registrationCompleted == true) "Registered" else "Guest",
                    icon = Icons.Default.CloudQueue,
                    tint = Color(0xFF0EA5E9),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Logout row
            Button(
                onClick = {
                    viewModel.logout()
                    onBackClicked() // Pop screen back to login
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF1F2)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFECDD3)),
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .height(52.dp)
                    .testTag("logout_btn")
            ) {
                Icon(Icons.Default.Logout, "Log Out icon symbol", tint = Color(0xFFBE123C))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Log Out Profile Session", color = Color(0xFFBE123C), fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}

@Composable
fun ProfileStatCard(
    title: String,
    value: String,
    icon: ImageVector,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(110.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, fontSize = 12.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(20.dp))
            }
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1E293B)
            )
        }
    }
}
