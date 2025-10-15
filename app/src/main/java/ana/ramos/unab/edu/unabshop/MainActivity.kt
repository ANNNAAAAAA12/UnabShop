package ana.ramos.unab.edu.unabshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ana.ramos.unab.edu.unabshop.ui.theme.UnabShopTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
          val navController = rememberNavController()
            val startDestination = "login"
          NavHost(navController,startDestination) {
              composable(route = "login") {
                  LoginScreen()
              }
                  composable(route = "Register"){
                      RegisterScreen()}
                      composable(route = "Home"){
                          HomeScreen()
              }
          }
        }
    }
}
