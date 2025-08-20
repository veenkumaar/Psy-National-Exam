package com.example.psynationalexam.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.composed
import androidx.navigation.NavHostController
import com.example.psynationalexam.AppDestinations
import com.example.psynationalexam.model.Course
import com.example.psynationalexam.model.NewsItem


private val recentCourses = listOf(
    Course("1", "Mathematics", "High School Algebra I: Help and Review", 12, 0.5f, "12h 20m"),
    Course("2","Mathematics","Enlargement to Trigonometry",10,0.2f,"8h 05m")
)

private val recommendedCourses = listOf(
    Course("3","Biology","Bacterial Biology Overview",12,0f,"12 Lessons"),
    Course("4","Biology","Mendelian Genetics & Mechanisms of Heredity",14,0f,"14 Lessons"),
    Course("5","Biology","Metabolism for High School",12,0f,"12 Lessons")
)

private val newsItems = listOf(
    NewsItem(
        "1",
        "Biology",
        "The Effects of Temperature on Enzyme Activity and Biology",
        "1 hour ago",
        4795
    ),
    NewsItem("2","Mathematics","How to Work Out a Percentage Using a Calculator","24 August 2020",4795)
)

@Composable
fun HomeScreen(navController: NavHostController) { // <-- Add NavController
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Recommended", "Algebra", "Geometry")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item { GreetingHeader(navController = navController) } // <-- Pass NavController
        item { Spacer(Modifier.height(16.dp)) }
        item { RecentLearningSection(recentCourses) }
        item { Spacer(Modifier.height(24.dp)) }
        item { CategoryTabs(tabs, selectedTab) { selectedTab = it } }
        item { Spacer(Modifier.height(12.dp)) }
        item { RecommendedCoursesSection(recommendedCourses) }
        item { Spacer(Modifier.height(24.dp)) }
        item { LatestNewsHeader() }
        items(newsItems) { news -> NewsRow(news) }
    }
}


@Composable
private fun GreetingHeader(navController: NavHostController) { // <-- Add NavController
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Hi, Christina", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF23223C))
                Spacer(Modifier.height(4.dp))
                Text("What do you want to learn today?", fontSize = 14.sp, color = Color(0xFF9A9A9A))
            }
            NotificationIcon(navController = navController) // <-- Pass NavController
        }
        Spacer(Modifier.height(20.dp))
        SearchBar()
    }
}


@Composable
private fun NotificationIcon(navController: NavHostController) { // <-- Add NavController
    Box(
        modifier = Modifier
            .size(40.dp)
            .clickable { // <-- Add clickable modifier here
                navController.navigate(AppDestinations.NOTIFICATIONS_ROUTE)
            },
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = Color(0xFF23223C),
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(Color(0xFF3B5FFF))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar() {
    // Placeholder search bar look
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search", color = Color(0xFF9A9A9A)) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF9A9A9A)) },
        singleLine = true,
        enabled = false, // static for now
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color(0xFFE5E5F0),
            disabledContainerColor = Color.White,
            disabledTextColor = Color.Black,
            disabledLeadingIconColor = Color(0xFF9A9A9A),
            disabledPlaceholderColor = Color(0xFF9A9A9A)
        )
    )
}

@Composable
private fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(text, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF23223C), modifier = modifier)
}

@Composable
private fun RecentLearningSection(courses: List<Course>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SectionTitle("Recent learning", modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(12.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(courses) { course -> RecentCourseCard(course) }
        }
    }
}

@Composable
private fun RecentCourseCard(course: Course) {
    ElevatedCard(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(190.dp)
            .padding(end = 12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE5F0FF)),
                contentAlignment = Alignment.Center
            ) {
                if (course.imageRes != null) {
                    Image(painter = painterResource(id = course.imageRes), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                } else {
                    Text(course.subject, color = Color(0xFF3B5FFF), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(course.subject, fontSize = 11.sp, color = Color(0xFF9A9A9A))
            Spacer(Modifier.height(2.dp))
            Text(course.title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 16.sp)
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
            Spacer(Modifier.height(4.dp))
            Text("${(course.progress * 10).toInt()}/10", fontSize = 11.sp, color = Color(0xFF9A9A9A))
        }
    }
}

@Composable
private fun CategoryTabs(tabs: List<String>, selected: Int, onSelect: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            tabs.forEachIndexed { index, title ->
                Column(
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .clickableNoIndication { onSelect(index) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = if (selected == index) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (selected == index) Color(0xFF23223C) else Color(0xFF9A9A9A)
                    )
                    Spacer(Modifier.height(6.dp))
                    if (selected == index) {
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(60.dp)
                                .background(Color(0xFF3B5FFF), RoundedCornerShape(1.dp))
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RecommendedCoursesSection(courses: List<Course>) {
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
        items(courses) { course -> RecommendedCourseCard(course) }
    }
}

@Composable
private fun RecommendedCourseCard(course: Course) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(160.dp)
            .padding(end = 16.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF3B5FFF).copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text("Img", color = Color(0xFF3B5FFF), fontSize = 12.sp)
            }
            Spacer(Modifier.height(8.dp))
            Text(course.title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, maxLines = 3, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(course.lessons.toString() + " Lessons", fontSize = 11.sp, color = Color(0xFF9A9A9A))
                Spacer(Modifier.width(8.dp))
                Text(course.duration, fontSize = 11.sp, color = Color(0xFF9A9A9A))
            }
        }
    }
}

@Composable
private fun LatestNewsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SectionTitle("Latest News")
        Text("See all", fontSize = 12.sp, color = Color(0xFF3B5FFF))
    }
    Spacer(Modifier.height(12.dp))
}

@Composable
private fun NewsRow(item: NewsItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF23223C).copy(alpha = 0.85f))
        )
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(item.category, fontSize = 11.sp, color = Color(0xFF9A9A9A))
            Spacer(Modifier.height(4.dp))
            Text(item.title, fontSize = 14.sp, fontWeight = FontWeight.Medium, lineHeight = 18.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(item.time, fontSize = 11.sp, color = Color(0xFF9A9A9A))
                Spacer(Modifier.width(12.dp))
                Text("${item.views} views", fontSize = 11.sp, color = Color(0xFF9A9A9A))
            }
        }
    }
}

// Utility to make a clickable area without ripple for tab text
private fun Modifier.clickableNoIndication(onClick: () -> Unit): Modifier = composed {
    val source = remember { MutableInteractionSource() }
    this.clickable(indication = null, interactionSource = source) { onClick() }
}
