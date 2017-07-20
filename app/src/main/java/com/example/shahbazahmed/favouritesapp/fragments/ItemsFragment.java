package com.example.shahbazahmed.favouritesapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shahbazahmed.favouritesapp.R;
import com.example.shahbazahmed.favouritesapp.adapters.ItemsRecyclerAdapter;
import com.example.shahbazahmed.favouritesapp.entities.Item;
import com.example.shahbazahmed.favouritesapp.retrofit.ApiService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemsFragment extends Fragment {
    public static final String BASE_URL = "http://54.254.198.83:8080/";
    private static final String TAG = "ItemsFragment";

    private ItemsRecyclerAdapter mAdapter;
    private ApiService mApiService;
    private CompositeDisposable mDisposables;

    public ItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = view.findViewById(R.id.list_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                this.getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.hasFixedSize();
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ItemsRecyclerAdapter(this.getContext());
        mRecyclerView.setAdapter(mAdapter);

        mDisposables = new CompositeDisposable();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Observable<List<Item>> issues = mApiService.getItems();
        mDisposables.add(issues.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ItemsFragment.this::handleItemResposes, ItemsFragment.this::handleError));
    }

    private void handleItemResposes(List<Item> items) {
        mAdapter.setItems(items);
    }

    private void handleError(Throwable e) {
        Log.e(TAG, "Error occured: " + e.toString());
        Toast.makeText(getContext(), "Oops! Some error occured.", Toast.LENGTH_SHORT).show();
    }
}
