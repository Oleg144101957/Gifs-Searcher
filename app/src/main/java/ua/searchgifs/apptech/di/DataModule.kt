package ua.searchgifs.apptech.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ua.searchgifs.apptech.data.repository.NetworkConnectionCheckerImpl
import ua.searchgifs.apptech.domain.repository.NetworkConnectionChecker


@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun provideNetworkCheckerRepository(@ApplicationContext context: Context): NetworkConnectionChecker {
        return NetworkConnectionCheckerImpl(context)
    }

}