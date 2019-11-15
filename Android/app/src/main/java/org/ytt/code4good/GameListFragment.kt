/*
 * Created by Yip Tsz To on 11/2/19 4:18 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.ytt.code4good.databinding.ListItemWithImage2Binding
import org.ytt.code4good.viewModels.GameViewModel

class GameListFragment(
    private val position: Int,
    private val mDataList: List<GameViewModel>,
    private val categories: List<String>
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(
            R.layout.fragment_game, container, false
        )

        val viewManager = LinearLayoutManager(activity)
        val viewAdapter =
            if (position == 0) PopularAdapter(mDataList) else CategoryAdapter(categories)

        view.findViewById<RecyclerView>(R.id.view_list).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        return view
    }


    private class PopularAdapter(val mDataList: List<GameViewModel>) :
        RecyclerView.Adapter<PopularAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemWithImage2Binding.inflate(layoutInflater, parent, false)

            return MyViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = mDataList[position]
            holder.bind(item)
        }

        override fun getItemCount() = mDataList.size

        private class MyViewHolder(private val binding: ListItemWithImage2Binding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: GameViewModel) {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
    }

    private class CategoryAdapter(val categories: List<String>) :
        RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

        private class MyViewHolder(val constraintLayout: ConstraintLayout) :
            RecyclerView.ViewHolder(constraintLayout)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_single,
                    parent,
                    false
                ) as ConstraintLayout
            )
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.constraintLayout.findViewById<TextView>(R.id.view_text).text =
                categories[position]
        }

        override fun getItemCount() = categories.size
    }
}