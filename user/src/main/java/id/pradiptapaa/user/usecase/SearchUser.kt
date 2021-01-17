package id.pradiptapaa.user.usecase

import id.pradiptapaa.user.data.SearchRepository
import javax.inject.Inject

class SearchUser
@Inject constructor(private val repository: SearchRepository){

    suspend operator fun invoke(parameter: HashMap<String, Any>) = repository.searchUsers(parameter)

}