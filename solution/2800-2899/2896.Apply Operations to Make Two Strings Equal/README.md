# [2896. 执行操作使两个字符串相等](https://leetcode.cn/problems/apply-operations-to-make-two-strings-equal)

[English Version](/solution/2800-2899/2896.Apply%20Operations%20to%20Make%20Two%20Strings%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的二进制字符串&nbsp;<code>s1</code> 和&nbsp;<code>s2</code>&nbsp;，两个字符串的长度都是&nbsp;<code>n</code>&nbsp;，再给你一个正整数&nbsp;<code>x</code>&nbsp;。</p>

<p>你可以对字符串 <code>s1</code>&nbsp;执行以下操作 <strong>任意次</strong>&nbsp;：</p>

<ul>
	<li>选择两个下标&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;，将&nbsp;<code>s1[i]</code> 和&nbsp;<code>s1[j]</code>&nbsp;都反转，操作的代价为&nbsp;<code>x</code>&nbsp;。</li>
	<li>选择满足 <code>i &lt; n - 1</code>&nbsp;的下标&nbsp;<code>i</code>&nbsp;，反转&nbsp;<code>s1[i]</code> 和&nbsp;<code>s1[i + 1]</code>&nbsp;，操作的代价为&nbsp;<code>1</code>&nbsp;。</li>
</ul>

<p>请你返回使字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;相等的&nbsp;<strong>最小</strong>&nbsp;操作代价之和，如果无法让二者相等，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><strong>注意</strong>&nbsp;，反转字符的意思是将&nbsp;<code>0</code>&nbsp;变成&nbsp;<code>1</code>&nbsp;，或者 <code>1</code>&nbsp;变成 <code>0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s1 = "1100011000", s2 = "0101001010", x = 2
<b>输出：</b>4
<b>解释：</b>我们可以执行以下操作：
- 选择 i = 3 执行第二个操作。结果字符串是 s1 = "110<em><strong>11</strong></em>11000" 。
- 选择 i = 4 执行第二个操作。结果字符串是 s1 = "1101<em><strong>00</strong></em>1000" 。
- 选择 i = 0 和 j = 8 ，执行第一个操作。结果字符串是 s1 = "<em><strong>0</strong></em>1010010<em><strong>1</strong></em>0" = s2 。
总代价是 1 + 1 + 2 = 4 。这是最小代价和。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s1 = "10110", s2 = "00011", x = 4
<b>输出：</b>-1
<b>解释：</b>无法使两个字符串相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == s1.length == s2.length</code></li>
	<li><code>1 &lt;= n, x &lt;= 500</code></li>
	<li><code>s1</code> 和&nbsp;<code>s2</code>&nbsp;只包含字符&nbsp;<code>'0'</code> 和&nbsp;<code>'1'</code> 。</li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
