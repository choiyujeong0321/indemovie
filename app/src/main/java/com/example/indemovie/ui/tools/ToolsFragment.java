package com.example.indemovie.ui.tools;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.indemovie.MainActivity;
import com.example.indemovie.Movie;
import com.example.indemovie.R;
import com.example.indemovie.ui.send.SendFragment;
import com.example.indemovie.ui.slideshow.SlideshowFragment;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class ToolsFragment extends Fragment implements MainActivity.OnBackPressedListener{


    final SlideshowFragment slideshowFragment = new SlideshowFragment();
    int ps=slideshowFragment.getArg();
    ArrayList<Movie> movielist = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view=lf.inflate(R.layout.fragment_tools,container,false);
        ImageView imageView=(ImageView)view.findViewById(R.id.posterview);
        TextView textView=(TextView)view.findViewById(R.id.text_tools);
        TextView textView1=(TextView)view.findViewById(R.id.title);
        TextView textView2=(TextView)view.findViewById(R.id.story);
        Button button=(Button)view.findViewById(R.id.theaterbutton);

        ActionBar actionBar=((MainActivity)getActivity()).getSupportActionBar();
        actionBar.hide();
        actionBar.setTitle(" ");

        final MainActivity mainActivity = (MainActivity) this.getActivity();
        movielist= mainActivity.getmList();

        String url="";


        int i=0;
       for(i=0;i<movielist.size();i++) {
           if (i == ps) {
               textView1.setText(movielist.get(i).getMOVIVE_NM());
               textView.setText(movielist.get(i).toString());
               textView2.setText(movielist.get(i).getSUMMARY());
               url = movielist.get(i).getPOSTER();

               button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new SendFragment()).commit();
                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                       transaction.hide(ToolsFragment.this);

                   }
               });
           }
        }
        Glide.with(this).load(url).into(imageView);




        return view;
    }



    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        MainActivity activity = (MainActivity)getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        activity.setOnBackPressedListener(null);
        // MainFragment 로 교체

        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new SlideshowFragment()).commit();
        ActionBar actionBar=((MainActivity)getActivity()).getSupportActionBar();
        actionBar.show();
        actionBar.setTitle(" ");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(ToolsFragment.this);
    }


    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}
