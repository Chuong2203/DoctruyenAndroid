<?php
$connection=mysqli_connect("localhost","root","","truyentranhonline");
 	mysqli_set_charset($connection,'UTF8');

 	$idloaitruyen=$_POST['idloaitruyen'];
 	$idtruyen=$_POST['idtruyen'];
 	

 	$sql="SELECT * FROM `tentruyen`";
 	$querymoi=mysqli_query($connection,$sql);
 	$biendem=0;
 	while ($row=mysqli_fetch_assoc($querymoi)) {
 		$biendem=$biendem+1;
 	}
 	
 	//-------------------------------------------------------------------------------------------------------------
 	
 	while ($row = mysqli_fetch_assoc($querymoi)) {
 		echo "hello $row";
 	}

 	if($idtruyen<=$biendem){
 	$sql="SELECT * FROM `tentruyen` WHERE `idloaitruyen`='$idloaitruyen' AND `idtruyen`='$idtruyen'";
 	$querry=mysqli_query($connection,$sql);

 	/**
 	 * 
 	 */
 	class tentruyen
 	{
 		
 		function tentruyen($idtruyen,$chương,$tenchuong,$noidung,$idloaitruyen)
 		{
 			$this->idtruyen=$idtruyen;
 			$this->chương=$chương;
 			$this->tenchuong=$tenchuong;
 			$this->noidung=$noidung;
 			$this->idloaitruyen=$idloaitruyen;
 		}
 	}
 	$mangloaitruyen=array();
 	while ($row=mysqli_fetch_assoc($querry)) {
 		array_push($mangloaitruyen, new tentruyen(
 			$row['idtruyen'],
 			$row['chương'],
 			$row['tenchuong'],
 			$row['noidung'],
 			$row['idloaitruyen']
 		));
 	}
 	echo json_encode($mangloaitruyen);
 }
?>