<?php
$connectt=mysqli_connect("localhost","root","","truyentranhonline");
			mysqli_set_charset($connectt,'UTF8');
$sql="SELECT*FROM user";
$query=mysqli_query($connectt,$sql);

/**
 * 
 */
class user
{
	
	function user($iduser,$username,$password,$sodienthoai,$email,$diachi,$luuchuong)
	{
		$this->iduser=$iduser;
		$this->username=$username;
		$this->password=$password;
		$this->sodienthoai=$sodienthoai;
		$this->email=$email;
		$this->diachi=$diachi;
		$this->luuchuong=$luuchuong;
	}
}

$manguser=array();
while ($row=mysqli_fetch_assoc($query)) {
	array_push($manguser,new user(
		$row['iduser'],
		$row['username'],
		$row['password'],
		$row['sodienthoai'],
		$row['email'],
		$row['diachi'],
		$row['luuchuong']
	));
}

echo json_encode($manguser);
?>