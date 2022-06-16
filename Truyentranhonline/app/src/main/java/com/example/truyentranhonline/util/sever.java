package com.example.truyentranhonline.util;

import android.content.Context;
import android.widget.Toast;

public class sever {
    //thay đổi mạng wifi thì lấy ip wifi (ipv4) với cú pháp->cmd->ipconfig->ipv4
    public static String localhost="192.168.1.26";
    public  static String getdatauser="http://"+localhost+"/A_truyentranhonline_php/getdata_user.php";
    public  static String getdataloaitruyen="http://"+localhost+"/A_truyentranhonline_php/getdataloaitruyen.php";
    public  static String get_chuong="http://"+localhost+"/A_truyentranhonline_php/get_chuong.php";
    public  static String getdatachuongtruyen="http://"+localhost+"/A_truyentranhonline_php/getdatachuongtruyen.php";
    public  static String update_luuchuong="http://"+localhost+"/A_truyentranhonline_php/update_luuchuong.php";
    public  static String insert_taikhoan="http://"+localhost+"/A_truyentranhonline_php/insert_taikhoan.php";

    public static void showtoast(Context context,String thongbao){
      Toast.makeText(context,thongbao,Toast.LENGTH_SHORT).show();
  }
}
