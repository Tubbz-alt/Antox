package com.tox.antox;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.tox.antox.MESSAGE";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /* Check if first time ever running by checking the preferences */
        SharedPreferences pref = getSharedPreferences("main", Context.MODE_PRIVATE);
        
        //For testing WelcomeActivity
        //SharedPreferences.Editor editor = pref.edit();
        //editor.putInt("beenLoaded", 0);
        //editor.apply();
        //End testing
        
        //If beenLoaded is blank, then never been run
        int beenLoaded = pref.getInt("beenLoaded", 0);
        if (beenLoaded == 0)
        {
        	//Launch welcome activity which will run the user through initial settings
        	//and give a brief description of antox
        	Intent intent = new Intent(this, WelcomeActivity.class);
        	startActivity(intent);
        }

        /* Set up friends list using a ListView */
        
        final ListView listView = (ListView) findViewById(R.id.mainListView);
        /* Placeholder values until tox binding is done */
        String values[] = new String[] { 
        		"astonex", 
        		"irungentoo", 
        		"sonOfRa", 
        		"stqism",  
        		"nurupo",
        		"JFreegman",
        		"FullName"
        		};
        
        ArrayList<String> valuesList = new ArrayList<String>();
        valuesList.addAll(Arrays.asList(values));
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
        		 R.layout.main_list_item, values);
        
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				/* Just for show */
				Context context = getApplicationContext();
				CharSequence text = (String) listView.getItemAtPosition(position);
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				
				/* Load chat activity */
			}
        	
        });
        
        getActionBar().setHomeButtonEnabled(true);
    }
    
    public void openSearch()
    {
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
    }
    
    public void addFriend()
    {
    	Intent intent = new Intent(this, AddFriendActivity.class);
    	startActivity(intent);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    	case R.id.action_settings:
    		openSearch();
    		return true;
    	case R.id.add_friend:
    		addFriend();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
