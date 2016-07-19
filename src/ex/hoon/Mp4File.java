package ex.hoon;

import java.io.File;
import java.io.FilenameFilter;
//확장자가 mp4인지 확인하는 클래스
public class Mp4File implements FilenameFilter {
    public boolean accept(File dir, String filename) {
        return( filename.endsWith(".mp4") ); // 확장자가 "mp4"인지 확인한다.
     }
}

 