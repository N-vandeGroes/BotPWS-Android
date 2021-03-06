package nl.dynasys.nick.botpws.fragments;

import nl.dynasys.nick.botpws.BotControlActivity;
import nl.dynasys.nick.botpws.BotDebugActivity;
import nl.dynasys.nick.botpws.BotRootActivity;
import nl.dynasys.nick.botpws.R;
import nl.dynasys.nick.botpws.adapters.DefaultListAdapter;
import nl.dynasys.nick.botpws.adapters.DefaultListAdapter.RowDetailTag;
import nl.dynasys.nick.botpws.handlers.BotPWSDebugger;
import nl.dynasys.nick.botpws.types.BotDetails;
import nl.dynasys.nick.botpws.types.ModeDetails;
import nl.dynasys.nick.botpws.types.PagerController;
import nl.dynasys.nick.botpws.types.ParamHandler;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ConnectionTypeFragment extends Fragment implements ListView.OnItemClickListener, ParamHandler {

	private PagerController pagerController;
	
	public ConnectionTypeFragment(){
		
		
		
	}
	
	public ConnectionTypeFragment(PagerController pController){
		
		this.pagerController = pController;
		
	}
		
	private TextView connModeTextView;
	private ListView connModeListView;
	
	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewParent, Bundle savedInstanceState){
		
		// Create View
		View fragmentView = layoutInflater.inflate(R.layout.fragment_botselector_connmode, viewParent, false);
				
		// Initialize TextView
		connModeTextView = (TextView) fragmentView.findViewById(R.id.BOTSELECTOR_CONNMODE_LABELFRAME_LABEL);
		
		// Initialize ListView
		connModeListView = (ListView) fragmentView.findViewById(R.id.BOTSELECTOR_CONNMODE_SELECTORLIST);
		
		// Debug Logging
		BotPWSDebugger.log("Check Available Modi.");
		BotPWSDebugger.log("Waiting for User Choice...");
		
		// Initialize ConnMode Array
		ModeDetails[] availableModus = {new ModeDetails(), new ModeDetails(), new ModeDetails()};

		availableModus[0].MODE_DISPLAY_NAME = "Control Mode";
		availableModus[0].MODE_DESC = "Control the robot with the BotPWS Application.";
		availableModus[0].MODE_TECH_ID = 1;
		
		availableModus[1].MODE_DISPLAY_NAME = "Root Mode";
		availableModus[1].MODE_DESC = "Full Access to everything that's going on inside the Bot.";
		availableModus[1].MODE_TECH_ID = 2;
		
		availableModus[2].MODE_DISPLAY_NAME = "Debug Mode";
		availableModus[2].MODE_DESC = "Access to all Sensors and Debugging Information.";
		availableModus[2].MODE_TECH_ID = 3;
		
		// Set ListAdapter
		connModeListView.setAdapter(new DefaultListAdapter(this.getActivity(), availableModus));
		connModeListView.setOnItemClickListener(this);
		
		// Return View
		return fragmentView;
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View clickedView, int viewPosition, long viewId) {

		RowDetailTag rdHolder = (RowDetailTag) clickedView.getTag();
		ModeDetails selectedMode = rdHolder.modeDetails;
		
		switch(selectedMode.MODE_TECH_ID){
		
			case 1 :
			
				// Debug Logging
				BotPWSDebugger.log("User Choice Accepted.");
				BotPWSDebugger.log("Launching BotController.");
				
				// Start Control Activity
				Intent controlIntent = new Intent(this.getActivity(), BotControlActivity.class);
				startActivity(controlIntent);
				
			break;
			
			case 2 :
				
				// Debug Logging
				BotPWSDebugger.log("User Choice Accepted.");
				BotPWSDebugger.log("Launching Root Mode.");
				
				// Start Root Activity
				Intent rootIntent = new Intent(this.getActivity(), BotRootActivity.class);
				startActivity(rootIntent);
				
			break;
		
			case 3 :
				
				// Debug Logging
				BotPWSDebugger.log("User Choice Accepted.");
				BotPWSDebugger.log("Launching BotDebugger.");
				
				// Start Debug Activity
				Intent debugIntent = new Intent(this.getActivity(), BotDebugActivity.class);
				startActivity(debugIntent);
				
			break;
			
		}
		
	}
	
	@Override
	public void handleParameters(Object paramPacket) {
		
		// Change Bot Name in String
		String botString = connModeTextView.getText().toString();
		botString = botString.replace("{BOT}", ((BotDetails) paramPacket).BOT_DISPLAY_NAME);
		
		// Create SpannableStringBuilder for Making words BOLD
		SpannableStringBuilder ssBuilder = new SpannableStringBuilder(botString);

		// Create StyleSpan
		StyleSpan styleObj = new StyleSpan(android.graphics.Typeface.BOLD);

		// Set BOTNAME Part to BOLD
		ssBuilder.setSpan(styleObj, 50, 51 + ((BotDetails) paramPacket).BOT_DISPLAY_NAME.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); 
		
		// Set Text
		connModeTextView.setText(ssBuilder);
				
	}

}
