package Cards;

import android.content.Context;

import android.widget.TextView;
import android.widget.ImageView;

import android.view.View;
import android.view.ViewGroup;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

import com.squareup.picasso.Picasso;
import com.t_danbubbletea.bubbleteaapp.R;

public class CustomizationCard extends Card{

    String customizationType;
    int imageDrawable;
    public CustomizationCard(Context context, int innerLayout, String customType, int drawableImg){
        super(context, innerLayout);
        customizationType = customType;
        imageDrawable = drawableImg;
        init();
    }

    private void init(){
        IconThumbnail iconThumb = new IconThumbnail(getContext());
        iconThumb.setExternalUsage(true);
        addCardThumbnail(iconThumb);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view){
        TextView customizeOptions = (TextView) view.findViewById(R.id.custom_card_title);
        customizeOptions.setText(customizationType);
    }

    class IconThumbnail extends CardThumbnail {

        public IconThumbnail(Context context) {
            super(context);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View viewImage) {

            Picasso.with(getContext())
                    .load(imageDrawable)
                    .into((ImageView) viewImage);

            viewImage.getLayoutParams().width = 190;
            viewImage.getLayoutParams().height = 190;
        }
    }
}
