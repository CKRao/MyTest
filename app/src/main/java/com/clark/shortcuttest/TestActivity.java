package com.clark.shortcuttest;

import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clark on 2017/4/6.
 */

public class TestActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        delete();
    }

    private void delete() {
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        /********* 移除弹出列表图标 **********/
        // 所有动态创建图标
        List<ShortcutInfo> infos = shortcutManager.getDynamicShortcuts();

        List<String> ids1 = new ArrayList<>();
        for (ShortcutInfo info: infos){
            ids1.add(info.getId());
        }
        // 禁用所有的快捷方式
        shortcutManager.disableShortcuts(ids1,"已禁用");
        shortcutManager.removeDynamicShortcuts(ids1);

        /********* 移除拖出来的桌面快捷图标 **********/
        // 放在桌面的图标
        List<ShortcutInfo> infos2 = shortcutManager.getPinnedShortcuts();

        List<String> ids2 = new ArrayList<>();
        for (ShortcutInfo info : infos2 ) {
            ids2.add(info.getId());
        }

        shortcutManager.disableShortcuts(ids2, "已禁用");
        shortcutManager.removeAllDynamicShortcuts();
    }


}
