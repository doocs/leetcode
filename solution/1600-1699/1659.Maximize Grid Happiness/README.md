# [1659. 最大化网格幸福感](https://leetcode.cn/problems/maximize-grid-happiness)

[English Version](/solution/1600-1699/1659.Maximize%20Grid%20Happiness/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你四个整数 <code>m</code>、<code>n</code>、<code>introvertsCount</code> 和 <code>extrovertsCount</code> 。有一个 <code>m x n</code> 网格，和两种类型的人：内向的人和外向的人。总共有 <code>introvertsCount</code> 个内向的人和 <code>extrovertsCount</code> 个外向的人。</p>

<p>请你决定网格中应当居住多少人，并为每个人分配一个网格单元。 注意，<strong>不必</strong> 让所有人都生活在网格中。</p>

<p>每个人的 <strong>幸福感</strong> 计算如下：</p>

<ul>
	<li>内向的人 <strong>开始</strong> 时有 <code>120</code> 个幸福感，但每存在一个邻居（内向的或外向的）他都会 <strong>失去</strong>  <code>30</code> 个幸福感。</li>
	<li>外向的人 <strong>开始</strong> 时有 <code>40</code> 个幸福感，每存在一个邻居（内向的或外向的）他都会 <strong>得到</strong>  <code>20</code> 个幸福感。</li>
</ul>

<p>邻居是指居住在一个人所在单元的上、下、左、右四个直接相邻的单元中的其他人。</p>

<p><strong>网格幸福感</strong> 是每个人幸福感的 <strong>总和</strong> 。 返回 <strong>最大可能的网格幸福感</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1659.Maximize%20Grid%20Happiness/images/grid_happiness.png" style="width: 261px; height: 121px;" />
<pre>
<strong>输入：</strong>m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2
<strong>输出：</strong>240
<strong>解释：</strong>假设网格坐标 (row, column) 从 1 开始编号。
将内向的人放置在单元 (1,1) ，将外向的人放置在单元 (1,3) 和 (2,3) 。
- 位于 (1,1) 的内向的人的幸福感：120（初始幸福感）- (0 * 30)（0 位邻居）= 120
- 位于 (1,3) 的外向的人的幸福感：40（初始幸福感）+ (1 * 20)（1 位邻居）= 60
- 位于 (2,3) 的外向的人的幸福感：40（初始幸福感）+ (1 * 20)（1 位邻居）= 60
网格幸福感为：120 + 60 + 60 = 240
上图展示该示例对应网格中每个人的幸福感。内向的人在浅绿色单元中，而外向的人在浅紫色单元中。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1
<strong>输出：</strong>260
<strong>解释：</strong>将内向的人放置在单元 (1,1) 和 (3,1) ，将外向的人放置在单元 (2,1) 。
- 位于 (1,1) 的内向的人的幸福感：120（初始幸福感）- (1 * 30)（1 位邻居）= 90
- 位于 (2,1) 的外向的人的幸福感：40（初始幸福感）+ (2 * 20)（2 位邻居）= 80
- 位于 (3,1) 的内向的人的幸福感：120（初始幸福感）- (1 * 30)（1 位邻居）= 90
网格幸福感为 90 + 80 + 90 = 260
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0
<strong>输出：</strong>240
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= m, n <= 5</code></li>
	<li><code>0 <= introvertsCount, extrovertsCount <= min(m * n, 6)</code></li>
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
