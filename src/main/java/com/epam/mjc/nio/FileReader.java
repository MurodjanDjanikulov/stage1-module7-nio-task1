package com.epam.mjc.nio;

import java.io.File;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();

        Path filePath = Paths.get(file.getPath());
        try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            lines.forEach(line -> parseAndSetProfileData(line, profile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }

    private void parseAndSetProfileData(String line, Profile profile) {
        String[] keyValue = line.split(":");
        if (keyValue.length == 2) {
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            switch (key) {
                case "Name":
                    profile.setName(value);
                    break;
                case "Age":
                    profile.setAge(Integer.parseInt(value));
                    break;
                case "Email":
                    profile.setEmail(value);
                    break;
                case "Phone":
                    profile.setPhone(Long.parseLong(value));
                    break;
                default:
                    System.out.println("Wrong key");
                    break;
            }
        }
    }
}

