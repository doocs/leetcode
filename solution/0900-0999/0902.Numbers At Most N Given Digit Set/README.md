# [902. 最大为 N 的数字组合](https://leetcode.cn/problems/numbers-at-most-n-given-digit-set)

[English Version](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个按&nbsp;<strong>非递减顺序</strong>&nbsp;排列的数字数组<meta charset="UTF-8" />&nbsp;<code>digits</code>&nbsp;。你可以用任意次数&nbsp;<code>digits[i]</code>&nbsp;来写的数字。例如，如果<meta charset="UTF-8" />&nbsp;<code>digits = ['1','3','5']</code>，我们可以写数字，如<meta charset="UTF-8" />&nbsp;<code>'13'</code>,&nbsp;<code>'551'</code>, 和&nbsp;<code>'1351315'</code>。</p>

<p>返回 <em>可以生成的小于或等于给定整数 <code>n</code> 的正整数的个数</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>digits = ["1","3","5","7"], n = 100
<strong>输出：</strong>20
<strong>解释：</strong>
可写出的 20 个数字是：
1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>digits = ["1","4","9"], n = 1000000000
<strong>输出：</strong>29523
<strong>解释：</strong>
我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
81 个四位数字，243 个五位数字，729 个六位数字，
2187 个七位数字，6561 个八位数字和 19683 个九位数字。
总共，可以使用D中的数字写出 29523 个整数。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong>digits = ["7"], n = 8
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= digits.length &lt;= 9</code></li>
	<li><code>digits[i].length == 1</code></li>
	<li><code>digits[i]</code>&nbsp;是从&nbsp;<code>'1'</code>&nbsp;到&nbsp;<code>'9'</code> 的数</li>
	<li><code>digits</code>&nbsp;中的所有值都 <strong>不同</strong>&nbsp;</li>
	<li><code>digits</code>&nbsp;按&nbsp;<strong>非递减顺序</strong>&nbsp;排列</li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
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
