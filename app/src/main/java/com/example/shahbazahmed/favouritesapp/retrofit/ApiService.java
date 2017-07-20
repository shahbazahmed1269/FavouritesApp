package com.example.shahbazahmed.favouritesapp.retrofit;

import com.example.shahbazahmed.favouritesapp.entities.Item;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shahbazahmed on 19/07/17.
 */

public interface ApiService {
    @GET("/favourite.json")
    Observable<List<Item>> getItems();
}
