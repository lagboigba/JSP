import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

public class Paint extends HttpServlet {

	// Class form qui représente un dessin
	public Form form1 = null;

	public class Form {

		private String type;
		private String startX;
		private String startY;
		private String strokeStyle;
		private String lineWidth;
		private String endX;
		private String endY;

		public Form(String type, String startX, String startY, String strokeStyle, String lineWidth, String endX,
				String EndY) {
			this.type = type;
			this.startX = startX;
			this.startY = startY;
			this.strokeStyle = strokeStyle;
			this.lineWidth = lineWidth;
			this.endX = endX;
			this.endY = endY;
		}

		public String xml() {

			return "<action type=\"" + this.type + "\" startX=\"" + this.startX + "\" startY=\"" + this.startY + "\" strokeStyle=\""
					+ this.strokeStyle + "\" lineWidth=\"" + this.lineWidth + "\" endX=\"" + this.endX + "\" endY=\"" + this.endY
					+ "\">" + "</action>";
		}
	}

	private static final long serialVersionUID = 1L;
	List<Form> figures = new ArrayList<Form>();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.setContentType("text/xml");// setting the content type
		res.setContentType("text/xml;charset=UTF-8");
		//PrintWriter writer = response.getWriter();
		
		PrintWriter pw = res.getWriter();// get the stream to write the data
		HttpSession s = req.getSession();
		String xmlSend ="";
		String xmlLive ="";
		System.out.println(req.getParameter("type"));
		if (req.getParameter("type") != null) {
			form1 = new Form(req.getParameter("type"), req.getParameter("startX"), req.getParameter("startY"),
					req.getParameter("strokeStyle"), req.getParameter("lineWidth"), req.getParameter("endX"),
					req.getParameter("endY"));

			if (req.getParameter("endDraw").equals("true")) {
				figures.add(form1);
			}else {
				
				form1 = null;
			}

		}
		
		xmlSend +="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//		xmlSend +="<picture>";
//		if(!figures.isEmpty())
//		for (int i = 0; i < figures.size(); i++) {
//			xmlSend +=figures.get(i).xml();
//		}
//		if(form1 != null) {
//		xmlSend += form1.xml();
//		}
//		xmlSend +="</picture>";
//		
//		pw.write(xmlSend);
//		pw.flush();
//		pw.close();
//		xmlSend ="";
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<picture>");
		if(!figures.isEmpty())
		for (int i = 0; i < figures.size(); i++) {
			pw.println(figures.get(i).xml());
		}
		if(form1 != null) {
		pw.println(form1.xml());
		}
		pw.println("</picture>");
		figures = null;
		pw.flush();
		pw.close();
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
