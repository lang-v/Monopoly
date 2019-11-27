package cqupt.match.game.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cqupt.match.game.R;
import cqupt.match.game.resource.Res;

public class StartActivity extends Activity {

    //把姓名和ip存储到本地xml文件
    private SharedPreferences preferences;

    //控件实例
    private Button create,join;
    private EditText eName,eIp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
    }

    /*
    初始化
     */
    private void init(){
        preferences = getPreferences(Context.MODE_PRIVATE);
        //绑定控件
        eName = findViewById(R.id.edit_name);
        eIp = findViewById(R.id.edit_ip);
        create = findViewById(R.id.create_room);
        join = findViewById(R.id.join_room);
        //加载姓名，ip
        loadData();

        eName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    //焦点交给下一个编辑框
                    eIp.requestFocus();
                    return true;
                }
                return false;
            }
        });
        eIp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    //收起键盘
                    InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(eIp.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    return true;
                }
                return false;
            }
        });

        //创建房间
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                if("".equals(name)){
                    Toast.makeText(StartActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveData();
                RoomActivity.createRoom(StartActivity.this,name);
            }
        });
        //加入房间
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                String ip = eIp.getText().toString();
                if ("".equals(name)||"".equals(ip)){
                    Toast.makeText(StartActivity.this, "姓名和IP不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveData();
                RoomActivity.joinRoom(StartActivity.this,name,ip);
            }
        });
    }


    private void loadData(){
        String name = preferences.getString(Res.NAME,"");
        String ip = preferences.getString(Res.IP,"");
        if (!"".equals(name) && eName!=null){
            eName.setText(name);
        }
        if (!"".equals(ip) && eIp!=null){
            eIp.setText(ip);
        }
    }

    private void saveData(){
        String name = eName.getText().toString();
        String ip = eIp.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Res.NAME,name);
        editor.putString(Res.IP,ip);
        editor.apply();
    }
}
