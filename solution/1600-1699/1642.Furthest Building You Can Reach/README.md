# [1642. 可以到达的最远建筑](https://leetcode.cn/problems/furthest-building-you-can-reach)

[English Version](/solution/1600-1699/1642.Furthest%20Building%20You%20Can%20Reach/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>heights</code> ，表示建筑物的高度。另有一些砖块 <code>bricks</code> 和梯子 <code>ladders</code> 。</p>

<p>你从建筑物 <code>0</code> 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。</p>

<p>当从建筑物 <code>i</code> 移动到建筑物 <code>i+1</code>（下标<strong> 从 0 开始 </strong>）时：</p>

<ul>
	<li>如果当前建筑物的高度 <strong>大于或等于</strong> 下一建筑物的高度，则不需要梯子或砖块</li>
	<li>如果当前建筑的高度 <strong>小于</strong> 下一个建筑的高度，您可以使用 <strong>一架梯子</strong> 或 <strong><code>(h[i+1] - h[i])</code> 个砖块</strong></li>
</ul>
如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标<strong> 从 0 开始 </strong>）。

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1642.Furthest%20Building%20You%20Can%20Reach/images/q4.gif" style="width: 562px; height: 561px;" />
<pre>
<strong>输入：</strong>heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
<strong>输出：</strong>4
<strong>解释：</strong>从建筑物 0 出发，你可以按此方案完成旅程：
- 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2
- 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7
- 不使用砖块或梯子到达建筑物 3 ，因为 7 >= 6
- 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9
无法越过建筑物 4 ，因为没有更多砖块或梯子。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
<strong>输出：</strong>7
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>heights = [14,3,19,3], bricks = 17, ladders = 0
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= heights.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= heights[i] <= 10<sup>6</sup></code></li>
	<li><code>0 <= bricks <= 10<sup>9</sup></code></li>
	<li><code>0 <= ladders <= heights.length</code></li>
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
