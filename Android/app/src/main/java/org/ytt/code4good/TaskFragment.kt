/*
 * Created by Yip Tsz To on 11/2/19 4:18 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.ytt.code4good.databinding.ListItemTaskBinding
import org.ytt.code4good.viewModels.TaskViewModel

class TaskFragment : Fragment() {
    private val taskList = listOf(
        TaskViewModel("Talk to one of your old friends", "5"),
        TaskViewModel("Play 2 mini-games", "5"),
        TaskViewModel("Learn about dolphins", "10"),
        TaskViewModel("Watch a funny video", "10")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_task, container, false)

        val viewManager = LinearLayoutManager(activity)
        val viewAdapter = TaskAdapter(taskList)

        view.findViewById<RecyclerView>(R.id.view_list).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        return view
    }

    private class TaskAdapter(val mDataList: List<TaskViewModel>) :
        RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemTaskBinding.inflate(layoutInflater, parent, false)

            return MyViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = mDataList[position]
            holder.bind(item)
        }

        override fun getItemCount() = mDataList.size

        private class MyViewHolder(private val binding: ListItemTaskBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: TaskViewModel) {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
    }
}