<?php
$connection=mysqli_connect("localhost","root","","truyentranhonline");
 	mysqli_set_charset($connection,'UTF8');

 	$idloaitruyen=$_POST['idloaitruyen'];
 	// $idloaitruyen=1;
 	$sql="SELECT `chương`,`idloaitruyen`,`idtruyen` FROM`tentruyen` WHERE `idloaitruyen`=$idloaitruyen";
 	$querry=mysqli_query($connection,$sql);

 	/**
 	 * 
 	 */
 	class tentruyen
 	{
 		
 		function tentruyen($idtruyen,$chương,$idloaitruyen)
 		{
 			$this->idtruyen=$idtruyen;
 			$this->chương=$chương;
 			$this->idloaitruyen=$idloaitruyen;
 		}
 	}
 	$mangloaitruyen=array();
 	while ($row=mysqli_fetch_assoc($querry)) {
 		array_push($mangloaitruyen, new tentruyen(
 			$row['idtruyen'],
 			$row['chương'],
 			$row['idloaitruyen']
 		));
 	}
 	echo json_encode($mangloaitruyen);

?>