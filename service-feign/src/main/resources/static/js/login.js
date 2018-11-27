$("#submit_btn").click(function () {
    var parm = $("form").serialize();
    $.post("/feign/web/doLogin", parm, function (re) {
        if (re.code === 0){
            window.location = "../index.html";
        } else {
            alert(re.message);
        }
    })
});


