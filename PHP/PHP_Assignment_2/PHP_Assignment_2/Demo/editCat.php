<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category</title>
</head>
<body>

    <?php 
    
        include_once('../connection.php');
        

        if(isset($_GET["id"])){
            $cid = $_GET["id"];
            $cname = $_GET["cname"];
            //$data = $cn->query(" delete from Category_tbl where Cid='$cid' ");
        }

        if(isset($_POST['submit']))
        {
            $data = $cn->query("update Category_tbl set Cname='$cname' where Cid='$id' ");
            header("Location: category.php");
        }

    ?>

    <center>
    <h1>Edit Category</h1>
		<form method="post" action="category.php">
			<table>
				<tr>
					<td>Enter Category:</td>
					<td><input type="text" value="<?= $cname ?>" name="category"></td>
				</tr>
				
				<tr>
					<td></td>
					<td><input type="submit" name="submit" value="Edit"></td>
				</tr>
            </table>
            
			
        </form>
	</center>
</body>
</html>