# [1166. 设计文件系统](https://leetcode-cn.com/problems/design-file-system)

[English Version](/solution/1100-1199/1166.Design%20File%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要设计一个能提供下面两个函数的文件系统：</p>

<ul>
	<li><strong>create(path, value):</strong>&nbsp;创建一个新的路径，并尽可能将值 <code>value</code> 与路径 <code>path</code> 关联，然后返回&nbsp;<code>True</code>。如果路径已经存在或者路径的父路径不存在，则返回&nbsp;<code>False</code>。</li>
	<li><strong>get(path):</strong>&nbsp;返回与路径关联的值。如果路径不存在，则返回&nbsp;<code>-1</code>。</li>
</ul>

<p>&ldquo;路径&rdquo; 是由一个或多个符合下述格式的字符串连接起来形成的：在&nbsp;<code>/</code>&nbsp;后跟着一个或多个小写英文字母。</p>

<p>例如&nbsp;<code>/leetcode</code>&nbsp;和&nbsp;<code>/leetcode/problems</code>&nbsp;都是有效的路径，但空字符串和&nbsp;<code>/</code>&nbsp;不是有效的路径。</p>

<p>好了，接下来就请你来实现这两个函数吧！（请参考示例以获得更多信息）</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong> 
[&quot;FileSystem&quot;,&quot;create&quot;,&quot;get&quot;]
[[],[&quot;/a&quot;,1],[&quot;/a&quot;]]
<strong>输出：</strong> 
[null,true,1]
<strong>解释：</strong> 
FileSystem fileSystem = new FileSystem();

fileSystem.create(&quot;/a&quot;, 1); // 返回 true
fileSystem.get(&quot;/a&quot;); // 返回 1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong> 
[&quot;FileSystem&quot;,&quot;create&quot;,&quot;create&quot;,&quot;get&quot;,&quot;create&quot;,&quot;get&quot;]
[[],[&quot;/leet&quot;,1],[&quot;/leet/code&quot;,2],[&quot;/leet/code&quot;],[&quot;/c/d&quot;,1],[&quot;/c&quot;]]
<strong>输出：</strong> 
[null,true,true,2,false,-1]
<strong>解释：</strong>
FileSystem fileSystem = new FileSystem();

fileSystem.create(&quot;/leet&quot;, 1); // 返回 true
fileSystem.create(&quot;/leet/code&quot;, 2); // 返回 true
fileSystem.get(&quot;/leet/code&quot;); // 返回 2
fileSystem.create(&quot;/c/d&quot;, 1); // 返回 false 因为父路径 &quot;/c&quot; 不存在。
fileSystem.get(&quot;/c&quot;); // 返回 -1 因为该路径不存在。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>对两个函数的调用次数加起来小于等于&nbsp;<code>10^4</code></li>
	<li><code>2 &lt;= path.length &lt;= 100</code></li>
	<li><code>1 &lt;= value &lt;= 10^9</code></li>
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
