package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.mapper.StudentMapper;
import com.github.wjxiu.service.StudentService;
import org.springframework.stereotype.Service;

/**
* @author xiu
* @description 针对表【student(学生表)】的数据库操作Service实现
* @createDate 2024-01-06 21:21:24
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentDO>
    implements StudentService {

}




