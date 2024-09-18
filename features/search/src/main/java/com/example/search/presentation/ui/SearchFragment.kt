package com.example.search.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.search.databinding.FragmentSearchBinding
import com.example.search.presentation.adapter.SearchScreenItem
import com.example.search.presentation.adapter.bottomDelegate
import com.example.search.presentation.adapter.headerCollapsedDelegate
import com.example.search.presentation.adapter.headerExpandedDelegate
import com.example.search.presentation.adapter.vacancyDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel>()

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private lateinit var adapter: ListDelegationAdapter<List<SearchScreenItem>>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when(it){
                        is SearchScreenState.Error -> {}
                        SearchScreenState.Loading -> {}
                        is SearchScreenState.Search -> {
                            adapter.items = it.data
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListDelegationAdapter<List<SearchScreenItem>>(
            headerCollapsedDelegate{ link ->
                openLink(link)
            },
            headerExpandedDelegate {
                viewModel.changeExpandState()
            },
            vacancyDelegate(
                unsetLike = { id ->
                    viewModel.unsetFavorite(id)
                },
                setLike = { id ->
                    viewModel.setFavorite(id)
                }
            ),
            bottomDelegate {
                viewModel.changeExpandState()
            }
        )
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun openLink(link: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}



