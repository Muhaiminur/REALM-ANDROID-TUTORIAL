package com.muhaiminurabir.realm_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.muhaiminurabir.realm_android.MODEL.Name_List;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class Data_List extends AppCompatActivity {

    @BindView(R.id.name_recycler)
    RecyclerView nameRecycler;


    Name_List_Adapter mAdapter;
    List<Object>mRecyclerViewItems=new ArrayList<Object>();

    private Realm realm;
    RealmResults<Name_List> name_lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__list);
        ButterKnife.bind(this);
        //adddata();
        nameRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        nameRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        nameRecycler.setLayoutManager(mLayoutManager);
        nameRecycler.setItemAnimator(new DefaultItemAnimator());
        mAdapter= new Name_List_Adapter(mRecyclerViewItems);
        nameRecycler.setAdapter(mAdapter);


        try {
            Realm.init(getApplicationContext());
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name("realm_android.realm")
                    .schemaVersion(1)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm = Realm.getInstance(config);
            name_lists=realm.where(Name_List.class).findAll().sort("first_name", Sort.DESCENDING);
            mRecyclerViewItems.addAll(name_lists);
            mAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.d("Error Line Number", Log.getStackTraceString(e));
            if (realm!=null){
                realm.close();
            }
        }finally {

        }
    }
    public void adddata(){
        Name_List n1=new Name_List("Abir1","Rahman");
        Name_List n2=new Name_List("Abir2","Rahman");
        Name_List n3=new Name_List("Abir3","Rahman");
        Name_List n4=new Name_List("Abir4","Rahman");
        Name_List n5=new Name_List("Abir5","Rahman");
        mRecyclerViewItems.add(n1);
        mRecyclerViewItems.add(n2);
        mRecyclerViewItems.add(n3);
        mRecyclerViewItems.add(n4);
        mRecyclerViewItems.add(n5);mRecyclerViewItems.add(n1);mRecyclerViewItems.add(n1);

    }
}
