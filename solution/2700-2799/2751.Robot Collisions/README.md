# [2751. 机器人碰撞](https://leetcode.cn/problems/robot-collisions)

[English Version](/solution/2700-2799/2751.Robot%20Collisions/README_EN.md)

<!-- tags:栈,数组,排序,模拟 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>现有 <code>n</code> 个机器人，编号从 <strong>1</strong> 开始，每个机器人包含在路线上的位置、健康度和移动方向。</p>

<p>给你下标从 <strong>0</strong> 开始的两个整数数组 <code>positions</code>、<code>healths</code> 和一个字符串 <code>directions</code>（<code>directions[i]</code> 为 <strong>'L'</strong> 表示 <strong>向左</strong> 或 <strong>'R'</strong> 表示 <strong>向右</strong>）。 <code>positions</code> 中的所有整数 <strong>互不相同</strong> 。</p>

<p>所有机器人以 <strong>相同速度</strong> <strong>同时</strong> 沿给定方向在路线上移动。如果两个机器人移动到相同位置，则会发生 <strong>碰撞</strong> 。</p>

<p>如果两个机器人发生碰撞，则将 <strong>健康度较低</strong> 的机器人从路线中 <strong>移除</strong> ，并且另一个机器人的健康度 <strong>减少 1</strong> 。幸存下来的机器人将会继续沿着与之前 <strong>相同</strong> 的方向前进。如果两个机器人的健康度相同，则将二者都从路线中移除。</p>

<p>请你确定全部碰撞后幸存下的所有机器人的 <strong>健康度</strong> ，并按照原来机器人编号的顺序排列。即机器人 1 （如果幸存）的最终健康度，机器人 2 （如果幸存）的最终健康度等。 如果不存在幸存的机器人，则返回空数组。</p>

<p>在不再发生任何碰撞后，请你以数组形式，返回所有剩余机器人的健康度（按机器人输入中的编号顺序）。</p>

<p><strong>注意：</strong>位置&nbsp; <code>positions</code> 可能是乱序的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img height="169" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2751.Robot%20Collisions/images/image-20230516011718-12.png" width="808" /></p>

<pre>
<strong>输入：</strong>positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = "RRRRR"
<strong>输出：</strong>[2,17,9,15,10]
<strong>解释：</strong>在本例中不存在碰撞，因为所有机器人向同一方向移动。所以，从第一个机器人开始依序返回健康度，[2, 17, 9, 15, 10] 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img height="176" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2751.Robot%20Collisions/images/image-20230516004433-7.png" width="717" /></p>

<pre>
<strong>输入：</strong>positions = [3,5,2,6], healths = [10,10,15,12], directions = "RLRL"
<strong>输出：</strong>[14]
<strong>解释：</strong>本例中发生 2 次碰撞。首先，机器人 1 和机器人 2 将会碰撞，因为二者健康度相同，二者都将被从路线中移除。接下来，机器人 3 和机器人 4 将会发生碰撞，由于机器人 4 的健康度更小，则它会被移除，而机器人 3 的健康度变为 15 - 1 = 14 。仅剩机器人 3 ，所以返回 [14] 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img height="172" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2751.Robot%20Collisions/images/image-20230516005114-9.png" width="732" /></p>

<pre>
<strong>输入：</strong>positions = [1,2,5,6], healths = [10,10,11,11], directions = "RLRL"
<strong>输出：</strong>[]
<strong>解释：</strong>机器人 1 和机器人 2 将会碰撞，因为二者健康度相同，二者都将被从路线中移除。机器人 3 和机器人 4 将会碰撞，因为二者健康度相同，二者都将被从路线中移除。所以返回空数组 [] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= positions.length == healths.length == directions.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= positions[i], healths[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>directions[i] == 'L'</code> 或 <code>directions[i] == 'R'</code></li>
	<li><code>positions</code> 中的所有值互不相同</li>
</ul>

## 解法

<!-- end -->
