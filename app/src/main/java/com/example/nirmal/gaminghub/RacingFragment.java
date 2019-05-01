package com.example.nirmal.gaminghub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class RacingFragment extends Fragment {


    public RacingFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_racing, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {

        ImageButton bt1 = null;
        ImageButton bt2 = null;
        ImageButton bt3 = null;
        final ImageButton imgbtns[]={bt1,bt2,bt3};
        int ids[]={R.id.gm213,R.id.gm131,R.id.gm516};
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
                          case "gm213":
                              bundle.putString("gameid",ID);
                              detailsOfGameActivity.putExtras(bundle);
                              startActivity(detailsOfGameActivity);
                              break;

                          case "gm131":
                              bundle.putString("gameid",ID);
                              detailsOfGameActivity.putExtras(bundle);
                              startActivity(detailsOfGameActivity);
                              break;

                          case "gm516":
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
