package info.bijon.image_upload;

import android.os.AsyncTask;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Bj on 05-06-17.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://appsticit.com/mucse/add_user.php";
        String data_one=params[0];
        String data_two=params[1];
        String data_three = params[2];
        String data_four = params[3];
        String data_five = params[4];
        String data_six = params[5];
        String data_seven = params[6];



        try{
            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(data_one,"UTF-8")+"&"+
                    URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(data_two,"UTF-8")+"&"+
                    URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(data_three,"UTF-8")+"&"+
                    URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(data_four,"UTF-8")+"&"+
                    URLEncoder.encode("word","UTF-8")+"="+URLEncoder.encode(data_five,"UTF-8")+"&"+
                    URLEncoder.encode("zone","UTF-8")+"="+URLEncoder.encode(data_six,"UTF-8")+"&"+
                    URLEncoder.encode("roadno","UTF-8")+"="+URLEncoder.encode(data_seven,"UTF-8")+"&";
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            inputStream.close();
            return "Data inserted";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


}
