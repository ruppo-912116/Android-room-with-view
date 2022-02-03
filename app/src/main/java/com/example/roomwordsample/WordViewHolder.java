package com.example.roomwordsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordViewHolder extends RecyclerView.ViewHolder {

    private final TextView wordItemView;

    public WordViewHolder(@NonNull View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text){
        wordItemView.setText(text);
    }

    /**
     * the layout inflator class is used to instantiate the contents of layout XML files
     * into their corresponding view objects
     * in other words, it takes an XML file as input and builds the view objects from it.
     * Layout inflater is used to create a new view or layout object from one of your xml layouts.
     * the most common time people use it is in a RecyclerView
     * you have to inflate a new layout for every single visible item in the list or grid
     * @param parent
     * @return
     */
    static WordViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(view);
    }
}
