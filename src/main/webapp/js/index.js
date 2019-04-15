$(function () {
    $.ajax({
        type:"post",
        url:"../OrderItemServlet",
        dataType:"json",
        success:function (data) {
            console.log(data.count);
            $("#J_MiniCartNum").text(data.count);
        }
    })
    $.ajax({
        type: "post",
        url: "../IndexServlet",
        dataType: "json",
        success: function (data) {
            $("#s-name").html(data.name);
        }
    })
});

// 更新用户信息
function upd1() {
    $.ajax({
        type: "post",
        url: "../UserServlet",
        data: {
            theWay: "upd",
            name: $("#user-name2").val(),
            password: $("#user-password2").val(),
            phone: $("#user-phone2").val(),
            email: $("#user-email2").val(),
        },
        dataType: "json",
        success: function (data) {
            if (data.pass) {
                alert(data.msg);
                window.location.href = "../home/login.html";
            }else {
                alert(data.msg);
            }
        }
    })
}

// 保存收货地址
function save() {
    $.ajax({
        type: "post",
        url: "../UserServlet",
        data: {
            theWay: "saveAddress",
            name: $("#user-name").val(),
            phone: $("#user-phone").val(),
            address: $("#user-intro").val(),
        },
        dataType: "json",
        success: function (data) {
            if (data.pass) {
                alert(data.msg);
                window.location.href = "address.html";
            }else {
                alert(data.msg);
            }
        }
    })
}

// 地址删除
function delAddress(id){
    $.ajax({
        type: "post",
        url: "../UserServlet",
        data: {
            theWay: "delAddress",
            id: id,
        },
        dataType: "json",
        success: function (data) {
            if (data.pass) {
                alert(data.msg);
                window.location.href = "address.html";
            }else {
                alert(data.msg);
            }
        }
    })
}


//保存问题 和答案
function saveQ() {
    $.ajax({
        type:"post",
        url:"../UserServlet",
        data: {
            theWay: "saveQ",
            problem: $('#q option:selected').val(),
            answer: $("#answer").val(),
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data.pass) {
                alert(data.msg);
            }else {
                alert(data.msg);
            }
        }
    })
}