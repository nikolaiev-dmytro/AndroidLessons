package com.nikolaiev.lesson18mvp.mvp;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.*;
import android.view.View;
import android.widget.TextView;

import com.nikolaiev.lesson18mvp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by User on 30.03.2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {
    private List<String> mStrings=new ArrayList<>();

    public ListAdapter(List<String> strings) {
        mStrings = strings;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.mTextView.setText(mStrings.get(position));
    }


    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView mTextView;

        Holder(android.view.View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
