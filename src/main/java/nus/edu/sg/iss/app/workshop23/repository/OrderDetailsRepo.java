package nus.edu.sg.iss.app.workshop23.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.edu.sg.iss.app.workshop23.model.OrderDetails;
import static nus.edu.sg.iss.app.workshop23.repository.DBQueries.*;

@Repository
public class OrderDetailsRepo {

    @Autowired
    JdbcTemplate jdbcTemplate; 

    public OrderDetails getOrderDetailsWithDiscount(Integer orderId){
        
        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(ORDER_DETAILS_WITH_DISCOUNT_QUERY, orderId);

        while(resultSet.next()){
            orderDetailsList.add(OrderDetails.create(resultSet));
        }
        return orderDetailsList.get(0); 
    }
    
}
