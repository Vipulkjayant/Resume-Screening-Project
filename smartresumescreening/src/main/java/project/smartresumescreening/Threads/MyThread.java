package project.smartresumescreening.Threads;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import project.smartresumescreening.Reader.PdfReader;
public class MyThread extends Thread{
    


    private MultipartFile file;
    private String name;
    private List<MultipartFile> FilteredResume;

    public MyThread()
    {
        
    }

    public MyThread(MultipartFile file,String name,List<MultipartFile> files)
    {
       this.file=file;
       this.name=name;
       this.FilteredResume=files;
    }

    @Override
    public void run()
    {

 
            try {

      //1.provides how much words are matching to job Role inside resume

                PdfReader pdfReader=new PdfReader(file,name); 
                int count= pdfReader.IsPDFExists();


      //2.Condition-->Exclude the resume that is not related to Job-role          

                if(count>0)
                {

     //3.Safety-->Multiple threads can override the list that leads to data corruption inside the list

                synchronized(FilteredResume) 
                    {
                        FilteredResume.add(file);

                    }

                }

    } catch (Exception e) {
            System.out.println("Exception occurs..........");
            e.printStackTrace();
                            }
                
                        }
                


     //4.Filtered Resumes List                   

      public List<MultipartFile> getFilteredResume()
      {

        return FilteredResume;
      }


    

}
