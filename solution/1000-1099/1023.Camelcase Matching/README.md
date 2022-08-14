# [1023. 驼峰式匹配](https://leetcode.cn/problems/camelcase-matching)

[English Version](/solution/1000-1099/1023.Camelcase%20Matching/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果我们可以将<strong>小写字母</strong>插入模式串&nbsp;<code>pattern</code>&nbsp;得到待查询项&nbsp;<code>query</code>，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）</p>

<p>给定待查询列表&nbsp;<code>queries</code>，和模式串&nbsp;<code>pattern</code>，返回由布尔值组成的答案列表&nbsp;<code>answer</code>。只有在待查项&nbsp;<code>queries[i]</code> 与模式串&nbsp;<code>pattern</code> 匹配时，&nbsp;<code>answer[i]</code>&nbsp;才为 <code>true</code>，否则为 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>queries = [&quot;FooBar&quot;,&quot;FooBarTest&quot;,&quot;FootBall&quot;,&quot;FrameBuffer&quot;,&quot;ForceFeedBack&quot;], pattern = &quot;FB&quot;
<strong>输出：</strong>[true,false,true,true,false]
<strong>示例：</strong>
&quot;FooBar&quot; 可以这样生成：&quot;F&quot; + &quot;oo&quot; + &quot;B&quot; + &quot;ar&quot;。
&quot;FootBall&quot; 可以这样生成：&quot;F&quot; + &quot;oot&quot; + &quot;B&quot; + &quot;all&quot;.
&quot;FrameBuffer&quot; 可以这样生成：&quot;F&quot; + &quot;rame&quot; + &quot;B&quot; + &quot;uffer&quot;.</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>queries = [&quot;FooBar&quot;,&quot;FooBarTest&quot;,&quot;FootBall&quot;,&quot;FrameBuffer&quot;,&quot;ForceFeedBack&quot;], pattern = &quot;FoBa&quot;
<strong>输出：</strong>[true,false,true,false,false]
<strong>解释：</strong>
&quot;FooBar&quot; 可以这样生成：&quot;Fo&quot; + &quot;o&quot; + &quot;Ba&quot; + &quot;r&quot;.
&quot;FootBall&quot; 可以这样生成：&quot;Fo&quot; + &quot;ot&quot; + &quot;Ba&quot; + &quot;ll&quot;.
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输出：</strong>queries = [&quot;FooBar&quot;,&quot;FooBarTest&quot;,&quot;FootBall&quot;,&quot;FrameBuffer&quot;,&quot;ForceFeedBack&quot;], pattern = &quot;FoBaT&quot;
<strong>输入：</strong>[false,true,false,false,false]
<strong>解释： </strong>
&quot;FooBarTest&quot; 可以这样生成：&quot;Fo&quot; + &quot;o&quot; + &quot;Ba&quot; + &quot;r&quot; + &quot;T&quot; + &quot;est&quot;.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= queries.length &lt;= 100</code></li>
	<li><code>1 &lt;= queries[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= pattern.length &lt;= 100</code></li>
	<li>所有字符串都仅由大写和小写英文字母组成。</li>
</ol>

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
