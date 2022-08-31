package com.bruncts.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bruncts.workshopmongo.domain.Post;
import com.bruncts.workshopmongo.domain.User;
import com.bruncts.workshopmongo.dto.AuthorDTO;
import com.bruncts.workshopmongo.repositories.PostRepository;
import com.bruncts.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post pos1 = new Post(null, sdf.parse("30/08/2022"), "Let's go to Brazil!", "I gonna travel to Brazil!",
				new AuthorDTO(maria));
		Post pos2 = new Post(null, sdf.parse("31/08/2022"), "Good Morning Brazil", "I waked up in other country today.",
				new AuthorDTO(maria));

		postRepository.saveAll(Arrays.asList(pos1, pos2));
	}

}
