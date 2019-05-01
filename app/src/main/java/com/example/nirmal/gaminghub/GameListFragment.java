package com.example.nirmal.gaminghub;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameListFragment extends Fragment {
    public GameListFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_list, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        //---------------------calling action games--------------------------------------------------------------------------------
        final TextView action_opt=(TextView)getView().findViewById(R.id.action);
        action_opt.setOnClickListener(new View.OnClickListener()
        {
                                         public void onClick(View v)
                                         {
                                             action_opt.setTextColor(Color.BLUE);
                                             GamesFragment gamesFragment=new GamesFragment();
                                             android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                             fragmentTransaction.replace(R.id.fragment_container, gamesFragment);
                                             fragmentTransaction.commit();
                                         }
                                      }
        );
        //-------------------------calling Racing games--------------------------------------------------------------------------
        final TextView racing_opt=(TextView)getView().findViewById(R.id.racing);
        racing_opt.setOnClickListener(new View.OnClickListener()
        {
                                         public void onClick(View v)
                                         {
                                             racing_opt.setTextColor(Color.BLUE);
                                             RacingFragment racingFragment=new RacingFragment();
                                             android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                             fragmentTransaction.replace(R.id.fragment_container, racingFragment);
                                             fragmentTransaction.commit();
                                         }
                                      }
        );
        //=======================calling stretagy games------------------------------------------------------------------------
        final TextView stretady_opt=(TextView)getView().findViewById(R.id.stretagy);
        stretady_opt.setOnClickListener(new View.OnClickListener()
        {
                                           public void onClick(View v)
                                           {
                                               stretady_opt.setTextColor(Color.BLUE);
                                               StretagyFragment stretagyFragment=new StretagyFragment();
                                               android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                               fragmentTransaction.replace(R.id.fragment_container, stretagyFragment);
                                               fragmentTransaction.commit();
                                           }
                                        }
        );
    }
}
