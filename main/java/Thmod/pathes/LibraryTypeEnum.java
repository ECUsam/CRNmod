package Thmod.pathes;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.helpers.CardLibrary;
/**
 * 备注: 将角色定义的卡牌颜色CRN_COLOR注册到CardLibrary,必须有。
 */
public class LibraryTypeEnum {
    @SpireEnum
    public static CardLibrary.LibraryType CRN_COLOR;

    @SpireEnum
    public static CardLibrary.LibraryType CRN_DERIVATIONS;
}