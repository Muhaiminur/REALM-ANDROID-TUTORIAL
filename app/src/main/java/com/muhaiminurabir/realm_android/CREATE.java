package com.muhaiminurabir.realm_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muhaiminurabir.realm_android.MODEL.Name_List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CREATE extends AppCompatActivity {

    @BindView(R.id.create_input_first)
    EditText createInputFirst;
    @BindView(R.id.create_input_last)
    EditText createInputLast;
    @BindView(R.id.create_data)
    Button createData;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);
        try {
            Realm.init(getApplicationContext());
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name("realm_android.realm")
                    .schemaVersion(1)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm = Realm.getInstance(config);
        }catch (Exception e){
            Log.d("Error Line Number", Log.getStackTraceString(e));
            if (realm!=null){
                realm.close();
            }
        }finally {

        }
    }

    @OnClick(R.id.create_data)
    public void onViewClicked() {
        try {
            Name_List exist_name=realm.where(Name_List.class).equalTo("first_name",createInputFirst.getText().toString()).findFirst();
            realm.beginTransaction();
            if (exist_name!=null){
                Toast.makeText(this, "Alreasy in database try another name", Toast.LENGTH_SHORT).show();
            }else {
                Name_List nameList=realm.createObject(Name_List.class);
                nameList.setFirst_name(createInputFirst.getText().toString());
                nameList.setLast_name(createInputLast.getText().toString());
                finish();
                Toast.makeText(this, "Saved Data", Toast.LENGTH_SHORT).show();
            }
            realm.commitTransaction();
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }
}
