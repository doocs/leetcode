# [2122. 还原原数组](https://leetcode-cn.com/problems/recover-the-original-array)

[English Version](/solution/2100-2199/2122.Recover%20the%20Original%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 有一个下标从 <strong>0</strong> 开始的数组 <code>arr</code> ，由 <code>n</code> 个正整数组成。她会选择一个任意的 <strong>正整数 </strong><code>k</code> 并按下述方式创建两个下标从 <strong>0</strong> 开始的新整数数组 <code>lower</code> 和 <code>higher</code> ：</p>

<ol>
	<li>对每个满足 <code>0 &lt;= i &lt; n</code> 的下标 <code>i</code> ，<code>lower[i] = arr[i] - k</code></li>
	<li>对每个满足 <code>0 &lt;= i &lt; n</code> 的下标 <code>i</code> ，<code>higher[i] = arr[i] + k</code></li>
</ol>

<p>不幸地是，Alice 丢失了全部三个数组。但是，她记住了在数组 <code>lower</code> 和 <code>higher</code> 中出现的整数，但不知道每个整数属于哪个数组。请你帮助 Alice 还原原数组。</p>

<p>给你一个由 2n 个整数组成的整数数组 <code>nums</code> ，其中 <strong>恰好</strong> <code>n</code> 个整数出现在 <code>lower</code> ，剩下的出现在 <code>higher</code> ，还原并返回 <strong>原数组</strong> <code>arr</code> 。如果出现答案不唯一的情况，返回 <strong>任一</strong> 有效数组。</p>

<p><strong>注意：</strong>生成的测试用例保证存在 <strong>至少一个</strong> 有效数组 <code>arr</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,10,6,4,8,12]
<strong>输出：</strong>[3,7,11]
<strong>解释：</strong>
如果 arr = [3,7,11] 且 k = 1 ，那么 lower = [2,6,10] 且 higher = [4,8,12] 。
组合 lower 和 higher 得到 [2,6,10,4,8,12] ，这是 nums 的一个排列。
另一个有效的数组是 arr = [5,7,9] 且 k = 3 。在这种情况下，lower = [2,4,6] 且 higher = [8,10,12] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,3,3]
<strong>输出：</strong>[2,2]
<strong>解释：</strong>
如果 arr = [2,2] 且 k = 1 ，那么 lower = [1,1] 且 higher = [3,3] 。
组合 lower 和 higher 得到 [1,1,3,3] ，这是 nums 的一个排列。
注意，数组不能是 [1,3] ，因为在这种情况下，获得 [1,1,3,3] 唯一可行的方案是 k = 0 。
这种方案是无效的，k 必须是一个正整数。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [5,435]
<strong>输出：</strong>[220]
<strong>解释：</strong>
唯一可行的组合是 arr = [220] 且 k = 215 。在这种情况下，lower = [5] 且 higher = [435] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 * n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>生成的测试用例保证存在 <strong>至少一个</strong> 有效数组 <code>arr</code></li>
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
