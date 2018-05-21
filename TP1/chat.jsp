<html>
<head>
<title>Message wall with names</title>
</head>
<body>

	<%@ page import="java.util.*"%>

	<%! 
	List<MyMessage> msgs = new ArrayList(); 
	class MyMessage{
		String sender;
		String msg;
		String receiver;
		Date datesend;
		public MyMessage(String s, String m, String r, Date d){ sender = s; msg = m; receiver = r; datesend =d;};
		public String get_msg() { return msg;};
		public String get_sender() {return sender;};
		public String get_receiver() {return receiver;};
		public Date get_date() {return datesend;};
	};

%>
	<%! int nbuser = 0;
%>

	<%! List<Users> usrs = new ArrayList(); 
	class Users{
		String user;
		String pass;
		String realname;
		public Users(String u, String p, String rn){ user = u; pass = p; realname = rn; };
		public String get_user() { return user;};
		public String get_realname() {return realname;};
		public String get_pass() {return realname;};
	};

%>


	<% if(request.getParameter("username")!=null){
		if (request.getParameter("connect") == null){
			session.setAttribute("username",request.getParameter("username"));
			session.setAttribute("password",request.getParameter("password"));
			session.setAttribute("realname",request.getParameter("realname"));
			
			nbuser++;
			usrs.add(new Users(session.getAttribute("username").toString(), session.getAttribute("password").toString(), session.getAttribute("realname").toString()));
			
		}else{
			boolean userFoundList = false;
			String userName ="";
			for(int i =0; i < usrs.size(); i++){
				if(usrs.get(i).get_user().equals(request.getParameter("username")) && usrs.get(i).get_pass().equals(request.getParameter("password"))){
					userFoundList = true;
					userName = usrs.get(i).get_realname();
				}
			}
			if(userFoundList == true){
				session.setAttribute("username",request.getParameter("username"));
				session.setAttribute("password",request.getParameter("password"));
				session.setAttribute("realname",userName);
			}else{
				
				response.sendRedirect("chat.jsp");
			}
		
		}


}%>


	<% if(session.getAttribute("username")==null) { %>

	In order to participate, tell us your name:
	<form method="post">
		Username : <input type="text" name="username" /> <br> Password :
		<input type="text" name="password" /> <br> Real name : <input
			type="text" name="realname" /> <br> <input type="submit"
			value="Enter" />
	</form>

	If you already have an account with us :

	<form method="post">
		<input type="hidden" id="connect" name="connect" /> Username : <input
			type="text" name="username" /> <br> Password : <input
			type="text" name="password" /> <br> <input type="submit"
			value="Enter" />
	</form>


	<% } else {
		
		
	%>


	<% if(request.getParameter("newmsg")!="" && request.getParameter("newmsg")!=null){
	msgs.add(new MyMessage(session.getAttribute("username").toString(),request.getParameter("newmsg"), request.getParameter("receiver"), new Date()));
}
		
%>

	You are logged in as
	<%= session.getAttribute("username") %>:
	<br />
	<hr />

	Users' list:
	<br /> Username &nbsp; - &nbsp; Realname
	<br />
	<% for(int i=0; i<usrs.size(); i++){ %>
	<%=usrs.get(i).get_user() %>
	&nbsp; - &nbsp;
	<%= usrs.get(i).get_realname() %>
	<br />
	<% } %>

	<hr />

	Messages to
	<%= session.getAttribute("username") %>:
	<br /> Date &nbsp; - &nbsp; Sender &nbsp; - &nbsp; Recipient &nbsp; -
	&nbsp; Message
	<br />
	<% for(int i=0; i<msgs.size(); i++){ if (msgs.get(i).get_receiver().equals(session.getAttribute("username").toString()) ||  msgs.get(i).get_sender().equals(session.getAttribute("username").toString())) { %>
	<%=msgs.get(i).get_date() %>&nbsp; - &nbsp;
	<%= msgs.get(i).get_sender() %>
	&nbsp; - &nbsp;
	<%=msgs.get(i).get_receiver()%>
	&nbsp; - &nbsp;
	<%=msgs.get(i).get_msg()%><br />
	<% }} %>

	<hr />
	<br /> Send a message:
	<br />

	<form method="post">
		Current user:
		<%= session.getAttribute("username") %>
		<br /> Enter your message <input type="text" name="newmsg" /> <br />
		Choose the receiver <input type="text" name="receiver" /> <br /> <input
			type="submit" value="Send" /><br />
	</form>
	<%
			if (request.getParameter("isDisconnect") !=null){
		
		session.invalidate();
		response.sendRedirect("chat.jsp");

	}
	 %>
	<form method="post">
		<input type="hidden" id="isDisconnect" name="isDisconnect" /> <input
			type="submit" value="logout" />

	</form>

	<% } %>

</body>
</html>
