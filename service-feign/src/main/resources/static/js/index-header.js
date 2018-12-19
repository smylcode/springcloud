$(function () {

    var topNavId = sessionStorage.getItem("topNavId");
    if (isEmpty(topNavId)) {
        topNavId = "";
    }
    $.ajax({
        type: "get",
        url: contextPath + '/feign/web/getTopMenu',
        data: null,
        async: false,
        dataType: 'json',
        success: function (res) {
            topNavId = res.data[0].id;
            $.each(res.data, function (i, e) {
                $("#topNavHeader").append("<li class='layui-nav-item'><a href='javascript:void(0);'" +
                    "id='top-" + e.id + "'><i class='layui-icon " + e.icon + "'></i>&nbsp;&nbsp; " + e.name + "</a></li>");
            });
            $("#topNavHeader").addClass("layui-nav layui-layout-left");
            $("#top-" + topNavId).parent("li").addClass("layui-this");
            renderAll();
            element.on('nav(topNavHeader)', function (elem) {
                var topNavId = elem.attr("id");
                topNavId = topNavId.split("-")[1];
                if (!isEmpty(topNavId)) {
                    sessionStorage.setItem("topNavId", topNavId);
                }
                elem.parent("dd").addClass("layui-this");
                getLeftMenu(topNavId);
            });
            console.log("顶部菜单加载完成");
        }
    });
    getLeftMenu(topNavId);
    function getLeftMenu(topId) {
        if (topId === ""){
            topId = 1;
        }
        $("#leftMenuMiddle").empty();
        var openHashUrl = "#/auth/index/index";
        $.ajax({
            type: "get",
            url: contextPath + '/feign/web/getLeftMenu',
            data: {topId: topId},
            async: false,
            dataType: 'json',
            success: function (res) {
                if (res.code == 0){
                    $.each(res.data, function (i, e) {
                        if (e.hasChild) {
                            $("#leftMenuMiddle").append("<li class='layui-nav-item' id='extend" + e.id + "'><a id='left-" + e.id + "' href='javascript:;'><i class='' layui-icon " + e.icon + "'></i>&nbsp;&nbsp; " + e.name + "</a><dl class='layui-nav-child' id='layui-nav-child-left" + i + "'></dl></li>");
                            $.each(e.childList, function (ii, nn) {
                                $("#layui-nav-child-left" + i).append("<dd id='left-dd" + nn.id + "'><a id='left-" + nn.id + "' href='" + nn.url + "?leftMenuId=" + nn.id + "&topNavId=" + nn.topId + "'><i class=' layui-icon " + nn.icon + "'></i>&nbsp;&nbsp;" + nn.name + "</a></dd>");
                            });
                            openHashUrl = e.childList[0].url + "?leftMenuId=" + e.childList[0].id + "&topNavId=" + e.childList[0].topId;
                        } else {
                            $("#leftMenuMiddle").append("<li class='layui-nav-item' id='extend" + e.id + "'><a id='left-" + e.id + "' href='" + e.url + "?leftMenuId=" + e.id + "&topNavId=" + e.topId + "'><i class=' layui-icon " + e.icon + "'></i>&nbsp;&nbsp; " + e.name + "</a></li>");
                            openHashUrl = e.url + "?leftMenuId=" + e.id + "&topNavId=" + e.topId;
                        }
                    })
                    $("#leftMenuMiddle").addClass("layui-nav layui-nav-tree");
                    console.info(openHashUrl);
                    Alpaca.run(openHashUrl);
                    renderAll();
                    element.on('nav(left-nav)', function (elem) {
                        var leftId = elem.attr("id");
                        leftId = leftId.split("-")[1];
                        if (!isEmpty(leftId)) {
                            sessionStorage.setItem("leftMenuId", leftId);
                        }
                        elem.parent("dd").addClass("layui-this");
                    });
                } else {
                    layer.msg(res.des)
                }
            },
            error:function (res) {
                layer.msg(res);
            }
        });
    }
});