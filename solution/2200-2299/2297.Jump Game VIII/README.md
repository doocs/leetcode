# [2297. 跳跃游戏 VIII](https://leetcode.cn/problems/jump-game-viii)

[English Version](/solution/2200-2299/2297.Jump%20Game%20VIII/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为 n 的下标从 <strong>0</strong>&nbsp;开始的整数数组 <code>nums</code>。初始位置为下标 <code>0</code>。当 <code>i &lt; j</code> 时，你可以从下标 <code>i</code> 跳转到下标 <code>j</code>:</p>

<ul>
	<li>对于在&nbsp;<code>i &lt; k &lt; j</code>&nbsp;范围内的所有下标 <code>k</code> 有&nbsp;<code>nums[i] &lt;= nums[j]</code> 和&nbsp;<code>nums[k] &lt; nums[i]</code> , 或者</li>
	<li>对于在&nbsp;<code>i &lt; k &lt; j</code>&nbsp;范围内的所有下标 <code>k</code>&nbsp;有&nbsp;<code>nums[i] &gt; nums[j]</code> 和&nbsp;<code>nums[k] &gt;= nums[i]</code>&nbsp;。</li>
</ul>

<p>你还得到了一个长度为 <code>n</code> 的整数数组 <code>costs</code>，其中 <code>costs[i]</code> 表示跳转<strong>到</strong>下标 <code>i</code> 的代价。</p>

<p>返回<em>跳转到</em>下标 <em><code>n - 1</code> 的最小代价。</em></p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [3,2,4,4,1], costs = [3,7,6,4,2]
<strong>输出:</strong> 8
<strong>解释:</strong> 从下标 0 开始。
- 以 costs[2]= 6 的代价跳转到下标 2。
- 以 costs[4]= 2 的代价跳转到下标 4。
总代价是 8。可以证明，8 是所需的最小代价。
另外两个可能的路径是:下标 0 -&gt; 1 -&gt; 4 和下标 0 -&gt; 2 -&gt; 3 -&gt; 4。
它们的总代价分别为9和12。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1,2], costs = [1,1,1]
<strong>输出:</strong> 2
<strong>解释:</strong> 从下标 0 开始。
- 以 costs[1] = 1 的代价跳转到下标 1。
- 以 costs[2] = 1 的代价跳转到下标 2。
总代价是 2。注意您不能直接从下标 0 跳转到下标 2，因为 nums[0] &lt;= nums[1]。
</pre>

<p>&nbsp;</p>

<p><strong>解释:</strong></p>

<ul>
	<li><code>n == nums.length == costs.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], costs[i] &lt;= 10<sup>5</sup></code></li>
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
