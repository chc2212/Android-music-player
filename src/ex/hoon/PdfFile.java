
package ex.hoon;
//Ȯ���ڰ� pdf���� Ȯ���ϴ� Ŭ����
import java.io.FilenameFilter;
import java.io.File;
  public class PdfFile implements FilenameFilter {
        public boolean accept(File dir, String filename) {
            return( filename.endsWith(".pdf") ); // Ȯ���ڰ� "pdf"���� Ȯ���Ѵ�.
         }
  }