package ex.hoon;

import java.io.File;
import java.io.FilenameFilter;

//Ȯ���ڰ� avi���� Ȯ���ϴ� Ŭ����
public class AviFile implements FilenameFilter {
    public boolean accept(File dir, String filename) {
        return( filename.endsWith(".avi") ); // Ȯ���ڰ� ".avi"���� Ȯ���Ѵ�.
     }
    
}