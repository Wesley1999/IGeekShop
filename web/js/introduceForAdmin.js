document.write("<script type='text/javascript' src='/js/jquery-1.11.3.min.js'></script>");
$(function () {
    $("body").prepend("<div id=\"header\"></div>")
    $("#header").load("/admin/header.html");
    $("body").append("<div id=\"footer\"></div>")
    $("#footer").load("/admin/footer.html");
});
