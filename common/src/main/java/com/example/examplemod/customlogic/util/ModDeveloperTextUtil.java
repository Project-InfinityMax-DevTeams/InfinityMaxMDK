package com.example.examplemod.customlogic.util;

/**
 * MOD開発者向けの純Java文字列ユーティリティです。
 */
public final class ModDeveloperTextUtil {
    private ModDeveloperTextUtil() {
    }

    /**
     * ログ用プレフィックスを作ります。
     * @param tag 任意文字列に置換可能
     * @return 整形済みプレフィックス
     */
    public static String prefix(String tag) {
        return "[ExampleMod/" + tag + "]";
    }
}
