package sr.unasat.joeyd.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.Dish;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class SyncSpecialsWithDatabaseService extends IntentService {

    private SQLiteDatabase db;
    public static final String REST_API_URL = "https://unasat-2018.000webhostapp.com/specials.json";

    public SyncSpecialsWithDatabaseService() {
        super("SyncSpecialsWithDatabaseService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        callRestAPI();
    }

    private void callRestAPI(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // sync the specials into the database
                        mapJsonToDishObjectAndSaveToDatabase(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("HTTP Request: ", "Can't call REST API");
            }
        });
        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }

    private void mapJsonToDishObjectAndSaveToDatabase(String jsonArray) {
        ObjectMapper mapper = new ObjectMapper();
        List<Dish> specialsList = new ArrayList<>();
        List<Map<String, ?>> specialsArray = null;

        try {
            SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
            db = joeyDDatabaseHelper.getReadableDatabase();
            specialsArray = mapper.readValue(jsonArray, List.class);
            for (Map<String, ?> map : specialsArray) {

                Cursor checkIfSpecialIsAlreadyInserted = db.rawQuery(String.format("select * from dish where name = '%s'", (String) map.get("name")), null);

                if(checkIfSpecialIsAlreadyInserted.getCount() == 0){
                    ContentValues special = new ContentValues();
                    special.put("name", (String) map.get("name"));
                    special.put("price", (String) map.get("price"));
                    special.put("img_id", (Integer) map.get("img_id"));
                    special.put("type", (String) map.get("type"));
                    special.put("day", (String) map.get("day"));

                    long insertedSpecialDish = db.insert("dish", null, special);
                    Log.i("Inserted Special Dish: ", "ID: " + insertedSpecialDish);

                } else {
                    Log.i("Special dish found", "name: " + (String) map.get("name"));
                }
            }
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JSON to Dish: ", "Er is wat fout gegaan bij het parsen van de json data");
        }
    }
}
