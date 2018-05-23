<html>
<head>
<title>Message wall with names</title>
</head>
<body>

	<%@ page import="java.util.*"%>


	<%! List<Users> usrs = new ArrayList(); 
	class Users{
		String user;
		boolean isX;
		
		public Users(String u, boolean p){ user = u; isX = p;};
		public String get_user() { return user;}
		public boolean get_isX() { return isX;}
	};

%>

	<%! boolean userX = false;
	boolean userY = false;
	int nbuser = 0;
	int nbplay=0;
	
	

%>

	<%! 
	//List<MyPlays> plays = new ArrayList();
	MyPlays[][] tab = new MyPlays[3][3];
	
	class MyPlays{
		String player;
		String pos;
		String charac="";
		public MyPlays(){
			
		};
		public MyPlays(String pl, String p, String c){ player = pl; pos = p; charac = c;};
		public String get_pos() { return pos;};
		public String get_player() {return player;};
		public String get_charac() {return charac;};
		public void set_charac(String charac) { this.charac = charac;};
		
		
	};
	public void solverTicTacToe(int x, int y, String sign){
		
		
		
		 if(tab[x][y].get_charac().equals("") && nbplay <= 9){
			 tab[x][y].set_charac(sign);
	        }
		
		
	}
	
	public void initTab(){
		
		for(int i=0; i < tab.length; i++){
			for(int j=0; j < tab[i].length; j++){
				tab[i][j] = new MyPlays();
			}
		}
	}
%>

	<% if(usrs.size() < 2){ %>
	<% if(request.getParameter("username")!=null){
		String play="";
		session.setAttribute("username",request.getParameter("username"));
		if(request.getParameter("userX") != null){
			
			play ="x";
			usrs.add(new Users(session.getAttribute("username").toString(), true));
			userX = true;
		}
		
		else if(request.getParameter("userY") != null){
			play ="o";
			usrs.add(new Users(session.getAttribute("username").toString(), false));
			userY = true;
		}
	
		
		session.setAttribute("username",play);
	}
	initTab();
	}
	%>


	<% if(session.getAttribute("username") == null) {%>

	<% if(userX == false) {%>
	Play as player X:
	<form method="post">
		<input type="hidden" id="userX" name="userX" /> Name : <input
			type="text" name="username" /> <input type="submit" value="Play" />
	</form>
	<%} %>

	<% if(userY == false) {%>
	Play as player O:
	<form method="post">
		<input type="hidden" id="userY" name="userY" /> Name : <input
			type="text" name="username" /> <input type="submit" value="Play" />
	</form>
	<%} %>

	<% } else {%>


	Tic tac toe
	<hr />
	<br /> You are currently playing as
	<% if(session.getAttribute("username").equals("x")) { %>

	player X

	<%}
	if(session.getAttribute("username").equals("o")){ %>

	player Y

	<%}%>

	<br />
	<br /> Players:
	<br />
	<% for(int i=0; i<usrs.size(); i++){ %>
	<%if(usrs.get(i).get_isX() == true) {%>
	X :
	<%} %>
	<%if(usrs.get(i).get_isX() == false) {%>
	Y :
	<%} %>
	&nbsp;
	<%=usrs.get(i).get_user() %>


	<br <br />
		<br
		<br />
	<% } %>
	
		<%
	
	if(request.getParameter("row")!=null && request.getParameter("column") != null){
		
		solverTicTacToe(Integer.parseInt(request.getParameter("row")), Integer.parseInt(request.getParameter("column")), session.getAttribute("username").toString());
		
		
		
	}
	
	%>
		<% 	if(usrs.size() <= 2){%>
		<table width=30% height=30% border=1>
<tr><td width=33%><%= tab[0][0].get_charac()%></td><td width=33%><%= tab[0][1].get_charac()%></td>
<td width=34%><%= tab[0][2].get_charac()%></td></tr>
<tr><td width=33%><%= tab[1][0].get_charac()%></td><td width=33%><%= tab[1][1].get_charac()%></td>
<td width=34%><%= tab[1][2].get_charac()%></td></tr>
<tr><td width=33%><%= tab[2][0].get_charac()%></td><td width=33%><%= tab[2][1].get_charac()%></td>
<td width=34%><%= tab[2][2].get_charac()%></td></tr>
</table>
		<br <br /> Current status : to play
		<form method="post">
		 Row : <input
			type="text" name="row" /> <br> Column : <input
			type="text" name="column" /> <br> <input type="submit"
			value="Play" />
	</form>
	
	<% }%>
 	<%= session.toString() %>
		<% } %>
</body>
</html>
