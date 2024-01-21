# [3012. 通过操作使数组长度最小](https://leetcode.cn/problems/minimize-length-of-array-using-operations)

[English Version](/solution/3000-3099/3012.Minimize%20Length%20of%20Array%20Using%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，它只包含 <strong>正</strong>&nbsp;整数。</p>

<p>你的任务是通过进行以下操作&nbsp;<strong>任意次</strong>&nbsp;（可以是 0 次）&nbsp;<strong>最小化</strong>&nbsp;<code>nums</code>&nbsp;的长度：</p>

<ul>
	<li>在 <code>nums</code>&nbsp;中选择 <strong>两个不同</strong>&nbsp;的下标&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;，满足&nbsp;<code>nums[i] &gt; 0</code>&nbsp;且&nbsp;<code>nums[j] &gt; 0</code>&nbsp;。</li>
	<li>将结果&nbsp;<code>nums[i] % nums[j]</code>&nbsp;插入&nbsp;<code>nums</code>&nbsp;的结尾。</li>
	<li>将 <code>nums</code>&nbsp;中下标为&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;的元素删除。</li>
</ul>

<p>请你返回一个整数，它表示进行任意次操作以后<em>&nbsp;</em><code>nums</code>&nbsp;的 <strong>最小长度</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,3,1]
<b>输出：</b>1
<b>解释：</b>使数组长度最小的一种方法是：
操作 1 ：选择下标 2 和 1 ，插入 nums[2] % nums[1] 到数组末尾，得到 [1,4,3,1,3] ，然后删除下标为 2 和 1 的元素。
nums 变为 [1,1,3] 。
操作 2 ：选择下标 1 和 2 ，插入 nums[1] % nums[2] 到数组末尾，得到 [1,1,3,1] ，然后删除下标为 1 和 2 的元素。
nums 变为 [1,1] 。
操作 3 ：选择下标 1 和 0 ，插入 nums[1] % nums[0] 到数组末尾，得到 [1,1,0] ，然后删除下标为 1 和 0 的元素。
nums 变为 [0] 。
nums 的长度无法进一步减小，所以答案为 1 。
1 是可以得到的最小长度。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,5,5,10,5]
<b>输出：</b>2
<b>解释：</b>使数组长度最小的一种方法是：
操作 1 ：选择下标 0 和 3 ，插入 nums[0] % nums[3] 到数组末尾，得到 [5,5,5,10,5,5] ，然后删除下标为 0 和 3 的元素。
nums 变为 [5,5,5,5] 。
操作 2 ：选择下标 2 和 3 ，插入 nums[2] % nums[3] 到数组末尾，得到 [5,5,5,5,0] ，然后删除下标为 2 和 3 的元素。
nums 变为 [5,5,0] 。
操作 3 ：选择下标 0 和 1 ，插入 nums[0] % nums[1] 到数组末尾，得到 [5,5,0,0] ，然后删除下标为 0 和 1 的元素。
nums 变为 [0,0] 。
nums 的长度无法进一步减小，所以答案为 2 。
2 是可以得到的最小长度。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,4]
<b>输出：</b>1
<b>解释：</b>使数组长度最小的一种方法是：
操作 1 ：选择下标 1 和 2 ，插入 nums[1] % nums[2] 到数组末尾，得到 [2,3,4,3] ，然后删除下标为 1 和 2 的元素。
nums 变为 [2,3] 。
操作 2 ：选择下标 1 和 0 ，插入 nums[1] % nums[0] 到数组末尾，得到 [2,3,1] ，然后删除下标为 1 和 0 的元素。
nums 变为 [1] 。
nums 的长度无法进一步减小，所以答案为 1 。
1 是可以得到的最小长度。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
