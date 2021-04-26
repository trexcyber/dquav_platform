package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.Doc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TrEx
 * @date 2021/3/23 - 14:52
 *
 * 活动文件的持久层接口
 */
public interface DocMapper {

    /**
     * 添加活动文件
     * @param doc 活动文件数据
     * @return 受影响行数
     */
    Integer addDoc (Doc doc);

    /**
     * 根据文件名 查询文件信息
     * @param docName 文件名数据
     * @return 返回文件信息
     */
    Doc getDocByName(String docName);

    /**
     * 查询文件名
     * @param activityId 活动项目id
     * @return 返回所对应的文件数据
     */
    List<Doc> getDocNameByActivityId(Integer activityId);

    /**
     * 根据文件id 删除对应文件数据
     * @param docId 文件id
     * @return 受影响行数
     */
    Integer deleteDocById(Integer docId);

    /**
     * 根据活动项目id 删除所包含的所有文件数据
     * @param activityId 活动项目id
     * @return 受影响行数
     */
    Integer deleteDocByActivityId(Integer activityId);


}
