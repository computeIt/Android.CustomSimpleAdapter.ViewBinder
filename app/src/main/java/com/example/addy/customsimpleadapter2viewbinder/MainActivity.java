package com.example.addy.customsimpleadapter2viewbinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final String ATTR_NAME_TEXT = "text";
    final String ATTR_NAME_PB = "pb";
    final String ATTR_NAME_LL = "ll";

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] load = {41, 48, 22, 35, 30, 67, 51, 88};

        ArrayList<Map<String, Object>> data = new ArrayList<>(load.length);
        Map<String, Object> map;

        for (int i = 0; i < load.length; i++) {
            map = new HashMap<>();
            map.put(ATTR_NAME_TEXT, "Day " + (i+1) + ". Load " + load[i] + "%");
            map.put(ATTR_NAME_PB, load[i]);
            map.put(ATTR_NAME_LL, load[i]);
            data.add(map);
        }

        String[] from = {ATTR_NAME_TEXT, ATTR_NAME_PB, ATTR_NAME_LL};
        int[] to = {R.id.textviewLoad, R.id.progressBar, R.id.llLoad};

        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        adapter.setViewBinder(new MyViewBinder());

        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);




    }

    private class MyViewBinder implements SimpleAdapter.ViewBinder {
        int red = getResources().getColor(R.color.red);
        int orange = getResources().getColor(R.color.orange);
        int green = getResources().getColor(R.color.green);

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            int i=0;
            switch (view.getId()){
                case R.id.llLoad:
                    i = ((Integer)data).intValue();
                    if(i<40)
                        view.setBackgroundColor(green);
                    else if(i<70)
                        view.setBackgroundColor(orange);
                    else
                        view.setBackgroundColor(red);
                    return true;
                case R.id.progressBar:
                    i = ((Integer)data).intValue();
                    ((ProgressBar)view).setProgress(i);
                    return true;
            }
            return false;
        }
    }
}
