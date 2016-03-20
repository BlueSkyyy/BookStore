package gz.itcast.web;

import gz.itcast.entity.Books;
import gz.itcast.entity.Types;
import gz.itcast.service.BooksService;
import gz.itcast.service.BooksServiceImpl;
import gz.itcast.service.TypesService;
import gz.itcast.service.TypesServiceImpl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.omg.CORBA.Request;

public class AdminsBooksServlet extends BaseServlet {

	TypesService typesService = new TypesServiceImpl();
	BooksService booksService = new BooksServiceImpl();
	
	//查询所有书的信息
	//rul /admin/books?action=list
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.调用业务：获取数据
		List<Books> books = booksService.findAll();
		//2.通过域对象转发到jsp页面
		request.setAttribute("books", books);
		request.getRequestDispatcher("/admin/pages/books/list.jsp").forward(request, response);
	}
	
	//跳转到添加页面前，查询所有图书分类，用于选择图书分类
	//URL: /admin/books?action=preEdit
	public void preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("来到了preEdit方法，跳转到edit页面");
		//1.查询所有的图书分类
		List<Types> types = typesService.findAll();
		//2.通过域对象转发到/admin/pages/books/edit.jsp
		request.setAttribute("types", types);
		request.getRequestDispatcher("/admin/pages/books/edit.jsp").forward(request, response);
	}
	
	//删除图书信息
	//URL: /admin/books?action=remove
	public void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 接收id和covers
		String id = request.getParameter("id");
		String covers = request.getParameter("covers");
		
		//2 调用业务,删除图书信息
		booksService.deleteBooks(Integer.parseInt(id));
		
		//3 获取图片所在硬盘的绝对路径，再删除封面图片
		String realPath = getServletContext().getRealPath(covers);
		File file = new File(realPath);
		file.delete();
		
		//4.成功
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("删除成功！1秒之后跳转回显示页面");
		response.setHeader("refresh", "1;url="+request.getContextPath()+"/admin/books?action=list");	
	}
	
	//删除图书信息
	//URL: /admin/books?action=findOne
	public void queryOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 接收id和covers
		String id = request.getParameter("id");
		String tag = request.getParameter("tag");
		
		System.out.println("queryOne:"+id);
		if(id!=null && !id.equals("")){		
			//2 调用业务,查询图书信息
			Books book = booksService.findById(Integer.parseInt(id));
			System.out.println(book);
			//3 将数据转发
			request.setAttribute("book", book);
	
			//4 判断
			if("detail".equals(tag)){
				request.getRequestDispatcher("/admin/pages/books/detail.jsp").forward(request, response);
			}else if("update".equalsIgnoreCase(tag)){
				System.out.println("跳转到preEdit方法");
				preEdit(request, response);
				
			}
			
		}
	}
		
	//上传封面并保存图书信息
		//URL: /admin/books?action=update
		public void update(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String id = request.getParameter("id");
			Books book = booksService.findById(Integer.parseInt(id));
			//获取文件名称
			String covers2 = book.getCovers();
			System.out.println("图片的地址名称是："+covers2);
			
			//1.创建DiskFileItemFactory，设置缓存大小，和缓存目录
			DiskFileItemFactory factory = new DiskFileItemFactory(1*1024*1024,new File("c:/tempFiles/"));
			//2.创建ServletFileUpload，解析上传的文件
			ServletFileUpload sfu = new ServletFileUpload(factory);
			//设置文件名称的中文
			sfu.setHeaderEncoding("utf-8");
			try {
				//3.解析上传文件
				List<FileItem> list = sfu.parseRequest(request);
				//为了后面设置出版时间方便
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//把图书信息封装成Books对象
				Books books = new Books();
				String covers = null;
				//无论是file还是普通表单，都会封装成FileItem对象
				if(list!=null){
					for(FileItem item:list){
						//判断是否为普路表单
						if(item.isFormField()){
							//普通表单
							//得到表单的name属性值
							String fieldName = item.getFieldName();
							//单独处理图书分类ID
							if("typesID".equals(fieldName)){
								String typesID = item.getString("utf-8");
								//封装一个包含ID的Tpyes对象，把Types对象封装到Books对象中
								Types types = new Types();
								types.setId(Integer.parseInt(typesID));
								books.setTypes(types);
							}else{
								//约定： 表单的name属性值和Books对象的属性名保持一致
								Field field = books.getClass().getDeclaredField(fieldName);
								if(field!=null){
									//给属性赋值
									field.setAccessible(true);
									//如果遇到java.util.Date类型，需要转换
									if(field.getType().getSimpleName().equals("Date")){
										String publishtime = item.getString("utf-8");
										field.set(books, sdf.parse(publishtime));
									}else if(field.getType().getSimpleName().equals("double")){
										//处理double类型
										String number = item.getString("utf-8");
										field.set(books, Double.parseDouble(number));
									}else if(field.getType().getSimpleName().equals("int")){
										//其他基本类型，直接赋值即可！
										String number = item.getString("utf-8");
										field.set(books, Integer.parseInt(number));
									}else{
										//其他基本类型，直接赋值即可！
										field.set(books, item.getString("utf-8"));
									}
								}
							}
						}else{
							
							//获取文件名
							String fileName = book.getCovers();
							
							//新的文件名
							String newFileName = fileName.substring(fileName.lastIndexOf("/"));
							
							//得到images目录所在的硬盘的绝对路径
							String imagePath = getServletContext().getRealPath("/images"); // C:\apache-tomcat-6.0.39\webapps\bookstore\images
							System.out.println("图片的地址是："+imagePath);
							//保存文件
							FileUtils.copyInputStreamToFile(item.getInputStream(), new File(imagePath+"/"+newFileName));
							//删除缓存文件
							item.delete();
							
							//封面图片的路径
							covers = fileName;
						}
						//以下的方法只能新增加图片，不能覆盖以前的图片！！
						/*{
							//file
							//1.保存到服务器的硬盘上（保存到项目的images目录下）
							//获取文件名
							String fileName = item.getName();
							//生成唯一的文件名
							String uuid = UUID.randomUUID().toString();
							//后缀
							String ext = fileName.substring(fileName.lastIndexOf("."));
							//新的文件名
							String newFileName = uuid+ext;
							
							//得到images目录所在的硬盘的绝对路径
							String imagePath = getServletContext().getRealPath("/images"); // C:\apache-tomcat-6.0.39\webapps\bookstore\images
							System.out.println("图片的地址是："+imagePath);
							//保存文件
							FileUtils.copyInputStreamToFile(item.getInputStream(), new File(imagePath+"/"+newFileName));
							//删除缓存文件
							item.delete();
							
							//封面图片的路径
							covers = "/images/"+newFileName;
						}*/
					}
				}
				
				//单独把封面路径设置到Books对象中去
				books.setCovers(covers);
				
				//2.调用业务：把图书保存到数据库
				booksService.updateBooks(books);

				//3.成功
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("修改成功！1秒之后跳转回显示页面");
				response.setHeader("refresh", "1;url="+request.getContextPath()+"/admin/books?action=list");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		//上传封面并保存图书信息
		//URL: /admin/books?action=save
		public void save(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			//1.创建DiskFileItemFactory，设置缓存大小，和缓存目录
			DiskFileItemFactory factory = new DiskFileItemFactory(1*1024*1024,new File("c:/tempFiles/"));
			//2.创建ServletFileUpload，解析上传的文件
			ServletFileUpload sfu = new ServletFileUpload(factory);
			//设置文件名称的中文
			sfu.setHeaderEncoding("utf-8");
			try {
				//3.解析上传文件
				List<FileItem> list = sfu.parseRequest(request);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//把图书信息封装成Books对象
				Books books = new Books();
				String covers = null;
				//无论是file还是普通表单，都会封装成FileItem对象
				if(list!=null){
					for(FileItem item:list){
						//判断是否为普路表单
						if(item.isFormField()){
							//普通表单
							//得到表单的name属性值
							String fieldName = item.getFieldName();
							//单独处理图书分类ID
							if("typesID".equals(fieldName)){
								String typesID = item.getString("utf-8");
								//封装一个包含ID的Tpyes对象，把Types对象封装到Books对象中
								Types types = new Types();
								types.setId(Integer.parseInt(typesID));
								books.setTypes(types);
							}else{
								//约定： 表单的name属性值和Books对象的属性名保持一致
								Field field = books.getClass().getDeclaredField(fieldName);
								if(field!=null){
									//给属性赋值
									field.setAccessible(true);
									//如果遇到java.util.Date类型，需要转换
									if(field.getType().getSimpleName().equals("Date")){
										String publishtime = item.getString("utf-8");
										field.set(books, sdf.parse(publishtime));
									}else if(field.getType().getSimpleName().equals("double")){
										//处理double类型
										String number = item.getString("utf-8");
										field.set(books, Double.parseDouble(number));
									}else{
										//其他基本类型，直接赋值即可！
										field.set(books, item.getString("utf-8"));
									}
								}
							}
						}else{
							//file
							//1.保存到服务器的硬盘上（保存到项目的images目录下）
							
							//获取文件名
							String fileName = item.getName();
							//生成唯一的文件名
							String uuid = UUID.randomUUID().toString();
							//后缀
							String ext = fileName.substring(fileName.lastIndexOf("."));
							//新的文件名
							String newFileName = uuid+ext;
							
							//得到images目录所在的硬盘的绝对路径
							String imagePath = getServletContext().getRealPath("/images"); // C:\apache-tomcat-6.0.39\webapps\bookstore\images
							System.out.println(imagePath);
							//保存文件
							FileUtils.copyInputStreamToFile(item.getInputStream(), new File(imagePath+"/"+newFileName));
							//删除缓存文件
							item.delete();
							
							//封面图片的路径
							covers = "/images/"+newFileName;
						}
					}
				}
				
				//单独把封面路径设置到Books对象中去
				books.setCovers(covers);
				
				//2.调用业务：把图书保存到数据库
				booksService.addBooks(books);
				
				//3.成功
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("添加成功！1秒之后跳转回添加页面");
				response.setHeader("refresh", "1;url="+request.getContextPath()+"/admin/books?action=preEdit");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
}
