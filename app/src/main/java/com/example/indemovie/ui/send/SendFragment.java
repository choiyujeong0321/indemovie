package com.example.indemovie.ui.send;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.indemovie.MainActivity;
import com.example.indemovie.R;
import com.example.indemovie.ui.home.HomeFragment;
import com.example.indemovie.ui.share.ShareFragment;
import com.example.indemovie.ui.slideshow.SlideshowFragment;

public class SendFragment extends Fragment implements MainActivity.OnBackPressedListener{

    final SlideshowFragment slideshowFragment = new SlideshowFragment();
    int ps=slideshowFragment.getArg();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view=lf.inflate(R.layout.fragment_send,container,false);
        WebView webView = view.findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://indiefilmseoul.org/");

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
        transaction.hide(SendFragment.this);
    }


    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((MainActivity) context).setOnBackPressedListener(this);
    }
}
