package com.example.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.home.ui.screen.HomeScreen
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
                HomeScreen { mediaId ->
                    goToMovieDetail(mediaId)
                }
            }
        }
    }

    private fun goToMovieDetail(mediaId: Int) {
        val request = NavDeepLinkRequest.Builder
            .fromUri("netflix-clone://com.example.netflixclone/detail_fragment/$mediaId".toUri())
            .build()
        findNavController().navigate(request)
    }
}
