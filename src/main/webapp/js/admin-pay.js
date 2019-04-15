var pid;
var numb;
 pids =[];
 nums =[];
$(() => {
    $.ajax({
        type:"post",
        url:"../OrderItemServlet",
        dataType:"json",
        success:function (data) {
            console.log(data.count);
            $("#J_MiniCartNum").text(data.count);
        }
    })
    var url = window.location.search; //获取url中"?"符后的字串
    numb = localStorage.getItem("number");//获取商品数量
    if (url.indexOf("?") != -1) {
        pid = url.substr(url.indexOf("=") + 1);//获取商品id
        $.ajax({//点击直接购买
            type: "post",
            url: "../PayServlet",
            data: {"pid": pid, "act": "queryByPid"},
            dataType: "json",
            success: function (data) {
                var price = 0;//计算价格的
                console.log(data);
                var html = '';
                html += '<div class="bundle  bundle-last" >'
                html += '<div class="bundle-main">'
                html += '<ul class="item-content clearfix">'
                html += '<div class="pay-phone">'
                html += '<li class="td td-item">'
                html += '<div class="item-pic">'
                html += '<a href="introduction.html?pid=' + data.id + '" class="J_MakePoint">'
                html += '<img src="' + data.type + '" class="itempic J_ItemImg" style="height: 85px;"></a>'
                html += '</div>'
                html += '<div class="item-info">'
                html += '<div class="item-basic-info">'
                html += '<a href="introduction.html?pid=' + data.id + '" class="item-title J_MakePoint" data-point="tbcart.8.11">' + data.subTitle + '</a>'
                html += '</div>'
                html += '</div>'
                html += '</li>'
                html += '<li class="td td-price">'
                html += '<div class="item-price price-promo-promo">'
                html += ' <div class="price-content">'
                html += '<em id="pp" class="J_Price price-now">' + data.promotePrice + '</em>'
                html += '</div>'
                html += '</div>'
                html += '</li>'
                html += '</div>'
                html += '<li class="td td-amount">'
                html += '<div class="amount-wrapper ">'
                html += '<div class="item-amount ">'
                html += '<span class="phone-title">购买数量</span>'
                html += '<div class="sl">'
                html += '<input class="min am-btn" onclick="cut1()" type="button" value="-" />'
                html += '<input onchange="num1()" class="text_box" id="numb" type="text" value="' + numb + '" style="width:30px; height: 32.5px;text-align: -webkit-center" />'
                html += '<input class="add am-btn" onclick="add1()" type="button" value="+" />'
                html += '<input id="hide" type="hidden" value="' + data.otid + '" />'
                html += '<input id="kucun" type="hidden" value="' + data.count + '" />'
                html += '</div>'
                html += '</div>'
                html += '</div>'
                html += '</li>'
                html += '<li class="td td-sum">'
                html += '<div class="td-inner">'
                html += '<em id="tPrice" class="J_ItemSum number">' + (Number(numb * data.promotePrice)).toFixed(2) + '</em>'
                html += '</div>'
                html += '</li>'
                html += '<li class="td td-oplist">'
                html += '<div class="td-inner">'
                html += '<span class="phone-title">配送方式</span>'
                html += '<div class="pay-logis">'
                html += '快递<b class="sys_item_freprice">免邮</b>'
                html += '</div>'
                html += '</div>'
                html += '</li>'
                html += '</ul>'
                html += '<div class="clear"></div>'
                html += '</div>'
                html += ' </div>'
                $("#paylist").append(html);
                price += +$("#tPrice").text();
                console.log(price)
                $("#totalPrice").text(Number(price).toFixed(2))
                $("#J_ActualFee").text(Number(price).toFixed(2));
                nums.push($("#numb").val());
                pids.push(data.id);
            }
        })

    } else {
        var odids = JSON.parse(localStorage.getItem("odids"));//购物车id
        console.log(odids + '--' + numb);
        if (odids != null) {
            var i = 1;
            $.ajax({
                type: "post",
                url: "../PayServlet",
                data: {"odids": odids, "act": "queryByOdid"},
                traditional: true,
                dataType: "json",
                success: function (data) {
                    var price = 0;//计算价格的
                    $.each(data, function (index, obj) {
                        var html = '';
                        html += '<div class="bundle  bundle-last" >'
                        html += '<div class="bundle-main">'
                        html += '<ul class="item-content clearfix">'
                        html += '<div class="pay-phone">'
                        html += '<li class="td td-item">'
                        html += '<div class="item-pic">'
                        html += '<a href="introduction.html?pid=' + obj.id + '" class="J_MakePoint">'
                        html += '<img src="' + obj.type + '" class="itempic J_ItemImg" style="height: 85px;"></a>'
                        html += '</div>'
                        html += '<div class="item-info">'
                        html += '<div class="item-basic-info">'
                        html += '<a href="introduction.html?pid=' + obj.id + '" class="item-title J_MakePoint" data-point="tbcart.8.11">' + obj.subTitle + '</a>'
                        html += '</div>'
                        html += '</div>'
                        html += '</li>'
                        html += '<li class="td td-price">'
                        html += '<div class="item-price price-promo-promo">'
                        html += ' <div class="price-content">'
                        html += '<em id="pp' + i + '" class="J_Price price-now">' + obj.promotePrice + '</em>'
                        html += '</div>'
                        html += '</div>'
                        html += '</li>'
                        html += '</div>'
                        html += '<li class="td td-amount">'
                        html += '<div class="amount-wrapper ">'
                        html += '<div class="item-amount ">'
                        html += '<span class="phone-title">购买数量</span>'
                        html += '<div class="sl">'
                        html += '<input class="min am-btn" onclick="cut(' + i + ')" type="button" value="-" />'
                        html += '<input onchange="num(' + i + ')" class="text_box" id="numb' + i + '" type="text" value="' + obj.number + '" style="width:30px; height: 32.5px;text-align: -webkit-center" />'
                        html += '<input class="add am-btn" onclick="add(' + i + ')" type="button" value="+" />'
                        html += '<input id="hide' + i + '" type="hidden" value="' + obj.otid + '" />'
                        html += '<input id="kucun' + i + '" type="hidden" value="' + obj.count + '" />'
                        html += '</div>'
                        html += '</div>'
                        html += '</div>'
                        html += '</li>'
                        html += '<li class="td td-sum">'
                        html += '<div class="td-inner">'
                        html += '<em id="tPrice' + i + '" class="J_ItemSum number">' + (Number(obj.number * obj.promotePrice)).toFixed(2) + '</em>'
                        html += '</div>'
                        html += '</li>'
                        html += '<li class="td td-oplist">'
                        html += '<div class="td-inner">'
                        html += '<span class="phone-title">配送方式</span>'
                        html += '<div class="pay-logis">'
                        html += '快递<b class="sys_item_freprice">免邮</b>'
                        html += '</div>'
                        html += '</div>'
                        html += '</li>'
                        html += '</ul>'
                        html += '<div class="clear"></div>'
                        html += '</div>'
                        html += ' </div>'
                        $("#paylist").append(html);
                        price += +$("#tPrice" + i).text();
                        $("#totalPrice").text(Number(price).toFixed(2))
                        $("#J_ActualFee").text(Number(price).toFixed(2));
                        nums.push($("#numb" + i ).val());
                        pids.push(obj.id);
                        i++;
                    })
                }
            })
        }
    }

})

