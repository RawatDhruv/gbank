package gbank

class Customer implements Serializable {

    transient springSecurityService

    static hasMany = [accounts: Account]

//    String id
    String firstName
    String lastName
    String email
    String password

    static mapping = {
//        id generator: "uuid" , length: 16 ,stratergy: org.hibernate.id.UUIDGenerator
    }

    static constraints = {
        firstName blank: false
        lastName blank: false
        email blank: false, unique: true
        password blank: false

    }

    def beforeInsert(){
        this.password = this.password.encodeAsMD5()
    }

    def beforeUpdate(){
        this.password = this.password.encodeAsMD5()
    }




}