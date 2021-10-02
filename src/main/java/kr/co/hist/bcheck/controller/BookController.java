package kr.co.hist.bcheck.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.hist.bcheck.dto.*;
import kr.co.hist.bcheck.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "[010] 도서 관리(도서 조회, 추가, 수정, 삭제)", description = "도서 추가,수정,삭제")
@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {

    final BookService bookService;

    @Operation(description = "ISBN 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ISBN 검색을 통해 책 정보를 리턴한다",
                    content = @Content(schema = @Schema(implementation = IsbnResponse.class)))
    })
    @GetMapping(value="/isbn", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> isbnSearch(
           @Parameter(description = "ISBN No.", example="isbn-123456") @RequestParam(required = true) String isbn
    ) {
        return ResponseEntity.ok(bookService.search(isbn));
    }
}
