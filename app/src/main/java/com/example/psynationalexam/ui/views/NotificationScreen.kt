package com.example.psynationalexam.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class NotificationItem(
	val title: String,
	val subtitle: String,
	val time: String
)

private val notifications = listOf(
	NotificationItem("High School Algebra I: Help and Review", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1 min ago"),
	NotificationItem("Metabolic Biochemistry for High School", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "10 min ago"),
	NotificationItem("Bacterial Biology Overview", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1 hour ago"),
	NotificationItem("Enlargement to Trigonometry", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1 hour ago"),
	NotificationItem("Mendelian Genetics & Mechanisms of Heredity", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1 hour ago"),
	NotificationItem("Metabolic Biochemistry for High School", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1 hour ago")
)

@Composable
fun NotificationScreen(onBack: (() -> Unit)? = null) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White)
	) {
		NotificationTopBar(onBack)
		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			contentPadding = PaddingValues(vertical = 8.dp)
		) {
			items(notifications) { item ->
				NotificationCard(item)
			}
		}
	}
}

@Composable
private fun NotificationTopBar(onBack: (() -> Unit)?) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.White)
			.padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		IconButton(onClick = { onBack?.invoke() }) {
			Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF23223C))
		}
		Spacer(Modifier.width(4.dp))
		Text(
			text = "Notification",
			fontWeight = FontWeight.Bold,
			fontSize = 20.sp,
			color = Color(0xFF23223C)
		)
	}
}

@Composable
private fun NotificationCard(item: NotificationItem) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 12.dp, vertical = 6.dp)
			.clip(RoundedCornerShape(12.dp))
			.background(Color(0xFFF7F8FA)),
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			modifier = Modifier
				.padding(12.dp)
				.size(32.dp)
				.clip(RoundedCornerShape(8.dp))
				.background(Color(0xFFE5E5F0)),
			contentAlignment = Alignment.Center
		) {
			Icon(Icons.Default.Notifications, contentDescription = null, tint = Color(0xFF3B5FFF), modifier = Modifier.size(20.dp))
		}
		Column(modifier = Modifier.weight(1f)) {
			Text(item.title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = Color(0xFF23223C), maxLines = 1, overflow = TextOverflow.Ellipsis)
			Text(item.subtitle, fontSize = 13.sp, color = Color(0xFF9A9A9A), maxLines = 1, overflow = TextOverflow.Ellipsis)
		}
		Text(item.time, fontSize = 12.sp, color = Color(0xFF9A9A9A), modifier = Modifier.padding(end = 12.dp))
	}
}
