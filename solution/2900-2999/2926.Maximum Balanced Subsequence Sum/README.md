# [2926. 平衡子序列的最大和](https://leetcode.cn/problems/maximum-balanced-subsequence-sum)

[English Version](/solution/2900-2999/2926.Maximum%20Balanced%20Subsequence%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p><code>nums</code>&nbsp;一个长度为 <code>k</code>&nbsp;的 <strong>子序列</strong>&nbsp;指的是选出 <code>k</code>&nbsp;个 <strong>下标</strong>&nbsp;<code>i<sub>0</sub>&nbsp;&lt;&nbsp;i<sub>1</sub> &lt;&nbsp;... &lt; i<sub>k-1</sub></code>&nbsp;，如果这个子序列满足以下条件，我们说它是 <strong>平衡的</strong>&nbsp;：</p>

<ul>
	<li>对于范围&nbsp;<code>[1, k - 1]</code>&nbsp;内的所有&nbsp;<code>j</code>&nbsp;，<code>nums[i<sub>j</sub>] - nums[i<sub>j-1</sub>] &gt;= i<sub>j</sub> - i<sub>j-1</sub></code>&nbsp;都成立。</li>
</ul>

<p><code>nums</code>&nbsp;长度为 <code>1</code>&nbsp;的 <strong>子序列</strong>&nbsp;是平衡的。</p>

<p>请你返回一个整数，表示 <code>nums</code>&nbsp;<strong>平衡</strong>&nbsp;子序列里面的 <strong>最大元素和</strong>&nbsp;。</p>

<p>一个数组的 <strong>子序列</strong>&nbsp;指的是从原数组中删除一些元素（<strong>也可能一个元素也不删除</strong>）后，剩余元素保持相对顺序得到的 <strong>非空</strong>&nbsp;新数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,3,5,6]
<b>输出：</b>14
<b>解释：</b>这个例子中，选择子序列 [3,5,6] ，下标为 0 ，2 和 3 的元素被选中。
nums[2] - nums[0] &gt;= 2 - 0 。
nums[3] - nums[2] &gt;= 3 - 2 。
所以，这是一个平衡子序列，且它的和是所有平衡子序列里最大的。
包含下标 1 ，2 和 3 的子序列也是一个平衡的子序列。
最大平衡子序列和为 14 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,-1,-3,8]
<b>输出：</b>13
<b>解释：</b>这个例子中，选择子序列 [5,8] ，下标为 0 和 3 的元素被选中。
nums[3] - nums[0] &gt;= 3 - 0 。
所以，这是一个平衡子序列，且它的和是所有平衡子序列里最大的。
最大平衡子序列和为 13 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [-2,-1]
<b>输出：</b>-1
<b>解释：</b>这个例子中，选择子序列 [-1] 。
这是一个平衡子序列，而且它的和是 nums 所有平衡子序列里最大的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
