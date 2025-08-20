package com.example.psynationalexam.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.psynationalexam.R

class CourseDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseDetailsScreen(onBack = { finish() })
        }
    }
}

@Composable
fun CourseDetailsScreen(onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    var introExpanded by remember { mutableStateOf(true) }
    var arithmeticExpanded by remember { mutableStateOf(false) }
    var quizExpanded by remember { mutableStateOf(true) }
    var decimalsExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        // Header with image and back button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .background(Color(0xFF3B5FFF)),
        ) {
            // Illustration
            Image(
                painter = painterResource(id = R.drawable.illustration),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(170.dp)
            )
            // Back button
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
                    .size(36.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF23223C))
            }
        }

        // Card-like container
        Column(
            modifier = Modifier
                .offset(y = (-32).dp)
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            // Title and badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "High School Algebra I: Help and Review",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF23223C),
                        maxLines = 2
                    )
                    Spacer(Modifier.height(4.dp))
                    Text("Mathematics", color = Color(0xFF9A9A9A), fontSize = 14.sp)
                }
                Box(
                    modifier = Modifier
                        .background(Color(0xFFE5E5F0), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text("Medium", color = Color(0xFF3B5FFF), fontSize = 13.sp)
                }
            }

            Spacer(Modifier.height(20.dp))

            // Description
            Text("Description", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color(0xFF23223C))
            Spacer(Modifier.height(6.dp))
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Habitasse dolor etiam sed ante donec quis sapien. Malesuada rhoncus nullam eleifend lorem egestas mauris massa massa. More",
                color = Color(0xFF23223C),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(Modifier.height(20.dp))

            // Information Row
            Text("Information", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color(0xFF23223C))
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoItem(icon = R.drawable.ic_time, label = "12h 35m")
                InfoItem(icon = R.drawable.ic_time, label = "12 chapter")
                InfoItem(icon = R.drawable.ic_time, label = "45 students")
                InfoItem(icon = R.drawable.ic_time, label = "350 reviews")
            }

            Spacer(Modifier.height(24.dp))

            // Syllabus
            Text("Syllabus", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color(0xFF23223C))
            Spacer(Modifier.height(10.dp))
            // Syllabus Items
            SyllabusItem(
                title = "Introduction",
                expanded = introExpanded,
                onExpandToggle = { introExpanded = !introExpanded },
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Semper et eu, commodo, lacus. Semper urna nunc dictum duis adipiscing aenean scelerisque in porta. Magna viverra auctor tortor elementum. Arcu cras egestas erat condimentum mattis quam justo."
            )
            SyllabusItem(
                title = "Basic Arithmetic",
                expanded = arithmeticExpanded,
                onExpandToggle = { arithmeticExpanded = !arithmeticExpanded },
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Semper et eu, commodo, lacus. Learn More"
            )
            SyllabusItem(
                title = "Quiz",
                expanded = quizExpanded,
                onExpandToggle = { quizExpanded = !quizExpanded },
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Semper et eu, commodo, lacus. Learn More"
            )
            SyllabusItem(
                title = "Decimals and Fractions",
                expanded = decimalsExpanded,
                onExpandToggle = { decimalsExpanded = !decimalsExpanded },
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Semper et eu, commodo, lacus. Learn More"
            )

            Spacer(Modifier.height(32.dp))

            // Price Section
            Text("Price", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = Color(0xFF23223C))
            Spacer(Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFE5E5F0), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text("Discount", color = Color(0xFF3B5FFF), fontSize = 13.sp)
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    "$240",
                    color = Color(0xFF9A9A9A),
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    "$120",
                    color = Color(0xFF3B5FFF),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                "(Save 50%)",
                color = Color(0xFF3B5FFF),
                fontSize = 13.sp,
                modifier = Modifier.padding(start = 4.dp)
            )

            Spacer(Modifier.height(24.dp))

            // Buy Button
            Button(
                onClick = { /* TODO: Handle buy */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B5FFF))
            ) {
                Text("Buy Course", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Composable
private fun InfoItem(icon: Int, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(6.dp))
        Text(label, fontSize = 13.sp, color = Color(0xFF23223C))
    }
}

@Composable
private fun SyllabusItem(
    title: String,
    expanded: Boolean,
    onExpandToggle: () -> Unit,
    content: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onExpandToggle() }
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontWeight = FontWeight.Medium, fontSize = 15.sp, color = Color(0xFF23223C), modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = Color(0xFF23223C)
            )
        }
        if (expanded) {
            Text(
                content,
                color = Color(0xFF23223C),
                fontSize = 13.sp,
                lineHeight = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp, start = 2.dp, end = 2.dp)
            )
        }
        Divider(color = Color(0xFFE5E5F0), thickness = 1.dp)
    }
}