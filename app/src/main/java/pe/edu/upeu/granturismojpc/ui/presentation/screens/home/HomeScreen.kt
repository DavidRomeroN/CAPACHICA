package pe.edu.upeu.granturismojpc.ui.presentation.screens.home

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upeu.granturismojpc.ui.presentation.components.BottomNavigationBar

import pe.edu.upeu.granturismojpc.ui.presentation.components.HeroSection
import pe.edu.upeu.granturismojpc.ui.presentation.components.TopBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.placeholder
import pe.edu.upeu.granturismojpc.R
import pe.edu.upeu.granturismojpc.model.PaqueteResp
import pe.edu.upeu.granturismojpc.ui.navigation.Destinations
import pe.edu.upeu.granturismojpc.ui.presentation.components.LoadingCard

import pe.edu.upeu.granturismojpc.ui.presentation.screens.paquete.PaqueteGestion
import pe.edu.upeu.granturismojpc.ui.presentation.screens.paquete.PaqueteMainViewModel
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import pe.edu.upeu.granturismojpc.ui.presentation.components.CategoryChips
import pe.edu.upeu.granturismojpc.ui.presentation.components.SimpleBottomNavigationBar

@Composable
fun HomeScreen(
    navegarPantalla2: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,


    ) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    //val navController = rememberNavController()

    val openDialog = {
        // Aquí puedes implementar lógica de abrir filtros
        println("Abriendo diálogo de filtros...")
    }

    val displaySnackBar = {
        // Aquí puedes mostrar un SnackBar o cualquier feedback
        println("Mostrando snackbar...")
    }
    val searchQuery = remember { mutableStateOf("") }
    val paquetes by viewModel.prods.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarPaquetes()
    }



    Scaffold(
        topBar = {
            TopBar(
                scope = scope,
                scaffoldState = drawerState,
                openDialog = openDialog,
                displaySnackBar = displaySnackBar
            )
        },
        bottomBar = {
            SimpleBottomNavigationBar(navController=navController)



        }
    ) { innerPadding ->
        // Usar LazyColumn en lugar de Column con verticalScroll
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Aplicar el padding del Scaffold
        ) {
            item {
                HeroSection(
                    searchQuery = searchQuery.value,
                    onSearchChange = { searchQuery.value = it }
                )
            }

            item {
                OutlinedTextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    label = { Text("Buscar asociaciones") },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
            }
            item {
                CategoryChips(
                    navController=navController
                )
            }

            item {
                Text(
                    text = "Asociaciones Turisticas",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp)
                )
            }
/*
            // Aquí podrías añadir los elementos de la lista de paquetes
            if (isLoading) {
                item {
                    // Mostrar loading
                    LoadingCard()
                }
            } else {
                // Mostrar los paquetes
                items(paquetes.size) { index ->
                    // Aquí iría el componente para mostrar cada paquete
                    //PaqueteItem(paquete = paquetes[index])
                    PaqueteGestion(
                        navController = navController,
                        onAddClick = null,
                        onDeleteClick = null,
                        paquetes = TODO(),
                        isLoading = TODO(),
                        onEditClick = TODO(),
                        viewModel = TODO()
                    )
                }
            }*/
        }
    }
}



