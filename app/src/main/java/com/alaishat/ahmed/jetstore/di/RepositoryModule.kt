package com.alaishat.ahmed.jetstore.di

import com.alaishat.ahmed.jetstore.data.auth.AuthRepository
import com.alaishat.ahmed.jetstore.data.auth.impl.FakeAuthRepository
import com.alaishat.ahmed.jetstore.data.products.ProductsRepository
import com.alaishat.ahmed.jetstore.data.products.impl.FakeProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * Created by Ahmed Al-Aishat on Sep/29/2022.
 * JetStore Project.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
//    @ViewModelScoped
    @Singleton
    fun provideProductsRepository(
        coroutineDispatcher: CoroutineDispatcher,
    ): ProductsRepository = FakeProductsRepository(coroutineDispatcher)

    @Provides
    @Singleton
    fun provideAuthRepository(
        coroutineDispatcher: CoroutineDispatcher,
    ): AuthRepository = FakeAuthRepository(coroutineDispatcher)
}