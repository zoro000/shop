$(()=>{
    $.ajax({
        type:"post",
        url:"../OrderItemServlet",
        dataType:"json",
        success:function (data) {
            console.log(data.count);
            $("#J_MiniCartNum").text(data.count);
        }
    })
    var i = 1;//给价格 数量 总价 的id的计数器
    $.ajax({
        type:"post",
        url:"../ShopCartServlet",
        data:{"act":"listshop"},
        dataType:"json",
        success:function (data) {
            console.log(data);
            $.each(data,function (index,product) {
                var html='';
                html+='<ul class="item-content clearfix">'
                html+='<li class="td td-chk">'
                html+='<div class="cart-checkbox ">'
                html+='<input class="check" id="J_CheckBox_170037950254" name="items"  type="checkbox" onclick="box()">'
                html+='<label for="J_CheckBox_170037950254"></label>'
                html+='</div>'
                html+='</li>'
                html+='<li class="td td-item">'
                html+='<div class="item-pic">'
                html+='<a href="introduction.html?pid='+product.id+'" target="_blank" data-title="美康粉黛醉美东方唇膏口红正品 持久保湿滋润防水不掉色护唇彩妆" class="J_MakePoint" data-point="tbcart.8.12">'
                html+='<img src="'+product.type+'" class="itempic J_ItemImg" style="height: 85px;"></a>'
                html+='</div>'
                html+='<div class="item-info">'
                html+='<div class="item-basic-info">'
                html+='<a href="introduction.html?pid='+product.id+'" target="_blank" title="美康粉黛醉美唇膏 持久保湿滋润防水不掉色" class="item-title J_MakePoint" data-point="tbcart.8.11">'+product.subTitle+'</a>'
                html+='</div>'
                html+='</div>'
                html+='</li>'
                html+='<li class="td td-info">'
                html+='<div class="item-props ">'
                html+='</div>'
                html+='</li>'
                html+='<li class="td td-price">'
                html+='<div class="item-price price-promo-promo">'
                html+='<div class="price-content">'
                html+='<div class="price-line">'
                html+='<em class="price-original">'+product.orignalPrice+'</em>'
                html+='</div>'
                html+='<div class="price-line">'
                html+='<em class="J_Price price-now" tabindex="0" id="pp'+i+'">'+product.promotePrice+'</em>'
                html+='</div>'
                html+='</div>'
                html+='</div>'
                html+='</li>'
                html+='<li class="td td-amount">'
                html+='<div class="amount-wrapper ">'
                html+='<div class="item-amount ">'
                html+='<div class="sl">'
                html+='<input class="min am-btn" onclick="cut('+i+')" type="button" value="-" />'
                html+='<input class="text_box" id="numb'+i+'" type="text" value="'+product.number+'" style="width:30px; height: 28px;text-align: -webkit-center" onchange="num('+i+')" />'
                html+='<input class="add am-btn" onclick="add('+i+')" type="button" value="+" />'
                html+='<input id="hide'+i+'" type="hidden" value="'+product.otid+'" />'
                html+='<input id="kucun'+i+'" type="hidden" value="'+product.count+'" />'
                html+='<input id="pid'+i+'" type="hidden" value="'+product.id+'" />'
                html+='</div>'
                html+='</div>'
                html+='</div>'
                html+='</li>'
                html+='<li class="td td-sum">'
                html+='<div class="td-inner">'
                html+='<em tabindex="0" class="J_ItemSum number" id="total'+i+'">'+(Number(product.number*product.promotePrice)).toFixed(2)+'</em>'
                html+='</div>'
                html+='</li>'
                html+='<li class="td td-op">'
                html+='<div class="td-inner">'
                html+='<a type="button" onclick="del('+product.otid+')" data-point-url="#" class="delete">删除</a>'
                html+='</div>'
                html+='</li>'
                html+='</ul>'
                $("#shopcart").append(html);
                console.log(product.count);
                i++;

            })

        }
    })

})
//删除购物车
function del(id) {
    console.log(id);
    var box = document.getElementsByName("items");
    for(k in box){
        if(box[k].checked){//如果被选中就删除
            if(confirm("是否删除")) {
                $.ajax({
                    type: "post",
                    url: "../ShopCartServlet",
                    data: {"act": "delorderitem", "orderitemid": id},
                    dataType: "json",
                    success: function (data) {
                        console.log(data.msg);
                        if (data.msg) {
                            window.location.href = "shopcart.html";
                        }
                    }
                })
            }
        }
    }

}
//批量删除
function del() {
    var box = document.getElementsByName("items");
    var j=0;
    Arrayid=[];
    for(k in box){
        if(box[k].checked){//如果被选中就删除
            j=+k+1;
            Arrayid.push($("#hide"+j).val())
        }
    }
    console.log(Arrayid);
    $.ajax({
        type: "post",
        url: "../ShopCartServlet",
        traditional:true,
        data: {"act": "delorderitems", "orderitemids": Arrayid},
        dataType: "json",
        success: function (data) {
            console.log(data.msg);
            if (data.msg) {
                window.location.href = "shopcart.html";
            }
        }
    })
}
//当购买数量改变时
function num(index) {
    $("#J_Total").empty();
    var orderitemid=$("#hide"+index).val();//获取购物车表id
    var num = $("#numb"+index).val();//获取改变后的购买数量值
    var promotePrice= $("#pp"+index).text();//获取价格
    var price = num * promotePrice; //计算价格
    $.ajax({
        type:"post",
        url:"../ShopCartServlet",
        data:{"act":"changeNum","number":num,"oderitemid":orderitemid},
        dataType:"json",
        success:function (data) {
            if(data.msg){
                $("#total"+index).text(Number(price).toFixed(2));
                box();
            }

        }
    })
}
//商品件数和商品总价
function box() {
    var box = document.getElementsByName("items");
    // console.log(box.length)
    var productCount = 0;//商品件数计数器
    var totalPrice=0;//商品合计总价
    var price=0;//商品价格
    var j = 0;
    for(var i=0;i<box.length;i++){
        if(box[i].checked){
            j=i+1;
            productCount++;
            price=+$("#total"+j).text();//+表示把字符串转成数字
            totalPrice+=price;
            $("#J_SelectedItemsCount").text(productCount);//商品数量
            $("#J_Total").text(Number(totalPrice).toFixed(2));//商品总价
            $("#J_MiniCartNum").text(productCount);//页面最上面的购物车
        }else{
            $("#J_SelectedItemsCount").text(productCount);
            $("#J_Total").text(Number(totalPrice).toFixed(2));
            $("#J_MiniCartNum").text(productCount)
        }
    }
}
//判断有没有结算
function pay() {
    var box = document.getElementsByName("items");
    var j = 0;
    odids = [];//获取购物车表id
    for(var i=0;i<box.length;i++){
            if(box[i].checked){//是否被勾选了
              j=+i+1;
              var orderitemid=$("#hide"+j).val();//获取购物车表id
              odids.push(orderitemid);
            }
    }
    console.log(orderitemid)
    localStorage.setItem("odids", JSON.stringify(odids));
     var pao = $("#J_Total").text();
     if(pao!=0.00){
         window.location.href="pay.html";
     }else {
         alert("请先选择商品");
     }

}
function cut(index) {//减一
    $.ajax({
        type:"post",
        url:"../ShopCartServlet",
        data:{"act":"changeNum","oderitemid":$("#hide"+index).val(),"number":parseInt($("#numb"+index).val())-1},
        dataType:"json",
        success:function (data) {
            if(data.msg){
                if(parseInt($("#numb"+index).val())<=1){
                    return;
                }else{
                    $("#numb"+index).val(parseInt($("#numb"+index).val())-1);
                    num(index);
                }
            }else{
                return;
            }
        }
    })
}
function add(index) {//加一
    $.ajax({
        type:"post",
        url:"../ShopCartServlet",
        data:{"act":"changeNum","oderitemid":$("#hide"+index).val(),"number":parseInt($("#numb"+index).val())+1},
        dataType:"json",
        success:function (data) {
            if(data.msg){
                if(parseInt($("#numb"+index).val())<$("#kucun"+index).val()){
                    $("#numb"+index).val(parseInt($("#numb"+index).val())+1);
                    num(index);
                }else{
                    return;
                }
            }else{
                return;
            }
        }
    })

}

