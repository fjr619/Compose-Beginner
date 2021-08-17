package com.fjr619.composebeginner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjr619.composebeginner.presentation.ui.theme.ComposeBeginnerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var app: BaseApplication

    @Inject
    lateinit var randomString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            ComposeBeginnerTheme{
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background,
//                modifier = Modifier.fillMaxSize()) {
//                    BasicRow()
//                }
//            }
//        }

        setContentView(R.layout.activity_main)
        println("onCreate: the app context: ${app}")
        println("onCreate: ${randomString}")
    }
}

//@Composable
//fun BasicColumn() {
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .verticalScroll(state = ScrollState(0))) {
//        Image(
//            painterResource(R.drawable.happy_meal_small),
//            contentDescription = "",
//            modifier = Modifier.height(300.dp).fillMaxWidth(),
//            contentScale = ContentScale.Crop
//        )
//
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(
//                text = "Happy Meal",
//                fontSize = 26.sp
//            )
//            Spacer(modifier = Modifier.padding(top = 10.dp))
//            Text(
//                text = "800 Calories",
//                fontSize = 17.sp
//            )
//            Spacer(modifier = Modifier.padding(top = 10.dp))
//            Text(
//                text = "$5.99",
//                color = Color(0xFF85bb65),
//                fontSize = 14.sp
//            )
//        }
//    }
//}
//
//@Composable
//fun BasicRow() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(state = ScrollState(0))
//
//    ) {
//        Image(
//            painterResource(R.drawable.happy_meal_small),
//            contentDescription = "",
//            modifier = Modifier.height(300.dp).fillMaxWidth(),
//            contentScale = ContentScale.Crop
//        )
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//            ) {
//
//                //https://stackoverflow.com/a/66622843
//                Text(
//                    text = "Happy Happy",
//                    fontSize = 26.sp,
//                    modifier = Modifier.weight(1f)
//                )
//
//                Text(
//                    text = "$5.99",
//                    color = Color(0xFF85bb65),
//                    fontSize = 14.sp,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//            }
//
//            Spacer(modifier = Modifier.padding(top = 10.dp))
//            Text(
//                text = "800 Calories",
//                fontSize = 17.sp
//            )
//
//            Spacer(modifier = Modifier.padding(top = 10.dp))
//            Button(
//                onClick = {},
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                Text(text = "button")
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//    Text("Hello World $name", Modifier.fillMaxWidth())
//}
//
//@Composable
//fun DefaultPreview() {
//    ComposeBeginnerTheme {
//        Greeting("Android")
//    }
//}