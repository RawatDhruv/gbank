package gbank

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class TransactionService {

    CustomerService customerService
    AccountService accountService

    def list(GrailsParameterMap params) {
        params.max = params.max ?: GlobalConfig.itemsPerPage()
        List<Transaction> transactionList = Transaction.createCriteria().list(params) {
            if (params?.colName && params?.colValue) {
                like(params.colName, "%" + params.colValue + "%")
            }
            if (!params.sort) {
                order("id", "desc")
            }
        }
        return [list: transactionList, count: transactionList.totalCount]
    }

    def transfer(GrailsParameterMap params) {
        Transaction transfer = new Transaction(params)
        def toAccount = Account.get(params.toAccount)
        def fromAccount = Account.get(params.fromAccount)
        def amount = Double.parseDouble(params.amount);
        if(fromAccount.getBalance()<amount){
            println(error)
        } else {
            fromAccount.setBalance(fromAccount.getBalance() - amount)
            toAccount.setBalance(toAccount.getBalance() + amount)
        }
        //requires account update statement
        def response = AppUtil.saveResponse(false, transfer)
//            toaccount.save(flush: true)
//            fromAccount.save(flush:true)
            accountService.update(toAccount)
            accountService.update(fromAccount)
         if (transfer.validate()) {
             transfer.save(flush: true)
             if (!transfer.hasErrors()) {
                 response.isSuccess = true
             }
         }
        return response
    }

}
