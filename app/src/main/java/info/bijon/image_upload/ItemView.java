package info.bijon.image_upload;

        import android.content.Intent;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.Button;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import java.util.Locale;

public class ItemView extends AppCompatActivity {

    TextView tv;
    Button mapBtn;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        tv=(TextView) findViewById(R.id.textView6);
        mapBtn = (Button) findViewById(R.id.button4);
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(getIntent().getStringExtra("imageURL"));
        webView.setWebViewClient(new WebViewClient());


        tv.setText(getIntent().getStringExtra("address"));
        final  String mapdata=getIntent().getStringExtra("data");
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+mapdata));
                i.setClassName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity");
                startActivity(i);
            }
        });


    }
}
