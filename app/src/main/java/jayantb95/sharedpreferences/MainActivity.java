package jayantb95.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private EditText edtName;
    private EditText edtContact;
    private TextView txtHeadingSharedPref;
    private TextView txtName;
    private TextView txtContact;
    private Button btnSave;
    private Button btnRetrive;
    private Button btnClear;
    public static final String MY_PREFERENCES = "pref";
    public static final String NAME = "keyname";
    public static final String CONTACT = "keycontact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        listener();
    }

    private void initialize() {
        edtName = findViewById(R.id.edt_name);
        edtContact = findViewById(R.id.edt_contact);
        txtName = findViewById(R.id.txt_retrieve_name);
        txtContact = findViewById(R.id.txt_retrieve_contact);
        txtHeadingSharedPref = findViewById(R.id.txt_shared_pref_heading);
        btnSave = findViewById(R.id.btn_save);
        btnRetrive = findViewById(R.id.btn_retrive);
        btnClear = findViewById(R.id.btn_clear);
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        loadData();
    }

    private void listener() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeData();
                clear();
            }
        });

        btnRetrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }

    public void storeData() {
        String n = edtName.getText().toString();
        String c = edtContact.getText().toString();
        if (n.isEmpty() | c.isEmpty()) {
            Toast.makeText(MainActivity.this,
                    "Enter both the values",
                    Toast.LENGTH_SHORT)
                    .show();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NAME, n);
            editor.putString(CONTACT, c);
            editor.apply();
            Toast.makeText(MainActivity.this, "Data has ben stored sucessfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clear() {
        edtName.setText("");
        edtContact.setText("");
    }

    public void loadData() {
        if (sharedPreferences.contains(NAME)) {
            txtHeadingSharedPref.setVisibility(View.VISIBLE);
            txtName.setVisibility(View.VISIBLE);
            txtName.setText("Name: " + sharedPreferences.getString(NAME, ""));
        } else {
            txtHeadingSharedPref.setVisibility(View.GONE);
            txtName.setVisibility(View.GONE);
        }
        if (sharedPreferences.contains(CONTACT)) {
            txtHeadingSharedPref.setVisibility(View.VISIBLE);
            txtContact.setVisibility(View.VISIBLE);
            txtContact.setText("Contact: " + sharedPreferences.getString(CONTACT, ""));
        } else {
            txtHeadingSharedPref.setVisibility(View.GONE);
            txtContact.setVisibility(View.GONE);
        }
    }
}