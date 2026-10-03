package com.example.ui.splash

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SplashScreen(
    onTimeout: () -> Unit,
    viewModel: SplashViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect {
            onTimeout()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1339AF),
                        Color(0xFF1941C4),
                        Color(0xFF0F2B88)
                    )
                )
            )
            .drawBehind {
                val dotColor = Color(0x18FFFFFF)
                val step = 24.dp.toPx()
                var x = step / 2
                while (x < size.width) {
                    var y = step / 2
                    while (y < size.height) {
                        drawCircle(color = dotColor, radius = 1.2.dp.toPx(), center = Offset(x, y))
                        y += step
                    }
                    x += step
                }
            }
            .testTag("splash_screen_root")
    ) {
        // Main Centered Content
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo Container with Badge
            Box(
                modifier = Modifier.testTag("splash_logo_container"),
                contentAlignment = Alignment.BottomEnd
            ) {
                Surface(
                    modifier = Modifier.size(112.dp),
                    shape = RoundedCornerShape(28.dp),
                    color = Color.White,
                    shadowElevation = 10.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(14.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFF1B40BD)),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.size(46.dp)) {
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
                                strokeWidth = 3.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                            drawLine(
                                color = blue,
                                start = Offset(cx, topY),
                                end = Offset(botRightX, botY),
                                strokeWidth = 3.dp.toPx(),
                                cap = StrokeCap.Round
                            )

                            val r = 3.5.dp.toPx()
                            drawCircle(color = blue, radius = r, center = Offset(cx, topY))
                            drawCircle(color = blue, radius = r, center = Offset(botLeftX, botY))
                            drawCircle(color = blue, radius = r, center = Offset(botRightX, botY))
                        }
                    }
                }

                Surface(
                    modifier = Modifier
                        .offset(x = 6.dp, y = 6.dp)
                        .size(32.dp)
                        .testTag("splash_badge"),
                    shape = CircleShape,
                    color = Color(0xFF059669),
                    shadowElevation = 6.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // App Title: SmartPaper AI
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.testTag("splash_title_row")
            ) {
                Text(
                    text = "SmartPaper ",
                    style = TextStyle(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = (-0.5).sp
                    )
                )
                Text(
                    text = "AI",
                    style = TextStyle(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA5C4FF),
                        letterSpacing = (-0.5).sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Subtitle
            Text(
                text = "Assistive Paper Evaluation for\nTeachers",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF93C5FD),
                    lineHeight = 22.sp
                ),
                modifier = Modifier.testTag("splash_subtitle")
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Capsule Pill Badge
            Surface(
                modifier = Modifier.testTag("splash_capsule_badge"),
                shape = RoundedCornerShape(50),
                color = Color(0xFF2851D6).copy(alpha = 0.9f),
                border = BorderStroke(1.dp, Color(0xFF4B73EC))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🎓",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Educator Intelligence Platform",
                        style = TextStyle(
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }
            }
        }

        // Bottom Footer Container
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(bottom = 36.dp)
                .testTag("splash_bottom_container")
        ) {
            // 4 Pagination Dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.testTag("splash_dots_row")
            ) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4B70DF))
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Progress bar
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(3.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0xFF2144AF))
                    .testTag("splash_progress_bar")
            ) {
                Box(
                    modifier = Modifier
                        .width(85.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color(0xFF34D399))
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Footer Dot & Version text
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.testTag("splash_footer")
            ) {
                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF6B8CEB))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "v2.4.1 • Powered by Vision AI",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF82A4F8)
                    )
                )
            }
        }
    }
}
