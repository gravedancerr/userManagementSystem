package client;

import com.webservice.usermanagement.dto.EditUserDto;
import com.webservice.usermanagement.dto.UserDto;
import com.webservice.usermanagement.model.Role;
import com.webservice.usermanagement.model.User;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserRestControllerTest {
    HttpAuthenticationFeature feature = HttpAuthenticationFeature
            .basic("admin", "admin");
    private final Client client = ClientBuilder.newClient().register(feature);
    public static final int HTTP_STATUS = 200;
    private static final String URL = "http://localhost:8080/api/users";

    @Test
    public void shouldCreateUser() {
        Response resp = client.target(URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(UserDto.builder()
                        .login("person")
                        .password("person")
                        .email("person@gmail.com")
                        .firstName("Santa")
                        .lastName("Klaus")
                        .birthday(Date.valueOf("1950-12-25"))
                        .role(new Role("3", "person"))
                        .build(), MediaType.APPLICATION_JSON));
        assertEquals(HTTP_STATUS, resp.getStatus());
    }

    @Test
    public void shouldUpdateUser() {
        Response resp = client.target(URL + "/637bfac84e1e63628ae44c1d")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(EditUserDto.builder()
                        .id("637bfac84e1e63628ae44c1d")
                        .login("newuser")
                        .password("newuser")
                        .email("new@gmail.com")
                        .firstName("new")
                        .lastName("new")
                        .birthday(Date.valueOf("2000-02-02"))
                        .role(new Role("4", "new"))
                        .build(), MediaType.APPLICATION_JSON));
        assertEquals(HTTP_STATUS, resp.getStatus());
    }

    @Test
    public void shouldFindAllUsers() {
        Response resp = client.target(URL)
                .request(MediaType.APPLICATION_JSON).get();
        List<User> users = resp.readEntity(new GenericType<>() {});
        assertEquals(HTTP_STATUS, resp.getStatus());
        assertNotNull(users);
    }

    @Test
    public void shouldFindUserByLogin() {
        Response resp = client.target(URL + "/login/person")
                .request(MediaType.APPLICATION_JSON).get();
        User user = resp.readEntity(User.class);
        assertEquals(HTTP_STATUS, resp.getStatus());
        assertNotNull(user);
        assertEquals("person", user.getLogin());
    }

    @Test
    public void shouldFindUserByEmail() {
        Response resp = client
                .target(URL + "/email/person@gmail.com")
                .request(MediaType.APPLICATION_JSON).get();
        User user = resp.readEntity(User.class);
        assertEquals(HTTP_STATUS, resp.getStatus());
        assertNotNull(user);
        assertEquals("person@gmail.com", user.getEmail());
    }

    @Test
    public void shouldFindUserById() {
        Response resp = client.target(URL + "/id/637bfa814e1e63628ae44c1b")
                .request(MediaType.APPLICATION_JSON).get();
        User user = resp.readEntity(User.class);
        assertEquals(HTTP_STATUS, resp.getStatus());
        assertNotNull(user);
        assertEquals("637bfa814e1e63628ae44c1b", user.getId());
    }

    @Test
    public void shouldDeleteUser() {
        Response resp = client.target(URL + "/637bfac84e1e63628ae44c1d")
                .request(MediaType.APPLICATION_JSON).delete();
        User user = resp.readEntity(User.class);
        assertEquals(HTTP_STATUS, resp.getStatus());
        assertNull(user);
    }
}
