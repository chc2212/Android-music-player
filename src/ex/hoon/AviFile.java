package ex.hoon;

import java.io.File;
import java.io.FilenameFilter;

//확장자가 avi인지 확인하는 클래스
public class AviFile implements FilenameFilter {
    public boolean accept(File dir, String filename) {
        return( filename.endsWith(".avi") ); // 확장자가 ".avi"인지 확인한다.
     }
    
}