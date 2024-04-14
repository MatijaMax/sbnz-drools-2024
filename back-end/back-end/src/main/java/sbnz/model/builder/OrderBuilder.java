package sbnz.model.builder;

import sbnz.model.Customer;
import sbnz.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


public class OrderBuilder {
    
    private final Order instance;
    private Optional<OrderLineBuilder> orderLineBuilder = Optional.empty();
    
    public OrderBuilder(Customer customer) {
        this.instance = new Order();
        
        //default values for the new Order
        this.instance.setCustomer(customer);
        this.instance.setDate(new Date());
        this.instance.setOrderLines(new ArrayList<>());
        
    }

       
    public OrderBuilder withDate(Date date){
        this.instance.setDate(date);
        return this;
    }
    
    public OrderLineBuilder newLine(){
        if (this.orderLineBuilder.isPresent()){
            this.instance.getOrderLines().add(this.orderLineBuilder.get().build());
        }
        this.orderLineBuilder = Optional.of(new OrderLineBuilder(this));
        return this.orderLineBuilder.get();
    }
    
    public Order build(){
        if (this.orderLineBuilder.isPresent()){
            this.instance.getOrderLines().add(this.orderLineBuilder.get().build());
        }
        return this.instance;
    }
    
    public OrderBuilder end(){
        return this;
    }
}
