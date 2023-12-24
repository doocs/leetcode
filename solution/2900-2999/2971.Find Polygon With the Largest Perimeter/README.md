# [2971. 找到最大周长的多边形](https://leetcode.cn/problems/find-polygon-with-the-largest-perimeter)

[English Version](/solution/2900-2999/2971.Find%20Polygon%20With%20the%20Largest%20Perimeter/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的&nbsp;<strong>正</strong>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p><strong>多边形</strong>&nbsp;指的是一个至少有 <code>3</code>&nbsp;条边的封闭二维图形。多边形的 <strong>最长边</strong>&nbsp;一定 <strong>小于</strong>&nbsp;所有其他边长度之和。</p>

<p>如果你有&nbsp;<code>k</code>&nbsp;（<code>k &gt;= 3</code>）个&nbsp;<strong>正</strong>&nbsp;数&nbsp;<code>a<sub>1</sub></code>，<code>a<sub>2</sub></code>，<code>a<sub>3</sub></code>, ...，<code>a<sub>k</sub></code> 满足&nbsp;<code>a<sub>1</sub> &lt;= a<sub>2</sub> &lt;= a<sub>3</sub> &lt;= ... &lt;= a<sub>k</sub></code> <strong>且</strong> <code>a<sub>1</sub> + a<sub>2</sub> + a<sub>3</sub> + ... + a<sub>k-1</sub> &gt; a<sub>k</sub></code><sub>&nbsp;</sub>，那么 <strong>一定</strong>&nbsp;存在一个&nbsp;<code>k</code>&nbsp;条边的多边形，每条边的长度分别为&nbsp;<code>a<sub>1</sub></code>&nbsp;，<code>a<sub>2</sub></code>&nbsp;，<code>a<sub>3</sub></code>&nbsp;，&nbsp;...，<code>a<sub>k</sub></code>&nbsp;。</p>

<p>一个多边形的 <strong>周长</strong>&nbsp;指的是它所有边之和。</p>

<p>请你返回从 <code>nums</code>&nbsp;中可以构造的 <strong>多边形&nbsp;</strong>的 <strong>最大周长</strong>&nbsp;。如果不能构造出任何多边形，请你返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [5,5,5]
<b>输出：</b>15
<b>解释：</b>nums 中唯一可以构造的多边形为三角形，每条边的长度分别为 5 ，5 和 5 ，周长为 5 + 5 + 5 = 15 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,12,1,2,5,50,3]
<b>输出：</b>12
<b>解释：</b>最大周长多边形为五边形，每条边的长度分别为 1 ，1 ，2 ，3 和 5 ，周长为 1 + 1 + 2 + 3 + 5 = 12 。
我们无法构造一个包含变长为 12 或者 50 的多边形，因为其他边之和没法大于两者中的任何一个。
所以最大周长为 12 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [5,5,50]
<b>输出：</b>-1
<b>解释：</b>无法构造任何多边形，因为多边形至少要有 3 条边且 50 &gt; 5 + 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
