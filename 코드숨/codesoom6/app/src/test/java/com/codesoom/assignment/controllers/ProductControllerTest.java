package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.AuthenticationService;
import com.codesoom.assignment.application.ProductService;
import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.dto.ProductData;
import com.codesoom.assignment.errors.InvalidTokenException;
import com.codesoom.assignment.errors.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2UiLCJ1c2VySWQiOjF9.bOQfc4mYhcmbCH6JjPuzSc6eRJEr_A0d8tVrPtxE9aU";
    private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2UiLCJ1c2VySWQiOjF9.bOQfc4mYhcmbCH6JjPuzSc6eRJEr_A0d8tVrPtxE9a0";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    AuthenticationService authenticationService;


    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .id(1L)
                .name("쥐돌이")
                .maker("냥이월드")
                .price(5000)
                .build();
        given(productService.getProducts()).willReturn(List.of(product));

        given(productService.getProduct(1L)).willReturn(product);

        given(productService.getProduct(1000L))
                .willThrow(new ProductNotFoundException(1000L));

        given(productService.createProduct(any(ProductData.class)))
                .willReturn(product);

        given(productService.updateProduct(eq(1L), any(ProductData.class)))
                .will(invocation -> {
                    Long id = invocation.getArgument(0);
                    ProductData productData = invocation.getArgument(1);
                    return Product.builder()
                            .id(id)
                            .name(productData.getName())
                            .maker(productData.getMaker())
                            .price(productData.getPrice())
                            .build();
                });

        given(productService.updateProduct(eq(1000L), any(ProductData.class)))
                .willThrow(new ProductNotFoundException(1000L));

        given(productService.deleteProduct(1000L))
                .willThrow(new ProductNotFoundException(1000L));

        given(authenticationService.parseToken(VALID_TOKEN))
                .willReturn(1L);


        given(authenticationService.parseToken(INVALID_TOKEN))
                .willThrow(new InvalidTokenException());

    }

    @Test
    void list() throws Exception {
        mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .header("Authorization", "Bearer " + VALID_TOKEN)

                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐돌이")));
    }

    @Test
    void deatilWithExsitedProduct() throws Exception {
        mockMvc.perform(
                        get("/products/1")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .header("Authorization", "Bearer " + VALID_TOKEN)

                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐돌이")));
    }

    @Test
    void deatilWithNotExsitedProduct() throws Exception {
        mockMvc.perform(get("/products/1000")
                        .header("Authorization", "Bearer " + VALID_TOKEN)

                )
                .andExpect(status().isNotFound());
    }

    @Test
    void createWithValidAttributes() throws Exception {
        mockMvc.perform(post("/products")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐돌이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
                        .header("Authorization", "Bearer " + VALID_TOKEN)

                )
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("쥐돌이")));

        verify(productService).createProduct(any(ProductData.class));
    }


    @Test
    void createWithAccessToken() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"쥐돌이\",\"maker\":\"냥이월드\"," +
                                        "\"price\":5000}")

                                .header("Authorization", "Bearer " + VALID_TOKEN)

                )
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("쥐돌이")));

        verify(productService).createProduct(any(ProductData.class));
    }

    @Test
    void createWithNotToken() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"쥐돌이\",\"maker\":\"냥이월드\"," +
                                        "\"price\":5000}")
                )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid Access Token"));
    }

    @Test
    void createWithBlankAttributes() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"쥐돌이\",\"maker\":\"냥이월드\"," +
                                        "\"price\":5000}")
                )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid Access Token"))
                .andDo(print());
    }

    @Test
    void createWithInvalidAccessToken() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"쥐돌이\",\"maker\":\"냥이월드\"," +
                                        "\"price\":5000}")
                                .header("Authorization", "Bearer " + INVALID_TOKEN)
                )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid Access Token"))
                .andDo(print());

    }


    @Test
    void createWithInvalidAttributes() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"\",\"maker\":\"\"," +
                                        "\"price\":0}")
                                .header("Authorization", "Bearer " + VALID_TOKEN)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateWithExistedProduct() throws Exception {
        mockMvc.perform(
                        patch("/products/1")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                        "\"price\":5000}")
                                .header("Authorization", "Bearer " + VALID_TOKEN)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐순이")));

        verify(productService).updateProduct(eq(1L), any(ProductData.class));
    }

    @Test
    void updateWithNotExistedProduct() throws Exception {
        mockMvc.perform(
                        patch("/products/1000")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                        "\"price\":5000}")
                                .header("Authorization", "Bearer " + VALID_TOKEN)

                )
                .andExpect(status().isNotFound());

        verify(productService).updateProduct(eq(1000L), any(ProductData.class));
    }

    @Test
    void updateWithValidToken() throws Exception {
        mockMvc.perform(
                        patch("/products/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                        "\"price\":5000}")
                                .header("Authorization", "Bearer " + VALID_TOKEN)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(productService).updateProduct(eq(1L), any(ProductData.class));
    }

    @Test
    void updateWithInvalidToken() throws Exception {
        mockMvc.perform(
                        patch("/products/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                        "\"price\":5000}")
                                .header("Authorization", "Bearer " + INVALID_TOKEN)
                )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void updateWithBlankInValidToken() throws Exception {
        mockMvc.perform(
                        patch("/products/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                        "\"price\":5000}")
                )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid Access Token"))
                .andDo(print());
    }


    @Test
    void updateWithInvalidAttributes() throws Exception {
        mockMvc.perform(
                        patch("/products/1")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"\",\"maker\":\"\"," +
                                        "\"price\":0}")
                                .header("Authorization", "Bearer " + VALID_TOKEN)

                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void destroyWithExistedProduct() throws Exception {
        mockMvc.perform(
                        delete("/products/1")
                                .header("Authorization", "Bearer " + VALID_TOKEN)

                )
                .andExpect(status().isNoContent());

        verify(productService).deleteProduct(1L);
    }

    @Test
    void destroyWithValidToken() throws Exception {
        mockMvc.perform(
                        delete("/products/1")
                                .header("Authorization", "Bearer " + VALID_TOKEN)
                )
                .andExpect(status().isNoContent());

        verify(productService).deleteProduct(1L);
    }

    @Test
    void destroyWithInValidToken() throws Exception {
        mockMvc.perform(
                        delete("/products/1")
                                .header("Authorization", "Bearer " + INVALID_TOKEN)
                )
                .andExpect(status().isUnauthorized());

    }

    @Test
    void destroyWithValidValues() throws Exception {
        mockMvc.perform(
                        delete("/products/1")
                                .header("Authorization", "Bearer " + VALID_TOKEN)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void destroyWithBlackToken() throws Exception {
        mockMvc.perform(
                        delete("/products/1")
                )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid Access Token"))
                .andDo(print());
    }

    @Test
    void destroyWithNotExistedProduct() throws Exception {
        mockMvc.perform(
                        delete("/products/1000")
                                .header("Authorization", "Bearer " + VALID_TOKEN)
                )
                .andExpect(status().isNotFound());

        verify(productService).deleteProduct(1000L);
    }
}
