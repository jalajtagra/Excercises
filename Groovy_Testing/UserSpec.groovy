package com.im.test

import spock.lang.Specification

class UserSpec extends Specification {

    /* def "First test"(){
         expect:
         true
     }*/

    def "Ful name is calculated with first name and last name"() {
        setup:
        User user = new User(firstName: fName, lastName: lName)
        when:
        String name = user.fullName;
        then:
        name == expectedOutput
        where:
        fName   | lName   | expectedOutput
        "Jalaj" | "Tagra" | "Jalaj Tagra"

    }


    def "Getting prefix based on gender"() {
        setup:
        User user = new User(gender: gender)
        when:
        String name = user.displayName()
        then:
        name.startsWith(prefix)
        where:
        gender   | prefix
        "Female" | "Ms"
        "Male"   | "Mr"
    }

    def "Testing weather the password is valid or not"() {
        setup:
        User user = new User()
        String password = passwd
        when:
        Boolean result = user.isValidPassword(password)
        then:
        result == output
        where:
        passwd      | output
        "djkhfkjah" | true
        "242342"    | false

    }

    def "Checking wether password is reset "() {
        setup:

        def user1 = Spy(User)
        String password = "dfgkhjdsfj"
        user1.password = password
        user1.encyryptPassword(_ as String) >> "1@#GHKJGK"
        def emailService = Mock(EmailService)
//              1 * emailService.sendCancellationEmail(_ as User,_ as String)
        when:
        user1.resetPasswordAndSendEmail()
        then:
        println user1.password != password

    }

    def "checking class according to income group"() {
        setup:
        User user = new User(incomePerMonth: income);
        when:
        String output = user.getIncomeGroup()
        then:
        output == expectedResult

        where:
        income | expectedResult
        1000   | "MiddleClass"
        10000  | "Higher MiddleClass"
        7000   | "MiddleClass"

    }

    def "checking wether product list is incremented upon purchasing"() {
        setup:
        User user = new User();
        when:
        user.purchase(new Product())
        then:
        user.purchasedProducts.size() == 1
    }

    def "checking wether product list is decremented upon purchasing"() {
        setup:
        User user = new User();
        Product p = new Product()
        user.purchase(p)
        when:
        user.cancelPurchase(p)
        then:
        user.purchasedProducts.size() == 0
    }


    def "testing wether the password is getting encrypted or not"() {
        setup:

        def user = new User(password: "dflklsdfl")
        def passwordEncrypterService = Mock(PasswordEncrypterService)
        passwordEncrypterService.encrypt() >> "1@^%^&GHJG"
        when:
        String encryptedPasswd = user.encyryptPassword()
        then:
        println user.password != encryptedPasswd

    }


}
