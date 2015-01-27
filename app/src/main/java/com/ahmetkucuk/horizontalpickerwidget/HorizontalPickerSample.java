package com.ahmetkucuk.horizontalpickerwidget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ahmetkucuk.com.horizontalpickerwidgetlibrary.HorizontalPickerWidget;
import ahmetkucuk.com.horizontalpickerwidgetlibrary.adapter.DefaultHorizontalPickerAdapter;
import ahmetkucuk.com.horizontalpickerwidgetlibrary.adapter.HorizontalPickerAdapter;


public class HorizontalPickerSample extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_picker_sample);

        HorizontalPickerAdapter<String> adapter = new DefaultHorizontalPickerAdapter(new ArrayList<String>(){{add("Item 1"); add("Item 2"); add("Item 3"); add("Item 4");}});
        HorizontalPickerWidget horizontalPickerWidget = (HorizontalPickerWidget)findViewById(R.id.picker);
        horizontalPickerWidget.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horizontal_picker_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
