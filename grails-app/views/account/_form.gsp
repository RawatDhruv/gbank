<div class="form-group">
    <label><g:message code="customer_id"/> *</label>
    <g:textField name="customer_id" class="form-control" value="${acccount?.customer_id}" placeholder="Please Enter Customer ID"/>
    <UIHelper:renderErrorMessage fieldName="customer_id" model="${account}" errorMessage="please.enter.customer_id"/>
</div>

<div class="form-group">
    <label><g:message code="balance"/> *</label>
    <g:field type="number" name="balance" class="form-control" value="${account?.balance}" placeholder="Please Enter Balance"/>
    <UIHelper:renderErrorMessage fieldName="balance" model="${account}" errorMessage="Input is not Valid "/>
</div>
