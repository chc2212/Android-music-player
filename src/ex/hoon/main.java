/* 
 * JAVA Programming - Android Application
 * Title: Media Player Sample Source
 * 
 * @author JIHOON YANG
 * @email jihoon.yang@gmail.com
 *
 */


package ex.hoon;
//091895 조호철
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class main extends ListActivity {
	
	
	public MediaMP3File filter;
    
	private final String MEDIA_PATH = new String("/sdcard/");
    private ArrayList mediaList = new ArrayList();  //리스트 저장공간
    private MediaPlayer mp = new MediaPlayer();
    private int curMediaPos = 0;  //파일목록의 포지션
    private int curpos=0;  //재생시간 포지션
    int mp3n=0;  //.mp3파일의  갯수

  
   
    
    //-------------버튼생성------------//
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        Button startKey = (Button)findViewById(R.id.BtnStart);
        startKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		startMediaPlayer();
        	}
        });
        
        Button stopKey = (Button)findViewById(R.id.BtnStop);
        stopKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		stopMediaPlayer();
        	}
        });
        
        Button pauseKey = (Button)findViewById(R.id.BtnPause);
        pauseKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		pauseMediaPlayer();
        	}
        });
        
        Button resumeKey = (Button)findViewById(R.id.BtnResume);
        resumeKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		resumeMediaPlayer();
        	}
        });
        
        Button PrevKey = (Button)findViewById(R.id.BtnPrev);
        PrevKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		prevMediaPlay();
        	}
        });
        
        Button NextKey = (Button)findViewById(R.id.BtnNext);
        NextKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		nextMediaPlay();
        	}
        });
        
        Button FastforwardKey = (Button)findViewById(R.id.BtnFastForward);
        FastforwardKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		FastBackward();
        	}
        });
        
        Button FastBackwardKey = (Button)findViewById(R.id.BtnFastBackward);
        FastBackwardKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		FastForward();
        	}
        });
        
        Button VolumeUpKey = (Button)findViewById(R.id.BtnVolumeUp);
        VolumeUpKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		volumeUp();
        	}
        });
        
        Button VolumeDownKey = (Button)findViewById(R.id.BtnVolumeDown);
        VolumeDownKey.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		volumeDown();
        	}
        });
        
        

        GetMediaList();
    }
    
    public void GetMediaList(){
        File mediaFile = new File(MEDIA_PATH);
//FilenameFilter통해서 확장자리스트 가져와서 리스트 구성
        if( mediaFile.listFiles(new MediaMP3File()).length > 0 ) {
            for( File file : mediaFile.listFiles(new MediaMP3File()) ) {
            	mediaList.add(file.getName());
            	mp3n++;
            	
            }
         
            if( mediaFile.listFiles(new AviFile()).length > 0 ) {
                for( File file : mediaFile.listFiles(new AviFile()) ) {
                   mediaList.add(file.getName());
                }
            }
            
            if( mediaFile.listFiles(new Mp4File()).length > 0 ) {
                for( File file : mediaFile.listFiles(new Mp4File()) ) {
                   mediaList.add(file.getName());
                }
            }
            
            if( mediaFile.listFiles(new PdfFile()).length > 0 ) {
                for( File file : mediaFile.listFiles(new PdfFile()) ) {
                   mediaList.add(file.getName());
                }
            }

            ArrayAdapter listadapter = new ArrayAdapter(this, R.layout.media_item, mediaList);
            setListAdapter(listadapter);
        }

    }
   

    protected void onListItemClick(ListView l, View v, int position, long id) {
   
    	//mp3아닌 파일을 클릭했을때는 현재포지션에 그 포지션을 넣지 않는다 
    	if(position < mp3n)
        curMediaPos = position;
    	else
    	;
        
        
        //----------------mp3파일인지 아닌지 인식 ------------//
    
        if(position < mp3n)
        {   
        	mediaPlayer( MEDIA_PATH +mediaList.get(position) );
       
        }
       else
    	   Toast.makeText(this, "잘못된 파일 형식입니다" , Toast.LENGTH_SHORT).show();
        
        
    }
//-----------------------미디어 플레이어 실행---------
    private void mediaPlayer(String mediaPath) {
        try {
        	
        	mp.reset();
            mp.setDataSource(mediaPath);
            startMediaPlayer();
       
            //한곡 재생이 끝나면 다음곡으로 넘어가는 기능구현
            
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){ 
        	public void onCompletion(MediaPlayer mp) {
        		nextMediaPlay();
              
              }
               
                        
                
            });
           
            
        } catch(IOException e) {

        }
    }        
