# Surgeon
English | [中文](https://github.com/TangXiaoLv/Surgeon/blob/master/README-CN.md)

<img src="img/1.png" width = "200" height = "200"/>

|Lib|surgeon-plugin|surgeon-compile|
|:---:|:---|:---|
|latest|1.0.2|1.0.1|

Surgeon is a hot function replace framework for Android which was simple to use,flexible,high-performance.

Integration
---
```gradle
//add plugin
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.tangxiaolv.surgeon:surgeon-plugin:1.0.2'
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
    annotationProcessor 'com.tangxiaolv.surgeon:surgeon-compile:1.0.1'
}
```

Getting Started
---
**1.Add annotation on target function**
```java
package com.tangxiaolv.sdk;
public class SDKActivity extends AppCompatActivity {
    //namespace usually is packageName + className,Also can define any string if you want. 
    //function function name,usually is current function name,Also can define any string if you want. 
    //namespace + function must unique
    @ReplaceAble(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    private String getTwo() {
        return "TWO";
    }
}
```

**2.Add annotation on replace function**
```java
//Create ISurgeon subclass.
public class HotReplace implements ISurgeon {

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

Advance
---
**Target function**
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

**1.Static Replace**
```java
public class HotReplace implements ISurgeon {
    @Override
    public boolean singleton() {
        return false;
    }

    //called before target function call
    //target : target function owner object
    @ReplaceBefore(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public void getTwoBefore(Object target) {
    }
    
    //replace target function
    //handle :You can invoke target function or get target function owner by handler.
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public String getTwo(TargetHandle handle) {
        return "getTwo from remote";
    }
    
    //replace target override function
    //handle :You can invoke target function or get target function owner by handler.
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo.text")
    public String getTwo(TargetHandle handle,String text/**target params*/) {
        return "getTwo from remote";
    }
    
    //called after target function call
    //target : target function owner object
    @ReplaceAfter(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getTwo")
    public void getTwoAfter(Object target) {
    }
    
    //replace target function
    @Replace(namespace = "com.tangxiaolv.sdk.SDKActivity", function = "getThree")
    public String getThree(TargetHandle handle, String text) throws Throwable {
        String newText = text + "_hack!";
        //invoke origin method with new params
        return (String) handle.proceed(newText);
    }
}
```

**1.Runtime Replace**
```java
//replace return value for target function
Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", "Runtime result");

//replace return value for target override function 
Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo.text", "Runtime result");

//replace target function
Surgeon.replace("com.tangxiaolv.sdk.SDKActivity.getTwo", new ReplacerImpl<String>(){
    //params[0] = function owner's object,The other is origin params 
    @Override
    public void before(Object[] params) {
        super.before(params);
    }

    //params[0] = TargetHandle,The other is origin params
    @Override
    public String replace(Object[] params) {
        return super.replace(params);
    }

    //params[0] = function owner's object,The other is origin params
    @Override
    public void after(Object[] params) {
        super.after(params);
    }
});
```

ProGuard
---
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
