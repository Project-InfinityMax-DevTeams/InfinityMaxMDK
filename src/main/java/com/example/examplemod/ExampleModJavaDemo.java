package com.example.examplemod;

import com.example.examplemod.customlogic.ModCommonLogic;
import com.example.examplemod.system.ModSystem;
import com.example.examplemod.system.ModSystemContext;
import com.example.examplemod.system.SystemPipeline;

/**
 * Java側デモクラスです。
 * <p>
 * このクラスは「カスタムゲームロジック全般」をJavaで記述する方針に沿って、
 * 純Javaシステムと補助ロジックの利用例を示します。
 */
public final class ExampleModJavaDemo {
    private ExampleModJavaDemo() {
    }

    /**
     * 純Javaシステムの起動例です。
     */
    public static void runJavaSystems() {
        SystemPipeline pipeline = new SystemPipeline();
        pipeline.addSystem(new ExampleEnergySystem());
        pipeline.initialize();
        pipeline.tick();

        // Kotlin DSLから呼ぶメソッドと同じロジックをJava単体でも実行できます。
        ModCommonLogic.onItemUse("local-java-demo-context");
    }

    /**
     * 純Javaで記述されたエネルギーシステム例です。
     */
    public static final class ExampleEnergySystem implements ModSystem {
        /** {@inheritDoc} */
        @Override
        public void initialize(ModSystemContext context) {
            // ここを任意の数値に置換可能: 初期エネルギー
            context.put("energy", 100);
        }

        /** {@inheritDoc} */
        @Override
        public void tick(ModSystemContext context) {
            Object raw = context.get("energy");
            int energy = raw instanceof Integer ? (Integer) raw : 0;
            // ここを任意の数値に置換可能: 1tickあたりの減衰値
            context.put("energy", energy - 1);
        }
    }
}
