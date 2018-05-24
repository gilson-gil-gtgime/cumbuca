<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:template title="Cadastro de Estacionamento">

	<h1>Receitas cadastradas</h1>
	<table class="table">
		<tr>
			<th>Nome</th>
			<th>Ingredientes</th>
		</tr>
		<c:forEach items="${recipes }" var="r">
			<tr>
				<td>${r.name }</td>
				<td>${r.commaSeparatedFoods() }</td>
			</tr>
		</c:forEach>
	</table>

</tags:template>