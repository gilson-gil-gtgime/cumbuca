<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:template title="Cadastro de Receita">
	
	<h1>Edição de receita</h1>
	${msg }
	<c:url value="/recipe/edit" var="action"/>
	<form:form action="${action }" method="post" commandName="recipeForm">
		<form:hidden path="id" />
		<div class="form-group">
			<form:label path="name">Nome</form:label>
			<form:input path="name" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<form:label path="foods">Alimentos (separados por vírgula ",")</form:label>
			<form:input path="foods" cssClass="form-control"/>
		</div>
		<input type="submit" value="Editar" class="btn btn-primary">
		<c:url value="/recipe/list" var="link"/>
		<a href="${link }" class="btn btn-danger">Cancelar</a>
	</form:form>

</tags:template>