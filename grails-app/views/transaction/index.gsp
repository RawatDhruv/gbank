%{--Include Main Layout--}%
<meta name="layout" content="main"/>

<div class="card">
    <div class="card-header">
        <g:message code="transaction" args="['List']"/>

        %{--Actions--}%
        <span class="float-right">

        %{--Search Panel --}%
        <div class="btn-group">
            <g:form controller="transaction" action="index" method="GET">
                <div class="input-group" id="search-area">
                    <g:select name="colName" class="form-control" from="[fromAccount: 'fromAccount', toAccount: 'toAccount', amount: 'amount']" value="${params?.colName}" optionKey="key" optionValue="value"/>
                    <g:textField name="colValue" class="form-control" value="${params?.colValue}"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Search</button>
                    </span>
                </div>
            </g:form>
        </div>

        %{--Create and Reload Panel--}%
        <div class="btn-group">
            <g:link controller="transaction" action="index" class="btn btn-primary"><g:message code="reload"/></g:link>
        </div>
        </span>
    </div>

    %{--Table Panel--}%
    <div class="card-body">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <g:sortableColumn property="fromAccount" title="${g.message(code: "fromAccount")}"/>
                <g:sortableColumn property="toAccount" title="${g.message(code: "toAccount")}"/>
                <g:sortableColumn property="amount" title="${g.message(code: "amount")}"/>

            </tr>
            </thead>
            <tbody>
                <g:each in="${transactionList}" var="info">
                    <tr>
                        <td>${info?.fromAccount}</td>
                        <td>${info?.toAccount}</td>
                        <td>${info?.amount}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    %{--Pagination Area--}%
    <div class="paginate">
        <g:paginate total="${total ?: 0}" />
    </div>
    </div>
</div>