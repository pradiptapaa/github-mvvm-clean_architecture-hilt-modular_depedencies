package id.pradiptapaa.github.presenter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import id.pradiptapaa.github.framework.interactor.SearchUserPagingSource
import id.pradiptapaa.github.infrastructure.constant.AppConstant
import id.pradiptapaa.github.infrastructure.constant.AppConstant.PAGINATION
import id.pradiptapaa.user.usecase.SearchUser

class SearchUserViewModel @ViewModelInject constructor(
    private val searchUser: SearchUser
) : ViewModel() {

    fun getUserList(query: String = "") = Pager(
        PagingConfig(
            pageSize = PAGINATION.PAGE_SIZE,
            prefetchDistance = PAGINATION.PREFETCH_SIZE
        )
    ) {
        SearchUserPagingSource(searchUser, query.ifEmpty { AppConstant.DEFAULT_QUERY })
    }.flow.cachedIn(viewModelScope)


}