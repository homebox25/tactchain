package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tactchain.dao.AssetDao
import com.tactchain.model.Asset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val dao: AssetDao) : ViewModel() {
    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets = _assets.asStateFlow()

    fun loadAssets() {
        viewModelScope.launch {
            _assets.value = dao.getAll()
        }
    }

    fun addAsset(assetId: String, status: String) {
        viewModelScope.launch {
            dao.insert(Asset(assetId, status))
            loadAssets()
        }
    }
}
