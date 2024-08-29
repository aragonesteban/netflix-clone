package com.example.detail.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.core.domain.model.MediaType
import com.example.detail.ui.screen.DetailMediaScreen
import com.example.ui.navigation.navigateToMovieDetail
import com.example.ui.theme.NetflixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMediaFragment : Fragment() {

    private val movieId: Int by lazy {
        requireNotNull(arguments?.getInt("mediaId"))
    }

    private val mediaType: String by lazy {
        requireNotNull(arguments?.getString("mediaType"))
    }

    private val playVideo: Boolean by lazy {
        requireNotNull(arguments?.getBoolean("playVideo"))
    }

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
                DetailMediaScreen(
                    mediaId = movieId,
                    playVideo = playVideo,
                    mediaType = MediaType.valueOf(mediaType.uppercase()),
                    onBackPress = { findNavController().popBackStack() },
                    onMediaClick = { mediaId, mediaType -> findNavController().navigateToMovieDetail(mediaId, mediaType) }
                )
            }
        }
    }
}
