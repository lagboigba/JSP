<html>
<head> <title> Message wall with names </title> </head>
<body>

<%@ page import="java.util.*" %>

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


<% if(request.getParameter("username")!=null) session.setAttribute("username",request.getParameter("username")); %>


<% if(session.getAttribute("username")==null) { %>

Play as player X:
<form method="post">
Name : <input type="text" name="username"/> <input type="submit" value="Play"/>
</form>

Play as player O:
<form method="post">
Name : <input type="text" name="username"/> <input type="submit" value="Play"/>
</form>


<% } else {%>



<hr />
<br />
Add a message:
<br />

Change your name:
<form method="post">
<input type="text" name="username"/> <input type="submit" value="Enter"/>
</form>


<% } %>

</body>
</html>
