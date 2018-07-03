package com.yoon.brokenglasses.Alarm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoon.brokenglasses.Custom.BaseRecyclerViewAdapter;
import com.yoon.brokenglasses.Model.network.ListTeamJoinResponse;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.databinding.ItemTeamJoinBinding;

/**
 * Created by Yoon on 2018-02-02.
 */

public class TeamJoinRecyclerViewAdapter extends BaseRecyclerViewAdapter<ListTeamJoinResponse,TeamJoinRecyclerViewAdapter.ViewHolder> {

    private AlarmPresenter presenter;

    public TeamJoinRecyclerViewAdapter(Context context, AlarmPresenter presenter) {
        super(context);
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_join ,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindView(ViewHolder holder, int position) {
        ListTeamJoinResponse listTeamJoinResponse = getItem(position);
        holder.binding.setListJoin(listTeamJoinResponse);
        holder.binding.setPresenter(presenter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemTeamJoinBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
