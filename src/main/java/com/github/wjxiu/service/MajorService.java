package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.MajorDO;

import java.util.List;

/**
* @author xiu
* @description 针对表【major】的数据库操作Service
* @createDate 2024-01-06 21:21:08
*/
public interface MajorService extends IService<MajorDO> {

    List< MajorDO> pageList(MajorDO majorDO, Integer pageNum, Integer pageSize);
}
