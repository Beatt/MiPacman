package personajes;

import utiles.Utiles;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import animatedSprite.AnimatedSprite;

public class Pacman {

	// Atributos
	private String color;
	private int velocidad = 5;
	private char[] mapa;
	private Bitmap pacman;
	private int cX, cY; // Cordenadas de la imagen
	private AnimatedSprite animatedSprite;
	private int vidas = 3;
	
	public Pacman(Bitmap pacman) {
		
		this.pacman = pacman;
		
		cX = Utiles.getImagenancho()*8;
		cY = Utiles.getImagenalto()*19;
		
		//Creamos una sprite de animación.
		animatedSprite = new AnimatedSprite(pacman, cX, cY, pacman.getHeight()/4, pacman.getWidth()/3, 
				2, 0, 50, 12, 3, 0);

	}// Fin Enemigos

	public int getcY() {
		return cY;
	}//Fin getcY

	public void setcY(int cY) {
		this.cY = cY;
	}//Fin setcY
	
	public int getcX() {
		return cX;
	}//Fin getcX

	public void setcX(int cX) {
		this.cX = cX;
	}//Fin setcX

	public void dibujarPacman(Canvas c) {
		
		//Actualizar animación Sprite.
		animatedSprite.Update(0);
		animatedSprite.setmXPos(cX);
		animatedSprite.setmYPos(cY);
		animatedSprite.draw(c);
		
	}// Fin dibujarEnemigo

	public String getColor() {
		return color;
	}// Fin getColor

	public void setColor(String color) {
		this.color = color;
	}// Fin setColor

	public int getVelocidad() {
		return velocidad;
	}// Fin getVelocidad

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}// Fin setVelocidad

	public char getMapa(int posicion) {
		return mapa[posicion];
	}// Fin getMapa

	public void setMapa(char[] mapa) {
		this.mapa = mapa;
	}// Fin setMapa

	public Bitmap getImagenEnemigo() {
		return pacman;
	}// Fin getImagenEnemigo

	public void setImagenEnemigo(Bitmap pacman) {
		this.pacman = pacman;
	}// Fin setImagenEnemigo

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

}// Fin class Enemigos
