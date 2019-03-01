package pers.loren.coderhub.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.loren.coderhub.util.Result;
import pers.loren.coderhub.util.ResultUtil;

@ControllerAdvice
public class LogExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof LogException) {
            LogException logException = (LogException) e;
            return ResultUtil.error(logException.getMessage());
        } else {
            Logger logger = LoggerFactory.getLogger(LogExceptionHandle.class);
            logger.info(">>>>>>>>>>异常开始<<<<<<<<<<<");
            logger.error("发生异常:" + e.getClass().getSimpleName() + ",\n" + e.getMessage());
            logger.info(">>>>>>>>>>异常结束<<<<<<<<<<<");
        }
        return ResultUtil.error((null == e.getMessage() || e.getMessage().equals("")) ? e.getClass().getSimpleName() : e.getMessage());
    }
}
