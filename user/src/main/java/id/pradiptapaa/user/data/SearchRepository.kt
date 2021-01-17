package id.pradiptapaa.user.data

import javax.inject.Inject

class SearchRepository
@Inject constructor(private val dataSource: SearchDataSource) {

    suspend fun searchUsers(parameter: HashMap<String, Any>) =
        dataSource.searchUser(parameter)

}