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

import Cards.CustomizeOptionCard;
import Cards.CustomizeOptionCard;
import Cards.TeaDescriptionCard;

import Cards.TeaTopRaterCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardViewNative;
import it.gmariotti.cardslib.library.view.CardListView;

public class TeaDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.tea_detail_layout, container, false);
        setUpTeaPage(rootView);
        setUpTeaCustomizeOptionList(rootView);

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

    private void setUpTeaCustomizeOptionList(View view){

        ArrayList<Card> customizeCards = new ArrayList<>();

        TeaTopRaterCard teaTopRaterCard = new TeaTopRaterCard(getActivity(), R.layout.tea_rater_card_layout);
        customizeCards.add(teaTopRaterCard);
        CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getActivity(), customizeCards);
        CardListView cardListView = (CardListView) view.findViewById(R.id.card_custom_list);
        cardListView.setAdapter(cardArrayAdapter);
    }
}
