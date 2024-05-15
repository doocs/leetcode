---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2069.Walking%20Robot%20Simulation%20II/README.md
rating: 1918
tags:
    - 设计
    - 模拟
---

# [2069. 模拟行走机器人 II](https://leetcode.cn/problems/walking-robot-simulation-ii)

[English Version](/solution/2000-2099/2069.Walking%20Robot%20Simulation%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个在 XY 平面上的&nbsp;<code>width x height</code>&nbsp;的网格图，<strong>左下角</strong>&nbsp;的格子为&nbsp;<code>(0, 0)</code>&nbsp;，<strong>右上角</strong>&nbsp;的格子为&nbsp;<code>(width - 1, height - 1)</code>&nbsp;。网格图中相邻格子为四个基本方向之一（<code>"North"</code>，<code>"East"</code>，<code>"South"</code>&nbsp;和&nbsp;<code>"West"</code>）。一个机器人 <strong>初始</strong>&nbsp;在格子&nbsp;<code>(0, 0)</code>&nbsp;，方向为&nbsp;<code>"East"</code>&nbsp;。</p>

<p>机器人可以根据指令移动指定的 <strong>步数</strong>&nbsp;。每一步，它可以执行以下操作。</p>

<ol>
	<li>沿着当前方向尝试 <strong>往前一步</strong>&nbsp;。</li>
	<li>如果机器人下一步将到达的格子 <strong>超出了边界</strong>&nbsp;，机器人会 <strong>逆时针</strong>&nbsp;转 90 度，然后再尝试往前一步。</li>
</ol>

<p>如果机器人完成了指令要求的移动步数，它将停止移动并等待下一个指令。</p>

<p>请你实现&nbsp;<code>Robot</code>&nbsp;类：</p>

<ul>
	<li><code>Robot(int width, int height)</code>&nbsp;初始化一个&nbsp;<code>width x height</code>&nbsp;的网格图，机器人初始在&nbsp;<code>(0, 0)</code>&nbsp;，方向朝&nbsp;<code>"East"</code>&nbsp;。</li>
	<li><code>void step(int num)</code>&nbsp;给机器人下达前进&nbsp;<code>num</code>&nbsp;步的指令。</li>
	<li><code>int[] getPos()</code>&nbsp;返回机器人当前所处的格子位置，用一个长度为 2 的数组&nbsp;<code>[x, y]</code>&nbsp;表示。</li>
	<li><code>String getDir()</code>&nbsp;返回当前机器人的朝向，为&nbsp;<code>"North"</code>&nbsp;，<code>"East"</code>&nbsp;，<code>"South"</code>&nbsp;或者&nbsp;<code>"West"</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="example-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2069.Walking%20Robot%20Simulation%20II/images/example-1.png" style="width: 498px; height: 268px;" /></p>

<pre>
<strong>输入：</strong>
["Robot", "step", "step", "getPos", "getDir", "step", "step", "step", "getPos", "getDir"]
[[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
<strong>输出：</strong>
[null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]

<strong>解释：</strong>
Robot robot = new Robot(6, 3); // 初始化网格图，机器人在 (0, 0) ，朝东。
robot.step(2);  // 机器人朝东移动 2 步，到达 (2, 0) ，并朝东。
robot.step(2);  // 机器人朝东移动 2 步，到达 (4, 0) ，并朝东。
robot.getPos(); // 返回 [4, 0]
robot.getDir(); // 返回 "East"
robot.step(2);  // 朝东移动 1 步到达 (5, 0) ，并朝东。
                // 下一步继续往东移动将出界，所以逆时针转变方向朝北。
                // 然后，往北移动 1 步到达 (5, 1) ，并朝北。
robot.step(1);  // 朝北移动 1 步到达 (5, 2) ，并朝 <strong>北</strong> （不是朝西）。
robot.step(4);  // 下一步继续往北移动将出界，所以逆时针转变方向朝西。
                // 然后，移动 4 步到 (1, 2) ，并朝西。
robot.getPos(); // 返回 [1, 2]
robot.getDir(); // 返回 "West"

</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= width, height &lt;= 100</code></li>
	<li><code>1 &lt;= num &lt;= 10<sup>5</sup></code></li>
	<li><code>step</code> ，<code>getPos</code>&nbsp;和&nbsp;<code>getDir</code>&nbsp;<strong>总共&nbsp;</strong>调用次数不超过&nbsp;<code>10<sup>4</sup></code>&nbsp;次。</li>
</ul>

## 解法

<!-- end -->
