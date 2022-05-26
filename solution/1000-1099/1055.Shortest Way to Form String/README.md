# [1055. 形成字符串的最短路径](https://leetcode.cn/problems/shortest-way-to-form-string)

[English Version](/solution/1000-1099/1055.Shortest%20Way%20to%20Form%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对于任何字符串，我们可以通过删除其中一些字符（也可能不删除）来构造该字符串的 <strong>子序列</strong> 。(例如，<code>“ace”</code>&nbsp;是 <code>“abcde”</code> 的子序列，而 <code>“aec”</code> 不是)。</p>

<p>给定源字符串&nbsp;<code>source</code> 和目标字符串&nbsp;<code>target</code>，返回 <em>源字符串&nbsp;<code>source</code>&nbsp;中能通过串联形成目标字符串&nbsp;</em><code>target</code>&nbsp;<em>的 <strong>子序列</strong> 的最小数量&nbsp;</em>。如果无法通过串联源字符串中的子序列来构造目标字符串，则返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>source = "abc", target = "abcbc"
<strong>输出：</strong>2
<strong>解释：</strong>目标字符串 "abcbc" 可以由 "abc" 和 "bc" 形成，它们都是源字符串 "abc" 的子序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>source = "abc", target = "acdbc"
<strong>输出：</strong>-1
<strong>解释：</strong>由于目标字符串中包含字符 "d"，所以无法由源字符串的子序列构建目标字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>source = "xyz", target = "xzyxz"
<strong>输出：</strong>3
<strong>解释：</strong>目标字符串可以按如下方式构建： "xz" + "y" + "xz"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= source.length, target.length &lt;= 1000</code></li>
	<li><code>source</code> 和&nbsp;<code>target</code>&nbsp;仅包含英文小写字母。</li>
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
