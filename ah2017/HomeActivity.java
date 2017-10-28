package damian.ah2017;

/**
 * Created by Damian on 27/10/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import static android.R.attr.fragment;

public class HomeActivity extends Fragment implements
    OnClickListener {

    private static View view;
    private static Button scan, meds, schedule;

    public HomeActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_home, container, false);
        initViews();
        setListeners();

        return view;
    }

    private void setListeners() {
        scan.setOnClickListener(this);
        meds.setOnClickListener(this);
        schedule.setOnClickListener(this);
    }

    private void initViews() {
        scan = (Button)view.findViewById(R.id.scan_button);
        meds = (Button)view.findViewById(R.id.med_button);
        schedule = (Button)view.findViewById(R.id.sched_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan_button:
                Intent intent = new Intent(getActivity(), ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.med_button:
                break;
            case R.id.sched_button:
                break;
        }
    }

    private void submitButtonTask(){

    }

}
