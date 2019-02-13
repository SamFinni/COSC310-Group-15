package GUI;

import java.util.LinkedList;

public class Typer implements Runnable
{

	private LinkedList<String> mMsgList;
	private Thread thdMe;
	private boolean mLogging;
	
	public Typer(boolean isLogging)
	{
		mMsgList = new LinkedList<>();
		mLogging = isLogging; // initiate with true to print to log false is not implemented
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			if(mMsgList.isEmpty())
			{
				try
				{
					synchronized(this)
					{
						this.wait();
					}
				} catch (InterruptedException e)
				{}
			}else{
				if(mMsgList.size() > 40)
				{
					String dump = "";
					while(mMsgList.size() > 25)
					{
						dump += mMsgList.remove();
					}
					Log.addText(dump);
					continue;
				}else
				{
					try
					{
						Thread.sleep(20);
					} catch (InterruptedException e)
					{}
				}

				
				if(mLogging){
					Log.addText(mMsgList.remove());
				}else{
					// it could call other methods here to type on other text fields
				}

			}
		}
		
	}
	
	public void addMsg(String str)
	{
		char[] msg = str.toCharArray();
		for(int i = 0; i < msg.length - 1; i++)
		{
			mMsgList.addLast(Character.toString(msg[i]));
		}
		mMsgList.addLast(Character.toString(msg[msg.length - 1]) + "\n");
		
		synchronized(this)
		{
			this.notify();
		}
		
	}
	
	public void begin()
	{
		if (thdMe == null) {
			thdMe = new Thread(this);
			thdMe.start();			
		}else{
			Log.println("Logger is already running, but was asked to run again");
		}
	}

}
