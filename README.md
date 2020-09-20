# 介绍

官方定义插件地址：[https://maven.apache.org/guides/plugin/guide-java-plugin-development.html](https://maven.apache.org/guides/plugin/guide-java-plugin-development.html)

注：官方介绍更加全面

# 实现自定义maven插件

## 新建maven项目，如图

![image-20200920210559030](image-20200920210559030.png)

## 修改打包类型

pom.xml文件修改为插件

> \<packaging\>maven-plugin\</packaging\>

## 增加依赖包

```xml
	<dependencies>
		<dependency>
			<groupId>org.apache.maven</groupId>
			<artifactId>maven-plugin-api</artifactId>
			<version>3.6.2</version>
		</dependency>

		<dependency>
			<groupId>org.apache.maven.plugin-tools</groupId>
			<artifactId>maven-plugin-annotations</artifactId>
			<version>3.6.0</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>
```

## 指定插件编译资源（可选）

```xml
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins </groupId>
            <artifactId>maven-compiler-plugin </artifactId>
            <version>3.6.1 </version>
            <configuration>
                <source>1.8 </source>
                <target>1.8 </target>
            </configuration>
        </plugin>
    </plugins>
```



## 编写核心实现类

- class xxx extends AbstractMojo
- @Mojo(name = "xxx",defaultPhase = LifecyclePhone.xxxxx)
- 传参方式
- 实现必要方法 execute

```java
/**
 * Copyright © 2020, lushunde or www.lushunde.com site. All rights reserved.
 * @package maven-plugin com.lushunde.plugin 
 * @author bellus
 * @node 
 */
package com.lushunde.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import java.util.List;

/**
 * @node 自定义MavenPlugin插件实现类
 * @package maven-plugin com.lushunde.plugin
 * @version 1.0
 * @date 2020年9月20日
 * @author bellus
 * @since JDK1.8
 */

@Mojo(name = "MyMavenPluginMojo", defaultPhase = LifecyclePhase.PACKAGE)
public class MyMavenPluginMojo extends AbstractMojo {

	/**
	 * 传参 String，使用方在POM定义
	 * <msg>This is my message </msg>
	 */
	@Parameter
	private String message;

	/**
	 * 传参 LIST<STRING>，使用方在POM定义
	 * <options>
	 * 	<option>one Array </option>
	 * 	<option>two Array </option>
	 * </options>
	 */
	@Parameter
	private List<String> options;

	/**
	 * 传参 String，使用方在执行命令中 定义参数
	 * mvn install -Dargs=abc
	 */
	@Parameter(property = "args")
	private String args;

	
	/**
	 * 真正需要操作的代码实现方法，编写具体插件操作逻辑
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("hello MyMavenPluginMojo:" + message);
		System.out.println("hello MyMavenPluginMojo:" + options);
		System.out.println("hello MyMavenPluginMojo:" + args);
	}

}

```

## 发布插件

> mvn install  

至此，插件已经写完并且发布出去了。

----------



# 项目使用自定义maven插件

## POM添加插件配置

在使用项目中添加如下插件信息

```xml
	<build>
		<plugins>
			<!-- 使用自定义插件 -->
			<plugin>
				<groupId>com.lushunde.maven</groupId>
				<artifactId>maven-plugin</artifactId>
				<version>0.0.1-SNAPSHOT</version>
				<configuration>
					<message>This is my message </message>
					<options>
						<option>one Array </option>
						<option>two Array </option>
					</options>

				</configuration>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>MyMavenPluginMojo</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
```

- executions 挂载插件
- phase 挂在在package上，执行package是就会执行<goal>MyMavenPluginMojo</goal>
