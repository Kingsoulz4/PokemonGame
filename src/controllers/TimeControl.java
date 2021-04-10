package controllers;

import javax.swing.JOptionPane;

import userinterface.MainFrame;

public class TimeControl extends Thread{
	MainFrame mFrame ;
	Boolean pause, resume;
	public TimeControl(MainFrame mFrame)
	{
		this.mFrame = mFrame;
	}
	public boolean isPause()
	{
		return pause;
	}
	public boolean isResume()
	{
		return resume;
	}
	public void run()
	{
		pause = false;
		resume = true;
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (isPause()) {
				if (isResume()) {
					mFrame.time--;
				}
			}
			else {
				mFrame.time--;
			}
			if (mFrame.time==0) {
				int select =JOptionPane.showConfirmDialog(null, "Play again", "You lose", JOptionPane.YES_NO_OPTION);
				if (select == JOptionPane.YES_OPTION) {
					mFrame.newGame();
				}
			}
		}
	}
	public Boolean getPause() {
		return pause;
	}
	public void setPause(Boolean pause) {
		this.pause = pause;
	}
	public Boolean getResume() {
		return resume;
	}
	public void setResume(Boolean resume) {
		this.resume = resume;
	}
}
