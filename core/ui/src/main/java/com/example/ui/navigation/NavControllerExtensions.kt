package com.example.ui.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.navOptions
import com.example.core.domain.model.MediaType
import com.example.core.ui.R

fun NavController.navigateToMovieDetail(
    mediaId: Int,
    mediaType: MediaType,
    playVideo: Boolean = false
) {
    val navOptions = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    val request = NavDeepLinkRequest.Builder
        .fromUri("netflix-clone://com.example.netflixclone/detail_fragment/$mediaId/${mediaType.value}/$playVideo".toUri())
        .build()
    navigate(request, navOptions)
}
