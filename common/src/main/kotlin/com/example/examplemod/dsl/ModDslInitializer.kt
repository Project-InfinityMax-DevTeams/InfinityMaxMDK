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
     * Kotlin 側のDSL初期化を行い、未対応要素を登録先へ渡す。
     *
     * DSL の登録と関連するバインディング処理を実行し、処理できない要素を引数の関数へ渡して登録させます。
     *
     * @param unsupportedRegistrar DSLで処理できない要素を受け取り登録するための関数
     */
    fun initialize(unsupportedRegistrar: (ExtraElementDefinition) -> Unit) {
        InfinityMaxDslRegistration.registerWithInfinityMaxDsl()
        InfinityMaxDslRegistration.bindBehaviors()
        InfinityMaxDslRegistration.bindLogic()
        ExtraGameElementRegistration.registerUnsupportedElements(unsupportedRegistrar)
    }
}
