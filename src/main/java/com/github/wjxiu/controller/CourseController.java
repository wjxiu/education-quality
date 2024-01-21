package com.github.wjxiu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.CourseDO;
import com.github.wjxiu.DO.MajorDO;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-10 13:53
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    final CourseService courseService;
    @GetMapping("/list")
    public R<PageInfo<CourseDO>> list( CourseDO course, Integer pageNum, Integer pageSize) {
       List<CourseDO> list= courseService.listPage(course,pageNum,pageSize);
        return R.success(new PageInfo<>(list));
    }
    @GetMapping(value = {"/allName/{departmentName}","/allName"})
    public R getCourseNameByDepartmentName(@PathVariable(value = "departmentName",required = false) String departmentName){
        List<String> list = courseService.list(new LambdaQueryWrapper<CourseDO>().select(CourseDO::getCourseName).eq(departmentName!=null&& !departmentName.isEmpty(),CourseDO::getDepartmentName,departmentName)).stream().map(CourseDO::getCourseName).toList();
        return R.success(list);
    }
    @GetMapping(value = {"/allName/major/{MajorName}"})
    public R getCourseNameByMajorName(@PathVariable(value = "MajorName",required = false) String MajorName){
        List<String> list = courseService.list(new LambdaQueryWrapper<CourseDO>().select(CourseDO::getCourseName).eq(MajorName!=null&& !MajorName.isEmpty(),CourseDO::getMajorName,MajorName)).stream().map(CourseDO::getCourseName).toList();
        return R.success(list);
    }
    @GetMapping("/{id}")
    public R getinfo(@PathVariable("id") Integer id){
        CourseDO CourseDO = courseService.getById(id);
        return R.success(CourseDO);
    }
    @PostMapping
    public R save(@Validated @RequestBody CourseDO CourseDO){
        boolean save = courseService.save(CourseDO);
        if (save)return R.success(CourseDO);
        throw new ClientException("新增课程失败");
    }
    @PutMapping
    public R update(@Validated @RequestBody CourseDO CourseDO){
        boolean save = courseService.updateById(CourseDO);
        if (save)return R.success();
        throw new ClientException("修改课程失败");
    }
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Integer> ids){
        boolean b = courseService.removeBatchByIds(ids);
        if (b)return R.success();
        throw new ClientException("删除课程失败");
    }
}
