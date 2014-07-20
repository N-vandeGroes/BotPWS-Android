package nl.dynasys.nick.botpws.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SolidViewPager extends ViewPager {

	// Constructor
    public SolidViewPager(Context svContext, AttributeSet attrSet) {
        
    	// Call Super Constructor
    	super(svContext, attrSet);

    }

    
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
    	
    	// Disable Scrolling
        return false;
        
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
    	
    	// Disable Scrolling
        return false;
        
    }

}
