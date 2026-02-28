package com.example.examplemod.customlogic.util;

/**
 * MOD開発者向けの純Java数学ユーティリティです。
 * <p>
 * Minecraftクラスに依存しないため、テストしやすく再利用しやすい構成です。
 */
public final class ModDeveloperMathUtil {
    private ModDeveloperMathUtil() {
    }

    /**
     * 値を範囲内へ制限します。
     * @param value 元値（任意数値に置換可能）
     * @param min 下限（任意数値に置換可能）
     * @param max 上限（任意数値に置換可能）
     * @return クランプ結果
     */
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * 進捗率（0.0～1.0）を計算します。
     * @param current 現在値（任意数値に置換可能）
     * @param total 合計値（任意数値に置換可能）
     * @return 進捗率
     */
    public static double ratio(double current, double total) {
        if (total == 0.0) {
            return 0.0;
        }
        return current / total;
    }
}
