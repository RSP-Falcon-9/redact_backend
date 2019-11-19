package cz.falcon9.redact.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class AdminTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        
    }

    @Test
    void getUsers_asUnauthenticatedUser_mustThrowUnauthorizedError() throws Exception {
        this.mockMvc.perform(get("/admin/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
    
    @Test
    @WithMockUser(username = "DummyUser")
    void getUsers_asNonAdmin_mustThrowForbiddenError() throws Exception {
        this.mockMvc.perform(get("/admin/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}
