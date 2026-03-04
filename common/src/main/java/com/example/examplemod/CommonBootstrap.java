package com.example.examplemod;

import com.example.examplemod.dsl.InfinityMaxDslRegistration;
import com.example.examplemod.dsl.ExtraGameElementRegistration;

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
     * 共通の初期化処理を実行する。
     *
     * Kotlin DSL の登録と共通ロジックの初期化を行い、起動ログを出力する。
     * Fabric および Forge の両方から呼び出せることを想定している。
     */
    public static void init() {
        // Kotlin 側の登録処理を実行
        InfinityMaxDslRegistration.INSTANCE.registerWithInfinityMaxDsl();
        InfinityMaxDslRegistration.INSTANCE.bindBehaviors();
        InfinityMaxDslRegistration.INSTANCE.bindLogic();
        ExtraGameElementRegistration.INSTANCE.registerUnsupportedElements(definition -> {
            System.out.println("[InfinityMax] Unsupported element registered: " + definition.getType() + " - " + definition.getId());
            // TODO: 将来的に Fabric/Forge 固有の登録処理を実装
        });
        // 今後ここに共通ロジックを追加できる
        System.out.println("[InfinityMax] Common bootstrap initialized.");
    }
}

