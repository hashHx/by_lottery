package com.hash.by_lottery.dao;

import com.hash.by_lottery.entities.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName BannerDao
 * @Descritption TODO
 * @Author Hash
 * @Date 2019/3/29 19:02
 * @Version 1.0
 **/


@Mapper
public interface BannerDao {

    List<Banner> getBanners();

}
