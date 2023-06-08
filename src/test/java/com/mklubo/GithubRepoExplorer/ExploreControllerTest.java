package com.mklubo.GithubRepoExplorer;

import com.mklubo.GithubRepoExplorer.exception.UsernameNotExistsException;
import com.mklubo.GithubRepoExplorer.model.BranchInfo;
import com.mklubo.GithubRepoExplorer.model.UserRepositoryData;
import com.mklubo.GithubRepoExplorer.service.ExplorerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ExploreControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@MockBean
	private ExplorerService explorerService;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void whenUsernameExistsReturns200() throws Exception {
		UserRepositoryData data = new UserRepositoryData("Repository_name", "username",
				List.of(new BranchInfo("master","dasdawmnoi5n41")));

		when(explorerService.getUserRepositoryData(anyString())).thenReturn(List.of(data));

		mockMvc.perform(get("/api/explorer/username")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].repository_name").value("Repository_name"))
				.andExpect(jsonPath("$[0].owner").value("username"))
				.andExpect(jsonPath("$[0].branch_info[0].sha").value("dasdawmnoi5n41"))
				.andExpect(jsonPath("$[0].branch_info[0].name").value("master"));
	}

	@Test
	public void whenUsernameDoesNotExistReturns404() throws Exception {
		when(explorerService.getUserRepositoryData(anyString())).thenThrow(new UsernameNotExistsException("User not found"));
		String nonExistentUser = "dmwoamdoaotn";
		mockMvc.perform(get("/api/explorer/"+ nonExistentUser)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void whenUnsupportedMediaTypeIsRequestedReturns406() throws Exception {
		mockMvc.perform(get("/api/explorer/username")
						.accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().isNotAcceptable());
	}

}
