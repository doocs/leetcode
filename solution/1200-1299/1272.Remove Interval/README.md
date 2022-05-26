# [1272. 删除区间](https://leetcode.cn/problems/remove-interval)

[English Version](/solution/1200-1299/1272.Remove%20Interval/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实数集合可以表示为若干不相交区间的并集，其中每个区间的形式为 <code>[a, b)</code>（左闭右开），表示满足&nbsp;<code>a &lt;= x &lt; b</code> 的所有实数&nbsp; <code>x</code>&nbsp;的集合。如果某个区间&nbsp;<code>[a, b)</code> 中包含实数 <code>x</code> ，则称实数 <code>x</code> 在集合中。</p>

<p>给你一个 <strong>有序的</strong> 不相交区间列表 <code>intervals</code>&nbsp;。<code>intervals</code> 表示一个实数集合，其中每一项 <code>intervals[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 都表示一个区间 <code>[a<sub>i</sub>, b<sub>i</sub>)</code> 。再给你一个要删除的区间 <code>toBeRemoved</code> 。</p>

<p>返回 <em>一组实数，该实数表示<code>intervals</code> 中&nbsp;<strong>删除</strong>&nbsp;了 <code>toBeRemoved</code> 的部分</em>&nbsp;。<em>换句话说，返回实数集合，并满足集合中的每个实数 <code>x</code> 都在&nbsp;<code>intervals</code> 中，但不在 <code>toBeRemoved</code> 中。你的答案应该是一个如上所述的 <strong>有序的</strong> 不相连的间隔列表&nbsp;。</em></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1272.Remove%20Interval/images/removeintervalex1.png" />
<pre>
<strong>输入：</strong>intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
<strong>输出：</strong>[[0,1],[6,7]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1272.Remove%20Interval/images/removeintervalex2.png" />
<pre>
<strong>输入：</strong>intervals = [[0,5]], toBeRemoved = [2,3]
<strong>输出：</strong>[[0,2],[3,5]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
<strong>输出：</strong>[[-5,-4],[-3,-2],[4,5],[8,9]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= a<sub>i</sub> &lt; b<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
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
