package gbank

class TransactionController {

    AccountService accountService
    TransactionService transactionService

    def index() {
        def response = transactionService.list(params)
        [transactionList: response.list, total: response.count]
    }

    def transfer(Long id) {
        println("ID is ")
        print(id)
        def response = accountService.getById(id)
        if (!response) {
           redirect(controller: "account", action: "index")
        } else {
            [accountInstance: response]
        }
    }

    def transferUtility(){
        println("params are")
        print(params)
        def response = transactionService.transfer(params)
        if (!response.isSuccess) {
            println("failure redirect")
            flash.redirectParams = response.model
            flash.message = AppUtil.infoMessage(g.message(code: "unable.to.transfer"), false)
            println(" in TU")
            println(params.id)
            redirect(controller: "transaction", action: "transfer" , id:params.fromAccount )
        }else{
            println("success redirect")
            flash.message = AppUtil.infoMessage(g.message(code: "Transfer Succesful"))
            redirect(controller: "account", action: "index")
        }
    }

}
