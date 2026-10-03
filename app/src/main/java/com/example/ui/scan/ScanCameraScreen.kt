package com.example.ui.scan

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.material.icons.filled.FlashAuto
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScanCameraScreen(
    viewModel: ScanCameraViewModel = viewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToReviewScan: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val topInset = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .testTag("scan_camera_screen_root")
    ) {
        // Camera Canvas Viewfinder Visual Simulation
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Simulated camera document preview background
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1E293B), Color(0xFF0F172A))
                )
            )

            // Simulated notebook sheet paper canvas in center
            val sheetLeft = w * 0.12f
            val sheetTop = h * 0.18f
            val sheetRight = w * 0.88f
            val sheetBottom = h * 0.72f

            drawRoundRect(
                color = Color(0xFF334155),
                topLeft = Offset(sheetLeft, sheetTop),
                size = Size(sheetRight - sheetLeft, sheetBottom - sheetTop),
                cornerRadius = CornerRadius(16f, 16f)
            )

            // Notebook spiral ring dots on left
            val dotSpacing = (sheetBottom - sheetTop) / 12f
            for (i in 1..11) {
                drawCircle(
                    color = Color(0xFF64748B),
                    radius = 4f,
                    center = Offset(sheetLeft + 12f, sheetTop + i * dotSpacing)
                )
            }

            // Green Mint Viewfinder Corners (#34D399)
            val cornerLen = 32.dp.toPx()
            val strokeW = 3.5.dp.toPx()
            val cornerColor = Color(0xFF34D399)

            val margin = w * 0.10f
            val topM = h * 0.16f
            val botM = h * 0.74f

            // Top-Left corner
            drawLine(cornerColor, Offset(margin, topM), Offset(margin + cornerLen, topM), strokeW)
            drawLine(cornerColor, Offset(margin, topM), Offset(margin, topM + cornerLen), strokeW)

            // Top-Right corner
            drawLine(cornerColor, Offset(w - margin, topM), Offset(w - margin - cornerLen, topM), strokeW)
            drawLine(cornerColor, Offset(w - margin, topM), Offset(w - margin, topM + cornerLen), strokeW)

            // Bottom-Left corner
            drawLine(cornerColor, Offset(margin, botM), Offset(margin + cornerLen, botM), strokeW)
            drawLine(cornerColor, Offset(margin, botM), Offset(margin, botM - cornerLen), strokeW)

            // Bottom-Right corner
            drawLine(cornerColor, Offset(w - margin, botM), Offset(w - margin - cornerLen, botM), strokeW)
            drawLine(cornerColor, Offset(w - margin, botM), Offset(w - margin, botM - cornerLen), strokeW)
        }

        // Overlaid UI Components
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topInset)
        ) {
            // Top Navigation Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onNavigateBack() },
                    modifier = Modifier.testTag("scan_camera_close_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Camera",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0x991E293B),
                    border = BorderStroke(1.dp, Color(0x3360A5FA))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color(0xFF60A5FA),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "${uiState.studentName} • ${uiState.studentRoll}",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        )
                    }
                }

                IconButton(
                    onClick = { viewModel.toggleFlash() },
                    modifier = Modifier.testTag("camera_flash_button")
                ) {
                    Icon(
                        imageVector = if (uiState.isFlashOn) Icons.Default.FlashOn else Icons.Default.FlashAuto,
                        contentDescription = "Flash",
                        tint = if (uiState.isFlashOn) Color(0xFFFBBF24) else Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.55f))

            // Center Viewfinder Alignment Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xCC0F172A),
                    border = BorderStroke(1.dp, Color(0x3334D399))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CropFree,
                            contentDescription = null,
                            tint = Color(0xFF34D399),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = uiState.alignmentMessage,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(0.45f))

            // Bottom Shutter Control Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xCC0F172A),
                                Color(0xFF0F172A)
                            )
                        )
                    )
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left Side: Thumbnail Preview Card
                    Box(
                        modifier = Modifier.testTag("captured_thumbnail_preview")
                    ) {
                        Surface(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            color = Color.White,
                            border = BorderStroke(1.5.dp, Color(0xFF94A3B8))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0xFFF1F5F9)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CropFree,
                                    contentDescription = null,
                                    tint = Color(0xFF1D4ED8),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .size(18.dp)
                                .align(Alignment.TopEnd)
                                .offset(x = 4.dp, y = (-4).dp),
                            shape = CircleShape,
                            color = Color(0xFF1D4ED8)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "${uiState.capturedPagesCount}",
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                            }
                        }
                    }

                    // Center Shutter Button
                    Box(
                        modifier = Modifier
                            .size(68.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable { viewModel.capturePage() }
                            .testTag("camera_shutter_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1D4ED8)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PhotoCamera,
                                contentDescription = "Capture Page",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // Right Side: Review Button
                    Button(
                        onClick = { onNavigateToReviewScan() },
                        modifier = Modifier
                            .height(44.dp)
                            .testTag("review_scanned_paper_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF002B9A))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Review (${uiState.capturedPagesCount})",
                                style = TextStyle(
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
