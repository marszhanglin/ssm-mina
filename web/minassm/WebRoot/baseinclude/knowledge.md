 ## JSP中的include的两种用法 ## 
#### 拷贝的内容 ####
 
1.两种用法

<@inlcude file =”header.jsp”/>

此时引入的是静态的jsp文件,它将引入的jsp中的源代码原封不动地附加到当前文件中,所以在jsp程序中使用这个指令的时候file里面的值（即要导入的文件）不能带多余的标签或是与当前jsp文件重复的东西。例如里面不要包含<html><body>这样的标签，因为是把源代码原封不动的附加过来，所以会与当前的jsp中的这样的标签重复导致出错。  

<jsp:include page=”/user/test”flush=”true”/>

此时引入执行页面或生成的应答文本.jsp:include标签导入一个重用文件的时候，这个文件是经过编译的，通俗点说就是附加这个要导入文件经过编译后的效果，所以可以含有与当前jsp程序中重复的内容，因为在附加过来之前就会被解析掉。其中flush 表示在读入包含内容之前是否清空任何现有的缓冲区。
 

[http://www.cnblogs.com/bloodhunter/p/4833788.html](http://www.cnblogs.com/bloodhunter/p/4833788.html "JSP中的include的两种用法")