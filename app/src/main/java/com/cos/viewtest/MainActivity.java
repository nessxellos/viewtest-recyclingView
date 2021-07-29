package com.cos.viewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cos.viewtest.adapter.PersonAdapter;
import com.cos.viewtest.model.Person;
import com.cos.viewtest.provider.PersonProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

// 어댑터와 리사이클러뷰 연결하기
public class MainActivity extends AppCompatActivity {

    private MainActivity mContext = this;
    private RecyclerView rvPersons;
    private RecyclerView.LayoutManager layoutManager;
    private PersonAdapter personAdapter;
    private FloatingActionButton fabAdd;  //1. 전역변수 선언하기(private)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initAdapter();
        initData();
        initListener();
    }

    private void init(){

        rvPersons = findViewById(R.id.rvPersons);
        fabAdd = findViewById(R.id.fabAdd); //2. 찾기
    }

    private void initAdapter(){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvPersons.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter(mContext);
        rvPersons.setAdapter(personAdapter);
    }

    private void initData(){
        PersonProvider p = new PersonProvider();
        personAdapter.addItems(p.findAll());
    }

        // 3. 리스너 만들기
    private void initListener() {
        fabAdd.setOnClickListener(view -> {
            personAdapter.addItem(new Person("이름new", "0102222"));
        });
    }

    public void mRvScroll(){
        rvPersons.scrollToPosition(personAdapter.getItemCount()-1);
    }
}