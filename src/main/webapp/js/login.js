$(function () {
    $("#kanbuq").click(() => {
        $("#codeImg").attr("src", "../ImageCodeServlet?" + Math.random())
})
})


function userlogin() {
    if ($("#name").val().length > 0 &&
        $("#password").val().length > 0&&
        $("#code").val().length > 0) {
        $.ajax({
            type: "post",
            url: "../LoginServlet",
            data: {
                name: $("#name").val(),
                password: $("#password").val(),
                code: $("#code").val(),
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.pass) {
                    alert("登陆成功");
                    location.href = "home.html?name="+$("#name").val();
                } else {
                    alert(data.msg);
                    $("#codeImg").attr("src", "../ImageCodeServlet?" + Math.random())
                }
            }
        })
    } else {
        alert("请全部填写，不能为空");
    }
}

function textNull() {
    alert("不能为空");
}

function register() {
    if ($("#name").val().length > 0 &&
        $("#password").val().length > 0 &&
        $("#email").val().length > 0 &&
        $("#phone").val().length > 0&&
        $("#code").val().length > 0) {
        $.ajax({
            type: "post",
            url: "../UserServlet",
            data: {
                theWay: "register",
                name: $("#name").val(),
                password: $("#password").val(),
                email: $("#email").val(),
                phone: $("#phone").val(),
                code: $("#code").val(),
            },
            dataType: "json",
            success: function (data) {
                if (data.pass) {
                    alert("注册成功！前往登录页面！");
                    location.href = "../home/login.html";
                } else {
                    alert("注册失败！")
                }
            }
        })
    } else {
        alert("请全部填写，不能为空");
    }
}

function queryPwd() {
    if ($("#user-name").val().length > 0 &&
        $("#user-answer").val().length > 0) {
        $.ajax({
            type: "post",
            url: "../UserServlet",
            data: {
                theWay: "forget",
                name: $("#user-name").val(),
                answer: $("#user-answer").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.pass) {
                    alert("您的密码为："+data.msg);
                } else {
                    alert("查询失败！请检查您的用户名或安全答案")
                }
            }
        })
    } else {
        alert("请全部填写，不能为空");
    }
}