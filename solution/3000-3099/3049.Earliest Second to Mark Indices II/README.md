# [3049. 标记所有下标的最早秒数 II](https://leetcode.cn/problems/earliest-second-to-mark-indices-ii)

[English Version](/solution/3000-3099/3049.Earliest%20Second%20to%20Mark%20Indices%20II/README_EN.md)

<!-- tags:贪心,数组,二分查找,堆（优先队列） -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>1</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code> 和&nbsp;<code>changeIndices</code>&nbsp;，数组的长度分别为&nbsp;<code>n</code> 和&nbsp;<code>m</code>&nbsp;。</p>

<p>一开始，<code>nums</code>&nbsp;中所有下标都是未标记的，你的任务是标记 <code>nums</code>&nbsp;中 <strong>所有</strong>&nbsp;下标。</p>

<p>从第 <code>1</code>&nbsp;秒到第 <code>m</code>&nbsp;秒（<b>包括&nbsp;</b>第&nbsp;<code>m</code>&nbsp;秒），对于每一秒 <code>s</code>&nbsp;，你可以执行以下操作 <strong>之一</strong>&nbsp;：</p>

<ul>
	<li>选择范围&nbsp;<code>[1, n]</code>&nbsp;中的一个下标 <code>i</code>&nbsp;，并且将&nbsp;<code>nums[i]</code> <strong>减少</strong>&nbsp;<code>1</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[changeIndices[s]]</code>&nbsp;设置成任意的 <strong>非负</strong>&nbsp;整数。</li>
	<li>选择范围&nbsp;<code>[1, n]</code>&nbsp;中的一个下标&nbsp;<code>i</code>&nbsp;， 满足&nbsp;<code>nums[i]</code> <strong>等于</strong> <code>0</code>, 并 <strong>标记</strong>&nbsp;下标&nbsp;<code>i</code> 。</li>
	<li>什么也不做。</li>
</ul>

<p>请你返回范围 <code>[1, m]</code>&nbsp;中的一个整数，表示最优操作下，标记&nbsp;<code>nums</code>&nbsp;中 <strong>所有</strong>&nbsp;下标的 <strong>最早秒数</strong>&nbsp;，如果无法标记所有下标，返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,3], changeIndices = [1,3,2,2,2,2,3]
<b>输出：</b>6
<b>解释：</b>这个例子中，我们总共有 7 秒。按照以下操作标记所有下标：
第 1 秒：将 nums[changeIndices[1]] 变为 0 。nums 变为 [0,2,3] 。
第 2 秒：将 nums[changeIndices[2]] 变为 0 。nums 变为 [0,2,0] 。
第 3 秒：将 nums[changeIndices[3]] 变为 0 。nums 变为 [0,0,0] 。
第 4 秒：标记下标 1 ，因为 nums[1] 等于 0 。
第 5 秒：标记下标 2 ，因为 nums[2] 等于 0 。
第 6 秒：标记下标 3 ，因为 nums[3] 等于 0 。
现在所有下标已被标记。
最早可以在第 6 秒标记所有下标。
所以答案是 6 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [0,0,1,2], changeIndices = [1,2,1,2,1,2,1,2]
<b>输出：</b>7
<b>解释：</b>这个例子中，我们总共有 8 秒。按照以下操作标记所有下标：
第 1 秒：标记下标 1 ，因为 nums[1] 等于 0 。
第 2 秒：标记下标 2 ，因为 nums[2] 等于 0 。
第 3 秒：将 nums[4] 减少 1 。nums 变为 [0,0,1,1] 。
第 4 秒：将 nums[4] 减少 1 。nums 变为 [0,0,1,0] 。
第 5 秒：将 nums[3] 减少 1 。nums 变为 [0,0,0,0] 。
第 6 秒：标记下标 3 ，因为 nums[3] 等于 0 。
第 7 秒：标记下标 4 ，因为 nums[4] 等于 0 。
现在所有下标已被标记。
最早可以在第 7 秒标记所有下标。
所以答案是 7 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3], changeIndices = [1,2,3]
<b>输出：</b>-1
<strong>解释：</strong>这个例子中，无法标记所有下标，因为我们没有足够的秒数。
所以答案是 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 5000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == changeIndices.length &lt;= 5000</code></li>
	<li><code>1 &lt;= changeIndices[i] &lt;= n</code></li>
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
