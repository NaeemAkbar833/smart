package com.example.ui.signup

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.R

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = viewModel(),
    onNavigateToLogin: () -> Unit = {},
    onSignupSuccess: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    val topInset = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val bottomInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .testTag("signup_screen_root")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = topInset + 16.dp, bottom = bottomInset + 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Top Bar: Back Button & Educator Portal Badge
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable { onNavigateToLogin() }
                        .testTag("signup_back_button"),
                    color = Color(0xFFF1F5F9),
                    shape = CircleShape
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF0F172A),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xFFE0E7FF),
                    modifier = Modifier.testTag("signup_portal_badge")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = Color(0xFF1D4ED8),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "EDUCATOR PORTAL",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1D4ED8)
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Logo Squircle with Badge
            Box(
                modifier = Modifier.testTag("signup_logo_container"),
                contentAlignment = Alignment.BottomEnd
            ) {
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White,
                    shadowElevation = 6.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF1B40BD)),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.size(32.dp)) {
                            val w = size.width
                            val h = size.height

                            val docPath = Path().apply {
                                moveTo(0f, h * 0.08f)
                                lineTo(w * 0.65f, h * 0.08f)
                                lineTo(w, h * 0.38f)
                                lineTo(w, h * 0.92f)
                                lineTo(0f, h * 0.92f)
                                close()
                            }
                            drawPath(docPath, color = Color.White)

                            val foldPath = Path().apply {
                                moveTo(w * 0.65f, h * 0.08f)
                                lineTo(w * 0.65f, h * 0.38f)
                                lineTo(w, h * 0.38f)
                                close()
                            }
                            drawPath(foldPath, color = Color(0xFFC7D7FE))

                            val blue = Color(0xFF1B40BD)
                            val cx = w * 0.5f
                            val topY = h * 0.48f
                            val botLeftX = w * 0.35f
                            val botRightX = w * 0.65f
                            val botY = h * 0.70f

                            drawLine(
                                color = blue,
                                start = Offset(cx, topY),
                                end = Offset(botLeftX, botY),
                                strokeWidth = 2.5.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                            drawLine(
                                color = blue,
                                start = Offset(cx, topY),
                                end = Offset(botRightX, botY),
                                strokeWidth = 2.5.dp.toPx(),
                                cap = StrokeCap.Round
                            )

                            val r = 2.8.dp.toPx()
                            drawCircle(color = blue, radius = r, center = Offset(cx, topY))
                            drawCircle(color = blue, radius = r, center = Offset(botLeftX, botY))
                            drawCircle(color = blue, radius = r, center = Offset(botRightX, botY))
                        }
                    }
                }

                Surface(
                    modifier = Modifier
                        .offset(x = 4.dp, y = 4.dp)
                        .size(22.dp)
                        .testTag("signup_badge"),
                    shape = CircleShape,
                    color = Color(0xFF059669),
                    shadowElevation = 4.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title: Create Teacher Account
            Text(
                text = "Create Teacher Account",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("signup_title")
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Subtitle
            Text(
                text = "Set up your educator workspace for\nautomated paper grading",
                style = TextStyle(
                    fontSize = 13.5.sp,
                    color = Color(0xFF64748B),
                    fontWeight = FontWeight.Normal,
                    lineHeight = 19.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("signup_subtitle")
            )

            Spacer(modifier = Modifier.height(20.dp))

            // White Card Form
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .testTag("signup_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Full Name Label Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Full Name",
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF475569)
                            )
                        )
                        Text(
                            text = "Required",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color(0xFF94A3B8)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Full Name Field
                    OutlinedTextField(
                        value = uiState.fullName,
                        onValueChange = viewModel::onFullNameChange,
                        placeholder = {
                            Text(
                                text = "Enter your full name (e.g., Dr. Jane Smith)",
                                color = Color(0xFF94A3B8),
                                fontSize = 13.5.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Badge,
                                contentDescription = null,
                                tint = Color(0xFF475569),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("signup_fullname_input"),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF1F5F9),
                            focusedContainerColor = Color(0xFFEEF2FF),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF2563EB)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email Label
                    Text(
                        text = "Email",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF475569)
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Email Field
                    OutlinedTextField(
                        value = uiState.email,
                        onValueChange = viewModel::onEmailChange,
                        placeholder = {
                            Text(
                                text = "Enter your Email",
                                color = Color(0xFF94A3B8),
                                fontSize = 13.5.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = Color(0xFF475569),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("signup_email_input"),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF1F5F9),
                            focusedContainerColor = Color(0xFFEEF2FF),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF2563EB)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Label
                    Text(
                        text = "Password",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF475569)
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Password Field
                    OutlinedTextField(
                        value = uiState.password,
                        onValueChange = viewModel::onPasswordChange,
                        placeholder = {
                            Text(
                                text = "Create a secure password (min. 8 charac",
                                color = Color(0xFF94A3B8),
                                fontSize = 13.5.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = Color(0xFF475569),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { viewModel.togglePasswordVisibility() },
                                modifier = Modifier.testTag("signup_password_toggle")
                            ) {
                                Icon(
                                    imageVector = if (uiState.isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Toggle password visibility",
                                    tint = Color(0xFF475569)
                                )
                            }
                        },
                        visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("signup_password_input"),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF1F5F9),
                            focusedContainerColor = Color(0xFFEEF2FF),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF2563EB)
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Password Rule Indicator Bar Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            repeat(3) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(4.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(Color(0xFFC7D7FE))
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                tint = Color(0xFF64748B),
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Min. 8 characters",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color(0xFF64748B)
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Confirm Password Label
                    Text(
                        text = "Confirm Password",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF475569)
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Confirm Password Field
                    OutlinedTextField(
                        value = uiState.confirmPassword,
                        onValueChange = viewModel::onConfirmPasswordChange,
                        placeholder = {
                            Text(
                                text = "Re-enter your password",
                                color = Color(0xFF94A3B8),
                                fontSize = 13.5.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.LockReset,
                                contentDescription = null,
                                tint = Color(0xFF475569),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { viewModel.toggleConfirmPasswordVisibility() },
                                modifier = Modifier.testTag("signup_confirm_password_toggle")
                            ) {
                                Icon(
                                    imageVector = if (uiState.isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Toggle confirm password visibility",
                                    tint = Color(0xFF475569)
                                )
                            }
                        },
                        visualTransformation = if (uiState.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("signup_confirm_password_input"),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF1F5F9),
                            focusedContainerColor = Color(0xFFEEF2FF),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF2563EB)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Terms & Privacy Checkbox Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = uiState.agreedToTerms,
                            onCheckedChange = viewModel::onAgreedToTermsChange,
                            modifier = Modifier.testTag("signup_terms_checkbox"),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF1D4ED8),
                                uncheckedColor = Color(0xFFCBD5E1)
                            )
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Column {
                            Row {
                                Text(
                                    text = "I agree to the ",
                                    style = TextStyle(fontSize = 12.sp, color = Color(0xFF475569))
                                )
                                Text(
                                    text = "Terms of Service",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = Color(0xFF1D4ED8),
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.clickable { /* UI element */ }
                                )
                                Text(
                                    text = " & ",
                                    style = TextStyle(fontSize = 12.sp, color = Color(0xFF475569))
                                )
                                Text(
                                    text = "Educator Privacy",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = Color(0xFF1D4ED8),
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.clickable { /* UI element */ }
                                )
                            }
                            Text(
                                text = "Policy",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color(0xFF1D4ED8),
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.clickable { /* UI element */ }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Create Account Button
                    Button(
                        onClick = { onSignupSuccess() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("signup_create_account_button"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF002B9A))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Create Account",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // OR Divider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = Color(0xFFE2E8F0)
                        )
                        Text(
                            text = "OR",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF94A3B8)
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = Color(0xFFE2E8F0)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Continue with Google Button
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { onSignupSuccess() }
                            .testTag("signup_google_button"),
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_google_logo),
                                contentDescription = "Google Logo",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Continue with Google",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF1E293B)
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Footer: Already have an account? Login >
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.testTag("signup_login_footer")
            ) {
                Text(
                    text = "Already have an account? ",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Normal
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onNavigateToLogin() }
                ) {
                    Text(
                        text = "Login ",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color(0xFF1D4ED8),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForward,
                        contentDescription = null,
                        tint = Color(0xFF1D4ED8),
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}
