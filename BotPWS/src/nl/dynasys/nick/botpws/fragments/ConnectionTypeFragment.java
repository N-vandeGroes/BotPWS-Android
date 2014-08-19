package nl.dynasys.nick.botpws.fragments;

import nl.dynasys.nick.botpws.R;
import nl.dynasys.nick.botpws.adapters.DefaultListAdapter;
import nl.dynasys.nick.botpws.adapters.BotSelectorListAdapter.RowDetailTag;
import nl.dynasys.nick.botpws.types.BotDetails;
import nl.dynasys.nick.botpws.types.ModeDetails;
import nl.dynasys.nick.botpws.types.PagerController;
import nl.dynasys.nick.botpws.types.ParamHandler;
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
		
		// Initialize ConnMode Array
		ModeDetails[] availableModus = {new ModeDetails(), new ModeDetails(), new ModeDetails()};

		availableModus[0].MODE_DISPLAY_NAME = "Control Mode";
		availableModus[0].MODE_DESC = "Control the robot with the BotPWS Application.";
		
		availableModus[1].MODE_DISPLAY_NAME = "Root Mode";
		availableModus[1].MODE_DESC = "Full Access to everything that's going on inside the Bot.";
		
		availableModus[2].MODE_DISPLAY_NAME = "Debug Mode";
		availableModus[2].MODE_DESC = "Access to all Sensors and Debugging Information.";
		
		// Set ListAdapter
		connModeListView.setAdapter(new DefaultListAdapter(this.getActivity(), availableModus));
		connModeListView.setOnItemClickListener(this);
		// Return View
		return fragmentView;
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View clickedView, int viewPosition, long viewId) {

		this.pagerController.nextPage();
		
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
