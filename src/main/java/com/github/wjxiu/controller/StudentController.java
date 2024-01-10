package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-10 13:50
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    final StudentService studentService;
    @PostMapping("/list")
    public PageInfo<TeacherPageResp> list(@RequestBody TeacherPageReq teacher, Integer pageNum, Integer pageSize) {
       return null;
    }
    @GetMapping("/{id}")
    public R getinfo(@PathVariable("id") Integer id){
        StudentDO StudentDO = studentService.getById(id);
        StudentDO.setPassword("");
        return R.success(StudentDO);
    }
    @PostMapping
    public R save(@Validated @RequestBody StudentDO StudentDO){
        boolean save = studentService.save(StudentDO);
        if (save)return R.success(StudentDO);
        throw new ClientException("新增学生失败");
    }
    @PutMapping
    public R update(@Validated @RequestBody StudentDO StudentDO){
        boolean save = studentService.updateById(StudentDO);
        if (save)return R.success();
        throw new ClientException("修改学生失败");
    }
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Integer> ids){
        boolean b = studentService.removeBatchByIds(ids);
        if (b)return R.success();
        throw new ClientException("删除学生失败");
    }
}
