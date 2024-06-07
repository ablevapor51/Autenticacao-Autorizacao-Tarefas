package br.com.gestaocliente.autorizacaodetarefas.seguranca;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SecurityConfig.class)
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="user", roles={"USER"})
    public void accessUserPage() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void accessAdminPage() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    public void accessAdminPageForbidden() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }
}
