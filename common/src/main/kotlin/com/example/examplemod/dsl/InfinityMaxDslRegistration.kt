package com.example.examplemod.dsl

import com.example.examplemod.customlogic.ModCommonLogic
import com.yuyuto.infinitymaxapi.api.libs.Phase
import com.yuyuto.infinitymaxapi.api.libs.behavior
import com.yuyuto.infinitymaxapi.api.libs.logic
import com.yuyuto.infinitymaxapi.api.event.ModEvent
import com.yuyuto.infinitymaxapi.api.libs.registry
import com.yuyuto.infinitymaxapi.api.libs.behavior.BehaviorConnector
import com.yuyuto.infinitymaxapi.api.libs.behavior.PacketBehaviorConnector
import com.yuyuto.infinitymaxapi.api.libs.packet.PacketDirection

/**
 * InfinityMaxAPI の登録DSL/振る舞いDSLを利用してゲーム要素を登録するクラスです。
 *
 * 重要:
 * - 文字列IDや数値を置き換えるだけで、独自MOD向け定義へ変更できます。
 * - このクラスは「登録とJavaロジックへの紐づけ」専用です。
 */
object InfinityMaxDslRegistration {

    /**
     * InfinityMaxAPI が提供する registry DSL で要素登録を実行します。
     */
    fun registerWithInfinityMaxDsl() {
        registry {
            // ここを任意の文字列に変更: アイテムID
            item("example_item", Any()) {
                // ここを任意の数値に変更: スタック数
                stack = 64
                durability = 0
                tab = "materials"
            }

            // ここを任意の文字列に変更: ブロックID
            block("example_block", Any()) {
                // ここを任意の数値に変更: 強度
                strength = 3.5f
                noOcclusion = false
            }

            blockEntity("example_block_entity", Any(), Any()) {
                profile = "default"
            }

            // ここを任意の文字列に変更: エンティティID
            entity<Any, Any>("example_entity", Any()) {
                // ここを列挙型の任意値に変更: カテゴリ
                category = null
                width = 0.8f
                height = 1.95f
            }

            dataGen("example_datagen", Any()) {
                namespace = "examplemod"
                overwrite = true
            }

            // ここを任意の文字列に変更: パケットID
            packet("example_packet", Any()) {
                direction = PacketDirection.C2S
                channel = "main"
            }

            network("example_network", Any()) {
                protocol = "1"
                clientSync = true
            }

            gui("example_screen", Any()) {
                screenId = "ui/example_screen"
                layer = 10
            }

            world("example_dimension", Any()) {
                kind = "dimension"
                order = 100
            }
        }
    }

    /**
     * InfinityMaxAPI の behavior DSL で、登録済み要素へ Java カスタムロジックを紐づけます。
     */
    fun bindBehaviors() {
        behavior {
            block("example_block") {
                resourceId = "models/block/example_block"
                phase = Phase.INTERACT
                logicId = "examplemod:block_interact"
                // ここを任意の数値に変更: 消費値
                meta("power_cost", 20)
                connector = BehaviorConnector { ctx -> 
                    ModCommonLogic.onBlockInteract(ctx)
                }
            }

            item("example_item") {
                resourceId = "textures/item/example_item"
                phase = Phase.USE
                logicId = "examplemod:item_use"
                // ここを任意の数値に変更: クールダウン
                meta("cooldown", 40)
                connector = BehaviorConnector { ctx -> 
                    ModCommonLogic.onItemUse(ctx)
                }
            }

            entity("example_entity") {
                resourceId = "entities/example_entity"
                phase = Phase.TICK
                logicId = "examplemod:entity_tick"
                connector = BehaviorConnector { ctx -> 
                    ModCommonLogic.onEntityTick(ctx)
                }
            }

            keybind("open_example_gui") {
                resourceId = "keybind/open_example_gui"
                phase = Phase.PRESS
                logicId = "examplemod:keybind_press"
                connector = BehaviorConnector { ctx -> 
                    ModCommonLogic.onClientKeyPress(ctx)
                }
            }

            ui("example_screen") {
                resourceId = "ui/example_screen"
                phase = Phase.RENDER
                logicId = "examplemod:ui_render"
                connector = BehaviorConnector { ctx ->
                    ModCommonLogic.onGuiRender(ctx)
                }
            }

            packet<Any>("example_packet") {
                resourceId = "network/example_packet"
                phase = Phase.RECEIVE
                logicId = "examplemod:packet_receive"
                connector = PacketBehaviorConnector<Any> { ctx, packet ->
                    ModCommonLogic.onPacketReceived(ctx, packet)
                }
            }
        }
    }

    /**
     * InfinityMaxAPI の logic DSL でイベントと LogicID を接続します。
     */
    fun bindLogic() {
        logic {
            event<ModEvent> {
                trigger("examplemod:item_use")
                phase = Phase.USE
                priority = 0
                async = false
                meta("source", "item_use")
            }

            event<ModEvent> {
                trigger("examplemod:packet_receive")
                phase = Phase.RECEIVE
                priority = 0
                async = false
                meta("source", "packet_receive")
            }
        }
    }
}
