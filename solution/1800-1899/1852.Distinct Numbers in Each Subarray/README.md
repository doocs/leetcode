# [1852. 每个子数组的数字种类数](https://leetcode.cn/problems/distinct-numbers-in-each-subarray)

[English Version](/solution/1800-1899/1852.Distinct%20Numbers%20in%20Each%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>与一个整数 <code>k</code>，请你构造一个长度 <code>n-k+1</code> 的数组 <code>ans</code>，这个数组第<code>i</code>个元素 <code>ans[i]</code> 是每个长度为k的子数组 <code>nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]]</code>中数字的种类数。</p>

<p>返回这个数组 <code>ans</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,2,2,1,3], k = 3
<strong>输出:</strong> [3,2,2,2,3]
<b>解释</b>：每个子数组的数字种类计算方法如下：
- nums[0:2] = [1,2,3] 所以 ans[0] = 3
- nums[1:3] = [2,3,2] 所以 ans[1] = 2
- nums[2:4] = [3,2,2] 所以 ans[2] = 2
- nums[3:5] = [2,2,1] 所以 ans[3] = 2
- nums[4:6] = [2,1,3] 所以 ans[4] = 3
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,1,1,1,2,3,4], k = 4
<strong>输出:</strong> [1,2,3,4]
<strong>解释: </strong>每个子数组的数字种类计算方法如下：
- nums[0:3] = [1,1,1,1] 所以 ans[0] = 1
- nums[1:4] = [1,1,1,2] 所以 ans[1] = 2
- nums[2:5] = [1,1,2,3] 所以 ans[2] = 3
- nums[3:6] = [1,2,3,4] 所以 ans[3] = 4
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
