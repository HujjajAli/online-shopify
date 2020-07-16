
<p class="lead">Shop Name</p>
   <div class="list-group">
   		<c:forEach items="${categories}" var="r">	
        	<a href="${contextRoot}/show/category/${r.id}" class="list-group-item">${r.name}</a>
        </c:forEach>
   </div>