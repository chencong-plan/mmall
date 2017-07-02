
# 在线购物商城开发

## 用户模块开发
>主要实现用户注册、登录、忘记密码、修改密码、通过问题修改密码、等一系列接口。

* **注册接口**

  >/user/register.do
  
  >注册说明：在使用此接口时候需要传入用户名(username)、密码(password)、电话(phone)、邮箱(email)、问题(question)、答案(answer)
  此时的问题、答案在后面修改密码中需要用到
  
  <table>
    <tr>
      <td>参数名称</td>
      <td>说明</td>
      <td>是否必须</td>
    </tr>
    <tr>
      <td>username</td>
      <td>用户名(不可重复)</td>
      <td>是</td>
    </tr>
    <tr>
      <td>password</td>
      <td>密码</td>
      <td>是</td>
    </tr>
    <tr>
      <td>phone</td>
      <td>电话</td>
      <td>否</td>
    </tr>
    <tr>
      <td>email</td>
      <td>邮箱</td>
      <td>否</td>
    </tr>
    <tr>
      <td>question</td>
      <td>问题(修改密码时用)</td>
      <td>否</td>
    </tr>
    <tr>
      <td>answer</td>
      <td>问题答案</td>
      <td>否</td>
    </tr>
  </table>
  
  >* **request请求参数**(POST)
    ```
     username,password,email,phone,question,answer
    ```
        
  >* **response返回结果**
    
  >success
    ```json
    {
        "status":0,
        "msg":"注册成功"
    }
    ```
  >fail (用户名已经被注册)
    ```json
    {
        "status":1,
        "msg":"用户名已经存在"
    }
    ```

  >fali (邮箱被注册)
   
    ```json
        {
            "status":1,
            "msg":"email已经存在"
        }
    ```
    

* **登录接口**

  >/user/login.do
  
  >登录时候直接使用前面注册(register.do)接口注册的用户名(username)、密码(password)进行登录。
  
  >* **request请求(POST)**
  
        username,password
  >* **response返回**
    
  >success(登陆成功)
    
    ```json
    {
        "status":0,
        "msg":"登录成功",
        "data":{
            "id":22,
            "username":"123456",
            "password":"",
            "email":"1042738887@qq.com",
            "phone":"15271917179",
            "question":"问题",
            "answer":"答案",
            "role":0,
            "createTime":1498796830000,
            "updateTime":1498796830000
        }
    }
    ```  
   >fail(登录错误----密码错误)
    ```json
    {
        "status": 1,
        "msg": "密码错误"
    }
    ```
  >fail(登录错误----用户名错误)
    ```json
    {
        "status":1,
        "msg":"用户名不存在"
    }
    ```

   
* 登出接口

* 检查邮箱/用户名接口
* 用户问题/答案验证接口
* 显示用户问题接口
* 登录状态下通过用户名/新密码/token修改密码接口
* 登录状态下得到用户信息接口
* 登录状态下新密码/旧密码重置密码接口
* 更新用户信息接口(email phone question answer)

        
