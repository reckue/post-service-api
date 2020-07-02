//package com.reckue.post.controllers;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.reckue.post.PostServiceApplicationTests;
//import com.reckue.post.models.Rating;
//import com.reckue.post.repositories.RatingRepository;
//import com.reckue.post.transfers.RatingRequest;
//import com.reckue.post.transfers.RatingResponse;
//import com.reckue.post.utils.converters.RatingConverter;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
///**
// * Class RatingControllerIntTest is the integration type of test.
// *
// * @author Iveri Narozashvili
// */
//@AutoConfigureMockMvc
//public class RatingControllerIntTest extends PostServiceApplicationTests {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private RatingController ratingController;
//
//    @Autowired
//    private RatingRepository ratingRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        ratingRepository.deleteAll();
//
//        ratingRepository.save(Rating.builder()
//                .id("1")
//                .userId("Garry")
//                .postId("cars")
//                .build());
//        ratingRepository.save(Rating.builder()
//                .id("2")
//                .userId("Antony")
//                .postId("animals")
//                .build());
//        ratingRepository.save(Rating.builder()
//                .id("3")
//                .userId("Bart")
//                .postId("news")
//                .build());
//    }
//
//    @Test
//    public void load() {
//        assertThat(ratingController).isNotNull();
//    }
//
//    @Test
//    public void findAllSortedByIdAsc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .limit(3)
//                .peek(System.out::println)
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=false&limit=3&offset=0&sort=id"))
//                        .andDo(print()).andExpect(status()
//                                .isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByIdDesc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .sorted(Comparator.comparing(RatingResponse::getId).reversed())
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=true&limit=2&offset=0&sort=id"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByTextAsc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .sorted(Comparator.comparing(RatingResponse::getId))
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=false&limit=2&offset=0&sort=text"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByTextDesc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .sorted(Comparator.comparing(RatingResponse::getId).reversed())
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=true&limit=2&offset=0&sort=text"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByUserIdAsc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .sorted(Comparator.comparing(RatingResponse::getUserId))
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=false&limit=2&offset=0&sort=userId"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByUserIdDesc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .sorted(Comparator.comparing(RatingResponse::getUserId).reversed())
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=true&limit=2&offset=0&sort=userId"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByPostIdAsc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .sorted(Comparator.comparing(RatingResponse::getPostId))
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=false&limit=2&offset=0&sort=postId"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByPostIdDesc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .sorted(Comparator.comparing(RatingResponse::getPostId).reversed())
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=true&limit=2&offset=0&sort=postId"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByPublishedAsc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .sorted(Comparator.comparing(RatingResponse::getPostId))
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=false&limit=2&offset=0&sort=published"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByPublishedDesc() throws Exception {
//        List<RatingResponse> expected = ratingRepository.findAll().stream()
//                .map(RatingConverter::convert)
//                .sorted(Comparator.comparing(RatingResponse::getPostId).reversed())
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<RatingResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/ratings?desc=true&limit=2&offset=0&sort=published"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findById() throws Exception {
//        RatingResponse expected = RatingConverter.convert(ratingRepository.findAll().get(0));
//
//        RatingResponse actual = objectMapper.readValue(this.mockMvc.perform(get("/ratings/" + expected.getId()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse().getContentAsString(), RatingResponse.class);
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void update() throws Exception {
//        RatingResponse expected = RatingConverter.convert(Rating.builder()
//                .id(ratingRepository.findAll().get(0).getId())
//                .userId("best id ever")
//                .postId("simple")
//                .build());
//
//        String json = objectMapper.writeValueAsString(RatingRequest.builder()
//                .userId("best id ever")
//                .postId("simple")
//                .build());
//
//        MockHttpServletRequestBuilder builder = put("/ratings/" + ratingRepository.findAll().get(0).getId())
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(json);
//
//        RatingResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse().getContentAsString(), RatingResponse.class);
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void save() throws Exception {
//        String json = objectMapper.writeValueAsString(RatingRequest.builder()
//                .userId("23")
//                .postId("2020")
//                .build());
//
//        MockHttpServletRequestBuilder builder = post("/ratings")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(json);
//
//        RatingResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse().getContentAsString(), RatingResponse.class);
//
//        RatingResponse expected = RatingConverter.convert(Rating.builder()
//                .id(actual.getId())
//                .userId("23")
//                .postId("2020")
//                .build());
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void deleteById() throws Exception {
//        String id = ratingRepository.findAll().get(0).getId();
//        this.mockMvc.perform(delete("/ratings/" + id))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        Assertions.assertEquals(ratingRepository.findAll().size(), 2);
//    }
//}