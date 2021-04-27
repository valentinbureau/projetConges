<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath }" />
<c:if test="${pageContext.request.userPrincipal.name != null }">
	<div class="d-inline-block">
		<span>Bonjour ${pageContext.request.userPrincipal.name} </span>
		<form action="${ctx}/logout" method="post" class="d-inline-block">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<button type="submit" class="btn btn-link">deconnection</button>
		</form>
	</div>
</c:if>
