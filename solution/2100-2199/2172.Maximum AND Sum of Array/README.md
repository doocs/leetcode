# [2172. 数组的最大与和](https://leetcode.cn/problems/maximum-and-sum-of-array)

[English Version](/solution/2100-2199/2172.Maximum%20AND%20Sum%20of%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>numSlots</code>&nbsp;，满足<code>2 * numSlots &gt;= n</code>&nbsp;。总共有&nbsp;<code>numSlots</code>&nbsp;个篮子，编号为&nbsp;<code>1</code>&nbsp;到&nbsp;<code>numSlots</code>&nbsp;。</p>

<p>你需要把所有&nbsp;<code>n</code>&nbsp;个整数分到这些篮子中，且每个篮子 <strong>至多</strong>&nbsp;有 2 个整数。一种分配方案的 <strong>与和</strong>&nbsp;定义为每个数与它所在篮子编号的 <strong>按位与运算</strong>&nbsp;结果之和。</p>

<ul>
	<li>比方说，将数字&nbsp;<code>[1, 3]</code>&nbsp;放入篮子&nbsp;<strong><em><code>1</code></em></strong>&nbsp;中，<code>[4, 6]</code> 放入篮子&nbsp;<strong><em><code>2</code></em></strong>&nbsp;中，这个方案的与和为&nbsp;<code>(1 AND <strong><em>1</em></strong>) + (3 AND <strong><em>1</em></strong>) + (4 AND <em><strong>2</strong></em>) + (6 AND <em><strong>2</strong></em>) = 1 + 1 + 0 + 2 = 4</code>&nbsp;。</li>
</ul>

<p>请你返回将 <code>nums</code>&nbsp;中所有数放入<em>&nbsp;</em><code>numSlots</code>&nbsp;个篮子中的最大与和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,4,5,6], numSlots = 3
<b>输出：</b>9
<b>解释：</b>一个可行的方案是 [1, 4] 放入篮子 <em><strong>1</strong></em>&nbsp;中，[2, 6] 放入篮子 <strong><em>2</em></strong>&nbsp;中，[3, 5] 放入篮子 <strong><em>3</em></strong> 中。
最大与和为 (1 AND <strong><em>1</em></strong>) + (4 AND <strong><em>1</em></strong>) + (2 AND <strong><em>2</em></strong>) + (6 AND <strong><em>2</em></strong>) + (3 AND <strong><em>3</em></strong>) + (5 AND <em><strong>3</strong></em>) = 1 + 0 + 2 + 2 + 3 + 1 = 9 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,3,10,4,7,1], numSlots = 9
<b>输出：</b>24
<b>解释：</b>一个可行的方案是 [1, 1] 放入篮子 <em><strong>1</strong></em> 中，[3] 放入篮子 <em><strong>3</strong></em> 中，[4] 放入篮子 <strong><em>4</em></strong> 中，[7] 放入篮子 <strong><em>7</em></strong> 中，[10] 放入篮子 <strong><em>9</em></strong>&nbsp;中。
最大与和为 (1 AND <strong><em>1</em></strong>) + (1 AND <strong><em>1</em></strong>) + (3 AND <strong><em>3</em></strong>) + (4 AND <strong><em>4</em></strong>) + (7 AND <strong><em>7</em></strong>) + (10 AND <strong><em>9</em></strong>) = 1 + 1 + 3 + 4 + 7 + 8 = 24 。
注意，篮子 2 ，5 ，6 和 8 是空的，这是允许的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= numSlots &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 2 * numSlots</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 15</code></li>
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

```ts

```

### **...**

```

```

<!-- tabs:end -->
