package com.sixkalmas.kalimasofIslam.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sixkalmas.kalimasofIslam.MoreAppsModel;
import com.sixkalmas.kalimasofIslam.R;
import com.sixkalmas.kalimasofIslam.adapter.MoreAppsAdapter;

import java.util.ArrayList;

public class SufiMoreAppsActivity extends AppCompatActivity {

    ArrayList<MoreAppsModel> moreAppsModelArrayList;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private SwipeRefreshLayout swipeContainer;
    private MoreAppsAdapter moreappadapter;
    private RecyclerView ry_category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_apps);
        ////////////////////////////////////////////

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("sixkalmasIslam");

        ry_category = (RecyclerView) findViewById(R.id.recycler_video);

        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                swipeContainer.setRefreshing(false);

                GetCategory();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        GetCategory();



    }


    public void GetCategory()
    {

        //  Constants.moreAppsModelArrayList.clear();


        moreAppsModelArrayList =   new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                swipeContainer.setRefreshing(false);

                for (DataSnapshot uniqueKeySnapshot : snapshot.getChildren())
                {
                    MoreAppsModel videoModel = uniqueKeySnapshot.getValue(MoreAppsModel.class);
                    moreAppsModelArrayList.add(videoModel);
                }


                moreappadapter = new MoreAppsAdapter(SufiMoreAppsActivity.this, moreAppsModelArrayList);
                ry_category.setLayoutManager(new GridLayoutManager(SufiMoreAppsActivity.this, 1));
                ry_category.setItemAnimator(new DefaultItemAnimator());
                ry_category.setAdapter(moreappadapter);
                moreappadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }



}
