package com.example.mydatasiswa.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mydatasiswa.R
import com.example.mydatasiswa.modeldata.DataSiswa
import com.example.mydatasiswa.uicontroller.SiswaTopAppBar
import com.example.mydatasiswa.viewmodel.DetailViewModel
import com.example.mydatasiswa.viewmodel.PenyediaViewModel
import com.example.mydatasiswa.viewmodel.StatusUIDetail
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaScreen (
    //navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory =
    PenyediaViewModel.Factory)
){
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(R.string.detail_siswa),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },

        floatingActionButton = {
            val uiState = viewModel.statusUIDetail
            FloatingActionButton(
                onClick = {
                    //when(uiState){ is StatusUIDetail.Success ->
                    //navigateToEditItem(uiState.satusiswa.id) else -> {}}
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier .padding(dimensionResource(R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.update)
                )
            }
        }, modifier = modifier
    ){ innerPaddng ->
        val coroutineScope = rememberCoroutineScope()
        BodyDetailDataSiswa(
            statusUIDetail = viewModel.statusUIDetail,
            onDelete = {coroutineScope.launch {
                viewModel.hapusSatuSiswa()
                navigateBack()
            }},
            modifier = Modifier
                .padding(innerPaddng)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun BodyDetailDataSiswa(
    statusUIDetail: StatusUIDetail,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_medium)
        )
    ){
        var deleteConfirmationRequired by rememberSaveable {
            mutableStateOf(false)
        }
        when (statusUIDetail){
            is StatusUIDetail.Success -> DetailDataSiswa(
                siswa = statusUIDetail.satusiswa,
                modifier = Modifier.fillMaxWidth()
            )
            else ->{}
        }

        OutlinedButton(
            onClick = {deleteConfirmationRequired = true},
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.delete))
        }
        if (deleteConfirmationRequired){
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = {deleteConfirmationRequired = false},
                modifier = Modifier.padding(dimensionResource(
                    id = R.dimen.padding_medium
                ))
            )
        }
    }
}

@Composable
fun DetailDataSiswa(
    siswa: DataSiswa, modifier: Modifier = Modifier
){
    Card (
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ){
            BarisDetailData(
                labelResID = R.string.nama1,
                itemDetail = siswa.nama,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )

            BarisDetailData(
                labelResID = R.string.alamat1,
                itemDetail = siswa.alamat,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )

            BarisDetailData(
                labelResID = R.string.telpon1,
                itemDetail = siswa.telpon,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Composable
private fun BarisDetailData(
    @StringRes labelResID: Int, itemDetail: String, modifier: Modifier = Modifier
){
    Row (modifier = modifier){
        Text(stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = {/*Do nothing*/},
        title = { Text(stringResource(R.string.attention))},
        text = { Text(stringResource(R.string.tanya))},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(stringResource(R.string.yes))
            }
        })
}