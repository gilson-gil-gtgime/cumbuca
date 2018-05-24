<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:template title="Receitas">

	<h1>Receitas cadastradas</h1>
	<table class="table">
		<tr>
			<th>Nome</th>
			<th>Ingredientes</th>
			<th></th>
		</tr>
		<c:forEach items="${recipes }" var="r">
			<tr>
				<td>${r.name }</td>
				<td>${r.commaSeparatedFoods() }</td>
				<td>
					<c:url value="/recipe/edit/${r.id }" var="link"/>
					<a href="${link}" class="btn btn-primary btn-sm">Editar</a>
					<button type="button" onclick="codigo.value = ${r.id }" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#excluirModal">
	 					Excluir
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- Modal -->
	<div class="modal fade" id="excluirModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        Realmente deseja excluir a receita ?
	      </div>
	      <div class="modal-footer">
	      	<c:url value="/recipe/delete" var="action"/>
	      	<form action="${action }" method="post">
	      		<input type="hidden" id="codigo" name="id">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		        <button type="submit" class="btn btn-danger">Excluir</button>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>

</tags:template>