# [2468. 根据限制分割消息](https://leetcode.cn/problems/split-message-based-on-limit)

[English Version](/solution/2400-2499/2468.Split%20Message%20Based%20on%20Limit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>message</code>&nbsp;和一个正整数&nbsp;<code>limit</code>&nbsp;。</p>

<p>你需要根据 <code>limit</code>&nbsp;将&nbsp;<code>message</code> <strong>分割</strong>&nbsp;成一个或多个 <strong>部分</strong>&nbsp;。每个部分的结尾都是&nbsp;<code>"&lt;a/b&gt;"</code>&nbsp;，其中&nbsp;<code>"b"</code>&nbsp;用分割出来的总数 <b>替换</b>，&nbsp;<code>"a"</code>&nbsp;用当前部分所在的编号 <strong>替换</strong>&nbsp;，编号从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>b</code>&nbsp;依次编号。除此以外，除了最后一部分长度 <strong>小于等于</strong>&nbsp;<code>limit</code>&nbsp;以外，其他每一部分（包括结尾部分）的长度都应该&nbsp;<strong>等于</strong>&nbsp;<code>limit</code>&nbsp;。</p>

<p>你需要确保分割后的结果数组，删掉每部分的结尾并<strong>&nbsp;按顺序&nbsp;</strong>连起来后，能够得到&nbsp;<code>message</code>&nbsp;。同时，结果数组越短越好。</p>

<p>请你返回<em>&nbsp;</em><code>message</code>&nbsp; 分割后得到的结果数组。如果无法按要求分割&nbsp;<code>message</code>&nbsp;，返回一个空数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>message = "this is really a very awesome message", limit = 9
<b>输出：</b>["thi&lt;1/14&gt;","s i&lt;2/14&gt;","s r&lt;3/14&gt;","eal&lt;4/14&gt;","ly &lt;5/14&gt;","a v&lt;6/14&gt;","ery&lt;7/14&gt;"," aw&lt;8/14&gt;","eso&lt;9/14&gt;","me&lt;10/14&gt;"," m&lt;11/14&gt;","es&lt;12/14&gt;","sa&lt;13/14&gt;","ge&lt;14/14&gt;"]
<strong>解释：</strong>
前面 9 个部分分别从 message 中得到 3 个字符。
接下来的 5 个部分分别从 message 中得到 2 个字符。
这个例子中，包含最后一个部分在内，每个部分的长度都为 9 。
可以证明没有办法分割成少于 14 个部分。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>message = "short message", limit = 15
<b>输出：</b>["short mess&lt;1/2&gt;","age&lt;2/2&gt;"]
<strong>解释：</strong>
在给定限制下，字符串可以分成两个部分：
- 第一个部分包含 10 个字符，长度为 15 。
- 第二个部分包含 3 个字符，长度为 8 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= message.length &lt;= 10<sup>4</sup></code></li>
	<li><code>message</code>&nbsp;只包含小写英文字母和&nbsp;<code>' '</code>&nbsp;。</li>
	<li><code>1 &lt;= limit &lt;= 10<sup>4</sup></code></li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
