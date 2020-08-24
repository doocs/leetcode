# [1166. 设计文件系统](https://leetcode-cn.com/problems/design-file-system)

[English Version](/solution/1100-1199/1166.Design%20File%20System/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>你需要设计一个能提供下面两个函数的文件系统：</p>

<ul>
	<li><strong>create(path, value):</strong> 创建一个新的路径，并尽可能将值 <code>value</code> 与路径 <code>path</code> 关联，然后返回 <code>True</code>。如果路径已经存在或者路径的父路径不存在，则返回 <code>False</code>。</li>
	<li><strong>get(path):</strong> 返回与路径关联的值。如果路径不存在，则返回 <code>-1</code>。</li>
</ul>

<p>“路径” 是由一个或多个符合下述格式的字符串连接起来形成的：在 <code>/</code> 后跟着一个或多个小写英文字母。</p>

<p>例如 <code>/leetcode</code> 和 <code>/leetcode/problems</code> 都是有效的路径，但空字符串和 <code>/</code> 不是有效的路径。</p>

<p>好了，接下来就请你来实现这两个函数吧！（请参考示例以获得更多信息）</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong> 
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

<pre><strong>输入：</strong> 
["FileSystem","create","create","get","create","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
<strong>输出：</strong> 
[null,true,true,2,false,-1]
<strong>解释：</strong>
FileSystem fileSystem = new FileSystem();

fileSystem.create("/leet", 1); // 返回 true
fileSystem.create("/leet/code", 2); // 返回 true
fileSystem.get("/leet/code"); // 返回 2
fileSystem.create("/c/d", 1); // 返回 false 因为父路径 "/c" 不存在。
fileSystem.get("/c"); // 返回 -1 因为该路径不存在。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>对两个函数的调用次数加起来小于等于 <code>10^4</code></li>
	<li><code>2 <= path.length <= 100</code></li>
	<li><code>1 <= value <= 10^9</code></li>
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