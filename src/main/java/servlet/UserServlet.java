package servlet;

import dao.UserDao;
import net.sf.json.JSONObject;
import pojo.Address;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String theWay = request.getParameter("theWay");
        PrintWriter out = response.getWriter();
        JSONObject jo = new JSONObject();
        UserDao userDao = new UserDao();

        if ("upd".equals(theWay)) {
            // 更新用户
            HttpSession session = request.getSession();
            String uname = ((User) session.getAttribute("loginer")).getName();
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            ;
            User user1 = userDao.selectUser(uname);
            // 判断是否为空，并且赋值
            name = name.length() == 0 ? uname : name;
            password = password.length() == 0 ? user1.getPassword() : password;
            phone = phone.length() == 0 ? user1.getPhone() : phone;
            email = email.length() == 0 ? user1.getEmail() : email;

            int id = user1.getId();
            int fluRow = userDao.updUser(name, password, phone, email, id);
            if (fluRow == 1) {
                jo.element("msg", "修改成功！请重新登陆！");
                jo.element("pass", true);
            } else {
                jo.element("msg", "修改失败!");
                jo.element("pass", false);
            }
        } else if ("register".equals(theWay)) {
            HttpSession session = request.getSession();
            String code = request.getParameter("code");
            String validCode = (String) session.getAttribute("validCode");

            if (code.equalsIgnoreCase(validCode)) {
                // 验证码正确
                // 用户注册
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                int fluRow = userDao.insertUser(name, password, phone, email);
                if (fluRow == 1) {
                    jo.element("msg", "修改成功！请重新登陆！");
                    jo.element("pass", true);
                } else {
                    jo.element("msg", "修改失败!");
                    jo.element("pass", false);
                }
            } else {
                jo.element("msg", "验证码错误!");
                jo.element("pass", false);
            }
        } else if ("saveAddress".equals(theWay)) {
            // 新增地址
            // 用户名
            HttpSession session = request.getSession();
            String uname = ((User) session.getAttribute("loginer")).getName();
            // 收货人姓名
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            User user = userDao.selectUser(uname);
            // 获取用户的ID
            int uid = user.getId();
            int fluRow = userDao.saveAddress(address, uid, name, phone);
            if (fluRow == 1) {
                jo.element("msg", "添加成功!");
                jo.element("pass", true);
            } else {
                jo.element("msg", "添加失败!");
                jo.element("pass", false);
            }
        } else if ("forget".equals(theWay)) {
            // 忘记密码 查询
            String name = request.getParameter("name");
            String answer = request.getParameter("answer");
            User user = userDao.queryPwd(name, answer);
            if (user != null) {
                jo.element("msg", user.getPassword());
                jo.element("pass", true);
            } else {
                jo.element("msg", "查询失败！");
                jo.element("pass", false);
            }
        } else if ("address".equals(theWay)) {
            // 默认查询地址
            HttpSession session = request.getSession();
            String name = ((User) session.getAttribute("loginer")).getName();
            User user = userDao.selectUser(name);
            List<Address> addressList = userDao.address(user.getId());
            jo.element("addressList", addressList);
        } else if ("delAddress".equals(theWay)) {
            // 地址删除
            int id = Integer.parseInt(request.getParameter("id"));
            int flwRow = userDao.delAddress(id);
            if (flwRow == 1) {
                jo.element("msg", "删除成功！");
                jo.element("pass", true);
            } else {
                jo.element("msg", "删除失败！");
                jo.element("pass", false);
            }
        } else if ("saveQ".equals(theWay)) {
            // 新增问题
            HttpSession session = request.getSession();
            String name = ((User) session.getAttribute("loginer")).getName();
            //问题
            System.out.println(name);
            String problem = request.getParameter("problem");
            // 答案
            String answer = request.getParameter("answer");
            userDao = new UserDao();
            int id = userDao.selectUser(name).getId();
            System.out.println(id);
            int fluRow = userDao.saveQ(problem, answer, id);
            if (fluRow == 1) {
                jo.element("msg", "添加成功!");
                jo.element("pass", true);
            } else {
                jo.element("msg", "添加失败!");
                jo.element("pass", false);
            }
        }
        out.print(jo);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
