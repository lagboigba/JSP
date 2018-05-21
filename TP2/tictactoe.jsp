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
%>

	<%! 
	List<MyPlays> plays = new ArrayList(); 
	class MyPlays{
		String player;
		String pos;
		String charac;
		public MyPlays(String pl, String p, String c){ player = pl; pos = p; charac = c;};
		public String get_pos() { return pos;};
		public String get_player() {return player;};
		public String get_charac() {return charac;};
		
	};

%>

	<% if(nbuser < 2){ %>
	<% if(request.getParameter("usernameX")!=null){
		session.setAttribute("usernameX",request.getParameter("usernameX"));
		usrs.add(new Users(session.getAttribute("usernameX").toString(), true));
		userX = true;
		nbuser++;
	}
	%>

	<% if(request.getParameter("usernameY")!=null ){
		session.setAttribute("usernameY",request.getParameter("usernameY"));
		usrs.add(new Users(session.getAttribute("usernameY").toString(), false));
		userY = true;
		nbuser++;
	}
			%>
	

	<% if(userX == false) {%>
	Play as player X:
	<form method="post">
		Name : <input type="text" name="usernameX" /> <input type="submit"
			value="Play" />
	</form>
	<%} %>

	<% if(userY == false) { %>
	Play as player O:
	<form method="post">
		Name : <input type="text" name="usernameY" /> <input type="submit"
			value="Play" />
	</form>
	<%} %>
<% }%>
	<% if (userX == true && userY == true){%>


	Tic tac toe
	<hr />
	<br /> You are currently playing as
	<% if(session.getAttribute("usernameX") != null) { %>

	player X

	<%}
	if(session.getAttribute("usernameY") != null){ %>

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
	
	<table width=30% height=30% border=1>
<tr><td width=33%></td><td width=33%></td>
<td width=34%></td></tr>
<tr><td width=33%></td><td width=33%></td>
<td width=34%></td></tr>
<tr><td width=33%></td><td width=33%></td>
<td width=34%></td></tr>
</table>

<br <br />

Current status : to play

	<form method="post">
		 Row : <input
			type="text" name="row" /> <br> Column : <input
			type="text" name="column" /> <br> <input type="submit"
			value="Play" />
	</form>
	
	
	<%= session.getAttribute("usernameX").toString() %>
	<%= session.getAttribute("usernameY").toString() %>
	<% } %>
</body>
</html>
