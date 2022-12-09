package com.assignment.rest;

import com.assignment.rest.Controller.ApiControllers;
import com.assignment.rest.Models.SSGroupByType;
import com.assignment.rest.Models.StockRecord;
import com.assignment.rest.Repo.RecordRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiControllers.class)
class RestApiApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	RecordRepo recordRepo;


	@Test
	void testDelete() throws Exception {
		StockRecord deleteRecord = new StockRecord();
		Mockito.when(recordRepo.findById(any())).thenReturn(Optional.of(deleteRecord));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/delete/1")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andReturn();
		Mockito.verify(recordRepo).delete(deleteRecord);

	}
	@Test
	void testGet() throws Exception {

		Mockito.when(recordRepo.getMinCurrencyAmount()).thenReturn(1.0);
		Mockito.when(recordRepo.getMaxCurrencyAmount()).thenReturn(2.0);
		Mockito.when(recordRepo.getAvgCurrencyAmount()).thenReturn(1.5);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/get")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(response.getContentAsString(), "[1.0,2.0,1.5]");

	}
	@Test
	void testGetCurrencyAmountWithStockBuyType() throws Exception {

		Mockito.when(recordRepo.getMinCurrencyAmountWithStockBuyType()).thenReturn(1.0);
		Mockito.when(recordRepo.getMaxCurrencyAmountWithStockBuyType()).thenReturn(2.0);
		Mockito.when(recordRepo.getAvgCurrencyAmountWithStockBuyType()).thenReturn(1.5);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getSSOfStockBuy")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(response.getContentAsString(), "[1.0,2.0,1.5]");

	}

	@Test
	void testGetCurrencyAmountWithType() throws Exception {
		SSGroupByType ssGroupByType = new SSGroupByType("abc", 1.0, 2.0, 1.5);
		List<SSGroupByType> example = new ArrayList<>();
		example.add(ssGroupByType);

		Mockito.when(recordRepo.getCurrencyAmountGroupByType()).thenReturn(example);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getCurrencyAmountWithType")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(response.getContentAsString(), "type = abchas min 1.0 max 2.0 avg 1.5\n");
	}

	@Test
	void testGetCurrencyAmountWithTypeAndQuoteCurrency() throws Exception {
		SSGroupByType ssGroupByType = new SSGroupByType("abc", 1.0, 2.0, 1.5);
		List<SSGroupByType> example = new ArrayList<>();
		example.add(ssGroupByType);

		Mockito.when(recordRepo.getCurrencyAmountWithTypeAndQuoteCurrency()).thenReturn(example);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getCurrencyAmountWithTypeAndQuoteCurrency")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(response.getContentAsString(), "type = abchas min 1.0 max 2.0 avg 1.5\n");
	}

}
