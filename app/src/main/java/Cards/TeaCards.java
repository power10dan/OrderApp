package Cards;

import android.content.Context;

import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

import com.gc.materialdesign.views.ButtonFlat;
import com.squareup.picasso.Picasso;
import com.t_danbubbletea.bubbleteaapp.R;

public class TeaCards extends Card {

    // tea card variables.
    private String teaName;
    private String teaImageURL;
    private String teaDescription;
    private String teaPrice;
    private String teaCalories;

    public TeaCards(Context context, String tea, String teaImage, String teaContent,
                    String teaCost, String teaCaloriesCount) {

        super(context);

        teaName = tea;
        teaImageURL = teaImage;
        teaDescription = teaContent;
        teaPrice = teaCost;
        teaCalories = teaCaloriesCount;

        init(teaName, teaImageURL, teaDescription, teaPrice, teaCalories);
    }

    public TeaCards(Context context, int layout, String tea, String teaImage, String teaContent,
                    String teaCost, String teaCaloriesCount) {

        super(context, layout);

        teaName = tea;
        teaImageURL = teaImage;
        teaDescription = teaContent;
        teaPrice = teaCost;
        teaCalories = teaCaloriesCount;

        init(teaName, teaImageURL, teaDescription, teaPrice, teaCalories);
    }

    private void init(String teaTitle, String teaImageSource, String teaDesc, String teaCost,
                      String teaCal) {

        TeaCardInnerText tInnerText = new TeaCardInnerText(getContext(),R.layout.tea_frag_card_layout);

        tInnerText.setButtonExpandVisible(false);
        tInnerText.titleOfCard = teaTitle;
        tInnerText.subTitleOfCard = "Complete Time: 2 to 5 Minutes";
        tInnerText.teaPrice = teaCost;
        tInnerText.teaIngredients = teaDesc;
        tInnerText.mTeaImageURL = teaImageSource;
        addCardHeader(tInnerText);

    }

    @Override
    public int getType() {
        //Very important with different inner layouts
        return 0;
    }

}
    class TeaCardInnerText extends CardHeader {

        String titleOfCard;
        String subTitleOfCard;
        String teaIngredients;
        String teaPrice;
        String mTeaImageURL;

        public TeaCardInnerText(Context context, int innerLayout) {
            super(context, innerLayout);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            ImageView viewImage = (ImageView)view.findViewById(R.id.card_thumbnail_image);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView subTitle = (TextView) view.findViewById(R.id.time_prep);
            final TextView price = (TextView) view.findViewById(R.id.price);
            // buttons
            ButtonFlat eightOunce = (ButtonFlat) view.findViewById(R.id.buttonflat);
            ButtonFlat sixteenOunce = (ButtonFlat) view.findViewById(R.id.buttonflat2);
            ButtonFlat thirtyTwoOunce = (ButtonFlat) view.findViewById(R.id.buttonflat3);

            eightOunce.setText("8oz Price");
            sixteenOunce.setText("16oz Price");
            thirtyTwoOunce.setText("32oz Price");

            eightOunce.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    price.setText("$"+teaPrice);

                }
            });

            sixteenOunce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    price.setText("$6.50");
                }
            });

            thirtyTwoOunce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    price.setText("$9.50");
                }
            });

            Picasso.with(getContext()).load(mTeaImageURL).into(viewImage);
            title.setText(titleOfCard);
            subTitle.setText(subTitleOfCard);
            price.setText("$"+teaPrice);
        }
    }