package animatedSprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
 
public class AnimatedSprite {
 
    private Bitmap mBitmap;     // imagen original con la secuencia de frames
    private Rect mSourceRect;   // el rectángulo a ser pintado obtenido de la imagen original
    private int mNoOfFrames;    // número de frames en la animación
    public static int mCurrentFrame;  // el frame actual
    private long mFrameTimer;  // el tiempo en que ocurrio el último cambio de frame.
    private int mFramePeriod;   // duración en milisegundos entre frames (1000/fps)
    private int mSpriteWidth;  //el ancho del sprite para calcular el recorte del rectángulo.
    private int mSpriteHeight;   //la altura del sprite
    private int mColumns;
    private int mXPos;  // la coordenada X donde se va a imprimir la animación (esquina superior izquierda)
	private int mYPos;
    private int mOffsetX;  // el ajuste en x
    private int mOffsetY;
 
    
    public AnimatedSprite(Bitmap theBitmap, int x, int y, int Height, int Width, int offsetX, int offsetY, int theFPS, int theFrameCount, int columns, int currentFrame) {
        mBitmap = theBitmap;
        mXPos = x;
        mYPos = y;
        mOffsetX = offsetX;
        mOffsetY = offsetY;
        mFrameTimer = 0;
        mCurrentFrame = currentFrame;
        mSpriteHeight = Height;
        mSpriteWidth = Width;
        mSourceRect = new Rect(0, 0, mSpriteWidth, mSpriteHeight);
        mFramePeriod = 1000 / theFPS;
        mNoOfFrames = theFrameCount;
        mColumns = columns;
    }
 
    public int getmCurrentFrame() {
		return mCurrentFrame;
	}

	public void setmCurrentFrame(int mCurrentFrame) {
		this.mCurrentFrame = mCurrentFrame;
	}

	// El método actualizar
    public void Update(long GameTime) {
        if (GameTime > mFrameTimer + mFramePeriod) {
            mFrameTimer = GameTime;
            
            // incremento del frame
            mCurrentFrame += 1;
 
            if (mCurrentFrame >= mNoOfFrames) {
                mCurrentFrame = 0;
            }
        }
 
        mSourceRect.left = mCurrentFrame % mColumns * mSpriteWidth + mOffsetX;
        mSourceRect.right = mSourceRect.left + mSpriteWidth;
        mSourceRect.top = mCurrentFrame / mColumns * mSpriteHeight + mOffsetY;
        mSourceRect.bottom = mSourceRect.top + mSpriteHeight;
    }
 
    // el método draw que dibuja el correspondiente frame
    public void draw(Canvas canvas) {
        // donde se imprime el sprite
        Rect dest = new Rect(getXPos(), getYPos(), getXPos() + mSpriteWidth,
                getYPos() + mSpriteHeight);
   
        canvas.drawBitmap(mBitmap, mSourceRect, dest, null);
    }
 
    private int getXPos() {
        return mXPos;
    }
 
    private int getYPos() {
        return mYPos;
    }
    
    public int getmXPos() {
  		return mXPos;
  	}

  	public void setmXPos(int mXPos) {
  		this.mXPos = mXPos;
  	}

  	public int getmYPos() {
  		return mYPos;
  	}

  	public void setmYPos(int mYPos) {
  		this.mYPos = mYPos;
  	}
}
