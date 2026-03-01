package com.example.examplemod.dsl

/**
 * InfinityMaxAPI の現行DSLで直接扱えない要素を Kotlin 側で補完登録するクラスです。
 *
 * 対象:
 * - ブロックエンティティ
 * - クライアント
 * - GUI
 * - ディメンション
 * - バイオーム
 * - DataGen
 */
object ExtraGameElementRegistration {

    /**
     * 未対応要素の登録定義一覧。
     *
     * ここは文字列/数値だけ置換すればそのまま流用できます。
     */
    val definitions: List<ExtraElementDefinition> = listOf(
        ExtraElementDefinition("block_entity", "example_block_entity", "com.example.examplemod.content.blockentity.ExampleBlockEntity"),
        ExtraElementDefinition("client", "example_client", "com.example.examplemod.client.ExampleClientBootstrap"),
        ExtraElementDefinition("gui", "example_screen", "com.example.examplemod.client.gui.ExampleScreen"),
        ExtraElementDefinition("dimension", "example_dimension", "com.example.examplemod.world.dimension.ExampleDimension"),
        ExtraElementDefinition("biome", "example_biome", "com.example.examplemod.world.biome.ExampleBiome"),
        ExtraElementDefinition("datagen", "example_datagen", "com.example.examplemod.datagen.ExampleDataGenerator")
    )

    /**
     * ローダー側の実登録処理へ橋渡しするための呼び出し口です。
     *
     * @param registrar 各ローダー向け実装
     */
    fun registerUnsupportedElements(registrar: (ExtraElementDefinition) -> Unit) {
        definitions.forEach(registrar)
    }
}

/**
 * DSL未対応の要素定義です。
 *
 * @property type 要素種別。任意文字列へ置換可能
 * @property id 一意ID。任意文字列へ置換可能
 * @property implementationClass 実装クラス名。任意文字列へ置換可能
 */
data class ExtraElementDefinition(
    val type: String,
    val id: String,
    val implementationClass: String
)
