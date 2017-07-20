package com.example.shahbazahmed.favouritesapp;

import android.support.annotation.NonNull;

import com.example.shahbazahmed.favouritesapp.entities.Item;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by shahbazahmed on 20/07/17.
 */

public final class ItemsBus {
    private static PublishSubject<List<Item>> sSubject = PublishSubject.create();

    private ItemsBus() {
    }


    public static Disposable subscribe(@NonNull Consumer<List<Item>> action) {
        return sSubject.subscribe(action);
    }

    public static void publish(@NonNull List<Item> message) {
        sSubject.onNext(message);
    }
}
