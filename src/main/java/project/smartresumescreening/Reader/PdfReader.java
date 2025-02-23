package project.smartresumescreening.Reader;

import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import project.smartresumescreening.Algorithms.ConvertTextToList;
import project.smartresumescreening.Algorithms.CountWords;

public class PdfReader {

  private MultipartFile file;
  private String name;

  public PdfReader(MultipartFile file, String name) {

    this.file = file;
    this.name = name;

  }

  public int IsPDFExists() throws Exception {

    File filee = null;
    String job_role = "Developer";
    int count = 0;

    try {

      // 1.Converting multipart file to file

      filee = File.createTempFile("resume_" + name, ".pdf");
      file.transferTo(filee);
      PDDocument pdDocument = PDDocument.load(filee);

      // 2.Extract the text from the file

      PDFTextStripper pdfTextStripper = new PDFTextStripper();
      String text = pdfTextStripper.getText(pdDocument);

      //3. Algo-1-->convert the words of the strings into the array of words ,
      // based on space

      ConvertTextToList convertTextToList = new ConvertTextToList();
      List<String> li = convertTextToList.convert(text);

      //4. Algo-2-->Find matching word and count it

      CountWords countWords = new CountWords();
      count = countWords.countIt(li, job_role);
      pdDocument.close();

    } catch (Exception e) {
      e.printStackTrace();
   }
   
      //5.Free up the resource of unused file
   
     finally {
      if (filee != null && filee.exists()) {

        filee.delete();

      }
    }

    return count;

  }

}
