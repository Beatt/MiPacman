package musica;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class MusicaAmbiente implements IMusica{
	
	List<MediaPlayer> ambiente;
	MediaPlayer media;
	
	public MusicaAmbiente(Context context, int... resid) {
		
		media = new MediaPlayer();
		ambiente = new ArrayList<MediaPlayer>();
		
		for (int sonidos : resid) 
		{
			media.setAudioSessionId(sonidos);
			ambiente.add(MediaPlayer.create(context.getApplicationContext(), sonidos));
			
		}
	
	}
	
	public boolean startMusica() {
		
		try {
			
				ambiente.get(0).start();
				ambiente.get(0).setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						ambiente.get(1).start();
					}
					
				});
		}catch(IllegalStateException e) {
			e.getStackTrace();
			return false;
		}
		return true;
		
	}//Fin startMusica
	
	public boolean stopMusica() {
		
		try {
			
			for (MediaPlayer efecto : ambiente) 
			{
				efecto.stop();
			}
		}catch(IllegalStateException e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void finMusica() {
		// TODO Auto-generated method stub
	}

}
