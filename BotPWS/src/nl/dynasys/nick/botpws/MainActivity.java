package nl.dynasys.nick.botpws;

import java.util.ArrayList;

import nl.dynasys.nick.botpws.adapters.DefaultSlideAdapter;
import nl.dynasys.nick.botpws.fragments.BotControlFragment;
import nl.dynasys.nick.botpws.fragments.BotSelectorFragment;
import nl.dynasys.nick.botpws.fragments.ConnectionTypeFragment;
import nl.dynasys.nick.botpws.fragments.SplashScreenFragment;
import nl.dynasys.nick.botpws.handlers.BotPWSDebugger;
import nl.dynasys.nick.botpws.types.PagerController;
import nl.dynasys.nick.botpws.types.ParamHandler;
import nl.dynasys.nick.botpws.view.SolidViewPager;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;

public class MainActivity extends FragmentActivity implements PagerController{
	
	// Hold viewpager object for dynamic editing.
	private SolidViewPager vpObject;
	
	// Viewpager Adapter, object processes pages to display
	private PagerAdapter vpAdapter;

	// Initialize SliderPages Object
	private ArrayList<Fragment> sliderPages = new ArrayList<Fragment>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// Call Parent onCreate Method
		super.onCreate(savedInstanceState);
	
		// Debug Logging
		BotPWSDebugger.log("Initializing Application.");
		
		// Set Current Layout
		setContentView(R.layout.activity_sliderframe_main);			
		
		// Create Fragment Array For Slider Pages
		//sliderPages.add(new SplashScreenFragment(this));
		sliderPages.add(new BotSelectorFragment(this));
		sliderPages.add(new ConnectionTypeFragment(this));
		
		// DEPRECATED : Fragment is Replaced By Activity
		// sliderPages.add(new BotControlFragment());
		
		// Debug Logging
		BotPWSDebugger.log("Setting up Robot Selection Menu.");
		
		// Initialize ViewPager
		vpObject = (SolidViewPager) this.findViewById(R.id.MAIN_ACTIVITY_VIEWPAGER);
		vpAdapter = new DefaultSlideAdapter(getSupportFragmentManager(), sliderPages);
				
		// Set ViewPager Adapter
		vpObject.setAdapter(vpAdapter);	
		
	}
	
	@Override
	public void onBackPressed(){
		
		// Slide Back, except to the SplashScreen
		if(vpObject.getCurrentItem() > 1){
			
			vpObject.setCurrentItem(vpObject.getCurrentItem() - 1);
		
		}
			
	}
	
	@Override
	public void nextPage() {
		
		// Slide to next page on fragment trigger event
		vpObject.setCurrentItem(vpObject.getCurrentItem() + 1);
				
	}

	@Override
	public void nextPageWithParams(Object pageParams) {

		// Slide to next page on fragment trigger event
		vpObject.setCurrentItem(vpObject.getCurrentItem() + 1);
		
		// Pass Bundle to Fragment
		ParamHandler targetFragment = (ParamHandler) sliderPages.get(vpObject.getCurrentItem());
		targetFragment.handleParameters(pageParams);		
		
	}
	
}
