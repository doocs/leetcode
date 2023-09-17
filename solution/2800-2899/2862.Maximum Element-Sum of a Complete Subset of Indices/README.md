# [2862. 完全子集的最大元素和](https://leetcode.cn/problems/maximum-element-sum-of-a-complete-subset-of-indices)

[English Version](/solution/2800-2899/2862.Maximum%20Element-Sum%20of%20a%20Complete%20Subset%20of%20Indices/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>1</strong> 开始、由 <code>n</code> 个整数组成的数组。</p>

<p>如果一组数字中每对元素的乘积都是一个完全平方数，则称这组数字是一个 <strong>完全集</strong> 。</p>

<p>下标集 <code>{1, 2, ..., n}</code> 的子集可以表示为 <code>{i<sub>1</sub>, i<sub>2</sub>, ..., i<sub>k</sub>}</code>，我们定义对应该子集的 <strong>元素和</strong> 为 <code>nums[i<sub>1</sub>] + nums[i<sub>2</sub>] + ... + nums[i<sub>k</sub>]</code> 。</p>

<p>返回下标集&nbsp;<code>{1, 2, ..., n}</code> 的 <strong>完全子集</strong> 所能取到的 <strong>最大元素和</strong> 。</p>

<p>完全平方数是指可以表示为一个整数和其自身相乘的数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [8,7,3,5,7,2,4,9]
<strong>输出：</strong>16
<strong>解释：</strong>除了由单个下标组成的子集之外，还有两个下标集的完全子集：{1,4} 和 {2,8} 。
与下标 1 和 4 对应的元素和等于 nums[1] + nums[4] = 8 + 5 = 13 。
与下标 2 和 8 对应的元素和等于 nums[2] + nums[8] = 7 + 9 = 16 。
因此，下标集的完全子集可以取到的最大元素和为 16 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,10,3,10,1,13,7,9,4]
<strong>输出：</strong>19
<strong>解释：</strong>除了由单个下标组成的子集之外，还有四个下标集的完全子集：{1,4}、{1,9}、{2,8}、{4,9} 和 {1,4,9} 。 
与下标 1 和 4 对应的元素和等于 nums[1] + nums[4] = 5 + 10 = 15 。 
与下标 1 和 9 对应的元素和等于 nums[1] + nums[9] = 5 + 4 = 9 。 
与下标 2 和 8 对应的元素和等于 nums[2] + nums[8] = 10 + 9 = 19 。
与下标 4 和 9 对应的元素和等于 nums[4] + nums[9] = 10 + 4 = 14 。 
与下标 1、4 和 9 对应的元素和等于 nums[1] + nums[4] + nums[9] = 5 + 10 + 4 = 19 。 
因此，下标集的完全子集可以取到的最大元素和为 19 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
