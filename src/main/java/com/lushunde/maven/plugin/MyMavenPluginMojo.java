/**
 * Copyright © 2020, lushunde or www.lushunde.com site. All rights reserved.
 * @package my-maven-plugin com.lushunde.maven.plugin 
 * @author bellus
 * @node 
 */
package com.lushunde.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import java.util.List;

/**
 * @node
 * @package my-maven-plugin com.lushunde.maven.plugin
 * @version 1.0
 * @date 2020年9月20日
 * @author bellus
 * @since JDK1.8
 */

@Mojo(name = "MyMavenPluginMojo", defaultPhase = LifecyclePhase.PACKAGE)
public class MyMavenPluginMojo extends AbstractMojo {

	/**
	 * 传参 String，使用方在POM定义 <msg>This is my message </msg>
	 */
	@Parameter
	private String message;

	/**
	 * 传参 LIST<STRING>，使用方在POM定义 <options> <option>one Array </option>
	 * <option>two Array </option> </options>
	 */
	@Parameter
	private List<String> options;

	/**
	 * 传参 String，使用方在执行命令中 定义参数 mvn install -Dargs=abc
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
