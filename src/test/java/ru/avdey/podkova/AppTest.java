package ru.avdey.podkova;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.avdey.podkova.controller.MainController;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;


/**'
 *
 *  интеграционное тестирование
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController controller;


    @Test
    public void testControllerSuccess() throws Exception {
        assertThat(controller).isNotNull();


    }

    /**
     * проверка  доступа пользователя
     *
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception {

        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().is3xxRedirection()) // система будет перенаправлять на страницу логина
                .andExpect(redirectedUrl("http://localhost/login"));

    }


    /**
     * проверяем что заходит на главную страницу
     *
     * @throws Exception
     */
    @Test
    public void testIndexPage() throws Exception {

        this.mockMvc.perform(get("/")) // сделать запрос на главную страницу нашего проекта
                .andDo(print()) // выводит полученный результат
                .andExpect(status().isOk()) // http 200
                .andExpect(content().string(containsString("на главную страницу"))); //


    }

    /**
     * проверяем авторизация пользователя
     * @throws Exception
     */
    @Test
    public void testCorrectLogin() throws Exception {

        this.mockMvc.perform(formLogin().user("1").password("1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }



    /**
     * ошибка полномочий прав
     */
    @Test
    public void testBadCredentials() throws Exception{

        this.mockMvc.perform(post("/login").param("user" , "San"))
                .andDo(print())
                .andExpect(status().isForbidden()); // 403 статус не возможно получить доступ


    }

}
