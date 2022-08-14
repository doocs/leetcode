# [1794. 统计距离最小的子串对个数](https://leetcode.cn/problems/count-pairs-of-equal-substrings-with-minimum-difference)

[English Version](/solution/1700-1799/1794.Count%20Pairs%20of%20Equal%20Substrings%20With%20Minimum%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>输入数据为两个字符串<code>firstString</code> 和 <code>secondString</code>，两个字符串下标均从0开始，且均只包含小写的英文字符，请计算满足下列要求的下标四元组<code>(i,j,a,b)</code>的个数：</p>

<ul>
	<li><code>0 <= i <= j < firstString.length</code></li>
	<li><code>0 <= a <= b < secondString.length</code></li>
	<li><code>firstString</code>字符串中从<code>i</code>位置到<code>j</code>位置的子串(包括<code>j</code>位置的字符)和<code>secondString</code>字符串从<code>a</code>位置到<code>b</code>位置的子串(包括<code>b</code>位置字符)相等</li>
	<li><code>j-a</code>的数值是所有符合前面三个条件的四元组中可能的最小值</li>
</ul>

<p>返回符合上述 4 个条件的四元组的 <strong>个数</strong> 。</p>

<p> </p>

<p><strong>示例1：</strong></p>

<pre>
<strong>输入：</strong>firstString = "abcd", secondString = "bccda"
<strong>输出：</strong>1
<strong>解释：</strong>(0,0,4,4)是唯一符合条件的四元组且其<code>j-a</code>的数值是最小的.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>firstString = "ab", secondString = "cd"
<strong>输出：</strong>0
<strong>解释：</strong>没有任何一个四元组能满足上述4个要求.
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= firstString.length, secondString.length <= 2 * 10<sup>5</sup></code></li>
	<li>两个输入字符串均只包含小写英文字符.</li>
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
