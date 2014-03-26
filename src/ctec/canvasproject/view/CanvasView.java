package ctec.canvasproject.view;

import ctec.samplecanvasprojectapp.activities.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CanvasView extends ImageView
{
	private Context currentContext;
	private Runnable canvasRunnable;
	private BitmapDrawable yesAndroid;
	private BitmapDrawable noAndroid;
	private Handler canvasHandler;
	private final int FRAME_RATE = 30;
	private int backgroundColor = Color.GREEN;
	/**
	 * Position variables for the images.
	 */
	private int yesX, yesY, noX, noY;
	/**
	 * Speed values for the images
	 */
	private int noXVelocity, noYVelocity, yesXVelocity, yesYVelocity;

	/**
	 * Creates a CanvasView object for use within Android.  This is a widget designed to load images and basic animation.
	 * @param context
	 * @param attrs
	 */
	public CanvasView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		currentContext = context;

		// Initialize position variables.
		yesX = 23;
		yesY = 30;
		noX = 50;
		noY = 17;

		// Initialize the speeds of the images
		yesXVelocity = 5;
		noXVelocity = 10;

		yesYVelocity = 15;
		noYVelocity = 5;

		canvasRunnable = new Runnable()
		{

			@Override
			public void run()
			{
				invalidate();
			}
		};
		canvasHandler = new Handler();

	}

	/**
	 * Draws a background rectangle so the canvas can be a different color than the default.
	 * @param currentCanvas A reference to the current Canvas object.
	 */
	private void drawBackgroundRectangle(Canvas currentCanvas)
	{
		Paint canvasPaint = new Paint();
		canvasPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvasPaint.setStrokeWidth(5);
		canvasPaint.setAntiAlias(true);
		canvasPaint.setColor(backgroundColor);
		currentCanvas.drawRect(0,0, this.getWidth(), this.getHeight(), canvasPaint);

	}
	
	/**
	 * This is what code you call when you want to redraw the screen. Note that
	 * this is not the collision detection method but instead what is drawn
	 * every time the screen refreshes.
	 * 
	 * @param currentCanvas
	 *            Refers to the canvas passed to the method by the Android OS.
	 */
	protected void onDraw(Canvas currentCanvas)
	{
		yesAndroid = (BitmapDrawable) currentContext.getResources().getDrawable(R.drawable.android_yes);
		noAndroid = (BitmapDrawable) currentContext.getResources().getDrawable(R.drawable.android_no);
		
		drawBackgroundRectangle(currentCanvas);
		
		bounceYesImage();
		bounceNoImage();

		currentCanvas.drawBitmap(yesAndroid.getBitmap(), yesX, yesY, null);
		currentCanvas.drawBitmap(noAndroid.getBitmap(), noX, noY, null);
		canvasHandler.postDelayed(canvasRunnable, FRAME_RATE);

	}
	
	/**
	 * Creates a new random background color for the canvas.
	 */
	private void changeBackgroundColor()
	{
		int red, green, blue;
		red = (int) (Math.random() * 256);
		green = (int) (Math.random() * 256);
		blue = (int) (Math.random() * 256);
		backgroundColor = Color.argb(255, red, green, blue);
	}

	/**
	 * Adjusts the movement of the android_yes image based on its position.  If the image bounces of the top or bottom 
	 * walls the background color of the canvas is changed.
	 */
	private void bounceYesImage()
	{
		if (yesX < 0 && yesY < 0)
		{
			yesX = this.getWidth() / 2;
			yesY = this.getHeight() / 2;
		}
		else
		{
			yesX += yesXVelocity;
			yesY += yesYVelocity;

			if ((yesX > this.getWidth() - yesAndroid.getBitmap().getWidth()) || (yesX < 0))
			{
				yesXVelocity *= -1;
			}

			if ((yesY > this.getHeight() - yesAndroid.getBitmap().getHeight()) || (yesY < 0))
			{
				yesYVelocity *= -1;
				changeBackgroundColor();
			}
		}
	}

	/**
	 * Adjusts the movement of the android_no image based on its position.  If the image bounces of the left or right 
	 * walls the background color of the canvas is changed.
	 */
	private void bounceNoImage()
	{
		if (noX < 0 && noY < 0)
		{
			noX = this.getWidth() / 2;
			noY = this.getHeight() / 2;
		}
		else
		{
			noX += noXVelocity;
			noY += noYVelocity;

			if ((noX > this.getWidth() - noAndroid.getBitmap().getWidth()) || (noX < 0))
			{
				noXVelocity *= -1;
				changeBackgroundColor();
			}

			if ((noY > this.getHeight() - noAndroid.getBitmap().getHeight()) || (noY < 0))
			{
				noYVelocity *= -1;
			}
		}

	}

}
