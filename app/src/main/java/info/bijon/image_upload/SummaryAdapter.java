package info.bijon.image_upload;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bj on 10-06-17.
 */

public class SummaryAdapter extends ArrayAdapter<SummaryGetSet> {
    ArrayList<SummaryGetSet> summaryGetSets;
    Context context;
    int resource;


    public SummaryAdapter(Context context, int resource, ArrayList<SummaryGetSet> products) {
        super(context,resource,products);

        this.context = context;
        this.resource = resource;
        this.summaryGetSets=products;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.summary_item, null, true);

        }
        SummaryGetSet summaryGetSet = getItem(position);

        TextView wardView = (TextView) convertView.findViewById( R.id.tvward) ;
        wardView.setText(summaryGetSet.getWardno());



        TextView nofospot = (TextView) convertView.findViewById( R.id.tvnofspot) ;
        nofospot.setText(summaryGetSet.getNoOfSpot());


        TextView clear = (TextView) convertView.findViewById( R.id.tvclear) ;
        clear.setText(summaryGetSet.getClear());


        TextView unclear = (TextView) convertView.findViewById( R.id.tvunclear) ;
        unclear.setText(summaryGetSet.getUnClear());


        TextView remark = (TextView) convertView.findViewById( R.id.tvremark) ;
        remark.setText(summaryGetSet.getRemark());
        return convertView;
    }
}

