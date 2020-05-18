package com.dut.dao;

import com.dut.entity.EpicEntity;

public interface EpicDao {
    public void createEpic(EpicEntity epicEntity);

    public void updateEpic(EpicEntity epicEntity);

}