<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h3>Configura la sezione monitor</h3>
<%@include file="modal.jsp"%>
<c:url var="url" value="setting" />

<form:form class="form-horizontal" method="post" action="${url}" modelAttribute="appsettingBean">
  <div class="form-group">
    <label for="refreshpagemonitor" class="col-sm-2 control-label">Refresh pagina monitor (in secondi) </label>
    <div class="col-sm-10">
      <form:input type="number" class="form-control" id="refreshpagemonitor" path="refreshpagemonitor" placeholder="Refresh pagina monitor (in secondi) " />
    </div>
  </div>
  <div class="form-group">
    <label for="numberrowstosee" class="col-sm-2 control-label">Numero righe da visualizzare</label>
    <div class="col-sm-10">
      <form:input type="number" class="form-control" id="numberrowstosee" path="numberrowstosee" placeholder="Numero righe da visualizzare"/>
    </div>
  </div>
  <div class="form-group">
    <label for="minutetodefinecritic" class="col-sm-2 control-label">Indicazione attivit&agrave; critica (in minuti)</label>
    <div class="col-sm-10">
      <form:input type="number" class="form-control" id="minutetodefinecritic" path="minutetodefinecritic" placeholder="Indicazione attivit&agrave; critica (in minuti)"/>
    </div>
  </div>
  <form:input type="hidden" class="form-control" id="id" path="id" />
  
  <div class="form-group">
    <div class="col-sm-offset-1 col-sm-10">
      <button type="submit" class="btn btn-default">Salva</button>
    </div>
  </div>
</form:form>
<c:if test="${not empty(message)}">
	<script type="text/javascript"> 
		$("#dialog-confirm").modal()
	</script>
</c:if>
 	

