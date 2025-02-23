package project.smartresumescreening.Algorithms;

import java.util.ArrayList;
import java.util.List;

public class ConvertTextToList {

    public List<String> convert(String text) {

        List<String> li = new ArrayList<>();
        StringBuilder res = new StringBuilder();

        for (int j = 0; j < text.length(); j++) {
            char ch = text.charAt(j);

            if (ch == ' ') {
                if (res.length() > 0) { // Add the word before space
                    li.add(res.toString());
                    res.setLength(0);
                }
                li.add(" "); // Add space separately
            } else {
                res.append(ch);
            }
        }

        // Add last word if any
        if (res.length() > 0) {
            li.add(res.toString());
        }

        return li;
    }
}
