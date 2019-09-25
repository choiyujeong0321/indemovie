package com.example.indemovie.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.indemovie.MainActivity;
import com.example.indemovie.NMapViewer;
import com.example.indemovie.R;
import com.example.indemovie.ui.gallery.GalleryFragment;
import com.example.indemovie.ui.share.ShareFragment;
import com.example.indemovie.ui.slideshow.SlideshowFragment;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(" ");
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_home, container, false);


        Button button1 = view.findViewById(R.id.button1);
        Button button2 = view.findViewById(R.id.button2);
        Button button3 = view.findViewById(R.id.button3);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //첫번째 버튼 행동
                    case R.id.button1:
                        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new SlideshowFragment()).commit();
                        transaction.hide(HomeFragment.this);
                        break;

                    case R.id.button2:
                        Intent intent= new Intent(getActivity(), NMapViewer.class);
                        startActivity(intent);
                        break;
                    case R.id.button3:
                        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ShareFragment()).commit();
                        transaction.hide(HomeFragment.this);
                        break;

                }
            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);


        return view;

    }
}



