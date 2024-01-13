package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.DTO.Req.StudentPageReq;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-10 13:50
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    final StudentService studentService;
    @GetMapping("/list")
    public R<PageInfo<StudentDO>> list(StudentPageReq studentDO, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<StudentDO> page=  studentService.pageList(studentDO,pageNum,pageSize);
       return R.success(page);
    }
    @GetMapping("/getallteacher")
    public R getAllTeacher(){
      return R.success(studentService.getAllTeacher());
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
        log.info("删除学生");
        boolean b = studentService.removeBatchByIds(ids);
        if (b)return R.success();
        throw new ClientException("删除学生失败");
    }
}
