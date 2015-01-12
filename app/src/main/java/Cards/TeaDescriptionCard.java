package Cards;

import android.widget.TextView;

import android.view.View;
import android.view.ViewGroup;

import android.content.Context;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

import com.t_danbubbletea.bubbleteaapp.R;

public class TeaDescriptionCard extends Card {

    public TeaDescriptionCard(Context context) {
        super(context);
        initCustomCard();
    }

    public TeaDescriptionCard(Context context, int layout_Id){
        super(context, layout_Id);
        initCustomCard();
    }

    private void initCustomCard(){
        TeaDescriptionHeader tInnerDescText = new TeaDescriptionHeader(getContext(),
                                                                       R.layout.tea_description_layout);
        tInnerDescText.innerTitle = "Tea Description:";
        tInnerDescText.innerSubTitle = "This is a test";
        addCardHeader(tInnerDescText);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {


    }

}
    class TeaDescriptionHeader extends CardHeader{

        String innerTitle;
        String innerSubTitle;

        public TeaDescriptionHeader(Context context, int innerLayout) {
            super(context, innerLayout);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view){
            TextView teaHeader = (TextView)view.findViewById(R.id.tea_desc_title);
            TextView teaDescription = (TextView) view.findViewById(R.id.tea_desc_content);

            teaHeader.setText(innerTitle);
            teaDescription.setText(innerSubTitle);

        }


    }
