<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <link href = "/templates/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/templates/css/style.css">
</head>

<body class="text-center">

<form class="form-signin" action="/login" method="post">
    <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Sign in</h1>
    <label for="inputName" class="sr-only">Name</label>
    <input type="text" name="name" id="inputName" class="form-control" placeholder="Name" required autofocus>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <br>
    <a class="mt-5 mb-3 text-muted" href="/auth">AUTHORISATION</a>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2018</p>
</form>
</body>
</html>
