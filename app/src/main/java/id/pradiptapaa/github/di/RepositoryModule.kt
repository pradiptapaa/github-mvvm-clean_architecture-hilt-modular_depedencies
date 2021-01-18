package id.pradiptapaa.github.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.pradiptapaa.github.framework.interactor.SearchUserDataSourceImpl
import id.pradiptapaa.user.data.SearchRepository

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides fun provideSearchRepository(dataSource: SearchUserDataSourceImpl): SearchRepository =
        SearchRepository(dataSource)

}