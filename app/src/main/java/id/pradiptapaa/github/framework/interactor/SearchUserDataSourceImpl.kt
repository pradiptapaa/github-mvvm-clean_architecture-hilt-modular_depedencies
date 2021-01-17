package id.pradiptapaa.github.framework.interactor

import id.pradiptapaa.github.framework.network.GithubApiService
import id.pradiptapaa.user.data.SearchDataSource
import id.pradiptapaa.user.domain.UserSearch
import javax.inject.Inject

class SearchUserDataSourceImpl
@Inject constructor(private val service: GithubApiService) : SearchDataSource{

    override suspend fun searchUser(parameter: HashMap<String, Any>): UserSearch {
        return service.searchUser(parameter)
    }
}