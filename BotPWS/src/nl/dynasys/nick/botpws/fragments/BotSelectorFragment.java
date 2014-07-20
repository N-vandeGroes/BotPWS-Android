package nl.dynasys.nick.botpws.fragments;

import nl.dynasys.nick.botpws.R;
import nl.dynasys.nick.botpws.adapters.BotSelectorListAdapter;
import nl.dynasys.nick.botpws.adapters.BotSelectorListAdapter.RowHolder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class BotSelectorFragment extends Fragment implements ListView.OnItemClickListener {

	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewParent, Bundle savedInstanceState){
		
		// Create Fragment Layout
		View fragmentView = layoutInflater.inflate(R.layout.fragment_botselector_default, viewParent, false);
		
		// Initialize ListView
		ListView selectorList = (ListView) fragmentView.findViewById(R.id.BOTSELECTOR_SELECTORLIST);
		
		// Debug Botnames
		String[] botnames = {"ProfielWerkStukRobot","ControlBot","DebugBot", "RaceAuto", "RaceAuto", "RaceAuto", "RaceAuto", "RaceAuto", "RaceAuto", "RaceAuto"};
		
		// Set List Adapter with Debug Information
		selectorList.setAdapter(new BotSelectorListAdapter(this.getActivity(), botnames));
		
		// Set onItemClickListener for ListAdapter
		selectorList.setOnItemClickListener(this);
		
		// Display FragmentView
		return fragmentView;
		
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View clickedView, int viewPosition, long viewId) {

		Toast.makeText(getActivity(), ((RowHolder) clickedView.getTag()).botNameView.getText(), Toast.LENGTH_LONG).show();
		
	}
	
}
