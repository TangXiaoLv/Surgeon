# Surgeon
[English](https://github.com/TangXiaoLv/Surgeon/blob/master/README.md) | 中文

<img src="img/1.png" width = "200" height = "200"/>

|Lib|surgeon-plugin|surgeon-compile|
|:---:|:---|:---|
|最新版本|1.0.0|1.0.0|

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
        classpath 'com.tangxiaolv.surgeon:surgeon-plugin:1.0.0'//version参照上表
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
    annotationProcessor 'com.tangxiaolv.surgeon:surgeon-compile:1.0.0'//version参照上表
}
```

快速入门
---
**一：在library/application中目标方法上加上注解**
```java
@ReplaceAble
private String getTwo() {
    return "TWO";
}
```

**二：在替换方法上配置目标方法路径的注解**
```java
//创建新类实现ISurgeon
public class HotReplace implements ISurgeon {
    /**
     * ref为目标方法的packageName+className+methodName
     * @param target 默认传递:目标方法所在对象,如果目标方法为静态,target为null
     */
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public String getTwo(Object target) {
        return "getTwo from HotReplace2";
    }
}
```

进阶
---
**目标方法**
```java
@ReplaceAble
private String getTwo() {
    return "TWO";
}

@ReplaceAble(extra = "text")
private String getTwo(String text) {
    return text;
}
```

**一：静态替换**
```java
public class HotReplace implements ISurgeon {
    //调用目标方法前调用
    @ReplaceBefore(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public void getTwoBefore(Object target) {
    }
    
    //替换目标方法
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public String getTwo(Object target) {
        return "getTwo from remote";
    }
    
    //目标重载方法替换
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo",extra = "text")
    public String getTwo(Object target,String text/**目标方法参数*/) {
        return "getTwo from remote";
    }
    
    //目标方法调用之后调用
    @ReplaceAfter(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public void getTwoAfter(Object target) {
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
    @Override
    public void before(Object[] params) {
        super.before(params);
    }

    //params[0] = target,其他是目标方法参数
    @Override
    public String replace(Object[] params) {
        return super.replace(params);
    }

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
