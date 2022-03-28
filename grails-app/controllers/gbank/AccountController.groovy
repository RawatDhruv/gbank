package gbank

import grails.web.servlet.mvc.GrailsParameterMap

class AccountController {


    AccountService accountService


    def index() {
        //println(params)
        def response = accountService.list(params)
        [accountList: response.list, total: response.count]
    }

    def create() {
        [accountInstance: new Account(params)]
    }

    def details(Long id) {
        def response = accountService.getById(id)
        if (!response) {
            redirect(controller: "account", action: "index")
        } else {
            [accountInstance: response]
        }
    }

    def save() {
        def response = accountService.save(params)
        if (!response.isSuccess) {
            flash.redirectParams = response.model
            flash.message = AppUtil.infoMessage(g.message(code: "unable.to.save"), false)
            redirect(controller: "account", action: "create")
        }else{
            flash.message = AppUtil.infoMessage(g.message(code: "saved"))
            redirect(controller: "account", action: "index")
        }
    }

    def transaction(Long id) {
        def response = accountService.getById(id)
//        println(params.type)
        if (!response) {
            redirect(controller: "account", action: "index")
        } else {
            [accountInstance: response , type : params.type]
        }
    }


    def withdraw(){
        def response = accountService.withdraw(params)
        if (!response.isSuccess) {
            flash.redirectParams = response.model
            flash.message = AppUtil.infoMessage(g.message(code: "unable.to.withdraw"), false)
            redirect(controller: "account", action: "transaction" , id:params.id , params:[type:"withdraw"])
        }else{
            flash.message = AppUtil.infoMessage(g.message(code: "Withdraw Succesful"))
            redirect(controller: "account", action: "index")
        }
    }

    def deposit(){
        def response = accountService.deposit(params)
        if (!response.isSuccess) {
            flash.redirectParams = response.model
            flash.message = AppUtil.infoMessage(g.message(code: "unable.to.deposit"), false)
            redirect(controller: "account", action: "transaction" , id:params.id , params:[type:"deposit"])
        }else{
            flash.message = AppUtil.infoMessage(g.message(code: "Deposit Succesful"))
            redirect(controller: "account", action: "index")
        }
    }




}
