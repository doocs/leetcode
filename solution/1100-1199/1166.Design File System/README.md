# [1166. 设计文件系统](https://leetcode-cn.com/problems/design-file-system)

[English Version](/solution/1100-1199/1166.Design%20File%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要设计一个文件系统，你可以创建新的路径并将它们与不同的值关联。</p>

<p>路径的格式是一个或多个连接在一起的字符串，形式为：&nbsp;<code>/</code> ，后面跟着一个或多个小写英文字母。例如， <code>" /leetcode"</code> 和 <code>"/leetcode/problems"</code> 是有效路径，而空字符串 <code>""</code> 和 <code>"/"</code> 不是。</p>

<p>实现 <code>FileSystem</code> 类:</p>

<ul>
	<li><meta charset="UTF-8" /><code>bool createPath(string path, int value)</code>&nbsp;创建一个新的&nbsp;<code>path</code> ，并在可能的情况下关联一个 <code>value</code> ，然后返回 <code>true</code> 。如果路径<strong>已经存在</strong>或其父路径<strong>不存在</strong>，则返回&nbsp;<code>false</code>&nbsp;。</li>
	<li>&nbsp;<code>int get(string path)</code> 返回与 <code>path</code> 关联的值，如果路径不存在则返回 <code>-1</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
["FileSystem","create","get"]
[[],["/a",1],["/a"]]
<strong>输出：</strong> 
[null,true,1]
<strong>解释：</strong> 
FileSystem fileSystem = new FileSystem();

fileSystem.create("/a", 1); // 返回 true
fileSystem.get("/a"); // 返回 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong> 
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
<strong>输出：</strong> 
[null,true,true,2,false,-1]
<strong>解释：</strong>
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // 返回 true
fileSystem.createPath("/leet/code", 2); // 返回 true
fileSystem.get("/leet/code"); // 返回 2
fileSystem.createPath("/c/d", 1); // 返回 false 因为父路径 "/c" 不存在。
fileSystem.get("/c"); // 返回 -1 因为该路径不存在。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>对两个函数的调用次数加起来小于等于&nbsp;<meta charset="UTF-8" /><code>10<sup>4</sup></code>&nbsp;</li>
	<li><code>2 &lt;= path.length &lt;= 100</code></li>
	<li><code>1 &lt;= value &lt;= 10<sup>9</sup></code>&nbsp;</li>
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
