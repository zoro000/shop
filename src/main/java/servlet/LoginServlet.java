package servlet;

import dao.LoginDao;
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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();


        PrintWriter out = response.getWriter();
        JSONObject jo = new JSONObject();

        String code = request.getParameter("code");
        String validCode = (String) session.getAttribute("validCode");
        if (code.equalsIgnoreCase(validCode)) {
            // 验证码正确

            // 获取用户名和密码
            String name = request.getParameter("name");
            String pssword = request.getParameter("password");

            // 查询数据库
            LoginDao loginDao = new LoginDao();
            User user = loginDao.selectLogin(name, pssword);

            if (user == null) {
                // 用户名或密码不正确
                jo.element("pass", false);
                jo.element("msg", "用户名或者密码错误");
            } else {
                // 储存用户名到 session
                session.setAttribute("loginer", user);
                jo.element("pass", true);
                jo.element("msg", "登录成功");
            }
        } else {
            // 验证码不正确
            jo.element("pass", false);
            jo.element("msg", "验证码错误！！！");
        }

        out.print(jo);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
