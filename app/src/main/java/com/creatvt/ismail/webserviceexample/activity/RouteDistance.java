package com.creatvt.ismail.webserviceexample.activity;

import android.graphics.Path;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.creatvt.ismail.webserviceexample.R;
import com.creatvt.ismail.webserviceexample.data.DirectionResponse;
import com.creatvt.ismail.webserviceexample.service.APIClient;
import com.creatvt.ismail.webserviceexample.service.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteDistance extends AppCompatActivity {

    TextInputEditText txtSource,txtDestination;
    TextView txtDistance,txtDuration;
    CardView searchBtn;
    ProgressBar progress;

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

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.setVisibility(View.VISIBLE);
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

                String source = txtSource.getText().toString().trim();
                String destination = txtDestination.getText().toString().trim();

                String url = AppConstant.BASE_URL + source + AppConstant.DESTINATION + destination + AppConstant.MODE + AppConstant.ALTERNATIVES + "&key=" + AppConstant.DIRECTION_API_KEY;

                APIInterface service = APIClient.getClient().create(APIInterface.class);

                Call<DirectionResponse> call = service.getDirectionData(url);

                call.enqueue(new Callback<DirectionResponse>() {
                    @Override
                    public void onResponse(Call<DirectionResponse> call, Response<DirectionResponse> response) {
                        DirectionResponse direction = response.body();

                        String distance = direction.getRows().get(0).getElements().get(0).getDistance().getText();
                        String duration = direction.getRows().get(0).getElements().get(0).getDuration().getText();

                        txtDistance.setText(distance);
                        txtDuration.setText(duration);

                        progress.setVisibility(View.GONE);
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
}
