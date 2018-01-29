package com.pgmail.martsulg.tankmods;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pgmail.martsulg.tankmods.entity.HotRoot;
import com.pgmail.martsulg.tankmods.fragments.HotFragment;
import com.pgmail.martsulg.tankmods.network.UseCase.HotUseCase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.observers.DisposableObserver;

public class ParserService extends Service {

    public HotUseCase hotUseCase = new HotUseCase();
    public ParserService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Служба создана",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*HotRoot root;
        try {
            Gson gson = new Gson();
            InputStream input = getAssets().open("hot.json");
            int size = input.available();
            byte [] buffer = new byte[size];
            input.read(buffer);
            input.close();
            String jsonStr = new String(buffer, "UTF-8");
            root = gson.fromJson(jsonStr,HotRoot.class);
           // BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(getAssets().open("hot.json"))));
           // BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(R.raw.hot)));
           // root = gson.fromJson(reader,HotRoot.class);
            Log.e("AAAAA", "file found");
            Log.e("AAAAAAA", root.getUser().getName());
            Log.e("AAAAAAA", root.getFeed().get(4).getItems().get(0).getSubject().getUuid());
        } catch
         (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        hotUseCase.execute(null, new DisposableObserver<HotRoot>() {
            @Override
            public void onNext(HotRoot hotRoot) {
                Log.e("AAAAA", "request success!");
                //Log.e("AAAAAAA", hotRoot.getUser().getName());
                //Log.e("AAAAAAA", hotRoot.getFeed().get(4).getItems().get(0).getSubject().getUuid());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Intent bcIntent = new Intent(HotFragment.BROADCAST_ACTION);
                bcIntent.putExtra(HotFragment.PARSER_STATUS, HotFragment.STATUS_OK);
                sendBroadcast(bcIntent);
                stopSelf();
            }
        });
                return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Служба остановлена",
                Toast.LENGTH_SHORT).show();
            super.onDestroy();
            hotUseCase.dispose();
    }
}
