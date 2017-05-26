# Surgeon
English | [中文](https://github.com/TangXiaoLv/Surgeon/blob/master/README-CN.md)

<img src="img/1.png" width = "200" height = "200"/>

|Lib|surgeon-plugin|surgeon-compile|
|:---:|:---|:---|
|latest|1.0.0|1.0.0|

Surgeon is a hot method replace framework for Android which was simple to use,flexible,high-performance.

Integration
---
```gradle
//add plugin
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.tangxiaolv.surgeon:surgeon-plugin:1.0.0'
    }
}

//use plugin
apply plugin: 'com.android.library'
apply plugin: 'com.tangxiaolv.surgeon'

or

apply plugin: 'com.android.application'
apply plugin: 'com.tangxiaolv.surgeon'

//add annotationProcessor
dependencies {
    annotationProcessor 'com.tangxiaolv.surgeon:surgeon-compile:1.0.0'
}
```

Getting Started
---
**1.Add annotation on target method**
```java
@ReplaceAble
private String getTwo() {
    return "TWO";
}
```

**2.Add annotation on replace method**
```java
//Create ISurgeon subclass.
public class HotReplace implements ISurgeon {
    /**
     * ref = target(packageName + className + methodName)
     * @param target Defualt passed,The target method owner object,If target method is static then it equal null.
     */
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public String getTwo(Object target) {
        return "getTwo from HotReplace2";
    }
}
```

Advance
---
**Target method**
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

**1.Static Replace**
```java
public class HotReplace implements ISurgeon {
    //called before target method call
    @ReplaceBefore(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public void getTwoBefore(Object target) {
    }
    
    //replace target method
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public String getTwo(Object target) {
        return "getTwo from remote";
    }
    
    //replace target override method
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo",extra = "text")
    public String getTwo(Object target,String text/**target method params*/) {
        return "getTwo from remote";
    }
    
    //called after target method call
    @ReplaceAfter(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public void getTwoAfter(Object target) {
    }
}
```

**1.Runtime Replace**
```java
//replace return value for target method
Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", "Runtime result");

//replace return value for target override method 
Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo.text", "Runtime result");

//replace target method
Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", new ReplacerImpl<String>(){
    @Override
    public void before(Object[] params) {
        super.before(params);
    }

    //params[0] = target,the other is target params 
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

**ProGuard**
```
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
