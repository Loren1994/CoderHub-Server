package pers.loren.coderhub.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pers.loren.coderhub.constant.Constants;

//定时任务
@Component
@Transactional
public class TimerTask {

    //每隔INTERVAL_TIME毫秒执行一次
    @Scheduled(fixedDelay = Constants.INTERVAL_TIME)
    public void intervalTask() {
        System.out.println("定时任务intervalTask执行");
    }

    //在执行分钟数执行一次
    @Scheduled(cron = "0 3,29,33 * * * ?")
    public void cronTask() {
        System.out.println("定时任务cronTask执行");
    }

}
