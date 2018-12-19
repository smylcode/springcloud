var data = {name: "gouchao.test"};
Alpaca.Tpl({template: './index-header.html', to: '#index-header', data: data});
Alpaca.Tpl({template: './index-left.html', to: '#index-left', data: data});
Alpaca.Tpl({template: './index-footer.html', to: '#index-footer', data: data});
$().ready(function () {
    var hash = window.location.hash;
    if (hash === "") {
        hash = "#/auth/index/index";
    }
    Alpaca.run(hash);
    // Alpaca.Router.getParams 该方法一定要在Alpaca.run之后，否则会报错。
    var topNavId = Alpaca.Router.getParams("topNavId");
    console.log("获取的URL参数 topNavId ...为：" + topNavId);
    if (!isEmpty(topNavId)) {
        //topNavId = topNavId.split("-")[1];
        sessionStorage.setItem("topNavId",topNavId);
    }

    // $LAB.script("//static-cdn.06care.com/lib/layui/2.3.0-rc1/layui.all.js").wait()
    //     .script("../js/common.js").wait()
    //     .script("header.js").wait();
    //隐藏、显示左侧导航
    $(".hideMenu").click(function () {
        $(".layui-layout-admin").toggleClass("showMenu");
    });

    element.on('nav(left-nav)', function (elem) {
        console.log(elem.attr("href")); //得到当前点击的DOM对象
    });
    /**
     * 所有的a标签 hash路由地址进行刷新显示，友好体验。
     */
    $("a").click(function () {
        var href = $(this).attr("href");
        if (typeof(href) !== "undefined") {
            if (href.startWith("#")) {
                console.log("href is :" + href);
                $('body').animate({
                    backgroundColor: 'gray',
                    opacity: 0.7,
                    cursor: 'wait',
                    'z-index': 3000
                }, 200);
                $('body').animate({
                    backgroundColor: 'white',
                    opacity: 1,
                    cursor: 'wait',
                    'z-index': 3000
                }, 400);
            }
        }
    });
});

