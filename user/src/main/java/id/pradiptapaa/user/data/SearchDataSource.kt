package id.pradiptapaa.user.data

import id.pradiptapaa.user.domain.UserSearch

interface SearchDataSource {

    suspend fun searchUser(parameter: HashMap<String, Any>) : UserSearch

}