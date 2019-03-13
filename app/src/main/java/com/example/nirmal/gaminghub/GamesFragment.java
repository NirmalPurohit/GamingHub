package com.example.nirmal.gaminghub;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class GamesFragment extends Fragment {


    public GamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {

        ImageButton bt1 = null;
        ImageButton bt2 = null;
        ImageButton bt3 = null;
        ImageButton bt4 = null;
        ImageButton bt5 = null;
        final ImageButton imgbtns[]={bt1,bt2,bt3,bt4,bt5};
        int ids[]={R.id.gm123,R.id.gm234,R.id.gm345,R.id.gm141,R.id.gm617};
        for(int i=0;i<imgbtns.length;i++)
        {
            imgbtns[i]=(ImageButton)getView().findViewById(ids[i]);
            final ImageButton temp=imgbtns[i];

            imgbtns[i].setOnClickListener(new View.OnClickListener()
            {
                                              Intent detailsOfGameActivity=new Intent(getActivity(),DetailsOfGame.class);
                                              Bundle bundle = new Bundle();

                                             public void onClick(View v)
                                             {
                                                 String ID=temp.getResources().getResourceEntryName(temp.getId());
                                                 switch(ID)
                                                 {
                                                     case "gm123":
                                                         bundle.putString("gameid",ID);
                                                         detailsOfGameActivity.putExtras(bundle);
                                                         startActivity(detailsOfGameActivity);
                                                         break;

                                                     case "gm234":
                                                         bundle.putString("gameid",ID);
                                                         detailsOfGameActivity.putExtras(bundle);
                                                         startActivity(detailsOfGameActivity);
                                                         break;

                                                     case "gm345":
                                                         bundle.putString("gameid",ID);
                                                         detailsOfGameActivity.putExtras(bundle);
                                                         startActivity(detailsOfGameActivity);
                                                         break;

                                                     case "gm141":
                                                         bundle.putString("gameid",ID);
                                                         detailsOfGameActivity.putExtras(bundle);
                                                         startActivity(detailsOfGameActivity);
                                                         break;

                                                     case "gm617":
                                                         bundle.putString("gameid",ID);
                                                         detailsOfGameActivity.putExtras(bundle);
                                                         startActivity(detailsOfGameActivity);
                                                         break;

                                                 }
                                             }
                                          }
            );



        }


    }

}
