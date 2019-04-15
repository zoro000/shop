package servlet;

import dao.ProductDao;
import net.sf.json.JSONArray;
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

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String act = request.getParameter("act");
        System.out.println(act);
        ProductDao productDao = new ProductDao();
        PrintWriter out = response.getWriter();
        if("product".equals(act)){
            List<Product> products = productDao.queryAll();
            JSONArray json = (JSONArray)JSONSerializer.toJSON(products);
            out.print(json);
            out.flush();
            out.close();
        }else if("productPath".equals(act)){
            List<Product> products = productDao.queryProductAndPath();
            JSONArray json = (JSONArray)JSONSerializer.toJSON(products);
            out.print(json);
            out.flush();
            out.close();
        }
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
        }
}
