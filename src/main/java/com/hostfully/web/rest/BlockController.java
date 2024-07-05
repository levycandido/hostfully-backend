package com.hostfully.web.rest;

import com.hostfully.service.BlockService;
import com.hostfully.service.dao.BlockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/blocks")
public class BlockController {

    @Autowired
    private BlockService blockService;

    @PostMapping
    public ResponseEntity<BlockDTO> createBlockDTO(@RequestBody BlockDTO block) {
        if (block.getStartDate() == null) {
            throw new IllegalArgumentException("Start date is required");
        }

        BlockDTO result = blockService.createBlock(block);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlockDTO> updateBlockDTO(@PathVariable Long id, @RequestBody BlockDTO block) {
        if (block.getStartDate() == null) {
            throw new IllegalArgumentException("Start date is required");
        }
        BlockDTO result = blockService.updateBlock(id, block);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlockDTO(@PathVariable Long id) {
        try {
            blockService.deleteBlock(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping
    public ResponseEntity<List<BlockDTO>> getAllBlockDTOs() {
        List<BlockDTO> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(blocks);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
