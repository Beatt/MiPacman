package com.example.mipacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import musica.Musica;
import musica.MusicaAmbiente;
import personajes.Enemigo;
import personajes.Pacman;
import utiles.Utiles;
import escenario.Escenario;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import animatedSprite.AnimatedSprite;

public class JuegoPacman extends View implements Runnable {

	// Atributos
	private final static int ENEMIGOS = 4;
	
	private Escenario escenario;
	
	private Pacman pacman;
	
	private Bitmap fantasma,
				   imgPacmac;
	
	private int direccion,
				displayWidth,
				displayHeight;
	
	private Random r;
	
	private List<Enemigo> enemigos;
	
	private Musica intro, 
				   muerte,
				   ambienteFantasma;
	
	private MusicaAmbiente ambienteComer;
	
	private Context context;
	
	public static boolean comenzarJuego = false;
	
	private long tiempo;
	
	public JuegoPacman(Context context) {
		super(context);
	
		this.setBackgroundColor(Color.GREEN);
		this.context = context;
		
		
		
		// Cargar imagenes
		fantasma = BitmapFactory.decodeResource(getResources(),
				R.drawable.fantasma);
		
		imgPacmac = BitmapFactory.decodeResource(getResources(), R.drawable.pacman);
		
		// crea instanacia de Escenario
		escenario = new Escenario(context);

		// crear instancias de pacman
		pacman = new Pacman(imgPacmac);
		
		enemigos = new ArrayList<Enemigo>();
		
		r = new Random();
		
		crearEnemigos();
		
		inicializarMusica();
		intro.startMusica();
		intro.finMusica();
	}// Fin Pacman
	
	
	/*
	 * Objetivo: Crear enemigos.
	 */
	private void crearEnemigos() {
		
		for (int i = 0; i < ENEMIGOS; i++) 
		{
			
			Enemigo enemigo = new Enemigo(fantasma);
			enemigo.setDirec(r.nextInt(4));
			enemigos.add(enemigo);
		}
	}//Fin crearEnemigos
	
	/*
	 * Objetivo: Inicializar la música.
	 */
	private void inicializarMusica() {
		
		int[] musicaAmbienteFantasma = {R.raw.ambient_1, 
								R.raw.ambient_2,
								R.raw.ambient_3,
								R.raw.ambient_4,
								R.raw.ambient_eyes};
		
		int[] musicaAmbienteComer = {
										R.raw.eating_dot_1,
										R.raw.eating_dot_2
									};
		
		intro = new Musica(context.getApplicationContext(), R.raw.start_music);
		ambienteFantasma = new Musica(context.getApplicationContext(), musicaAmbienteFantasma[0]);
		muerte = new Musica(context.getApplicationContext(), R.raw.death);
		ambienteComer = new MusicaAmbiente(context.getApplicationContext(), musicaAmbienteComer);
		
	}//Fin inicializarMusica
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		displayWidth = canvas.getWidth();
	
		escenario.dibujarMapa(canvas, pacman, ambienteComer);
		
		dibujarControles();
		
		pacman.dibujarPacman(canvas);
		
		for (Enemigo enemigo : enemigos) 
		{
			enemigo.dibujarEnemigo(canvas);	 
		}
		
		colisionEntrePersonajes();
		
		
		
