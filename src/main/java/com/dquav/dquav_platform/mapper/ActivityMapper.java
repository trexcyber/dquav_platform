package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.BaseActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/3/23 - 11:32
 * <p>
 * 处理活动项目持久层接口
 */
public interface ActivityMapper {

    /**
     * 根据活动项目名称查询活动项目数据
     *
     * @param activityName 活动项目名称
     * @return 返回项目数据 | 否则 null
     */
    Activity getByActivityName(String activityName);

    /**
     * 根据活动项目id查询活动项目数据
     *
     * @param activityId 活动项目id
     * @return 返回项目数据 | 否则 null
     */
    Activity getByActivityId(Integer activityId);

    /**
     * 添加活动项目
     *
     * @param activity 活动项目数据
     * @return 受影响行数
     */
    Integer addActivity(Activity activity);

    /**
     * 删除活动项目
     *
     * @param activityId 活动项目id
     * @return 受影响行数
     */
    Integer deleteByActivityId(Integer activityId);

    /**
     * 修改活动项目数据
     *
     * @param activityId        项目id
     * @param activityName      活动项目名
     * @param activityStartTime 活动项目开始时间
     * @param activityEndTime   活动项目结束时间
     * @param activityAdds      活动项目地址
     * @param updateBy          项目修改用户
     * @param updateTime        项目修改时间
     * @return 受影响行数
     */
    Integer updateActivity(@Param("activityId") Integer activityId,
                           @Param("activityName") String activityName,
                           @Param("activityStartTime") Date activityStartTime,
                           @Param("activityEndTime") Date activityEndTime,
                           @Param("activityAdds") String activityAdds,
                           @Param("updateBy") String updateBy,
                           @Param("updateTime") Date updateTime);

    /**
     * 查询活动名称和活动开始时间
     *
     * @return 返回活动名称和开始时间数据
     */
    List<BaseActivity> getActivityNameAndTime();

}
