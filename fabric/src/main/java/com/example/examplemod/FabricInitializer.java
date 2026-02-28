package com.example.examplemod;

import net.fabricmc.api.ModInitializer;

/**
 * Fabric 用エントリーポイント。
 */
public class FabricInitializer implements ModInitializer {

    @Override
    public void onInitialize() {
        // 共通初期化処理を呼び出す
        CommonBootstrap.init();
    }
}