package id.pradiptapaa.github.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import id.pradiptapaa.github.R
import id.pradiptapaa.github.infrastructure.utils.loadFromDrawable
import kotlinx.android.synthetic.main.layout_search_user.*
import kotlinx.android.synthetic.main.partial_layout_error.*
import kotlinx.android.synthetic.main.partial_layout_error.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchUserActivity : AppCompatActivity() {

    private val viewModel: SearchUserViewModel by viewModels()

    private var _mAdapter: UserListAdapter? = null
    private val mAdapter get() = _mAdapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_search_user)
        setOnClickListener()
        setAdapter()
        setQuery()
    }

    private fun isEmpty(
        refresh: LoadState,
        adapter: UserListAdapter
    ) = refresh is LoadState.NotLoading && adapter.itemCount == 0

    private fun isError(
        refresh: LoadState
    ) = refresh is LoadState.Error

    private fun setErrorMsg(
        refresh: LoadState,
        adapter: UserListAdapter,
        loadState: CombinedLoadStates
    ): String {
        val errorState = when {
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            else -> null
        }
        if (isEmpty(refresh, adapter)) {
            return getString(R.string.hint_error_msg_user_not_found, search_user_et_query.text)
        } else {
            errorState?.let {
                return it.error.message!!
            } ?: run {
                return getString(R.string.hint_error_msg_general)
            }
        }
    }

    private fun setErrorTitle(refresh: LoadState, adapter: UserListAdapter): String {
        return if (isEmpty(refresh, adapter)) {
            getString(R.string.hint_error_title_user_not_found)
        } else {
            getString(R.string.hint_error_title_general)
        }
    }

    private fun setErrorDrawable(refresh: LoadState, adapter: UserListAdapter): Int {
        return if (isEmpty(refresh, adapter)) {
            R.drawable.ic_not_found
        } else {
            R.drawable.ic_error
        }
    }

    private fun setAdapter() {
        _mAdapter = UserListAdapter()
        search_user_rv_list.adapter = mAdapter
        lifecycleScope.launch {
            mAdapter.loadStateFlow.collectLatest { loadStates ->
                val refresh = loadStates.refresh
                search_user_include_loading.isVisible = refresh is LoadState.Loading
                search_user_include_error.apply {

                    partial_layout_error_iv_error_ic.loadFromDrawable(
                        setErrorDrawable(refresh, mAdapter)
                    )

                    partial_layout_error_tv_error_title.text =
                        setErrorTitle(refresh, mAdapter)

                    partial_layout_error_tv_error_msg.text =
                        setErrorMsg(refresh, mAdapter, loadStates)

                    isVisible = isEmpty(refresh, mAdapter) || isError(refresh)
                }
            }
        }
    }

    private fun setOnClickListener() {
        search_user_btn_search.setOnClickListener {
            setQuery(search_user_et_query.text.toString())
        }
        partial_layout_error_btn_error_refresh.setOnClickListener {
            search_user_et_query.setText("")
            setQuery()
        }
    }

    private fun setQuery(query: String = "") {
        lifecycleScope.launch {
            viewModel.getUserList(query).collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mAdapter = null
    }
}