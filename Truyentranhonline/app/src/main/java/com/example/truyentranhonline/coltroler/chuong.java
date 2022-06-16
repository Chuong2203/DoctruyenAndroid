package com.example.truyentranhonline.coltroler;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.truyentranhonline.Adapter.adapter_loaitruyen;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.model.model_loaitruyen;
import com.example.truyentranhonline.util.sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class chuong extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView lisview_chuong;
    private ArrayList<model_loaitruyen>arrayList;
    private adapter_loaitruyen adapter;
    private int idloaitruyen;

    private static chuong instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuong);
        Intent intent=getIntent();
        idloaitruyen=intent.getIntExtra("idloaitruyen",123);
        anhxa();
        setactionbar();
        getdata_chuongtruyen();
        sukienclick();
    }

    private void sukienclick() {
        if(arrayList!=null){
            lisview_chuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(getApplicationContext(),noidungtruyen.class);
                    intent.putExtra("idtruyen",arrayList.get(position).getId());
                    intent.putExtra("idloaitruyen",arrayList.get(position).getLoaitruyen());
                    intent.putExtra("sttcuoi",arrayList.get(arrayList.size()-1).getId());
                    intent.putExtra("tentruyen",arrayList.get(position).getTentruyen());
                    startActivity(intent);
                }
            });
        }
    }

    private void setactionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),loaitruyen.class));
                finish();
            }
        });
    }

    private void getdata_chuongtruyen() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, sever.get_chuong, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AAA","get chuong"+response);
            if(response!=null){
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        arrayList.add(new model_loaitruyen(
                               jsonObject.getInt("idtruyen"),
                               jsonObject.getString("chương"),
                               jsonObject.getInt("idloaitruyen")
                        ));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA","loi get chuong"+error.toString());
                sever.showtoast(getApplicationContext(),error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("idloaitruyen",idloaitruyen+"");
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void anhxa() {
        arrayList=new ArrayList<>();
        toolbar=findViewById(R.id.toolbarchuong);
        lisview_chuong=findViewById(R.id.lisview_chuong);
        adapter=new adapter_loaitruyen(getApplicationContext(),R.layout.layout_loaitruyen,R.drawable.chuong,arrayList);
        adapter.notifyDataSetChanged();
        lisview_chuong.setAdapter(adapter);
    }

    //SIGNLE TONE PATTERN
    public static chuong getInstance(){
        if(instance == null){
            instance = new chuong();
        }
        return instance;
    }
}
