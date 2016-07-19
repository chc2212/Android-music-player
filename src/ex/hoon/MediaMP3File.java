/* 
 * JAVA Programming - Android Application
 * Title: Media Palyer Sample Source
 * 
 * @author JIHOON YANG
 * @email jihoon.yang@gmail.com
 *
 */


//확장자가 mp3인지 확인해서 mp3인것들만 리턴해주는 
package ex.hoon;

import java.io.File;
import java.io.FilenameFilter;

public class MediaMP3File implements FilenameFilter {
    public boolean accept(File dir, String filename) {
        return( filename.endsWith(".mp3") ); // 확장자가 "mp3"인지 확인한다.
     }
}
    

    
  