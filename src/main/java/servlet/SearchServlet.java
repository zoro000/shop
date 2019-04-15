package servlet;

import dao.SearchDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import pojo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String act = request.getParameter("act");
        SearchDao searchDao = new SearchDao();
        JSONObject json = new JSONObject();
        if("selectBySubTitle".equals(act)){
            String subTitle = request.getParameter("subTitle");
            if(subTitle.length()!=0){//获取模糊查询的subtitle
                List<Product> products = searchDao.queryBySubTitle(subTitle);
                if(products.size()!=0){
                    json.element("flag","true");
                    json.element("products",products);
                }else{
                    json.element("flag","false");
                    json.element("msg","查无此商品");
                }
            }else{
                return;
            }
        }else if("selectByCid".equals(act)){
            String cid = request.getParameter("cid");//获取查询的cid
            System.out.println(cid);
            if(cid.length()!=0){
                List<Product> products = searchDao.queryByCid(Integer.parseInt(cid));
                if(products.size()!=0){
                    json.element("flag","true");
                    json.element("products",products);
                }else{
                    json.element("flag","false");
                    json.element("msg","查无此商品");
                }
            }else {
                return;
            }
        }
        out.print(json);
        out.flush();
        out.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

