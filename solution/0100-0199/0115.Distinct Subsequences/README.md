# [115. 不同的子序列](https://leetcode-cn.com/problems/distinct-subsequences)

[English Version](/solution/0100-0199/0115.Distinct%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code><strong> </strong>和一个字符串 <code>t</code> ，计算在 <code>s</code> 的子序列中 <code>t</code> 出现的个数。</p>

<p>字符串的一个 <strong>子序列</strong> 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，<code>"ACE"</code> 是 <code>"ABCDE"</code> 的一个子序列，而 <code>"AEC"</code> 不是）</p>

<p>题目数据保证答案符合 32 位带符号整数范围。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "rabbbit", t = "rabbit"<code>
<strong>输出</strong></code><strong>：</strong><code>3
</code><strong>解释：</strong>
如下图所示, 有 3 种可以从 s 中得到 <code>"rabbit" 的方案</code>。
(上箭头符号 ^ 表示选取的字母)
<code>rabbbit</code>
^^^^ ^^
<code>rabbbit</code>
^^ ^^^^
<code>rabbbit</code>
^^^ ^^^
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "babgbag", t = "bag"
<code><strong>输出</strong></code><strong>：</strong><code>5
</code><strong>解释：</strong>
如下图所示, 有 5 种可以从 s 中得到 <code>"bag" 的方案</code>。 
(上箭头符号 ^ 表示选取的字母)
<code>babgbag</code>
^^ ^
<code>babgbag</code>
^^    ^
<code>babgbag</code>
^    ^^
<code>babgbag</code>
  ^  ^^
<code>babgbag</code>
    ^^^</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= s.length, t.length <= 1000</code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
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
