package gbank

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class AccountService {

    CustomerService customerService

    def list(GrailsParameterMap params) {
        params.max = params.max ?: GlobalConfig.itemsPerPage()
        List<Account> accountList = Account.createCriteria().list(params) {
            if (params?.colName && params?.colValue) {
                like(params.colName, "%" + params.colValue + "%")
            }
            if (!params.sort) {
                order("id", "desc")
            }
        }
        return [list: accountList, count: accountList.totalCount]
    }

    def getById(Serializable id) {
        return Account.get(id)
    }

    def save(GrailsParameterMap params) {
        Account account = new Account(params)
        print params
        //ch.qos.logback.classic.Logger.info(params)
//        def customer = customerService.getById(params.customer_id)
        def customer = Customer.get(params.customer_id)
        def name = customer.firstName + customer.lastName
        account.customer = customer
        account.name = name
        def response = AppUtil.saveResponse(false, customer)
        if (account.validate()) {
            account.save(flush: true)
            if (!account.hasErrors()){
                response.isSuccess = true
            }
        }
        return response
    }

//    def update(Account account, GrailsParameterMap params) {
//        account.properties = params
//        def response = AppUtil.saveResponse(false, customer)
//        if (account.validate()) {
//            account.save(flush: true)
//            if (!account.hasErrors()){
//                response.isSuccess = true
//            }
//        }
//        return response
//    }

    def update(Account account) {
        def response = AppUtil.saveResponse(false, account)
        if (account.validate()) {
            account.save(flush: true)
            if (!account.hasErrors()){
                response.isSuccess = true
            }
        }
        return response
    }

    def withdraw(GrailsParameterMap params) {
        def amount = Double.parseDouble(params.amount);
        def account = Account.get(params.id)
        def response = AppUtil.saveResponse(false, account)
        if (account.getBalance()>=amount) {
            def newBalance = account.getBalance() - amount
            account.setBalance(newBalance)
            account.save(flush: true)
            if (!account.hasErrors()){
                response.isSuccess = true
            }
        }
        return response
    }

    def deposit(GrailsParameterMap params) {
        def amount = Double.parseDouble(params.amount);
        def account = Account.get(params.id)
        def response = AppUtil.saveResponse(false, account)
            def newBalance = account.getBalance() + amount
            account.setBalance(newBalance)
            account.save(flush: true)
            if (!account.hasErrors()){
                response.isSuccess = true
            }
        return response
    }

}
