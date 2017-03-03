package se.kth.miracle.universal_convertor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private double convertingMultiplier;
    private double fromMultiplier;
    private String mesureTo;
    private String mesureFrom;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private ArrayList<CharSequence> choices;
    private  ArrayList<CharSequence> settedTempChoicesDistance;
    private ArrayAdapter<CharSequence> adapterTo;
    private int positionFrom = 0;
    private double resultValue;
    private Measure measure;

    public Spinner getSpinnerFrom() {
        return spinnerFrom;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void convert(View view) {
        EditText input = (EditText) findViewById(R.id.input_message);
        EditText result = (EditText) findViewById(R.id.result_message);
        double inputValue = Double.parseDouble(input.getText().toString());


        resultValue = inputValue * fromMultiplier * convertingMultiplier;
        DecimalFormat resultFormat = new DecimalFormat("#.00");

        result.setText(inputValue + " " + mesureFrom + " equals " + resultFormat.format(resultValue) + " " + mesureTo);
        setDefaultSprinerFrom();
        spinnerFrom_handleDistance();
        setDefaultSpinerToDistance();

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.distances :
                if (checked) {
                    measure = Measure.DISTANCE;
                }

                break;
            case R.id.weights :
                if (checked) {
                    measure = Measure.WEIGHT;

                }

                break;
            case R.id.volumes :
                if (checked) {
                    measure = Measure.VOLUME;
                }

                break;
        }
        setDefaultSprinerFrom();
        setDefaultSpinerToDistance();
        spinnerFrom_handleDistance();
    }

    /**
     * Handles the spiner initializing the multipliers:
     * for distances all the multipliers transfer value to 1 meters
     * for volumes all the multipliers transfer value to 1 litre
     * for weight all the multipliers transfer value to 1 gramm
     */
    public void spinnerFrom_handleDistance(){

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mesureFrom = spinnerFrom.getItemAtPosition(position).toString();

                switch (mesureFrom) {
                    //for distances all the multipliers transfer value to 1 meters
                    case "meters" : fromMultiplier = 1;
                        break;
                    case "centimeters" : fromMultiplier = 0.01;
                        break;
                    case "kilometers" : fromMultiplier = 1000;
                        break;
                    case "foots" : fromMultiplier = 0.3048;
                        break;
                    case "inches" : fromMultiplier = 0.0254;
                        break;
                    case "yards" : fromMultiplier = 0.9144;
                        break;
                    case "miles" : fromMultiplier = 1609.34;
                        break;

                    //for weight all the multipliers transfer value to 1 gramm
                    case "kilograms" : fromMultiplier = 1000;
                        break;
                    case "grams" : fromMultiplier = 1;
                        break;
                    case "ounce" : fromMultiplier = 28.35;
                        break;
                    case "pound" : fromMultiplier = 453.6;
                        break;

                    //for volumes all the multipliers transfer value to 1 litre
                    case "litre" : fromMultiplier = 1;
                        break;
                    case "pint" : fromMultiplier = 0.473;
                        break;
                    case "fluid ounce" : fromMultiplier = 0.03;
                        break;
                    case "gallon" : fromMultiplier = 3.78;
                        break;
                    default: fromMultiplier = 1;
                }

                updateSpinerTo(spinnerFrom.getItemAtPosition(position), position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void updateSpinerTo(Object itemAtPosition, int position) {
        for(int i = 0; i < settedTempChoicesDistance.size(); i++ ) {
            if (itemAtPosition.equals(settedTempChoicesDistance.get(i))) {
                settedTempChoicesDistance.remove(i);
                adapterTo = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, settedTempChoicesDistance);
                spinnerTo.setAdapter(adapterTo);
                Toast.makeText(MainActivity.this, "adapter updated", Toast.LENGTH_SHORT).show();
            }
        }
        positionFrom = position;
        spinnerTo_handle();
    }

    public void spinnerTo_handle(){

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mesureTo = spinnerTo.getItemAtPosition(position).toString();
                switch (mesureTo) {
        //for distances all the multipliers transfer value as from 1 meters
                    case "meters" : convertingMultiplier = 1;
                        break;
                    case "centimeters" : convertingMultiplier = 100;
                        break;
                    case "kilometers" : convertingMultiplier = 0.001;
                        break;
                    case "foots" : convertingMultiplier = 3.28084;
                        break;
                    case "inches" : convertingMultiplier = 39.3701;
                        break;
                    case "yards" : convertingMultiplier = 1.09361;
                        break;
                    case "miles" : convertingMultiplier = 0.0006;
                        break;

        //for weight all the multipliers transfer value as from 1 gramm
                    case "kilograms" : convertingMultiplier = 0.001;
                        break;
                    case "grams" : convertingMultiplier = 1;
                        break;
                    case "ounce" : convertingMultiplier = 0.035;
                        break;
                    case "pound" : convertingMultiplier = 0.0022;
                        break;

        //for volumes all the multipliers transfer value as from 1 litre
                    case "litre" : convertingMultiplier = 1;
                        break;
                    case "pint" : convertingMultiplier = 2.11338;
                        break;
                    case "fluid ounce" : convertingMultiplier = 33.814;
                        break;
                    case "gallon" : convertingMultiplier = 0.264;
                        break;
                    default: convertingMultiplier = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "Please select item to continue", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setDefaultSpinerToDistance() {
        String[] defaultChoices;

        switch (measure) {
            case DISTANCE: defaultChoices = getResources().getStringArray(R.array.options_to_convert_to_distances);
                break;
            case WEIGHT:defaultChoices = getResources().getStringArray(R.array.options_to_convert_to_weights);
                break;
            case VOLUME:defaultChoices = getResources().getStringArray(R.array.options_to_convert_to_volumes);
                break;
            default: defaultChoices = getResources().getStringArray(R.array.options_to_convert_to_distances);
        }

        choices = new ArrayList<CharSequence>();
        for (int i = 0; i < defaultChoices.length; i++)
            choices.add(defaultChoices[i]);

        settedTempChoicesDistance = choices;

        spinnerTo = (Spinner) findViewById(R.id.my_spinner_to);
        adapterTo = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item, choices);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTo.setAdapter(adapterTo);
    }

    public void setDefaultSprinerFrom() {
        spinnerFrom = (Spinner) findViewById(R.id.spinner_from);
        ArrayAdapter<String> adapter;

        switch (measure) {
            case DISTANCE:  adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.options_to_convert_from_distances));
                break;
            case WEIGHT: adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.options_to_convert_from_weights));
                break;
            case VOLUME: adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.options_to_convert_from_volumes));
                break;

            default: adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.options_to_convert_from_distances));
        }

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
    }
}