# [1182. 与目标颜色间的最短距离](https://leetcode-cn.com/problems/shortest-distance-to-target-color)

[English Version](/solution/1100-1199/1182.Shortest%20Distance%20to%20Target%20Color/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给你一个数组 <code>colors</code>，里面有  <code>1</code>、<code>2</code>、 <code>3</code> 三种颜色。</p>

<p>我们需要在 <code>colors</code> 上进行一些查询操作 <code>queries</code>，其中每个待查项都由两个整数 <code>i</code> 和 <code>c</code> 组成。</p>

<p>现在请你帮忙设计一个算法，查找从索引 <code>i</code> 到具有目标颜色 <code>c</code> 的元素之间的最短距离。</p>

<p>如果不存在解决方案，请返回 <code>-1</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
<strong>输出：</strong>[3,0,3]
<strong>解释： </strong>
距离索引 1 最近的颜色 3 位于索引 4（距离为 3）。
距离索引 2 最近的颜色 2 就是它自己（距离为 0）。
距离索引 6 最近的颜色 1 位于索引 3（距离为 3）。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>colors = [1,2], queries = [[0,3]]
<strong>输出：</strong>[-1]
<strong>解释：</strong>colors 中没有颜色 3。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= colors.length <= 5*10^4</code></li>
	<li><code>1 <= colors[i] <= 3</code></li>
	<li><code>1 <= queries.length <= 5*10^4</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 <= queries[i][0] < colors.length</code></li>
	<li><code>1 <= queries[i][1] <= 3</code></li>
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
