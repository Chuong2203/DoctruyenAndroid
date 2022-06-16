package com.example.truyentranhonline.coltroler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.util.sever;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DangKy extends AppCompatActivity {
    private EditText username3,password3,sodienthoai3,email3,diachi3;
    private Button btndangky3,huy;

    private static DangKy instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        //ánh xạ view
        anhxa();

        //listener onclick view
        sukienclick();
    }

    private void sukienclick() {
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // bắt sự kiên onClick view button đăng ký
        btndangky3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(kiemtra()==true){
                dangky();
            }else{
                sever.showtoast(getApplicationContext(),"vui long dien day du thong tin");
            }
            }
        });
    }

    //kiểm tra xem dữ liệu nhập vào edittext có trống hay không
    private boolean kiemtra(){
        if(username3.getText().toString().equals("")||
        password3.getText().toString().equals("")||
        sodienthoai3.getText().toString().equals("")||
        email3.getText().toString().equals("")||
        diachi3.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    //sau khi kiểm tra formart ở trên đúng thì tiến hanhh đăng ký
    private void dangky(){
        //tạo mới một request đến server(api)
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        //String request (kiểu dữ liệu trả về là string)
        StringRequest stringRequest=new StringRequest(Request.Method.POST, sever.insert_taikhoan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            //nếu dữ liệu trả về thành công showToast và chuyển màn hình đến MainActivity(đăng nhập)
            if(response.equals("thanh cong")){
                sever.showtoast(getApplicationContext(),"thanh cong");
                MainActivity.arrayList.clear();
                MainActivity.arrayList=null;
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }else{
                //ngược lại đăng ký thất bại thì show toast(thông báo lỗi)
                sever.showtoast(getApplicationContext(),"loi inset");
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //không connect được với server thì thông báo lỗi
                Log.d("AAA","loi insert_taikhoan"+error.toString());
                sever.showtoast(getApplicationContext(),error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                // đăng ký thì lấy dữ liệu edittext post lên api(file php) trùng với tên cột trong bảng user
                hashMap.put("username",username3.getText().toString());
                hashMap.put("password",password3.getText().toString());
                hashMap.put("sodienthoai",sodienthoai3.getText().toString());
                hashMap.put("email",email3.getText().toString());
                hashMap.put("diachi",diachi3.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    //ánh xạ view
    private void anhxa() {
        username3=findViewById(R.id.username3);
        password3=findViewById(R.id.password3);
        sodienthoai3=findViewById(R.id.sodienthoai3);
        email3=findViewById(R.id.email3);
        diachi3=findViewById(R.id.diachi3);
        btndangky3=findViewById(R.id.btndangky3);
        huy=findViewById(R.id.huy);
    }

    //SIGNLETON PATTERN
    public static DangKy getInstance(){
        if(instance == null){
            instance = new DangKy();
        }
        return instance;
    }
}
