package Cards;

import android.util.Log;
import java.util.HashMap;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import com.t_danbubbletea.bubbleteaapp.R;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;
import it.gmariotti.cardslib.library.internal.base.BaseCard;
import it.gmariotti.cardslib.library.internal.Card;
import com.squareup.picasso.Picasso;

public class NewArrivalCards extends CardWithList {

    ArrayList<HashMap<String, String>> teaInformation;

    public NewArrivalCards(Context context, ArrayList<HashMap<String, String>> teaInfo) {
        super(context);
        teaInformation = teaInfo;
    }

    @Override
    protected CardHeader initCardHeader() {
        CardHeader cardHead = new CardHeader(getContext(), R.layout.new_arrival_header_layout);
        // set up collapse  menu
        cardHead.setPopupMenu(R.menu.overflow_menu, new CardHeader.OnClickCardHeaderPopupMenuListener() {

            @Override
            public void onMenuItemClick(BaseCard card, MenuItem item) {
                Toast.makeText(getContext(), "Click on " + item.getTitle(), Toast.LENGTH_SHORT).show();
            }

        });

        cardHead.setTitle("New Arrivals");

        return cardHead;
    }

    @Override
    protected void initCard() {
        setUseProgressBar(true);
    }

    @Override
    protected List<ListObject> initChildren() {

        // object that contains a list of child objects to be displayed
        List<ListObject> listData= new ArrayList<>();

        for(int i = 0; i < teaInformation.size(); i++) {

            NewArrivalObject newArrivalData = new NewArrivalObject(this);

            for(HashMap.Entry<String,String> entries: teaInformation.get(i).entrySet()) {

                if(entries.getKey().equals("newTeaName")) {
                    newArrivalData.teaName = entries.getValue();
                }

                if(entries.getKey().equals("newTeaImage")) {
                    newArrivalData.teaImage = entries.getValue();
                }

                if(entries.getKey().equals("newTeaPrice")) {
                    newArrivalData.teaPrice = entries.getValue();
                }
            }

            listData.add(newArrivalData);
        }

        return listData;
    }

    @Override
    public View setupChildView(int i, ListObject listObject, View view, ViewGroup viewGroup) {

        ImageView teaThumbNail = (ImageView) view.findViewById(R.id.new_arrival_image);
        TextView teaText = (TextView) view.findViewById(R.id.name_tea);
        TextView price = (TextView) view.findViewById(R.id.new_tea_price);

        NewArrivalObject newArrivalObject = (NewArrivalObject) listObject;
        // using picasso to load tea thumbnail and scaling it
        Picasso.with(getContext()).load(newArrivalObject.teaImage).into(teaThumbNail);
        teaText.setText(newArrivalObject.teaName);
        price.setText("Price: $ " + newArrivalObject.teaPrice);

        return view;
    }

    @Override
    public int getChildLayoutId() {
        return R.layout.new_arrival_inner_layout;
    }

    class NewArrivalObject extends DefaultListObject {

        public String teaName;
        public String teaImage;
        public String teaPrice;

        public NewArrivalObject(Card parentCard) {
            super(parentCard);
        }

    }

    @Override
    public int getType() {
        return 1;
    }
}