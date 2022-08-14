# [1910. 删除一个字符串中所有出现的给定子字符串](https://leetcode.cn/problems/remove-all-occurrences-of-a-substring)

[English Version](/solution/1900-1999/1910.Remove%20All%20Occurrences%20of%20a%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>s</code> 和 <code>part</code> ，请你对 <code>s</code> 反复执行以下操作直到 <b>所有</b> 子字符串 <code>part</code> 都被删除：</p>

<ul>
	<li>找到 <code>s</code> 中 <strong>最左边</strong> 的子字符串 <code>part</code> ，并将它从 <code>s</code> 中删除。</li>
</ul>

<p>请你返回从 <code>s</code> 中删除所有 <code>part</code> 子字符串以后得到的剩余字符串。</p>

<p>一个 <strong>子字符串</strong> 是一个字符串中连续的字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "daabcbaabcbc", part = "abc"
<b>输出：</b>"dab"
<b>解释：</b>以下操作按顺序执行：
- s = "da<strong>abc</strong>baabcbc" ，删除下标从 2 开始的 "abc" ，得到 s = "dabaabcbc" 。
- s = "daba<strong>abc</strong>bc" ，删除下标从 4 开始的 "abc" ，得到 s = "dababc" 。
- s = "dab<strong>abc</strong>" ，删除下标从 3 开始的 "abc" ，得到 s = "dab" 。
此时 s 中不再含有子字符串 "abc" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "axxxxyyyyb", part = "xy"
<b>输出：</b>"ab"
<b>解释：</b>以下操作按顺序执行：
- s = "axxx<strong>xy</strong>yyyb" ，删除下标从 4 开始的 "xy" ，得到 s = "axxxyyyb" 。
- s = "axx<strong>xy</strong>yyb" ，删除下标从 3 开始的 "xy" ，得到 s = "axxyyb" 。
- s = "ax<strong>xy</strong>yb" ，删除下标从 2 开始的 "xy" ，得到 s = "axyb" 。
- s = "a<strong>xy</strong>b" ，删除下标从 1 开始的 "xy" ，得到 s = "ab" 。
此时 s 中不再含有子字符串 "xy" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>1 &lt;= part.length &lt;= 1000</code></li>
	<li><code>s</code>​​​​​​ 和 <code>part</code> 只包小写英文字母。</li>
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
