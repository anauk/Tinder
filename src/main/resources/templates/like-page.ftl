<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="../static/css/style.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
                    <img src="${user_photo}" alt="no image to display" class="mx-auto rounded-circle img-fluid" >
                    <h3 class="mb-0 text-truncated">${user_name}</h3>
                    <br>
                </div>
                <div class="col-12 col-lg-6" ${style}>
                    <form action="/users" method="post">
                        <input type="hidden" value="dislike" name="choice">
                        <input type="hidden" value="${user_id}" name="user_id">
                        <button type="summit" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                    </form>
                </div>
                <div class="col-12 col-lg-6" ${style}>
                    <form action="/users" method="post">
                        <input type="hidden" value="like" name="choice">
                        <input type="hidden" value="${user_id}" name="user_id">
                        <button type="summit" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                    </form>
                </div>
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

<form action="/users" class="form-signin form-linked">
    <a href="/liked" class="btn btn-lg btn-primary btn-links">Go to my liked</a>
    <input type="hidden" name="logout" class="form-control">
    <button class="btn btn-lg btn-primary btn-links" type="submit">Log out</button>
</form>


</body>
</html>
