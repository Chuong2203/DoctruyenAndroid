package com.example.truyentranhonline.coltroler.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.example.truyentranhonline.util.dialog.Dialog;


public class doctruyen_viewmodel extends AndroidViewModel {
    public doctruyen_viewmodel(@NonNull Application application) {
        super(application);
    }

    public void showDialog(FragmentManager fragmentManager){
        Dialog.getInstance().setTitle("Thông Báo")
                .setMessage("Bạn có muốn thoát khỏi app để mở trình duyệt? ")
                .setTitleAccept("Đồng ý")
                .setTitleCancel("Hủy")
                .show(fragmentManager,"TAG");
    }
}
