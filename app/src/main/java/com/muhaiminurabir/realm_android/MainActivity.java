package com.muhaiminurabir.realm_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.create)
    Button create;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.update)
    Button update;
    @BindView(R.id.find)
    Button find;
    @BindView(R.id.list)
    Button list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.create)
    public void onCreateClicked() {
        startActivity(new Intent(MainActivity.this, CREATE.class));
    }

    @OnClick(R.id.delete)
    public void onDeleteClicked() {
        startActivity(new Intent(MainActivity.this, DELETE.class));
    }

    @OnClick(R.id.update)
    public void onUpdateClicked() {
        startActivity(new Intent(MainActivity.this, UPDATE.class));
    }

    @OnClick(R.id.find)
    public void onFindClicked() {
        startActivity(new Intent(MainActivity.this, FIND.class));
    }

    @OnClick(R.id.list)
    public void onListClicked() {

        startActivity(new Intent(MainActivity.this, Data_List.class));
    }
}
