# [2116. 判断一个括号字符串是否有效](https://leetcode.cn/problems/check-if-a-parentheses-string-can-be-valid)

[English Version](/solution/2100-2199/2116.Check%20if%20a%20Parentheses%20String%20Can%20Be%20Valid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个括号字符串是只由&nbsp;<code>'('</code> 和&nbsp;<code>')'</code>&nbsp;组成的&nbsp;<strong>非空</strong>&nbsp;字符串。如果一个字符串满足下面 <b>任意</b>&nbsp;一个条件，那么它就是有效的：</p>

<ul>
	<li>字符串为&nbsp;<code>()</code>.</li>
	<li>它可以表示为 <code>AB</code><span style="">（</span><code>A</code>&nbsp;与&nbsp;<code>B</code>&nbsp;连接），其中<code>A</code> 和&nbsp;<code>B</code>&nbsp;都是有效括号字符串。</li>
	<li>它可以表示为&nbsp;<code>(A)</code>&nbsp;，其中&nbsp;<code>A</code>&nbsp;是一个有效括号字符串。</li>
</ul>

<p>给你一个括号字符串&nbsp;<code>s</code>&nbsp;和一个字符串&nbsp;<code>locked</code>&nbsp;，两者长度都为&nbsp;<code>n</code>&nbsp;。<code>locked</code>&nbsp;是一个二进制字符串，只包含&nbsp;<code>'0'</code>&nbsp;和&nbsp;<code>'1'</code>&nbsp;。对于&nbsp;<code>locked</code>&nbsp;中&nbsp;<strong>每一个</strong>&nbsp;下标&nbsp;<code>i</code> ：</p>

<ul>
	<li>如果&nbsp;<code>locked[i]</code>&nbsp;是&nbsp;<code>'1'</code>&nbsp;，你 <strong>不能</strong>&nbsp;改变&nbsp;<code>s[i]</code>&nbsp;。</li>
	<li>如果&nbsp;<code>locked[i]</code>&nbsp;是&nbsp;<code>'0'</code>&nbsp;，你&nbsp;<strong>可以</strong>&nbsp;将&nbsp;<code>s[i]</code>&nbsp;变为&nbsp;<code>'('</code>&nbsp;或者&nbsp;<code>')'</code>&nbsp;。</li>
</ul>

<p>如果你可以将 <code>s</code>&nbsp;变为有效括号字符串，请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2116.Check%20if%20a%20Parentheses%20String%20Can%20Be%20Valid/images/eg1.png" style="width: 311px; height: 101px;" /></p>

<pre>
<b>输入：</b>s = "))()))", locked = "010100"
<b>输出：</b>true
<b>解释：</b>locked[1] == '1' 和 locked[3] == '1' ，所以我们无法改变 s[1] 或者 s[3] 。
我们可以将 s[0] 和 s[4] 变为 '(' ，不改变 s[2] 和 s[5] ，使 s 变为有效字符串。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "()()", locked = "0000"
<b>输出：</b>true
<b>解释：</b>我们不需要做任何改变，因为 s 已经是有效字符串了。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = ")", locked = "0"
<b>输出：</b>false
<b>解释：</b>locked 允许改变 s[0] 。
但无论将 s[0] 变为 '(' 或者 ')' 都无法使 s 变为有效字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == s.length == locked.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;要么是&nbsp;<code>'('</code>&nbsp;要么是&nbsp;<code>')'</code>&nbsp;。</li>
	<li><code>locked[i]</code> 要么是&nbsp;<code>'0'</code>&nbsp;要么是&nbsp;<code>'1'</code> 。</li>
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
