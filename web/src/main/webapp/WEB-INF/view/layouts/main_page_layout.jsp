<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.02.2020
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
    <title><tiles:getAsString name="title"/></title>
</head>
<body>
<table>
    <tr>
        <td colspan="5">
            <tiles:insertAttribute name="header"/>
        </td>
    </tr>
    <tr>
        <td colspan="5">
            <tiles:insertAttribute name="body"/>
        </td>
    </tr>
    <tr>
        <td colspan="5">
            <tiles:insertAttribute name="footer"/>
        </td>
    </tr>
</table>
</body>
</html>