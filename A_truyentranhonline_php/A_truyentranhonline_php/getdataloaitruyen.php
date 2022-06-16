<?php
$connection=mysqli_connect("localhost","root","","truyentranhonline");
 	mysqli_set_charset($connection,'UTF8');
 	$sql="SELECT * FROM `loaitruyen`";
 	$querry=mysqli_query($connection,$sql);
 	
 	/**
 	 * 
 	 */
 	class loaitruyen
 	{
 		
 		function loaitruyen($id,$tentruyen,$loaitruyen)
 		{
 			$this->id=$id;
 			$this->tentruyen=$tentruyen;
 			$this->loaitruyen=$loaitruyen;
 		}
 	}
 	$mangloaitruyen=array();
 	while ($row=mysqli_fetch_assoc($querry)) {
 		array_push($mangloaitruyen, new loaitruyen(
 			$row['id'],
 			$row['tentruyen'],
 			$row['loaitruyen']
 		));
 	}
 	echo json_encode($mangloaitruyen);
?>