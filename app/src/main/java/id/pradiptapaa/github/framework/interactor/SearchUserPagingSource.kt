package id.pradiptapaa.github.framework.interactor

import androidx.paging.PagingSource
import id.pradiptapaa.github.infrastructure.constant.AppConstant.PAGINATION
import id.pradiptapaa.user.domain.User
import id.pradiptapaa.user.usecase.SearchUser
import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SearchUserPagingSource (
    private val searchUser: SearchUser,
    private val query: String
) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page  = params.key ?: PAGINATION.DEFAULT_PAGE_COUNT
            val parameter = hashMapOf<String, Any>("page" to page, "q" to query)
            val users = searchUser(parameter).items
            LoadResult.Page(
                data = users!!,
                prevKey = if (page == PAGINATION.DEFAULT_PAGE_COUNT) null else page - 1,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        } catch (e: UnknownHostException) {
            LoadResult.Error(e)
        } catch (e: SocketTimeoutException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        } catch (e: InterruptedIOException){
            LoadResult.Error(e)
        } catch (e: IOException){
            LoadResult.Error(e)
        }
    }
}