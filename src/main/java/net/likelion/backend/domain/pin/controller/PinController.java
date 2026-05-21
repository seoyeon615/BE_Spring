package net.likelion.backend.domain.pin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.pin.dto.PinRequestDto;
import net.likelion.backend.domain.pin.dto.PinResponseDto;
import net.likelion.backend.domain.pin.service.PinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pin API", description = "메모 고정 및 고정 조회 API")
@RestController
@RequestMapping("/pins")
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService;

    @Operation(summary = "고정된 메모 전체 조회", description = "고정된 모든 메모 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<List<PinResponseDto>> getAllPins() {
        List<PinResponseDto> response = pinService.getAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "메모 고정", description = "memos에서 생성한 메모를 고정합니다.")
    @ApiResponse(responseCode = "201", description = "고정 성공")
    @PostMapping
    public ResponseEntity<PinResponseDto> createPin(@Valid @RequestBody PinRequestDto request) {
        PinResponseDto response = pinService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}