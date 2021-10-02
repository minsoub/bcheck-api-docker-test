package kr.co.hist.bcheck.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ISBN 정보")
public class IsbnResponse {
    @Schema(description = "책제목", example = "OKGOSU")
    private String title;

    @Schema(description = "링크정보", example = "http://book.naver.com/bookdb/book_detail.php?bid=6259425")
    private String link;

    @Schema(description = "이미지 정보", example = "https://bookthumb-phinf.pstatic.net/cover/062/594/06259425.jpg?type=m1&udate=20141122")
    private String image;

    @Schema(description = "작가", example = "정민섭")
    private String author;

    @Schema(description = "가격", example = "48000")
    private String price;

    @Schema(description = "할인가격", example = "43200")
    private String discount;

    @Schema(description = "발행인", example = "OKGOSU")
    private String publisher;

    @Schema(description = "발행일자", example = "20100419")
    private String pubdate;

    @Schema(description = "ISBN", example = "8960771295 9788960771291")
    private String isbn;

    @Schema(description = "설명", example = "OKGOSU")
    private String description;
}
