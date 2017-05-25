# Surgeon
[English](https://github.com/TangXiaoLv/Android-Router/blob/master/README.md) | 中文

<img src="img/2.png" width = "660" height = "300"/>

|Lib|androidrouter|androidrouter-compiler|androidrouter-annotations|
|:---:|:---|:---|:---|
|最新版本|2.0.6|1.0.1|1.0.0|

高性能，灵活，简单易用的轻量级Android组件化协议框架，用来解决复杂工程的互相依赖，解耦出的单个模块有利于独立开发和维护。

Update Log
---
```
```

目标
---
- 工程解耦
- 模块独立开发独立维护
- 让生活变得美好

特性
---
- 编译时处理注解生成模板代码

组件化路由图
---

<img src="img/1.png" width = "824" height = "528"/>

Gradle
---
```gradle
//需要在各自的application/library 中添加依赖
//android plugin version >= 2.2+
dependencies {
    compile 'com.library.tangxiaolv:androidrouter:x.x.x'
    annotationProcessor 'com.library.tangxiaolv:androidrouter-compiler:x.x.x'
}

//android plugin version < 2.2
apply plugin: 'com.neenbedankt.android-apt'

buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
    }
}

dependencies {
    compile 'com.library.tangxiaolv:androidrouter:x.x.x'
    apt 'com.library.tangxiaolv:androidrouter-compiler:x.x.x'
}
```

快速入门
---
**第一步:给自定义Module配置注解协议**
```java
```

**第二步:调用协议**
```java

```

**混淆**
```
//配置混淆
-keep class * implements com.tangxiaolv.router.interfaces.IMirror{*;}
-keep class * implements com.tangxiaolv.router.interfaces.IRouter{*;}
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
