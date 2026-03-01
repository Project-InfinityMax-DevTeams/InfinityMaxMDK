package com.example.examplemod.dsl

/**
 * Kotlin 側の初期化エントリーポイントです。
 *
 * 責務:
 * - InfinityMaxAPI DSL を使った登録
 * - DSL未対応要素の補完登録
 * - Javaカスタムロジックへの紐づけ
 */
object ModDslInitializer {

    /**
     * 初期化実行。
     *
     * @param unsupportedRegistrar DSL未対応要素をローダー実装へ渡す関数
     */
    fun initialize(unsupportedRegistrar: (ExtraElementDefinition) -> Unit) {
        InfinityMaxDslRegistration.registerWithInfinityMaxDsl()
        InfinityMaxDslRegistration.bindBehaviors()
        ExtraGameElementRegistration.registerUnsupportedElements(unsupportedRegistrar)
    }
}
