package FragmentPages;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ImageView;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.t_danbubbletea.bubbleteaapp.R;

import Cards.CustomizationCard;
import Cards.TeaDescriptionCard;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardGridArrayAdapter;
import it.gmariotti.cardslib.library.view.CardViewNative;
import it.gmariotti.cardslib.library.view.CardGridView;

public class TeaDetailFragment extends Fragment {

    int [] drawable = {R.drawable.flavorthumb, R.drawable.milkthumb, R.drawable.tempthumb,
                        R.drawable.weightthumb};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.tea_detail_layout, container, false);
        setUpTeaPage(rootView);
        setUpCardGrid(rootView);

        return rootView;

    }

    private void setUpTeaPage(View view){
        // set up tea profile image
        ImageView teaImage = (ImageView) view.findViewById(R.id.tea_image);
        Picasso.with(getActivity())
                .load("http://chuangmi.my-place.us/Tea_Pictures/assam.jpg")
                .into(teaImage);

        // set up description card
        TeaDescriptionCard descriptCard = new TeaDescriptionCard(getActivity());
        //Set card in the cardView
        CardViewNative cardView = (CardViewNative) view.findViewById(R.id.description_card);
        cardView.setCard(descriptCard);

    }

    private void setUpCardGrid(View view){

        ArrayList<Card> arrayGridCards = new ArrayList<>();
        // add customization card
        CustomizationCard customCardFlavor = new CustomizationCard(getActivity()
                , R.layout.customization_text_layout
                , "Flavor", drawable[0]);
        arrayGridCards.add(customCardFlavor);

        CustomizationCard customCardMilk = new CustomizationCard(getActivity()
                , R.layout.customization_text_layout
                , "Milk", drawable[1]);
        arrayGridCards.add(customCardMilk);

        CustomizationCard customCardTemp = new CustomizationCard(getActivity()
                , R.layout.customization_text_layout
                , "Temperature", drawable[2]);
        arrayGridCards.add(customCardTemp);

        CustomizationCard customCardWeight = new CustomizationCard(getActivity()
                , R.layout.customization_text_layout
                , "Weight", drawable[3]);
        arrayGridCards.add(customCardWeight);

        // set up card array adapter
        CardGridArrayAdapter cardGridArrayAdapter = new CardGridArrayAdapter(getActivity()
                                                                             ,arrayGridCards);

        CardGridView customTeaGridView = (CardGridView) view.findViewById(R.id.tea_customize_grid);
        customTeaGridView.setAdapter(cardGridArrayAdapter);

    }
}
