package zak.barbellweightcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class Plates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView weightInLb = findViewById(R.id.weightInLb);
        TextView weightInKg = findViewById(R.id.weightInKg);
        ListView plateTypes = findViewById(R.id.plateTypes);
        ListView plateNumber = findViewById(R.id.numberOfPlates);

        if(getIntent().getStringExtra("lbWeight") != null) {
            double lbs = Double.parseDouble(getIntent().getStringExtra("lbWeight"));
            double kgs = Double.parseDouble(getIntent().getStringExtra("kgWeight"));
            String[] types = getIntent().getStringArrayExtra("plateTypes");
            String[] plateAmount = getIntent().getStringArrayExtra("plates");

            weightInLb.setText("Weight in lbs: " + lbs);
            weightInKg.setText("Weight in kgs: " + kgs);

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, types);
            plateTypes.setAdapter(adapter1);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plateAmount);
            plateNumber.setAdapter(adapter2);
        }
    }

    public void returnHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
