package DBTeam3.Abandonne.controller;

import DBTeam3.Abandonne.domain.User_Info;
import DBTeam3.Abandonne.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    protected ObjectMapper objectMapper;
    
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @DisplayName("새로운 유저 생성")
    @Test
    void save() throws Exception {
        //given
        String url = "/api/user/new";
        User_Info user = new User_Info("fname", "lname",
                "email@naver.com",
                "Violin",
                "01012346543", "pw22");
        final String requestBody = objectMapper.writeValueAsString(user);
        
        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));
        //then
        result.andExpect(status().isCreated());

        User_Info createdUser = userRepository.findById(user.getUid()).get();

        assertThat(createdUser.getPw()).isEqualTo(user.getPw());
        assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void findById() throws Exception {

        //given
        User_Info user = new User_Info("fname", "lname",
                "email@naver.com",
                "Violin",
                "01012346543", "pw22");
        userRepository.save(user);

        String url ="api/userprofile/1";

        //when
        ResultActions result =
                mockMvc.perform(get(url, user.getUid()));

        //given
        result.andExpect(jsonPath("$.email").value(user.getEmail()));

    }

    /*@DisplayName(" 유저 정보 전체 보여주기 ")
    @Test
    void showUserAllById() throws Exception {

        //given
        /*String url = "/api/userall/2";
        User_Info user = new User_Info("fname", "lname",
                "email@naver.com",
                "Violin",
                "01012346543", "pw22");



        final String requestBody = objectMapper.writeValueAsString(user);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));
        //then
        result.andExpect(status().isCreated());

        User_Info createdUser = userRepository.findById(user.getUid()).get();

        assertThat(createdUser.getPw()).isEqualTo(user.getPw());
        assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());

    }*/

}