package zak.barbellweightcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Spinner inputWeightSpinner;
    private Spinner outputWeightSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputWeightSpinner = (Spinner)findViewById(R.id.inputWeightType);
        String[] items = new String[]{"lb", "kg"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        inputWeightSpinner.setAdapter(adapter);

        outputWeightSpinner = (Spinner)findViewById(R.id.outputWeightType);
        String[] items2 = new String[]{"kg", "lb"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        outputWeightSpinner.setAdapter(adapter2);
    }

    public void calculatePlates(View v){

        EditText inputWeight = (EditText)findViewById(R.id.inputWeight);
        String convertWeight = inputWeight.getText().toString();

        if(convertWeight.equals("")){
            return;
        }

        double tempWeight = 0;
        int[] plates;
        String inputWeightType = inputWeightSpinner.getSelectedItem().toString();
        String outputWeightType = outputWeightSpinner.getSelectedItem().toString();


        int weight = Integer.parseInt(convertWeight);
        if(inputWeightType.equals("lb")){

            if(outputWeightType.equals("kg")){

                tempWeight = (weight*0.453592) - 20; //lbs to kgs removing bar
                tempWeight = 2.5*Math.round(tempWeight/2.5);
                plates = new int[]{0, 0, 0, 0, 0, 0, 0};

                while(tempWeight >= 50){
                    plates[0] += 1;
                    tempWeight -= 50;
                }

                while(tempWeight >= 40){
                    plates[1] += 1;
                    tempWeight -= 40;
                }

                while(tempWeight >= 30){
                    plates[2] += 1;
                    tempWeight -= 30;
                }

                while(tempWeight >= 20){
                    plates[3] += 1;
                    tempWeight -= 20;
                }

                while(tempWeight >= 10){
                    plates[4] += 1;
                    tempWeight -= 10;
                }

                while(tempWeight >= 5){
                    plates[5] += 1;
                    tempWeight -= 5;
                }

                while(tempWeight > 0){
                    plates[6] += 1;
                    tempWeight -= 2.5;
                }

            } else {

                tempWeight = weight - 45;
                plates = new int[]{0, 0, 0, 0, 0, 0};

                while(tempWeight >= 90){
                    plates[0] += 1;
                    tempWeight -= 90;
                }

                while(tempWeight >= 70){
                    plates[1] += 1;
                    tempWeight -= 70;
                }

                while(tempWeight >= 50){
                    plates[2] += 1;
                    tempWeight -= 50;
                }

                while(tempWeight >= 20){
                    plates[3] += 1;
                    tempWeight -= 20;
                }

                while(tempWeight >= 10){
                    plates[4] += 1;
                    tempWeight -= 10;
                }

                while(tempWeight >= 5){
                    plates[5] += 1;
                    tempWeight -= 5;
                }

                while(tempWeight > 0){
                    plates[5] += 1;
                    tempWeight -= 5;
                }

            }

        } else {

            if(outputWeightType.equals("kg")){

                tempWeight = weight - 20;
                plates = new int[]{0, 0, 0, 0, 0, 0, 0};

                while(tempWeight >= 50){
                    plates[0] += 1;
                    tempWeight -= 50;
                }

                while(tempWeight >= 40){
                    plates[1] += 1;
                    tempWeight -= 40;
                }

                while(tempWeight >= 30){
                    plates[2] += 1;
                    tempWeight -= 30;
                }

                while(tempWeight >= 20){
                    plates[3] += 1;
                    tempWeight -= 20;
                }

                while(tempWeight >= 10){
                    plates[4] += 1;
                    tempWeight -= 10;
                }

                while(tempWeight >= 5){
                    plates[5] += 1;
                    tempWeight -= 5;
                }

                while(tempWeight > 0){
                    plates[6] += 1;
                    tempWeight -= 2.5;
                }

            } else {

                tempWeight = (weight*2.20462) - 45;
                tempWeight = 5.0*Math.round(tempWeight/5.0);
                plates = new int[]{0, 0, 0, 0, 0, 0};

                while(tempWeight >= 90){
                    plates[0] += 1;
                    tempWeight -= 90;
                }

                while(tempWeight >= 70){
                    plates[1] += 1;
                    tempWeight -= 70;
                }

                while(tempWeight >= 50){
                    plates[2] += 1;
                    tempWeight -= 50;
                }

                while(tempWeight >= 20){
                    plates[3] += 1;
                    tempWeight -= 20;
                }

                while(tempWeight >= 10){
                    plates[4] += 1;
                    tempWeight -= 10;
                }

                while(tempWeight >= 5){
                    plates[5] += 1;
                    tempWeight -= 5;
                }

                while(tempWeight > 0){
                    plates[5] += 1;
                    tempWeight -= 5;
                }
            }
        }


        //open new activity with plateType and list of plates per side
        double kgWeight;
        double lbWeight;
        int intWeight = Integer.parseInt(convertWeight);
        String[] plateTypes;
        if(outputWeightType.equals("lb")) {
            lbWeight = 45 + plates[0]*2*45 + plates[1]*2*35 + plates[2]*2*25 + plates[3]*2*10 + plates[4]*2*5 + plates[5]*2*2.5;
            kgWeight = lbWeight * 0.453592;
        } else{
            kgWeight = 20 + plates[0]*2*25 + plates[1]*2*20 + plates[2]*2*15 + plates[3]*2*10 + plates[4]*2*5 + plates[5]*2*2.5 + plates[6]*2*1.25;
            lbWeight = kgWeight * 2.20462;
        }

        if(outputWeightType.equals("kg")){
            plateTypes = new String[]{"25", "20", "15", "10", "5", "2.5", "1.25"};
        } else {
            plateTypes = new String[]{"45", "35", "25", "10", "5", "2.5"};
        }

        String[] platesStr = new String[plates.length];
        for(int i = 0; i < plates.length; i++) {
            platesStr[i] = "" + plates[i];
        }

        Intent intent = new Intent(this, Plates.class);
        intent.putExtra("kgWeight", ""+kgWeight);
        intent.putExtra("lbWeight", ""+lbWeight);
        intent.putExtra("plateTypes", plateTypes);
        intent.putExtra("plates", platesStr);
        startActivity(intent);
    }
}
