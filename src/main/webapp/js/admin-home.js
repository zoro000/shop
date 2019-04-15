function show(n){//轮播
    if(n==5){n=1}
    document.getElementById("img").setAttribute("src","../images/ad"+n+".jpg");
    document.getElementById("p").setAttribute("href","introduction.html?pid="+n);
    n++;
    _t = window.setTimeout("show("+n+")",1000);
}
window.setTimeout("show(1)",1000);
//加的
function search(){
    localStorage.setItem("item",$("#searchInput").val())
    window.location.href="search.html";
}
//
$.ajax({
    type:"post",
    url:"../OrderItemServlet",
    dataType:"json",
    success:function (data) {
        console.log(data.count);
        $("#count").text(data.count);
        $("#J_MiniCartNum").text(data.count);
    }
})
$(()=>{
    var id;
    var url  = window.location.search;
    if(url.indexOf("?")!=-1){
        id=url.substr(url.indexOf("=")+1);
        if(id!=0){
            $("#head").hide();
            $("#welcome").append('<div class="menu-hd" >欢迎登录</div>');
        }
    }/*else{
        return
        /!*var html='';
        html+='<a href="login.html" target="_top" class="h">亲，请登录</a>'
        html+='<a href="register.html" target="_top">免费注册</a>'
        $("#head").append(html);*!/
    }*/


    $.ajax({
        type:"post",
        url:"../ProductServlet",
        data:{"act":"product"},
        dataType:"json",
        success:function (data) {
            // console.log(data);
            var f=1;//判断是水果的计数器
            var j=1;//判断是坚果的计数器
            $.each(data,function (index,product) {
                if(product.cid==1 && f<=6){
                    f++;
                    var html = '<a class="outer" href="introduction.html?id=' + product.id + '"><span class="inner"><b class="text">' + product.name + '</b></span></a>';//console.log(html);
                    $("#fruit").append(html);
                }else if(product.cid==2 && j<=6){
                    j++;
                    var html='<a class="outer" href="introduction.html?id='+product.id+'"><span class="inner"><b class="text">'+product.name+'</b></span></a>';
                    $("#nut").append(html);
                }
            })

        }
    })
    $.ajax({
        type:"post",
        url:"../ProductServlet",
        dataType:"json",
        data:{"act":"productPath"},
        success:function (data) {
            //console.log(data);
            var f=1;//判断是水果的计数器
            var j=1;//判断是坚果的计数器
            $.each(data,function (index,product) {
                if(product.cid==1 && f==1){
                    f++;
                    var html='';
                    html+='<div class="outer-con ">';
                    html+='<div class="title ">'+product.name;
                    html+='</div>';
                    html+='<div class="sub-title ">￥'+product.promotePrice;
                    html+='</div>';
                    html+='<i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i>';
                    html+='</div>';
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a></div>';
                    // console.log(html);
                    $("#fruit1").append(html);
                }else if(product.cid==1 && f==2){
                    f++;
                    var html='';
                    html+='<div class="outer-con ">';
                    html+='<div class="title ">'+product.name;
                    html+='</div>';
                    html+='<div class="sub-title ">￥'+product.promotePrice;
                    html+='</div>';
                    html+='<i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i>';
                    html+='</div>';
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a>';
                    $("#fruit2").append(html);
                }else if (product.cid==1 && f==3){
                    var html='';
                    f++;
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div> <div class="sub-title ">￥'+product.promotePrice;
                    html+='</div><i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i></div>'
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'"/></a>'
                    $("#fruit3").append(html);
                }else if(product.cid==1 && f==4){
                    f++;
                    var html='';
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div> <div class="sub-title ">￥'+product.promotePrice;
                    html+='</div> <i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i> </div>'
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a>';
                    $("#fruit4").append(html);
                }else if (product.cid==1 && f==5){
                    f++;
                    var html='';
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div><div class="sub-title ">¥'+product.promotePrice;
                    html+='</div> <i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i></div>';
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a>';
                    $("#fruit5").append(html);
                } else if (product.cid ==1 && f==6){
                    var html='';
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div><div class="sub-title ">¥'+product.promotePrice;
                    html+='</div><i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i></div>'
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a>';
                    $("#fruit6").append(html);
                    f++;
                } else if(product.cid==2 && j==1){
                    j++;
                    var html='';
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div><div class="sub-title ">￥'+product.promotePrice;
                    html+='</div><i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i></div>';
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a></div>';
                    $("#nut1").append(html);
                } else if (product.cid==2 && j==2){
                    j++;
                    var html='';
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div><div class="sub-title ">￥'+product.promotePrice;
                    html+='</div><i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i></div>';
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a>';
                    $("#nut2").append(html);
                } else if (product.cid==2 && j==3){
                    var html='';
                    j++;
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div> <div class="sub-title ">￥'+product.promotePrice;
                    html+='</div><i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i></div>'
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'"/></a>'
                    $("#nut3").append(html);
                } else if (product.cid==2 && j==4){
                    j++;
                    var html='';
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div> <div class="sub-title ">￥'+product.promotePrice;
                    html+='</div> <i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i> </div>'
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a>';
                    $("#nut4").append(html);
                } else if (product.cid==2 && j==5){
                    j++;
                    var html='';
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div><div class="sub-title ">¥'+product.promotePrice;
                    html+='</div> <i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i></div>';
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a>';
                    $("#nut5").append(html);
                } else if (product.cid==2 && j==6){
                    var html='';
                    html+='<div class="outer-con "><div class="title ">'+product.name;
                    html+='</div><div class="sub-title ">¥'+product.promotePrice;
                    html+='</div><i class="am-icon-shopping-basket am-icon-md  seprate" onclick="add('+product.id+')"></i></div>'
                    html+='<a href="introduction.html?id='+product.id+'"><img src="'+product.type+'" /></a>';
                    $("#nut6").append(html);
                    j++;
                } else {
                    return;
                }
            })
        }
    })

})
function add(pid) {//增加购物车信息
    var count = $("#J_MiniCartNum").text();
    $("#J_MiniCartNum").text(parseInt(count)+1);
    $("#count").text(+count+1);
    $.ajax({
        type:"post",
        url:"../ShopCartServlet",
        data:{"act":"InsertShopCart","uid":1,"pid":pid,"number": 1},
        dataType:"json",
        success:function (data) {
            if(data.msg){
                return;
            }
        }
    })
}
function shopcart() {
    window.location.href="shopcart.html"
}