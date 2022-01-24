package com.example.moviesdb

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerScrollListener(val onRequestMore: () -> Unit) : RecyclerView.OnScrollListener() {
    var requesting = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val target = recyclerView.layoutManager as LinearLayoutManager
        val totalItemCount = target.itemCount
        val lastVisible = target.findLastVisibleItemPosition()
        val lastItem = lastVisible + 3 >= totalItemCount
        if (totalItemCount > 0 && lastItem && !requesting) {
            onRequestMore()
            setRequestingNextPage(true)
        }
    }

    fun setRequestingNextPage(requesting: Boolean){
        this.requesting = requesting
    }
}