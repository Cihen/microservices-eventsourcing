package com.thucchien02.bookservice.query.projection;

import com.thucchien02.bookservice.command.data.Book;
import com.thucchien02.bookservice.command.data.BookRepository;
import com.thucchien02.bookservice.query.model.BookResponseModel;
import com.thucchien02.bookservice.query.queries.GetAllBookQuery;
import com.thucchien02.bookservice.query.queries.GetBookDetailQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookProjection {

    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query) {
        List<Book> list = bookRepository.findAll();
        return list.stream()
                .map(book -> {
                    BookResponseModel model = new BookResponseModel();
                    BeanUtils.copyProperties(book, model);
                    return model;
                })
                .collect(Collectors.toList());
    }

    @QueryHandler
    public BookResponseModel handle(GetBookDetailQuery query) {
        BookResponseModel result = new BookResponseModel();
        bookRepository.findById(query.getId())
                .ifPresent(book -> {
                    BeanUtils.copyProperties(book, result);
                });
        return result;
    }
}
