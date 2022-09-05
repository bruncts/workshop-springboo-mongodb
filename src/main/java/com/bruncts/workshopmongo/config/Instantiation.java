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
import com.bruncts.workshopmongo.dto.CommentDTO;
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

		Post post1 = new Post(null, sdf.parse("30/08/2022"), "Let's go to Brazil!", "I gonna travel to Brazil!",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("31/08/2022"), "Good Morning Brazil", "I waked up in other country today.",
				new AuthorDTO(maria));

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().add(post1);
		maria.getPosts().add(post2);
		 
		userRepository.save(maria);
		
		CommentDTO c1 = new CommentDTO("have a nice trip!", sdf.parse("30/08/2022"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Enjoy the journey!", sdf.parse("31/08/2022"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("have a nice day", sdf.parse("01/09/2022"), new AuthorDTO(alex));
	
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
			
	}

}
