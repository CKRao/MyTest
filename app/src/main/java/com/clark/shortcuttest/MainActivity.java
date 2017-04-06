package com.clark.shortcuttest;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register();
        Button mButton = (Button) findViewById(R.id.bt);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);

            }
        });
    }

    //动态注册
    private void register() {
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        List<ShortcutInfo> infos = new ArrayList<>();
        //按下返回按钮跳转的Activity
        Intent intent1 = new Intent(this, MainActivity.class);
        intent1.setAction(Intent.ACTION_VIEW);
        //目标Activity
        Intent intent2 = new Intent(this, TestActivity.class);
        intent2.setAction("com.clark.BACK");
        Intent[] intents = new Intent[2];
        intents[0] = intent1;
        intents[1] = intent2;

        ShortcutInfo info = new ShortcutInfo.Builder(this, "publish-2")
                .setLongLabel("动态创建")
                .setShortLabel("动态创建")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher_round))
                .setIntents(intents)
                .build();
        infos.add(info);

        shortcutManager.setDynamicShortcuts(infos);
    }
}
