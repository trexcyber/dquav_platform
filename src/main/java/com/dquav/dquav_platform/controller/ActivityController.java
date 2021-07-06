package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.BaseActivity;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.util.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/")
    public ResponseResult<List<BaseActivity>> allActivity() {
        return new ResponseResult<>(SUCCESS, iActivityService.findAllActivity());
    }

    @PostMapping("activity_info")
    public ResponseResult<Activity> activity(String activityName) {
        return new ResponseResult<>(SUCCESS, iActivityService.getActivity(activityName));
    }

    @PostMapping("add_activity")
    public ResponseResult<Void> addActivity(@RequestParam("activity_name") String activityName, @RequestParam(
            "activity_start_time") Date activityStartTime, @RequestParam("activity_end_time") Date activityEndTime,
                                            @RequestParam("activity_adds") String activityAdds, HttpSession session) {
        String username = getUsernameFromSession(session);
        Activity activity = new Activity();
        activity.setActivityName(activityName);
        activity.setActivityStartTime(activityStartTime);
        activity.setActivityEndTime(activityEndTime);
        activity.setActivityAdds(activityAdds);
        iActivityService.addActivity(username, activity);
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("change_activity")
    public ResponseResult<Void> changeActivity(@RequestParam("old_activity_name") String oldActivityName,
                                               String username,
                                               @RequestParam("activity_name") String activityName, @RequestParam(
            "activity_start_time") Date activityStartTime, @RequestParam(
            "activity_end_time") Date activityEndTime,
                                               @RequestParam("activity_adds") String activityAdds) {
        iActivityService.changeActivity(oldActivityName, username, activityName, activityStartTime, activityEndTime,
                activityAdds);
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("delete")
    public ResponseResult<Void>remove(String activityName,HttpSession session){
        String username=getUsernameFromSession(session);
        iActivityService.removeActivity(username,activityName);
        return new ResponseResult<>(SUCCESS);
    }

}
