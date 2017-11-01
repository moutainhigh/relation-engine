package com.yingu.relationengine.swagger.model;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class AuthorityDTO implements Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -5766444528614113758L;

    /**  */
    @NotBlank
    @Size(min = 6, max = 32)
    private String name;

    private List<SysResourceDTO> sysResource;

    public AuthorityDTO(){

    }
    public AuthorityDTO(String name, List<SysResourceDTO> sysResource){
        this.name = name;
        this.sysResource = sysResource;
    }

    /**
     * 获取
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置
     *
     * @param name
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    public List<SysResourceDTO> getSysResource() {
        return sysResource;
    }

    public void setSysResource(List<SysResourceDTO> sysResource) {
        this.sysResource = sysResource;
    }
}
