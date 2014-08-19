package nl.dynasys.nick.botpws.adapters;

import nl.dynasys.nick.botpws.R;
import nl.dynasys.nick.botpws.types.ModeDetails;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DefaultListAdapter extends ArrayAdapter<ModeDetails> {

	private Context aCTX;
	private ModeDetails[] modeStore;
	
	public static class ModeRowHolder {
		
		public TextView modeNameView;
		public TextView modeDescView;
		
	}
	
	public static class RowDetailTag {
	
		public ModeRowHolder rowHolder;
		public ModeDetails modeDetails;
	
	}
	
	public DefaultListAdapter(Context appContext, ModeDetails[] availableModus) {
		
		// Call Super Constructor
		super(appContext, R.layout.listitem_botselector_botlist, availableModus);
		
		// Set Context
		this.aCTX = appContext;
		
		// Store Robot Names
		this.modeStore = availableModus;
		
	}
	
	@Override
	public View getView(int viewPosition, View convertView, ViewGroup viewParent){
				
		View rowView = convertView;
		
		if(rowView == null){
						
			// Create New RowView
			LayoutInflater layoutInflater = (LayoutInflater) aCTX.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
			rowView = layoutInflater.inflate(R.layout.listitem_botselector_botlist, viewParent, false);
			
			// Initialize RowHolder
			ModeRowHolder rowHolder = new ModeRowHolder();
			
			rowHolder.modeNameView = (TextView) rowView.findViewById(R.id.LISTITEM_BOTSELECTOR_BOTNAME);
			rowHolder.modeDescView = (TextView) rowView.findViewById(R.id.LISTITEM_BOTSELECTOR_TECHBOTNAME);

			// Initialize BotDetails
			ModeDetails connModeDetails = new ModeDetails();
						
			// Initialize RowDetailTag
			RowDetailTag rdTag = new RowDetailTag();
			rdTag.modeDetails = connModeDetails;
			rdTag.rowHolder = rowHolder;
			
			// Set Tag
			rowView.setTag(rdTag);
			
		}
		 
		// Get Tag From Existing View
		RowDetailTag rdTag = (RowDetailTag) rowView.getTag();
		
		// Initialize BotDetails
		ModeDetails connModeDetails = this.modeStore[viewPosition];
		
		// Initialize RowHolder
		ModeRowHolder rowHolder = rdTag.rowHolder;
		
		// Set Display Name		
		rowHolder.modeNameView.setText(connModeDetails.MODE_DISPLAY_NAME);
		
		// Set Connection String
		rowHolder.modeDescView.setText(connModeDetails.MODE_DESC);
	    
		// Re-Initialize BotDetails
		rdTag.modeDetails = connModeDetails;
		
		// Set Tag With BotDetails
		rowView.setTag(rdTag);
		
		return rowView;
		
	}

}
