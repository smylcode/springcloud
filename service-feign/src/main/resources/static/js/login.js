var token = sessionStorage.getItem("token");
var expiry = sessionStorage.getItem("expiry");
var now = new Date().getTime();
if (expiry != null && now > expiry) {
    console.log("token已过期，清除本地token");
    clearLoginInfo();
}
if (token != null && token.length > 100) {
    window.location = "../index.html";
}
$("#submit_btn").click(function () {
    var $this = $(this);
    $this.attr("disabled", "disabled");
    var parm = $("form").serialize();
    $.post("/feign/web/doLogin", parm, function (re) {
        if (re.code === 0) {
            sessionStorage.setItem('user', re.data.user);
            sessionStorage.setItem('token', re.data.token);
            sessionStorage.setItem('expiry', JSON.stringify(re.data.expiry));
            window.location = "../index.html";
        } else {
            alert(re.des);
        }
        $this.removeAttr("disabled");
    })
});




