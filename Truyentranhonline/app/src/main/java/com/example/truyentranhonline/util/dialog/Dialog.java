package com.example.truyentranhonline.util.dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.truyentranhonline.R;
import com.example.truyentranhonline.coltroler.tongquan;

public class Dialog extends DialogFragment implements  IActionDialog<Dialog>{
    private static Dialog instance;
    private DisplayUIDialog displayUIDialog = new DisplayUIDialog();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = view.findViewById(R.id.txtTitle);
        TextView message = view.findViewById(R.id.txtMessage);
        Button titleCancel = view.findViewById(R.id.btnCancel);
        Button titleAccept = view.findViewById(R.id.btnAccept);

        title.setText(displayUIDialog.getTitle());
        message.setText(displayUIDialog.getMessage());
        titleCancel.setText(displayUIDialog.getTitleCancel());
        titleAccept.setText(displayUIDialog.getTitleAccept());

        view.findViewById(R.id.btnAccept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyển màn hình đến tongquan activity
                startActivity(new Intent(getContext(), tongquan.class));
            }
        });

        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @SuppressLint("ValidFragment")
    private Dialog(){ }

    public static Dialog getInstance(){
        if(instance == null){
            instance = new Dialog();
        }
        return instance;
    }

    @Override
    public Dialog setTitle(String title) {
        displayUIDialog.setTitle(title);
        return this;
    }

    @Override
    public Dialog setMessage(String title) {
        displayUIDialog.setMessage(title);
        return this;
    }

    @Override
    public Dialog setTitleAccept(String title) {
        displayUIDialog.setTitleAccept(title);
        return this;
    }

    @Override
    public Dialog setTitleCancel(String title) {
        displayUIDialog.setTitleCancel(title);
        return this;
    }

    @Override
    public Dialog showDialog(FragmentManager fragmentManager) {
        show(fragmentManager,"TAG");
        return this;
    }
}
