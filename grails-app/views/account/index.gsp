      %{--Include Main Layout--}%
      <meta name="layout" content="main"/>

      <div class="card">
          <div class="card-header">
              <g:message code="account" args="['List']"/>

              %{--Actions--}%
              <span class="float-right">

              %{--Search Panel --}%
              <div class="btn-group">
                  <g:form controller="account" action="index" method="GET">
                      <div class="input-group" id="search-area">
                          <g:select name="colName" class="form-control" from="[id:'id' , customer_id : 'customer_id' ,name: 'name', balance: 'balance']" value="${params?.colName}" optionKey="key" optionValue="value"/>
                          <g:textField name="colValue" class="form-control" value="${params?.colValue}"/>
                          <span class="input-group-btn">
                              <button class="btn btn-default" type="submit">Search</button>
                          </span>
                      </div>
                  </g:form>
              </div>

              %{--Create and Reload Panel--}%
              <div class="btn-group">
                  <g:link controller="account" action="create" class="btn btn-success"><g:message code="create"/></g:link>
                  <g:link controller="account" action="index" class="btn btn-primary"><g:message code="reload"/></g:link>
              </div>
              </span>
          </div>

          %{--Table Panel--}%
          <div class="card-body">
              <table class="table table-bordered">
                  <thead class="thead-dark">
                  <tr>
                      <g:sortableColumn property="id" title="${g.message(code: "id")}"/>
                      <g:sortableColumn property="customer_id" title="${g.message(code: "customer_id")}"/>
                      <g:sortableColumn property="name" title="${g.message(code: "name")}"/>
                      <g:sortableColumn property="balance" title="${g.message(code: "balance")}"/>
                      <th class="action-row"><g:message code="action"/></th>
                  </tr>
                  </thead>
                  <tbody>
                      <g:each in="${accountList}" var="info">
                          <tr>
                              <td>${info?.id}</td>
                              <td>${info?.customerId}</td>
                              <td>${info?.name}</td>
                              <td>${info?.balance}</td>
                              <td>
                                    <div class="btn-group">
                                        <g:link controller="account" action="details" class="btn btn-secondary" id="${info.id}"><p>Details</p></g:link>
                                        <g:link controller="account" action="transaction" class="btn btn-secondary" id="${info.id}" params = "[type:'withdraw']" type='withdraw'><p>Withdraw</p></g:link>
                                        <g:link controller="account" action="transaction" class="btn btn-secondary" id="${info.id}" params = "[type:'deposit']" type="deposit"><p>Deposit</p></g:link>
                                        <g:link controller="transaction" action="transfer" class="btn btn-secondary" id="${info.id}"  type="transfer"><p>Transfer</p></g:link>

                                    </div>
                              </td>
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