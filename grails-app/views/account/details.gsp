<meta name="layout" content="main"/>

<div class="card">
    <div class="card-header">
        <g:message code="Account Details" args="['Details']"/>
    </div>
    <div class="card-body">
        <g:if test="${accountInstance}">
            <table class="table">
                <tr>
                    <th class="text-right">ID</th><td class="text-left">${accountInstance.id}</td>
                </tr>
                <tr>
                    <th class="text-right">Name</th><td class="text-left">${accountInstance.name}</td>
                </tr>
                <tr>
                    <th class="text-right">balance</th><td class="text-left">${accountInstance.balance}</td>
                </tr>
            </table>
        </g:if>
        <div class="form-action-panel">
            <g:link controller="account" action="index" class="btn btn-primary"><g:message code="cancel"/></g:link>
        </div>
    </div>
</div>