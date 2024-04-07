package com.zionhuang.music.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zionhuang.innertube.YouTube
import com.zionhuang.innertube.models.AlbumItem
import com.zionhuang.innertube.models.PlaylistItem
import com.zionhuang.music.utils.reportException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor() : ViewModel() {
    val playlists = MutableStateFlow<List<PlaylistItem>?>(null)
    val albums = MutableStateFlow<List<AlbumItem>?>(null)

    init {
        viewModelScope.launch {
            YouTube.likedPlaylists().onSuccess {
                playlists.value = it
            }.onFailure {
                reportException(it)
            }
            YouTube.likedAlbums().onSuccess {
                albums.value = it
            }.onFailure {
                reportException(it)
            }
        }
    }
}
