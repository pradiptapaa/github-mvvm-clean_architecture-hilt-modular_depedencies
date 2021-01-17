package id.pradiptapaa.github.framework.network

import id.pradiptapaa.user.domain.UserSearch
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GithubApiService {

    @GET("search/users")
    suspend fun searchUser(
        @QueryMap condition: HashMap<String, Any>
    ): UserSearch

}