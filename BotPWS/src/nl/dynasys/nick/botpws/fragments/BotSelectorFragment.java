package nl.dynasys.nick.botpws.fragments;

import nl.dynasys.nick.botpws.R;
import nl.dynasys.nick.botpws.adapters.BotSelectorListAdapter;
import nl.dynasys.nick.botpws.adapters.BotSelectorListAdapter.RowDetailTag;
import nl.dynasys.nick.botpws.types.BotDetails;
import nl.dynasys.nick.botpws.types.PagerController;
import nl.dynasys.nick.botpws.types.ParamHandler;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class BotSelectorFragment extends Fragment implements ListView.OnItemClickListener, ParamHandler {

	private PagerController pagerController;
	
	public BotSelectorFragment(){
	
		
	
	}
	
	public BotSelectorFragment(PagerController pController){
		
		this.pagerController = pController;
		
	}
	
	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewParent, Bundle savedInstanceState){
		
		// Create Fragment Layout
		View fragmentView = layoutInflater.inflate(R.layout.fragment_botselector_default, viewParent, false);
		
		// Initialize ListView
		ListView selectorList = (ListView) fragmentView.findViewById(R.id.BOTSELECTOR_SELECTORLIST);
		
		// Debug Botnames
		BotDetails[] botnames = {new BotDetails(), new BotDetails()};
		
		botnames[0].BOT_DISPLAY_NAME = "ProfielWerkstukRobot Nick";
		botnames[1].BOT_DISPLAY_NAME = "PWSRobot Roel";
		
		// Debugging
		botnames[0].BOT_CONN_2400 = true;
		botnames[1].BOT_CONN_2400 = true;	
		
		botnames[0].BOT_CONN_433 = true;
		botnames[1].BOT_CONN_433 = true;
		
		botnames[0].BOT_CONN_BLUETOOTH = true;
		botnames[1].BOT_CONN_BLUETOOTH = true;
		
		botnames[0].BOT_CONN_WIFI = true;
		botnames[1].BOT_CONN_WIFI = true;
		
		// Set List Adapter with Debug Information
		selectorList.setAdapter(new BotSelectorListAdapter(this.getActivity(), botnames));
		
		// Set onItemClickListener for ListAdapter
		selectorList.setOnItemClickListener(this);
		
		// Display FragmentView
		return fragmentView;
		
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View clickedView, int viewPosition, long viewId) {

		RowDetailTag rdHolder = (RowDetailTag) clickedView.getTag();
		this.pagerController.nextPageWithParams(rdHolder.botDetails);
		
	}

	@Override
	public void handleParameters(Object paramPacket) {
		// TODO Auto-generated method stub
		
	}
	
}
