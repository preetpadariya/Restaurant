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
        .side-bar{
            margin-left: -25px;
            margin-top: 50px;
            height: 100vh;
            width: 17vw;
            background-color: white;
            box-shadow: 0px 0 20px -2px black;
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


        #content{
            width: 80vw;
            height: 94vh;
            margin-top: 7vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .contentcontainer{
            width: 95%;
            height: 95%;
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
        #content{
            width: 80vw;
            height: 94vh;
            margin-top: 7vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .contentcontainer{
            width: 95%;
            height: 95%;
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
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        .add-city-form{
            margin: auto;
            margin-top: 2rem;
            width: 80%;
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
                <a href="/restaurant-dashboard/product" class="side-bar-link"> <li id="manageProduct"><div class="side-bar-image"><i class="fa-solid fa-bag-shopping"></i></div> <span >Manage Product</span></li></a>
                <a href="/restaurant-dashboard/manage-offers" class="side-bar-link"><li id="manageArea"><div class="side-bar-image"><i class="fa-solid fa-briefcase"></i></div> <span >Manage Offers</span></li></a>
                <a href="/restaurant-dashboard/manage-orders" class="side-bar-link"><li id="manageCategory"><div class="side-bar-image"><i class="fa-solid fa-truck"></i></div> <span >Manage Order</span></li></a>
                <a  class="side-bar-link" style=" background: rgb(167,169,208)"><li id="manageSubCategory"><div class="side-bar-image"><i class="fa-solid fa-phone"></i></div> <span >Manage Complaint</span></li></a>
                <a href="/logout" class="side-bar-link"><li id="logout"><div class="side-bar-image"><i class="fa-solid fa-desktop"></i></div> <span >Logout</span></li></a>
            </ul>
        </div>
    </section>
    <div id="content">
        <div class="contentcontainer">
            <div class="add-city-container">
                <div class="heading">Add Complaints</div>
                <form onsubmit="return checkFileSize();" class="add-city-form form" action="/restaurant-dashboard/manage-complaints/add-complaints/add" method="POST" enctype="multipart/form-data">
                    <div class="form-group mb-5">
                        <label for="complaintSubject">Complaint Subject</label>
                        <input type="text" class="form-control" id="complaintSubject" name="complaintSubject" placeholder="Complaint Subject" required>
                    </div>
                    <div class="form-group mb-5">
                        <label for="complaintDesc">Complaint Description</label>
                        <textarea class="form-control"  id="complaintDesc" name="complaintDesc" rows="2" placeholder="Description" required></textarea>
                    </div>
                    <div class="form-group mb-5">
                        <label for="pdf">Attachment</label>
                        <input type="file" class="form-control" id="pdf" accept="application/pdf" name="pdf" multiple />
                    </div>
                    <button type="submit" class="btn btn-success">ADD</button>
                </form>
            </div>
            <script>
                function checkFileSize() {
                    var input = document.getElementById('photo');
                    if (input.files.length > 0) {
                        var fileSize = input.files[0].size;
                        var maxSize = 5 * 1024 * 1024;
                        if (fileSize > maxSize) {
                            alert('File size exceeds the maximum limit of 5 MB.');
                            return false;
                        }
                    }
                    return true;
                }
            </script>
        </div>
    </div>
</section>
</body>
</html>
