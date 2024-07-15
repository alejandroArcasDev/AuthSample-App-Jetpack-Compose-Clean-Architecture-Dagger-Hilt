package alejandroarcas.authsample.di

import alejandroarcas.authsample.data.local.DataStoreRepository
import alejandroarcas.authsample.data.local.DataStoreRepositoryImpl
import alejandroarcas.authsample.data.remote.repository.UserRepositoryImpl
import alejandroarcas.authsample.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(impl: DataStoreRepositoryImpl): DataStoreRepository

}