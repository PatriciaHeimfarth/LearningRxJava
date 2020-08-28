package com.pheimfarth.learningrxjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleFunctionWithObserverAndLogs();

    }

    private void simpleFunctionWithObserverAndLogs(){
        Observable<String> membersObservable =
                Observable.just("Steven", "Jane", "Phillip");
                //.map( x -> x.concat(" Schmidt")); //Apply function on every item
        /*.map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s.concat(" Schulze");
            }
        });*/


        Observer<String> membersObserver = getMembersObserver();

        membersObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(membersObserver);
    }

    private Observer<String> getMembersObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Meow", "Start!");
            }

            @Override
            public void onNext(String s) {
                Log.d("Meow", "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Meow", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("Meow", "Completed!");
            }
        };
    }
}