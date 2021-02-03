<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php 
    session_start();
        // $cn = mysqli_connect('localhost','root','root','exam_db');
        // if($cn)
        //     echo "Connected";
        $xml=simplexml_load_file("demo.xml");
        if(isset($_POST['submit']))
        {
            if($_SESSION['code']==$_POST['capt'])
            {
                echo "hello";
                
            }
            else {
                echo "Invalid captch";
            }
        }
    
    ?>
        <form method='post'  >
            USername:<input type="text" name="uname"/>
            Pwd:<input type="text" name="pwd"/>
            age:<input type="text" name="role"/>
            <img src="./captch.php"/>
            Captcha:<input type="text" name="capt"/>
            <input type="submit" name="submit"/>
        </form>
    <table>
        <tr>
            <td>username</td>
            <td>password</td>
            <td>role</td>
        </tr>
        <?php 
        
            foreach ($xml->children() as $key) {
                //$cn->query("insert into tbluser values(null,' $key->Username',' $key->Password','$key->Role')");
                ?>
                <tr>
                    <td><?= $key->Username ?></td>
                    <td><?= $key->Password ?></td>
                    <td><?= $key->Role ?></td>
                </tr>
                <?php
            }
        
        ?>
    </table>
</body>
</html>