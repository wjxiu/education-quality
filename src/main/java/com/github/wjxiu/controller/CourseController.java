package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.CourseDO;
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
    @PostMapping("/list")
    public PageInfo<TeacherPageResp> list(@RequestBody TeacherPageReq teacher, Integer pageNum, Integer pageSize) {
        return null;
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
