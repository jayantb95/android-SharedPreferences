package jayantb95.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private TextView txtName;
    private TextView txtEmail;
    private Button btn_Save;
    private Button btn_Retrive;
    private Button btn_Clear;
    public static final String mypreferences = "pref";
    public static final String Name = "keyname";
    public static final String Email = "keyemail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        listener();
    }

    private void initialize() {
        txtName = (TextView) findViewById(R.id.etName);
        txtEmail = (TextView) findViewById(R.id.etEmail);
        btn_Save = (Button) findViewById(R.id.btnSave);
        btn_Retrive = (Button) findViewById(R.id.btnRetrive);
        btn_Clear = (Button) findViewById(R.id.btnClear);
        sharedPreferences = getSharedPreferences(mypreferences, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(Name)) {
            txtName.setText(sharedPreferences.getString(Name, ""));
        }
        if (sharedPreferences.contains(Email)) {
            txtEmail.setText(sharedPreferences.getString(Email, ""));
        }
    }

    private void listener() {

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeData();
                clear();
            }
        });

        btn_Retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }

    public void storeData() {

        String n = txtName.getText().toString();
        String e = txtEmail.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.apply();

        Toast.makeText(MainActivity.this, "Data has ben stored sucessfully!", Toast.LENGTH_SHORT).show();
    }

    public void clear() {

        txtName.setText("");
        txtEmail.setText("");
    }

    public void loadData() {

        if (sharedPreferences.contains(Name)) {
            txtName.setText(sharedPreferences.getString(Name, ""));
        }
        if (sharedPreferences.contains(Email)) {
            txtEmail.setText(sharedPreferences.getString(Email, ""));

        }
    }
}
