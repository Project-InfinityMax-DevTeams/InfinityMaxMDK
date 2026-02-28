package com.example.examplemod.customlogic;

import com.example.examplemod.customlogic.util.ModDeveloperMathUtil;
import com.example.examplemod.customlogic.util.ModDeveloperTextUtil;

/**
 * MOD開発者が純Javaでカスタムゲームロジックを書くための集約クラスです。
 * <p>
 * Kotlin DSL からこのクラスのメソッド参照を行い、
 * ゲーム要素とロジックを接続します。
 */
public final class ModCommonLogic {

    private ModCommonLogic() {
    }

    /**
     * ブロック操作時の処理例です。
     * @param context ローダー側で渡される任意コンテキスト
     */
    public static void onBlockInteract(Object context) {
        // ここを任意の数値に変更: 消費エネルギー
        int cost = 20;
        int result = ModDeveloperMathUtil.clamp(cost * 2, 0, 9999);
        System.out.println(ModDeveloperTextUtil.prefix("BlockInteract") + " result=" + result + ", ctx=" + context);
    }

    /**
     * アイテム使用時の処理例です。
     * @param context ローダー側で渡される任意コンテキスト
     */
    public static void onItemUse(Object context) {
        // ここを任意文字列に変更: ログ表示名
        String display = "ExampleItem";
        System.out.println(ModDeveloperTextUtil.prefix(display) + " used, ctx=" + context);
    }

    /**
     * エンティティtick処理例です。
     * @param context ローダー側で渡される任意コンテキスト
     */
    public static void onEntityTick(Object context) {
        System.out.println(ModDeveloperTextUtil.prefix("EntityTick") + " ctx=" + context);
    }

    /**
     * クライアントキー入力時処理例です。
     * @param context ローダー側で渡される任意コンテキスト
     */
    public static void onClientKeyPress(Object context) {
        System.out.println(ModDeveloperTextUtil.prefix("ClientKey") + " ctx=" + context);
    }

    /**
     * GUI描画時処理例です。
     * @param context ローダー側で渡される任意コンテキスト
     */
    public static void onGuiRender(Object context) {
        System.out.println(ModDeveloperTextUtil.prefix("GuiRender") + " ctx=" + context);
    }

    /**
     * パケット受信時処理例です。
     * @param context ローダー側で渡される任意コンテキスト
     * @param packet 受信パケット
     */
    public static void onPacketReceived(Object context, Object packet) {
        System.out.println(ModDeveloperTextUtil.prefix("Packet") + " packet=" + packet + ", ctx=" + context);
    }
}
