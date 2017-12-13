package info.bijon.image_upload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText userid, pass1, pass2, name, phone, word, zone, roadno;
    String useritSt, pass1ST, pass2ST, nameST,phoneST, wordST, zoneST, roadnoST;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userid = (EditText) findViewById(R.id.etUserid);

        pass1 = (EditText) findViewById(R.id.etpass1);

        pass2 = (EditText) findViewById(R.id.etpass2);

        name =(EditText) findViewById(R.id.etName);

        phone= (EditText) findViewById(R.id.etPhone);

        word = (EditText) findViewById(R.id.etWordno);

        zone = (EditText) findViewById(R.id.etZone);

        roadno = (EditText) findViewById(R.id.etRoad);


        submitBtn = (Button) findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass1ST = pass1.getText().toString();
                pass2ST = pass2.getText().toString();
                useritSt = userid.getText().toString();
                nameST = name.getText().toString();
                phoneST = phone.getText().toString();
                wordST = word.getText().toString();
                zoneST = zone.getText().toString();
                roadnoST = roadno.getText().toString();

                if (pass1ST.length()!=0&&pass2ST.length()!=0&&useritSt.length()!=0) {

                if (pass1ST.equals(pass2ST)) {
                    BackgroundTask backgroundTask = new BackgroundTask();
                    backgroundTask.execute(nameST, useritSt, pass1ST, phoneST, wordST, zoneST, roadnoST);
                    Toast.makeText(Registration.this, "Successfully Added User", Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(Registration.this, "Password Error", Toast.LENGTH_LONG).show();
                }

            }
                else {
                    Toast.makeText(Registration.this, "Please Fill Up All fild", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
