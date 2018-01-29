package com.pgmail.martsulg.tankmods.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.pgmail.martsulg.tankmods.R;
import com.pgmail.martsulg.tankmods.entity.HotRoot;


/**
 * Created by g_washingt0n on 22.01.2018.
 */

public class HotFragment extends Fragment {

    private static final int PAGE_SIZE = 10;

    public static HotFragment newInstance(android.support.v4.app.FragmentManager fragmentManager, HotRoot hotRoot){
        Fragment fragment = fragmentManager
                .findFragmentByTag(HotFragment.class.getName());
        HotFragment hotFragment;

        if (fragment != null && fragment instanceof HotFragment) {
            hotFragment = (HotFragment) fragment;
        } else {
            hotFragment = new HotFragment();
            Bundle bundle = new Bundle();
            bundle.putString("hotRoot",new Gson().toJson(hotRoot, HotRoot.class));
            //bundle.putParcelable("hotRoot",hotRoot);
            hotFragment.setArguments(bundle);
        }
        return hotFragment;
    }

    public final static String BROADCAST_ACTION = "myBroadcastAction";
    public final static int STATUS_OK = 1;
    public final static String PARSER_STATUS = "parsed";
    private RecyclerView recyclerView;
    protected LinearLayoutManager layoutManager;
    private HotRoot hotRoot;
    private HotAdapter hotAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        hotRoot = new Gson().fromJson(bundle.getString("hotRoot"),HotRoot.class);
        //hotRoot = bundle.getParcelableArrayList("hotRoot");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container,false);
        recyclerView = view.findViewById(R.id.hotFragment_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        hotAdapter = new HotAdapter(getActivity(), hotRoot.getFeed());
        recyclerView.setAdapter(hotAdapter);

        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public static void showFragment(android.support.v4.app.FragmentManager fragmentManager, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_nav_screen_fragment,fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();


                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {

                    //запрос к серверу для загрузки последующих страниц

                }
        }
    };
}
