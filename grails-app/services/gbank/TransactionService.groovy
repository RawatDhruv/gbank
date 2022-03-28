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
        def response = AppUtil.saveResponse(false, transfer)
        def toAccount = Account.get(params.toAccount)
        def fromAccount = Account.get(params.fromAccount)
        if(!toAccount){
            println("To Account doesnt exist")
            return response
        }
        println("amount value is: " )
        println(params.amount)
        def amount = Double.parseDouble(params.amount);

        if(fromAccount.getBalance()<amount){
            println("inside validation ")
            return response
        } else {
            fromAccount.setBalance(fromAccount.getBalance() - amount)
            toAccount.setBalance(toAccount.getBalance() + amount)
        }
        //requires account update statement

//            toaccount.save(flush: true)
//            fromAccount.save(flush:true)
            accountService.update(toAccount)
             println("between updates")
            accountService.update(fromAccount)
         if (transfer.validate()) {
             transfer.save(flush: true)
             if (!transfer.hasErrors()) {
                 response.isSuccess = true
             }
         }
        println("final response ")
        return response
    }

}
