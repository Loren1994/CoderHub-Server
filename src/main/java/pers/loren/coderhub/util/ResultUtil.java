package pers.loren.coderhub.util;

public class ResultUtil {
    public static Result success(Object object) {
        return new Result(200, "操作成功", object);
    }

    public static Result error(String returnMsg) {
        return new Result(400, returnMsg);
    }
}
