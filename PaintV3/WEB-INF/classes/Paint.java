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
		private String finishDraw;
	

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
			this.finishDraw = "faux";
		}
		
		public void setDraw(String finishDraw) {
			this.finishDraw = finishDraw;
		}

		public String xml() {

			return "<action type=\"" + this.type + "\" startX=\"" + this.startX + "\" startY=\"" + this.startY
					+ "\" strokeStyle=\"" + this.strokeStyle + "\" lineWidth=\"" + this.lineWidth + "\" endX=\""
					+ this.endX + "\" endY=\"" + this.endY + "\" id=\"" + this.id + "\" finishDraw=\""+this.finishDraw+"\">" + "</action>";
		}
		
		public String toString() {
			
			return this.type+this.startX+this.startY+this.endX+this.endY;
		}
	}

	private static final long serialVersionUID = 1L;
	List<Form> figures = new ArrayList<Form>();
	List<Form> figuresUndo = new ArrayList<Form>();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// res.setContentType("text/xml");// setting the content type
		res.setContentType("text/xml;charset=UTF-8");
		// PrintWriter writer = response.getWriter();

		PrintWriter pw = res.getWriter();// get the stream to write the data
		HttpSession s = req.getSession();
		String xmlSend = "";
		String xmlLive = "";
		Set<Form> hs = new HashSet<>();
		System.out.println(req.getParameter("type"));
		System.out.println(figures.size() + "????????????????????????????");
		if (req.getParameter("type") != null) {
			form1 = new Form(req.getParameter("type"), req.getParameter("startX"), req.getParameter("startY"),
					req.getParameter("strokeStyle"), req.getParameter("lineWidth"), req.getParameter("endX"),
					req.getParameter("endY"), req.getParameter("id"));
			System.out.println(req.getParameter("endDraw") + "??????????????????????????");
			if (req.getParameter("endDraw").equals("true")) {
				form1.setDraw("vrai");
				figures.add(form1);
			}

		}

		// Quand on recoit ce paramètre bah ça vide la liste puis on renvoit une liste vide donc ça n'affiche rien
		if (req.getParameter("clear") != null) {

			figures.clear();
		}
		
		// Cet attribut permet de savoir si on fait un undo, si c'est le cas on recherche l'objet form qui contient un toString qui
		//correspond à la valeur de l'attribut. Si on trouve une  correspondance, on stocke l'objet dans le tableau undo
		if (req.getParameter("undo") != null) {

			for(int i = 0; i < figures.size(); i++) {
				if(req.getParameter("undo").equals(figures.get(i).toString())) {
					figuresUndo.add(figures.get(i));
					figures.remove(i);
				}
			}
		}
		
		// Si on redo, bah on fait l'inverse
		if (req.getParameter("redo") != null) {

			for(int i = 0; i < figuresUndo.size(); i++) {
				if(req.getParameter("redo").equals(figuresUndo.get(i).toString())) {
					figures.add(figuresUndo.get(i));
					figuresUndo.remove(i);
				}
			}
		}

		hs.addAll(figures);
		figures.clear();
		figures.addAll(hs);

		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<picture>");
		if (!figures.isEmpty())
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
