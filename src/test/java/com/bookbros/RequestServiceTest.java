package com.bookbros;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.daos.RequestRepository;
import com.bookbros.models.Request;
import com.bookbros.models.User;
import com.bookbros.services.RequestService;


@SpringBootTest(classes= {RequestService.class})
public class RequestServiceTest {

	@Autowired
	private RequestService requestService;

	@MockBean
	private RequestRepository mockRequestRepository;

	static Request mock;
	static Request mock2;
	static Optional<Request> mockO;
	static Optional<Request> mockO2;
	static List<Request> mocks; 

	@BeforeAll
	public static void setUp(){
		User user = new User();

		Request p1 = new Request(1, user, "title", "author", "description");
		Request p2 = new Request(2, user, "title", "author", "description");

		mocks = new ArrayList<>();
		mocks.add(p1);
		mocks.add(p2);

		mock = p1;
		mock2 = p2;
	}

}
