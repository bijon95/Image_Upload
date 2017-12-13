package info.bijon.image_upload;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ListViewJson extends AppCompatActivity {


    ArrayList<Product> arrayList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_json);
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://appsticit.com/mucse/dustbin_Json.php");
            }
        });
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("server_response");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Product(
                            productObject.getString("image"),
                            productObject.getString("postBY"),
                            productObject.getString("location"),
                            productObject.getString("latitude"),
                            productObject.getString("longitude"),
                            productObject.getString("remark")
                            ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final CustomListAdapter adapter = new CustomListAdapter(
                    getApplicationContext(), R.layout.custom_list_layout, arrayList
            );
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                  //  String item = view.findViewById(View.LAYOUT_DIRECTION_LTR).toString();
                    String item = ((TextView)view.findViewById(R.id.latitude)).getText().toString();
                     String item2 = ((TextView)view.findViewById(R.id.longitude)).getText().toString();
                    String mapdata = item+","+item2;
                    String address= ((TextView)view.findViewById(R.id.txtPrice)).getText().toString();
                    String imageURL = ((TextView)view.findViewById(R.id.imageID)).getText().toString();

                Intent i = new Intent(ListViewJson.this,ItemView.class);
               //     String latitude = String.valueOf(arrayList.get(4));
                 //   String longitude = String.valueOf(arrayList.get(5));

                   // String item = ((TextView)view).getText().toString();
                    i.putExtra("imageURL",imageURL);
                    i.putExtra("address",address);
                    i.putExtra("data",mapdata);
                    // Toast.makeText(ListViewJson.this,item,Toast.LENGTH_LONG).show();

                    startActivity(i);
                }
            });
        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewLIST:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Intent i = new Intent(ListViewJson.this, Summary.class);
                startActivity(i);
                return true;

            case R.id.addperson:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Intent in = new Intent(ListViewJson.this, Registration.class);
                startActivity(in);
                return true;




        }
        return super.onOptionsItemSelected(item);
    }
}