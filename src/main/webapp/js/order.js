$(function () {

    $.ajax({
        type: "post",
        url: "../OrderServlet",
        data: {
            theWay: "selectOrder",
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#allDiv").empty();
            var html = "";
            $.each(data.orderList, function (i, o) {
                html += '<div class="order-title">'
                html += '<div class="dd-num">订单编号：<a href="javascript:;">' + o.id + '</a></div>'
                html += '<span>成交时间：' + o.payDate + '</span> </div> <div class="order-content">'
                html += '<div class="order-left" id="左侧信息">'

                $.each(data.ordersList, function (x, os) {
                    if (os.oid == o.id) {
                        html += '<ul class="item-list" id="单个div">'
                        html += '<li class="td td-item">'
                        html += '<div class="item-pic"><a href="#" class="J_MakePoint">'
                        html += '<img src="' + os.productimage.type + '"'
                        html += 'class="itempic J_ItemImg"></a> </div> <div class="item-info">'
                        html += '<div class="item-basic-info"> <a href="#">'
                        html += '<p>' + os.product.subTitle + '</p>'
                        html += '</a></div></div></li>'
                        html += '<li class="td td-price">'
                        html += '<div class="item-price">￥' + os.product.promotePrice + '</div></li>'
                        html += '<li class="td td-number"><div class="item-number">'
                        html += '<span>' + os.num + '</span></div></li></ul>'
                    }
                })

                html += '</div><div class="order-right">'
                html += '<li class="td td-amount">'
                html += '<div class="item-amount">合计：￥' + o.price + ' <p><span></span></p> </div></li>'
                html += '<div class="move-right">'
                html += '<li class="td td-status">'
                html += '<div class="item-status">'
                html += '<p class="Mystatus">交易成功</p>'
                html += '<p class="order-info"><a href="orderinfo.html">订单详情</a></p>'
                html += '<p class="order-info"><a href="logistics.html">查看物流</a></p></div> </li>'
                html += '<li class="td td-change">'
                html += '<button type="button" onclick="delOrder(' + o.id + ')" class="am-btn am-btn-danger anniu">删除订单</button></li></div> </div></div>'
            })
            $("#allDiv").append(html);
        }
    })
});

// 删除订单
function delOrder(id) {
    $.ajax({
        type: "post",
        url: "../OrderServlet",
        data: {
            theWay: "delOrder",
            id:id,
        },
        dataType: "json",
        success: function (data){
            if (data.pass){
                alert(data.msg);
                location.href = "order.html";
            }else {
                alert(data.msg);
            }
        },
    })
};