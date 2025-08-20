package com.example.psynationalexam.ui.views

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.psynationalexam.R
import com.example.psynationalexam.model.LibraryCourse

private val libraryCourses = listOf(
	LibraryCourse(
		"1",
		"Biology",
		"Bacterial Biology Overview",
		0.5f,
		"5/10 Chapter",
		"5h 35m left",
		imageRes = R.drawable.illustration
	),
	LibraryCourse("2","Biology","Metabolic Biochemistry for High School",0.7f,"5/10 Chapter","5h 35m left", imageRes = R.drawable.illustration2),
	LibraryCourse("3","Biology","Mendelian Genetics & Mechanisms of Heredity",0.6f,"5/10 Chapter","5h 35m left", imageRes = R.drawable.illustration3),
	LibraryCourse("4","Mathematics","High School Algebra I: Help and Review",0.5f,"5/10 Chapter","5h 35m left", imageRes = R.drawable.illustration3, isDiscuss = true),
	LibraryCourse("5","Mathematics","Enlargement to Trigonometry",0.4f,"5/10 Chapter","5h 35m left", imageRes = R.drawable.illustration3)
)
@Preview(showSystemUi = true)
@Composable
fun LibraryScreen() {
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White),
		contentPadding = PaddingValues(vertical = 16.dp)
	) {
		items(libraryCourses) { course ->
			LibraryCourseCard(course)
			Spacer(Modifier.height(16.dp))
		}
	}
}

@Composable
private fun LibraryCourseCard(course: LibraryCourse) {
	val context = LocalContext.current
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 12.dp)
			.clip(RoundedCornerShape(16.dp))
			.background(Color.White),
//			.shadow(2.dp, RoundedCornerShape(16.dp)),
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			modifier = Modifier
				.size(width = 120.dp, height = 120.dp)
				.clip(RoundedCornerShape(16.dp))
				.background(Color(0xFF3B5FFF).copy(alpha = 0.08f)),
			contentAlignment = Alignment.TopStart
		) {
			if (course.imageRes != null) {
				Image(
					painter = painterResource(id = course.imageRes),
					contentDescription = null,
					contentScale = ContentScale.Crop,
					modifier = Modifier.fillMaxSize()
				)
			}
			Box(
				modifier = Modifier
					.padding(8.dp)
					.clip(RoundedCornerShape(6.dp))
					.background(if (course.subject == "Biology") Color(0xFF3B5FFF) else Color(0xFF3B5FFF)),
				contentAlignment = Alignment.Center
			) {
				Text(
					text = course.subject,
					color = Color.White,
					fontSize = 13.sp,
					fontWeight = FontWeight.Bold,
					modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
				)
			}
		}
		Spacer(Modifier.width(16.dp))
		Column(
			modifier = Modifier.weight(1f)
		) {
			Text(
				text = course.title,
				fontWeight = FontWeight.SemiBold,
				fontSize = 15.sp,
				color = Color(0xFF23223C),
				maxLines = 2,
				overflow = TextOverflow.Ellipsis
			)
			Spacer(Modifier.height(8.dp))
			Row(verticalAlignment = Alignment.CenterVertically) {
				Text(course.progressText, fontSize = 12.sp, color = Color(0xFF9A9A9A))
				Spacer(Modifier.width(12.dp))
				Text("\u23F1  ", fontSize = 12.sp, color = Color(0xFF9A9A9A))
				Text(course.timeLeft, fontSize = 12.sp, color = Color(0xFF9A9A9A))
			}
			Spacer(Modifier.height(8.dp))
			LinearProgressIndicator(
				progress = { course.progress },
				modifier = Modifier
					.fillMaxWidth()
					.height(6.dp)
					.clip(RoundedCornerShape(3.dp)),
				color = Color(0xFF3B5FFF),
				trackColor = Color(0xFFE5E5F0)
			)
			Spacer(Modifier.height(12.dp))
			Row {
				OutlinedButton(
					onClick = { context.startActivity(Intent(context, CourseDetailsActivity::class.java)) },
					modifier = Modifier.weight(1f),
					shape = RoundedCornerShape(8.dp),
					colors = ButtonDefaults.outlinedButtonColors(
						containerColor = Color.White,
						contentColor = Color(0xFF3B5FFF)
					)
				) {
					Text("View", fontSize = 15.sp, fontWeight = FontWeight.Medium)
				}
				Spacer(Modifier.width(8.dp))
				Button(
					onClick = {},
					modifier = Modifier.weight(1f),
					shape = RoundedCornerShape(8.dp),
					colors = ButtonDefaults.buttonColors(
						containerColor = if (course.isDiscuss) Color(0xFF3B5FFF) else Color.White,
						contentColor = if (course.isDiscuss) Color.White else Color(0xFF3B5FFF)
					),
					border = if (!course.isDiscuss) ButtonDefaults.outlinedButtonBorder else null
				) {
					Text("Discuss", fontSize = 15.sp, fontWeight = FontWeight.Medium)
				}
			}
		}
	}
}
