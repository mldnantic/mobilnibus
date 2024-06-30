package com.example.mobilnibus.viemodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mobilnibus.model.UserModel
import com.example.mobilnibus.storage.UserStorageService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val storageService: UserStorageService):ViewModel(){

    var currentUserModel: UserModel by mutableStateOf(UserModel())
    private set

    fun setCurrentUser(userModel: UserModel){
        currentUserModel = userModel
    }

    fun resetCurrentUser()
    {
        currentUserModel=UserModel()
    }

    val users: Flow<List<UserModel>> = storageService.users

    fun addUser(uuid: String,firstName:String,lastName:String,phone:String)
    {
        val u = UserModel(uuid=uuid,firstName=firstName,lastName=lastName,phone=phone)
        viewModelScope.launch{
            storageService.save(u)
        }
    }

    //TODO Other CRUD methods for users
    fun getUser(uuid: String)
    {
        viewModelScope.launch {
            storageService.getUserByUID(uuid)
        }
    }
}

class UserViewModelFactory(private val storageService: UserStorageService):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(storageService) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }

}
