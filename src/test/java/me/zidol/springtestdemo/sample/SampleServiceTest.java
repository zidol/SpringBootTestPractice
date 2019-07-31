package me.zidol.springtestdemo.sample;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
//mock : mock servlet environment
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)    //서블릿을 mocking한것이 뜸
//@AutoConfigureMockMvc
// RANDOM_PORT, DEFINED<PORT
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 슬라이스 테스트 : 레이어 별로 잘라서 테스트하고 싶을때
//JSON
//@JsonTest
@WebMvcTest(SampleController.class) // controller만 등록, web 관련된것들만 bean으로 등록돰 => service, repository 등록 안
public class SampleServiceTest {
//    @Autowired
//    MockMvc mockMvc; 내장 톰캣 사용 안함//    @Test
//    public void hello() throws Exception{
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("hello zidol"))
//                .andDo(print());
//    }

//    @Autowired
//    TestRestTemplate testRestTemplate;
//    @MockBean //실제 service를 사용 하지 않고 mockSample service를 사용, 내장 톰캣 사용
//    SampleService mockSampleService;
//
//    @Test
//    public void hello()throws Exception {
//        when(mockSampleService.getName()).thenReturn("whiteship");
//
//        String result = testRestTemplate.getForObject("/hello", String.class);
//        assertThat(result).isEqualTo("hello whiteship");
//    }

//    @Autowired
//    WebTestClient webTestClient;    //async로 동작
//
//    @MockBean //실제 service를 사용 하지 않고 mockSample service를 사용, 내장 톰캣 사용
//            SampleService mockSampleService;
//
//    @Test
//    public void hello()throws Exception {
//        when(mockSampleService.getName()).thenReturn("zidol");
//
//        webTestClient.get().uri("/hello").exchange()
//                .expectStatus().isOk()
//                .expectBody(String.class).isEqualTo("hello zidol");
//    }

    @Rule
    public OutputCapture outputCapture = new OutputCapture();   //로그를 비롯 콘솔에 찍히는것을 캡쳐
    //WebMvcTest에서는 service는 등록이 안되기때문에 주입을 해줘야 함
    @MockBean
    SampleService mockSampleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello()throws Exception {
        when(mockSampleService.getName()).thenReturn("zidol");

        mockMvc.perform(get("/hello"))
                .andExpect(content().string("hello zidol"));
        assertThat(outputCapture.toString())
                .contains("holoman")
                .contains("skip");

    }
}