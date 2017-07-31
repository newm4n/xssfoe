# XSSFoe
"A Java based XSS attack prevention library inspired by OWASP guidance"

## What is XSS
[OWASP](https://github.com/markdown-it/markdown-it) says "Cross-Site Scripting (XSS) attacks are a type of injection, in which malicious scripts are injected into otherwise benign and trusted web sites."

[WIKIPEDIA](https://www.owasp.org/index.php/XSS_(Cross_Site_Scripting)_Prevention_Cheat_Sheet) says "Cross-site scripting (XSS) is a type of computer security vulnerability typically found in web applications."

XSSFOE says "Cross-Site Scripting (XSS) is an attack style done by bad and mean internet user toward our good and cool website."

## Why protect your website from XSS ?
Well, websites usually accepting information from many sources. They accept user/password when a user logging in. They accept user particulars when they're registering.
While Good and honest user will provide good data. But thiefs, robbers, bad bad guys tend to submit wrong information. Thanks to the advancement of technologies this days, browser is
getting smart and smarter. This blessing to web browser is also become it's curse. When users send information from their browser to your website, this information commonly shown back 
to the user, and while displaying this information, the browser executes information for display or processing purpose, regardles if the information is good or bad.
This makes the browser become a threat to your visitor's privacy, their sensitive data, their computer, their bank account, your website and their (and your) live.

With a little improvised effort, Bad Guys can steal other user's information (like password), etc. Yes... that bad.

## What does this library do ?
This library provide a string filter mechanism, to strip/remove or modify an input string containing possible XSS attack vector and return a "safe" form of it.
It utilizes lots of regular expression patterns to scan given string looking for potential XSS form.

Or, simply try to detect what ever bad information get sent to the server, try remove them before it get accepted further by the server for safe keeping or display.

## Wait !! Before you guys says it !!
I know, i know. The best XSS prevention is to do "Positive XSS Prevention Model" as OWASP says it. Basicaly, it strips all XSS threat prior out-put them (usually into an HTML).
Well, this is a simple library, it doesn't have a HTML parser (YET). So, it filter based on the input as it coming to the server in HTTP request. So far, it serve me well.

Still, I'm more than willing to enhance the library for the good of every body. You know html parsing ? Contact me !! Immediately !!

This library also do not protect XSS as it were outputted in non HTML documents, if the attack comes from the CSS style then why on earth you let the user input to become an output in your CSS style ?

Well, I say, I filter them out as they comes in. So it safe for outputing everything =).

## How to user XSSFoe ?

You can use the servlet filter in your web.xml, add the following snippet into your web.xml. Make sure you included xssfoe's jar file into your web app's lib.

```xml
<filter>
   <filter-name>XSSFoe</filter-name>
   <filter-class>org.xssfoe.xss.XSSFilter</filter-class>
</filter>

<filter-mapping>
   <filter-name>XSSFoe</filter-name>
   <url-pattern>/*</url-pattern>
</filter-mapping>
```

## How To Compile, Package and Test

Well, as for now, July 31st 2017, I have not submit any release to Maven Central yet. Will do in a couple of days. So, you need to get it straight from github. Its easy !!!

### Compile, Package and Install.

* First you clone from GitHub.
* Second, go to the cloned directory in your computer and ...
* Execute "mvn clean install"

Thats it !! It will compile, test and install the library into your local maven repository, Then you can add this maven dependency in your web app project.

```xml
<dependency>
	<groupId>org.xssfoe</groupId>
    <artifactId>xssfoe</artifactId>
    <version>1.1</version>
</dependency>
```

### Test

This instruction bellow,

```
mvn test
```

Will test the library against all the attack vectors in [this file](https://github.com/newm4n/xssfoe/blob/develop/src/test/resources/xssvector.txt). Yes, there's alot of vector already there. Feel free to add more line(s) into the file if you have a suspected XSS vector, run the mvn command again. See if XSSFoe can deal with it.


## Want to contribute ?

YES, we need contributor !! For ye who are willing ! in the name of open-source. Join us in our holy war against XSS attacks ! We need your feedback, your input, and your code !! We also accept money, LOL, who doesn't ?! =)
