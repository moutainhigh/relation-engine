package com.yingu.relationengine.remote.feign;


import com.yingu.relationengine.client.AuthorizedFeignClient;
import com.yingu.relationengine.config.FeignSkipBadRequestsConfiguration;
import com.yingu.relationengine.remote.feign.bean.LoginVM;
import com.yingu.relationengine.swagger.model.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AuthorizedFeignClient(name = "uaa", configuration = FeignSkipBadRequestsConfiguration.class)
public interface UaaFeignClient {

    @RequestMapping(value = "/api/authenticate",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> authorize(LoginVM loginVM);

    @RequestMapping(value = "/oauth/token",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Token> login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("grant_type") String grant_type, @RequestParam("scope") String scope, @RequestParam("client_id") String client_id, @RequestParam("client_secret") String client_secret);

    @RequestMapping(value = "/api/users",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List> getAllUsers(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("login") String login);

    @RequestMapping(value = "/api/register",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UAAResponse> registerUsers(ManagedUserVM managedUserVM);

    @RequestMapping(value = "/api/activate",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> activate(@RequestParam("login") String login, @RequestParam("activate") Boolean activate);

    @RequestMapping(value = "/api/users",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> addUser(@RequestBody ManagedUserVM managedUserVM);

    @RequestMapping(value = "/api/users",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> updateUser(@RequestBody ManagedUserVM managedUserVM);

    @RequestMapping(value = "/api/users/{login}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> deleteUser(@PathVariable("login") String login);

    @RequestMapping(value = "/api/users/{login}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> getUser(@PathVariable("login") String login);

    @RequestMapping(value = "/api/check/account",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity checkUser(@RequestParam("login") String login, @RequestParam("password") String password);

    @RequestMapping(value = "/api/authority/{login}/type/menu",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthorityDTO> getAuthMenu(@PathVariable("login") String login);

    @RequestMapping(value = "/api/resource/type/button",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<SysResourceDTO>> getAllButtonResource();

    @RequestMapping(value = "/api/authority",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AuthorityDTO>> getAllAuthority();

    @RequestMapping(value = "/api/page/resource",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<SysResourceDTO>> getAllResource(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

    @RequestMapping(value = "/api/authority/{name}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> addAuthority(@PathVariable("name") String name);


    @RequestMapping(value = "/api/resource/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> deleteAuthority(@PathVariable("id") Long id);

    @RequestMapping(value = "/api/resource",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> addResource(@RequestBody ManagedResourceVM managedResourceVM);

    @RequestMapping(value = "/api/resource",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> updateResource(@RequestBody ManagedResourceVM managedResourceVM);

    @RequestMapping(value = "/api/resource/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> deleteResource(@PathVariable("id") Long id);

    @RequestMapping(value = "/api/resource/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> getOneResource(@PathVariable("id") Long id);

    @RequestMapping(value = "/api/init/password",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> password(LoginAndPasswordVM loginAndPasswordVM);

    @RequestMapping(value = "/api/account/email",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> getByEmail(@RequestParam("email") String email);

//    @RequestMapping(value = "/api/users/{login}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity<UserDTO> getUser(@PathVariable("login") String login);
//
//    @RequestMapping(value = "/api/check/account",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity checkUser(@RequestParam("login")String login,@RequestParam("password")String password);

    @RequestMapping(value = "/api/password/back",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> getBackPassword(@RequestParam("email") String email, @RequestParam("password") String password);

    @RequestMapping(value = "/api/operator",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> getOperator(@RequestParam("login") String login, @RequestParam("email") String email);

    @RequestMapping(value = "/api/operator/list",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDTO>> getOperatorsList(@RequestBody UserNamesVm userNamesVm);

    @RequestMapping(value = "/api/authority",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessModel> updateAuthority(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName);
}
