package nl.dynasys.nick.botpws.adapters;

import nl.dynasys.nick.botpws.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BotSelectorListAdapter extends ArrayAdapter<String> {

	private Context aCTX;
	private String[] botNameArray;
	
	public static class RowHolder {
		
		public TextView botNameView;
		public TextView techBotDetails;
		
	}
	
	public BotSelectorListAdapter(Context appContext, String[] botNames) {
		
		// Call Super Constructor
		super(appContext, R.layout.listitem_botselector_botlist, botNames);
		
		// Set Context
		this.aCTX = appContext;
		
		// Store Robot Names
		botNameArray = botNames;
		
	}
	
	@Override
	public View getView(int viewPosition, View convertView, ViewGroup viewParent){
		
		View rowView = convertView;
		
		if(rowView == null){
			
			LayoutInflater layoutInflater = (LayoutInflater) aCTX.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
			rowView = layoutInflater.inflate(R.layout.listitem_botselector_botlist, viewParent, false);
			
			RowHolder rowHolder = new RowHolder();
			
			rowHolder.botNameView = (TextView) rowView.findViewById(R.id.LISTITEM_BOTSELECTOR_BOTNAME);
			rowHolder.techBotDetails = (TextView) rowView.findViewById(R.id.LISTITEM_BOTSELECTOR_TECHBOTNAME);

			rowView.setTag(rowHolder);
			
		}
		
		RowHolder rHolder = (RowHolder) rowView.getTag();
		
	    String botName = botNameArray[viewPosition];
	    
	    rHolder.botNameView.setText(botName);
	    rHolder.techBotDetails.setText("Bluetooth // 2.4 GHz // 5 GHz // WiFi");
	    
		return rowView;
		
	}

}
