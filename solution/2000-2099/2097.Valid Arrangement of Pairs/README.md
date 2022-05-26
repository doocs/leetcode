# [2097. 合法重新排列数对](https://leetcode.cn/problems/valid-arrangement-of-pairs)

[English Version](/solution/2000-2099/2097.Valid%20Arrangement%20of%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>pairs</code>&nbsp;，其中&nbsp;<code>pairs[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;。如果 <code>pairs</code>&nbsp;的一个重新排列，满足对每一个下标 <code>i</code> （&nbsp;<code>1 &lt;= i &lt; pairs.length</code>&nbsp;）都有&nbsp;<code>end<sub>i-1</sub> == start<sub>i</sub></code><sub> </sub>，那么我们就认为这个重新排列是&nbsp;<code>pairs</code> 的一个 <strong>合法重新排列</strong> 。</p>

<p>请你返回 <strong>任意一个</strong>&nbsp;<code>pairs</code> 的合法重新排列。</p>

<p><b>注意：</b>数据保证至少存在一个 <code>pairs</code>&nbsp;的合法重新排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>pairs = [[5,1],[4,5],[11,9],[9,4]]
<b>输出：</b>[[11,9],[9,4],[4,5],[5,1]]
<strong>解释：
</strong>输出的是一个合法重新排列，因为每一个 end<sub>i-1</sub> 都等于 start<sub>i</sub>&nbsp;。
end<sub>0</sub> = 9 == 9 = start<sub>1</sub> 
end<sub>1</sub> = 4 == 4 = start<sub>2</sub>
end<sub>2</sub> = 5 == 5 = start<sub>3</sub>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>pairs = [[1,3],[3,2],[2,1]]
<b>输出：</b>[[1,3],[3,2],[2,1]]
<strong>解释：</strong>
输出的是一个合法重新排列，因为每一个 end<sub>i-1</sub> 都等于 start<sub>i</sub>&nbsp;。
end<sub>0</sub> = 3 == 3 = start<sub>1</sub>
end<sub>1</sub> = 2 == 2 = start<sub>2</sub>
重新排列后的数组 [[2,1],[1,3],[3,2]] 和 [[3,2],[2,1],[1,3]] 都是合法的。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>pairs = [[1,2],[1,3],[2,1]]
<b>输出：</b>[[1,2],[2,1],[1,3]]
<strong>解释：</strong>
输出的是一个合法重新排列，因为每一个 end<sub>i-1</sub> 都等于 start<sub>i</sub>&nbsp;。
end<sub>0</sub> = 2 == 2 = start<sub>1</sub>
end<sub>1</sub> = 1 == 1 = start<sub>2</sub>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pairs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>pairs[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub>, end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>start<sub>i</sub> != end<sub>i</sub></code></li>
	<li><code>pairs</code>&nbsp;中不存在一模一样的数对。</li>
	<li>至少 <strong>存在</strong> 一个合法的&nbsp;<code>pairs</code>&nbsp;重新排列。</li>
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
