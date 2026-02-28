package com.example.examplemod

import com.example.examplemod.dsl.ExtraElementDefinition
import com.example.examplemod.dsl.ModDslInitializer

/**
 * Kotlin側の統合エントリーポイントです。
 *
 * 責務:
 * - InfinityMaxAPI DSLによる登録
 * - DSL未対応要素の補完登録
 */
object ExampleModKotlinDemo {

    /**
     * Kotlin登録処理を実行します。
     */
    fun initializeFromKotlin() {
        ModDslInitializer.initialize(::registerUnsupportedElement)
    }

    /**
     * DSL未対応要素をローダー固有処理へ接続する例です。
     *
     * @param definition 未対応要素定義
     */
    private fun registerUnsupportedElement(definition: ExtraElementDefinition) {
        // ここを Fabric / Forge の実登録APIに置き換えてください。
        println("[EXTRA-REGISTER] type=${definition.type}, id=${definition.id}, impl=${definition.implementationClass}")
    }
}
