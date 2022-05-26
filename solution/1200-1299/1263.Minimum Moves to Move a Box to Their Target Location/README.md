# [1263. 推箱子](https://leetcode.cn/problems/minimum-moves-to-move-a-box-to-their-target-location)

[English Version](/solution/1200-1299/1263.Minimum%20Moves%20to%20Move%20a%20Box%20to%20Their%20Target%20Location/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。</p>

<p>游戏地图用大小为&nbsp;<code>m x n</code>&nbsp;的网格 <code>grid</code> 表示，其中每个元素可以是墙、地板或者是箱子。</p>

<p>现在你将作为玩家参与游戏，按规则将箱子&nbsp;<code>'B'</code>&nbsp;移动到目标位置&nbsp;<code>'T'</code> ：</p>

<ul>
	<li>玩家用字符&nbsp;<code>'S'</code>&nbsp;表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。</li>
	<li>地板用字符&nbsp;<code>'.'</code>&nbsp;表示，意味着可以自由行走。</li>
	<li>墙用字符&nbsp;<code>'#'</code>&nbsp;表示，意味着障碍物，不能通行。&nbsp;</li>
	<li>箱子仅有一个，用字符&nbsp;<code>'B'</code>&nbsp;表示。相应地，网格上有一个目标位置&nbsp;<code>'T'</code>。</li>
	<li>玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。</li>
	<li>玩家无法越过箱子。</li>
</ul>

<p>返回将箱子推到目标位置的最小 <strong>推动</strong> 次数，如果无法做到，请返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1263.Minimum%20Moves%20to%20Move%20a%20Box%20to%20Their%20Target%20Location/images/sample_1_1620.png" style="height: 335px; width: 500px;" /></strong></p>

<pre>
<strong>输入：</strong>grid = [["#","#","#","#","#","#"],
             ["#","T","#","#","#","#"],
&nbsp;            ["#",".",".","B",".","#"],
&nbsp;            ["#",".","#","#",".","#"],
&nbsp;            ["#",".",".",".","S","#"],
&nbsp;            ["#","#","#","#","#","#"]]
<strong>输出：</strong>3
<strong>解释：</strong>我们只需要返回推箱子的次数。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [["#","#","#","#","#","#"],
             ["#","T","#","#","#","#"],
&nbsp;            ["#",".",".","B",".","#"],
&nbsp;            ["#","#","#","#",".","#"],
&nbsp;            ["#",".",".",".","S","#"],
&nbsp;            ["#","#","#","#","#","#"]]
<strong>输出：</strong>-1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>grid = [["#","#","#","#","#","#"],
&nbsp;            ["#","T",".",".","#","#"],
&nbsp;            ["#",".","#","B",".","#"],
&nbsp;            ["#",".",".",".",".","#"],
&nbsp;            ["#",".",".",".","S","#"],
&nbsp;            ["#","#","#","#","#","#"]]
<strong>输出：</strong>5
<strong>解释：</strong>向下、向左、向左、向上再向上。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 20</code></li>
	<li><code>grid</code> 仅包含字符&nbsp;<code>'.'</code>, <code>'#'</code>,&nbsp; <code>'S'</code> , <code>'T'</code>, 以及&nbsp;<code>'B'</code>。</li>
	<li><code>grid</code>&nbsp;中&nbsp;<code>'S'</code>, <code>'B'</code>&nbsp;和&nbsp;<code>'T'</code>&nbsp;各只能出现一个。</li>
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
