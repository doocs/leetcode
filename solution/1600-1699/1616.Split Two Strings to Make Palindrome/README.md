# [1616. 分割两个字符串得到回文串](https://leetcode.cn/problems/split-two-strings-to-make-palindrome)

[English Version](/solution/1600-1699/1616.Split%20Two%20Strings%20to%20Make%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>a</code> 和&nbsp;<code>b</code>&nbsp;，它们长度相同。请你选择一个下标，将两个字符串都在&nbsp;<strong>相同的下标 </strong>分割开。由&nbsp;<code>a</code>&nbsp;可以得到两个字符串：&nbsp;<code>a<sub>prefix</sub></code>&nbsp;和&nbsp;<code>a<sub>suffix</sub></code>&nbsp;，满足&nbsp;<code>a = a<sub>prefix</sub> + a<sub>suffix</sub></code><sub>&nbsp;</sub>，同理，由&nbsp;<code>b</code> 可以得到两个字符串&nbsp;<code>b<sub>prefix</sub></code> 和&nbsp;<code>b<sub>suffix</sub></code>&nbsp;，满足&nbsp;<code>b = b<sub>prefix</sub> + b<sub>suffix</sub></code>&nbsp;。请你判断&nbsp;<code>a<sub>prefix</sub> + b<sub>suffix</sub></code> 或者&nbsp;<code>b<sub>prefix</sub> + a<sub>suffix</sub></code>&nbsp;能否构成回文串。</p>

<p>当你将一个字符串&nbsp;<code>s</code>&nbsp;分割成&nbsp;<code>s<sub>prefix</sub></code> 和&nbsp;<code>s<sub>suffix</sub></code>&nbsp;时，&nbsp;<code>s<sub>suffix</sub></code> 或者&nbsp;<code>s<sub>prefix</sub></code> 可以为空。比方说，&nbsp;<code>s = "abc"</code>&nbsp;那么&nbsp;<code>"" + "abc"</code>&nbsp;，&nbsp;<code>"a" + "bc"&nbsp;</code>，&nbsp;<code>"ab" + "c"</code>&nbsp;和&nbsp;<code>"abc" + ""</code>&nbsp;都是合法分割。</p>

<p>如果 <strong>能构成回文字符串</strong> ，那么请返回&nbsp;<code>true</code>，否则返回<em>&nbsp;</em><code>false</code>&nbsp;。</p>

<p><strong>注意</strong>，&nbsp;<code>x + y</code>&nbsp;表示连接字符串&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>a = "x", b = "y"
<b>输出：</b>true
<b>解释：</b>如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割：
a<sub>prefix</sub> = "", a<sub>suffix</sub> = "x"
b<sub>prefix</sub> = "", b<sub>suffix</sub> = "y"
那么 a<sub>prefix</sub> + b<sub>suffix</sub> = "" + "y" = "y" 是回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = "abdef", b = "fecab"
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>a = "ulacfd", b = "jizalu"
<b>输出：</b>true
<b>解释：</b>在下标为 3 处分割：
a<sub>prefix</sub> = "ula", a<sub>suffix</sub> = "cfd"
b<sub>prefix</sub> = "jiz", b<sub>suffix</sub> = "alu"
那么 a<sub>prefix</sub> + b<sub>suffix</sub> = "ula" + "alu" = "ulaalu" 是回文串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a.length == b.length</code></li>
	<li><code>a</code> 和&nbsp;<code>b</code>&nbsp;都只包含小写英文字母</li>
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
