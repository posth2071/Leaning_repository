package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.example.Activity.ProgressActivity;
import com.example.example.Activity.SeekBarActivity;
import com.example.example.Adapter.MyAdapter;
import com.example.example.item.Activity_item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView activity_List;
    private ArrayList<Activity_item> activitys;
    private MyAdapter myAdapter;
    private final String TAG = "테스트";

    private Button main_title;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        // ListView Adapter 연결 List 설정
        init_ArrayList();

        activity_List = findViewById(R.id.Activity_List);
        myAdapter = new MyAdapter(this, activitys);
        activity_List.setAdapter(myAdapter);
    }

    // List View - Activity 추가
    private void init_ArrayList(){
        activitys = new ArrayList<>();

        activitys.add(new Activity_item("SeekBarActivity Example", SeekBarActivity.class));
        activitys.add(new Activity_item("ProgressActivity Example", ProgressActivity.class));
    }
}
