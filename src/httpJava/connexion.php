<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
</head>

<body>
    <?php
    if (isset($_POST["send"])) {
        if (!empty($_POST["password"])) {
            if ($_POST["password"] == "passer") {
                echo "You are connected";
            } else {
                echo "Wrong password";
            }
        } else {
            echo "Please enter a password";
        }
    }
    ?>
    <form method="post" action="">
        <p>Login :</p>
        <input type="text" name="login">
        <p>Password :</p>
        <input type="text" name="password">
        <p><button onclick="" name="send">Valider</button></p>
    </form>
</body>

</html>