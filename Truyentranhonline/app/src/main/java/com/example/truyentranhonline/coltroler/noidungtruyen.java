package com.example.truyentranhonline.coltroler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.model.model_chuong;
import com.example.truyentranhonline.util.Dangnhap;
import com.example.truyentranhonline.util.sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class noidungtruyen extends AppCompatActivity {
    private int idtruyen, idloai, idcuoi;
    private String tentruyen;
    private TextView txttentruyen1, txtchuong, txttenchuong, txtnoidung;
    private ArrayList<model_chuong> arrayList;
    private ImageView imgnext, imgpree;
    private Toolbar toolbarnoidung;

    private static noidungtruyen instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noidungtruyen);

        //leeys dữ liệu từ activity khác truyền qua
        Intent intent = getIntent();
        idtruyen = intent.getIntExtra("idtruyen", 123);
        idloai = intent.getIntExtra("idloaitruyen", 123);
        idcuoi = intent.getIntExtra("sttcuoi", 123);
        tentruyen = intent.getStringExtra("tentruyen");

        //ánh xạ view
        anhxa();

        //lấy nội dung truyện
        getdatanoidungtruyen(idtruyen);


        gangiatri();

        //bắt sự kiện click view
        sukienclick();

        //handle action bar
        actionbar();
    }

    //custom actionbar
    private void actionbar() {
        setSupportActionBar(toolbarnoidung);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarnoidung.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luuchuong();
            }
        });
    }

    //lưu chương khi người dùng xem truyện tới 1 chương nào đó
    private void luuchuong() {
        //tạo mới một request đến server(api)
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //String request (kiểu dữ liệu trả về là string)
        StringRequest stringRequest = new StringRequest(Request.Method.POST, sever.update_luuchuong, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //nếu dữ liệu trả về thành công showToast đóng màn hình
                if (response.equals("tahnhcong")) {
                    sever.showtoast(getApplicationContext(), "thanh cong");
                    finish();
                } else {
                    //ngược lại đăng ký thất bại thì đóng trang
                    finish();
                }
                //set lưu chương
                MainActivity.arrayList.get(Dangnhap.vitringuoidung).setLuuchuong(txttentruyen1.getText().toString() + "-" + txtchuong.getText().toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA", "update luu chuong:" + error.toString());
                //không connect được với server thì thông báo lỗi
                sever.showtoast(getApplicationContext(), error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // lấy dữ liệu  post lên api(file php) để sử lý lưu trong trên backend(api)
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("iduser", MainActivity.arrayList.get(Dangnhap.vitringuoidung).getIduser() + "");
                hashMap.put("luuchuong", txttentruyen1.getText().toString() + "-" + txtchuong.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    //listener onclick view
    private void sukienclick() {
        imgpree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayList.size() > 0) {
                    getdatanoidungtruyen(idtruyen--);
                }
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayList.size() > 0) {
                    getdatanoidungtruyen(idtruyen++);
                }
            }
        });
    }

    private void gangiatri() {
        if (arrayList != null) {

        }
    }

    //ánh xạ view
    private void anhxa() {
        toolbarnoidung = findViewById(R.id.toolbarnoidung);
        imgnext = findViewById(R.id.imgnext);
        imgpree = findViewById(R.id.imgpree);
        arrayList = new ArrayList<>();
        txtnoidung = findViewById(R.id.txtnoidung);
        txttentruyen1 = findViewById(R.id.txttentruyen1);
        txtchuong = findViewById(R.id.txtchuong);
        txttenchuong = findViewById(R.id.txttenchuong);
    }


    private void getdatanoidungtruyen(final int idtruyennn) {
        //tạo mới một request đến server(api)
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //String request (kiểu dữ liệu trả về là string)
        StringRequest stringRequest = new StringRequest(Request.Method.POST, sever.getdatachuongtruyen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AAA", "noi dung truyen" + response);
                //nếu dữ liệu trả về thành công showToast và update lại UI
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            arrayList.add(new model_chuong(
                                    jsonObject.getInt("idtruyen"),
                                    jsonObject.getString("chương"),
                                    jsonObject.getString("tenchuong"),
                                    jsonObject.getString("noidung"),
                                    jsonObject.getInt("idloaitruyen")
                            ));
                        }
                        txttentruyen1.setText(Dangnhap.tentruyen);
                        txtchuong.setText(arrayList.get(arrayList.size() - 1).getChuong());
                        txttenchuong.setText(arrayList.get(arrayList.size() - 1).getTenchuong());
                        txtnoidung.setText(arrayList.get(arrayList.size() - 1).getNoidung());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    //show toaats
                    sever.showtoast(getApplicationContext(), "Bạn Đã Đọc Đến Chương Cuối rôu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA", "loi get noi dung truyen" + error.toString());
                //không connect được với server thì thông báo lỗi
                sever.showtoast(getApplicationContext(), error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //  lấy dữ liệu  post lên api(file php)
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idloaitruyen", idloai + "");
                hashMap.put("idtruyen", idtruyennn + "");
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static noidungtruyen getInstance(){
        if(instance == null){
            instance = new noidungtruyen();
        }
        return instance;
    }
}
