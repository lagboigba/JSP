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
		private String id;

		public Form(String type, String startX, String startY, String strokeStyle, String lineWidth, String endX,
				String endY, String id) {
			this.type = type;
			this.startX = startX;
			this.startY = startY;
			this.strokeStyle = strokeStyle;
			this.lineWidth = lineWidth;
			this.endX = endX;
			this.endY = endY;
			this.id = id;
		}

		public String xml() {

			return "<action type=\"" + this.type + "\" startX=\"" + this.startX + "\" startY=\"" + this.startY + "\" strokeStyle=\""
					+ this.strokeStyle + "\" lineWidth=\"" + this.lineWidth + "\" endX=\"" + this.endX + "\" endY=\"" + this.endY + "\" id=\"" + this.id
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
		Set<Form> hs = new HashSet<>();
		System.out.println(req.getParameter("type"));
		if (req.getParameter("type") != null) {
			form1 = new Form(req.getParameter("type"), req.getParameter("startX"), req.getParameter("startY"),
					req.getParameter("strokeStyle"), req.getParameter("lineWidth"), req.getParameter("endX"),
					req.getParameter("endY"), req.getParameter("id"));
			System.out.println(req.getParameter("endDraw")+"??????????????????????????");
			if (req.getParameter("endDraw").equals("true")) {
				figures.add(form1);
			}

		}
		
		hs.addAll(figures);
		figures.clear();
		figures.addAll(hs);
		
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<picture>");
		if(!figures.isEmpty())
		for (int i = 0; i < figures.size(); i++) {
			pw.println(figures.get(i).xml());
		}
		pw.println(form1.xml());
		
		pw.println("</picture>");
		
		pw.flush();
		pw.close();
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
