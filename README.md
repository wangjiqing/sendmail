#Spring Boot 发送邮件实例

### maven添加如下依赖：

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>

<h2>1. 发送普通邮件</h2>

    /sendSimpleMail

<h2>2. 发送HTML格式邮件</h2>

    /sendHtmlMail

<h2>3. 发送带有附件的邮件</h2>

    /sendBlobMail

# 普通maven项目发送邮件实例

### maven添加如下依赖

    <!-- 邮件相关依赖 -->
    <dependency>
      <groupId>com.sun.mail</groupId>
      <artifactId>dsn</artifactId>
      <version>1.4.7</version>
    </dependency>
    <dependency>
      <groupId>com.sun.mail</groupId>
      <artifactId>gimap</artifactId>
      <version>1.4.7</version>
    </dependency>
    <dependency>
      <groupId>com.sun.mail</groupId>
      <artifactId>imap</artifactId>
      <version>1.4.7</version>
    </dependency>
    <dependency>
      <groupId>com.sun.mail</groupId>
      <artifactId>javax.mail</artifactId>
      <version>1.4.7</version>
    </dependency>
    <dependency>
      <groupId>com.sun.mail</groupId>
      <artifactId>mailapi</artifactId>
      <version>1.4.7</version>
    </dependency>
    <dependency>
      <groupId>com.sun.mail</groupId>
      <artifactId>pop3</artifactId>
      <version>1.4.7</version>
    </dependency>
    <dependency>
      <groupId>com.sun.mail</groupId>
      <artifactId>smtp</artifactId>
      <version>1.4.7</version>
    </dependency>
    
###maven中添加如下plugin

     <plugins>
       <plugin>
         <groupId>org.apache.maven.plugins</groupId>
         <artifactId>maven-shade-plugin</artifactId>
         <version>1.2.1</version>
         <executions>
           <execution>
             <phase>package</phase>
             <goals>
               <goal>shade</goal>
             </goals>
             <configuration>
               <transformers>
                 <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                   <mainClass>com.baidu.depth.DoMailBusiness</mainClass>
                 </transformer>
               </transformers>
             </configuration>
           </execution>
         </executions>
       </plugin>
     </plugins>    
       

