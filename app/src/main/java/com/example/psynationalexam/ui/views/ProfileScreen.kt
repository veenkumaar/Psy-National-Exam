package com.example.psynationalexam.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.psynationalexam.R

@Composable
fun ProfileScreen() {
	var selectedTab by remember { mutableStateOf(0) }
	val tabs = listOf("General", "Badges")
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White),
		horizontalAlignment = Alignment.CenterHorizontally,
		contentPadding = PaddingValues(bottom = 32.dp)
	) {
		item { Spacer(Modifier.height(24.dp)) }
		item { ProfileHeader() }
		item { Spacer(Modifier.height(16.dp)) }
		item { ProfileTabs(tabs, selectedTab) { selectedTab = it } }
		item { Spacer(Modifier.height(16.dp)) }
		if (selectedTab == 0) {
			item { GeneralSection() }
		} else {
			item { BadgesSection() }
		}
		item { Spacer(Modifier.height(32.dp)) }
		item { BecomePremiumButton() }
		item { Spacer(Modifier.height(16.dp)) }
		item { LogOutButton() }
	}
}

@Composable
private fun ProfileHeader() {
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		Box(
			modifier = Modifier
				.size(96.dp)
				.clip(CircleShape)
				.background(Color(0xFFE5E5F0)),
			contentAlignment = Alignment.Center
		) {
			// Placeholder for profile image
			Icon(
				imageVector = Icons.Default.AccountCircle,
				contentDescription = null,
				tint = Color.Black,
				modifier = Modifier.size(80.dp)
			)
		}
		Spacer(Modifier.height(12.dp))
		Text("Christina Angela", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color(0xFF23223C))
		Spacer(Modifier.height(2.dp))
		Text("@angela.christina123", fontSize = 15.sp, color = Color(0xFF9A9A9A))
	}
}

@Composable
private fun ProfileTabs(tabs: List<String>, selected: Int, onSelect: (Int) -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 32.dp),
		horizontalArrangement = Arrangement.Center
	) {
		tabs.forEachIndexed { i, label ->
			Button(
				onClick = { onSelect(i) },
				shape = RoundedCornerShape(10.dp),
				colors = ButtonDefaults.buttonColors(
					containerColor = if (selected == i) Color(0xFF3B5FFF) else Color.White,
					contentColor = if (selected == i) Color.White else Color(0xFF3B5FFF)
				),
				modifier = Modifier
					.weight(1f)
					.height(44.dp)
					.then(if (i == 0) Modifier else Modifier.padding(start = 8.dp))
			) {
				Text(label, fontWeight = FontWeight.Medium, fontSize = 16.sp)
			}
		}
	}
}

@Composable
private fun GeneralSection() {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 20.dp)
	) {
		ProfileField(
			icon = Icons.Default.Person,
			label = "Name",
			value = "Christina Angela",
			actionText = "Edit"
		)
		ProfileField(
			icon = Icons.Default.Email,
			label = "Email",
			value = "christina.angela123@mail.com",
			actionText = "Edit"
		)
		ProfileField(
			icon = Icons.Default.Lock,
			label = "Password",
			value = "Tap to Change Password",
			actionText = "Edit",
			isPassword = true
		)
		ProfileField(
			icon = Icons.Default.Phone,
			label = "Phone Number",
			value = "(684) 555-0102",
			actionText = "Edit"
		)
		ProfileField(
			icon = Icons.Default.CreditCard,
			label = "Payment",
			value = "Tap to Change Payment",
			actionText = "Edit"
		)
		ProfileField(
			icon = Icons.Default.Security,
			label = "Privacy Policy",
			value = "Tap to See Privacy Policy",
			actionText = ">"
		)
	}
}

@Composable
private fun ProfileField(
	icon: androidx.compose.ui.graphics.vector.ImageVector,
	label: String,
	value: String,
	actionText: String,
	isPassword: Boolean = false
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			modifier = Modifier
				.size(36.dp)
				.clip(RoundedCornerShape(8.dp))
				.background(Color(0xFFEEF2FF)),
			contentAlignment = Alignment.Center
		) {
			Icon(icon, contentDescription = null, tint = Color(0xFF3B5FFF), modifier = Modifier.size(20.dp))
		}
		Spacer(Modifier.width(12.dp))
		Column(modifier = Modifier.weight(1f)) {
			Text(label, fontSize = 13.sp, color = Color(0xFF9A9A9A))
			if (isPassword) {
				Row(verticalAlignment = Alignment.CenterVertically) {
					Text(value, fontSize = 15.sp, color = Color(0xFF23223C))
					Spacer(Modifier.width(8.dp))
					Icon(Icons.Default.VisibilityOff, contentDescription = null, tint = Color(0xFF9A9A9A), modifier = Modifier.size(16.dp))
				}
			} else {
				Text(value, fontSize = 15.sp, color = Color(0xFF23223C))
			}
		}
		Spacer(Modifier.width(8.dp))
		Text(
			actionText,
			color = if (actionText == ">") Color(0xFF3B5FFF) else Color(0xFF3B5FFF),
			fontSize = 14.sp,
			fontWeight = FontWeight.Medium,
			modifier = Modifier.clickable { }
		)
	}
}

@Composable
private fun BadgesSection() {
	val badges = listOf(
		"Good Listener" to "Lorem ipsum dolor sit amet",
		"Focused" to "Lorem ipsum dolor sit amet",
		"Fast Learner" to "Lorem ipsum dolor sit amet",
		"Story Teller" to "Lorem ipsum dolor sit amet",
		"Champion" to "Lorem ipsum dolor sit amet"
	)
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 20.dp)
	) {
		badges.forEach { (title, desc) ->
			BadgeRow(title, desc)
			Spacer(Modifier.height(10.dp))
		}
	}
}

@Composable
private fun BadgeRow(title: String, desc: String) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier.fillMaxWidth()
	) {
		// Use a placeholder medal icon
		Icon(
			imageVector = Icons.Default.EmojiEvents,
			contentDescription = null,
			tint = Color(0xFFFFC107),
			modifier = Modifier.size(32.dp)
		)
		Spacer(Modifier.width(12.dp))
		Column {
			Text(title, fontWeight = FontWeight.Medium, fontSize = 15.sp, color = Color(0xFF23223C))
			Text(desc, fontSize = 13.sp, color = Color(0xFF9A9A9A), maxLines = 1, overflow = TextOverflow.Ellipsis)
		}
	}
}

@Composable
private fun BecomePremiumButton() {
	OutlinedButton(
		onClick = {},
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 32.dp)
			.height(48.dp),
		shape = RoundedCornerShape(12.dp),
		colors = ButtonDefaults.outlinedButtonColors(
			containerColor = Color.White,
			contentColor = Color(0xFF3B5FFF)
		)
	) {
		Text("Become Premium", fontSize = 16.sp, fontWeight = FontWeight.Medium)
	}
}

@Composable
private fun LogOutButton() {
	TextButton(
		onClick = {},
		modifier = Modifier.fillMaxWidth(),
	) {
		Text("LOG OUT", fontSize = 16.sp, color = Color(0xFF3B5FFF), fontWeight = FontWeight.Bold)
	}
}
