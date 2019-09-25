package com.example.indemovie.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.indemovie.ui.home.HomeFragment;
public class GalleryFragment extends Fragment implements MainActivity.OnBackPressedListener{




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar=((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("우리동네 상영관");
        View view = inflater.inflate(R.layout.fragment_gallery, container,false);
        Intent intent = new Intent(getActivity(), NMapViewer.class);
        startActivity(intent);
        View temp = inflater.inflate(R.layout.fragment_home, container,false);
        //임시로 바꿔놨음 이제보니까 같은 창이 두개가 띄워지는 문제점이 발생하네 (아무기능 안하는 게 문제 ㅋㅋ) ...?

        return null;

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
        transaction.hide(GalleryFragment.this);
    }


    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}




