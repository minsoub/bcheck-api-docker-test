package kr.co.hist.bcheck.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ISBN 정보")
public class GeneralResponse {
    @Schema(description = "상태", example = "0")
    private int status;

    @Schema(description = "상태메시지", example = "성공적으로 등록하였습니다")
    private String message;
}
