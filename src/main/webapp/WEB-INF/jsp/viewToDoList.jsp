<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>View ToDo Item List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    <style>
        a {
            color: white;
        }

        a:hover {
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="p-3">ToDo Item List</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="todo" items="${list}">
            <tr>
                <td>${todo.id}</td>
                <td>${todo.title}</td>
                <td>${todo.date}</td>
                <td>${todo.status}</td>
                <td>
                    <a href="/updateToDoStatus/${todo.id}" class="btn btn-success">Mark Complete</a>
                    <a href="/editToDoItem/${todo.id}" class="btn btn-primary">Edit</a>
                    <a href="/deleteToDoItem/${todo.id}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/addToDoItem" class="btn btn-primary btn-block">Add New ToDo Item</a>
</div>
<script>
    $(document).ready(function () {
        var msg = "${message}";
        if (msg === "Save Success") {
            toastr.success("Item added successfully!!");
        } else if (msg === "Delete Success") {
            toastr.success("Item deleted successfully!!");
        } else if (msg === "Delete Failure") {
            toastr.error("Some error occurred, couldn't delete item");
        } else if (msg === "Edit Success" || msg === "Update Success") {
            toastr.success("Item updated successfully!!");
        } else if (msg === "Update Failure") {
            toastr.error("Some error occurred, couldn't update item");
        }

        toastr.options = {
            "closeButton": true,
            "debug": false,
            "newestOnTop": false,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "preventDuplicates": false,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
    });
</script>
</body>
</html>
