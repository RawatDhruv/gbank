<meta name="layout" content="main"/>

<div class="card">
    <div class="card-header">
        <g:message code="Account Details" args="['Details']"/>
    </div>
    <div class="card-body">
        <g:if test="${accountInstance}">
            <table class="table">
                <tr>
                    <th >ID</th><td>${accountInstance.id}</td>
                </tr>
                <tr>
                    <th >Name</th><td>${accountInstance.name}</td>
                </tr>
                <tr>
                    <th >balance</th><td>${accountInstance.balance}</td>
                </tr>
            </table>
        </g:if>
        <g:if test="${type == 'withdraw'|| type == 'deposit'}">
        <g:form controller="account" action="${type}">
            <div class="form-group">
                <label><g:message code="amount"/> *</label>
                <g:field type="number" name="amount" value="${params.amount}" class="form-control"  placeholder="Please Enter Amount"/>
                <UIHelper:renderErrorMessage fieldName="Amount" />
            </div>
            <g:hiddenField type="number" name="id" value="${accountInstance.id}" />
                <div class="form-action-panel">
                    <g:submitButton class="btn btn-primary"  name="${type}" value="${g.message(code: "${type}")}"/>
                </div>
        </g:form>
        </g:if>

        <g:if test="${flash.message}">
           <div class="update_message" role="status">${flash.message}</div>
        </g:if>
        <div class="form-action-panel">
            <g:link controller="account" action="index" class="btn btn-primary"><g:message code="cancel"/></g:link>
        </div>
    </div>
</div>