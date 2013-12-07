package escenario;

import musica.MusicaAmbiente;
import personajes.Pacman;
import utiles.Utiles;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.mipacman.R;

public class Escenario{
	
	
	private final int filas = 22,
					  columnas = 18;

	private static int countComida;
	
	private Bitmap imagenMuro, 
				   imagenMoneda,
				   imagenPuerta;
	

	protected char[][] escenario2 = new char[][]{
			{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
			{'1','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','1'},
			{'1','0','1','0','0','0','0','0','1','0','0','0','0','0','0','1','0','1'},
			{'1','0','1','0','1','1','1','0','1','0','1','1','1','1','0','1','0','1'},
			{'1','0','1','0','1',' ','1','0','1','0','1',' ',' ','1','0','1','0','1'},
			{'1','0','1','0','1',' ','1','0','1','0','1',' ',' ','1','0','1','0','1'},
			{'1','0','0','0','1','1','1','0','0','0','1','1','1','1','0','0','0','1'},
			{'1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1'},
			{'1','1','1','0','0','0','0','0','0','0','0','0','0','0','0','1','1','1'},
			{' ','0','0','0','0','0','0','0','0','0','0','0','1','0','0','0','0',' '},
			{' ','0','0','0','1','0','0','1','-','1','0','0','1','0','0','0','0',' '},
			{'1','1','1','0','1','0','0','1','F','1','0','0','1','0','0','1','1','1'},
			{'1','0','0','0','1','0','0','1','F','1','0','0','1','0','0','0','0','1'},
			{'1','0','0','0','0','0','0','1','1','1','0','0','0','0','0','0','0','1'},
			{'1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1'},
			{'1','0','1','0','1','1','1','0','0','0','1','1','1','1','0','1','0','1'},
			{'1','0','1','0','1',' ','1','0','0','0','1',' ',' ','1','0','1','0','1'},
			{'1','0','1','0','1',' ','1','0','1','0','1',' ',' ','1','0','1','0','1'},
			{'1','0','1','0','1','1','1','0','1','0','1','1','1','1','0','1','0','1'},
			{'1','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','1'},
			{'1','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','1'},
			{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}
	};

	public Escenario(Context c) {
		imagenMuro = BitmapFactory.decodeResource(c.getResources(), R.drawable.muro);
		imagenMoneda = BitmapFactory.decodeResource(c.getResources(), R.drawable.moneda);
		//imagenPuerta = BitmapFactory.decodeResource(c.getResources(), R.drawable.);
		
	}

	public void dibujarMapa(Canvas c, Pacman e, MusicaAmbiente ambienteComer)
	{
		
		int cordenadaX = 0 , cordenadaY = 0;
				
		for (int i = 0; i < filas; i++) {
			
			
			for (int j = 0; j < columnas; j++) {
				
				if(escenario2[i][j] == '1') 
				{
					//Wall draw
					c.drawBitmap(imagenMuro, cordenadaX, cordenadaY, null);
					
				}
				else if(escenario2[i][j] == '-')
				{
					c.drawBitmap(imagenMuro, cordenadaX, cordenadaY, null);
				}
				else if(escenario2[i][j] == '0')
				{
					//Coin draw
					c.drawBitmap(imagenMoneda, cordenadaX, cordenadaY, null);
					
					if(e.getcY()/Utiles.getImagenalto() == i 
							&& e.getcX()/Utiles.getImagenancho() ==  j)
					{
						ambienteComer.startMusica();
						escenario2[i][j] = ' ';
	
					}
					
				}
				cordenadaX +=Utiles.getImagenancho();
			}
			cordenadaX = 0;
			cordenadaY +=Utiles.getImagenalto();
			
		}
	}//Fin dibujarMapa
	
	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}
	
	
	public char getEscenario2(int x, int y) {
		return escenario2[x][y];
	}

	public void setEscenario2(char[][] escenario2) {
		this.escenario2 = escenario2;
	}

}//SFin class Escenario
