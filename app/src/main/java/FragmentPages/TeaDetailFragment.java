package FragmentPages;

import java.util.ArrayList;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.t_danbubbletea.bubbleteaapp.R;

import Cards.TeaDescriptionCard;

import Cards.TeaTopRaterCard;
import Cards.UserStatisticsCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardViewNative;
import it.gmariotti.cardslib.library.view.CardListView;
import mehdi.sakout.fancybuttons.FancyButton;

public class TeaDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.tea_detail_layout, container, false);
        setUpTeaPage(rootView);
        setUpTeaCustomizeOptionList(rootView);
        buttonSetup(rootView);

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

        UserStatisticsCard userStatisticsCard = new UserStatisticsCard(getActivity(),
                                                                R.layout.tea_use_statistics_layout);
        customizeCards.add(userStatisticsCard);

        CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getActivity(), customizeCards);
        CardListView cardListView = (CardListView) view.findViewById(R.id.card_custom_list);
        cardListView.setAdapter(cardArrayAdapter);
    }

    private void buttonSetup(View view){
        FancyButton customButton = (FancyButton) view.findViewById(R.id.btn_customize);
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                // replace current fragment with new fragment
                fm.beginTransaction().remove(new TeaDetailFragment())
                        .replace(R.id.tea_frag_frame, new CustomizationPage())
                        .addToBackStack(null).commit();
            }
        });
    }

}
