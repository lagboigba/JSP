import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;  
import java.util.*;

public class Chat extends HttpServlet{  
	private static final long serialVersionUID = 1L;
	List<String> messages = new ArrayList<String>();
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)  
	throws ServletException,IOException  
	{  
		res.setContentType("text/xml");//setting the content type  
		PrintWriter pw=res.getWriter();//get the stream to write the data  

		HttpSession s = req.getSession();

		if(req.getParameter("uname")!=null)
			s.setAttribute("uname",req.getParameter("uname"));

		String uname; 
		if(s.getAttribute("uname")!=null) uname = s.getAttribute("uname").toString();
		else uname = "Anonymous";
	
		if(req.getParameter("newmsg")!=null){
			messages.add(uname+": "+req.getParameter("newmsg"));
		}

		if(req.getParameter("clear")!=null){
			messages = new ArrayList<String>();
		}
  


		pw.println("<picture>");
		for(int i=0;i<action.size();i++){
			pw.println("<action type="+action.get_type(i)+" startX="+action.get_startx(i)+" startY="+action.get_starty(i)+" strokeStyle="+action.get_stroke(i)+" lineWidth="+action.get_width(i)+" endX="+action.get_stroke(i)+" endY="+action.get_endY(i)+">"+"</action>");
		}
		pw.println("</picture>");
  
		pw.close();
	}

	public void doPost(HttpServletRequest req,HttpServletResponse res)  
	throws ServletException,IOException  
	{
		doGet(req,res);
	}
}  
