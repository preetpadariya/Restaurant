<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: System 53
  Date: 15-02-2024
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
          integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body{
            overflow-x:hidden ;
        }
        .main{
            display: flex;
            position: relative;
        }
        .nav{
            background-color: white;
            height: 7vh;
            width: 100vw;
            position: absolute;
            box-shadow: 20vw 0 10px -2px black;
        }
        .heading-text{
            display: flex;
            align-items: center;
            font-size:30px;
            color: black;
            font-weight:bold ;
            width: 28vw;
            justify-content: space-evenly;
        }
        .side-bar {
            margin-left: -25px;
            margin-top: 50px;
            min-height: 100vh;
            width: 17vw;
            background-color: white;
            box-shadow: 0px 0 20px -2px black;
            overflow-y: auto;
            display: flex;
            flex-direction: column;
        }

        .side-bar .list {
            flex-grow: 1;
        }

        .list{
            color: black;
            height: 80%;
        }
        .list ul{
            list-style: none;
            font-size: 1rem;
            font-weight: 600;
            display: flex;
            height: 100%;
            width: 100%;
            flex-direction: column;
            align-items: flex-start;
            padding-left: 0px;
        }

        .list ul a{
            margin-top: -5px;
            width: 100%;
        }

        .list ul a li{
            text-align: left;
            padding-left: 40px;
            width: 100%;
            height: 75px;
            display: flex;
            justify-content: left;
            align-items: center;
        }


        .list ul a li:hover{
            background-color: rgba(100, 149, 237, 0.1);
            color: cadetblue;
        }

        .list ul a li div{
            width: 10%;
        }

        .list ul a li span{
            width: 86%;
            text-align: left;
        }
        .side-bar-link{
            text-decoration: none;
            color: black;
        }
        .side-bar-link:hover{
            color: #5f62a0;
            text-decoration: none;
        }

        .heading{
            font-size: 25px;
            font-weight: bold;
            width: 100%;
            margin: 10px 30px;

        }
        #content{
            width: 80vw;
            height: 100%;
            margin-top: 10vh;
            margin-bottom: 10vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .contentcontainer{
            width: 95%;
            height: 100%;
            border-radius: 20px;
            background-color: #ffffff;
            box-shadow: 0px 0px 10px -3px black;
        }
        .heading{
            font-size: 25px;
            font-weight: bold;
            width: 100%;
            margin: 10px 30px;

        }
        button{
            align-items: center;
        }
        .add-city-container{
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        .add-city-form{
            margin: auto;
            margin-top: 2rem;
            width: 80%;
            margin-bottom: 10vh;
            font-size: large;
            font-weight: 500;
            justify-items: center;
        }
    </style>
    <title>Title</title>
</head>
<body>
<section class="main">
    <nav class="nav">
        <div class="heading-text">
                <span>FOOD ORDER
                </span>
            <i class="fa fa-bars" aria-hidden="true"></i>
        </div>

    </nav>
    <section class="side-bar">
        <div class="list">
            <ul>
                <a href="/restaurant-dashboard" class="side-bar-link"> <li class="lidiv" id="dashboard"><div class="side-bar-image"><i class="fa-solid fa-desktop"></i></div> <span > Dashboard</span></li></a>
                <a class="side-bar-link" style=" background: rgb(167,169,208)"> <li id="manageProduct"><div class="side-bar-image"><i class="fa-solid fa-bag-shopping"></i></div> <span >Manage Product</span></li></a>
                <a href="/restaurant-dashboard/manage-offers" class="side-bar-link"><li id="manageArea"><div class="side-bar-image"><i class="fa-solid fa-briefcase"></i></div> <span >Manage Offers</span></li></a>
                <a href="/restaurant-dashboard/manage-orders" class="side-bar-link"><li id="manageCategory"><div class="side-bar-image"><i class="fa-solid fa-truck"></i></div> <span >Manage Order</span></li></a>
                <a href="/restaurant-dashboard/manage-complaints" class="side-bar-link"><li id="manageSubCategory"><div class="side-bar-image"><i class="fa-solid fa-phone"></i></div> <span >Manage Complaint</span></li></a>
                <a href="/logout" class="side-bar-link"><li id="logout"><div class="side-bar-image"><i class="fa-solid fa-desktop"></i></div> <span >Logout</span></li></a>
            </ul>
        </div>
    </section>
    <div id="content">
        <div class="contentcontainer">
            <div class="add-city-container">
                <div class="heading">Add Product</div>
                <form class="add-city-form form" action="/restaurant-dashboard/product/add-product/add" method="POST" enctype="multipart/form-data">
                    <div class="form-group mb-5 row">
                        <div class="col">
                            <label for="category">Select Category :</label>
                            <select name="category" id="category" class="form-control">
                                <option value="" data-category="" selected>Select option</option>
                                <c:forEach var="category" items="${category}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <label for="subcat">Select SubCategory :</label>
                            <select name="subcat" id="subcat" class="form-control">
                                <option value="" data-category="">Select option</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group mb-5">
                        <label for="ProductName">Product Name</label>
                        <input type="text" class="form-control" id="ProductName" name="ProductName" placeholder="Product Name" required>
                    </div>
                    <div class="form-group mb-5">
                        <label for="productPrice">Product Price</label>
                        <input type="number" class="form-control" id="productPrice" name="productPrice" placeholder="Product Price" required>
                    </div>
                    <div class="form-group mb-5">
                        <label for="productDesc">Product Description</label>
                            <textarea class="form-control"  id="productDesc" name="productDesc" rows="2" placeholder="Description" required></textarea>
                    </div>
                    <div class="form-group mb-5">
                        <label for="photo">Photo</label>
                        <input type="file" class="form-control-file" id="photo" accept="image/jpeg" name="photo">
                    </div>
                    <button type="submit" class="btn btn-success">ADD</button>
                </form>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script>
                    $(document).ready(function() {
                        $('#category').change(function() {
                            var selectedCityId = $(this).val();
                            var categoryId = {"categoryId":selectedCityId}
                            $.ajax({
                                url:"http://localhost:8080/admin-dashboard/add-product/fetchSubCategoryByCategory",
                                data:categoryId,
                                success: function(resp){
                                    console.log(resp)
                                    html='  <option value="" data-category="">Select option</option>'

                                    for(var i=0;i<resp.length;i++){
                                        var json=resp[i];
                                        html+='  <option value="'+json.id+'" data-category="">'+json.name+'</option>'
                                    }
                                    $("#subcat").html(html);
                                }
                            })


                        });
                    });
                </script>
            </div>
        </div>
    </div>
</section>
</body>
</html>
