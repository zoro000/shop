package servlet;

import dao.OrderDao;
import net.sf.json.JSONObject;
import pojo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();
        JSONObject jo = new JSONObject();

        String theWay = request.getParameter("theWay");
        String act = request.getParameter("act");
        //  String odids[] = request.getParameterValues("odids");
        Date da = new Date();
        SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        OrderDao orderDao = new OrderDao();
        String sa = sb.format(da);
        System.out.println(sa);
        Order order = new Order();
        int orderid = 0;
        if ("selectOrder".equals(theWay)) {
            // 根据用户ID查询所有订单
            int uid = ((User) session.getAttribute("loginer")).getId();
            List<Order> orderList = orderDao.queryOrder(uid);
            List<Orders> ordersList = orderDao.uidQueryOrders(uid);

            // 根据orders的pid查询商品
            int[] ints = new int[ordersList.size()];
            for (int i = 0; i < ordersList.size(); i++) {
                ints[i] = ordersList.get(i).getPid();
                Product product = orderDao.queryProduct(ints[i]);
                ordersList.get(i).setProduct(product);
            }

            // 通过商品ID获取图片路径
            int[] ints2 = new int[ordersList.size()];
            for (int i = 0; i < ints2.length; i++) {
                ints2[i] = ordersList.get(i).getProduct().getId();
                Productimage productimage = orderDao.queryProductimage(ints2[i]);
                ordersList.get(i).setProductimage(productimage);
            }

            jo.element("orderList", orderList);
            jo.element("ordersList", ordersList);
        } else if ("delOrder".equals(theWay)) {
            // 删除订单
            int id = Integer.parseInt(request.getParameter("id"));
            int i = orderDao.delOrder(id);
            i += orderDao.delOrders(id);
            if (i > 1) {
                jo.element("pass", true);
                jo.element("msg", "删除成功！");
            } else {
                jo.element("pass", false);
                jo.element("msg", "删除失败！");
            }
        }

        if ("insertOrder".equals(act)) {
            String addressId = request.getParameter("addressId");
            String status = request.getParameter("status");
            String price = request.getParameter("price");
            order.setAddressId(Integer.parseInt(addressId));
            order.setPayDate(sa);
            order.setStatus(Integer.parseInt(status));
            order.setPrice(Double.parseDouble(price));
            int uid = ((User) session.getAttribute("loginer")).getId();
            order.setUid(uid);
            int fluRows = orderDao.insertOrder(order);
            if (fluRows > 0) {
                orderid = orderDao.selectOrderId(sa);
                System.out.println("id:" + orderid);
                jo.element("flag", true);
                jo.element("msg", "购买成功");
                jo.element("orderid", orderid);
            } else {
                jo.element("flag", false);
                jo.element("msg", "购买失败");
            }
        } else if ("insertOrders".equals(act)) {
            int fluRows = 0;
            String pids[] = request.getParameterValues("pids");
            String nums[] = request.getParameterValues("nums");
            orderid = Integer.parseInt(request.getParameter("orderid"));
            for (int i = 0; i < pids.length; i++) {
                Orders order1 = new Orders(Integer.parseInt(pids[i]), orderid, Integer.parseInt(nums[i]));
                fluRows += orderDao.insertOrders(order1);
            }
            if (fluRows > 0) {
                jo.element("flag", "true");
                jo.element("msg", "ok");
            } else {
                jo.element("flag", "false");
                jo.element("msg", "nook");
            }
        }
        out.print(jo);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
