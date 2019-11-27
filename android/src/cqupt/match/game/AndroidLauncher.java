package cqupt.match.game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import cqupt.match.game.resource.Res;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		Intent intent = getIntent();
		int number = intent.getIntExtra(Res.PLAYER_NUMBER,-1);
		if (number == -1){
			Toast.makeText(this, "未知错误，请重新进入游戏", Toast.LENGTH_SHORT).show();
			return;
		}
		initialize(new Monopoly(number), config);
	}
}
