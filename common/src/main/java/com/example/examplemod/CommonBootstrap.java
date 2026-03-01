package com.example.examplemod;

/**
 * ローダー共通の初期化処理をまとめるクラスです。
 *
 * 責務:
 * - Kotlin DSL登録の呼び出し
 * - 今後追加される共通初期化処理の集約
 */
public final class CommonBootstrap {

    // インスタンス化防止
    private CommonBootstrap() {}

    /**
     * 共通初期化処理。
     *
     * Fabric / Forge のどちらからもここを呼び出す。
     */
    public static void init() {
        // Kotlin 側の登録処理を実行
        InfinityMaxDslRegistrationKt.register();

        // 今後ここに共通ロジックを追加できる
        System.out.println("[InfinityMax] Common bootstrap initialized.");
    }
}
