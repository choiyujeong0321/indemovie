package com.example.indemovie;

import android.os.Bundle;


import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.util.ArrayList;

import static com.example.indemovie.R.*;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    ArrayList<Movie> movielist = new ArrayList<>();
    ArrayList<String> moviename = new ArrayList<>();

    static boolean calledAlready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);


        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true); // 다른 인스턴스보다 먼저 실행되어야 한다.
            calledAlready = true;
        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("movieConveInfo");

        // Read from the database
        databaseRef.child("MOVIE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // 클래스 모델이 필요?
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    Movie movie = new Movie();
                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                    //하위키들의 value를 어떻게 가져오느냐???
                    movie.setMOVIVE_NM(fileSnapshot.child("MOVIE_NM").getValue(String.class));
                    movie.setACTOR(fileSnapshot.child("ACTOR").getValue(String.class));
                    movie.setAGING(fileSnapshot.child("AGING").getValue(String.class));
                    movie.setDIRECTOR(fileSnapshot.child("DIRECTOR").getValue(String.class));
                    movie.setRUNTIME(fileSnapshot.child("RUNTIME").getValue(String.class));
                    movie.setSUMMARY(fileSnapshot.child("SUMMARY").getValue(String.class));
                    movie.setPOSTER(fileSnapshot.child("POSTER").getValue(String.class));
                    movielist.add(movie); //1개 영화 저장 완료
                    moviename.add(movie.getMOVIVE_NM());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });


        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(id.drawer_layout);
        NavigationView navigationView = findViewById(id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                id.nav_home, id.nav_gallery, id.nav_slideshow,
                id.nav_tools, id.nav_share, id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private long pressedTime = 0;

    // 리스너 생성
    public interface OnBackPressedListener {
        public void onBack();
    }

    // 리스너 객체 생성
    private OnBackPressedListener mBackListener;

    // 리스너 설정 메소드
    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }

    @Override
    public void onBackPressed() {

        // 다른 Fragment 에서 리스너를 설정했을 때 처리됩니다.
        if (mBackListener != null) {
            mBackListener.onBack();
            Log.e("!!!", "Listener is not null");
            // 리스너가 설정되지 않은 상태(예를들어 메인Fragment)라면
            // 뒤로가기 버튼을 연속적으로 두번 눌렀을 때 앱이 종료됩니다.
        } else {
            Log.e("!!!", "Listener is null");
            if (pressedTime == 0) {

                pressedTime = System.currentTimeMillis();
            } else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);

                if (seconds > 2000) {

                    pressedTime = 0;
                } else {
                    super.onBackPressed();
                    Log.e("!!!", "onBackPressed : finish, killProcess");
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        }
    }

    public ArrayList<String> getMovieName() {
        return moviename;
    }

    public ArrayList<Movie> getmList() {
        return movielist;
    }

}