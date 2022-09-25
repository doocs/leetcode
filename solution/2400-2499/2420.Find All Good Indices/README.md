# [2420. 找到所有好下标](https://leetcode.cn/problems/find-all-good-indices)

[English Version](/solution/2400-2499/2420.Find%20All%20Good%20Indices/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个正整数&nbsp;<code>k</code>&nbsp;。</p>

<p>对于&nbsp;<code>k &lt;= i &lt; n - k</code>&nbsp;之间的一个下标&nbsp;<code>i</code>&nbsp;，如果它满足以下条件，我们就称它为一个&nbsp;<strong>好</strong>&nbsp;下标：</p>

<ul>
	<li>下标 <code>i</code> <strong>之前</strong> 的 <code>k</code>&nbsp;个元素是 <strong>非递增的</strong>&nbsp;。</li>
	<li>下标 <code>i</code> <strong>之后</strong>&nbsp;的 <code>k</code>&nbsp;个元素是 <strong>非递减的</strong>&nbsp;。</li>
</ul>

<p>按 <strong>升序</strong>&nbsp;返回所有好下标。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,1,1,3,4,1], k = 2
<b>输出：</b>[2,3]
<b>解释：</b>数组中有两个好下标：
- 下标 2 。子数组 [2,1] 是非递增的，子数组 [1,3] 是非递减的。
- 下标 3 。子数组 [1,1] 是非递增的，子数组 [3,4] 是非递减的。
注意，下标 4 不是好下标，因为 [4,1] 不是非递减的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,1,2], k = 2
<b>输出：</b>[]
<b>解释：</b>数组中没有好下标。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n / 2</code></li>
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
