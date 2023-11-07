package ua.searchgifs.apptech.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ua.searchgifs.apptech.domain.repository.NetworkConnectionChecker
import ua.searchgifs.apptech.presantation.viewmodel.MainViewModel


@Module
@InstallIn(ActivityComponent::class)
class PresentationModule {

    @Provides
    fun provideMainViewModel(networkConnectionChecker: NetworkConnectionChecker): MainViewModel {
        return MainViewModel(networkConnectionChecker)
    }
}