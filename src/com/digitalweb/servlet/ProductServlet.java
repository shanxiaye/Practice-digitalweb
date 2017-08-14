package com.digitalweb.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.digitalweb.impl.ProductDaoImpl;
import com.digitalweb.model.Product;

public class ProductServlet extends HttpServlet {
private String info = "校验成功";
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ProductDaoImpl pdi = new ProductDaoImpl();
		String nextPage = "ProductServlet?flag=list";
		HashMap<String,String[]> map = (HashMap<String,String[]>) request.getParameterMap();
		HttpSession session = request.getSession();
		boolean flag = true;
		if(map.get("flag")[0].equals("add")){
			Product p = new Product();
			p.setBrand(map.get("brand")[0]);
			p.setCode(map.get("code")[0]);
			String intro = map.get("intro")[0];
			p.setIntro(map.get("intro")[0]);
			p.setName(map.get("name")[0]);
			p.setNum(Integer.parseInt(map.get("num")[0]));
			p.setPic(map.get("pic")[0]);
			p.setPrice(Double.parseDouble(map.get("price")[0]));
			p.setSale(Double.parseDouble(map.get("sale")[0]));
			p.setType(map.get("type")[0]);
			flag = pdi.add(p);
		}else if(map.get("flag")[0].equals("update")){
			Product p = new Product();
			p.setBrand(map.get("brand")[0]);
			p.setCode(map.get("code")[0]);
			p.setId(Integer.parseInt(map.get("id")[0]));
			p.setIntro(map.get("intro")[0]);
			p.setName(map.get("name")[0]);
			p.setNum(Integer.parseInt(map.get("num")[0]));
			p.setPic(map.get("pic")[0]);
			p.setPrice(Double.parseDouble(map.get("price")[0]));
			p.setSale(Double.parseDouble(map.get("sale")[0]));
			p.setType(map.get("type")[0]);
			flag = pdi.update(p);
		}else if(map.get("flag")[0].equals("delete")){
			int id = Integer.parseInt(map.get("id")[0]);
			flag = pdi.delete(id);
		}else if(map.get("flag")[0].equals("list")){
			session.setAttribute("productList", pdi.list());
			nextPage = "admin/list_product.jsp";
		}else if(map.get("flag")[0].equals("search")){
			String searchType = map.get("searchType")[0];
			String key = map.get("key")[0];
			String sql = "select * from product_info where "+searchType+" like '%"+key+"%'";
			session.setAttribute("productList", pdi.search(sql));
			nextPage = "admin/list_product.jsp";
		}else if(map.get("flag")[0].equals("import")){
			String filePath = "";
			String[] sf =  request.getParameter("fileName").trim().split("/");
			String pre = request.getSession().getServletContext().getRealPath("") ;
			for(int i=2;i<sf.length;i++){
				filePath = filePath + sf[i]+"\\";
			}
			filePath = pre + "\\" + filePath.substring(0,filePath.lastIndexOf("\\"));
			List<Product> pList = translate(filePath);
			if(pList==null||pList.size()==0){
				session.setAttribute("importInfo", info);
				nextPage = "importProduct.jsp";
			}else{
				flag = pdi.importProduct(pList);
				nextPage = "ProductServlet?flag=list";
			}
		}
		if(flag)
			response.sendRedirect(nextPage);
	}
	public List<Product> translate(String filePath){
		List<Product> pList = new ArrayList<Product>();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
//			HSSFCellStyle cellStyle = workbook.createCellStyle();
//	        HSSFDataFormat format = null;
			HSSFSheet sheet = workbook.getSheetAt(0);
//			sheet = workbook.getSheet("");
			HSSFRow row = null;
			int num = sheet.getPhysicalNumberOfRows();
			for(int i=1;i<num;i++){
				row = sheet.getRow(i);
				Product p = new Product();
				//获取最后一列的列数
				int num2 = row.getPhysicalNumberOfCells(); 
				HSSFCell cell = null;
				int j=0;
				try{
				for(;j<num2;j++){
					cell = row.getCell(j);
					switch(j){
					case 0://code
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						p.setCode(cell.getStringCellValue());break;
					case 1://name
						p.setName(cell.getStringCellValue());break;
					case 2: //type
						p.setType(cell.getStringCellValue());break;
					case 3://brand
						p.setBrand(cell.getStringCellValue());break;
					case 4://pic
						p.setPic(cell.getStringCellValue());break;
					case 5://num
						p.setNum((int)cell.getNumericCellValue());break;
					case 6://price
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						p.setPrice((double)cell.getNumericCellValue());break;
					case 7://sale
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						p.setSale((double)cell.getNumericCellValue());break;
					case 8://intro
						p.setIntro(cell.getStringCellValue());break;
					default:break;
					}//end of switch
				}//end of for
				}catch(Exception e){
					e.printStackTrace();
					info = "第"+(i+1)+"行第"+(j+1)+"列单元格格式错误";
					pList.clear();
				    break;
		        }
				pList.add(p);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			info = "文件找不到";
			pList.clear();
		} catch (IOException e) {
			e.printStackTrace();
			info = "文件传输错误";
			pList.clear();
		}
		return pList;
	}
}
