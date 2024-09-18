package com.example.favorites.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorites.databinding.FragmentFavoritesBinding
import com.example.favorites.presentation.adapter.headerDelegate
import com.example.favorites.presentation.adapter.vacancyDelegate
import com.example.favorites.presentation.model.FavoritesScreenItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewModel by viewModels<FavoritesViewModel>()

    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!

    private lateinit var adapter: ListDelegationAdapter<List<FavoritesScreenItem>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListDelegationAdapter<List<FavoritesScreenItem>>(
            headerDelegate(),
            vacancyDelegate(
                unsetLike = { id ->
                    viewModel.unsetFavorite(id)
                },
                setLike = { id ->
                    viewModel.setFavorite(id)
                }
            )
        )

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when(it){
                        is FavoritesScreenState.Error -> {}
                        is FavoritesScreenState.Favorites -> {
                            adapter.items = it.data
                            adapter.notifyDataSetChanged()
                        }
                        FavoritesScreenState.Loading -> {}
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}