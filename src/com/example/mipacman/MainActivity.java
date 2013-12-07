package com.example.mipacman;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import animatedSprite.AnimatedSprite;

public class MainActivity extends Activity implements OnTouchListener {

	private JuegoPacman pacman;
	
	private Thread hilo;
	
	private LinearLayout l;
	
	private ImageButton botonArriba, 
						botonAbajo, 
						botonIzquierda, 
						botonDerecha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
		cargarIds();
		cargarEventoOnTouchListener();
	
		pacman = new JuegoPacman(this, this.getIntent());
		l.addView(pacman);
		hilo = new Thread(pacman);
		hilo.start();

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(MotionEvent.ACTION_DOWN == event.getAction())
		{
			if(JuegoPacman.comenzarJuego) 
			{
				int direccion = -1;
				if (v.getId() == R.id.arriba)
				{
					direccion = 0;
				}
				else if (v.getId() == R.id.abajo)
				{
					direccion = 2;
				}
				else if (v.getId() == R.id.derecha)
				{
					direccion = 1;
				}
				else if (v.getId() == R.id.izquierda)
				{
					direccion = 3;
				}	
				pacman.setDireccion(direccion);
			}	
		}
		
		if(MotionEvent.ACTION_UP == event.getAction())
		{
			AnimatedSprite.mCurrentFrame = 2;
		}
		
		return true;
	}

	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		JuegoPacman.comenzarJuego = false;
	}

	public void cargarIds() {

		botonArriba = (ImageButton) findViewById(R.id.arriba);
		botonAbajo = (ImageButton) findViewById(R.id.abajo);
		botonDerecha = (ImageButton) findViewById(R.id.derecha);
		botonIzquierda = (ImageButton) findViewById(R.id.izquierda);
		l = (LinearLayout) findViewById(R.id.layout);
	}

	public void cargarEventoOnTouchListener() {
		botonArriba.setOnTouchListener(this);
		botonAbajo.setOnTouchListener(this);
		botonDerecha.setOnTouchListener(this);
		botonIzquierda.setOnTouchListener(this);
	}
}// Fin MainActivity
