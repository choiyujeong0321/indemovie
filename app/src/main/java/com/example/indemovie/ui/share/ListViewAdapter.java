package com.example.indemovie.ui.share;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.indemovie.Post;
import com.example.indemovie.R;
import com.example.indemovie.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * 리뷰 리스트뷰 어댑터 클래스
 */
public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Post> reviewData;
    private int nListCnt=0;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    private String password;
    // 생성자
    public ListViewAdapter(ArrayList<Post> post){

      reviewData=post;
      nListCnt=post.size();
    }
    public ListViewAdapter(){


    }
    public ArrayList<Post> getReviewList(){
        return reviewData;
    }



    @Override
    public int getCount(){return reviewData.size();}
    @Override
    public String getItem(int position){return reviewData.get(position).getTitle();}
    @Override
    public long getItemId(int position){return position;}
    @Override
    public View getView(int position, View convertView, final ViewGroup parent){

        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.review_list_item, parent, false);
        }
      TextView title=convertView.findViewById(R.id.reviewTitle);
      RatingBar star=convertView.findViewById(R.id.review_star);
      TextView body=convertView.findViewById(R.id.reviewBody);

      final EditText pw=convertView.findViewById(R.id.pw);




      Button button = convertView.findViewById(R.id.button4);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              password=pw.getText().toString();
              Log.i("TEST22",password);
              final ListViewAdapter adapter=new ListViewAdapter();
              DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
              Query applesQuery = ref.child("review").orderByChild("pw").equalTo(password);

              applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot dataSnapshot) {
                      for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                          appleSnapshot.getRef().removeValue();
                          reviewData.clear();
                      }

                  }

                  @Override
                  public void onCancelled(DatabaseError databaseError) {
                      Log.e(TAG, "onCancelled", databaseError.toException());
                  }
              });
              //Toast.makeText(ListViewAdapter.this,"비밀번호를 확인해주세요",Toast.LENGTH_LONG).show();

              adapter.notifyDataSetChanged();
          }
      });


        title.setText(reviewData.get(position).getTitle());

        star.setRating(reviewData.get(position).getStar());

        body.setText(reviewData.get(position).getBody());

        return convertView;
    }





}
