package com.example.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.MediaType
import com.example.home.ui.screen.HomeScreen
import com.example.home.ui.viewmodel.HomeViewModel
import com.example.ui.navigation.navigateToMovieDetail
import com.example.ui.theme.NetflixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(
        requireContext()
    ).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            NetflixTheme {
                val viewModel = hiltViewModel<HomeViewModel>()

                HomeScreen(
                    viewModel = viewModel,
                    onMediaClick = { mediaId, mediaType, playVideo ->
                        findNavController().navigateToMovieDetail(mediaId, mediaType, playVideo)
                    },
                    onGetMediaContent = { mediaType ->
                        when (mediaType) {
                            MediaType.MOVIES -> viewModel.getMovies()
                            MediaType.SERIES -> viewModel.getSeries()
                        }
                    }
                )

            }
        }
    }

}
