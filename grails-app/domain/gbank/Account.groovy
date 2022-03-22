package gbank

class Account implements Serializable {
    static belongsTo = [customer: Customer]

    String name
//    String id
    Double balance = 0

    static mapping = {
//        id generator: "uuid" , length: 16
    }

    static constraints = {
        name blank: false
    }
}