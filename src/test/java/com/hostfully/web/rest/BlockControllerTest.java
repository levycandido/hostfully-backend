package com.hostfully.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hostfully.entity.Block;
import com.hostfully.entity.Place;
import com.hostfully.service.BlockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BlockController.class)
@Import(BlockService.class)
public class BlockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlockService blockService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateBlock() throws Exception {
        Block block;
        Place property = new Place(1L, "Test Property", "Test Location");
        block = new Block(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7),  property);

        when(blockService.createBlock(any(Block.class))).thenReturn(block);

        String jsonContent = objectMapper.writeValueAsString(block);
        System.out.println("JSON Content: " + jsonContent);

        mockMvc.perform(post("/v1/blocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate").value("2023-07-01"));

        verify(blockService, times(1)).createBlock(any(Block.class));
    }

    @Test
    public void testUpdateBlock() throws Exception {

        Place property = new Place(1L, "Test Property", "Test Location");
        Block block = new Block(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7),  property);
        when(blockService.updateBlock(anyLong(), any(Block.class))).thenReturn(block);

        mockMvc.perform(put("/v1/blocks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(block)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate").value("2023-07-01"));


        verify(blockService, times(1)).updateBlock(anyLong(), any(Block.class));
    }

    @Test
    public void testDeleteBlock() throws Exception {
        doNothing().when(blockService).deleteBlock(anyLong());

        mockMvc.perform(delete("/v1/blocks/1"))
                .andExpect(status().isNoContent());

        verify(blockService, times(1)).deleteBlock(anyLong());
    }

    @Test
    public void testGetAllBlocks() throws Exception {
        Place property = new Place(1L, "Test Property", "Test Location");
        Block block = new Block(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7),  property);
        when(blockService.getAllBlocks()).thenReturn(Collections.singletonList(block));

        mockMvc.perform(get("/v1/blocks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].startDate").value("2023-07-01"));


        verify(blockService, times(1)).getAllBlocks();
    }

    @Test
    public void testCreateBlockFail() throws Exception {
        Place property = new Place(1L, "Test Property", "Test Location");

        Block block = new Block(1L, null, LocalDate.of(2023, 7, 7), property);

        String jsonContent = objectMapper.writeValueAsString(block);
        System.out.println("JSON Content: " + jsonContent);

        Mockito.doThrow(new IllegalArgumentException("Start date is required"))
                .when(blockService).createBlock(Mockito.any(Block.class));

        mockMvc.perform(post("/v1/blocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    Throwable exception = result.getResolvedException();
                    assertTrue(exception instanceof IllegalArgumentException, "Exception should be IllegalArgumentException");
                    assertEquals("Start date is required", exception.getMessage(), "Error message should be 'Start date is required'");
                });
    }

    @Test
    public void testUpdateBlockFail() throws Exception {
        Place property = new Place(1L, "Test Property", "Test Location");
        Block block = new Block(1L, null, LocalDate.of(2023, 7, 7), property);

        String jsonContent = objectMapper.writeValueAsString(block);
        System.out.println("JSON Content: " + jsonContent);

        mockMvc.perform(put("/v1/blocks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    Throwable exception = result.getResolvedException();
                    assertTrue(exception instanceof IllegalArgumentException, "Exception should be IllegalArgumentException");
                    assertEquals("Start date is required", exception.getMessage(), "Error message should be 'Start date is required'");
                });
    }

    @Test
    public void testDeleteBlockFail() throws Exception {
        doThrow(new IllegalArgumentException("Block not found")).when(blockService).deleteBlock(anyLong());

        mockMvc.perform(delete("/v1/blocks/1"))
                .andExpect(status().isNotFound());
    }


}
