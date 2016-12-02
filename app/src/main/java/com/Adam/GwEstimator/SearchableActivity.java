package com.Adam.GwEstimator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class SearchableActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    handleIntent(getIntent());
	}
	
	//prevent results reloading on rotation
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
	    setIntent(intent);
	    handleIntent(intent);
	}
	
	private void handleIntent(final Intent intent) {
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {	
		  // Do the search and display the results
	    final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Loading...");
	      final WebView webView = (WebView) findViewById(R.id.webView);
	      new Thread()
	      {
	    	  public void run()
	    	  {
	    	      String query = intent.getStringExtra(SearchManager.QUERY);
	    	      String HTMLresult = doSearch(query);
	    	      webView.loadData(HTMLresult, "text/html", "UTF-8");	
	    		  progressDialog.dismiss();	    		 
	    	  }
	      }.start();
	      webView.setVisibility(0);
	    }
	}
	
	public String doSearch(String query) {
		String result = new String();
		String error = "Error during search. Please try again later.";
		try {
			Connection conn = Jsoup.connect("http://argos-soft.net/GwEstimator/index.php?search="+query);
			conn.timeout(10000);
			Document doc = Jsoup.parse(conn.get().html());
			Element elem = doc.select("div#result").first();
			result = elem.html();
		}
		catch(Exception e) {
			return error;
		}
		return result;
	}
	
}
