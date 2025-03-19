package com.example.challenge.presentation.screen.connection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge.databinding.ItemConnectionLayoutBinding
import com.example.challenge.presentation.model.Connection
import com.example.challenge.presentation.util.loadImage

class ConnectionsAdapter :
    ListAdapter<Connection, ConnectionsAdapter.ConnectionsViewHolder>(OBJECT_COMPARATOR) {

    inner class ConnectionsViewHolder(private val binding: ItemConnectionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(connection: Connection) = with(binding) {
            imvProfile.loadImage(connection.avatar)
            tvFullName.text = connection.fullName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = ItemConnectionLayoutBinding.inflate(layoutInflater, parent, false)

        return ConnectionsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ConnectionsViewHolder, position: Int) {
        val connection = getItem(position)
        holder.bind(connection)
    }
}

private val OBJECT_COMPARATOR = object : DiffUtil.ItemCallback<Connection>() {
    override fun areItemsTheSame(oldItem: Connection, newItem: Connection): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Connection, newItem: Connection): Boolean {
        return oldItem == newItem
    }
}