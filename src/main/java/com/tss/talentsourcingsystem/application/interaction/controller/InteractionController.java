package com.tss.talentsourcingsystem.application.interaction.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.generic.dto.RestResponse;
import com.tss.talentsourcingsystem.application.interaction.dto.InteractionDto;
import com.tss.talentsourcingsystem.application.interaction.dto.InteractionSaveRequestDto;
import com.tss.talentsourcingsystem.application.interaction.dto.InteractionUpdateRequestDto;
import com.tss.talentsourcingsystem.application.interaction.service.InteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interactions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InteractionController {
    private final InteractionService interactionService;

    @PostMapping
    public ResponseEntity<RestResponse<InteractionDto>> saveInteraction(@RequestBody InteractionSaveRequestDto interactionSaveRequest) {
        InteractionDto interactionDto=interactionService.saveInteraction(interactionSaveRequest);
        return ResponseEntity.ok(RestResponse.of(interactionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<InteractionDto>> getInteractionById(@PathVariable Long id) {
        InteractionDto interactionDto=interactionService.getInteractionById(id);
        return ResponseEntity.ok(RestResponse.of(interactionDto));
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<RestResponse<List<InteractionDto>>> getInteractionsByCandidateId(@PathVariable Long id) {
        List<InteractionDto> interactionDtoList=interactionService.getInteractionsByCandidateId(id);
        return ResponseEntity.ok(RestResponse.of(interactionDtoList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<InteractionDto>> updateInteraction(@PathVariable Long id, @RequestBody InteractionUpdateRequestDto interactionUpdateRequestDto) {
        InteractionDto interactionDto=interactionService.updateInteraction(id, interactionUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(interactionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteInteraction(@PathVariable Long id) {
        interactionService.deleteInteraction(id);
        return ResponseEntity.ok(RestResponse.empty());
    }
}
