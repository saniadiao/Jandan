package com.sania.jandan;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(Constants.TAG, "MainActivity.onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Log.d(Constants.TAG, "MainActivity.onCreateOptionsMenu");
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		Log.d(Constants.TAG, "MainActivity.onTabSelected");
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		Log.d(Constants.TAG, "MainActivity.onTabReselected");
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		private Fragment[] mFragments;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			Log.d(Constants.TAG, "SectionsPagerAdapter.SectionsPagerAdapter");
			mFragments = new ListFragment[Constants.TAB_NUMBERS];
			for(int i=0;i<Constants.TAB_NUMBERS;i++){
				mFragments[i] = new ListFragment();
			}
		}

		@Override
		public Fragment getItem(int position) {
			Log.d(Constants.TAG, "SectionsPagerAdapter.getItem");
			return mFragments[position];
		}

		@Override
		public int getCount() {
			Log.d(Constants.TAG, "SectionsPagerAdapter.getCount");
			return Constants.TAB_NUMBERS;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Log.d(Constants.TAG, "SectionsPagerAdapter.getPageTitle");
			switch (position) {
			case 0:
				return getString(R.string.title_section_home).toUpperCase();
			case 1:
				return getString(R.string.title_section_fml).toUpperCase();
			case 2:
				return getString(R.string.title_section_ooxx).toUpperCase();
			case 3:
				return getString(R.string.title_section_pic).toUpperCase();
			default:
				return getString(R.string.default_string).toUpperCase();
			}
		}
	}


	public class ListFragment extends Fragment {

		private ListView mListView;
		private ListAdapter mAdapter;
		
		public class ListAdapter extends BaseAdapter{

			public ListAdapter(Context context, int textViewResourceId) {
				// TODO Auto-generated constructor stub
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		
		public ListFragment() {
			Log.d(Constants.TAG, "ListFragment.ListFragment");
			mListView = new ListView(MainActivity.this);
			mAdapter = new ListAdapter(MainActivity.this,R.id.menu_settings);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			Log.d(Constants.TAG, "ListFragment.onCreateView");
			return getCorrectListView();
		}
		private ListView getCorrectListView(){
			if(mListView.getParent() == null){
				return mListView;
			}
			else{
				((ViewGroup) mListView.getParent()).removeView(mListView);
				return mListView;
			}
		}
	}

}
