<?php 
$connection=mysqli_connect("localhost","root","","truyentranhonline");
 	mysqli_set_charset($connection,'UTF8');

 	$username=$_POST['username'];
 	$password=$_POST['password'];
 	$sodienthoai=$_POST['sodienthoai'];
 	$email=$_POST['email'];
 	$diachi=$_POST['diachi'];

 	$sql="INSERT INTO `user`(`username`, `password`, `sodienthoai`, `email`, `diachi`) VALUES ('$username','$password','$sodienthoai','$email','$diachi')";
 	$query=mysqli_query($connection,$sql);
 	if($query){
 		echo "thanh cong";
 	}else{
 		echo "that bai";
 	}
 ?>