package Cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.t_danbubbletea.bubbleteaapp.R;

import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by daniellin on 15/1/13.
 */
public class CustomizeOptionCard extends Card {

    public CustomizeOptionCard(Context context) {
        super(context);
        initCustomCard();
    }

    public CustomizeOptionCard(Context context, int layout_Id){
        super(context, layout_Id);
        initCustomCard();
    }

    private void initCustomCard(){
        TeaCustomCardHeader tCustomText = new TeaCustomCardHeader(getContext(),
                                                                  R.layout.tea_custom_inner_layout);
        tCustomText.innerTitle = "Tea Customization Option 1";
        tCustomText.innerSubTitle = "Click me to customize me! ";
        addCardHeader(tCustomText);
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














