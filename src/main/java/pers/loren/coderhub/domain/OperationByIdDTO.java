package pers.loren.coderhub.domain;

public class OperationByIdDTO extends BaseDTO {
    private Integer id;

    public OperationByIdDTO(Integer id) {
        this.id = id;
    }

    public OperationByIdDTO() {
    }

    public OperationByIdDTO(Integer id, Integer userId) {
        this.id = id;
        this.setUserId(userId);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
