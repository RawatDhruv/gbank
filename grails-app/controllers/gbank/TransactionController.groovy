package gbank

class TransactionController {

    AccountService accountService
    TransactionService transactionService

    def index() {
        def response = transactionService.list(params)
        [transactionList: response.list, total: response.count]
    }

    def transaction(Long id) {
        def response = accountService.getById(id)
        println(params.type)
        if (!response) {
            redirect(controller: "account", action: "index")
        } else {
            [transactionInstance: response , type : params.type]
        }
    }

    def transfer(){
        def response = transactionService.transfer(params)
        if (!response.isSuccess) {
            flash.redirectParams = response.model
            flash.message = AppUtil.infoMessage(g.message(code: "unable.to.transfer"), false)
            redirect(controller: "account", action: "create")
        }else{
            flash.message = AppUtil.infoMessage(g.message(code: "Transfer Succesful"))
            redirect(controller: "account", action: "index")
        }
    }

}
