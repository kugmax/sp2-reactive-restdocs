package com.kugmax.learn.sp2.reactiverestdocs.reactiverestdocs.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
@AutoConfigureRestDocs
public class TestGameController {

    @Autowired
    private MockMvc mockMvc;

//    private GameController controller;
//    @Rule
//    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
//
//    @Before
//    public void before() {
//        controller = new GameController();
//
//        mockMvc = standaloneSetup(controller)
//                .apply(documentationConfiguration(this.restDocumentation)
//                        .uris()
//                        .withScheme("http")
//                        .withHost("example.com/")
//                        .withPort(80)
//                )
//                .build();
//    }

    @Test
    public void put() throws Exception {
         MvcResult mvcResult = mockMvc.perform(
                RestDocumentationRequestBuilders.put("/GameAPI/game/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{" +
                                "\"gameID\" : 1," +
                                "\"name\" : \"Nice\"," +
                                "\"description\" : \"Game\"" +
                                "}")
         ).andReturn();

         mockMvc.perform(asyncDispatch(mvcResult))
                 .andExpect(status().isOk())
                 .andExpect(content().string(""))
                 .andDo(
                        document("GameAPI-put-success",
                                requestFields(
                                        fieldWithPath("gameID").description("Game id"),
                                        fieldWithPath("name").description("Game name"),
                                        fieldWithPath("description").description("Game description")
                                )
                        ));
    }

    @Test
    public void get_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/GameAPI/game/{id}", 1L)
        ).andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                 .andExpect(status().isOk())
                 .andExpect(content().string("{" +
                                "\"gameID\":1," +
                                "\"name\":\"Nice\"," +
                                "\"description\":\"Game\"" +
                                "}"))
                 .andDo(
                        document("GameAPI-get-success",
                                pathParameters(
                                        parameterWithName("id").description("Game id")
                                ),
                                responseFields(
                                        fieldWithPath("gameID").description("Game id"),
                                        fieldWithPath("name").description("Game name"),
                                        fieldWithPath("description").description("Game description")
                                )
                        ));
    }
}
