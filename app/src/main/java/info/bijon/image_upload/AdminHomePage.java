package info.bijon.image_upload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomePage extends AppCompatActivity {

    Button btnList, newUserBtn , summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        btnList = (Button) findViewById(R.id.dataView);
        newUserBtn = (Button) findViewById(R.id.AddUserBtn);
        summary = (Button) findViewById(R.id.summaryBtn) ;

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHomePage.this,ListViewJson.class);
                startActivity(i);
            }
        });
        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHomePage.this,Registration.class);
                startActivity(i);
            }
        });
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHomePage.this,Summary.class);
                startActivity(i);
            }
        });
    }
}
