package com.yingu.relationengine.swagger.model;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public class SysResourceDTO implements Serializable {

    /**  */
    private Long id;

    /** 名称 */
    @Size(min = 6, max = 32)
    private String name;

    /** 类别：菜单menu，按钮button，其他function */
    @Size(min = 6, max = 32)
    private String type;

    /** 路径 */
    @Size( max = 200)
    private String url;

    /** 父ID */
    private Long parentId;

    /** 父项全路径 */
    private String parentIds;

    /**  */
    @Size( max = 16)
    private String permission;

    /** 是否可用 */
    private Boolean available;

    private Set<String> authorities;

    public SysResourceDTO(){

    }

    public SysResourceDTO(Long id, String name, String type, String url, Long parentId, String parentIds, String permission, Boolean available, Set<String> authorities){
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.permission = permission;
        this.available = available;
        this.authorities = authorities;
    }
    /**
     * 获取
     *
     * @return
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置
     *
     * @param id
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置名称
     *
     * @param name
     *          名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类别：菜单menu，按钮button，其他function
     *
     * @return 类别：菜单menu
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置类别：菜单menu，按钮button，其他function
     *
     * @param type
     *          类别：菜单menu，按钮button，其他function
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取路径
     *
     * @return 路径
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置路径
     *
     * @param url
     *          路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取父ID
     *
     * @return 父ID
     */
    public Long getParentId() {
        return this.parentId;
    }

    /**
     * 设置父ID
     *
     * @param parentId
     *          父ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取父项全路径
     *
     * @return 父项全路径
     */
    public String getParentIds() {
        return this.parentIds;
    }

    /**
     * 设置父项全路径
     *
     * @param parentIds
     *          父项全路径
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 获取
     *
     * @return
     */
    public String getPermission() {
        return this.permission;
    }

    /**
     * 设置
     *
     * @param permission
     *
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取是否可用
     *
     * @return 是否可用
     */
    public Boolean getAvailable() {
        return this.available;
    }

    /**
     * 设置是否可用
     *
     * @param available
     *          是否可用
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
