# [848. 字母移位](https://leetcode.cn/problems/shifting-letters)

[English Version](/solution/0800-0899/0848.Shifting%20Letters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个由小写字母组成的字符串 <code>s</code>，和一个长度相同的整数数组 <code>shifts</code>。</p>

<p>我们将字母表中的下一个字母称为原字母的 <em>移位</em>&nbsp;<code>shift()</code>&nbsp;（由于字母表是环绕的， <code>'z'</code>&nbsp;将会变成&nbsp;<code>'a'</code>）。</p>

<ul>
	<li>例如，<code>shift('a') = 'b'<font color="#333333"><font face="Helvetica Neue, Helvetica, Arial, sans-serif"><span style="font-size:14px"><span style="background-color:#ffffff">,&nbsp;</span></span></font></font></code><code>shift('t') = 'u'</code>,&nbsp;以及&nbsp;<code>shift('z') = 'a'</code>。</li>
</ul>

<p>对于每个&nbsp;<code>shifts[i] = x</code>&nbsp;， 我们会将 <code>s</code>&nbsp;中的前&nbsp;<code>i + 1</code>&nbsp;个字母移位&nbsp;<code>x</code>&nbsp;次。</p>

<p>返回 <em>将所有这些移位都应用到 <code>s</code> 后最终得到的字符串</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abc", shifts = [3,5,9]
<strong>输出：</strong>"rpl"
<strong>解释： </strong>
我们以 "abc" 开始。
将 S 中的第 1 个字母移位 3 次后，我们得到 "dbc"。
再将 S 中的前 2 个字母移位 5 次后，我们得到 "igc"。
最后将 S 中的这 3 个字母移位 9 次后，我们得到答案 "rpl"。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "aaa", shifts = [1,2,3]
<strong>输出:</strong> "gfd"
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;由小写英文字母组成</li>
	<li><code>shifts.length == s.length</code></li>
	<li><code>0 &lt;= shifts[i] &lt;= 10<sup>9</sup></code></li>
</ul>
<span style="display:block"><span style="height:0px"><span style="position:absolute">​​​​​​</span></span></span>

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
