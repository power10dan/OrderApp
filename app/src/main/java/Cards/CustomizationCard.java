package Cards;

import android.content.Context;

import android.widget.TextView;

import android.view.View;
import android.view.ViewGroup;


import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import com.t_danbubbletea.bubbleteaapp.R;

public class CustomizationCard extends Card{

    public CustomizationCard(Context context, int innerLayout){
        super(context, innerLayout);
        init();
    }

    private void init(){
        //Add thumbnail
        CardThumbnail cardThumbnail = new CardThumbnail(mContext);
        cardThumbnail.setDrawableResource(R.drawable.carddemo_ic_gmaps);
        addCardThumbnail(cardThumbnail);



    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view){
        TextView customizeOptions = (TextView) view.findViewById(R.id.);

        customizeOptions.setText("Milk type");




    }
}
