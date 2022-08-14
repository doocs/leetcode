# [1840. 最高建筑高度](https://leetcode.cn/problems/maximum-building-height)

[English Version](/solution/1800-1899/1840.Maximum%20Building%20Height/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一座城市里，你需要建 <code>n</code> 栋新的建筑。这些新的建筑会从 <code>1</code> 到 <code>n</code> 编号排成一列。</p>

<p>这座城市对这些新建筑有一些规定：</p>

<ul>
	<li>每栋建筑的高度必须是一个非负整数。</li>
	<li>第一栋建筑的高度 <strong>必须</strong> 是 <code>0</code> 。</li>
	<li>任意两栋相邻建筑的高度差 <strong>不能超过</strong>  <code>1</code> 。</li>
</ul>

<p>除此以外，某些建筑还有额外的最高高度限制。这些限制会以二维整数数组 <code>restrictions</code> 的形式给出，其中 <code>restrictions[i] = [id<sub>i</sub>, maxHeight<sub>i</sub>]</code> ，表示建筑 <code>id<sub>i</sub></code> 的高度 <strong>不能超过</strong> <code>maxHeight<sub>i</sub></code> 。</p>

<p>题目保证每栋建筑在 <code>restrictions</code> 中<strong> 至多出现一次</strong> ，同时建筑 <code>1</code> <strong>不会</strong> 出现在 <code>restrictions</code> 中。</p>

<p>请你返回 <strong>最高</strong> 建筑能达到的 <strong>最高高度</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1840.Maximum%20Building%20Height/images/ic236-q4-ex1-1.png" style="width: 400px; height: 253px;" />
<pre>
<b>输入：</b>n = 5, restrictions = [[2,1],[4,1]]
<b>输出：</b>2
<b>解释：</b>上图中的绿色区域为每栋建筑被允许的最高高度。
我们可以使建筑高度分别为 [0,1,2,1,2] ，最高建筑的高度为 2 。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1840.Maximum%20Building%20Height/images/ic236-q4-ex2.png" style="width: 500px; height: 269px;" />
<pre>
<b>输入：</b>n = 6, restrictions = []
<b>输出：</b>5
<b>解释：</b>上图中的绿色区域为每栋建筑被允许的最高高度。
我们可以使建筑高度分别为 [0,1,2,3,4,5] ，最高建筑的高度为 5 。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1840.Maximum%20Building%20Height/images/ic236-q4-ex3.png" style="width: 500px; height: 187px;" />
<pre>
<b>输入：</b>n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
<b>输出：</b>5
<b>解释：</b>上图中的绿色区域为每栋建筑被允许的最高高度。
我们可以使建筑高度分别为 [0,1,2,3,3,4,4,5,4,3] ，最高建筑的高度为 5 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 10<sup>9</sup></code></li>
	<li><code>0 <= restrictions.length <= min(n - 1, 10<sup>5</sup>)</code></li>
	<li><code>2 <= id<sub>i</sub> <= n</code></li>
	<li><code>id<sub>i</sub></code> 是 <strong>唯一的</strong> 。</li>
	<li><code>0 <= maxHeight<sub>i</sub> <= 10<sup>9</sup></code></li>
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
