package com.wowotoffer.shelf.common.core.validator;


import com.wowotoffer.shelf.common.core.annotation.IsMobile;
import com.wowotoffer.shelf.common.core.entity.constant.RegexpConstant;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否为合法的手机号码
 *
 * @author of
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE;
                return ShelfUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
