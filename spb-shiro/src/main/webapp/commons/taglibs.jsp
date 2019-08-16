<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  


<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="year" value="<%=Calendar.getInstance().get(Calendar.YEAR)%>"/>
<c:set var="socreHight" value="600"/>
<c:set var="socreLow" value="430"/>