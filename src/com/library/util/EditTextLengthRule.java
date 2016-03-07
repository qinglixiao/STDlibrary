
package com.library.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

/***
 * EditText 字数长度限制规则
 * */
public class EditTextLengthRule implements InputFilter {
    private int maxLength;

    private int cur_length;

    public EditTextLengthRule(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
            int dend) {
        // TODO Auto-generated method stub
        cur_length = 0;
        if (dest != null) {
            cur_length = CharNumCalc(dest.toString());
            // 如果文本已经超过指定长度则返回空内容
            if (cur_length >= maxLength) {
                return "";
            }
        }
        if (!TextUtils.isEmpty(source)) {
            int length = CharNumCalc(source.toString());
            // 如果新录入的文本+之前文本未超过限制则允许录入
            if (cur_length + length < maxLength) {
                return null;
            } else {
                StringBuilder temp = new StringBuilder();
                length = source.length();
                // 逐个判断新录入的字符
                for (int i = 0; i < length; i++) {
                    if (isChinese(source.charAt(i))) {
                        // 未超出限制则允许录入
                        if (cur_length + 2 <= maxLength) {
                            temp.append(source.charAt(i));
                            cur_length += 2;
                        } else
                            break;
                    } else {
                        // 未超出限制则允许录入
                        if (cur_length + 1 <= maxLength) {
                            temp.append(source.charAt(i));
                            cur_length++;
                        } else
                            break;
                    }

                }
                return temp;
            }
        }
        return null;
    }

    // 计算字符串中字符数
    private int CharNumCalc(String text) {
        int temp = 0;
        int length = text.length();
        for (int i = 0; i < length; i++) {
            // 如果为汉字或中文字符则将长度加2
            if (isChinese(text.charAt(i)))
                temp += 2;
            else
                temp += 1;
        }
        return temp;
    }

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
