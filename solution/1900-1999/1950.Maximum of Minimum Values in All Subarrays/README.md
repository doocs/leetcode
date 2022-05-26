# [1950. 所有子数组最小值中的最大值](https://leetcode.cn/problems/maximum-of-minimum-values-in-all-subarrays)

[English Version](/solution/1900-1999/1950.Maximum%20of%20Minimum%20Values%20in%20All%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，你需要处理 <code>n</code> 个查询。</p>

<p>对于第 <code>i</code> （<code>0 &lt;= i &lt;&nbsp;n</code>）个查询：</p>

<ol>
	<li>你需要先找出 <code>nums</code> 的所有长度为 <code>i + 1</code> 的子数组中的<strong> 最小值</strong> 。</li>
	<li>在这些最小值中找出<strong> 最大值</strong> 作为答案。</li>
</ol>

<p>返回一个 <strong>下标从 0 开始</strong> 的长度为 <code>n</code> 的整数数组 <code>ans</code> ，<code>ans[i]</code> 代表第 <code>i</code> 个查询的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1,2,4]
<strong>输出:</strong> [4,2,1,0]
<strong>解释:</strong>
i = 0:
- 大小为 1 的子数组为 [0], [1], [2], [4]
- 有最大的最小值的子数组是 [4], 它的最小值是 4
i = 1:
- 大小为 2 的子数组为 [0,1], [1,2], [2,4]
- 有最大的最小值的子数组是 [2,4], 它的最小值是 2
i = 2:
- 大小为 3 的子数组为 [0,1,2], [1,2,4]
- 有最大的最小值的子数组是 [1,2,4], 它的最小值是 1
i = 3:
- 大小为 4 的子数组为 [0,1,2,4]
- 有最大的最小值的子数组是 [0,1,2,4], 它的最小值是 0</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>nums = [10,20,50,10]
<strong>输出: </strong>[50,20,10,10]
<strong>解释:</strong>
i = 0: 
- 大小为 1 的子数组为 [10], [20], [50], [10]
- 有最大的最小值的子数组是 [50], 它的最小值是 50
i = 1: 
- 大小为 2 的子数组为 [10,20], [20,50], [50,10]
- 有最大的最小值的子数组是 [20,50], 它的最小值是 20
i = 2: 
- 大小为 3 的子数组为 [10,20,50], [20,50,10]
- 有最大的最小值的子数组是 [10,20,50], 它的最小值是 10
i = 3: 
- 大小为 4 的子数组为 [10,20,50,10]
- 有最大的最小值的子数组是 [10,20,50,10], 它的最小值是 10</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
