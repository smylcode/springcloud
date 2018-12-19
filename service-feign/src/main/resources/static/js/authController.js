//定义index模块
Alpaca.AuthModule = {};
//定义index控制器
Alpaca.AuthModule.IndexController = {
    //定义index动作
    indexAction: function () {
        Alpaca.Tpl({template: '../page/index-body.html', to: '#layui-body', data: data});
        renderAll(); // 重要，重新渲染页面，保证功能正确性。
    },

    menuListAction: function () {
        $("#body").addClass("noClick");
        $("#layui-body").load("../page/menuList.html", function () {
            renderAll();
            $("#body").removeClass("noClick");
        });
    },

    userListAction: function () {
        $("#body").addClass("noClick");
        $("#layui-body").load("../page/userList.html", function () {
            renderAll();
            $("#body").removeClass("noClick");
        });
    }
};