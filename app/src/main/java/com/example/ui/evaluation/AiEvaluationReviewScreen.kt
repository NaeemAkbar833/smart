package com.example.ui.evaluation

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AiEvaluationReviewScreen(
    viewModel: AiEvaluationReviewViewModel = viewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToProcessing: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    val topInset = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .testTag("ai_evaluation_review_root")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topInset)
        ) {
            // Top Bar Navigation Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable { onNavigateBack() }
                            .testTag("ai_evaluation_top_back_button"),
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

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "AI Evaluation Review",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A)
                        )
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { /* UI action */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = Color(0xFF0F172A)
                        )
                    }

                    Box {
                        Surface(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape),
                            color = Color(0xFFE2E8F0)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Profile",
                                    tint = Color(0xFF64748B),
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .size(10.dp)
                                .align(Alignment.BottomEnd)
                                .offset(x = 1.dp, y = 1.dp),
                            shape = CircleShape,
                            color = Color(0xFF10B981),
                            border = BorderStroke(1.5.dp, Color.White)
                        ) {}
                    }
                }
            }

            // Scrollable Main Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp, vertical = 4.dp)
            ) {
                // Verification Mode Sub-Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1D4ED8))
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "VERIFICATION MODE",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1D4ED8)
                            )
                        )
                    }

                    Text(
                        text = "Batch #B-104 • 14/28 Papers",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color(0xFF64748B)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Student Context Banner Card
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("evaluation_student_card"),
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFEEF2FF)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFE0E7FF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = Color(0xFF1D4ED8),
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = uiState.studentName,
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A)
                                )
                            )
                            Text(
                                text = "Roll: ${uiState.rollNo} • ${uiState.examName}",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color(0xFF64748B)
                                )
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color(0xFFE2E8F0),
                            modifier = Modifier.clickable { /* View original */ }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = null,
                                    tint = Color(0xFF475569),
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Original",
                                    style = TextStyle(
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF475569)
                                    )
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // AI Assistive Draft Warning Banner
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFFFF7ED),
                    border = BorderStroke(1.dp, Color(0xFFFED7AA))
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFC2410C)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(13.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "AI Assistive Draft — Not Final",
                                    style = TextStyle(
                                        fontSize = 13.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF7C2D12)
                                    )
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(50),
                                color = Color(0xFFFFEDD5)
                            ) {
                                Text(
                                    text = "Mandatory",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFC2410C)
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Teacher review and score confirmation required before results are published. Machine suggestions do not update official records until approved.",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color(0xFF9A3412),
                                lineHeight = 16.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // TOTAL EVALUATED SCORE Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("total_evaluated_score_card"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFF1F5F9)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "TOTAL EVALUATED SCORE",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF64748B)
                                )
                            )

                            Surface(
                                shape = RoundedCornerShape(50),
                                color = Color(0xFFDBEAFE)
                            ) {
                                Text(
                                    text = "[Pending Approval]",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1D4ED8)
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = "${uiState.q1Score + uiState.q2Score}",
                                    style = TextStyle(
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF0F172A)
                                    )
                                )
                                Text(
                                    text = " / 15",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF64748B)
                                    ),
                                    modifier = Modifier.padding(bottom = 3.dp)
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(50),
                                color = Color(0xFFD1FAE5)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "⚡ 100% Match",
                                        style = TextStyle(
                                            fontSize = 11.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF059669)
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Completion: 2 of 2 Questions Checked • Ready for Sign-off",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color(0xFF64748B)
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(Color(0xFF002B9A))
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Question Filter Tabs Row
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = if (uiState.selectedFilterTab == 0) Color(0xFFEEF2FF) else Color(0xFFF1F5F9),
                                border = if (uiState.selectedFilterTab == 0) BorderStroke(1.dp, Color(0xFFC7D7FE)) else null,
                                modifier = Modifier.clickable { viewModel.onFilterTabSelect(0) }
                            ) {
                                Text(
                                    text = "All (2)",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (uiState.selectedFilterTab == 0) Color(0xFF1D4ED8) else Color(0xFF64748B)
                                    ),
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(50),
                                color = if (uiState.selectedFilterTab == 1) Color(0xFFEEF2FF) else Color(0xFFF1F5F9),
                                border = if (uiState.selectedFilterTab == 1) BorderStroke(1.dp, Color(0xFFC7D7FE)) else null,
                                modifier = Modifier.clickable { viewModel.onFilterTabSelect(1) }
                            ) {
                                Text(
                                    text = "Q1 (5m)",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (uiState.selectedFilterTab == 1) Color(0xFF1D4ED8) else Color(0xFF64748B)
                                    ),
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(50),
                                color = if (uiState.selectedFilterTab == 2) Color(0xFFEEF2FF) else Color(0xFFF1F5F9),
                                border = if (uiState.selectedFilterTab == 2) BorderStroke(1.dp, Color(0xFFC7D7FE)) else null,
                                modifier = Modifier.clickable { viewModel.onFilterTabSelect(2) }
                            ) {
                                Text(
                                    text = "Q2 (10m)",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (uiState.selectedFilterTab == 2) Color(0xFF1D4ED8) else Color(0xFF64748B)
                                    ),
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Question Evaluation Cards
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Question #1
                    if (uiState.selectedFilterTab == 0 || uiState.selectedFilterTab == 1) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("question_evaluation_card_1"),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = BorderStroke(1.dp, Color(0xFFF1F5F9)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                // Top Row
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(Color(0xFFEEF2FF))
                                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                text = "#1",
                                                style = TextStyle(
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color(0xFF1D4ED8)
                                                )
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Column {
                                            Text(
                                                text = "Short Answer",
                                                style = TextStyle(
                                                    fontSize = 13.5.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color(0xFF0F172A)
                                                )
                                            )
                                            Text(
                                                text = "Max Marks: 5.0",
                                                style = TextStyle(
                                                    fontSize = 11.5.sp,
                                                    color = Color(0xFF64748B)
                                                )
                                            )
                                        }
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(50),
                                        color = Color(0xFFD1FAE5)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = Color(0xFF059669),
                                                modifier = Modifier.size(12.dp)
                                            )
                                            Spacer(modifier = Modifier.width(3.dp))
                                            Text(
                                                text = "OCR Confidence 98%",
                                                style = TextStyle(
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color(0xFF059669)
                                                )
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // Rubric Question Box
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0xFFF8FAFC),
                                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Text(
                                            text = "RUBRIC QUESTION",
                                            style = TextStyle(
                                                fontSize = 10.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF1D4ED8)
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "Define Newton's Second Law of Motion and state its SI unit.",
                                            style = TextStyle(
                                                fontSize = 13.5.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color(0xFF0F172A)
                                            )
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // Transcribed Student Manuscript Box
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.CropFree,
                                            contentDescription = null,
                                            tint = Color(0xFF64748B),
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "Transcribed Student Manuscript",
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color(0xFF64748B)
                                            )
                                        )
                                    }

                                    Text(
                                        text = ":: Snippet",
                                        style = TextStyle(
                                            fontSize = 11.5.sp,
                                            color = Color(0xFF1D4ED8),
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                // Snippet Preview Canvas Box
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(90.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFE2E8F0)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Canvas(modifier = Modifier.fillMaxSize()) {
                                        val w = size.width
                                        val h = size.height

                                        drawRect(color = Color(0xFFF1F5F9))

                                        // Green bounding box snippet
                                        drawRoundRect(
                                            color = Color(0xFF10B981),
                                            topLeft = Offset(w * 0.2f, h * 0.2f),
                                            size = Size(w * 0.6f, h * 0.6f),
                                            cornerRadius = CornerRadius(6f, 6f),
                                            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
                                        )
                                    }

                                    Surface(
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .padding(6.dp),
                                        shape = RoundedCornerShape(6.dp),
                                        color = Color(0xCC1E293B)
                                    ) {
                                        Text(
                                            text = "Crop Q1",
                                            style = TextStyle(
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            ),
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "\"Force equals mass times acceleration (F=ma). The SI unit of force is the Newton (N).\"",
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        color = Color(0xFF0F172A),
                                        lineHeight = 17.sp
                                    )
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                // AI Assessment Draft
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0xFFD1FAE5)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = Color(0xFF059669),
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column {
                                            Text(
                                                text = "AI Assessment Draft",
                                                style = TextStyle(
                                                    fontSize = 11.5.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color(0xFF065F46)
                                                )
                                            )
                                            Text(
                                                text = "Correct formula and valid definition provided. SI unit accurately identified.",
                                                style = TextStyle(
                                                    fontSize = 12.sp,
                                                    color = Color(0xFF065F46)
                                                )
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                // Awarded Score Stepper Row
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "Awarded Score",
                                            style = TextStyle(
                                                fontSize = 12.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF0F172A)
                                            )
                                        )
                                        Text(
                                            text = "Adjust Marks",
                                            style = TextStyle(
                                                fontSize = 11.5.sp,
                                                color = Color(0xFF1D4ED8)
                                            )
                                        )
                                    }

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = Color(0xFFEEF2FF),
                                            modifier = Modifier.clickable { viewModel.updateQ1Score(-1) }
                                        ) {
                                            Box(
                                                modifier = Modifier.size(36.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Remove,
                                                    contentDescription = "Decrease",
                                                    tint = Color(0xFF1D4ED8),
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.width(10.dp))

                                        Text(
                                            text = "${uiState.q1Score} / 5",
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF0F172A)
                                            )
                                        )

                                        Spacer(modifier = Modifier.width(10.dp))

                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = Color(0xFFEEF2FF),
                                            modifier = Modifier.clickable { viewModel.updateQ1Score(1) }
                                        ) {
                                            Box(
                                                modifier = Modifier.size(36.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Add,
                                                    contentDescription = "Increase",
                                                    tint = Color(0xFF1D4ED8),
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // Feedback Field
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Teacher Feedback & Annotations",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF64748B)
                                        )
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit",
                                        tint = Color(0xFF64748B),
                                        modifier = Modifier.size(14.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                OutlinedTextField(
                                    value = uiState.q1Feedback,
                                    onValueChange = viewModel::updateQ1Feedback,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("q1_feedback_input"),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedContainerColor = Color(0xFFF8FAFC),
                                        focusedContainerColor = Color(0xFFEEF2FF),
                                        unfocusedBorderColor = Color(0xFFE2E8F0),
                                        focusedBorderColor = Color(0xFF2563EB)
                                    )
                                )
                            }
                        }
                    }

                    // Question #2
                    if (uiState.selectedFilterTab == 0 || uiState.selectedFilterTab == 2) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("question_evaluation_card_2"),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = BorderStroke(1.dp, Color(0xFFF1F5F9)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                ) {
                                    // Top Row
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier
                                                    .clip(RoundedCornerShape(6.dp))
                                                    .background(Color(0xFFEEF2FF))
                                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                            ) {
                                                Text(
                                                    text = "#2",
                                                    style = TextStyle(
                                                        fontSize = 13.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color(0xFF1D4ED8)
                                                    )
                                                )
                                            }

                                            Spacer(modifier = Modifier.width(8.dp))

                                            Column {
                                                Text(
                                                    text = "Numerical Problem",
                                                    style = TextStyle(
                                                        fontSize = 13.5.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color(0xFF0F172A)
                                                    )
                                                )
                                                Text(
                                                    text = "Max Marks: 10.0",
                                                    style = TextStyle(
                                                        fontSize = 11.5.sp,
                                                        color = Color(0xFF64748B)
                                                    )
                                                )
                                            }
                                        }

                                        Surface(
                                            shape = RoundedCornerShape(50),
                                            color = Color(0xFFD1FAE5)
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color(0xFF059669),
                                                    modifier = Modifier.size(12.dp)
                                                )
                                                Spacer(modifier = Modifier.width(3.dp))
                                                Text(
                                                    text = "OCR Confidence 96%",
                                                    style = TextStyle(
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color(0xFF059669)
                                                    )
                                                )
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Rubric Question Box
                                    Surface(
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0xFFF8FAFC),
                                        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text(
                                                text = "RUBRIC QUESTION",
                                                style = TextStyle(
                                                    fontSize = 10.5.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color(0xFF1D4ED8)
                                                )
                                            )
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = "A 1200kg vehicle decelerates from 25 m/s to rest in 5 seconds. Calculate the average net braking force required.",
                                                style = TextStyle(
                                                    fontSize = 13.5.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = Color(0xFF0F172A)
                                                )
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Transcribed Student Working Box
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.CropFree,
                                                contentDescription = null,
                                                tint = Color(0xFF64748B),
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "Transcribed Student Working",
                                                style = TextStyle(
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = Color(0xFF64748B)
                                                )
                                            )
                                        }

                                        Text(
                                            text = ":: Snippet",
                                            style = TextStyle(
                                                fontSize = 11.5.sp,
                                                color = Color(0xFF1D4ED8),
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))

                                    // Snippet Preview Canvas Box
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(90.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(Color(0xFFE2E8F0)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Canvas(modifier = Modifier.fillMaxSize()) {
                                            val w = size.width
                                            val h = size.height

                                            drawRect(color = Color(0xFFF1F5F9))

                                            // Green bounding box snippet
                                            drawRoundRect(
                                                color = Color(0xFF10B981),
                                                topLeft = Offset(w * 0.15f, h * 0.15f),
                                                size = Size(w * 0.7f, h * 0.7f),
                                                cornerRadius = CornerRadius(6f, 6f),
                                                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
                                            )
                                        }

                                        Surface(
                                            modifier = Modifier
                                                .align(Alignment.BottomEnd)
                                                .padding(6.dp),
                                            shape = RoundedCornerShape(6.dp),
                                            color = Color(0xCC1E293B)
                                        ) {
                                            Text(
                                                text = "Crop Q2",
                                                style = TextStyle(
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White
                                                ),
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "\"a = (0 - 25)/5 = -5 m/s². F = m * a = 1200 * -5 = -6000 N. Braking force = 6000 N.\"",
                                        style = TextStyle(
                                            fontSize = 13.sp,
                                            color = Color(0xFF0F172A),
                                            lineHeight = 17.sp
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // AI Assessment Draft
                                    Surface(
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0xFFD1FAE5)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(10.dp),
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = null,
                                                tint = Color(0xFF059669),
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Column {
                                                Text(
                                                    text = "AI Assessment Draft",
                                                    style = TextStyle(
                                                        fontSize = 11.5.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color(0xFF065F46)
                                                    )
                                                )
                                                Text(
                                                    text = "Calculations and signs verified correctly. Step-by-step methodology followed.",
                                                    style = TextStyle(
                                                        fontSize = 12.sp,
                                                        color = Color(0xFF065F46)
                                                    )
                                                )
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Awarded Score Stepper Row
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                text = "Awarded Score",
                                                style = TextStyle(
                                                    fontSize = 12.5.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color(0xFF0F172A)
                                                )
                                            )
                                            Text(
                                                text = "Correct Marks",
                                                style = TextStyle(
                                                    fontSize = 11.5.sp,
                                                    color = Color(0xFF1D4ED8)
                                                )
                                            )
                                        }

                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Surface(
                                                shape = RoundedCornerShape(8.dp),
                                                color = Color(0xFFEEF2FF),
                                                modifier = Modifier.clickable { viewModel.updateQ2Score(-1) }
                                            ) {
                                                Box(
                                                    modifier = Modifier.size(36.dp),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Remove,
                                                        contentDescription = "Decrease",
                                                        tint = Color(0xFF1D4ED8),
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                }
                                            }

                                            Spacer(modifier = Modifier.width(10.dp))

                                            Text(
                                                text = "${uiState.q2Score} / 10",
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color(0xFF0F172A)
                                                )
                                            )

                                            Spacer(modifier = Modifier.width(10.dp))

                                            Surface(
                                                shape = RoundedCornerShape(8.dp),
                                                color = Color(0xFFEEF2FF),
                                                modifier = Modifier.clickable { viewModel.updateQ2Score(1) }
                                            ) {
                                                Box(
                                                    modifier = Modifier.size(36.dp),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Add,
                                                        contentDescription = "Increase",
                                                        tint = Color(0xFF1D4ED8),
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Feedback Field
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Teacher Feedback & Annotations",
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color(0xFF64748B)
                                            )
                                        )
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Edit",
                                            tint = Color(0xFF64748B),
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))

                                    OutlinedTextField(
                                        value = uiState.q2Feedback,
                                        onValueChange = viewModel::updateQ2Feedback,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .testTag("q2_feedback_input"),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            unfocusedContainerColor = Color(0xFFF8FAFC),
                                            focusedContainerColor = Color(0xFFEEF2FF),
                                            unfocusedBorderColor = Color(0xFFE2E8F0),
                                            focusedBorderColor = Color(0xFF2563EB)
                                        )
                                    )
                                }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Teacher Validation Checkpoint Box
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("teacher_validation_checkpoint"),
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFEEF2FF),
                    border = BorderStroke(1.dp, Color(0xFFC7D7FE))
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Checkbox(
                            checked = uiState.isTeacherValidated,
                            onCheckedChange = { viewModel.toggleValidation() },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF1D4ED8),
                                uncheckedColor = Color(0xFF64748B)
                            ),
                            modifier = Modifier.testTag("teacher_validation_checkbox")
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Column {
                            Text(
                                text = "Teacher Validation Checkpoint",
                                style = TextStyle(
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A)
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "I have reviewed all AI-evaluated answers, corrected marks where necessary, and approve this assessment.",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color(0xFF475569),
                                    lineHeight = 16.sp
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Fixed Bottom Navigation Bar
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("evaluation_review_bottom_bar"),
                color = Color.White,
                shadowElevation = 8.dp,
                border = BorderStroke(1.dp, Color(0xFFE2E8F0))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Re-evaluate Button
                        Button(
                            onClick = { /* Re-evaluate action */ },
                            modifier = Modifier
                                .weight(0.32f)
                                .height(50.dp)
                                .testTag("re_evaluate_button"),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEF2FF))
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = null,
                                    tint = Color(0xFF0F172A),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Re-evaluate",
                                    style = TextStyle(
                                        fontSize = 12.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF0F172A)
                                    )
                                )
                            }
                        }

                        // Approve Result & Finalize Button
                        Button(
                            onClick = { onNavigateToProcessing() },
                            modifier = Modifier
                                .weight(0.68f)
                                .height(50.dp)
                                .testTag("approve_result_finalize_button"),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF002B9A))
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Approve Result & Finalize",
                                    style = TextStyle(
                                        fontSize = 12.5.sp,
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

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Permanently marks student status as Completed",
                        style = TextStyle(
                            fontSize = 11.sp,
                            color = Color(0xFF64748B),
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}
