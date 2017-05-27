# Surgeon
English | [中文](https://github.com/TangXiaoLv/Surgeon/blob/master/README-CN.md)

<img src="img/1.png" width = "200" height = "200"/>

|Lib|surgeon-plugin|surgeon-compile|
|:---:|:---|:---|
|latest|1.0.1|1.0.0|

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
        classpath 'com.tangxiaolv.surgeon:surgeon-plugin:1.0.1'
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
**1.Add annotation on target function**
```java
package com.tangxiaolv.sdk;
public class SDKActivity extends AppCompatActivity {
    @ReplaceAble
    private String getTwo() {
        return "TWO";
    }
}
```

**2.Add annotation on replace function**
```java
//Create ISurgeon subclass.
public class HotReplace implements ISurgeon {
    /**
     * ref = target(packageName + className + methodName)
     * @param target Defualt passed,The target function owner object,If target function is static then it equal null.
     */
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public String getTwo(Object target) {
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
    @ReplaceAble
    private String getTwo() {
        return "TWO";
    }
    
    @ReplaceAble(extra = "text")
    private String getTwo(String text) {
        return text;
    }
    
    @ReplaceAble(extra = "text")
    private String getThree(String text) {
        return "getThree_"+text;
    }
}
```

**1.Static Replace**
```java
public class HotReplace implements ISurgeon {
    //called before target function call
    @ReplaceBefore(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public void getTwoBefore(Object target) {
    }
    
    //replace target function
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public String getTwo(TargetHandle handle) {
        return "getTwo from remote";
    }
    
    //replace target override function
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo",extra = "text")
    public String getTwo(TargetHandle handle,String text/**origin params*/) {
        return "getTwo from remote";
    }
    
    //called after target function call
    @ReplaceAfter(ref = "com.tangxiaolv.sdk.SDKActivity.getTwo")
    public void getTwoAfter(Object target) {
    }
    
    @Replace(ref = "com.tangxiaolv.sdk.SDKActivity.getThree", extra = "text")
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
