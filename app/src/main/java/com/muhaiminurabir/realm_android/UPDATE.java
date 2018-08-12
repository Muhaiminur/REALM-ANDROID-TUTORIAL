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

public class UPDATE extends AppCompatActivity {

    @BindView(R.id.update_input_first)
    EditText updateInputFirst;
    @BindView(R.id.update_data)
    Button updateData;

    Realm realm;
    @BindView(R.id.update_input)
    EditText updateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);
        try {
            Realm.init(getApplicationContext());
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name("realm_android.realm")
                    .schemaVersion(1)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm = Realm.getInstance(config);
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
            if (realm != null) {
                realm.close();
            }
        } finally {

        }
    }

    @OnClick(R.id.update_data)
    public void onViewClicked() {
        Name_List name_rows = realm.where(Name_List.class).equalTo("first_name", updateInputFirst.getText().toString()).findFirst();
        if (name_rows==null){
            Toast.makeText(this, "DATA NOT FOUND", Toast.LENGTH_SHORT).show();
        }else {
            realm.beginTransaction();
            name_rows.setFirst_name(updateInput.getText().toString());
            realm.commitTransaction();
            finish();
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        }
    }
}
