package servlet;

import dao.ProductDao;
import dao.ProductDetailDao;
import net.sf.json.JSONArray;
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
import java.util.List;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ProductDetailDao productDetailDao = new ProductDetailDao();
        Product product = new Product();
        PrintWriter out = response.getWriter();
        String act = request.getParameter("act");
        String sid = request.getParameter("id");//获取页面传过来的id
        System.out.println(sid);
        if("top".equals(act)){
            product = productDetailDao.queryById(Integer.parseInt(sid));
            JSONObject json = (JSONObject) JSONSerializer.toJSON(product);
            out.print(json);
        }else if("down".equals(act)){
            int cid = productDetailDao.queryCid(Integer.parseInt(sid));//查出cid
            List<Product> products = productDetailDao.queryByCid(cid);//根据cid查出同一类产品
            JSONArray json = (JSONArray) JSONSerializer.toJSON(products);
            out.print(json);
        }
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
