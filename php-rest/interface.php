<html>
	<head>
	</head>
<body>
<?php
	// If activated gives syntax errors :)
	// ini_set('display_errors', 1);
	// error_reporting(E_ALL);

	// Example put:
	// curl -i -X POST -d "usr=Globuli&pwd=partyhard&action=put&dd=5&mm=8&yy=2014&kto=Curl&sko=Soll&cur=VND&val=43.7&cat=Freizeit&sub=Reisen&dsc=Kuchen" http://www.philippfonteyn.de/csh/interface.php

    // Example get:
    // curl -i -X POST -d "usr=Globuli&pwd=partyhard&action=get&dd=5&mm=8&yy=2018"
    // http://limestone.ming.selfhost.de/csh/interface.php

	// Authorization
	$usr = $_POST["usr"];
	$pwd = $_POST["pwd"];

	if ($usr!=$apiUser or $pwd!=$apiPassword)
	{
	    echo "<h1>403 Forbidden</h1>\n</body>\n</html>\n";
	    header("HTTP/1.0 403 Forbidden");
	    return;
	}

	include("dbcredentials.php");
	$conn = mysqli_connect($servername, $username, $password, $dbname);
	if (!$conn)
	{
	    echo "<h1>503 Service Unavailable</h1>\n</body>\n</html>\n";
	    header("HTTP/1.0 503 Service Unavailable");
	    return;
	}

	// Evaluate Action
	$action = $_POST["action"];

	if ($action != 'get' && $action != 'put')
	{
	    echo "<h1>503 Service Unavailable</h1>\n</body>\n</html>\n";
	    header("HTTP/1.0 503 Service Unavailable");
	    return;
	}

	if($action == 'get'){
		$dd = $_POST["dd"];
		$mm = $_POST["mm"];
		$yy = $_POST["yy"];

		$formattedMM = sprintf("%02d", $mm);
		$formattedDD = sprintf("%02d", $dd);

		echo "Get: day=".$formattedDD." month=".$formattedMM." year=".$yy."<br>\n";
		$sqlGet = 'SELECT * FROM DATEN3 WHERE Datum>=\''.$yy.'-'.$formattedMM.'-'.$formattedDD.'\';';
		$response = mysqli_query($conn, $sqlGet);
 
		if ($response === FALSE) {
		    echo "<h1>500 Internal Server Error</h1>\n";
 		    //echo mysqli_error($conn);
		    header("HTTP/1.0 500 Internal Server Error");  	
		}else{
		    header("HTTP/1.0 200 OK");

		    if (mysqli_num_rows($response) > 0) {	
			echo "<START>\n";
		    	while($row = mysqli_fetch_assoc($response)) {
	 				echo $row["DatumDD"]."."
					.$row["DatumMM"]."."
					.$row["DatumYYYY"].";"
					.$row["Konto"].";"
					.$row["Skonto"].";"
					.$row["Waehrung"].";"
					.str_replace(".", ",", $row["Betrag"]).";"
					.$row["Kategorie"].";"
					.$row["Unterkategorie"].";"
					.$row["Kommentar"].";"
					."\n";
		    	}
			echo "<ENDE>\n";
		     } else {
		    	echo "<NOTHING>\n";
		     }
                }
		mysqli_close($conn);
	}

	if($action == 'put'){
		$dd = $_POST["dd"];
		$mm = $_POST["mm"];
		$yy = $_POST["yy"];
		$formattedMM = sprintf("%02d", $mm);
		$formattedDD = sprintf("%02d", $dd);
		$dat = $yy.'-'.$formattedMM.'-'.$formattedDD;
		$kto = $_POST["kto"];
		$sko = $_POST["sko"];
		$cur = $_POST["cur"];
		$val = $_POST["val"];
		$cat = $_POST["cat"];
		$sub = $_POST["sub"];
		$dsc = $_POST["cmt"];
		
		echo $dat." ".$kto." ".$sko." ".$cur." ".$val." ".$cat." ".$sub." ".$dsc."<br>\n";

	$sql  = "INSERT INTO DATEN3 (DatumDD,DatumMM,DatumYYYY,Datum,Konto,Skonto,Waehrung,Betrag,Kategorie,Unterkategorie,Kommentar)" 
	       ."VALUE ('".$dd."', '".$mm."', '".$yy."', '".$dat."', '".$kto."', '".$sko."', '".$cur."', '".$val."', '".$cat."', '".$sub."', '".$dsc."');";

		$response = mysqli_query($conn, $sql);
		 	     
		if ($response === TRUE) {
		    echo "Data processed.\n";
		    header("HTTP/1.0 200 OK");
		} else {
		    echo "<h1>500 Internal Server Error</h1>\n";
		    //echo mysqli_error($conn);
		    header("HTTP/1.0 500 Internal Server Error");  
		}
	 
		mysqli_close($conn);	
	}

?>
</body>
</html>