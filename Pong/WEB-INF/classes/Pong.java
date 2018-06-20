import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

public class Pong extends HttpServlet {

	// Class form qui représente un dessin
	public State State1 = null;

	public class State {
//////////////////////////

		///////////////

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

					if(req.getParameter("player")!=null){
			s.setAttribute(player,req.getParameter(player));
}



/////////////////Player movement
				if (req.getParameter("action") == "leftp") {
					 if(this.p1_px > 8 ){
          				this.p1_px = this.p1_px - 8;
         }
         				else this.p1_px = this.p1_px;
     }
 

     if ( req.getParameter("action")== "rightp") {
     	 if(this.p1_px < 410 - 8){
           this.p1_px = this.p1_px + 8;
         }
         else {this.p1_px = this.p1_px;
         }
     };

////////////////// Ball movement     
public void upball(State stateac){      
if (stateac.bally > 20){
  stateac.bally--;
    }
  if(stateac.bally == stateac.p1_py+10){
if((stateac.ballx >= stateac.p1_px+10) && (stateac.ballx <= stateac.p1_px+40)){
    	stateac.axe = 0;
    	}
        else if((stateac.ballx >= stateac.p1_px-2 & stateac.ballx < stateac.p1_px+10) || (stateac.ballx <= stateac.p1_px+52 & stateac.ballx > stateac.p1_px+40)){
            	axe = 0;
            	//Speed
        }
        else{
      renitPosition(stateac);
        scoreB++;
        }
    }
}



public  void leftball(State stateac){      
       if ((stateac.bally <= 410 && stateac.bally >= 0)  && ( stateac.ballx >= 0)) { // verifie qu'on est pas au bord

         stateac.ballx--;
         }
         else{
         stateac.dir = 1;
     }
 }
public void rightball(State stateac){      
         if ((stateac.bally <=  410 && stateac.bally >= 0)  && ( stateac.ballx >= 0)) { // verifie qu'on est pas au bord

         stateac.ballx--;
         }
         else{
         stateac.dir = 1;
 }
}

public void downball(State stateac){  
        if (stateac.bally < 410){
           stateac.bally++;
         }
           if(stateac.bally == stateac.p2_py-5){
if((stateac.ballx >= stateac.p2_px+10) && (stateac.ballx <= stateac.p2_px+40)){
    	axe = 1;
    	}
        else if((stateac.ballx >= stateac.p2_px-2 & stateac.ballx < stateac.p2_px+10) || (stateac.ballx <= stateac.p2_px+52 & stateac.ballx > stateac.p2_px+40)){
            	axe = 1;
            	//Speed
        }
        else{
      renitPosition(stateac);
        scoreA++;
        }
    }

    
}

public void renitPosition(State stateac){  
         stateac.ballx = Math.floor(Math.random() * 400) + 10;
         ball.bally = 210;
         stateac.center = 0;
         stateac.axe = Math.round(Math.random());
         startball();
         }

//////////////////////EQUIVALENT DE setInterval(move,20)
Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run()
        {
          if (this.center){
           if(this.dir) rightball(this); else leftball(this);
         }

         if(this.axe) upball(this); else downball(this);
           
        }
    };
timer.schedule(task, 0L ,200L);





			State1 = new Form(req.getParameter("type"), req.getParameter("startX"), req.getParameter("startY"),
					req.getParameter("strokeStyle"), req.getParameter("lineWidth"), req.getParameter("endX"),
					req.getParameter("endY"), req.getParameter("id"));
		
		
		//Test XML a retourner
/*<Pongstate> <ball ballX="267.75390625" ballY="204.51171875" axe="1" dir="1" center= "1"></ball>  
<player1 p1_px="267.75390625" p2_px="204.51171875"></player1>
<player2 p2_px"267.75390625" p2_py="204.51171875"></player2>  </Pongstate>
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
