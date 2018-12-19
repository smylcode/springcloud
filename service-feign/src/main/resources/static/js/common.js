var form;
var layer;
var laytpl;
layer = layui.layer;
laytpl = layui.laytpl;
form = layui.form;
var element = layui.element;
var laypage = layui.laypage;
var table = layui.table;
var flow = layui.flow;
var laydate = layui.laydate;
var loadingIndex;
var contextPath = '';
$.ajaxSetup({
    beforeSend:function (request) {
        loadingIndex = layer.load(3, {time:60*1000});
        request.setRequestHeader("Authorization", sessionStorage.getItem("token"));
    },
    complete:function () {
        layer.close(loadingIndex)
    }
});
/**
 * 检查用户登录信息
 */
function checkLogin(isOnLoginPage) {
    var token = sessionStorage.getItem("token");
    var expiry = sessionStorage.getItem("expiry");
    console.log("checkLogin 中 expiry is :" + expiry);
    var now = new Date().getTime();
    if (expiry != null && now > expiry) {
        layer.msg("系统登录超时");
        console.log("token已过期，清除本地token");
        clearLoginInfo();
        setTimeout(function () {
            window.location.href = "../login.html";
        }, 2000);
    }
    if (isOnLoginPage) {
        if (token != null && token.length > 100) {
            window.location = "../index.html";
        }
    } else { // 如果是在系统内部页面则处理
        if (token == null || token === "") {
            layer.msg("您的登录信息已失效，请重新登录！");
            console.log("系统未授权访问，清除本地token");
            clearLoginInfo();
            setTimeout(function () {
                window.location.href = "../login.html";
            }, 2000);
        }
    }
}

function clearLoginInfo() {
    console.log("清除本地登录信息，并刷新页面");
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("expiry");
    sessionStorage.clear();
}

checkLogin(false);

/**
 * 判断该对象是否为空
 * @param obj
 * @returns {boolean}
 */
function isEmpty(obj) {
    if (typeof(obj) === "undefined") {
        return true;
    }
    if (obj === "undefined") {
        return true;
    }
    if (obj === "") {
        return true;
    }
    if (!obj && obj !== 0 && obj !== '' && obj == null) {
        return true;
    }
    if (Array.prototype.isPrototypeOf(obj) && obj.length === 0) {
        return true;
    }
    return Object.prototype.isPrototypeOf(obj) && Object.keys(obj).length === 0;
}

function renderAll() {
    element.render();
    form.render();
}

/**
 * js 判断是否以某字符串开始
 * @param s
 * @returns {boolean}
 */
String.prototype.startWith = function (s) {
    if (s == null || s == "" || this.length == 0 || s.length > this.length)
        return false;
    return this.substr(0, s.length) == s;
};
