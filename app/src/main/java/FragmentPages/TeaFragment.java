package FragmentPages;

import com.t_danbubbletea.bubbleteaapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.List;

import Cards.NewArrivalCards;
import Cards.TeaCards;
import Database.DatabaseConnector;
import it.gmariotti.cardslib.library.view.CardListView;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.prototypes.CardSection;
import it.gmariotti.cardslib.library.prototypes.SectionedCardAdapter;

public class TeaFragment extends Fragment {

    CardArrayAdapter cardArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tea_frag, container, false);

        ArrayList<Card> cards = new ArrayList<>();
        cardArrayAdapter = new CardArrayAdapter(getActivity(), cards);
        cardArrayAdapter.setInnerViewTypeCount(2);

        CardListView listView = (CardListView) rootView.findViewById(R.id.card_list);

        if (listView != null) {
            listView.setAdapter(cardArrayAdapter);
        }

        // get and parse the data
        try {
            new GetTeaInfo().execute(new DatabaseConnector()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    class GetTeaInfo extends AsyncTask<DatabaseConnector, Long, ArrayList<Card>> {

       private ArrayList<HashMap<String, String>> newArrivalTeaData = new ArrayList<>();
       private ArrayList<HashMap<String, String>> teaCardData = new ArrayList<>();
       private ArrayList<Card> cards = new ArrayList<>();

        @Override
        protected ArrayList<Card> doInBackground(DatabaseConnector... arg0) {

            JSONArray jsonArrayOfTeas = arg0[0].getAllTeas();

            for (int i = 0; i < jsonArrayOfTeas.length(); i++) {
                JSONObject jsonObject = null;

                try {

                    jsonObject = jsonArrayOfTeas.getJSONObject(i);

                    if (jsonObject.getInt("new") == 1) {
                        // remove backslash in URL
                        String newTeaImageURL = (jsonObject.getString("imageURL")).replace("\\", "");
                        String newTeaName = jsonObject.getString("name");
                        String newTeaPrice = jsonObject.getString("teaprice");

                        // init hashmap to contain parsed tea data
                        HashMap<String, String> newTeaInfo = new HashMap<>();

                        newTeaInfo.put("newTeaImage", newTeaImageURL);
                        newTeaInfo.put("newTeaPrice", newTeaPrice);
                        newTeaInfo.put("newTeaName", newTeaName);
                        newArrivalTeaData.add(newTeaInfo);

                    } else {

                        String teaImage = (jsonObject.getString("imageURL").replace("\\", ""));
                        String teaName = jsonObject.getString("name");
                        String teaPrice = jsonObject.getString("teaprice");
                        String teaCalories = jsonObject.getString("calories");
                        String teaDesc = jsonObject.getString("desc");

                        HashMap<String, String> teaInfo = new HashMap<>();

                        teaInfo.put("teaImage", teaImage);
                        teaInfo.put("teaName", teaName);
                        teaInfo.put("teaPrice", teaPrice);
                        teaInfo.put("teaCalories", teaCalories);
                        teaInfo.put("teaDesc", teaDesc);

                        teaCardData.add(teaInfo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            cards = initNewArrivalCard(cards, newArrivalTeaData);
            cards = initTeaCard(cards, teaCardData);


            return cards;
        }
        // after finishing getting all tea data and putting them into cards,
        // set the cards into the adapter and notify the GUI that data has been added / updated
        @Override
        protected void onPostExecute(ArrayList<Card> cards){
            cardArrayAdapter.addAll(cards);
            cardArrayAdapter.notifyDataSetChanged();
        }

    }

    private ArrayList <Card> initNewArrivalCard(ArrayList<Card> cardList,
                                                ArrayList<HashMap<String, String>> teaInfo) {

        NewArrivalCards newArrivalCard = new NewArrivalCards(getActivity(), teaInfo);
        newArrivalCard.init();
        cardList.add(newArrivalCard);

        return cardList;
    }

    private ArrayList <Card> initTeaCard (ArrayList <Card> cardList,
                                          ArrayList <HashMap<String, String>> teaDataInfo) {

        for(int i = 0; i < teaDataInfo.size(); i++) {
            String tea = "";
            String teaImage = "";
            String teaContent = "";
            String teaCost = "";
            String teaCaloriesCount = "";

            for (HashMap.Entry<String, String> entries : teaDataInfo.get(i).entrySet()) {

                if (entries.getKey().equals("teaName")) {
                    tea = entries.getValue();
                }

                if (entries.getKey().equals("teaImage")) {
                    teaImage = entries.getValue();
                }

                if (entries.getKey().equals("teaPrice")) {
                    teaCost = entries.getValue();
                }

                if(entries.getKey().equals("teaCalories")){
                    teaCaloriesCount = entries.getValue();

                }

                if(entries.getKey().equals("teaDesc")){
                    teaContent = entries.getValue();
                }

            }

            TeaCards teaCard = new TeaCards(getActivity(), tea, teaImage, teaContent,
                    teaCost, teaCaloriesCount);
            teaCard.setType(1);
            cardList.add(teaCard);
        }

        return cardList;
    }

    /*
    * Custom Card section
    */
    class TeaSections extends CardSection {

        public CharSequence mButtonTxt;

        public TeaSections(int firstPosition, CharSequence title, CharSequence buttonText) {
            super(firstPosition, title);
            mButtonTxt = buttonText;
        }

    }
}