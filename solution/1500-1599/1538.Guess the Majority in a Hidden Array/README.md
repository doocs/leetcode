# [1538. 找出隐藏数组中出现次数最多的元素](https://leetcode.cn/problems/guess-the-majority-in-a-hidden-array)

[English Version](/solution/1500-1599/1538.Guess%20the%20Majority%20in%20a%20Hidden%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组&nbsp;<code>nums</code>，且&nbsp;<code>nums</code>&nbsp;中的所有整数都为 <strong>0</strong> 或 <strong>1</strong>。你不能直接访问这个数组，你需要使用&nbsp;API <code>ArrayReader</code>&nbsp;，该 API 含有下列成员函数：</p>

<ul>
	<li><code>int query(int a, int b, int c, int d)</code>：其中&nbsp;<code>0 &lt;= a &lt; b &lt; c &lt; d&nbsp;&lt;&nbsp;ArrayReader.length()</code>&nbsp;。此函数查询以这四个参数为下标的元素并返回：
    <ul>
    	<li><strong>4 </strong>: 当这四个元素相同（0 或 1）时。</li>
    	<li><strong>2</strong>&nbsp;: 当其中三个元素的值等于 0 且一个元素等于 1 时，或当其中三个元素的值等于 1&nbsp;且一个元素等于 0&nbsp;时。</li>
    	<li><strong>0&nbsp;</strong>: 当其中两个元素等于 0 且两个元素等于 1 时。</li>
    </ul>
    </li>
    <li><code>int length()</code>：返回数组的长度。</li>

</ul>

<p>你可以调用&nbsp;<code>query()</code>&nbsp;最多&nbsp;<strong>2 * n 次</strong>，其中 n 等于&nbsp;<code>ArrayReader.length()</code>。</p>

<p>返回&nbsp;<code>nums</code>&nbsp;中出现次数最多的值的<strong>任意</strong>索引，若所有的值出现次数均相同，返回&nbsp;-1。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> nums = [0,0,1,0,1,1,1,1]
<strong>输出:</strong> 5
<strong>解释:</strong> API 的调用情况如下：
reader.length() // 返回 8，因为隐藏数组中有 8 个元素。
reader.query(0,1,2,3) // 返回 2，查询元素 nums[0], nums[1], nums[2], nums[3] 间的比较。
// 三个元素等于 0 且一个元素等于 1 或出现相反情况。
reader.query(4,5,6,7) // 返回 4，因为 nums[4], nums[5], nums[6], nums[7] 有相同值。
我们可以推断，最常出现的值在最后 4 个元素中。
索引 2, 4, 6, 7 也是正确答案。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,0,1,1,0]
<strong>输出:</strong> 0
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,0,1,0,1,0,1,0]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>5 &lt;= nums.length&nbsp;&lt;= 10^5</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>要找到出现次数最多的元素，需要至少调用&nbsp;<code>query()</code>&nbsp;多少次？</p>

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
