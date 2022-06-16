package com.example.truyentranhonline.util.dialog;

import android.support.v4.app.FragmentManager;

public interface IActionDialog <T>{
    T setTitle(String title);
    T setMessage(String title);
    T setTitleAccept(String title);
    T setTitleCancel(String title);

    T showDialog(FragmentManager fragmentManager);

}
