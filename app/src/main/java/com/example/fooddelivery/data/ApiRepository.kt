package com.example.fooddelivery.data

import com.example.fooddelivery.data.entity.login.LoginRequest
import com.example.fooddelivery.data.entity.register.RegisterRequest
import com.example.fooddelivery.data.local.LocalDataSource
import com.example.fooddelivery.data.remote.RemoteDataSource
import com.example.fooddelivery.utils.performAuthTokenNetworkOperation
import com.example.fooddelivery.utils.performNetworkOperation
import com.example.fooddelivery.utils.room.LocalOrder
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var localDataSource: LocalDataSource,
    private var remoteDataSource: RemoteDataSource
) {
    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )
    
    fun register(request: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postRegister(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun logOutUser(){
        localDataSource.saveToken("")
    }

    fun getUser() =
        performNetworkOperation {
            remoteDataSource.getUser()
        }

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }

    fun getRestaurantById(id: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantById(id)
        }

    fun getRestaurantByCuisine(cuisine: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantsByCuisine(cuisine)
        }

    fun getMealById(id: String) =
        performNetworkOperation {
            remoteDataSource.getMealById(id)
        }

    fun listOrders():List<LocalOrder> {
        return localDataSource.listOrders()
    }

    fun addOrder(localOrder : LocalOrder){
        localDataSource.addOrder(localOrder)
    }

    fun removeOrder(localOrder : LocalOrder){
        localDataSource.removeOrder(localOrder)
    }
}