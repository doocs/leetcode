# [555. 分割连接字符串](https://leetcode.cn/problems/split-concatenated-strings)

[English Version](/solution/0500-0599/0555.Split%20Concatenated%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串列表&nbsp;<code>strs</code>，你可以将这些字符串连接成一个循环字符串，对于每个字符串，你可以选择是否翻转它。在所有可能的循环字符串中，你需要分割循环字符串（这将使循环字符串变成一个常规的字符串），然后找到字典序最大的字符串。</p>

<p>具体来说，要找到字典序最大的字符串，你需要经历两个阶段：</p>

<ol>
	<li>将所有字符串连接成一个循环字符串，你可以选择是否翻转某些字符串，并按照给定的顺序连接它们。</li>
	<li>在循环字符串的某个位置分割它，这将使循环字符串从分割点变成一个常规的字符串。</li>
</ol>

<p>你的工作是在所有可能的常规字符串中找到字典序最大的一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> strs = ["abc","xyz"]
<strong>输出:</strong> "zyxcba"
<strong>解释:</strong> 你可以得到循环字符串 "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-"，其中 '-' 代表循环状态。 
答案字符串来自第四个循环字符串， 你可以从中间字符 'a' 分割开然后得到 "zyxcba"。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> strs = ["abc"]
<strong>输出:</strong> "cba"
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 1000</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(strs[i].length) &lt;= 1000</code></li>
	<li><code>strs[i]</code>&nbsp;只包含小写英文字母</li>
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
