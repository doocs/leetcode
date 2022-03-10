# [588. 设计内存文件系统](https://leetcode-cn.com/problems/design-in-memory-file-system)

[English Version](/solution/0500-0599/0588.Design%20In-Memory%20File%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个内存文件系统，模拟以下功能：</p>

<p>实现文件系统类:</p>

<ul>
	<li><code>FileSystem()</code>&nbsp;初始化系统对象</li>
	<li><code>List&lt;String&gt; ls(String path)</code>
	<ul>
		<li>如果 <code>path</code> 是一个文件路径，则返回一个仅包含该文件名称的列表。</li>
		<li>如果 <code>path</code> 是一个目录路径，则返回该目录中文件和 <strong>目录名</strong> 的列表。</li>
	</ul>
	</li>
</ul>

<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 答案应该 按<strong>字典顺序</strong> 排列。</p>

<ul>
	<li><code>void mkdir(String path)</code>&nbsp;根据给定的路径创建一个新目录。给定的目录路径不存在。如果路径中的中间目录不存在，您也应该创建它们。</li>
	<li><code>void addContentToFile(String filePath, String content)</code>
	<ul>
		<li>如果 <code>filePath</code> 不存在，则创建包含给定内容&nbsp;<code>content</code>的文件。</li>
		<li>如果 <code>filePath</code> 已经存在，将给定的内容&nbsp;<code>content</code>附加到原始内容。</li>
	</ul>
	</li>
	<li><code>String readContentFromFile(String filePath)</code>&nbsp;返回 <code>filePath</code>下的文件内容。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0588.Design%20In-Memory%20File%20System/images/filesystem.png" style="height: 315px; width: 650px;" /></p>

<pre>
<strong>输入:</strong> 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
<strong>输出:</strong>
[null,[],null,null,["a"],"hello"]

<strong>解释:</strong>
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // 返回 []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // 返回 ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // 返回 "hello"</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ul>
	<li><code>1 &lt;= path.length,&nbsp;filePath.length &lt;= 100</code></li>
	<li><code>path</code>&nbsp;和&nbsp;<code>filePath</code>&nbsp;都是绝对路径，除非是根目录&nbsp;<code>‘/’</code>&nbsp;自身，其他路径都是以&nbsp;<code>‘/’</code>&nbsp;开头且 <strong>不</strong> 以&nbsp;<code>‘/’</code>&nbsp;结束。</li>
	<li>你可以假定所有操作的参数都是有效的，即用户不会获取不存在文件的内容，或者获取不存在文件夹和文件的列表。</li>
	<li>你可以假定所有文件夹名字和文件名字都只包含小写字母，且同一文件夹下不会有相同名字的文件夹或文件。</li>
	<li><code>1 &lt;= content.length &lt;= 50</code></li>
	<li><code>ls</code>,&nbsp;<code>mkdir</code>,&nbsp;<code>addContentToFile</code>, and&nbsp;<code>readContentFromFile</code>&nbsp;最多被调用&nbsp;<code>300</code>&nbsp;次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
