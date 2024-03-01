package com.github.wjxiu.service.impl;

/**
 * @author xiu
 * @create 2024-02-29 18:13
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.DO.StudentReviewsDO;
import com.github.wjxiu.DTO.Resp.StudentReviewsResp;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.mapper.StuClassMapper;
import com.github.wjxiu.mapper.StudentCourseClassTeacherMapper;
import com.github.wjxiu.mapper.StudentReviewsMapper;
import com.github.wjxiu.service.StudentReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiu
 * @description 针对表【student_reviews(学生对老师的评价)】的数据库操作Service实现
 * @createDate 2024-02-29 17:35:49
 */
@Service@RequiredArgsConstructor
public  class StudentReviewsServiceImpl extends ServiceImpl<StudentReviewsMapper, StudentReviewsDO> implements StudentReviewsService {
    final StudentReviewsMapper mapper;
    final StudentCourseClassTeacherMapper studentCourseClassTeacherMapper;
    final StuClassMapper stuClassMapper;
    @Override
        public List<StudentReviewsResp> pageList(StudentReviewsDO studentReviewsDO, Integer pageNum, Integer pageSize) {
        return  mapper.pageList(studentReviewsDO,pageNum,pageSize);
    }

    @Override
    public List<StudentReviewsResp> commentOfTeacher(Integer stuClassId, Integer courseId) {
        List<StudentCourseClassTeacherDO> list = studentCourseClassTeacherMapper.selectList(new LambdaQueryWrapper<StudentCourseClassTeacherDO>()
                .eq(StudentCourseClassTeacherDO::getTeacherId, UserContext.getUserId())
                .eq(stuClassId!=null,StudentCourseClassTeacherDO::getStuClassId,stuClassId)
                .eq(courseId!=null,StudentCourseClassTeacherDO::getCourseId,courseId));
        if (list.isEmpty())return new ArrayList<>();
        Set<Integer> collect = list.stream().map(StudentCourseClassTeacherDO::getStuClassId).collect(Collectors.toSet());
        Map<Integer, StuClassDO> idtoclass = stuClassMapper.selectBatchIds(collect).stream().collect(Collectors.toMap(StuClassDO::getId, s -> s));
        ArrayList<StudentReviewsResp> res = new ArrayList<>();
        for (StudentCourseClassTeacherDO studentCourseClassTeacherDO : list) {
            Integer teacherstuClassId = studentCourseClassTeacherDO.getStuClassId();
            StuClassDO stuClassDO = idtoclass.get(teacherstuClassId);
            StudentReviewsResp studentReviewsResp = new StudentReviewsResp();
            studentReviewsResp.setRelation(studentCourseClassTeacherDO);
            studentReviewsResp.setStuClass(stuClassDO);
            res.add(studentReviewsResp);
        }
        return res;
    }

    @Override
    public List<? extends StudentReviewsResp> viewOfTeacher(Integer pageNum, Integer pageSize) {
//        获取登录教师的所有评价
        Integer userId = UserContext.getUserId();
        StudentReviewsDO studentReviewsDO = new StudentReviewsDO();studentReviewsDO.setTeacherId(userId);
       return  pageList(studentReviewsDO,pageNum,pageSize);
    }
}
