package id.pradiptapaa.github.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.pradiptapaa.github.framework.interactor.SearchUserDataSourceImpl
import id.pradiptapaa.github.framework.network.GithubApiService
import id.pradiptapaa.user.data.SearchDataSource
import id.pradiptapaa.user.data.SearchRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides fun provideSearchRepository(dataSource: SearchUserDataSourceImpl): SearchRepository =
        SearchRepository(dataSource)

}