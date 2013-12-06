package musica;

import com.example.mipacman.JuegoPacman;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class Musica implements IMusica{
	
	private MediaPlayer mediaPlayer;

	public Musica(Context context, int resid) {
		
		mediaPlayer = MediaPlayer.create(context.getApplicationContext(), resid);
		
	}
		
	public boolean startMusica() {
		
		try {
			
			mediaPlayer.start();
			
		}catch(IllegalStateException e) {
			e.getStackTrace();
			return false;
		}
		return true;
		
	}//Fin startMusica
	
	public boolean stopMusica() {
		
		try {
			
			mediaPlayer.stop();
			
		}catch(IllegalStateException e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}
	
	public void finMusica() {
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
            	JuegoPacman.comenzarJuego = true;
            }

        });
		
	}//Fin finMusica
	
}
