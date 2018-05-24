<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:template title="Cadastro de Receita">
	
	<h1>Cadastro de receita</h1>
	${msg }
	<c:url value="/recipe/register" var="action"/>
	<form:form action="${action }" method="post" commandName="recipeForm">
		<div class="form-group">
			<form:label path="name">Nome</form:label>
			<form:input path="name" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<form:label path="foods">Alimentos (separados por vírgula ",")</form:label>
			<form:input path="foods" cssClass="form-control"/>
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
	</form:form>

</tags:template>