/* 
 * JAVA Programming - Android Application
 * Title: Media Player Sample Source
 * 
 * @author JIHOON YANG
 * @email jihoon.yang@gmail.com
 *
 */


package ex.hoon;
//091895 ��ȣö
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
    private ArrayList mediaList = new ArrayList();  //����Ʈ �������
    private MediaPlayer mp = new MediaPlayer();
    private int curMediaPos = 0;  //���ϸ���� ������
    private int curpos=0;  //����ð� ������
    int mp3n=0;  //.mp3������  ����

  
   
    
    //-------------��ư����------------//
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
//FilenameFilter���ؼ� Ȯ���ڸ���Ʈ �����ͼ� ����Ʈ ����
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
   
    	//mp3�ƴ� ������ Ŭ���������� ���������ǿ� �� �������� ���� �ʴ´� 
    	if(position < mp3n)
        curMediaPos = position;
    	else
    	;
        
        
        //----------------mp3�������� �ƴ��� �ν� ------------//
    
        if(position < mp3n)
        {   
        	mediaPlayer( MEDIA_PATH +mediaList.get(position) );
       
        }
       else
    	   Toast.makeText(this, "�߸��� ���� �����Դϴ�" , Toast.LENGTH_SHORT).show();
        
        
    }
//-----------------------�̵�� �÷��̾� ����---------
    private void mediaPlayer(String mediaPath) {
        try {
        	
        	mp.reset();
            mp.setDataSource(mediaPath);
            startMediaPlayer();
       
            //�Ѱ� ����� ������ ���������� �Ѿ�� ��ɱ���
            
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){ 
        	public void onCompletion(MediaPlayer mp) {
        		nextMediaPlay();
              
              }
               
                        
                
            });
           
            
        } catch(IOException e) {

        }
    }        
//---------------------���������� ���� �޼ҵ�----
    private void prevMediaPlay() {
    
    	
    	if(!(curMediaPos==0)) 
    	    	{
    	    	    
    	    	    //Prev��ư�� ������ �ڷ� ��ĭ���̵�
    	    	        	curMediaPos--;
    	    	        	mediaPlayer( MEDIA_PATH + mediaList.get(curMediaPos) );
    	    	        }  	        		
    	    	 
    	    	else
    	    		ErrorPrev();  //���� ó���� �ִ� mp3�� ���õǾ��� ���� ErrorPrev �޼ҵ忡�� ó�����ش�.
    	    	        
    	    	}
    	
  //---------------------���������� ���� �޼ҵ�----
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
    
    private void FastBackward(){  //������ 10�� �̵� 
    	
    	curpos=mp.getCurrentPosition();
    	mp.seekTo(curpos-10000);
    	
    }
    
    
    private void FastForward(){  // �ڷ� 10�� �̵�
    	
    	curpos=mp.getCurrentPosition();
    	mp.seekTo(curpos+10000);
    	
    }
    

    
  //-----------------------�̵�� �÷��̾� ����---------
    private void startMediaPlayer(){
    	
    	try {
    	
    		if(mp.isPlaying()==false){
    			
    			mp.reset();   //��ó���� �ƹ��͵� ������ �ʰ� , Start������ �� ���� ���� �� �ڵ� �߰�
        		mp.setDataSource(MEDIA_PATH + mediaList.get(curMediaPos));    
    			mp.prepare(); 
    			mp.start();
    	    	
    		}
		} catch (IOException e) {
		
			
           
		}
    }
    // ����
    private void stopMediaPlayer(){
    	mp.seekTo(0);
    	mp.stop();
    	
    	
    }
    //�Ͻ�����
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
   
    private void volumeUp(){ //�������� �޼ҵ�
    	AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    	audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE,1);
    	
    	
    }
    private void volumeDown(){
    	AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    	audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER,1);
    	
    	
    }
    
    //mp3������ �ƴѰ�� �������Ϸ� �Ѿ�� �޼ҵ�
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
  //mp3������ �ƴѰ�� �������Ϸ� �Ѿ�� �޼ҵ�
    private void ErrorPrev(){ 
        
    
    	 curMediaPos = mediaList.size()-1;
	    while(curMediaPos+1 > mp3n)
	    {
    		  curMediaPos--;
        }
    	
    	mediaPlayer( MEDIA_PATH + mediaList.get(curMediaPos) );
    	
    	
    }
	

 
    
   
 
    
    //�޴�â 
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
    	case 1: // �Ͻ�����
    		pauseMediaPlayer();
    		
    		break;
    	case 2:// �ٽ����
    		resumeMediaPlayer();
    		break;
    	case 3:
    		nextMediaPlay(); // ������
    	case 4:
    		prevMediaPlay(); //������
    	   		
    	}
		return true;
    }

}


    
    