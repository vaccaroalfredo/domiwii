<style type="text/css">
		.tile{
			
			width:100%;
			text-align: center;
			vertical-align: middle;
			margin-left: 15px;
			margin-bottom: 15px;
			border-radius:10px;
		}
		.tile span {
		    display: block;
		    padding-top: calc(50% - 30px);
		    text-align: center;
		    line-height: 1em;
		}
</style>

 <div class="container" style="height:100%">
  		<c:choose>
   		<c:when test="${sessionScope.userSessionInfo.isAmministrator()}">
			<div class="row">
		      <div class="col-md-3 col-md-offset-3">
			    <div>
			      <a href="<c:url value="/home/monitor"/>" class="btn tile" style="background: #357CE0;color:#E9E9E9;height:230px;">
			      <i style="padding: 50px 0px 10px 0px;font-size:50px" class="fa fa-television" aria-hidden="true"></i>
			      <span style="padding: 0px">Monitor attivit&agrave;</span>
			      </a>
			    </div>
			  </div>
		      <div class="col-md-3">
		      	<div>
		        <a href="<c:url value="/home/note"/>" class="btn tile" style="background: #4AD355;color:#E9E9E9;height:230px;">
		        	<i style="padding: 50px 0px 10px 0px;font-size:50px" class="fa fa-sticky-note-o" aria-hidden="true"></i>
		        	<span style="padding: 0px">Monitor note</span></a>
		        </div>
			  </div>
		    </div>
		    <div class="row">
		      <div class="col-md-3 col-md-offset-3">
		      	<div>
			      	<a href="<c:url value="/home/activity"/>" class="btn tile" style="background: #F43552;color:#E9E9E9;height:230px;">
			      	<i style="padding: 50px 0px 10px 0px;font-size:50px" class="fa fa-calendar" aria-hidden="true"></i>
			      	<span style="padding: 0px;">Gestione attivit&agrave;</span></a>
		      	</div>
		      </div>
		      <div class="col-md-3">
			    <div>
			    <a href="<c:url value="/home/setting"/>" class="btn tile" style="background: #E9E9E9;color:#000000;height:230px;">
			    	<i style="padding: 50px 0px 10px 0px;font-size:50px" class="fa fa-cog" aria-hidden="true"></i>
            		<span style="padding: 0px;">Impostazioni</span>
            	</a>
			   	</div>
			  </div>
		    </div>  
		</c:when>
		<c:otherwise>
			<div class="row">
		      <div class="col-md-3 col-md-offset-3">
		       <div>
			       <a href="<c:url value="/home/monitor"/>" class="btn tile" style="background: #357CE0;color:#E9E9E9;height:230px;">
				       <i style="padding: 50px 0px 10px 0px;font-size:50px" class="fa fa-television" aria-hidden="true"></i>
				       <span style="padding: 0px;">Monitor attivit&agrave;</span>
			       </a>
		       </div>
		      </div>
		      <div class="col-md-3">
		        <div>
			        <a href="<c:url value="/home/note"/>" class="btn tile" style="background: #4AD355;color:#E9E9E9;height:230px;">
				        <i style="padding: 50px 0px 10px 0px;font-size:50px" class="fa fa-sticky-note-o" aria-hidden="true"></i>
				        <span style="padding: 0px;">Monitor note</span>
			        </a>
		        </div>
			  </div>
		      <div class="col-md-6 col-md-offset-3">
		      	<div>
			        <a href="<c:url value="/home/activity"/>" class="btn tile" style="background: #F43552;color:#E9E9E9;height:230px;">
			        	<i style="padding: 50px 0px 10px 0px;font-size:50px" class="fa fa-calendar" aria-hidden="true"></i>
			        	<span style="padding: 0px;">Gestione attivit&agrave;</span>
			        </a>
		        </div>
		      </div>
		    </div> 
		</c:otherwise>
	</c:choose>
</div>
<script type="text/javascript">
	$(".tile").hover(function(){
					$(this).find("i").css("font-size","80px");
			}, function(){
					$(this).find("i").css("font-size","50px");
		    });
</script>
       
