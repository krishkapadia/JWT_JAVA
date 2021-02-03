<?php 
    session_start();
    $cn=mysqli_connect("localhost","root","root","exam_db");
    if($cn)
        echo "Connectd";


    $data=$cn->query("select * from tblCategory");

    //$data1 = $data->fetch_all(MYSQLI_ASSOC);
    
    if(isset($_POST["submit"]))
    {
        $randNo = rand()*100;
        echo $randNo;
        $tmp_name=$_FILES["img"]["tmp_name"];
        $path="./img";
        $name= $_FILES["img"]["name"];
        echo $_FILES["img"]["name"];
        echo "<br>";
        echo $_FILES["img"]["tmp_name"];
        echo "<br>";
        move_uploaded_file($tmp_name,"$path/$randNo$name");
        echo "DbName : ".$randNo.$name;
    }
    echo $_SESSION["code"];
    //$r1 = $data->fetch_assoc();

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Imgae Upload</title>
</head>
<body>
    <form action="" method="post" enctype="multipart/form-data">
        Select Image: <input type="file" name="img" id="img">
        <input type="text" name="submit" value="<?= $r1["CategoryName"] ?>">
        <input type="submit" name="submit" value="Upload">
    </form>
    <img src="./captch.php"  alt="No Captcha"/>
    <table>
        <tr>
            <td>Category Id</td>
            <td>Category Name</td>
        </tr>
        <?php         

            while($r1 = $data->fetch_assoc())
            {
                ?>
                    <tr>
                        <td><?= $r1["CategoryId"] ?></td>
                        <td><?= $r1["CategoryName"] ?></td>
                    </tr>
                <?php                
            }

            while($r2 = $data->fetch_assoc())
            {
                ?>
                    <tr>
                        <td><?= $r2["CategoryId"] ?></td>
                        <td><?= $r2["CategoryName"] ?></td>
                    </tr>
                <?php                
            }
        
        ?>
    </table>
</body>
</html>