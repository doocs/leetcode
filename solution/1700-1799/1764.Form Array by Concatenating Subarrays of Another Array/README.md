# [1764. 通过连接另一个数组的子数组得到一个数组](https://leetcode.cn/problems/form-array-by-concatenating-subarrays-of-another-array)

[English Version](/solution/1700-1799/1764.Form%20Array%20by%20Concatenating%20Subarrays%20of%20Another%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的二维整数数组 <code>groups</code> ，同时给你一个整数数组 <code>nums</code> 。</p>

<p>你是否可以从 <code>nums</code> 中选出 <code>n</code> 个 <strong>不相交</strong> 的子数组，使得第 <code>i</code> 个子数组与 <code>groups[i]</code> （下标从 <strong>0</strong> 开始）完全相同，且如果 <code>i > 0</code> ，那么第 <code>(i-1)</code> 个子数组在 <code>nums</code> 中出现的位置在第 <code>i</code> 个子数组前面。（也就是说，这些子数组在 <code>nums</code> 中出现的顺序需要与 <code>groups</code> 顺序相同）</p>

<p>如果你可以找出这样的 <code>n</code> 个子数组，请你返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>如果不存在下标为 <code>k</code> 的元素 <code>nums[k]</code> 属于不止一个子数组，就称这些子数组是 <strong>不相交</strong> 的。子数组指的是原数组中连续元素组成的一个序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
<b>输出：</b>true
<b>解释：</b>你可以分别在 nums 中选出第 0 个子数组 [1,-1,0,<strong>1,</strong><strong>-1,</strong><strong>-1</strong>,3,-2,0] 和第 1 个子数组 [1,-1,0,1,-1,-1,<strong>3,</strong><strong>-2,0</strong>] 。
这两个子数组是不相交的，因为它们没有任何共同的元素。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
<b>输出：</b>false
<strong>解释：</strong>选择子数组 [<strong>1,2,3,4</strong>,10,-2] 和 [1,2,3,4,<strong>10,-2</strong>] 是不正确的，因为它们出现的顺序与 groups 中顺序不同。
[10,-2] 必须出现在 [1,2,3,4] 之前。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
<b>输出：</b>false
<strong>解释：</strong>选择子数组 [7,7,<strong>1,2,3</strong>,4,7,7] 和 [7,7,1,2,<strong>3,4</strong>,7,7] 是不正确的，因为它们不是不相交子数组。
它们有一个共同的元素 nums[4] （下标从 0 开始）。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>groups.length == n</code></li>
	<li><code>1 <= n <= 10<sup>3</sup></code></li>
	<li><code>1 <= groups[i].length, sum(groups[i].length) <= 10<sup><span style="">3</span></sup></code></li>
	<li><code>1 <= nums.length <= 10<sup>3</sup></code></li>
	<li><code>-10<sup>7</sup> <= groups[i][j], nums[k] <= 10<sup>7</sup></code></li>
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
