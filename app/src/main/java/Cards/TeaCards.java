package Cards;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Gravity;
import android.app.FragmentManager;

import FragmentPages.TeaDetailFragment;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

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

        tInnerText.setButtonExpandVisible(true);
        tInnerText.titleOfCard = teaTitle;
        tInnerText.subTitleOfCard = "Tea Facts";
        tInnerText.teaPrice = teaCost;
        tInnerText.teaIngredients = teaDesc;
        tInnerText.teaCalories = teaCal;
        tInnerText.mTeaImageURL = teaImageSource;
        tInnerText.setButtonExpandVisible(false);
        addCardHeader(tInnerText);

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        TextView title = (TextView) view.findViewById(R.id.card_main_inner_simple_title);
        title.setText("Click me for more details");
        title.setTextColor(mContext.getResources().getColor(R.color.lightBrown));
        title.setGravity(Gravity.CENTER_VERTICAL);

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
        String teaCalories;
        String teaPrice;
        String mTeaImageURL;

        public TeaCardInnerText(Context context, int innerLayout) {
            super(context, innerLayout);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            ImageView viewImage = (ImageView)view.findViewById(R.id.card_thumbnail_image);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView subTitle = (TextView) view.findViewById(R.id.section_layout);
            TextView calories = (TextView) view.findViewById(R.id.calories_layout);
            TextView ingredients = (TextView) view.findViewById(R.id.ingredients_layout);
            TextView price = (TextView)view.findViewById(R.id.price);

            Picasso.with(getContext()).load(mTeaImageURL).into(viewImage);
            title.setText(titleOfCard);
            subTitle.setText(subTitleOfCard);
            calories.setText("Calories: "+ teaCalories);
            ingredients.setText("Gluten Free");
            price.setText("Price: $" + teaPrice);

        }

    }