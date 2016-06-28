package com.im.test

import spock.lang.IgnoreRest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import spock.util.mop.ConfineMetaClassChanges

class TransactionSpec extends Specification {

    def "product is sold when balance is more than the product price"() {
        setup:
        Transaction trans = new Transaction();
        User user = new User(balance: balance, username: 'Jalaj');
        Product product = new Product(price: productPrice)
        when:
        trans.sell(product, user);
        then:
        user.purchasedProducts.size() == countOfPurchasedProducts;
        where:
        balance | productPrice | countOfPurchasedProducts
        100     | 50           | 1
        100     | 99           | 1
    }

    def "Exception is thrown when product price is more than user's balance"() {
        setup:
        Transaction trans = new Transaction();
        User user = new User(balance: 50, username: 'Jalaj');
        Product product = new Product(price: 100)
        when:
        trans.sell(product, user);
        then:
        thrown(SaleException);
//            stack.empty
    }

    def "User's balance is credited when sale is cancelled"() {
        setup:
        def transaction = Spy(Transaction)
        User user = new User(balance: 50, username: 'Jalaj');
        Product product = new Product(price: 100)

        transaction.calculateDiscount(_ as Product, _ as User) >> new BigDecimal(30)

        when:
        transaction.cancelSale(product, user);
        then:
        user.balance == 120
    }

    def "Testing discount value for different type of products and customers"() {
        setup:
        Transaction trans = new Transaction();
        User user = new User(balance: 50, username: 'Jalaj', isPrivellegedCustomer: isPrivCust);
        Product product = new Product(price: 100, discountType: discType)
        when:
        BigDecimal value = trans.calculateDiscount(product, user)
        then:
        value == output
        where:
        discType                     | isPrivCust | output
        DiscountType.NONE            | true       | 0
        DiscountType.PRIVELLEGE_ONLY | true       | 30
        DiscountType.PRIVELLEGE_ONLY | false      | 10

    }

    @ConfineMetaClassChanges(Product)
    def "Testing if all popular products are returned when getAllProducts is called"() {
        setup:
        Transaction trans = new Transaction();
        Product.metaClass.static.getCurrentProducts = {
            println "Hello"
            [new Product(price: 20, isPopular: true), new Product(price: 30, isPopular: true)]
        }
//            Product.getCurrentProducts()>>
        when:
        def list = trans.getAllPopularProducts();
        then:
        list.size() == 2


    }

}