//当购买数量改变时
function num(index) {
    var tPrice = $("#tPrice" + index).text();//同一商品改变数量前的价格
    var totalPrice = $("#J_ActualFee").text();//所有商品改变数量前价格
    $("#totalPrice").empty();//清空价格
    $("#J_ActualFee").empty();//清空价格
    var orderitemid = $("#hide" + index).val();//获取购物车表id
    var num = $("#numb" + index).val();//获取改变后的购买数量值
    var singlePrice = $("#pp" + index).text();//获取商品单价价格
    var promotePrice = num * singlePrice; //改变数量后的价格
    $("#tPrice" + index).text(Number(promotePrice).toFixed(2));//将改变后价格赋值
    var price = Number(promotePrice - tPrice).toFixed(2);//改变后的差价
    var J_ActualFee = parseFloat(price) + parseFloat(totalPrice);
    $.ajax({
        type: "post",
        url: "../ShopCartServlet",
        data: {"act": "changeNum", "number": num, "oderitemid": orderitemid},
        dataType: "json",
        success: function (data) {
            if (data.msg) {
                $("#totalPrice").text(Number(J_ActualFee).toFixed(2));
                $("#J_ActualFee").text(Number(J_ActualFee).toFixed(2));
            }

        }
    })
    // console.log($("#name").text()+$("#address").text()+$("#phone").text())

}

