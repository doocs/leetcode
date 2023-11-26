# [2943. 最大化网格图中正方形空洞的面积](https://leetcode.cn/problems/maximize-area-of-square-hole-in-grid)

[English Version](/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个网格图，由&nbsp;<code>n + 2</code>&nbsp;条 <strong>横线段</strong>&nbsp;和&nbsp;<code>m + 2</code>&nbsp;条&nbsp;<strong>竖线段</strong>&nbsp;组成，一开始所有区域均为&nbsp;<code>1 x 1</code>&nbsp;的单元格。</p>

<p>所有线段的编号从 <strong>1</strong>&nbsp;开始。</p>

<p>给你两个整数&nbsp;<code>n</code> 和&nbsp;<code>m</code>&nbsp;。</p>

<p>同时给你两个整数数组&nbsp;<code>hBars</code> 和&nbsp;<code>vBars</code>&nbsp;。</p>

<ul>
	<li><code>hBars</code> 包含区间&nbsp;<code>[2, n + 1]</code>&nbsp;内&nbsp;<strong>互不相同</strong>&nbsp;的横线段编号。</li>
	<li><code>vBars</code>&nbsp;包含&nbsp;<code>[2, m + 1]</code>&nbsp;内&nbsp;<strong>互不相同的</strong>&nbsp;竖线段编号。</li>
</ul>

<p>如果满足以下条件之一，你可以 <strong>移除</strong>&nbsp;两个数组中的部分线段：</p>

<ul>
	<li>如果移除的是横线段，它必须是&nbsp;<code>hBars</code>&nbsp;中的值。</li>
	<li>如果移除的是竖线段，它必须是&nbsp;<code>vBars</code>&nbsp;中的值。</li>
</ul>

<p>请你返回移除一些线段后（<strong>可能不移除任何线段）</strong>，剩余网格图中 <strong>最大正方形</strong>&nbsp;空洞的面积，正方形空洞的意思是正方形 <strong>内部</strong> 不含有任何线段。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-05-22-40-25.png" style="width: 411px; height: 220px;" /></p>

<pre>
<b>输入：</b>n = 2, m = 1, hBars = [2,3], vBars = [2]
<b>输出：</b>4
<b>解释：</b>左边的图是一开始的网格图。
横线编号的范围是区间 [1,4] ，竖线编号的范围是区间 [1,3] 。
可以移除的横线段为 [2,3] ，竖线段为 [2] 。
一种得到最大正方形面积的方法是移除横线段 2 和竖线段 2 。
操作后得到的网格图如右图所示。
正方形空洞面积为 4。
无法得到面积大于 4 的正方形空洞。
所以答案为 4 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-04-17-01-02.png" style="width: 368px; height: 145px;" /></p>

<pre>
<b>输入：</b>n = 1, m = 1, hBars = [2], vBars = [2]
<b>输出：</b>4
<b>解释：</b>左边的图是一开始的网格图。
横线编号的范围是区间 [1,3] ，竖线编号的范围是区间 [1,3] 。
可以移除的横线段为 [2] ，竖线段为 [2] 。
一种得到最大正方形面积的方法是移除横线段 2 和竖线段 2 。
操作后得到的网格图如右图所示。
正方形空洞面积为 4。
无法得到面积大于 4 的正方形空洞。
所以答案为 4 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-05-22-33-35.png" style="width: 648px; height: 218px;" /></p>

<pre>
<b>输入：</b>n = 2, m = 3, hBars = [2,3], vBars = [2,3,4]
<b>输出：</b>9
<b>解释：</b>左边的图是一开始的网格图。
横线编号的范围是区间 [1,4] ，竖线编号的范围是区间 [1,5] 。
可以移除的横线段为 [2,3] ，竖线段为 [2,3,4] 。
一种得到最大正方形面积的方法是移除横线段 2、3 和竖线段 3、4 。
操作后得到的网格图如右图所示。
正方形空洞面积为 9。
无法得到面积大于 9 的正方形空洞。
所以答案为 9 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= hBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= hBars[i] &lt;= n + 1</code></li>
	<li><code>1 &lt;= vBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= vBars[i] &lt;= m + 1</code></li>
	<li><code>hBars</code>&nbsp;中的值互不相同。</li>
	<li><code>vBars</code> 中的值互不相同。</li>
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
