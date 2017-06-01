# Surgeon
[English](https://github.com/TangXiaoLv/Surgeon/blob/master/README.md) | 中文

<img src="img/1.png" width = "200" height = "200"/>

|Lib|surgeon-plugin|surgeon-compile|
|:---:|:---|:---|
|最新版本|1.0.2|1.0.1|

Surgeon是Android上一个简单，灵活，高性能的方法热替换框架。

集成
---
```gradle
//添加插件
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.tangxiaolv.surgeon:surgeon-plugin:1.0.2'//version参照上表
    }
}

//引用插件
apply plugin: 'com.android.library'
apply plugin: 'com.tangxiaolv.surgeon'
或
apply plugin: 'com.android.application'
apply plugin: 'com.tangxiaolv.surgeon'

//添加注解解析器
dependencies {
    annotationProcessor 'com.tangxiaolv.surgeon:surgeon-compile:1.0.1'//version参照上表
}
```

快速入门
---
**一：在library/application中目标方法上加上注解**
```java
package com.tangxiaolv.sdk;
public class SDKActivity extends AppCompatActivity {
    //namespace 命名空间,通常为packageName + className,也可以自定义
    //function 方法名称,通常为当前的方法名字，也可自定义
    //namespace + function必须唯一,否则无法正确被替换
    @ReplaceAble(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    private String getTwo() {
        return "TWO";
    }
}
```

**二：在替换方法上配置目标方法路径的注解**
```java
//创建新类实现ISurgeon
public class HotReplace implements ISurgeon {

    //当前类是否为单例
    @Override
    public boolean singleton() {
        return false;
    }

    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public String getTwo(TargetHandle handle) {
        return "getTwo from HotReplace2";
    }
}
```

进阶
---
**目标方法**
```java
package com.tangxiaolv.sdk;
public class SDKActivity extends AppCompatActivity {
 
    @ReplaceAble(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    private String getTwo() {
        return "TWO";
    }
    
    @ReplaceAble(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo.text")
    private String getTwo(String text) {
        return text;
    }
    
    @ReplaceAble(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getThree")
    private String getThree(String text) {
        return "getThree_"+text;
    }
}
```

**一：静态替换**
```java
public class HotReplace implements ISurgeon {

    @Override
    public boolean singleton() {
        return false;
    }

    //调用目标方法前调用
    //target 目标方法所在对象
    @ReplaceBefore(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public void getTwoBefore(Object target) {
    }
    
    //替换目标方法
    //handle 目标方法的处理类，可通过它调用目标方法和获取目标方法所在对象
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public String getTwo(TargetHandle handle) {
        return "getTwo from remote";
    }
    
    //目标重载方法替换
    //handle 目标方法的处理类，可通过它调用目标方法和获取目标方法所在对象
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo.text")
    public String getTwo(TargetHandle handle,String text/**目标方法参数*/) {
        return "getTwo from remote";
    }
    
    //目标方法调用之后调用
    //target 目标方法所在对象
    @ReplaceAfter(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public void getTwoAfter(Object target) {
    }
    
    //替换目标方法
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getThree")
    public String getThree(TargetHandle handle, String text) throws Throwable {
        String newText = text + "_hack!";
        //使用新参数调用原始方法
        return (String) handle.proceed(newText);
    }
}
```

**一：动态替换**
```java
//替换目标方法返回值
Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", "Runtime result");

//替换目标重载方法返回值
Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo.text", "Runtime result");

//替换目标方法
Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", new ReplacerImpl<String>(){
    //params[0] = 目标方法所在对象,其他是目标方法参数
    @Override
    public void before(Object[] params) {
        super.before(params);
    }

    //params[0] = TargetHandle,其他是目标方法参数
    @Override
    public String replace(Object[] params) {
        return super.replace(params);
    }

    //params[0] = 目标方法所在对象,其他是目标方法参数
    @Override
    public void after(Object[] params) {
        super.after(params);
    }
});
```

混淆
---
```
//配置混淆
-keep class * implements com.surgeon.weaving.core.interfaces.ISurgeon{*;}
-keep class * implements com.surgeon.weaving.core.interfaces.IMaster{*;}
```

License
---
    Copyright 2017 TangXiaoLv
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
