# [2015. 每段建筑物的平均高度](https://leetcode.cn/problems/average-height-of-buildings-in-each-segment)

[English Version](/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一条完全笔直的街道由一条数字线表示。街道上有建筑物，由二维整数阵列&nbsp;<code>buildings</code> 表示，其中 <code>buildings[i] = [start<sub>i</sub>, end<sub>i</sub>, height<sub>i</sub>]</code>。这意味着在 <strong>半封闭的位置</strong><code>[starti，endi]</code> 有一座高度为&nbsp;<code>height<sub>i</sub></code>&nbsp;的建筑。<br />
你想用 <strong>最少</strong> 数量的非重叠 <strong>部分</strong> 来 <strong>描述</strong> 街道上建筑物的高度。街道可以用2D整数数组&nbsp;<code>street</code>&nbsp;来表示，其中&nbsp;<code>street[j] = [left<sub>j</sub>, right<sub>j</sub>, average<sub>j</sub>]</code>&nbsp;描述了道路的 <strong>半封闭区域</strong>&nbsp;<code>[left<sub>j</sub>, right<sub>j</sub>)</code>&nbsp;，该段中建筑物的 <strong>平均</strong> 高度为&nbsp;<code>average<sub>j</sub></code> 。</p>

<ul>
	<li>例如，如果&nbsp;<code>buildings = [[1,5,2],[3,10,4]]</code>&nbsp;，&nbsp;<code>street = [[1,3,2],[3,5,3],[5,10,4]]</code>&nbsp;可以表示街道，因为：
    <ul>
    	<li>从 1 到 3 ，只有第一栋建筑的平均高度为 <code>2 / 1 = 2</code> 。</li>
    	<li>从 3 到 5 ，第一和第二栋建筑的平均高度均为&nbsp;<code>（2+4） / 2 = 3 </code>。</li>
    	<li>从 5 到 10 ，只有第二栋建筑的平均高度为 <code>4 / 1 = 4</code> 。</li>
    </ul>
    </li>
</ul>

<p>给定&nbsp;<code>buildings</code> ，返回如上所述的二维整数矩阵<em>&nbsp;</em><code>street</code><em>&nbsp;</em>（ <strong>不包括</strong> 街道上没有建筑物的任何区域）。您可以按 <strong>任何顺序</strong> 返回数组。<br />
<code>n</code> 个元素的 <strong>平均值</strong> 是 <code>n</code> 个元素除以&nbsp;<code>n</code> 的 <strong>总和</strong> （<strong>整数除法</strong>）。<br />
<strong>半闭合段</strong>&nbsp;<code>[a, b)</code>&nbsp;是点&nbsp;<code>a</code>&nbsp;和 <code>b</code> 之间的数字线的截面，<strong>包括</strong> 点 <code>a</code> ，<strong>不包括&nbsp;</strong>点 <code>b</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/images/image-20210921224001-2.png" />
<pre>
<strong>输入:</strong> buildings = [[1,4,2],[3,9,4]]
<strong>输出:</strong> [[1,3,2],[3,4,3],[4,9,4]]
<strong>解释:</strong>
从 1 到 3 ，只有第一栋建筑的平均高度为 2 / 1 = 2。
从 3 到 4 ，第一和第二栋建筑的平均高度均为（2+4）/ 2 = 3。
从 4 到 9 ，只有第二栋建筑的平均高度为 4 / 1 = 4。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> buildings = [[1,3,2],[2,5,3],[2,8,3]]
<strong>输出:</strong> [[1,3,2],[3,8,3]]
<strong>解释:</strong>
从 1 到 2 ，只有第一栋建筑的平均高度为 2 / 1 = 2。
从 2 到 3 ，这三座建筑的平均高度均为 （2+3+3） / 3 = 2。
从 3 到 5 ，第二和第三栋楼都在那里，平均高度为 （3+3） / 2 = 3。
从 5 到 8 ，只有最后一栋建筑的平均高度为 3 / 1 = 3。
从 1 到 3 的平均高度是相同的，所以我们可以把它们分成一个部分。
从 3 到 8 的平均高度是相同的，所以我们可以把它们分成一个部分。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> buildings = [[1,2,1],[5,6,1]]
<strong>输出:</strong> [[1,2,1],[5,6,1]]
<strong>解释:</strong>
从 1 到 2 ，只有第一栋建筑的平均高度为 1 / 1 = 1。
从 2 到 5 ，没有建筑物，因此不包括在输出中。
从 5 到 6 ，只有第二栋建筑的平均高度为 1 / 1 = 1。
我们无法将这些部分组合在一起，因为没有建筑的空白空间将这些部分隔开。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= buildings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>buildings[i].length == 3</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= height<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
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
