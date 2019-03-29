package com.hash.by_lottery.dao;

import com.hash.by_lottery.entities.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName NoticeDao
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/29 19:03
 * @Version 1.0
 **/


@Mapper
public interface NoticeDao {

    List<Notice> getNotices();

}
