package com.example.ui.navigation

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.MediaType

fun NavController.navigateToMovieDetail(
    mediaId: Int,
    mediaType: MediaType,
    playVideo: Boolean = false
) {
    val request = NavDeepLinkRequest.Builder
        .fromUri("netflix-clone://com.example.netflixclone/detail_fragment/$mediaId/${mediaType.value}/$playVideo".toUri())
        .build()
    navigate(request)
}