//---------------------이전곡으로 가는 메소드----
    private void prevMediaPlay() {
    
    	
    	if(!(curMediaPos==0)) 
    	    	{
    	    	    
    	    	    //Prev버튼을 누르면 뒤로 한칸식이동
    	    	        	curMediaPos--;
    	    	        	mediaPlayer( MEDIA_PATH + mediaList.get(curMediaPos) );
    	    	        }  	        		
    	    	 
    	    	else
    	    		ErrorPrev();  //제일 처음에 있는 mp3가 선택되었을 때는 ErrorPrev 메소드에서 처리해준다.
    	    	        
    	    	}
    	
  //---------------------다음곡으로 가는 메소드----
    private void nextMediaPlay() {
    	
    	if(curMediaPos+1 < mp3n)
    	{
        if( curMediaPos >= mediaList.size()-1 ) {
            curMediaPos = 0;
            mediaPlayer( MEDIA_PATH + mediaList.get(curMediaPos) );
        } else {
        	curMediaPos++;
            mediaPlayer( MEDIA_PATH + mediaList.get(curMediaPos) );
        }
    	}
    	else
        ErrorNext();
    	
    }
    
    private void FastBackward(){  //앞으로 10초 이동 
    	
    	curpos=mp.getCurrentPosition();
    	mp.seekTo(curpos-10000);
    	
    }
    
    
    private void FastForward(){  // 뒤로 10초 이동
    	
    	curpos=mp.getCurrentPosition();
    	mp.seekTo(curpos+10000);
    	
    }
    

    
  //-----------------------미디어 플레이어 실행---------
    private void startMediaPlayer(){
    	
    	try {
    	
    		if(mp.isPlaying()==false){
    			
    			mp.reset();   //맨처음에 아무것도 누르지 않고 , Start눌렀을 때 에러 떠서 이 코드 추가
        		mp.setDataSource(MEDIA_PATH + mediaList.get(curMediaPos));    
    			mp.prepare(); 
    			mp.start();
    	    	
    		}
		} catch (IOException e) {
		
			
           
		}
    }
    // 중지
    private void stopMediaPlayer(){
    	mp.seekTo(0);
    	mp.stop();
    	
    	
    }
    //일시정지
    private void pauseMediaPlayer(){
    	if(mp.isPlaying()==true){
    		mp.pause();
    	}
  
    }
    
    private void resumeMediaPlayer(){
    	if(mp.isPlaying()==false){
    	   mp.start();
         
    	}

    }
   
    private void volumeUp(){ //볼륨조절 메소드
    	AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    	audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE,1);
    	
    	
    }
    private void volumeDown(){
    	AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    	audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER,1);
    	
    	
    }
    
    //mp3파일이 아닌경우 다음파일로 넘어가는 메소드
    private void ErrorNext(){ 
    	
    	if(curMediaPos+1 >= mp3n)
    	{
    	  while(curMediaPos+1 >= mp3n)
	    {
		  	
    	 if( curMediaPos >= mediaList.size()-1 ) 
    	 {
             curMediaPos = 0;
           
         }
    	 else
    	  	curMediaPos++;
        
	 
	    }
    	
    	mediaPlayer( MEDIA_PATH + mediaList.get(curMediaPos) );
    	}
    	
    	
    }
  //mp3파일이 아닌경우 이전파일로 넘어가는 메소드
    private void ErrorPrev(){ 
        
    
    	 curMediaPos = mediaList.size()-1;
	    while(curMediaPos+1 > mp3n)
	    {
    		  curMediaPos--;
        }
    	
    	mediaPlayer( MEDIA_PATH + mediaList.get(curMediaPos) );
    	
    	
    }
	

 
    
   
 
    
    //메뉴창 
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuItem item=menu.add(0, 1, 0, "pause");
    	menu.add(0,2,0,"resume");
    	menu.add(0,3,0,"next");
    	menu.add(0,4,0,"prev");
		return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	
    	switch(item.getItemId()){
    	case 1: // 일시정지
    		pauseMediaPlayer();
    		
    		break;
    	case 2:// 다시재생
    		resumeMediaPlayer();
    		break;
    	case 3:
    		nextMediaPlay(); // 다음곡
    	case 4:
    		prevMediaPlay(); //이전곡
    	   		
    	}
		return true;
    }

}


    
    