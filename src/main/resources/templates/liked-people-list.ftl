<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">${name}, users you liked:</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                    <form action="/liked" method="post">
                            <table class="table-users table" border="0">
                                <tbody>
                             <#list cart as cartItem>

                                 <tr>
                                     <td width="10" style="padding: 0">
                                         <label class="form-label" for="${cartItem.user2_id}">
                                         <div class="avatar-img">
                                             <img class="img-circle"
                                                  src="${cartItem.photo}"/>  
                                         </div>
                                         </label>
                                     </td>
                                     <td class="align-middle" style="padding: 0">
                                         <label class="form-label" for="${cartItem.user2_id}">
                                         ${cartItem.name}
                                         </label>
                                     </td>
                                     <td class="align-middle" style="padding: 0">
                                         <label class="form-label" for="${cartItem.user2_id}">
                                         ${cartItem.occupation}
                                         </label>
                                     </td>
                                     <td class="align-middle" style="padding: 0">
                                         <label class="form-label" for="${cartItem.user2_id}">
                                         </label>
                                             <button type="submit" value="${cartItem.user2_id}" name="user2_id" class="user2-id-getter-btn" id="${cartItem.user2_id}">open chat</button>
                                     </td>
                                 </tr>

                             </#list>
                                </tbody>
                            </table>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form action="/liked" class="form-signin form-linked" method="post">
    <a href="/users" class="btn btn-lg btn-primary btn-links">Go to users selection</a>
    <input type="hidden" name="logout" value="logout" class="form-control">
    <button class="btn btn-lg btn-primary btn-links" type="submit">Log out</button>
</form>
</body>
</html>
