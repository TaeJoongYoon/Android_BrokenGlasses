package com.yoon.brokenglasses.Search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoon.brokenglasses.Custom.BaseRecyclerViewAdapter;
import com.yoon.brokenglasses.Model.network.TeamSearchResponse;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.TeamDetail.TeamDetailActivity;
import com.yoon.brokenglasses.TeamDetail.TeamDetailContract;
import com.yoon.brokenglasses.databinding.ItemTeamSearchBinding;

/**
 * Created by Yoon on 2018-02-02.
 */

public class SearchRecyclerViewAdapter extends BaseRecyclerViewAdapter<TeamSearchResponse, SearchRecyclerViewAdapter.ViewHolder> implements BaseRecyclerViewAdapter.OnItemClickListener {
    private SearchContract.View view;

    public SearchRecyclerViewAdapter(Context context, SearchContract.View view) {
        super(context);
        this.view = view;
        setOnItemClickListener(this);
    }

    @Override
    public void onBindView(ViewHolder holder, int position) {
        TeamSearchResponse teamSearchResponse = getItem(position);
        holder.binding.setTeamSearch(teamSearchResponse);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_search ,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onItemClick(View view, int position) {
        this.view.start_detail(getItem(position).getTeamUID());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemTeamSearchBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
