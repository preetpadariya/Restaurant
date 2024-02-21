<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            background-image: url("../images/Loginbg.jpg");
            background-size: cover;
            margin: 0;
            padding: 0;
        }

        .loginContainer {
            height: 100vh;
            width: 100vw;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        .formClass {
            background-color: white;
            height: auto;
            width: 30vw;
            max-width: 400px;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            border-radius: 5px;
            box-shadow: 2px 2px 10px 2px black;
            margin-top: 50px;
        }

        .formClass img {
            margin-bottom: 20px;
            max-width: 50%;
        }

        .formClass label {
            margin-bottom: 5px;
        }

        .formClass .input-group {
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: flex-start; /* Align items to the start of the flex container */
            margin-bottom: 10px;
        }

        .formClass .input-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            text-align: left;
        }

        .formClass input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            border-radius: 4px;
        }

        .formClass input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .formClass span {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<section class="loginContainer">
    <form class="formClass" id="login-page" method="POST">

        <img src="../images/restaurant-image.png" alt="">
        <div class="input-group w-75">
            <label for="username">Email</label>
            <input type="email" class="form-control" name="username" id="username">
        </div>
        <div class="input-group w-75">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="row justify-content-center mt-4 w-75 mb-4">
            <input type="submit" value="Login" class="btn btn-primary">
        </div>

        <span>Register as a restaurant <a href="/register">Create One</a></span>
    </form>
</section>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script>
    document.getElementById("togglePassword").addEventListener("click", function () {
        var passwordInput = document.getElementById("password");
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
        } else {
            passwordInput.type = "password";
        }
    });
</script>
</body>
</html>
