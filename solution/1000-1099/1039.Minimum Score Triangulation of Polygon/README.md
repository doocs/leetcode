# [1039. 多边形三角剖分的最低得分](https://leetcode-cn.com/problems/minimum-score-triangulation-of-polygon)

[English Version](/solution/1000-1099/1039.Minimum%20Score%20Triangulation%20of%20Polygon/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定&nbsp;<code>N</code>，想象一个凸&nbsp;<code>N</code>&nbsp;边多边形，其顶点按顺时针顺序依次标记为&nbsp;<code>A[0], A[i], ..., A[N-1]</code>。</p>

<p>假设您将多边形剖分为 <code>N-2</code> 个三角形。对于每个三角形，该三角形的值是顶点标记的<strong>乘积</strong>，三角剖分的分数是进行三角剖分后所有 <code>N-2</code> 个三角形的值之和。</p>

<p>返回多边形进行三角剖分后可以得到的最低分。<br>
&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[1,2,3]
<strong>输出：</strong>6
<strong>解释：</strong>多边形已经三角化，唯一三角形的分数为 6。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1039.Minimum%20Score%20Triangulation%20of%20Polygon/images/minimum-score-triangulation-of-polygon-1.png" style="height: 150px; width: 253px;"></p>

<pre><strong>输入：</strong>[3,7,4,5]
<strong>输出：</strong>144
<strong>解释：</strong>有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[1,3,1,4,1,5]
<strong>输出：</strong>13
<strong>解释：</strong>最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>3 &lt;= A.length &lt;= 50</code></li>
	<li><code>1 &lt;= A[i] &lt;= 100</code></li>
</ol>

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
