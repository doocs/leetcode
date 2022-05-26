# [1653. 使字符串平衡的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced)

[English Version](/solution/1600-1699/1653.Minimum%20Deletions%20to%20Make%20String%20Balanced/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，它仅包含字符 <code>'a'</code> 和 <code>'b'</code>​​​​ 。</p>

<p>你可以删除 <code>s</code> 中任意数目的字符，使得 <code>s</code> <strong>平衡</strong> 。我们称 <code>s</code> <strong>平衡的</strong> 当不存在下标对 <code>(i,j)</code> 满足 <code>i < j</code> 且 <code>s[i] = 'b'</code> 同时 <code>s[j]= 'a'</code> 。</p>

<p>请你返回使 <code>s</code> <strong>平衡</strong> 的 <strong>最少</strong> 删除次数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aababbab"
<b>输出：</b>2
<b>解释：</b>你可以选择以下任意一种方案：
下标从 0 开始，删除第 2 和第 6 个字符（"aa<strong>b</strong>abb<strong>a</strong>b" -> "aaabbb"），
下标从 0 开始，删除第 3 和第 6 个字符（"aab<strong>a</strong>bb<strong>a</strong>b" -> "aabbbb"）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "bbaaaaabb"
<b>输出：</b>2
<b>解释：</b>唯一的最优解是删除最前面两个字符。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 要么是 <code>'a'</code> 要么是 <code>'b'</code>​<strong> </strong>。​</li>
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
