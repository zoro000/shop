//改
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
var result;
var url = window.location.search; //获取url中"?"符后的字串
if (url.indexOf("?") != -1) {
    localStorage.removeItem("item");//清空
    result = decodeURI(url.substr(url.indexOf("=") + 1));
    console.log(result);
    if(isNaN(result)){
        selectBySubTitle(result);//根据subTitle查询
    }else{
        selectByCid(result);
    }
}
var item = localStorage.getItem("item");
console.log(item)
if(isNaN(item)){
    selectBySubTitle(item);//根据subTitle查询
}
function selectBySubTitle(item){
    $.ajax({//根据subTitle查询
        type: "post",
        url: "../SearchServlet",
        data: {
            "act": "selectBySubTitle",
            "subTitle": item
        },
        dataType: "json",
        success: function (data) {
            console.log(data.flag)
            if(data.flag){
                $.each(data.products,function (index,product) {
                    var html='';
                    html+='<li>'
                    html+='<div class="i-pic limit">'
                    html+='<a href="introduction.html?pid='+product.id+'"><img src="'+product.type+'"/></a>'
                    html+='<p class="title fl">'+product.subTitle+'</p>'
                    html+='<p class="price fl">'
                    html+='<b>¥</b>'
                    html+='<strong>'+product.promotePrice+'</strong>'
                    html+='</p>'
                    html+='<p class="number fl">'
                    html+='</p> </div> </li>'
                    $("#product").append(html);
                })
            }else{
                alert(data.msg);
                window.location.href="home.html"
            }

        }
    })
}
function selectByCid(item){
    $.ajax({//根据cid查询
        type: "post",
        url: "../SearchServlet",
        data: {
            "act": "selectByCid",
            "cid": item
        },
        dataType: "json",
        success: function (data) {
            console.log(data.msg)
            if(data.flag){
                $.each(data.products,function (index,product) {
                    var html='';
                    html+='<li>'
                    html+='<div class="i-pic limit">'
                    html+='<a href="introduction.html?pid='+product.id+'"><img src="'+product.type+'"/></a>'
                    html+='<p class="title fl">'+product.subTitle+'</p>'
                    html+='<p class="price fl">'
                    html+='<b>¥</b>'
                    html+='<strong>'+product.promotePrice+'</strong>'
                    html+='</p>'
                    html+='<p class="number fl">'
                    html+='</p> </div> </li>'
                    $("#product").append(html);
                })
            }else{
                alert(data.msg);
                window.location.href="home.html";
            }

        }
    })
}

function search(){
    localStorage.removeItem("item");
    localStorage.setItem("item",$("#searchInput").val())
    window.location.href="search.html";
}








