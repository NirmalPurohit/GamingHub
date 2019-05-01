package com.example.nirmal.gaminghub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class StretagyFragment extends Fragment {
    public StretagyFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stretagy, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {

        ImageButton bt1 = null;
        ImageButton bt2 = null;
        final ImageButton imgbtns[]={bt1,bt2};
        int ids[]={R.id.gm151,R.id.gm171};
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
                          case "gm151":
                              //  Log.d("Racing", "gm312");
                              bundle.putString("gameid",ID);
                              detailsOfGameActivity.putExtras(bundle);
                              startActivity(detailsOfGameActivity);
                              break;

                          case "gm171":
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
