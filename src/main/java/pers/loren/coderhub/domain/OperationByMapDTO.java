package pers.loren.coderhub.domain;

import java.io.Serializable;
import java.util.HashMap;

public class OperationByMapDTO extends BaseDTO implements Serializable {
    private HashMap<String, Object> hashMap;
    private HashMap<String, HashMap<String, Object>> paramsMap;

    public OperationByMapDTO(HashMap<String, Object> hashMap, Integer userId) {
        this.hashMap = hashMap;
        this.setUserId(userId);
    }

    public HashMap<String, HashMap<String, Object>> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(HashMap<String, HashMap<String, Object>> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public HashMap<String, Object> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Object> hashMap) {
        this.hashMap = hashMap;
    }
}
