package com.aimusic.pocketmusician

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aimusic.pocketmusician.ui.theme.*
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

//class MainActivity : ComponentActivity() {
//    @OptIn(ExperimentalMaterial3Api::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            PocketMusicianTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Scaffold(
//                        floatingActionButton = {
//                            ExtendedFloatingActionButton(
//                                onClick = {},
//                                text = {Text(text = "Add songs")},
//                                icon = {Icon(Icons.Filled.Add, null)},
//                                containerColor = MaterialTheme.colorScheme.primary,
//                                contentColor = MaterialTheme.colorScheme.onPrimary
//                            )
//                        },
//                        topBar = {
//                            TopAppBar(
//                                title = {
//                                    Text(text = "Queue")
//                                },
//                                colors = TopAppBarDefaults.smallTopAppBarColors(
//                                    containerColor = MaterialTheme.colorScheme.inverseSurface,
//                                    titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
//                                )
//                            )
//                        }
//                    ){values ->
//                        LazyColumn(contentPadding = values){
//                            items(50) {
//                                ListItem(
//                                    headlineText = { Text("Song $it") },
//                                    colors = ListItemDefaults.colors(
//                                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
//                                        headlineColor = MaterialTheme.colorScheme.onSurfaceVariant
//                                    ),
//                                    //modifier = Modifier.padding(1.dp),
//                                    trailingContent = { Icon(Icons.Filled.Favorite, contentDescription = null) }
//                                )
//                                Divider(color = MaterialTheme.colorScheme.inverseSurface)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketMusicianTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(20.dp)
                    ){
                        Text(
                            text = "Music Type",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            mainAxisSpacing = 8.dp,
                            mainAxisSize = SizeMode.Wrap
                        ) {
                            AssistChip(
                                onClick = { },
                                colors = AssistChipDefaults.assistChipColors(
                                    leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(text = "Rock")
                                }
                            )
                            AssistChip(
                                onClick = { },
                                colors = AssistChipDefaults.assistChipColors(
                                    leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(text = "Dance")
                                }
                            )
                            AssistChip(
                                onClick = { },
                                colors = AssistChipDefaults.assistChipColors(
                                    leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(text = "Relaxing")
                                }
                            )
                            AssistChip(
                                onClick = { },
                                colors = AssistChipDefaults.assistChipColors(
                                    leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(text = "Study")
                                }
                            )
                            AssistChip(
                                onClick = { },
                                colors = AssistChipDefaults.assistChipColors(
                                    leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(text = "Electronic")
                                }
                            )
                            AssistChip(
                                onClick = { },
                                colors = AssistChipDefaults.assistChipColors(
                                    leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(text = "Suspenseful")
                                }
                            )
                        }
                        Divider(color = MaterialTheme.colorScheme.inverseSurface)

                    }
                }
            }
        }
    }
}



//                        LazyColumn(contentPadding = values){
//                            items(50) {
//                                Card(
//                                    modifier = Modifier.fillMaxWidth().padding(5.dp),
//                                    shape = MaterialTheme.shapes.small,
//                                    colors = CardDefaults.cardColors(
//                                        containerColor = MaterialTheme.colorScheme.surfaceVariant
//                                    )
//                                ){
//                                    Column(
//                                        modifier = Modifier.padding(5.dp)
//                                    ){
//                                        Text(
//                                            text = "Song $it"
//                                        )
//                                    }
//                                }
//                            }
//                        }