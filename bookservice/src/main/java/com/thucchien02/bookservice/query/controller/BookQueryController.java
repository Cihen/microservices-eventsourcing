package com.thucchien02.bookservice.query.controller;

import com.thucchien02.commonservice.model.BookResponseCommonModel;
import com.thucchien02.bookservice.query.queries.GetAllBookQuery;
import com.thucchien02.commonservice.query.GetBookDetailQuery;
import com.thucchien02.commonservice.service.KafkaService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @Autowired
    private KafkaService kafkaService;


    @GetMapping
    public List<BookResponseCommonModel> getAllBooks() {
        GetAllBookQuery query = new GetAllBookQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(BookResponseCommonModel.class)).join();
    }

    @GetMapping("/{bookId}")
    public BookResponseCommonModel getBookDetail(@PathVariable String bookId) {
        GetBookDetailQuery query = new GetBookDetailQuery(bookId);
        return queryGateway.query(query, ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String message){
        kafkaService.sendMessage("test", message);
    }
}
