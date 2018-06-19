import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

public class Pong extends HttpServlet {

	// Class form qui représente un dessin
	public State State1 = null;

	public class State {

		private String action;
		private String ballx;
		private String bally;
		private String axe;
		private String dir;
		private String center;
		private String p1_px;
		private String p1_py;
		private String p2_px;
		private String p2_py;

		public State(String action, String ballx, String bally, String axe, String dir, String center, String p1_px, String p1_py,
				String p2_px, String p2_py) {
			this.action = action;
			this.ballx = ballx;
			this.bally = bally;
			this.axe = axe;
			this.dir = dir;
			this.center = center;
			this.p1_px = p1_px;
			this.p1_py = p1_py;
			this.p2_px = p2_px;
			this.p2_py = p2_py;
		}

		public String xml() {

			return "<Pongstate> <ball ballX=\"" + this.ballx + "\" ballY=\"" + this.bally + "\" axe=\"" + this.axe + "\" dir=\""
					+ this.dir + "\" center=\"" + this.center + "\">" + "</ball>"+ "\" <player1 p1_px=\"" + this.p1_px + "\" p1_py=\"" + this.p1_py + "\">" + "</player1>" + "\" <player2> p2_px=\"" + this.p2_px + "\" p2_py=\"" + this.p2_py
					+ "\" </player2>" + "</Pongstate>";
		}
	}

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.setContentType("text/xml");// setting the content type
		res.setContentType("text/xml;charset=UTF-8");
		//PrintWriter writer = response.getWriter();
		
		PrintWriter pw = res.getWriter();// get the stream to write the data
		HttpSession s = req.getSession();
		String xmlSend ="";
		String xmlLive ="";

				if (req.getParameter("type") == "leftp") {
					 if(this.p1_px > 8 ){
          				this.p1_px = this.p1_px - 8;
         }
         				else this.p1_px = this.p1_px;
     }
     if (req.getParameter("type") == "rightp") {
     	 if(this.p1_px < 500 - 8){
           this.p1_px = this.p1_px + 8;
         }
         else {this.p1_px = this.p1_px;
         }


			State1 = new Form(req.getParameter("type"), req.getParameter("startX"), req.getParameter("startY"),
					req.getParameter("strokeStyle"), req.getParameter("lineWidth"), req.getParameter("endX"),
					req.getParameter("endY"), req.getParameter("id"));
			System.out.println(req.getParameter("endDraw")+"??????????????????????????");

		
		//Test XML a retourner
/*<Pongstate width="500" height="500"> <ball ballX="267.75390625" ballY="204.51171875" axe="1" dir="1"></ball>  
<player1 color="red" left="267.75390625" top="204.51171875" right="393.75390625" bottom="297.51171875"></player1>
<player2 color="blue" left="267.75390625" top="204.51171875" right="393.75390625" bottom="297.51171875"></player2>  </Pongstate>
*/
		
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		pw.println("<Pongstate>");

		pw.println(State1.xml());
		
//		pw.println("</Pongstate>");
		
		pw.flush();
		pw.close();
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
