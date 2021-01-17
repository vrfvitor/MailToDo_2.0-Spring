package br.com.mailtodo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class FileUtil {

	public String fetchResourceAsString(String filePath) {
		InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
		return new BufferedReader(new InputStreamReader(resourceStream)).lines()
				.collect(Collectors.joining(System.lineSeparator()));
	}

}
