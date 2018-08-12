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

public class DELETE extends AppCompatActivity {

    @BindView(R.id.delete_input_first)
    EditText deleteInputFirst;
    @BindView(R.id.delete_data)
    Button deleteData;

    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
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

    @OnClick(R.id.delete_data)
    public void onViewClicked() {
        Name_List name_rows= realm.where(Name_List.class).equalTo("first_name", deleteInputFirst.getText().toString()).findFirst();;
        realm.beginTransaction();
        name_rows.deleteFromRealm();
        realm.commitTransaction();
        finish();
        Toast.makeText(this, "Deleted Data", Toast.LENGTH_SHORT).show();
    }
}
