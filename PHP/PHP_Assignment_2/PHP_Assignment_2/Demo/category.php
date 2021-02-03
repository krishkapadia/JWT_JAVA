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
        if( isset($_POST['submit']))
        {
            $cname = $_POST['category'];
            $data = $cn->query(" insert into Category_tbl(Cname) values('$cname') ");
            // if($daat)
        }

        if(isset($_GET["id"])){
            $cid = $_GET["id"];
            $data = $cn->query(" delete from Category_tbl where Cid='$cid' ");
        }

    ?>

    <center>
		<form method="post" action="category.php">
			<table>
				<tr>
					<td>Enter Category:</td>
					<td><input type="text" name="category"></td>
				</tr>

                
				
				<tr>
					<td></td>
					<td><input type="submit" name="submit" value="Add"></td>
				</tr>
            </table>
            <table border=1>
                <tr>
                    <th>Category Id</th>
                    <th>Category Name</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <?php 
                
                    $data = $cn->query("select * from Category_tbl");
                    while ($d = $data->fetch_assoc()) {
                        ?>
                        <tr>
                            <td><?= $d['Cid'] ?></td>
                            <td><?= $d['Cname'] ?></td>
                            <td><a href="editCat.php?id=<?= $d['Cid'] ?>&cname=<?= $d['Cname'] ?>" >Edit</a></td>
                            <td><a href="category.php?id=<?= $d['Cid'] ?>" >Delete</a></td>
                        </tr>
                        <?php
                    }

                ?>
            </table>
			
        </form>
	</center>
</body>
</html>