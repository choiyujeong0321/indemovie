package com.example.indemovie.ui.slideshow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.indemovie.MainActivity;
import com.example.indemovie.Movie;
import com.example.indemovie.R;
import com.example.indemovie.ui.home.HomeFragment;
import com.example.indemovie.ui.tools.ToolsFragment;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment implements MainActivity.OnBackPressedListener{
    private ArrayList<String> mList;
    private ListView mListView;;
    private ArrayAdapter mAdapter;


    public static int ps = 0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final MainActivity mainActivity = (MainActivity) this.getActivity();
        mList=  mainActivity.getMovieName();
        ActionBar actionBar=((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle(" ");

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view=lf.inflate(R.layout.fragment_slideshow,container,false);




        mListView= (ListView) view.findViewById(R.id.list_view);
        mAdapter =  new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        ((ViewGroup)mListView.getParent()).removeView(mListView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                ps=position;
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                ToolsFragment toolsFragment = new ToolsFragment();
                toolsFragment.setArguments(bundle);

                Log.i("TEST",position+"d");


                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ToolsFragment()).commit();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(SlideshowFragment.this);


            }
        });

        Log.i("TEST","oncreateView");
        return mListView;

    }


    public int getArg()
    {
        return ps;
    }
    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        MainActivity activity = (MainActivity)getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        activity.setOnBackPressedListener(null);
        // MainFragment 로 교체

        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(SlideshowFragment.this);
    }


    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((MainActivity)context).setOnBackPressedListener(this);
    }

}
