/*
 * Created by Yip Tsz To on 11/2/19 4:18 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.ytt.code4good.databinding.ListItemWithImageBinding
import org.ytt.code4good.viewModels.ChatViewModel

class ChatFragment(application: Application) : Fragment() {

    private val chats = listOf(
        ChatViewModel(
            application,
            R.drawable.head2,
            "Amy",
            40,
            "You two just played the category game!",
            "1:23 pm"
        ),
        ChatViewModel(application, R.drawable.head2, "Brian", 0, "How's it going!?", "4:56 pm"),
        ChatViewModel(
            application,
            R.drawable.head3,
            "Charlie",
            0,
            "You two just played the Ninja Fight game!",
            "7:00 pm"
        ),
        ChatViewModel(application, R.drawable.head4, "David", 0, "Eh I'm alright, wbu?", "8:45 pm"),
        ChatViewModel(
            application,
            R.drawable.head5,
            "Emily",
            100,
            "You two just played the Twenty Nine game!",
            "4:45 pm"
        ),
        ChatViewModel(
            application,
            R.drawable.head6,
            "Fred",
            0,
            "You two just played the category game!",
            "4:45 pm"
        ),
        ChatViewModel(
            application,
            R.drawable.head7,
            "George",
            0,
            "You two just played the category game!",
            "4:45 pm"
        ),
        ChatViewModel(
            application,
            R.drawable.head4,
            "Hannah",
            0,
            "You two just played the category game!",
            "4:45 pm"
        ),
        ChatViewModel(
            application,
            R.drawable.head2,
            "Izzy",
            0,
            "You two just played the category game!",
            "4:45 pm"
        ),
        ChatViewModel(
            application,
            R.drawable.head3,
            "Jamie",
            4,
            "You two just played the category game!",
            "4:45 pm"
        ),
        ChatViewModel(
            application,
            R.drawable.head4,
            "Kelvin",
            0,
            "You two just played the category game!",
            "4:45 pm"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_chat, container, false)

        val viewManager = LinearLayoutManager(activity)
        val viewAdapter = ChatAdapter(chats)

        view.findViewById<RecyclerView>(R.id.view_list).also {
            it.setHasFixedSize(true)

            it.layoutManager = viewManager

            it.adapter = viewAdapter
        }

        return view
    }

    private class ChatAdapter(val data: List<ChatViewModel>) :
        RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemWithImageBinding.inflate(layoutInflater, parent, false)

            return MyViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = data[position]
            holder.bind(item)
            holder.itemView.setOnClickListener {
                it.context.startActivity(
                    Intent(it.context, ChatDetailActivity::class.java)
//                        .putExtra(EXTRA_CHAT_POS, position)
                        .putExtra(EXTRA_CHAT_IMAGE, item.imageSrc)
                        .putExtra(EXTRA_CHAT_NAME, item.name)
                )
            }
        }

        override fun getItemCount() = data.size

        private class MyViewHolder(private val binding: ListItemWithImageBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: ChatViewModel) {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
    }

    companion object {
//        const val EXTRA_CHAT_POS = "EXTRA_CHAT_POS"
        const val EXTRA_CHAT_IMAGE = "EXTRA_CHAT_IMAGE"
        const val EXTRA_CHAT_NAME = "EXTRA_CHAT_NAME"
    }
}