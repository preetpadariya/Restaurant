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
            margin-top: 50px;
            height: 100%;
            width: 20vw;
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
            width: 60%;
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
        .disabled-link {
            pointer-events: none;
            cursor: default;
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
        .cardcontainer{
            display: flex;
            justify-content: space-between;
        }
        .table {
            padding: 20px;
            margin-top: 25px;
            padding-right: 55px;
            width: 100%;
        }

        .table table {
            width: 100%;
            border-collapse: collapse;
            padding-right: 55px;
            border: 1px solid rgba(28, 28, 28, 0.25);
        }

        .table table tbody tr td {
            padding-top: 10px;
            padding-bottom: 10px;
        }


        .no{
            padding-left: 15px;
            width: 5%;
        }

        .name{
            width: 25%;
        }

        .des{
            width: 60%;
        }

        .handle{
            width: 15%;
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
                <a href="/admin-dashboard" class="side-bar-link"><li><div class="side-bar-image"><i class="fa-solid fa-desktop"></i></div><span > Dashboard</span></li></a>
                <a class="side-bar-link disabled-link" style=" background: rgb(167,169,208)"> <li><div class="side-bar-image"><i class="fa-solid fa-building"></i></div> <span >Manage City</span></li></a>
                <a href="/admin-dashboard/area" class="side-bar-link"><li><div class="side-bar-image"><i class="fa-solid fa-location-dot"></i></div> <span >Manage Area</span></li></a>
                <a href="/admin-dashboard/category-details" class="side-bar-link"><li><div class="side-bar-image"><i class="fa-solid fa-mug-saucer"></i></div> <span >Manage Category</span></li></a>
                <a href="/admin-dashboard/subcategory" class="side-bar-link"><li><div class="side-bar-image"><i class="fa-solid fa-bell-concierge"></i></div> <span >Manage Sub Category</span></li></a>
                <a href="/admin-dashboard/manage-restaurant" class="side-bar-link"><li><div class="side-bar-image"><i class="fa-solid fa-spoon"></i></div> <span>Manage Restaurant</span></li></a>
                <a href="/admin-dashboard/offers" class="side-bar-link"><li><div class="side-bar-image"><i class="fa-solid fa-bag-shopping"></i></div> <span >Manage Offers</span></li></a>
                <a href="/admin-dashboard/complaints" class="side-bar-link"><li><div class="side-bar-image"><i class="fa-solid fa-phone-flip"></i></div> <span >Manage Complaint</span></li></a>
                <a href="/admin-dashboard" class="side-bar-link"><li><div class="side-bar-image"><i class="fa-solid fa-thumbs-up"></i></div> <span >Manage Feedback</span></li></a>
                <a href="/logout" class="side-bar-link"><li><div class="side-bar-image"><i class="fa-solid fa-desktop"></i></div> <span >Logout</span></li></a>
            </ul>
        </div>
    </section>
    <div id="content">
        <div class="contentcontainer">
            <div class="city-details-data">
                <div class="heading">
                    City Details
                </div>
                <div class="cardcontainer">
                    <div class="buttons m-4">
                        <button class="btn btn-secondary" type="button">Copy</button>
                        <button class="btn btn-primary" type="button">CSV</button>
                        <button class="btn btn-success" type="button">Excel</button>
                        <button class="btn btn-danger" type="button">PDF</button>
                        <button class="btn btn-warning" type="button">Print</button>
                    </div>
                    <div class="search-bar m-4 ">
                        <form class="form-inline my-2 my-lg-0 d-flex  gap-3">
                            <input aria-label="Search" class="form-control mr-2 " placeholder="Search" type="search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>
                    </div>
                </div>
                <div class="m-4">
                    <a href="/admin-dashboard/add-city"><div class="btn btn-info">Add city</div></a>
                </div>
                <div class="table">
                    <table>
                        <thead>
                        <tr>
                            <th class="no" scope="col">No</th>
                            <th class="name" scope="col">City Name</th>
                            <th class="des" scope="col">City Description</th>
                            <th class="handle" scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${city}" var="cityItem" varStatus="loop">
                            <tr>
                                <td style="padding-left: 15px;">${loop.index + 1}</td>
                                <td>${cityItem.name}</td>
                                <td>${cityItem.description}</td>
                                <td>
                                    <a href="/admin-dashboard/city/${cityItem.id}/edit" class="btn" type="button" style="background: #b1b4dc">
                                        <i class="fa-solid fa-pen-to-square"></i>
                                    </a>
                                    <a href="/admin-dashboard/city/${cityItem.id}/delete" class="btn btn-danger" type="button">
                                        <i class="fa-regular fa-trash-can"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="page-changer">
                        <ul class="pagination justify-content-end">
                            <c:if test="${pageNo > 0}">
                                <li class="page-item">
                                    <a class="page-link" href="/cities?pageNo=${pageNo - 1}&pageSize=${pageSize}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach begin="0" end="${totalPages-1}" var="i">
                                <li class="page-item ${i == pageNo ? 'active' : ''}">
                                    <c:choose>
                                        <c:when test="${i == pageNo}">
                                            <span class="page-link">${i+1}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="page-link">${i+1}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                            <c:if test="${pageNo < totalPages - 1 && totalPages > 0}">
                                <li class="page-item">
                                    <a class="page-link" href="/cities?pageNo=${pageNo + 1}&pageSize=${pageSize}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>



                </div>
            </div>

        </div>

    </div>
</section>
</body>
</html>
