package com.pgmail.martsulg.tankmods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.pgmail.martsulg.tankmods.entity.HotRoot;
import com.pgmail.martsulg.tankmods.fragments.HotFragment;
import com.pgmail.martsulg.tankmods.network.UseCase.HotUseCase;

import io.reactivex.observers.DisposableObserver;

public class MainNavigationScreen extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private HotUseCase hotUseCase = new HotUseCase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation_screen);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        if(savedInstanceState==null){
            hotStart();
        }

    }


    @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_hot:
                    hotStart();
                    return true;
                case R.id.navigation_maps:
                    return true;
                case R.id.navigation_addons:
                    return true;
                case R.id.navigation_skins:
                    return true;
                case R.id.navigation_textures:
                    return true;
            }
            return false;
        }


        private void hotStart(){
            hotUseCase.execute(null, new DisposableObserver<HotRoot>() {
                @Override
                public void onNext(HotRoot hotRoot) {
                    Log.e("AAAAA", "file found");
                    Log.e("AAAAAAA", hotRoot.getUser().getName());
                    Log.e("AAAAAAA", hotRoot.getFeed().get(4).getItems().get(0).getSubject().getUuid());
                    showFragment(getSupportFragmentManager(), new HotFragment().newInstance(getSupportFragmentManager(),hotRoot));

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_nav_screen_fragment,fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
