package kitchenpos.ui;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import kitchenpos.dao.OrderTableDao;
import kitchenpos.domain.OrderTable;

@SpringBootTest
@AutoConfigureMockMvc
class TableRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderTableDao orderTableDao;

    @DisplayName("주문테이블을 등록한다")
    @Test
    void table1() throws Exception {
        OrderTable table = new OrderTable(null, 3, false);

        MvcResult result = mockMvc.perform(post("/api/tables")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(table)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.tableGroupId").value(table.getTableGroupId()))
            .andExpect(jsonPath("$.numberOfGuests").value(table.getNumberOfGuests()))
            .andExpect(jsonPath("$.empty").value(table.isEmpty()))
            .andReturn();

        assertThat(orderTableDao.findById(getId(result))).isNotEmpty();
    }

    @DisplayName("전체 주문테이블을 조회한다")
    @Test
    void table2() throws Exception {
        mockMvc.perform(get("/api/tables"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$..id").exists())
            .andExpect(jsonPath("$..tableGroupId").exists())
            .andExpect(jsonPath("$..numberOfGuests").exists())
            .andExpect(jsonPath("$..empty").exists())
        ;
    }

    @DisplayName("빈 테이블 여부를 변경한다")
    @Test
    void table3() throws Exception {
        OrderTable table = new OrderTable(null, 0, true);
        Long id = 1L;

        mockMvc.perform(put("/api/tables/" + id + "/empty")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(table)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.empty").value(table.isEmpty()));

        assertThat(findById(id).isEmpty()).isEqualTo(table.isEmpty());
    }

    @DisplayName("방문한 손님수를 변경한다")
    @Test
    void table4() throws Exception {
        OrderTable table = new OrderTable(null, 99, true);
        Long id = 9L;

        mockMvc.perform(put("/api/tables/" + id + "/number-of-guests")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(table)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.numberOfGuests").value(99));

        assertThat(findById(id).getNumberOfGuests()).isEqualTo(table.getNumberOfGuests());
    }

    private OrderTable findById(Long id) {
        return orderTableDao.findById(id).orElseThrow(RuntimeException::new);
    }

    private Long getId(MvcResult result) throws
        com.fasterxml.jackson.core.JsonProcessingException,
        UnsupportedEncodingException {
        String response = result.getResponse().getContentAsString();
        return objectMapper.readValue(response, OrderTable.class).getId();
    }

}
