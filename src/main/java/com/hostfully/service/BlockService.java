package com.hostfully.service;

import com.hostfully.entity.Block;
import com.hostfully.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlockService {

    // Implement your repository here
     @Autowired
     private BlockRepository blockRepository;

    public Block createBlock(Block block) {
        if (block.getStartDate() == null) {
            throw new IllegalArgumentException("Start date is required");
        }
        // Implement the creation logic here
        return blockRepository.save(block);
    }

    public Block updateBlock(Long id, Block block) {
        if (block.getStartDate() == null) {
            throw new IllegalArgumentException("Start date is required");
        }
        Optional<Block> existingBlock = blockRepository.findById(id);
        if (existingBlock.isPresent()) {
            Block updatedBlock = existingBlock.get();
            updatedBlock.setStartDate(block.getStartDate());
            updatedBlock.setEndDate(block.getEndDate());

            return blockRepository.save(updatedBlock);
        } else {
            throw new IllegalArgumentException("Block not found");
        }
    }

    public void deleteBlock(Long id) {
        Optional<Block> block = blockRepository.findById(id);
        if (block.isPresent()) {
            blockRepository.delete(block.get());
        } else {
            throw new IllegalArgumentException("Block not found");
        }
    }

    public Block getBlock(Long id) {
        return blockRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Block not found"));
    }

    public List<Block> getAllBlocks() {
        List<Block> blocks = blockRepository.findAll();
        if (blocks.isEmpty()) {
            throw new IllegalArgumentException("No blocks found");
        }
        return blocks;
    }
}
