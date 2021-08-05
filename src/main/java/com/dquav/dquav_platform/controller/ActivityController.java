package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.BaseActivity;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.util.ResponseResult;
import com.dquav.dquav_platform.util.UserLevelLimitUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/7/1 - 10:04
 */
@RestController
@RequestMapping("activity")
public class ActivityController extends BaseController {

    @Resource
    IActivityService iActivityService;
    @Resource
    IUserListService iUserListService;


    @PostMapping("/")
    public ResponseResult<List<BaseActivity>> allActivity() {
        return new ResponseResult<>(SUCCESS, iActivityService.findAllActivity());
    }

    @PostMapping("activity_info")
    public ResponseResult<Activity> activity(@RequestParam("activity_name") String activityName) {
        return new ResponseResult<>(SUCCESS, iActivityService.getActivity(activityName));
    }

    @PostMapping("add_activity")
    public ResponseResult<Void> addActivity(@RequestBody Activity activity, HttpSession session) {
        System.out.println(activity);
        Integer uid = getUidFromSession(session);
        System.out.println(uid + "+++++++++++");
        iActivityService.addActivity(uid, activity);
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("change_activity")
    public ResponseResult<Void> changeActivity(@RequestBody Activity activity,
                                               String oldActivityName,
                                               HttpSession session) {
        Integer uid = getUidFromSession(session);
        UserList user = iUserListService.getByUid(uid);
        iActivityService.changeActivity(oldActivityName, user.getUsername(), activity.getActivityName(),
                activity.getActivityStartTime(), activity.getActivityEndTime(),
                activity.getActivityAdds());
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("delete")
    public ResponseResult<Void> remove(@RequestParam("activity_name") String activityName, HttpSession session) {
        String username = getUsernameFromSession(session);
        iActivityService.removeActivity(username, activityName);
        return new ResponseResult<>(SUCCESS);
    }

}
