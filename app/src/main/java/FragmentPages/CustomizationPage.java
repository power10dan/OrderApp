package FragmentPages;

import java.util.ArrayList;


import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.t_danbubbletea.bubbleteaapp.R;

import Cards.CustomizeOptionCard;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

import mehdi.sakout.fancybuttons.FancyButton;

public class CustomizationPage extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.customization_page, container, false);
        setupCustomOptionCard(rootView);
        FancyButton submitButton = (FancyButton) rootView.findViewById(R.id.btn_submit_order);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crouton.makeText(getActivity(), "Order submitted", Style.CONFIRM).show();
            }
        });


        return rootView;
    }

    private void setupCustomOptionCard(View view) {

        ArrayList<Card> customCard = new ArrayList();
        // in future, we are changing these dummy data
        // they will instead use hashmap to store data instead of an array
        String [] sizeOptions = {"8 Oz", "16 Oz", "32 Oz"};
        String [] flavorOptions = {"Vanilla", "Chile", "Cool Beanary"};
        String [] tapiocaBallsType = {"Green Pepper", "Flower", "Magic Beans"};
        String [] options = {"Size","Flavor","Tapioca Ball Types"};

        CustomizeOptionCard customizeSizeCard = new CustomizeOptionCard(getActivity(),
                                                                        R.layout.tea_customization_inner_layout,
                                                                        options[0], sizeOptions);
        customCard.add(customizeSizeCard);
        CustomizeOptionCard customizeFlavorCard = new CustomizeOptionCard(getActivity(),
                                                                          R.layout.tea_customization_inner_layout,
                                                                          options[1], flavorOptions);
        customCard.add(customizeFlavorCard);
        CustomizeOptionCard customizeTapiocaCard = new CustomizeOptionCard(getActivity(),
                                                                          R.layout.tea_customization_inner_layout,
                                                                          options[2], tapiocaBallsType);
        customCard.add(customizeTapiocaCard);

        CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getActivity(), customCard);
        CardListView cardListView = (CardListView) view.findViewById(R.id.card_custom_list);
        cardListView.setAdapter(cardArrayAdapter);
    }

}
