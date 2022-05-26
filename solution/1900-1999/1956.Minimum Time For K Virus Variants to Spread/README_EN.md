# [1956. Minimum Time For K Virus Variants to Spread](https://leetcode.com/problems/minimum-time-for-k-virus-variants-to-spread)

[中文文档](/solution/1900-1999/1956.Minimum%20Time%20For%20K%20Virus%20Variants%20to%20Spread/README.md)

## Description

<p>There are <code>n</code> <strong>unique</strong> virus variants in an infinite 2D grid. You are given a 2D array <code>points</code>, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents a virus originating at <code>(x<sub>i</sub>, y<sub>i</sub>)</code> on day <code>0</code>. Note that it is possible for <strong>multiple </strong>virus variants to originate at the <strong>same</strong> point.</p>

<p>Every day, each cell infected with a virus variant will spread the virus to <strong>all </strong>neighboring points in the <strong>four</strong> cardinal directions (i.e. up, down, left, and right). If a cell has multiple variants, all the variants will spread without interfering with each other.</p>

<p>Given an integer <code>k</code>, return <em>the <strong>minimum integer</strong> number of days for <strong>any</strong> point to contain <strong>at least</strong> </em><code>k</code><em> of the unique virus variants</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1956.Minimum%20Time%20For%20K%20Virus%20Variants%20to%20Spread/images/case-1.png" style="width: 421px; height: 256px;" />
<pre>
<strong>Input:</strong> points = [[1,1],[6,1]], k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> On day 3, points (3,1) and (4,1) will contain both virus variants. Note that these are not the only points that will contain both virus variants.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1956.Minimum%20Time%20For%20K%20Virus%20Variants%20to%20Spread/images/case-2.png" style="width: 416px; height: 257px;" />
<pre>
<strong>Input:</strong> points = [[3,3],[1,2],[9,2]], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> On day 2, points (1,3), (2,3), (2,2), and (3,2) will contain the first two viruses. Note that these are not the only points that will contain both virus variants.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1956.Minimum%20Time%20For%20K%20Virus%20Variants%20to%20Spread/images/case-2.png" style="width: 416px; height: 257px;" />
<pre>
<strong>Input:</strong> points = [[3,3],[1,2],[9,2]], k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> On day 4, the point (5,2) will contain all 3 viruses. Note that this is not the only point that will contain all 3 virus variants.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == points.length</code></li>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 100</code></li>
	<li><code>2 &lt;= k &lt;= n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
