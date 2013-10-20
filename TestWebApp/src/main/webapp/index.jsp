<%@page import="in.mum.saiyyer.testwebapp.main.TestH2Connection"%>
<html>
<body>
<h2>Hello World!</h2>
<%= new TestH2Connection().getEmployees() %>
</body>
</html>
