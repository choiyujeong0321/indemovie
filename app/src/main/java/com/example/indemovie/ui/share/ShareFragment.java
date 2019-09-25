package com.example.indemovie.ui.share;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.indemovie.MainActivity;
import com.example.indemovie.R;
import com.example.indemovie.Post;
import com.example.indemovie.ui.home.HomeFragment;
import com.example.indemovie.ui.slideshow.SlideshowFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShareFragment extends Fragment implements MainActivity.OnBackPressedListener {

    static boolean calledAlready = false;

    private ArrayList<Post> reviewData;   // 후기 데이터
    private ListView reviewList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(" ");


        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_share, container, false);


        reviewData = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("review");

        reviewList = view.findViewById(R.id.reviewlistview);
        // Read from the database
        databaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("TEST", "onDataChange");


                // 클래스 모델이 필요?
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    Post post = new Post();
                    post.setTitle(fileSnapshot.child("title").getValue(String.class));
                    post.setBody(fileSnapshot.child("body").getValue(String.class));
                    post.setStar(Float.parseFloat(fileSnapshot.child("star").getValue(String.class)));


                    reviewData.add(post);

                }

                Log.i("TEST1", reviewData.toString());
                ListViewAdapter adapter = new ListViewAdapter(reviewData);
                reviewList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });


        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ShareFragment2()).commit();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(ShareFragment.this);

            }


        });

        return view;

    }

    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        MainActivity activity = (MainActivity) getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        activity.setOnBackPressedListener(null);
        // MainFragment 로 교체

        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(ShareFragment.this);
    }


    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((MainActivity) context).setOnBackPressedListener(this);
    }

}