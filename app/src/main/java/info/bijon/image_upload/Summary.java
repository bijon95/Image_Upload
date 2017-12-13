package info.bijon.image_upload;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Summary extends AppCompatActivity {
    ArrayList<SummaryGetSet> summaryList;
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        listview = (ListView) findViewById(R.id.listSummary);
        summaryList = new ArrayList<>();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Summary.ReadJSON().execute("http://appsticit.com/mucse/datasummary.php");
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
                JSONArray jsonArray =  jsonObject.getJSONArray("photoinfo");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    summaryList.add(new SummaryGetSet(
                            productObject.getString("wardno"),
                            productObject.getString("noOfSpot"),
                            productObject.getString("cleared"),
                            productObject.getString("unClear"),
                            productObject.getString("remark")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        final    SummaryAdapter summaryAdapter = new SummaryAdapter(getApplicationContext(), R.layout.summary_item,summaryList);
            listview.setAdapter(summaryAdapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(Summary.this,UnclearDataView.class);
                  String urlid = ((TextView)view.findViewById(R.id.tvward)).getText().toString();
                  i.putExtra("urlid",urlid);
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
}
