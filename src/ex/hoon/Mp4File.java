package ex.hoon;

import java.io.File;
import java.io.FilenameFilter;
//Ȯ���ڰ� mp4���� Ȯ���ϴ� Ŭ����
public class Mp4File implements FilenameFilter {
    public boolean accept(File dir, String filename) {
        return( filename.endsWith(".mp4") ); // Ȯ���ڰ� "mp4"���� Ȯ���Ѵ�.
     }
}

 