package com.thucchien02.borrowingservice.command.saga;

import com.thucchien02.borrowingservice.command.event.BorrowingCreatedEvent;
import com.thucchien02.commonservice.model.BookResponseCommonModel;
import com.thucchien02.commonservice.query.queries.GetBookDetailQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class BorrowingSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    private void handle(BorrowingCreatedEvent event) {
        log.info("BorrowCreatedEvent in Saga for BookId : "+event.getBookId()+ " : EmployeeId :  "+event.getEmployeeId());
        try {
            GetBookDetailQuery getDetailsBookQuery = new GetBookDetailQuery(event.getBookId());
            BookResponseCommonModel bookResponseModel = queryGateway.query(getDetailsBookQuery, ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();
            if(bookResponseModel.getIsReady() == true) {
                throw new Exception("Sach da co nguoi muon !");
            }
        } catch ()
    }
}
