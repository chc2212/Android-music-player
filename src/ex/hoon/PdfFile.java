
package ex.hoon;
//확장자가 pdf인지 확인하는 클래스
import java.io.FilenameFilter;
import java.io.File;
  public class PdfFile implements FilenameFilter {
        public boolean accept(File dir, String filename) {
            return( filename.endsWith(".pdf") ); // 확장자가 "pdf"인지 확인한다.
         }
  }