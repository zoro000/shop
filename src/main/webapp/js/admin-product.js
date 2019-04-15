$(()=>{
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
    var url=window.location.search; //获取url中"?"符后的字串
    if(url.indexOf("?")!=-1){
        result = url.substr(url.indexOf("=")+1);//获取商品id
        console.log(result);
    }
    $.ajax({//加载商品详情图片
        type:"post",
        url:"../ProductDetailServlet",
        data:{"id":result,"act":"top"},
        dataType:"json",
        success:function (data) {
            // console.log(data);
            var html='';
            html+='<div class="tb-booth tb-pic tb-s310">'
            html+='<img src="'+data.type+'" alt="细节展示放大镜特效" rel="../images/01.jpg" class="jqzoom" />'
            html+='</div>'
            html+='<ul class="tb-thumb" id="thumblist">'
            html+='<li class="tb-selected">'
            html+='<div class="tb-pic tb-s40">'
            html+='<img src="../images/01_small.jpg" mid="../images/01_mid.jpg" big="'+data.type+'">'
            html+='</div>'
            html+='</li>'
                /*<li>
						<div class="tb-pic tb-s40">
							<a href="#"><img src="../images/02_small.jpg" mid="../images/02_mid.jpg" big="../images/02.jpg"></a>
						</div>
					</li>
					<li>
						<div class="tb-pic tb-s40">
							<a href="#"><img src="../images/03_small.jpg" mid="../images/03_mid.jpg" big="../images/03.jpg"></a>
						</div>
					</li>*/
            html+='</ul>'
            $("#pic").append(html);

        }
    })
    $.ajax({//商品描述
        type:"post",
        url:"../ProductDetailServlet",
        data:{"id":result,"act":"top"},
        dataType:"json",
        success:function (data) {
            var html='<h1>'+data.subTitle+'</h1>';
            $("#subT").append(html);
        }
    })
    $.ajax({//价格
        type:"post",
        url:"../ProductDetailServlet",
        data:{"id":result,"act":"top"},
        dataType:"json",
        success:function (data) {
            var html='';
            html+='<li class="price iteminfo_price">'
            html+='<dt>促销价</dt>'
            html+='<dd><em>¥</em><b class="sys_item_price">'+data.promotePrice+'</b>  </dd>'
            html+='</li>'
            html+='<li class="price iteminfo_mktprice">'
            html+='<dt>原价</dt>'
            html+='<dd><em>¥</em><b class="sys_item_mktprice">'+data.orignalPrice+'</b></dd>'
            html+='</li>'
            html+='<div class="clear"></div>'
            $("#price").append(html);
        }
    })
    $.ajax({//库存
        type:"post",
        url:"../ProductDetailServlet",
        data:{"id":result,"act":"top"},
        dataType:"json",
        success:function (data) {
            var html=''
            html+='<input  class="am-btn am-btn-default" onclick="min1()" type="button" value="-" />'
            html+='<input id="text_box" type="text" value="1" style="width:30px; height: 28px;text-align: -webkit-center" />'
            html+='<input  class="am-btn am-btn-default" onclick="add1()" type="button" value="+" />'
            html+='<span id="Stock" class="tb-hidden">库存<span id="kucun" class="stock">'+data.count+'</span>件</span>'
            $("#inventory").append(html);
        }
    })
    $.ajax({//猜你喜欢
        type:"post",
        url:"../ProductDetailServlet",
        data:{"id":result,"act":"down"},
        dataType:"json",
        success:function (data) {
            // console.log(data)
            $.each(data,function (index,product) {
                // console.log(product.type);
                var html=''
                html+='<li>'
                html+='<div class="i-pic limit">'
                html+='<img src="'+product.type+'" />'
                html+='<p> <span>'+product.subTitle+'</span></p>'
                html+='<p class="price fl">'
                html+='<b>¥</b>'
                html+='<strong>'+product.promotePrice+'</strong></p></div></li>'
                $("#productD").append(html);
            })
        }
    })

})
function min1() {//减一
    if(parseInt($("#text_box").val())<=1){
        return;
    }else{
        $("#text_box").val(parseInt($("#text_box").val())-1);
    }

}
function add1() {//加一
    var num=0;
    var text_boxVal= $("#text_box").val();
    if(parseInt($("#text_box").val())<parseInt($("#kucun").text())){
        num=parseInt(text_boxVal)+1;
        $("#text_box").val(num);
    }else{
        return;
    }
}
function InsertShopCart() {
    var result;
    var url=window.location.search; //获取url中"?"符后的字串
    if(url.indexOf("?")!=-1){
        result = url.substr(url.indexOf("=")+1);//获取商品id
        console.log(result);
    }
    console.log(result);
    $.ajax({
        type:"post",
        url:"../ShopCartServlet",
        data:{"act":"InsertShopCart","pid":result,"number":$("#text_box").val()},
        dataType:"json",
        success:function (data) {
            if(data.msg){
               window.location.href="shopcart.html";
            }
        }
    })
}
function pay() {
    var pid;
    var url=window.location.search; //获取url中"?"符后的字串
    if(url.indexOf("?")!=-1){
        pid = url.substr(url.indexOf("=")+1);//获取商品id
        console.log(pid);
    }
    // localStorage.setItem("pid",result);
    localStorage.setItem("number",$("#text_box").val());
    window.location.href="pay.html?pid="+pid;
}