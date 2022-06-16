package com.example.truyentranhonline.coltroler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.coltroler.mvvm.doctruyen;
import com.example.truyentranhonline.model.model_getdatauser;
import com.example.truyentranhonline.util.Dangnhap;
import com.example.truyentranhonline.util.sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edttendangnhap,edtpassword;
    private TextView txtdangky;
    private Button btndangnhap;
    public static ArrayList<model_getdatauser>arrayList;

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        dangnhap();
        dangky();
    }

    private void dangky() {
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển màn hình đến đăng ký
            startActivity(new Intent(getApplicationContext(),DangKy.class));
            }
        });
    }

    private void dangnhap() {
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiểm tra đăng nhập
                boolean ckeck=false;
                if(arrayList!=null){
                    for (int i=0;i<arrayList.size();i++){
                        if(edttendangnhap.getText().toString().equals(arrayList.get(i).getUsername())&&edtpassword.getText().toString().equals(arrayList.get(i).getPassword())){
                            ckeck=true;
                            Dangnhap.password=edtpassword.getText().toString();
                            Dangnhap.tennguoidung=edttendangnhap.getText().toString();
                            Dangnhap.vitringuoidung=i;
                        }
                    }
                    if(ckeck==true){
                        sever.showtoast(getApplicationContext(),"Đăng Nhập Thành Công");
                        startActivity(new Intent(getApplicationContext(), doctruyen.class));
                    }else{
                        //có data user nhưng userName và password nhập vào không chính sác
                        sever.showtoast(getApplicationContext(),"Tài khoản không chính sác");
                    }
                }else{
                    //arraylist == null => chưa có userNao (chưa get dc data)
                    sever.showtoast(getApplicationContext(),"Lỗi mạng hiện tại chưa load được dữ liệu");
                }
            }
        });
    }

    //lấy toàn bộ user về
    private void getdatauser() {
        //tạo mới một request đến server(api)
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        //String request (kiểu dữ liệu trả về là string)
        StringRequest stringRequest=new StringRequest(sever.getdatauser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AAA","getdatauser"+response);
                //nếu dữ liệu trả về thành công thì get user và add vào list
            if(response!=null){
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        arrayList.add(new model_getdatauser(
                                jsonObject.getInt("iduser"),
                                jsonObject.getString("username"),
                                jsonObject.getString("password"),
                                jsonObject.getString("sodienthoai"),
                                jsonObject.getString("email"),
                                jsonObject.getString("diachi"),
                                jsonObject.getString("luuchuong")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA",error.toString());
                //ngược lại  thất bại thì show toast(thông báo lỗi)
                sever.showtoast(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    //ánh xạ view
    private void anhxa() {
        txtdangky=findViewById(R.id.txtdangky);
        edttendangnhap=findViewById(R.id.edttendangnhap);
        edtpassword=findViewById(R.id.edtpassword);
        btndangnhap=findViewById(R.id.btndangnhap);
        if(arrayList!=null){

        }else {
            arrayList=new ArrayList<>();
            getdatauser();
        }
    }

    public static MainActivity getInstance(){
        if(instance == null){
            instance = new MainActivity();
        }
        return instance;
    }
}
