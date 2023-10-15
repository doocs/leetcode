# [2902. 和带限制的子多重集合的数目](https://leetcode.cn/problems/count-of-sub-multisets-with-bounded-sum)

[English Version](/solution/2900-2999/2902.Count%20of%20Sub-Multisets%20With%20Bounded%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的非负整数数组&nbsp;<code>nums</code>&nbsp;和两个整数&nbsp;<code>l</code> 和&nbsp;<code>r</code>&nbsp;。</p>

<p>请你返回&nbsp;<code>nums</code>&nbsp;中子多重集合的和在闭区间&nbsp;<code>[l, r]</code>&nbsp;之间的 <strong>子多重集合的数目</strong> 。</p>

<p>由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9 </sup>+ 7</code>&nbsp;取余后返回。</p>

<p><strong>子多重集合</strong> 指的是从数组中选出一些元素构成的 <strong>无序</strong>&nbsp;集合，每个元素 <code>x</code>&nbsp;出现的次数可以是&nbsp;<code>0, 1, ..., occ[x]</code>&nbsp;次，其中&nbsp;<code>occ[x]</code>&nbsp;是元素&nbsp;<code>x</code>&nbsp;在数组中的出现次数。</p>

<p><b>注意：</b></p>

<ul>
	<li>如果两个子多重集合中的元素排序后一模一样，那么它们两个是相同的&nbsp;<strong>子多重集合</strong>&nbsp;。</li>
	<li><strong>空</strong>&nbsp;集合的和是 <code>0</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,2,3], l = 6, r = 6
<b>输出：</b>1
<b>解释：</b>唯一和为 6 的子集合是 {1, 2, 3} 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,4,2,7], l = 1, r = 5
<b>输出：</b>7
<b>解释：</b>和在闭区间 [1, 5] 之间的子多重集合为 {1} ，{2} ，{4} ，{2, 2} ，{1, 2} ，{1, 4} 和 {1, 2, 2} 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,1,3,5,2], l = 3, r = 5
<b>输出：</b>9
<b>解释：</b>和在闭区间 [3, 5] 之间的子多重集合为 {3} ，{5} ，{1, 2} ，{1, 3} ，{2, 2} ，{2, 3} ，{1, 1, 2} ，{1, 1, 3} 和 {1, 2, 2} 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>nums</code> 的和不超过&nbsp;<code>2 * 10<sup>4</sup></code> 。</li>
	<li><code>0 &lt;= l &lt;= r &lt;= 2 * 10<sup>4</sup></code></li>
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
