package com.example.examplemod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Forge 用エントリーポイント。
 */
@Mod("examplemod") // modidと一致させる
public class ForgeInitializer {

    public ForgeInitializer() {
        // CommonSetupイベントにリスナー登録
        FMLJavaModLoadingContext.get()
                .getModEventBus()
                .addListener(this::setup);
    }

    /**
     * Forge の共通セットアップ時に呼ばれる。
     */
    private void setup(final FMLCommonSetupEvent event) {
        CommonBootstrap.init();
    }
}