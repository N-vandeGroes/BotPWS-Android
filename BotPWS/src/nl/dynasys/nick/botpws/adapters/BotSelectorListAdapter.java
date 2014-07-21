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
	private BotDetails[] botDetailStore;
	
	public static class RowHolder {
		
		public TextView botNameView;
		public TextView techBotDetails;
		
	}
	
	public static class BotDetails {
		
		public String BOT_DISPLAY_NAME;
		
		// CONNTYPE : BlueTooth Antenna
		public boolean BOT_CONN_BLUETOOTH;
		public String BOT_BLUETOOTH_MAC;
		
		// CONNTYPE : Wi-Fi Antenna
		public boolean BOT_CONN_WIFI;
		public String BOT_WIFI_SSID;
		public String BOT_WIFI_MAC;
		
		// CONNTYPE : 433MHz Antenna
		public boolean BOT_CONN_433;
		public String BOT_433_MAC;
		
		// CONNTYPE : 2.4GHz Antenna
		public boolean BOT_CONN_2400;
		public String BOT_2400_MAC;
		
	}
	
	public static class RowDetailTag {
	
		public RowHolder rowHolder;
		public BotDetails botDetails;
	
	}
	
	public BotSelectorListAdapter(Context appContext, BotDetails[] discoveredBots) {
		
		// Call Super Constructor
		super(appContext, R.layout.listitem_botselector_botlist);
		
		// Set Context
		this.aCTX = appContext;
		
		// Store Robot Names
		this.botDetailStore = discoveredBots;
		
	}
	
	@Override
	public View getView(int viewPosition, View convertView, ViewGroup viewParent){
		
		View rowView = convertView;
		
		if(rowView == null){
			
			// Create New RowView
			LayoutInflater layoutInflater = (LayoutInflater) aCTX.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
			rowView = layoutInflater.inflate(R.layout.listitem_botselector_botlist, viewParent, false);
			
			// Initialize RowHolder
			RowHolder rowHolder = new RowHolder();
			
			rowHolder.botNameView = (TextView) rowView.findViewById(R.id.LISTITEM_BOTSELECTOR_BOTNAME);
			rowHolder.techBotDetails = (TextView) rowView.findViewById(R.id.LISTITEM_BOTSELECTOR_TECHBOTNAME);

			// Initialize BotDetails
			BotDetails botDetails = new BotDetails();
						
			// Initialize RowDetailTag
			RowDetailTag rdTag = new RowDetailTag();
			rdTag.botDetails = botDetails;
			rdTag.rowHolder = rowHolder;
			
			// Set Tag
			rowView.setTag(rdTag);
			
		}
		
		// Get Tag From Existing View
		RowDetailTag rdTag = (RowDetailTag) rowView.getTag();
		
		// Initialize BotDetails
		BotDetails botDetails = this.botDetailStore[viewPosition];
		
		// Initialize RowHolder
		RowHolder rowHolder = rdTag.rowHolder;
		
		// Set Display Name		
		rowHolder.botNameView.setText(botDetails.BOT_DISPLAY_NAME);
		
		// Set Connectivity Methods
		String botConnMethods = null;
		
		// Create Connection String
		if(botDetails.BOT_CONN_BLUETOOTH){
			
			botConnMethods = botConnMethods + "Bluetooth //";
			
		}
		
		if(botDetails.BOT_CONN_433){
			
			botConnMethods = botConnMethods + " 433MHz //";
			
		}
		
		if(botDetails.BOT_CONN_2400){
			
			botConnMethods = botConnMethods + " 2.4GHz //";
			
		}
		
		if(botDetails.BOT_CONN_WIFI){
			
			botConnMethods = botConnMethods + " Wi-Fi";
			
		}
		
		// Set Connection String
		rowHolder.techBotDetails.setText(botConnMethods);
	    
		// Re-Initialize BotDetails
		rdTag.botDetails = botDetails;
		
		// Set Tag With BotDetails
		rowView.setTag(rdTag);
		
		return rowView;
		
	}

}
