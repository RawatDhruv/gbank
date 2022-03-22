package gbank

class CustomerController {

    CustomerService customerService

    def index() {
        def response = customerService.list(params)
        [customerList: response.list, total: response.count]
    }

    def details(Integer id) {
        def response = customerService.getById(id)
        if (!response) {
            redirect(controller: "customer", action: "index")
        } else {
            [customer: response]
        }
    }

    def create() {
        [customer: flash.redirectParams]
    }

    def save() {
        def response = customerService.save(params)
        if (!response.isSuccess) {
            flash.redirectParams = response.model
            flash.message = AppUtil.infoMessage(g.message(code: "unable.to.save"), false)
            redirect(controller: "customer", action: "create")
        }else{
            flash.message = AppUtil.infoMessage(g.message(code: "saved"))
            redirect(controller: "customer", action: "index")
        }
    }


    def update() {
        def response = customerService.getById(params.id)
        if (!response) {
            flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
            redirect(controller: "customer", action: "index")
        } else {
            response = customerService.update(response, params)
            if (!response.isSuccess) {
                flash.redirectParams = response.model
                flash.message = AppUtil.infoMessage(g.message(code: "unable.to.update"), false)
                redirect(controller: "customer", action: "edit")
            } else {
                flash.message = AppUtil.infoMessage(g.message(code: "updated"))
                redirect(controller: "customer", action: "index")
            }
        }
    }

    def delete(Integer id) {
        def response = customerService.getById(id)
        if (!response) {
            flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
            redirect(controller: "customer", action: "index")
        } else {
            response = customerService.delete(response)
            if (!response) {
                flash.message = AppUtil.infoMessage(g.message(code: "unable.to.delete"), false)
            } else {
                flash.message = AppUtil.infoMessage(g.message(code: "deleted"))
            }
            redirect(controller: "customer", action: "index")
        }
    }


}


