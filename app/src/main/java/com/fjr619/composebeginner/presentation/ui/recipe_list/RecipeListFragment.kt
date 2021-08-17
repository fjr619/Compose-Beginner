package com.fjr619.composebeginner.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fjr619.composebeginner.R
import com.fjr619.composebeginner.presentation.ui.theme.HorizontalDottedProgress
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("RecipeListFragment: $viewModel")
        println("RecipeListFragment: repository: ${viewModel.getRepo()}")
        println("RecipeListFragment: token: ${viewModel.getAuthToken()}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            Column(modifier = Modifier
                .border(border = BorderStroke(1.dp, Color.Black))
                .padding(16.dp)
            ) {
                Text("THIS IS A COMPOSABLE INSIDE FRAGMENT")
                Spacer(modifier = Modifier.padding(10.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.padding(10.dp))

                val customView = HorizontalDottedProgress(LocalContext.current)
                AndroidView(factory = { customView })

                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = {
                        findNavController().navigate(R.id.action_recipeListFragment_to_recipeFragment)
                    }
                ) {
                    Text(text = "To recipe fragment")
                }
            }
        }
        return view
    }
}