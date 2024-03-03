package org.example.help;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class HelpRepositoryFile {
    public String getHelp() throws IOException {
        FileReader fileReader = new FileReader(
                Paths.get("src", "main", "java", "org", "example", "data", "help.txt").toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        StringBuilder help = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            help.append(line).append("\n");
        }
        bufferedReader.close();
        return help.toString();
    }
}
