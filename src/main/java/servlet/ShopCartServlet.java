package servlet;

import dao.ShopCartDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import pojo.Orderitem;
import pojo.Product;
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

@WebServlet("/ShopCartServlet")
public class ShopCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String act = request.getParameter("act");
        System.out.println(act);
        JSONObject jo = new JSONObject();
        PrintWriter out = response.getWriter();
        ShopCartDao shopCartDao = new ShopCartDao();
        HttpSession session = request.getSession();
        int uid = ((User) session.getAttribute("loginer")).getId();
        if ("listshop".equals(act)) {//列出购物车
            List<Orderitem> orderitems = shopCartDao.queryShop(uid);
            for (int i = 0; i < orderitems.size(); i++) {
                System.out.println(orderitems.get(i).getCid());
                Product product = shopCartDao.qeuryBy(orderitems.get(i).getId());
                jo.element("product" + i, product);
            }
        } else if ("delorderitem".equals(act)) {
            String sid = request.getParameter("orderitemid");//主键
            int fluRows = shopCartDao.delteById(Integer.parseInt(sid));
            if (fluRows > 0) {
                jo.element("msg", "true");
            } else {
                jo.element("msg", "false");
            }
        } else if ("delorderitems".equals(act)) {
            String sids[] = request.getParameterValues("orderitemids");//主键
            System.out.println(sids.length);
            int fluRows = 0;
            if (sids.length > 0) {
                for (int i = 0; i < sids.length; i++) {
                    int id = Integer.parseInt(sids[i]);
                    fluRows += shopCartDao.delteById(id);
                }
                if (fluRows > 0) {
                    jo.element("msg", "true");
                } else {
                    jo.element("msg", "false");
                }
            }
        } else if ("changeNum".equals(act)) {
            String sid = request.getParameter("oderitemid");//主键
            String number = request.getParameter("number");//数量
            System.out.println(number);
            int i = shopCartDao.updNum(Integer.parseInt(number), Integer.parseInt(sid));
            if (i > 0) {
                jo.element("msg", "true");
            }
        } else if ("InsertShopCart".equals(act)) {
            String pid = request.getParameter("pid");
            String number = request.getParameter("number");
            int i = shopCartDao.insertShopCart(Integer.parseInt(pid), Integer.parseInt(number), uid);
            if (i > 0) {
                jo.element("msg", "true");
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
