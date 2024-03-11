package com.github.wjxiu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.DO.StudentReviewsDO;
import com.github.wjxiu.DTO.Resp.StudentReviewsResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.StudentReviewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiu
 * @create 2024-02-29 17:39
 */
@RestController
@RequestMapping("studentReviews")
@RequiredArgsConstructor
@Slf4j
public class StudentReviewsController {
  final StudentReviewsService studentReviewsService;
  @GetMapping("/viewOfTeacher")
  public R<PageInfo<StudentReviewsResp>> viewOfTeacher(Integer pageNum, Integer pageSize){
    List<? extends StudentReviewsResp> studentReviewsResps = studentReviewsService.viewOfTeacher(pageNum, pageSize);
    log.info(""+studentReviewsResps);
    return  R.success(new PageInfo<>(studentReviewsResps));
  }
  @GetMapping("/list")
  public R<PageInfo<StudentReviewsResp>> list(StudentReviewsDO StudentReviewsDO,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
    return R.success(new PageInfo<>(studentReviewsService.pageList(StudentReviewsDO,pageNum,pageSize)));
  }

//  @GetMapping(value = {"/allName/{departmentName}","/allName"})
//  public R getMajorNameByDepartmentName(@PathVariable(value = "departmentName",required = false) String departmentName){
//    List<String> list = studentReviewsService.list(new LambdaQueryWrapper<StudentReviewsDO>().select(StudentReviewsDO::getMajorName).eq(departmentName!=null&& !departmentName.isEmpty(),StudentReviewsDO::getDepartmentName,departmentName)).stream().map(StudentReviewsDO::getMajorName).toList();
//    return R.success(list);
//  }
  @GetMapping("/{id}")
  public R getinfo(@PathVariable("id") Integer id){
    StudentReviewsDO StudentReviewsDO = studentReviewsService.getById(id);
    return R.success(StudentReviewsDO);
  }
  @PostMapping
  public R save(@Validated @RequestBody StudentReviewsDO StudentReviewsDO){
    boolean save = studentReviewsService.save(StudentReviewsDO);
    if (save)return R.success(StudentReviewsDO);
    throw new ClientException("新增学生评价失败");
  }
  //todo 修改关联的地方
  @PutMapping
  public R update(@Validated @RequestBody StudentReviewsDO StudentReviewsDO){
    boolean save = studentReviewsService.updateById(StudentReviewsDO);
    if (save)return R.success();
    throw new ClientException("修改学生评价失败");
  }
  @DeleteMapping("/{ids}")
  public R delete(@PathVariable("ids") List<Integer> ids){
    boolean b = studentReviewsService.removeBatchByIds(ids);
    if (b)return R.success();
    throw new ClientException("删除学生评价失败");
  }
  @GetMapping("/commentOfTeacher")
  public R commentOfTeacher(@RequestParam(value = "stuClassId",required = false)Integer stuClassId,
                            @RequestParam(value = "courseId",required = false)Integer courseId,
                            @RequestParam(value = "startYear",required = false)Integer startYear,
                            @RequestParam(value = "term",required = false)String term){
   List<StudentReviewsResp> list= studentReviewsService.commentOfTeacher(stuClassId,courseId,startYear,term);
   return R.success(new PageInfo<StudentReviewsResp>(list));
  }
}
