package com.example.truyentranhonline.coltroler;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.truyentranhonline.Adapter.adapter_loaitruyen;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.model.model_loaitruyen;
import com.example.truyentranhonline.util.Dangnhap;
import com.example.truyentranhonline.util.sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class loaitruyen extends AppCompatActivity {
    private ListView lisviewloaitruyen;
    private Toolbar toolbardstruyen;
    private adapter_loaitruyen adapter;
    private ArrayList<model_loaitruyen>arrayList;

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaitruyen);
        anhxa();
        setactionbar();
        getdata_loaitruyen();
        sukienclick();
    }

    private void sukienclick() {
        if(arrayList!=null){
            //click vào item loại truyện trong list
            lisviewloaitruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //chuyển màn hình đến chương activity
                    Intent intent=new Intent(getApplicationContext(),chuong.class);
                    intent.putExtra("idloaitruyen",arrayList.get(position-1).getLoaitruyen());
                    Dangnhap.tentruyen=arrayList.get(position-1).getTentruyen();
                    startActivity(intent);

                    //đóng trang
                    finish();
                }
            });
        }

        //update lại data UI
        TextView txt=new TextView(getApplicationContext());
        txt.setText("Bạn Đang Đọc: "+MainActivity.arrayList.get(Dangnhap.vitringuoidung).getLuuchuong());
        txt.setBackgroundColor(Color.BLACK);
        txt.setTextSize(20);
        txt.setTextColor(Color.WHITE);
        txt.setGravity(View.TEXT_ALIGNMENT_CENTER);
        txt.setPadding(50,50,50,50);
        lisviewloaitruyen.addHeaderView(txt);
    }

    private void getdata_loaitruyen() {
        //tạo mới một request đến server(api)
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        //String request (kiểu dữ liệu trả về là string)
        StringRequest stringRequest=new StringRequest(sever.getdataloaitruyen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            Log.d("AAA","Loaitruyen"+response);
                //nếu dữ liệu trả về thành công thì get loại truyện và add vào list
            if(response!=null){
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        arrayList.add(new model_loaitruyen(
                              jsonObject.getInt("id"),
                              jsonObject.getString("tentruyen"),
                              jsonObject.getInt("loaitruyen")
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
                Log.d("AAA","Loai_truyen"+error.toString());
                //ngược lại  thất bại thì show toast(thông báo lỗi)
                sever.showtoast(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }


    //custom toolbar
    private void setactionbar() {
        setSupportActionBar(toolbardstruyen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardstruyen.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        lisviewloaitruyen=findViewById(R.id.lisviewloaitruyen);
        toolbardstruyen=findViewById(R.id.toolbardstruyen);
        arrayList=new ArrayList<>();
        adapter=new adapter_loaitruyen(getApplicationContext(),R.layout.layout_loaitruyen,R.drawable.sach,arrayList);
        adapter.notifyDataSetChanged();
        lisviewloaitruyen.setAdapter(adapter);
    }

    public static MainActivity getInstance(){
        if(instance == null){
            instance = new MainActivity();
        }
        return instance;
    }
}
