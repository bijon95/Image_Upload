package info.bijon.image_upload;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.RelativeDateTimeFormatter;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity  {
    private  String locationAddress;
    private Button uploadBtn, chooseBtn;
    private EditText etRemark;
    private String name = "Bijon" ;
    private TextView addressTV;
    private ImageView imageView;
    private final int IMG_REQUET=1;
    private static final int CAMERA_REQUEST = 1888;
    private String status;

    private String encode_String, image_name;
    private Bitmap bitmap;
    private File file;
    private Uri file_uri;
    private RadioGroup radioGroup;

    GPSTracker gps;

    private double latitude, longitude;

    String mydate = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
    String uuid = UUID.randomUUID().toString();
    String imgName = uuid.substring(1,10);

    private  String UloadURL = "http://appsticit.com/mucse/photoinfo.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uploadBtn = (Button) findViewById(R.id.button);
        chooseBtn = (Button) findViewById(R.id.button3);

        imageView =(ImageView) findViewById(R.id.imageView);
        addressTV =(TextView) findViewById(R.id.textView2);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        etRemark= (EditText) findViewById(R.id.etremark);



        //Access location

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            checkLocation();



             // selectImage();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                chooseBtn.setVisibility(View.INVISIBLE);
                chooseBtn.setVisibility(View.GONE);
            }
                });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radio_clear){
                    status="0";

                }
                else if(checkedId==R.id.radio_unclear){
                    status="1";

                }
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();
            }

        });



    }



    private void getFileUri() {
        image_name = uuid.substring(1,10);
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + image_name);
        file_uri = Uri.fromFile(file);
    }


    private void selectImage(){

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,IMG_REQUET);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap = (Bitmap) data.getExtras().get("data");
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

            imageView.setImageBitmap(bitmap);
        }
    }

    private  String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes,Base64.DEFAULT);
    }


    private  void uploadImage(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UloadURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(MainActivity.this,Response,Toast.LENGTH_LONG).show();
                            imageView.setImageResource(0);
                            imageView.setVisibility(View.GONE);
                           addressTV.setText("");
                           addressTV.setVisibility(View.GONE);
                           etRemark.setVisibility(View.GONE);
                            radioGroup.setVisibility(View.GONE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //restartFirstActivity();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Upload Error",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                String maplatitude= new Double(latitude).toString();
                String maplongitude= new Double(longitude).toString();
                String remark = etRemark.getText().toString();
                String userid = getIntent().getStringExtra("userid");
               String wardno = getIntent().getStringExtra("wardno");

                params.put("name",imgName);
                params.put("image",imageToString(bitmap));
               params.put("address",locationAddress);
                params.put("maplatitude",maplatitude);
                params.put("maplongitude",maplongitude);
                params.put("userid",userid);
                params.put("status",status);
                params.put("remark",remark);
              //  params.put("sender",wardno);

                return params;
            }
        };
        MySingleton.getmInstance(MainActivity.this).addToRequestQue(stringRequest);
    }



    private void  checkLocation(){

        gps = new GPSTracker(MainActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

             latitude = gps.getLatitude();
             longitude = gps.getLongitude();
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    getApplicationContext(), new MainActivity.GeocoderHandler());
            // \n is for new line
        }else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }



    }

//For Location
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MainActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
//for Location
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
           addressTV.setText(locationAddress);
        }
    }
    private void restartFirstActivity()
    {
        Intent mIntent = getIntent();
        finish();
        startActivity(mIntent);
    }
}
