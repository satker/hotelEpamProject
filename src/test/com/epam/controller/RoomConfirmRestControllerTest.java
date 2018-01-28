package com.epam.controller;

import com.epam.service.RoomConfirmService;
import com.ge.predix.web.cors.CORSFilter;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RoomConfirmRestControllerTest {
    private MockMvc mockMvc;
    @Mock
    private RoomConfirmService roomConfirmService;

    @Spy
    @InjectMocks
    private RoomConfirmRestController mockRoomConfirmRestController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(mockRoomConfirmRestController)
                .addFilters(new CORSFilter())
                .build();
    }


}
