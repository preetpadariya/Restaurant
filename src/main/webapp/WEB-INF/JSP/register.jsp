<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Registration</title>
    <style>
        body {
            background-image: url("../images/Loginbg.jpg");
            background-size: cover;
            margin: 0;
            padding: 0;
        }

        .registrationContainer {
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
            width: 500vw;
            max-width: 900px;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            border-radius: 5px;
            box-shadow: 2px 2px 10px 2px black;
            margin-top: 50px;
        }

        .formClass img {
            width: 20%;
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

        .formClass .input-group input, .formClass .input-group textarea, select {
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
<section class="registrationContainer">
    <form class="formClass" id="registration-form" action="/register/add" method="post" enctype="multipart/form-data">
        <img src="../images/restaurant-image.png" alt="">
        <div class="input-group w-75">
            <label for="resturantname">Resturant Name</label>
            <input type="text" class="form-control" name="resturantname" id="resturantname" required>
        </div>
        <div class="input-group row">
            <div class="input-group col-md-6">
                <label for="restName">Email</label>
                <input type="email" class="form-control" name="restName" id="restName" required>
            </div>
            <div class="input-group col-md-6">
                <label for="contactnumber">Contact Number</label>
                <input type="tel" class="form-control" name="contactnumber" id="contactnumber" required>
            </div>
        </div>
        <div class="input-group w-75">
            <label for="address">Address</label>
            <textarea class="form-control"  id="address" name="address" rows="2" placeholder="Address" required></textarea>
        </div>
        <div class="input-group w-75">
            <label for="city">Select City:</label>
            <select name="city" id="city">
                <option value="" data-city="" selected>Select option</option>
                <c:forEach var="city" items="${cities}">
                    <option value="${city.id}">${city.name}</option>
                </c:forEach>
            </select>

            <label for="area">Select Area:</label>
            <select name="area" id="area">
                <option value="" data-city="">Select option</option>
            </select>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
        <script>
            $(document).ready(function() {
                $.validator.addMethod("checklower", function (value, element) {
                    return /[a-z]/.test(value);
                }, "At least one lowercase letter is required");

                $.validator.addMethod("checkupper", function (value, element) {
                    return /[A-Z]/.test(value);
                }, "At least one uppercase letter is required");

                $.validator.addMethod("checksc", function (value, element) {
                    return /[!@#$%^&*(),.?":{}|<>]/.test(value);
                }, "At least one special character is required");

                $.validator.addMethod("checkdigit", function (value, element) {
                    return /[0-9]/.test(value);
                }, "At least one digit is required");
                $.validator.addMethod("checkemail", function(value, element) {
                    return /^[\w-]+(?:\.[\w-]+)*@(?:[\w-]+\.)+[a-zA-Z]{2,7}$/.test(value);
                }, "Please enter a valid email address");

                $("#registration-form").validate({
                    rules: {
                        resturantname: {
                            required: true
                        },
                        restName: {
                            required: true,
                            email: true,
                            minlength: 6,
                            checklower: true,
                            checkupper: true,
                            checksc: true,
                            checkdigit: true
                        },
                        contactnumber: {
                            required: true,
                            minlength: 10,
                            maxlength: 10
                        },
                        address: {
                            required: true
                        },
                        restPassword: {
                            required: true,
                            minlength: 8
                        }
                    },
                    messages: {
                        resturantname: "Please enter restaurant name",
                        restName: {
                            required: "Please enter email address",
                            email: "Please enter a valid email address"
                        },
                        contactnumber: {
                            required: "Please enter contact number",
                            minlength: "Contact number must be 10 digits long",
                            maxlength: "Contact number must be 10 digits long"
                        },
                        address: "Please enter address",
                        restPassword: {
                            required: "Please enter password",
                            minlength: "Password must be at least 8 characters long"
                        }
                    },
                    submitHandler: function (form) {
                        var json = {
                            "restaurantName": $("#restName").val(),
                            "password": $("#restPassword").val(),
                            "email": $("#restEmail").val(),
                            "contactNo": $("#restContact").val(),
                            "address": $("#restAddress").val(),
                            "cityId": $("#city").val(),
                            "areaId": $("#area").val(),
                        }
                        console.log(json)
                        $.ajax({
                            url: "http://localhost:8080/register/add",
                            data: JSON.stringify(json),
                            type: "post",
                            contentType: "application/json",
                            success: function (resp) {
                                alert(resp.responseText)
                                location.reload();
                            }, error: function (resp) {
                                alert(resp.responseText);
                            }
                        })
                    }
                })
            })
        </script>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script>
                $(document).ready(function() {
                    $('#city').change(function() {
                        var selectedCityId = $(this).val();
                        var cityId = {"cityId":selectedCityId}
                        console.log(selectedCityId)
                        $.ajax({
                            url:"http://localhost:8080/register/fetchAreaByCity",
                            data:cityId,
                            success: function(resp){
                                console.log(resp)

                                html='  <option value="" data-city="">Select option</option>'

                                for(var i=0;i<resp.length;i++){
                                    var json=resp[i];
                                    html+='  <option value="'+json.id+'" data-city="">'+json.name+'</option>'
                                }
                                $("#area").html(html);
                            }
                        })
                    });
                });
            </script>



        <div class="input-group w-75">
            <label for="restPassword">Password</label>
            <input type="password" class="form-control" id="restPassword" name="restPassword">
        </div>
        <div class="row justify-content-center mt-4 w-75 mb-4">
            <input type="submit" value="Register" class="btn btn-primary">
        </div>
    </form>
</section>

</body>
</html>
