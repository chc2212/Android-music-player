/* 
 * JAVA Programming - Android Application
 * Title: Media Palyer Sample Source
 * 
 * @author JIHOON YANG
 * @email jihoon.yang@gmail.com
 *
 */


//Ȯ���ڰ� mp3���� Ȯ���ؼ� mp3�ΰ͵鸸 �������ִ� 
package ex.hoon;

import java.io.File;
import java.io.FilenameFilter;

public class MediaMP3File implements FilenameFilter {
    public boolean accept(File dir, String filename) {
        return( filename.endsWith(".mp3") ); // Ȯ���ڰ� "mp3"���� Ȯ���Ѵ�.
     }
}
    

    
  