<?php 
$connection=mysqli_connect("localhost","root","","truyentranhonline");
			mysqli_set_charset($connection,'UTF8');

			$luuchuong=$_POST['luuchuong'];
			$iduser=$_POST['iduser'];

			$sql="UPDATE `user` SET `luuchuong`='$luuchuong' WHERE `iduser`='$iduser'";
			$query=mysqli_query($connection,$sql);
			if($query){
				echo "tahnhcong";
			}
 ?>