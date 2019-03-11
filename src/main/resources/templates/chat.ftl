<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

    <link href = "templates/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="templates/css/style.css">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row">

        <div class="chat-main col-6 offset-3">
            <form action="/users" class="form-linked" method="post">
                <a href="/liked" class="btn btn-lg btn-light btn-links">Back to liked</a>
                <input type="hidden" name="choice" value="logout" class="form-control">
                <button class="btn btn-lg btn-light btn-links" type="submit">Log out</button>
            </form>
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h6 class="ml-1 mb-0">${name2}</h6>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
                        <p class="arrow-up mb-0">
                            <i class="fa fa-arrow-up text-center pt-1"></i>
                        </p>
                        <i class="fa fa-times hover text-center pt-1"></i>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                        <i class="fa fa-video-camera mr-3"></i>
                        <i class="fa fa-user-plus"></i>
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <ul class="p-0">

                        <#list chat as chatItem>
                        <#if chatItem.id1 == id1>
                        <li class="send-msg float-right mb-2">
                            <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                ${chatItem.message}
                            </p>
                            <span class="receive-msg-time">${chatItem.time}</span>
                        </li>
                        <#else>
                        <li class="receive-msg float-left mb-2">
                            <div class="sender-img">
                                <img src="${chatItem.user1photo}" class="float-left">
                            </div>
                            <div class="receive-msg-desc float-left ml-2">
                                <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                    ${chatItem.message}
                                </p>
                                <span class="receive-msg-time">${name2}, ${chatItem.time}</span>
                            </div>
                        </li>
                        </#if>
                        </#list>

                    </ul>
                </div>
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <form action="/chat" method="post" class="chat-form">
                        <textarea type="text" class="border-0 message-area" id="messageArea" placeholder="Send message" name="message"></textarea>
                        <input type="hidden" value="${id2}" name="id2"/>
                        <input type="submit" style="display: none"/>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

<script>
    $("#messageArea").keypress(function (e) {
        if(e.which == 13 && !e.shiftKey) {
            $(this).closest("form").submit();
            // $(this).val("");
            e.preventDefault();
            return false;
        }
    });
    </script>

</body>
</html>
