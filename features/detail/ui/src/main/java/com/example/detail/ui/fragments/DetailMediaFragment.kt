package com.example.detail.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.detail.ui.screen.DetailMediaScreen
import com.example.ui.theme.NetflixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMediaFragment : Fragment() {

    private val movieId: Int by lazy {
        requireNotNull(arguments?.getInt("movieId"))
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
                    onBackPress = { findNavController().popBackStack() }
                )
            }
        }
    }
}
