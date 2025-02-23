package project.smartresumescreening.Algorithms;

import java.util.List;

public class CountWords {

    public int countIt(List<String> li, String job_role) {
        int count = 0;
        job_role = job_role.toLowerCase().trim(); // Normalize the job role

        for (String word : li) {
            if (word.trim().equalsIgnoreCase(job_role)) { // Ignore case & trim spaces
                count++;
            }
        }

        return count;
    }
}
