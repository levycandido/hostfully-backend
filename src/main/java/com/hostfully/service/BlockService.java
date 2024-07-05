package com.hostfully.service;

import com.hostfully.ObjectMapperUtils;
import com.hostfully.entity.Block;
import com.hostfully.repository.BlockRepository;
import com.hostfully.service.dao.BlockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService {

    @Autowired
    private BlockRepository blockRepository;

    public BlockDTO createBlock(BlockDTO blockDTO) {
        Block block = ObjectMapperUtils.map(blockDTO, Block.class);
        if (blockDTO.getStartDate() == null) {
            throw new IllegalArgumentException("Start date is required");
        }
        return ObjectMapperUtils.map(blockRepository.save(block), BlockDTO.class);
    }

    public BlockDTO updateBlock(Long id, BlockDTO blockDTO) {
        if (blockDTO.getStartDate() == null) {
            throw new IllegalArgumentException("Start date is required");
        }
        Optional<Block> existingBlock = blockRepository.findById(id);
        if (existingBlock.isPresent()) {
            Block updatedBlock = existingBlock.get();
            updatedBlock.setStartDate(blockDTO.getStartDate());
            updatedBlock.setEndDate(blockDTO.getEndDate());

            return ObjectMapperUtils.map(blockRepository.save(updatedBlock), BlockDTO.class);
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

    public BlockDTO getBlock(Long id) {
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Block not found"));
        return ObjectMapperUtils.map(block, BlockDTO.class);
    }

    public List<BlockDTO> getAllBlocks() {
        List<Block> blocks = blockRepository.findAll();
        if (blocks.isEmpty()) {
            throw new IllegalArgumentException("No blocks found");
        }
        return ObjectMapperUtils.mapAll(blocks, BlockDTO.class);
    }
}
