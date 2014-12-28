package Adapters;

import java.util.ArrayList;
import java.util.Random;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.nostra13.universalimageloader.core.*;
import com.t_danbubbletea.bubbleteaapp.R;

public class ImageViewAdapter extends ArrayAdapter<String> {

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private final LayoutInflater layoutInflater;
    private final Random randomGridWidthGenerator; // help make grid width vary
    private static final SparseArray<Double> sPositionHeightRatios
                         = new SparseArray<Double>();

    public ImageViewAdapter(Context context, int textViewResourceId,
                            ArrayList<String> objects) {
        super(context, textViewResourceId, objects);
        this.layoutInflater = LayoutInflater.from(context);
        this.randomGridWidthGenerator = new Random();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    @Override
    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        ViewHolder vh;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.sgv_list_layout,
                    parent, false);
            vh = new ViewHolder();
            vh.imgView = (DynamicHeightImageView) convertView
                          .findViewById(R.id.imgView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);
        vh.imgView.setHeightRatio(positionHeight);
        imageLoader.displayImage(getItem(position), vh.imgView);
        return convertView;

    }

     static class ViewHolder {
        DynamicHeightImageView imgView;

     }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
        }
        return ratio;
    }
    private double getRandomHeightRatio() {
        return (randomGridWidthGenerator.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
    }
}
