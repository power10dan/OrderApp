package Cards;

import android.content.Context;

import java.util.ArrayList;

import android.content.DialogInterface;

import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import android.app.AlertDialog;

import com.afollestad.materialdialogs.MaterialDialog;
import com.t_danbubbletea.bubbleteaapp.R;

import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.Card;
import mehdi.sakout.fancybuttons.FancyButton;

public class CustomizeOptionCard extends Card {

    String customOption;
    String [] customChoice;

    public CustomizeOptionCard(Context context, String option, String [] choice) {
        super(context);
        customOption = option;
        customChoice = choice;
        initCustomCard(customOption);
    }

    public CustomizeOptionCard(Context context, int layout_Id, String option,
                               String [] choice){
        super(context, layout_Id);
        customOption = option;
        customChoice = choice;
        initCustomCard(customOption);
    }

    private void initCustomCard(String customOption){

        TeaCustomCardHeader tCustomText = new TeaCustomCardHeader(getContext(),
                                                                  R.layout.tea_custom_header_layout);
        tCustomText.innerTitle = customOption;
        addCardHeader(tCustomText);
    }

    @Override
    public void setupInnerViewElements(ViewGroup viewGroup, View view){

        final TextView userSelection = (TextView) view.findViewById(R.id.option_user_select);
        ArrayList<FancyButton> listOfButtons = new ArrayList<>();
        userSelection.setText(customChoice[0]); // default option, first element in choice array

        // setup button on click listeners, declare final to be used in inner class
        final FancyButton buttonOptions = (FancyButton) view.findViewById(R.id.btn_1);
        // set text and on click events
        buttonOptions.setText("Selections");
        // int i must be declared final if to be used in setOnClickListener
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpChoiceDialog(customChoice, userSelection);
            }
        });

    }

    private void setUpChoiceDialog(final String [] choiceArray, final TextView displaySelection){
        new MaterialDialog.Builder(getContext())
                .title("Options")
                .items(choiceArray)
                .itemsCallback(new MaterialDialog.ListCallback(){
                    @Override
                    public void onSelection (MaterialDialog dialog, View view, int which, CharSequence text){
                        displaySelection.setText(choiceArray[which]);
                    }
                }).show();
    }
}
    class TeaCustomCardHeader extends CardHeader {

        String innerTitle;

        public TeaCustomCardHeader(Context context, int innerLayout) {
            super(context, innerLayout);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view){
            TextView teaHeader = (TextView)view.findViewById(R.id.tea_custom_title);

            teaHeader.setText(innerTitle);
        }
    }