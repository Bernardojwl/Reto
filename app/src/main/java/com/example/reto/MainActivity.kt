package com.example.reto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.reto.ui.theme.RetoTheme
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetoTheme {
                Screen()
            }
        }
    }
}


//División de las diferentes partes del código (TopBar, menú, screens y navegación)
@Composable
fun Screen(modifier: Modifier = Modifier){
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController, drawerState)
            }

        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.apply {
                                if(isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ){ padding ->
            ScreenContent(navController = navController, modifier = Modifier.padding(padding))
        }
    }
}


//Menú de navegación
@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState, modifier: Modifier = Modifier){
    val scope = rememberCoroutineScope()

    Image(
        painter = painterResource(id = R.drawable.buffeeee),
        contentDescription = "Logo",
        modifier = Modifier
            .background(Color(0xFF2541B2))
            .fillMaxWidth()
    )
    HorizontalDivider()

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "Página Principal"
            )
        },
        label = {
            Text(
                text = "Página Principal",
                fontSize = 17.sp,
            )
        },
        selected = false,
        onClick = {
            scope.launch {
                navController.navigate("mainpage")
                drawerState.close()
            }
        }
    )


    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Info,
                contentDescription = "Abogados"
            )
        },
        label = {
            Text(
                text = "Información de Abogados",
                fontSize = 17.sp,
            )
        },
        selected = false,
        onClick = {
            scope.launch {
                navController.navigate("screen2")
                drawerState.close()
            }
        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Clients"
            )
        },
        label = {
            Text(
                text = "Catálogo de Clientes",
                fontSize = 17.sp,
            )
        },
        selected = false,
        onClick = {
            scope.launch {
                navController.navigate("screen3")
                drawerState.close()
            }
        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.List,
                contentDescription = "Procesos legales"
            )
        },
        label = {
            Text(
                text = "Procesos Legales Bufetec",
                fontSize = 17.sp,
            )
        },
        selected = false,
        onClick = {
            scope.launch {
                navController.navigate("screen3")
                drawerState.close()
            }
        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Create,
                contentDescription = "Foro"
            )
        },
        label = {
            Text(
                text = "Foro",
                fontSize = 17.sp,
            )
        },
        selected = false,
        onClick = {
            scope.launch {
                navController.navigate("screen3")
                drawerState.close()
            }
        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Face,
                contentDescription = "Caso"
            )
        },
        label = {
            Text(
                text = "Ver mi caso",
                fontSize = 17.sp,
            )
        },
        selected = false,
        onClick = {
            scope.launch {
                navController.navigate("screen2")
                drawerState.close()
            }
        }
    )
}


//Aquí van las screens
@Composable
fun ScreenContent(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "mainpage",
    ) {
        composable("mainpage") {
            MainPageContent(navController, modifier = modifier)
        }
        composable("screen2") {
            Screen2Content()
        }
        composable("screen3") {
            Screen3Content()
        }
    }
}


//Top Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onOpenDrawer: () -> Unit
    ){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF2541B2),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(28.dp)
                    .clickable{
                    onOpenDrawer()
                }
            )
        },
        title = {
            Image(
                painter = painterResource(id = R.drawable.buffeeee),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(start = 50.dp)
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(28.dp)
            )
        }
    )
}