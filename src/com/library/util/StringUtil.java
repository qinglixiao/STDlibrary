package com.library.util;

/**
 * Created by gfy on 2016/4/10.
 */
public class StringUtil {
    /**
     * 字符是否是汉字
     *
     * @param a char
     * @return boolean
     */
    /**
     * 输入的字符是否是汉字
     *
     * @param a char
     * @return boolean
     */
    public static boolean isChinese(char a) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(a);
        return (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION);
    }
}
