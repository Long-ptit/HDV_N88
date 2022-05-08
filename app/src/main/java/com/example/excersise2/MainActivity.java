package com.example.excersise2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.excersise2.model.Customer;
import com.example.excersise2.retrofit.APIInterface;
import com.example.excersise2.retrofit.ApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String PUB_KEY = "pk_test_51KwzuDJMW3MKEexIASee5Um024S0SBHV14WN2b8vBjXZfNezVDe2UEWkabyvdyhQKWt2BakDKZ8Z6wKGENW3VbZ700Mb92lXjY";
    String SECR_KEY = "sk_test_51KwzuDJMW3MKEexITRhkSptoJBfKwN7vMImCYpZGo7IH62NyBPou8OVUT4zo97cUIVNWrvGMRJezjV3fFnTI6uNt00MPnZg5fr";
    Button button;
    PaymentSheet paymentSheet;
    APIInterface apiInterface;
    String client_secret;
    String customer_id;
    String em_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PaymentConfiguration.init(this, PUB_KEY);

        PaymentConfiguration.init(this, PUB_KEY);

        paymentSheet = new PaymentSheet(this, paymentSheetResult -> {
            onPayment(paymentSheetResult);
        });

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        apiInterface.getCustomer("Bearer " + SECR_KEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    customer_id  = jsonObject.getString("id");
                    getEmprieKey();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

    private void onPayment(PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            Log.d("ptit", "success");
        }
    }

    private void getEmprieKey() {
        apiInterface.getEmprieKey(
                "Bearer " + SECR_KEY,
                customer_id,
                "2020-08-27"
        ).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("ptit", "onResponse: " + response.body().get("id"));
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    em_key = jsonObject.getString("id");
                    getClientSecret();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void getClientSecret() {
        apiInterface.createPayment(
                "Bearer " + SECR_KEY,
                customer_id,
                "12000",
                "usd",
                "true"
        ).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    client_secret = jsonObject.getString("client_secret");
                    paymentFlow();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void paymentFlow() {
        paymentSheet.presentWithPaymentIntent(
                client_secret,
                new PaymentSheet.Configuration(
                        "HDV N8",
                        new PaymentSheet.CustomerConfiguration(
                                customer_id,
                                em_key
                        )
                )
        );
    }
}