<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <#--<link href="../css/bootstrap.min.css" rel="stylesheet">-->
    <link href = "/templates/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/templates/css/style.css">
</head>
<body style="background-color: #f5f5f5;">
<div class="container">
    <div class="row">
        <div class="col-4 offset-4">
    <form action="/users" class="form-linked" method="post">
        <a href="/liked" class="btn btn-lg btn-dark btn-links">Go to my liked</a>
        <input type="hidden" name="choice" value="logout" class="form-control">
        <button class="btn btn-lg btn-dark btn-links" type="submit">Log out</button>
    </form>
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
                    <img src="${photo}" alt="No image to display" class="mx-auto rounded-circle img-fluid">
                    <h3 class="mb-0 text-truncated">${name1}</h3>
                    <br>
                </div>
                <div class="col-12 col-lg-6" ${style}>
                    <form action="/users" method="post">
                        <input type="hidden" value="dislike" name="choice">
                        <input type="hidden" value="${id}" name="id">
                    <button type="submit" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                    </form>
                </div>
                <div class="col-12 col-lg-6" ${style}>
                    <form action="/users" method="post">
                        <input type="hidden" value="like" name="choice">
                        <input type="hidden" value="${id}" name="id">
                    <button type="submit" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                    </form>
                </div>
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>
    </div>
</div>
</body>
</html>
