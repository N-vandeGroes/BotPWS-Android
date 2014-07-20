package nl.dynasys.nick.botpws.adapters;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class DefaultSlideAdapter extends FragmentStatePagerAdapter {

	private ArrayList<Fragment> fragmentList;
	
    public DefaultSlideAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragmentList) {
    	
    	// Call Super Fragment Manager
        super(fragmentManager);
        
        // Set FragmentList
        this.fragmentList = fragmentList;
        
    }

    public void addSlide(Fragment slideFragment){
    
    	// Add Slide
    	fragmentList.add(slideFragment);
    	
    }
    
    @Override
    public Fragment getItem(int itemPos) {
        
    	// Return Fragment
    	return fragmentList.get(itemPos);
    	
    }

    @Override
    public int getCount() {
        
    	// Return Page Amount
    	return fragmentList.size();
    	
    }
	
}
