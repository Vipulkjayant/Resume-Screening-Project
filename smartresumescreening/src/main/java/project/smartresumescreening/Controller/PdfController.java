package project.smartresumescreening.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import project.smartresumescreening.Threads.MyThread;

@RestController
public class PdfController {

    @PostMapping("/pdf")
    public List<String> getpdf(@RequestParam("files") MultipartFile[] files) throws Exception {

        int size = files.length;

        List<MultipartFile> files2 = new ArrayList<>();

        List<MyThread> threads = new ArrayList<>();

        // 1.Declaring threads on each file

        for (int i = 0; i < size; i++) {

            MyThread t = new MyThread(files[i], "Thread" + i, files2);
            threads.add(t);
            t.start();

        }

        // 2.waiting till all thread to complete their work

        for (MyThread myThread : threads) {
            myThread.join();
        }

        // 3.Convert filtered resumes to list of file names

        List<String> filteredFiles = new ArrayList<>();

        for (MultipartFile file : files2) {
            filteredFiles.add(file.getOriginalFilename());
        }

        return filteredFiles;
    }
}
