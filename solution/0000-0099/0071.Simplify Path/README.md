# [71. 简化路径](https://leetcode-cn.com/problems/simplify-path)

[English Version](/solution/0000-0099/0071.Simplify%20Path/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>以 Unix 风格给出一个文件的<strong>绝对路径</strong>，你需要简化它。或者换句话说，将其转换为规范路径。</p>

<p>在 Unix 风格的文件系统中，一个点（<code>.</code>）表示当前目录本身；此外，两个点 （<code>..</code>）&nbsp;表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：<a href="https://blog.csdn.net/u011327334/article/details/50355600" target="_blank">Linux / Unix中的绝对路径 vs 相对路径</a></p>

<p>请注意，返回的规范路径必须始终以斜杠 <code>/</code> 开头，并且两个目录名之间必须只有一个斜杠 <code>/</code>。最后一个目录名（如果存在）<strong>不能</strong>以 <code>/</code> 结尾。此外，规范路径必须是表示绝对路径的<strong>最短</strong>字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：&quot;</strong>/home/&quot;
<strong>输出：&quot;</strong>/home&quot;
<strong>解释：</strong>注意，最后一个目录名后面没有斜杠。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：&quot;</strong>/../&quot;
<strong>输出：&quot;</strong>/&quot;
<strong>解释：</strong>从根目录向上一级是不可行的，因为根是你可以到达的最高级。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：&quot;</strong>/home//foo/&quot;
<strong>输出：&quot;</strong>/home/foo&quot;
<strong>解释：</strong>在规范路径中，多个连续斜杠需要用一个斜杠替换。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：&quot;</strong>/a/./b/../../c/&quot;
<strong>输出：&quot;</strong>/c&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：&quot;</strong>/a/../../b/../c//.//&quot;
<strong>输出：&quot;</strong>/c&quot;
</pre>

<p><strong>示例 6：</strong></p>

<pre><strong>输入：&quot;</strong>/a//b////c/d//././/..&quot;
<strong>输出：&quot;</strong>/a/b/c&quot;</pre>



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