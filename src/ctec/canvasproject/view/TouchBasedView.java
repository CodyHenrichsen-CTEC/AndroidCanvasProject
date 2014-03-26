package ctec.canvasproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class TouchBasedView extends SurfaceView implements SurfaceHolder.Callback
{

	public TouchBasedView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent userEvent)
	{
		boolean isTouched = false;
		
		float touchX = userEvent.getX();
		float touchY = userEvent.getY();
		
		return isTouched;
	}
	
	@Override
	protected void onDraw(Canvas currentCanvas)
	{
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		
	}

}