		postInvalidate();

	}// Fin onDraw

	@Override
	public void run() {

		while (true) {

			try {
											
				if(this.comenzarJuego)
				{
					moverEnemigo();
				}
				
				Thread.sleep(300);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Objetivo: Verificar colisión entre personajes.
	 * 
	 */
	public void colisionEntrePersonajes() {
		
		for (Enemigo enemigo : enemigos) 
		{
			if(pacman.getcX() >= enemigo.getcX() && pacman.getcX() < (enemigo.getcX() + Utiles.getImagenancho()) 
				&& pacman.getcY() >= enemigo.getcY() &&  pacman.getcY() < enemigo.getcY() + Utiles.getImagenalto())
			{		
					//Pausa el juego
					comenzarJuego = false;
					
					//inicia musica de muerte 
					muerte.startMusica();
					
					//Regresa a todos los enemigos a la posición por defecto.
					for (Enemigo enemigosAll : enemigos) 
					{
						enemigosAll.setcX(Enemigo.POSICIONDEFAULTX);
						enemigosAll.setcY(Enemigo.POSICIONDEFAULTY);
					}
					
					//Regresa a pacman a la posición de inicio.
					pacman.setcX(Utiles.getImagenancho()*8);
					pacman.setcY(Utiles.getImagenalto()*18);
					
					//Al finalizar la musica, comenzarJuego se vuelve true y comienza el juego.
					muerte.finMusica();
					
					
			}
		}
		
	}//Fin colisionEntrePersonajes
	
	/*
	 * Objetivo: Permite desplazar a pacman
	 */
	private void dibujarControles() {
		
		int anchoImagen = Utiles.getImagenancho();
		int altoImagen = Utiles.getImagenalto();
		
		
		if(direccion != 4)
		{
			
			
			//Atajo de pacman
			if(pacman.getcX() <= 0 )
			{
				pacman.setcX((escenario.getColumnas()-2)*Utiles.getImagenancho());
			}
			else if(pacman.getcX() >= displayWidth - Utiles.getImagenancho())
			{
				pacman.setcX(27);
			}
			
			//RIGHT
			if(direccion == 3) 
			{
				if(escenario.getEscenario2(pacman.getcY()/altoImagen, (pacman.getcX()+anchoImagen)/anchoImagen) != '1')
				{
					AnimatedSprite.mCurrentFrame = 4;
					pacman.setcX(pacman.getcX()+anchoImagen);
			
				}
				else
				{
					direccion = 4;
				}
				
			}
			
			//LEFT
			else if(direccion == 1) 
			{ 
				if(escenario.getEscenario2(pacman.getcY()/altoImagen, (pacman.getcX()-anchoImagen)/anchoImagen) != '1')
				{
					AnimatedSprite.mCurrentFrame = 0;
					pacman.setcX(pacman.getcX()-anchoImagen);
				}
				else
				{
					direccion = 4;
				}
			}
			
			//DOWN
			else if(direccion == 2) 
			{ 
				if(escenario.getEscenario2((pacman.getcY()+altoImagen)/altoImagen, pacman.getcX()/anchoImagen) != '1' && 
						escenario.getEscenario2((pacman.getcY()+altoImagen)/altoImagen, pacman.getcX()/anchoImagen) != 'F')
				{
					AnimatedSprite.mCurrentFrame = 9;
					pacman.setcY(pacman.getcY()+altoImagen);
				}
				else
				{
					direccion = 4;
				}
			}
			
			//UP
			else if(direccion == 0) { 
				if(escenario.getEscenario2((pacman.getcY()-altoImagen)/altoImagen, pacman.getcX()/anchoImagen) != '1')
				{	
					AnimatedSprite.mCurrentFrame = 7;
					pacman.setcY(pacman.getcY()-altoImagen);
				}
				else
				{
					direccion = 4;
				}
			}					
		}
	
		direccion = 4;
		
	}//Fin dibujarControles
	
	/*
	 * Objetivo: Permite desplazar a los enemigos.
	 */
	private void moverEnemigo() {

		int anchoImagen = Utiles.getImagenancho();
		int altoImagen = Utiles.getImagenalto();
		
		for (Enemigo enemigo : enemigos) 
		{
			//Atajo de fantasma
			if(enemigo.getcX() <= 0 )
			{
				enemigo.setcX((escenario.getColumnas() - 2 ) * Utiles.getImagenancho() );
			}
			else if(enemigo.getcX() >= displayWidth - Utiles.getImagenancho() )
			{
				enemigo.setcX(27);
			}
			
			if(enemigo.getDirec() == 3) 
			{
				if(escenario.getEscenario2(enemigo.getcY() / altoImagen, (enemigo.getcX() + anchoImagen) / anchoImagen) != '1'
						&& escenario.getEscenario2(enemigo.getcY()/altoImagen, (enemigo.getcX() + anchoImagen) / anchoImagen) != '|')
				{
					enemigo.setcX(enemigo.getcX() + anchoImagen);
				}
				else
				{	
					//Genera movimiento aleatorio
					enemigo.setDirec(r.nextInt(4));
				}
			}
			
			else if(enemigo.getDirec() == 1) 
			{
				if(escenario.getEscenario2(enemigo.getcY()/altoImagen, (enemigo.getcX()-anchoImagen)/anchoImagen) != '1'
						&& escenario.getEscenario2(enemigo.getcY()/altoImagen, (enemigo.getcX()-anchoImagen)/anchoImagen) != '|')
				{	
					enemigo.setcX(enemigo.getcX()-anchoImagen);
				}
				else
				{
					//Genera movimiento aleatorio
					enemigo.setDirec(r.nextInt(4));
				}
			}
			
			else if(enemigo.getDirec() == 2) 
			{
				if(escenario.getEscenario2((enemigo.getcY()+altoImagen)/altoImagen, enemigo.getcX()/anchoImagen) != '1'
					&& escenario.getEscenario2((enemigo.getcY()+altoImagen)/altoImagen, enemigo.getcX()/anchoImagen) != '|'
					&& escenario.getEscenario2((enemigo.getcY()+altoImagen)/altoImagen, enemigo.getcX()/anchoImagen) != 'F')
				{
					enemigo.setcY(enemigo.getcY()+altoImagen);
				}	
				else
				{
					//Genera movimiento aleatorio
					enemigo.setDirec(r.nextInt(4));
				}
			}
			
			else if(enemigo.getDirec() == 0) 
			{
				if(escenario.getEscenario2((enemigo.getcY()-altoImagen)/altoImagen, enemigo.getcX()/anchoImagen) != '1'
						&& escenario.getEscenario2((enemigo.getcY()-altoImagen)/altoImagen, enemigo.getcX()/anchoImagen) != '|')
				{
					enemigo.setcY(enemigo.getcY()-altoImagen);
				}	
				else
				{
					//Genera movimiento aleatorio
					enemigo.setDirec(r.nextInt(4));
				}
			}
			
		}	
		ambienteFantasma.startMusica();
		
	}//Fin moverEnemigo
	
	public void setDireccion(int direccion){
		this.direccion = direccion;
		
	}//Fin setDireccion
	
	
	
}// Fin class Pacman
