package servlet;

import dao.OrderItemDao;
import net.sf.json.JSONObject;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/OrderItemServlet")
public class OrderItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        int uid = ((User) session.getAttribute("loginer")).getId();
        System.out.println("uid:"+uid);
        OrderItemDao orderItemDao = new OrderItemDao();
        int count = orderItemDao.selectCountItem(uid);
        JSONObject json= new JSONObject();
        json.element("count",count);
        out.print(json);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
