

package com.sandeep.newsfly.ui.viewmodel

import androidx.lifecycle.*
import com.sandeep.newsfly.storage.DataStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StorageViewModel @Inject constructor(private val dataStorage: DataStorage) : ViewModel()  {

    /**
     * This sets the theme to either light or dark mode
     */
    val selectedTheme = dataStorage.selectedTheme().asLiveData()
    fun changeSelectedTheme(theme: String){
        viewModelScope.launch {
            dataStorage.setSelectedTheme(theme)
        }
    }


    val isUsersFirstTime = dataStorage.isUserFirstTime().asLiveData()
    fun changeUsersFirstTime(isFirstTime: Boolean){
        viewModelScope.launch {
            dataStorage.setUserFirstTime(isFirstTime)
        }
    }

    private val _hasClickedRetryButton = MutableLiveData<Boolean>()
    val hasClickedRetryButton: LiveData<Boolean> = _hasClickedRetryButton

    fun userClickedRetryButton(isClicked: Boolean) {
        _hasClickedRetryButton.value = isClicked
    }

}