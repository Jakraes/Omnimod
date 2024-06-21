package jakraes.omnimod.utils;

import jakraes.omnimod.homes.Homes;

import java.io.FileNotFoundException;

public class SaverThread extends Thread {
	public static final int SAVE_INTERVAL = 6000 * 5; // 5 minutes

	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				Homes.save();
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}

			try {
				sleep(SAVE_INTERVAL);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
