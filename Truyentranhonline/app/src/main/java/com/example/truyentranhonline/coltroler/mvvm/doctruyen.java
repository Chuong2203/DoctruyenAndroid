package com.example.truyentranhonline.coltroler.mvvm;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.truyentranhonline.R;
import com.example.truyentranhonline.coltroler.loaitruyen;
import com.example.truyentranhonline.util.dialog.Dialog;

public class doctruyen extends AppCompatActivity {
    private Button btndoctruyen,btngioithieu;
    private static doctruyen instance;
    private doctruyen_viewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = ViewModelProviders.of(this).get(doctruyen_viewmodel.class);

        setContentView(R.layout.activity_doctruyen);
        anhxa();
        sukienclick();
    }

    private void sukienclick() {
        btndoctruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển màn hình đến loaitruyen activity
                startActivity(new Intent(getApplicationContext(), loaitruyen.class));
            }
        });
    btngioithieu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           viewmodel.showDialog(getSupportFragmentManager());
        }
    });
    }

    //ánh xạ view
    private void anhxa() {
        btndoctruyen=findViewById(R.id.btndoctruyen);
        btngioithieu=findViewById(R.id.btngioithieu);
    }

    public static doctruyen getInstance(){
        if(instance == null){
            instance = new doctruyen();
        }
        return instance;
    }
}
