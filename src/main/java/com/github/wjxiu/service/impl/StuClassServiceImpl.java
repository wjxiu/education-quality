package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.mapper.StuClassMapper;
import com.github.wjxiu.service.StuClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xiu
* @description 针对表【stu_class】的数据库操作Service实现
* @createDate 2024-01-06 21:21:17
*/
@Service
@RequiredArgsConstructor
public class StuClassServiceImpl extends ServiceImpl<StuClassMapper, StuClassDO>
    implements StuClassService {

    @Override
    public List<StuClassDO> pageList(StuClassDO stuClassDO, Integer pageNum, Integer pageSize) {
        return null;
    }
}




