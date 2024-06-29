package com.hostfully.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hostfully.entity.Booking;
import com.hostfully.entity.Guest;
import com.hostfully.entity.Person;
import com.hostfully.entity.Place;
import com.hostfully.service.BookingService;
import com.hostfully.service.dao.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @MockBean
    private BookingService bookingService;

    private MockMvc mockMvc;

    @InjectMocks
    private BookingController bookingController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    public void testCreateBooking() throws Exception {
        Person guest = new Guest(1L, "John Doe");
        Place property = new Place(1L, "Test Property", "Test Location");
        Booking booking = new Booking(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), status.PENDING, guest, property);

        when(bookingService.createBooking(any(Booking.class))).thenReturn(booking);

        String jsonContent = objectMapper.writeValueAsString(booking);
        System.out.println("JSON Content: " + jsonContent);

        mockMvc.perform(post("/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate[0]").value("2023"))
                .andExpect(jsonPath("$.startDate[1]").value("7"))
                .andExpect(jsonPath("$.startDate[2]").value("1"))
                .andExpect(jsonPath("$.endDate[0]").value("2023"))
                .andExpect(jsonPath("$.endDate[1]").value("7"))
                .andExpect(jsonPath("$.endDate[2]").value("7"));

        verify(bookingService, times(1)).createBooking(any(Booking.class));
    }

    @Test
    public void testUpdateBooking() throws Exception {
        Person guest1 = new Guest(1L, "John Doe");
        Place property1 = new Place(1L, "Test Property", "Test Location");
        Booking booking = new Booking(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), status.PENDING, guest1, property1);

        when(bookingService.updateBooking(anyLong(), any(Booking.class))).thenReturn(booking);

        mockMvc.perform(put("/v1/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guestName").value("John Doe"))
                .andExpect(jsonPath("$.startDate[0]").value("2023"))
                .andExpect(jsonPath("$.startDate[1]").value("7"))
                .andExpect(jsonPath("$.startDate[2]").value("1"))
                .andExpect(jsonPath("$.endDate[0]").value("2023"))
                .andExpect(jsonPath("$.endDate[1]").value("7"))
                .andExpect(jsonPath("$.endDate[2]").value("7"));

        verify(bookingService, times(1)).updateBooking(anyLong(), any(Booking.class));
    }

    @Test
    public void testDeleteBooking() throws Exception {
        doNothing().when(bookingService).deleteBooking(anyLong());

        mockMvc.perform(delete("/v1/bookings/1"))
                .andExpect(status().isNoContent());

        verify(bookingService, times(1)).deleteBooking(anyLong());
    }

    @Test
    public void testCancelBooking() throws Exception {
        doNothing().when(bookingService).cancelBooking(anyLong());

        mockMvc.perform(patch("/v1/bookings/1/cancel"))
                .andExpect(status().isNoContent());

        verify(bookingService, times(1)).cancelBooking(anyLong());
    }

    @Test
    public void testRebook() throws Exception {
        Person guest = new Guest(1L, "John Doe");
        Place property = new Place(1L, "Test Property", "Test Location");
        Booking booking = new Booking(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), status.PENDING, guest, property);

        when(bookingService.rebook(anyLong())).thenReturn(booking);

        mockMvc.perform(patch("/v1/bookings/1/rebook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guestName").value("John Doe"))
                .andExpect(jsonPath("$.startDate[0]").value("2023"))
                .andExpect(jsonPath("$.startDate[1]").value("7"))
                .andExpect(jsonPath("$.startDate[2]").value("1"))
                .andExpect(jsonPath("$.endDate[0]").value("2023"))
                .andExpect(jsonPath("$.endDate[1]").value("7"))
                .andExpect(jsonPath("$.endDate[2]").value("7"));

        verify(bookingService, times(1)).rebook(anyLong());
    }

    @Test
    public void testGetBooking() throws Exception {
        Person guest = new Guest(1L, "John Doe");
        Place property = new Place(1L, "Test Property", "Test Location");
        Booking booking = new Booking(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), status.PENDING, guest, property);

        when(bookingService.getBooking(anyLong())).thenReturn(booking);

        mockMvc.perform(get("/v1/bookings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guestName").value("John Doe"))
                .andExpect(jsonPath("$.startDate[0]").value("2023"))
                .andExpect(jsonPath("$.startDate[1]").value("7"))
                .andExpect(jsonPath("$.startDate[2]").value("1"))
                .andExpect(jsonPath("$.endDate[0]").value("2023"))
                .andExpect(jsonPath("$.endDate[1]").value("7"))
                .andExpect(jsonPath("$.endDate[2]").value("7"));

        verify(bookingService, times(1)).getBooking(anyLong());
    }

    @Test
    public void testGetAllBookings() throws Exception {
        Person guest = new Guest(1L, "John Doe");
        Place property = new Place(1L, "Test Property", "Test Location");
        Booking booking1 = new Booking(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), status.PENDING, guest, property);

        Person guest2 = new Guest(1L, "John Doe");
        Place property2 = new Place(1L, "Test Property", "Test Location");
        Booking booking2 = new Booking(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), status.PENDING, guest2, property2);

        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingService.getAllBookings()).thenReturn(bookings);

        mockMvc.perform(get("/v1/bookings"))
                .andExpect(status().isOk());


        verify(bookingService, times(1)).getAllBookings();
    }
}
