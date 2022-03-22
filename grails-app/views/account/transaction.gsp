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
        <g:if test="${type == 'transfer'}">
        <g:form controller="transaction" action="${type}">
                    <div class="form-group">
                        <label><g:message code="toAccount"/> *</label>
                        <g:textField name="toAccount" class="form-control" value="${transaction?.toAccount}" placeholder="Please Enter Reciever's Account ID"/>
                        <UIHelper:renderErrorMessage fieldName="toAccount" model="${transaction}" errorMessage="please.enter.recieverID"/>
                    </div>

                    <div class="form-group">
                        <label><g:message code="amount"/> *</label>
                        <g:field type="number" name="amount" class="form-control" value="${transfer?.amount}" placeholder="Please Enter Amount"/>
                        <UIHelper:renderErrorMessage fieldName="Amount" />
                    </div>
                    <g:hiddenField type="number" name="fromAccount" value="${accountInstance.id}" />
                        <div class="form-action-panel">
                            <g:submitButton class="btn btn-primary"  name="${type}" value="${g.message(code: "${type}")}"/>
                        </div>
        </g:form>
                </g:if>
        <div class="form-action-panel">
            <g:link controller="account" action="index" class="btn btn-primary"><g:message code="cancel"/></g:link>
        </div>
    </div>
</div>