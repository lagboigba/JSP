<html>
<head> <title> Message wall with names </title> </head>
<body>

<%@ page import="java.util.*" %>

<%! 
	List<MyMessage> msgs = new ArrayList(); 
	class MyMessage{
		String sender;
		String msg;
		String receiver;
		public MyMessage(String s, String m, String r){ sender = s; msg = m; receiver = r;};
		public String get_msg() { return msg;};
		public String get_sender() {return sender;};
		public String get_receiver() {return receiver;};
	};

%>
	
<%! List<Users> usrs = new ArrayList(); 
	class Users{
		String user;
		String pass;
		String realname;
		public Users(String u, String p, String rn){ user = u; pass = p; realname = rn; };
		public String get_user() { return user;};
		public String get_realname() {return realname;};
	};

%>


<% if(request.getParameter("username")!=null){session.setAttribute("username",request.getParameter("username"));
session.setAttribute("password",request.getParameter("password"));
session.setAttribute("realname",request.getParameter("realname"));
}%>


<% if(session.getAttribute("username")==null) { %>

In order to participate, tell us your name:
<form method="post">
Username :	<input type="text" name="username"/> <br>
Password : <input type="text" name="password"/> <br>
Real name : <input type="text" name="realname"/> <br>
<input type="submit" value="Enter"/>
</form>


<% } else {
usrs.add(new Users(session.getAttribute("username").toString(), session.getAttribute("password").toString(), session.getAttribute("realname").toString()));%>


<% if(request.getParameter("newmsg")!="" && request.getParameter("newmsg")!=null){
	msgs.add(new MyMessage(session.getAttribute("username").toString(),request.getParameter("newmsg"), request.getParameter("receiver").toString()));
}
%>

Messages so far: <br />

<% for(int i=0; i<msgs.size(); i++){ %>
<%=msgs.get(i).get_sender() %>:&nbsp; <%= msgs.get(i).get_msg() %> <br />
<% } %>

<hr />
<br />

Users' list:
<br />
<br />
<% for(int i=0; i<usrs.size(); i++){ %>
<%=usrs.get(i).get_user() %>:&nbsp; <%= usrs.get(i).get_realname() %> <br />
<% } %>

<br />
<br />
Add a message:
<br />

<form method="post">
Current user: <%= session.getAttribute("username") %> <br />
Enter your message <input type="text" name="receiver"/> <br />
Choose the receiver <input type="text" name="newmsg"/> <br /> 
<input type="submit" value="Add"/><br />
</form>


<% } %>

</body>
</html>
