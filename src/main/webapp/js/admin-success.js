
/*$.ajax({
    type:"post",
    url:"../OrderItemServlet",
    dataType:"json",
    success:function (data) {
        console.log(data.count);
        $("#J_MiniCartNum").text(data.count);
    }
})*/
list();

function list() {

    var odids = JSON.parse(localStorage.getItem("odids"));
    console.log(odids);
    var pids = JSON.parse(localStorage.getItem("pids"))
    var nums = JSON.parse(localStorage.getItem("nums"))
    var totalPrice = localStorage.getItem("totalPrice");
    var phone = localStorage.getItem("phone")
    var addressId = localStorage.getItem("addressId")
    var address = localStorage.getItem("address")
    var name = localStorage.getItem("name");
    console.log(totalPrice + phone + address + name);
    $("#price").text('￥' + totalPrice);//价格
    $("#p").text('收货人电话:' + phone);
    $("#a").text('收货人地址:' + address);
    $("#n").text('收货人姓名:' + name);
    $.ajax({//往order插入
        type:"post",
        url:"../OrderServlet",
        data:{
            "addressId":addressId,
            "price":totalPrice,
            "status":1,
            "act":"insertOrder"
        },
        dataType:"json",
        success:function (data) {
            if(data.flag){
                //alert('订单添加'+data.msg);
                orderid= data.orderid;
                console.log(orderid);
                addOrders(orderid);
                //window.location.href="home.html"
            }
        }
    })
    function addOrders(orderid){
        console.log('function'+orderid);
        $.ajax({//往orders插入数据
            type:"post",
            url:"../OrderServlet",
            traditional:true,
            data:{
                "pids":pids,
                "nums":nums,
                "orderid": orderid,
                "act":"insertOrders"
            },
            dataType:"json",
            success:function (data) {
                if(data.flag){
                    alert('购买成功！');
                }
            }
        })


    }

    if(odids!=null){

        $.ajax({//删除已付款的购物车信息
            url:"../ShopCartServlet",
            type:"post",
            traditional: true,
            data:{
                "act":"delorderitems",
                "orderitemids":odids
            },
            success:function (data) {
                if(data.msg){
                }
            }
        })

    }

}

