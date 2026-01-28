package edu.kokhaniuk.security;

/*
  @author nataly
  @project security
  @class AccessTests
  @version 1.0.0
  @since 01/18/2026 - 17.41
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    public void whenAnonymThenStatusUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/weapons"))
                .andExpect(status().isUnauthorized());
    }

    // User requests different pages
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserVisitsUserPageAuthenticatedThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/weapons/hello/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserVisitsAdminPageAuthenticatedThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/weapons/hello/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserVisitsUnknownPageAuthenticatedThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/weapons/hello/unknown"))
                .andExpect(status().isOk());
    }

    // Admin requests different pages
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminVisitsAdminPageAuthenticatedThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/weapons/hello/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminVisitsUserPageAuthenticatedThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/weapons/hello/user"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminVisitsUnknownPageAuthenticatedThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/weapons/hello/unknown"))
                .andExpect(status().isOk());
    }

    // Superadmin requests different pages
    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperadminVisitsUserPageAuthenticatedThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/weapons/hello/user"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperadminVisitsAdminPageAuthenticatedThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/weapons/hello/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperadminVisitsUnknownPageAuthenticatedThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/weapons/hello/unknown"))
                .andExpect(status().isForbidden());
    }
}