package pers.loren.coderhub.constant;

public interface Constants {
    /**
     * iss(Issuser)：代表这个JWT的签发主体；
     * sub(Subject)：代表这个JWT的主体，即它的所有人；
     * aud(Audience)：代表这个JWT的接收对象；
     * exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
     * nbf(Not Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的；
     * iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
     * jti(JWT ID)：是JWT的唯一标识。
     */
    String CLAIMS = "claims";
    String AUTHOR_HEADER = "Authorization";
    String AUTHOR_HEADER_PREFIX = "bearer;";
    String EXPIRED_TOKEN = "过期的token";
    String INVALID_TOKEN = "无效的token";
    String INVALID_USERNAME = "用户不存在";
    String INVALID_PASSWORD = "密码错误";
    long INTERVAL_TIME = 1000 * 60;
}
