# InfinityMaxAPI Registration / Logic DSL ガイド

このドキュメントは以下を説明します。

1. 書き方（基本構文）
2. DSLで登録・接続できるもの一覧
3. 各スコープで記述できる内容

---

## 1) コンセプト

InfinityMaxAPI は DSL を次の3層に分離します。

- **登録DSL (`registry {}`)**
  - 静的データ定義層（設定値の宣言のみ）
- **接続DSL (`behavior {}`)**
  - 登録済み要素 ID と LogicID の接続層
- **実行DSL (`logic {}`)**
  - Event と LogicID のトリガー定義層

> 重要: event 定義は `logic {}` でのみ行います。

---

## 2) `registry {}` の書き方（静的定義）

```kotlin
import com.yuyuto.infinitymaxapi.api.libs.packet.PacketDirection
import com.yuyuto.infinitymaxapi.api.libs.registry

registry {
    item("example_item", Any()) {
        stack = 64
        durability = 250
        tab = "materials"
    }

    block("example_block", Any()) {
        strength = 3.5f
        noOcclusion = false
    }

    blockEntity("example_block_entity", Any(), Any()) {
        profile = "default"
    }

    entity<Any, Any>("example_entity", Any()) {
        category = Any()
        width = 0.8f
        height = 1.95f
    }

    dataGen("example_datagen", Any()) {
        namespace = "examplemod"
        overwrite = true
    }

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
```

---

## 3) `behavior {}` の書き方（ID接続）

```kotlin
import com.yuyuto.infinitymaxapi.api.libs.Phase
import com.yuyuto.infinitymaxapi.api.libs.behavior
import com.yuyuto.infinitymaxapi.api.libs.behavior.BehaviorConnector
import com.yuyuto.infinitymaxapi.api.libs.behavior.PacketBehaviorConnector

behavior {
    item("example_item") {
        resourceId = "textures/item/example_item"
        phase = Phase.USE
        logicId = "examplemod:item_use"
        meta("cooldown", 40)
        connector = BehaviorConnector { ctx ->
            // Java/Kotlin ロジック
        }
    }

    packet<Any>("example_packet") {
        resourceId = "network/example_packet"
        phase = Phase.RECEIVE
        logicId = "examplemod:packet_receive"
        connector = PacketBehaviorConnector<Any> { ctx, payload ->
            // packet logic
        }
    }
}
```

---

## 4) `logic {}` の書き方（Eventトリガー）

```kotlin
import com.yuyuto.infinitymaxapi.api.libs.Phase
import com.yuyuto.infinitymaxapi.api.event.ModEvent
import com.yuyuto.infinitymaxapi.api.libs.logic

logic {
    event<ModEvent> {
        trigger("examplemod:item_use")
        phase = Phase.USE
        priority = 0
        async = false
        meta("source", "item_use")
    }
}
```

---

## 5) DSLで登録・接続できるもの一覧

### registry DSL

- `item`
- `block`
- `blockEntity`
- `entity`
- `dataGen`
- `packet`
- `network`
- `gui`
- `world`（`kind`: `dimension` / `biome` / `structure`）

### behavior DSL

- `block`
- `item`
- `entity`
- `keybind`
- `ui`
- `packet`

### logic DSL

- `event<T : ModEvent>`
  - `trigger(logicId)`（必須）
  - `phase`
  - `priority`
  - `async`
  - `meta(key, value)`
