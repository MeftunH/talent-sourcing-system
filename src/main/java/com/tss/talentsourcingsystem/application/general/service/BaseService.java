package com.tss.talentsourcingsystem.application.general.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.tss.talentsourcingsystem.application.general.entity.BaseAdditionalFields;
import com.tss.talentsourcingsystem.application.general.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public abstract class BaseService<E extends BaseEntity> {
    public void setAdditionalFields(E entity) {
        BaseAdditionalFields baseAdditionalFields=entity.getBaseAdditionalFields();
        if (baseAdditionalFields==null) {
            baseAdditionalFields=new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }
        if (entity.getId()==null) {
            baseAdditionalFields.setCreatedDate(new Date());
        }
        baseAdditionalFields.setUpdatedDate(new Date());
    }
}
