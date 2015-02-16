package FragmentPages;

import java.text.NumberFormat;
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
    private int sizeSelection;
    private String flavorSelection;
    private String typeSelection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.customization_page, container, false);
        setupCustomOptionCard(rootView, container);



        return rootView;
    }

    private void setupCustomOptionCard(View view, final ViewGroup container) {

        ArrayList<Card> customCard = new ArrayList();
        // in future, we are changing these dummy data
        // they will instead use hashmap to store data instead of an array
        String [] sizeOptions = {"8 Oz", "16 Oz", "32 Oz"};
        String [] flavorOptions = {"Vanilla", "Chile", "Cool Beanary"};
        String [] tapiocaBallsType = {"Green Pepper", "Flower", "Magic Beans"};
        String [] options = {"Size","Flavor","Tapioca Ball Types"};

        final CustomizeOptionCard customizeSizeCard = new CustomizeOptionCard(getActivity(),
                                                                        R.layout.tea_customization_inner_layout,
                                                                        options[0], sizeOptions);

        customCard.add(customizeSizeCard);
        final CustomizeOptionCard customizeFlavorCard = new CustomizeOptionCard(getActivity(),
                                                                          R.layout.tea_customization_inner_layout,
                                                                          options[1], flavorOptions);
        customCard.add(customizeFlavorCard);
        final CustomizeOptionCard customizeTapiocaCard = new CustomizeOptionCard(getActivity(),
                                                                          R.layout.tea_customization_inner_layout,
                                                                          options[2], tapiocaBallsType);
        customCard.add(customizeTapiocaCard);

        CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getActivity(), customCard);
        CardListView cardListView = (CardListView) view.findViewById(R.id.card_custom_list);
        cardListView.setAdapter(cardArrayAdapter);

        FancyButton submitButton = (FancyButton) view.findViewById(R.id.btn_submit_order);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeSelection = 0;
                char[] size = customizeSizeCard.getUserSelection().toCharArray();
                for (int i = 0; i < size.length; i++) {
                    if (Character.isDigit(size[i])) {
                        sizeSelection = sizeSelection * 10 + size[i] - 48;
                    }
                }

                flavorSelection = customizeFlavorCard.getUserSelection();
                typeSelection = customizeTapiocaCard.getUserSelection();
                MySQLiteHelper db = new MySQLiteHelper(container.getContext());
                // add tea
                db.addTeaData(new TeaData(sizeSelection, flavorSelection, typeSelection, (int) Math.floor(Math.random()*10000)));
            }
        });
    }

}
