/*
 * Created by Yip Tsz To on 11/2/19 8:21 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.ytt.code4good.databinding.ListItemWithImageSingleBinding
import org.ytt.code4good.viewModels.ConvoViewModel

class ChatDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)

        val name = intent.getStringExtra(ChatFragment.EXTRA_CHAT_NAME)
        val icon = intent.getIntExtra(ChatFragment.EXTRA_CHAT_IMAGE, R.drawable.head2)
        val convo = getMockedConvo(application, name, icon)

        for (i in convo.indices) {
            if (convo[i].name == "") {
                convo[i].name = name
            }
        }

        setSupportActionBar(findViewById(R.id.view_toolbar))

        supportActionBar?.setIcon(icon)
        supportActionBar?.title = name
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)


        val viewManager = LinearLayoutManager(this)
        val viewAdapter = ChatAdapter(convo)

        findViewById<RecyclerView>(R.id.view_list).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

            addItemDecoration(
                DividerItemDecoration(
                    this@ChatDetailActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private class ChatAdapter(val mDataList: List<ConvoViewModel>) :
        RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemWithImageSingleBinding.inflate(layoutInflater, parent, false)

            return MyViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = mDataList[position]
            holder.bind(item)
        }

        override fun getItemCount() = mDataList.size

        private class MyViewHolder(private val binding: ListItemWithImageSingleBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: ConvoViewModel) {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
    }
}

fun getMockedConvo(
    app: Application,
    name: String,
    @DrawableRes imageSrc: Int
): List<ConvoViewModel> {
    return listOf(
        ConvoViewModel(app, name, imageSrc, "Hey, how was your day?", "4:53pm"),

        ConvoViewModel(
            app,
            "Lisa",
            R.drawable.head1,
            "Just finished my treatment today, not so great",
            "4:55pm"
        ),
        ConvoViewModel(
            app,
            "Lisa",
            R.drawable.head1,
            "Spent hours doing nothing already...",
            "4:56pm"
        ),

        ConvoViewModel(
            app,
            name,
            imageSrc,
            "Don't think about it, you'll get better very soon!",
            "4:57pm"
        ),
        ConvoViewModel(
            app,
            name,
            imageSrc,
            "I went through that last week, it wasn't great.",
            "4:58pm"
        ),
        ConvoViewModel(
            app,
            name,
            imageSrc,
            "But Peter from the ward next door invited me to try this new game, want to check it out?.",
            "4:58pm"
        ),

        ConvoViewModel(
            app,
            "Lisa",
            R.drawable.head1,
            "Not sure if I'm awake enough for anything intensive",
            "5:00pm"
        ),

        ConvoViewModel(
            app,
            name,
            imageSrc,
            "It's the new category game, very simple and fun!",
            "5:01pm"
        ),

        ConvoViewModel(app, "Lisa", R.drawable.head1, "Sure then!", "5:02pm")
    )
}