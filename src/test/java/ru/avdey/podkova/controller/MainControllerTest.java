package ru.avdey.podkova.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

/**
 * тестируем функциональность класса {@link MainController}
 */

//@Transactional
@RunWith(SpringRunner.class) // окружение которое будет стартовать наши тесты
@SpringBootTest             // говорит что тесты запускаем в окружении спринг бут
@AutoConfigureMockMvc
@WithUserDetails(value = "operator") // авторизуем пользователя

//@WithMockUser(value = "operator")

// оба теста прошли с открытыми ниже не пройдут
  //   @WithUserDetails  ("userDetailsServiceBeanName=UserService")
//@TestPropertySource("/application-test.properties") // говоррим спрингу где брать проперти
// указывает спрингу что нужно выполнить скрипт перед тестами
//@Sql(value ={"/create-user-before.sql" , "/car-list-before.sql"} , executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value ={ "/car-list-after.sql" , "/create-user-after.sql"} , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController mainController;



    /**
     * проверяем авторизовался ли пользователь
     *
     * @throws Exception
     */

    @Test

    public void testMainPage() throws Exception {

        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='navbarSupportedContent']/div").string("operator"));

    }


    /**
     * корректное отображение списка автомобилей
     */

    @Test
    public void testCarViewSuccess() throws Exception {

        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='car-list']/div").nodeCount(37));


    }




    @Test
    public void testCarFilterSuccess() throws Exception {
        this.mockMvc.perform(get("/main").param("filter", "lada"))
                .andDo(print())
                .andExpect(authenticated())   //  //*[@id="car-list"]/div
                .andExpect(xpath("//*[@id='car-list']/div[@data-id='53']").exists());
    }




}