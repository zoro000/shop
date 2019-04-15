$(function () {
    $.ajax({
        type: "post",
        url: "../AdminServlet",
        data:{
            theWay:"Num",
        },
        dataType: "json",
        success: function (data) {
            $("#userNum").html(data.userNum);
            $("#orderNum").html(data.orderNum);
        }
    })
});