//点击结算的事件
function J_Go() {
    if ($("#name").text().length == 0) {
        alert("请先选择地址");
    } else {
        var odids = JSON.parse(localStorage.getItem("odids"));
        console.log(odids);
        localStorage.setItem("odids", JSON.stringify(odids));
        var totalPrice = $("#totalPrice").text();
        localStorage.setItem("totalPrice", totalPrice);
        localStorage.setItem("name", $("#name").text());
        localStorage.setItem("phone", $("#phone").text());
        localStorage.setItem("address", $("#address").text());
        localStorage.setItem("pids",JSON.stringify(pids));
        localStorage.setItem("nums",JSON.stringify(nums));
        window.location.href = "success.html";

    }

}

function cut(index) {//减一
    $.ajax({
        type: "post",
        url: "../ShopCartServlet",
        data: {
            "act": "changeNum",
            "oderitemid": $("#hide" + index).val(),
            "number": parseInt($("#numb" + index).val()) - 1
        },
        dataType: "json",
        success: function (data) {
            if (data.msg) {
                if (parseInt($("#numb" + index).val()) <= 1) {
                    return;
                } else {
                    $("#numb" + index).val(parseInt($("#numb" + index).val()) - 1);
                    num(index);
                }
            } else {
                return;
            }
        }
    })
}

function add(index) {//加一
    $.ajax({
        type: "post",
        url: "../ShopCartServlet",
        data: {
            "act": "changeNum",
            "oderitemid": $("#hide" + index).val(),
            "number": parseInt($("#numb" + index).val()) + 1
        },
        dataType: "json",
        success: function (data) {
            if (data.msg) {
                if (parseInt($("#numb" + index).val()) < $("#kucun" + index).val()) {
                    $("#numb" + index).val(parseInt($("#numb" + index).val()) + 1);
                    num(index);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    })

}

function add1() {//加一
    console.log($("#numb").val() + '-' + $("#kucun").val());
    if (parseInt($("#numb").val()) < parseInt($("#kucun").val())) {
        $("#numb").val(parseInt($("#numb").val()) + 1);
        num1();
    } else {
        return;
    }
}

function cut1() {//减一
    console.log($("#numb").val());
    if (parseInt($("#numb").val()) <= 1) {
        return;
    } else {
        $("#numb").val(parseInt($("#numb").val()) - 1);
        num1();
    }
}

function num1() {//数量改变时改变价格
    var tPrice = $("#tPrice").text();//同一商品改变数量前的价格
    var totalPrice = $("#J_ActualFee").text();//所有商品改变数量前价格
    $("#totalPrice").empty();//清空价格
    $("#J_ActualFee").empty();//清空价格
    //var orderitemid=$("#hide").val();//获取购物车表id
    var num = $("#numb").val();//获取改变后console.log($("#name").text()+$("#address").text()+$("#phone").text())的购买数量值
    var singlePrice = $("#pp").text();//获取商品单价价格
    var promotePrice = num * singlePrice; //改变数量后的价格
    $("#tPrice").text(Number(promotePrice).toFixed(2));//将改变后价格赋值
    var price = Number(promotePrice - tPrice).toFixed(2);//改变后的差价
    var J_ActualFee = parseFloat(price) + parseFloat(totalPrice);
    $("#totalPrice").text(Number(J_ActualFee).toFixed(2));
    $("#J_ActualFee").text(Number(J_ActualFee).toFixed(2));
    console.log($("#name").text()+$("#address").text()+$("#phone").text())
}
