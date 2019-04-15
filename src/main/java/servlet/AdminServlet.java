package servlet;

import dao.AdminDao;
import net.sf.json.JSONObject;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String theWay = request.getParameter("theWay");

        PrintWriter out = response.getWriter();
        JSONObject jo = new JSONObject();
        AdminDao adminDao=new AdminDao();
        if ("Num".equals(theWay)){
            // 查询用户数量
            int userNum =adminDao.userNum();
            int orderNum=adminDao.orderNum();
            jo.element("userNum", userNum);
            jo.element("orderNum",orderNum);
        }
        else if ("userList".equals(theWay)){
            // 查询所有用户
            List<User> userList =null;
            userList= adminDao.selectAll();
            jo.element("userList",userList);
        }
        else if ("queryList".equals(theWay)){
            // 模糊查询
            List<User> userList =null;
            userList= adminDao.queryUser(request.getParameter("name"));
            jo.element("userList",userList);
        }
        else if ("delUser".equals(theWay)){
            // 删除用户
            int i =adminDao.delUser(Integer.parseInt(request.getParameter("id")));
            if (i == 1){
                jo.element("msg","删除成功！");
            }else {
                jo.element("msg","删除失败！");
            }
        }
        out.print(jo);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
