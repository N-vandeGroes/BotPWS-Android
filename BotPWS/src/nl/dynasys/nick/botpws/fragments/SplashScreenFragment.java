package nl.dynasys.nick.botpws.fragments;

import nl.dynasys.nick.botpws.R;
import nl.dynasys.nick.botpws.types.PagerController;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SplashScreenFragment extends Fragment {

	private PagerController pagerController;
	
	public SplashScreenFragment(PagerController pController){
		
		this.pagerController = pController;
		
	}
	
	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewParent, Bundle savedInstanceState){
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {

		            	SplashScreenFragment.this.pagerController.nextPage();
		            
		            }
		        }, 
		        5000 
		);
		
		// Return View
		return layoutInflater.inflate(R.layout.fragment_splashscreen_default, viewParent, false);
		
	}
	
}
