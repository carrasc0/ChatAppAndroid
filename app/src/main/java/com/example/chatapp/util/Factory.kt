package com.example.chatapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.viewmodel.ChatViewModel

@Suppress("UNCHECKED_CAST")
class ChatViewModelFactory(private val nickname: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(nickname) as T
    }
}

class ScrollToTopDataObserver(val layoutManager: LinearLayoutManager,
                              val recyclerView: RecyclerView)
    : RecyclerView.AdapterDataObserver() {
    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)
        val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

        // If the recycler view is initially being loaded or the user is at the bottom of the
        // list, scroll to the bottom of the list to show the newly added message.
        if (lastVisiblePosition == -1 || positionStart >= itemCount - 1 && lastVisiblePosition == positionStart - 1) {
            recyclerView.scrollToPosition(positionStart)
        }
    }
}
