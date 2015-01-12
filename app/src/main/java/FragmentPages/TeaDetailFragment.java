package FragmentPages;

import android.os.Bundle;
import android.widget.ImageView;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.t_danbubbletea.bubbleteaapp.R;

import Cards.TeaDescriptionCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.view.CardViewNative;

public class TeaDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.tea_detail_layout, container, false);

        ImageView teaImage = (ImageView) rootView.findViewById(R.id.tea_image);

        Picasso.with(getActivity())
                .load("http://chuangmi.my-place.us/Tea_Pictures/assam.jpg")
                .into(teaImage);

        TeaDescriptionCard descriptCard = new TeaDescriptionCard(getActivity());
        //Set card in the cardView
        CardViewNative cardView = (CardViewNative) rootView.findViewById(R.id.description_card);
        cardView.setCard(descriptCard);

        return rootView;

    }
}
