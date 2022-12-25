# [2282. 在一个网格中可以看到的人数](https://leetcode.cn/problems/number-of-people-that-can-be-seen-in-a-grid)

[English Version](/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <code>m x n</code> <strong>下标从 0 开始</strong>的二维正整数数组 <code>heights</code>，其中 <code>heights[i][j]</code> 是站在位置 <code>(i, j)</code>&nbsp;上的人的高度。</p>

<p>站在 <code>(row<sub>1</sub>, col<sub>1</sub>)</code>&nbsp;位置的人可以看到站在 <code>(row<sub>2</sub>, col<sub>2</sub>)</code> 位置的人，前提是:</p>

<ul>
	<li><code>(row<sub>2</sub>, col<sub>2</sub>)</code>&nbsp;的人在&nbsp;<code>(row<sub>1</sub>, col<sub>1</sub>)</code> 的人的右边&nbsp;<strong>或&nbsp;</strong>下面。更正式地说，要么 <code>row<sub>1</sub> == row<sub>2</sub></code>&nbsp;时&nbsp;<code>col<sub>1</sub> &lt; col<sub>2</sub></code>，要么&nbsp;<code>row<sub>1</sub> &lt; row<sub>2</sub></code><sub>&nbsp;</sub>时 <code>col<sub>1</sub> == col<sub>2</sub></code>。</li>
	<li>他们中间的人&nbsp;<strong>都&nbsp;</strong>比他们两个矮。</li>
</ul>

<p>返回<em>一个&nbsp;<code>m x n</code> 的二维整数数组<code>answer</code>，其中&nbsp;<code>answer[i][j]</code>&nbsp;是位于&nbsp;<code>(i, j)</code> 位置的人可以看到的人数。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/images/image-20220524180458-1.png" style="width: 700px; height: 164px;" />
<pre>
<strong>输入:</strong> heights = [[3,1,4,2,5]]
<strong>输出:</strong> [[2,1,2,1,0]]
<strong>解释:</strong>
- (0,0) 上的人可以看到 (0,1) 和 (0,2) 的人。
  注意，他看不到 (0,4) 上的人，因为 (0,2) 上的人比他高。
- (0,1) 上的人可以看到 (0,2) 上的人。
- (0,2) 上的人可以看到 (0,3) 和 (0,4) 的人。
- (0,3) 上的人可以看到 (0,4) 上的人。
- (0,4) 上的人看不到任何人。</pre>

<p><strong class="example">示例 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/images/image-20220523113533-2.png" style="width: 400px; height: 249px;" />
<pre>
<strong>输入:</strong> heights = [[5,1],[3,1],[4,1]]
<strong>输出:</strong> [[3,1],[2,1],[1,0]]
<strong>解释:</strong>
- (0,0) 上的人可以看到 (0,1)、(1,0) 和 (2,0) 的人。
- (0,1) 上的人可以看到 (1,1) 上的人。
- (1,0) 上的人可以看到 (1,1) 和 (2,0) 的人。
- (1,1) 上的人可以看到 (2,1) 上的人。
- (2,0) 上的人可以看到 (2,1) 上的人。
- (2,1) 上的人看不到任何人。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 400</code></li>
	<li><code>1 &lt;= heights[i].length &lt;= 400</code></li>
	<li><code>1 &lt;= heights[i][j] &lt;= 10<sup>5</sup></code></li>
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
