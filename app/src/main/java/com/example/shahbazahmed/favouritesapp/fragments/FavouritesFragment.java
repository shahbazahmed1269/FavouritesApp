package com.example.shahbazahmed.favouritesapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shahbazahmed.favouritesapp.ItemsBus;
import com.example.shahbazahmed.favouritesapp.R;
import com.example.shahbazahmed.favouritesapp.adapters.FavouritesRecyclerAdapter;
import com.example.shahbazahmed.favouritesapp.entities.Item;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class FavouritesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FavouritesRecyclerAdapter mAdapter;
    private Disposable disposable;

    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = view.findViewById(R.id.favourites_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                this.getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.hasFixedSize();
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FavouritesRecyclerAdapter(this.getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        disposable = ItemsBus.subscribe(items -> {
            List<Item> favItems = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                if (item.isFavourite()) {
                    favItems.add(item);
                }
            }
            mAdapter.setItems(favItems);
        });
        super.onResume();
    }

    @Override
    public void onPause() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onPause();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
