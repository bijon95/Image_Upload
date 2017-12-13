package info.bijon.image_upload;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;
/**
 * Created by Bj on 30-05-17.
 */

public class CustomListAdapter extends ArrayAdapter<Product> {
    ArrayList<Product> products;
    Context context;
    int resource;

    public CustomListAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_layout, null, true);

        }
        Product product = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewProduct);
        Picasso.with(context).load(product.getImage()).into(imageView);

        TextView textUrl = (TextView) convertView.findViewById(R.id.imageID);
        textUrl.setText(product.getImage());
        textUrl.setVisibility(View.INVISIBLE);
        textUrl.setVisibility(View.GONE);

        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtName.setText(product.getName());

        TextView txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
        txtPrice.setText(product.getPrice());

        TextView latitude = (TextView) convertView.findViewById(R.id.latitude);
        latitude.setText(product.getLatitude());

        latitude.setVisibility(View.INVISIBLE);
        latitude.setVisibility(View.GONE);

        TextView longitude = (TextView) convertView.findViewById(R.id.longitude);
        longitude.setText(product.getLongitude());
        longitude.setVisibility(View.INVISIBLE);
        longitude.setVisibility(View.GONE);

        TextView remark = (TextView) convertView.findViewById(R.id.remark);
        remark.setText(product.getRemark());
        remark.setVisibility(View.INVISIBLE);
        remark.setVisibility(View.GONE);


        return convertView;
    }
}
