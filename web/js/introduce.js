$(function () {
    $("body").prepend("<div id=\"header\"></div>")
    $("#header").load("/header.html");
    $("body").append("<div id=\"footer\"></div>")
    $("#footer").load("/footer.html");
});
