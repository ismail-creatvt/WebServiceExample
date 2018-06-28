package com.creatvt.ismail.webserviceexample.activity;

import android.content.DialogInterface;
import android.graphics.Path;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.creatvt.ismail.webserviceexample.R;
import com.creatvt.ismail.webserviceexample.data.DirectionResponse;
import com.creatvt.ismail.webserviceexample.data.ElementsItem;
import com.creatvt.ismail.webserviceexample.service.APIClient;
import com.creatvt.ismail.webserviceexample.service.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteDistance extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextInputEditText txtSource,txtDestination;
    private TextView txtDistance,txtDuration;
    private CardView searchBtn;
    private ProgressBar progress;
    private Spinner travelMode;
    private APIInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_distance);

        txtSource = findViewById(R.id.source_input);
        txtDestination = findViewById(R.id.destination_input);

        txtDistance = findViewById(R.id.distance);
        txtDuration = findViewById(R.id.duration);

        searchBtn = findViewById(R.id.search_button);
        progress =findViewById(R.id.progress_bar);

        travelMode = (Spinner) findViewById(R.id.travel_mode);
        ArrayAdapter<CharSequence> travelModes = ArrayAdapter.createFromResource(this,R.array.travel_modes,android.R.layout.simple_spinner_item);
        travelModes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelMode.setOnItemSelectedListener(this);
        travelMode.setAdapter(travelModes);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(txtSource.getText()))
                {
                    showToast("Enter Source");
                    return;
                }
                if(TextUtils.isEmpty(txtDestination.getText()))
                {
                    showToast("Enter Destination");
                    return;
                }

                progress.setVisibility(View.VISIBLE);

                String source = txtSource.getText().toString().trim();
                String destination = txtDestination.getText().toString().trim();
                String selected = travelMode.getSelectedItem().toString().toLowerCase();
                Log.i("SELECTED ITEM",selected);
                String url = AppConstant.BASE_URL + source + AppConstant.DESTINATION + destination + AppConstant.MODE + selected + AppConstant.ALTERNATIVES + "&key=" + AppConstant.DIRECTION_API_KEY;

                Log.i("URL",url);
                service = APIClient.getClient().create(APIInterface.class);

                Call<DirectionResponse> call = service.getDirectionData(url);

                call.enqueue(new Callback<DirectionResponse>() {
                    @Override
                    public void onResponse(Call<DirectionResponse> call, Response<DirectionResponse> response) {
                        DirectionResponse direction = response.body();

                        ElementsItem element = direction.getRows().get(0).getElements().get(0);

                        try {
                            String distance = element.getDistance().getText();
                            String duration = element.getDuration().getText();

                            txtDistance.setText(distance);
                            txtDuration.setText(duration);

                        }
                        catch(NullPointerException npe){
                            AlertDialog.Builder builder = new AlertDialog.Builder(RouteDistance.this);
                            builder.setTitle("Not Found");
                            builder.setMessage("Please Enter Valid Source and Destination");
                            builder.setCustomTitle(LayoutInflater.from(RouteDistance.this).inflate(R.layout.not_found_title_layout,null,true));
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                        finally {
                            progress.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onFailure(Call<DirectionResponse> call, Throwable t) {

                    }
                });

             }
        });
    }

    public void showToast(String msg){
        Toast toast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,200);
        toast.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        showToast("Please Select Travel Mode...");
    }
}
