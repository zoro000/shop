package servlet;

import dao.PayDao;
import dao.ShopCartDao;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import pojo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PayServlet")
public class PayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String act = request.getParameter("act");
        System.out.println("1:"+act);
        PrintWriter out = response.getWriter();
        PayDao payDao = new PayDao();
        if("queryByOdid".equals(act)){//根据购物车主键查询
            String sids[] =request.getParameterValues("odids");//购物车主键
            JSONObject jo = new JSONObject();
            System.out.println("2:"+sids);
            if(sids.length>0){
                for(int i=0;i<sids.length;i++){
                    Product product = payDao.selectByOdid(Integer.parseInt(sids[i]));
                    jo.element("product"+i,product);
                }
            }
            out.print(jo);
            out.flush();
            out.close();
        }else if("queryByPid".equals(act)){//根据商品主键查询
            String pid = request.getParameter("pid");
            System.out.println("3--"+pid);
            Product product = payDao.qeuryByPid(Integer.parseInt(pid));
            JSONObject json = (JSONObject)JSONSerializer.toJSON(product);
            out.print(json);
            out.flush();
            out.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
