# [1520. 最多的不重叠子字符串](https://leetcode.cn/problems/maximum-number-of-non-overlapping-substrings)

[English Version](/solution/1500-1599/1520.Maximum%20Number%20of%20Non-Overlapping%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个只包含小写字母的字符串&nbsp;<code>s</code>&nbsp;，你需要找到 <code>s</code>&nbsp;中最多数目的非空子字符串，满足如下条件：</p>

<ol>
	<li>这些字符串之间互不重叠，也就是说对于任意两个子字符串&nbsp;<code>s[i..j]</code> 和&nbsp;<code>s[x..y]</code>&nbsp;，要么&nbsp;<code>j &lt; x</code>&nbsp;要么&nbsp;<code>i &gt; y</code>&nbsp;。</li>
	<li>如果一个子字符串包含字符&nbsp;<code>char</code> ，那么&nbsp;<code>s</code>&nbsp;中所有&nbsp;<code>char</code> 字符都应该在这个子字符串中。</li>
</ol>

<p>请你找到满足上述条件的最多子字符串数目。如果有多个解法有相同的子字符串数目，请返回这些子字符串总长度最小的一个解。可以证明最小总长度解是唯一的。</p>

<p>请注意，你可以以 <strong>任意</strong>&nbsp;顺序返回最优解的子字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "adefaddaccc"
<strong>输出：</strong>["e","f","ccc"]
<strong>解释：</strong>下面为所有满足第二个条件的子字符串：
[
&nbsp; "adefaddaccc"
&nbsp; "adefadda",
&nbsp; "ef",
&nbsp; "e",
  "f",
&nbsp; "ccc",
]
如果我们选择第一个字符串，那么我们无法再选择其他任何字符串，所以答案为 1 。如果我们选择 "adefadda" ，剩下子字符串中我们只可以选择 "ccc" ，它是唯一不重叠的子字符串，所以答案为 2 。同时我们可以发现，选择 "ef" 不是最优的，因为它可以被拆分成 2 个子字符串。所以最优解是选择 ["e","f","ccc"] ，答案为 3 。不存在别的相同数目子字符串解。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abbaccd"
<strong>输出：</strong>["d","bb","cc"]
<strong>解释：</strong>注意到解 ["d","abba","cc"] 答案也为 3 ，但它不是最优解，因为它的总长度更长。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
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
