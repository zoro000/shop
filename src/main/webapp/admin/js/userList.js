$(function () {
    $.ajax({
        type: "post",
        url: "../AdminServlet",
        data: {
            theWay: "userList",
        },
        dataType: "json",
        success: function (data) {
            $("#tbody").empty();
            var html = "";
            $.each(data.userList, function (i, o) {
                html += '<tr>'
                html += '<td>' + o.id + '</td>'
                html += '<td>' + o.name + '</td>'
                html += '<td>' + o.password + '</td>'
                html += '<td>' + o.phone + '</td>'
                html += '<td>' + o.email + '</td>'
                html += '<td>' + o.answer + '</td>'
                html += '<td class="td-manage">'
                html += ' <a title="删除" onclick="member_del('+o.id+')"'
                html += ' class="btn btn-xs btn-warning"><i class="icon-trash  bigger-120"></i></a>'
                html += '</td>'
                html += ' </tr>'
            })
            $("#tbody").append(html);
        }
    })
});

function queryUser() {
    $.ajax({
        type: "post",
        url: "../AdminServlet",
        data: {
            theWay: "queryList",
            name: $("#username").val(),
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#tbody").empty();
            var html = "";
            $.each(data.userList, function (i, o) {
                html += '<tr>'
                html += '<td>' + o.id + '</td>'
                html += '<td>' + o.name + '</td>'
                html += '<td>' + o.password + '</td>'
                html += '<td>' + o.phone + '</td>'
                html += '<td>' + o.email + '</td>'
                html += '<td>' + o.answer + '</td>'
                html += '<td class="td-manage">'
                html += ' <a title="删除"  onclick="member_del('+o.id+')"'
                html += ' class="btn btn-xs btn-warning"><i class="icon-trash  bigger-120"></i></a>'
                html += '</td>'
                html += ' </tr>'
            })
            $("#tbody").append(html);
        }
    })
}

/*用户-删除*/
function member_del(id) {
    $.ajax({
        type: "post",
        url: "../AdminServlet",
        data: {
            theWay: "delUser",
            id: id,
        },
        dataType: "json",
        success: function (data) {
            location.reload();
            alert(data.msg);
        }
    })
}