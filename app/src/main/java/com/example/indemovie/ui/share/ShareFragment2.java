package com.example.indemovie.ui.share;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.indemovie.MainActivity;
import com.example.indemovie.R;
import com.example.indemovie.ui.home.HomeFragment;
import com.example.indemovie.ui.send.SendFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ShareFragment2 extends Fragment implements MainActivity.OnBackPressedListener{

    DatabaseReference mDatabase;
    float starNum;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar=((MainActivity)getActivity()).getSupportActionBar();

        actionBar.setTitle(" ");

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view=lf.inflate(R.layout.review_write,container,false);



        final EditText reviewTitle= view.findViewById(R.id.reviewTitle) ;
        final  EditText reviewBody= view.findViewById(R.id.reviewBody) ;
        final  EditText reviewPw= view.findViewById(R.id.editpw) ;
        final RatingBar ratingBar  =view.findViewById(R.id.star_ratingbar);



        Button button=view.findViewById(R.id.completing_review_button);
        //리뷰작성버튼
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                starNum =ratingBar.getRating();
                HashMap <String, String> result = new HashMap<>();
                result.put("title", reviewTitle.getText().toString());
                result.put("body", reviewBody.getText().toString());
                result.put("star", String.valueOf(starNum));
                result.put("pw",reviewPw.getText().toString());

Log.i("TEST",String.valueOf(starNum));
                mDatabase = FirebaseDatabase.getInstance().getReference();
                //firebase에 저장
                mDatabase.child("review").push().setValue(result);
                Toast.makeText(getContext(),"글이 작성되었습니다.",Toast.LENGTH_LONG).show();






                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();


                downKeyboard(getContext(),reviewTitle);
                downKeyboard(getContext(),reviewBody);
                downKeyboard(getContext(),reviewPw);

                transaction.hide(ShareFragment2.this);



            }
        });

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


        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(ShareFragment2.this);
    }


    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((MainActivity)context).setOnBackPressedListener(this);
    }
    public static void downKeyboard(Context context, EditText editText) {
        InputMethodManager mInputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


}