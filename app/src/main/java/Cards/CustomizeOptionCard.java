package Cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.t_danbubbletea.bubbleteaapp.R;

import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.Card;

public class CustomizeOptionCard extends Card {

    String customOption;
    String [] customChoice;

    public CustomizeOptionCard(Context context, String option, String [] choice) {
        super(context);
        customOption = option;
        initCustomCard(customOption);
    }

    public CustomizeOptionCard(Context context, int layout_Id, String option, String [] choice){
        super(context, layout_Id);
        customOption = option;
        customChoice = choice;
        initCustomCard(customOption);
    }

    private void initCustomCard(String customOption){

        TeaCustomCardHeader tCustomText = new TeaCustomCardHeader(getContext(),
                                                                  R.layout.tea_custom_inner_layout);
        tCustomText.innerTitle = "Tea Customization Option 1";
        tCustomText.innerSubTitle = "Click me to customize me!";
        addCardHeader(tCustomText);
    }

    @Override
    public void setupInnerViewElements(ViewGroup viewGroup, View view){




    }

}
    class TeaCustomCardHeader extends CardHeader {

        String innerTitle;
        String innerSubTitle;

        public TeaCustomCardHeader(Context context, int innerLayout) {
            super(context, innerLayout);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view){
            TextView teaHeader = (TextView)view.findViewById(R.id.tea_custom_title);
            TextView teaDescription = (TextView) view.findViewById(R.id.tea_user_status);
            teaHeader.setText(innerTitle);
            teaDescription.setText(innerSubTitle);
        }
    }














