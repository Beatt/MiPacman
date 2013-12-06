package personajes;

import utiles.Utiles;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Enemigo{
	
	private Bitmap imagen;
	private int velocidad = 5;
	private int cX, cY;
	private int direc;
	public static final int POSICIONDEFAULTX = Utiles.getImagenancho()*8;
	public static final int POSICIONDEFAULTY = Utiles.getImagenalto()*12;
	public Enemigo(Bitmap imagen) {
		this.imagen = imagen;
		cX = Utiles.getImagenancho()*8;
		cY = Utiles.getImagenalto()*12;
	}
	
	public void dibujarEnemigo(Canvas c) {
		c.drawBitmap(imagen, cX, cY, null);
	}
	
	public Bitmap getImagen() {
		return imagen;
	}

	public void setImagen(Bitmap imagen) {
		this.imagen = imagen;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getcX() {
		return cX;
	}

	public void setcX(int cX) {
		this.cX = cX;
	}

	public int getcY() {
		return cY;
	}

	public void setcY(int cY) {
		this.cY = cY;
	}

	public int getDirec() {
		return direc;
	}

	public void setDirec(int direc) {
		this.direc = direc;
	}

}//Fin Enemigo
