---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/README.md
---

<!-- problem:start -->

# [3283. 吃掉所有兵需要的最多移动次数](https://leetcode.cn/problems/maximum-number-of-moves-to-kill-all-pawns)

[English Version](/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>50 x 50</code>&nbsp;的国际象棋棋盘，棋盘上有 <strong>一个</strong>&nbsp;马和一些兵。给你两个整数&nbsp;<code>kx</code> 和&nbsp;<code>ky</code>&nbsp;，其中&nbsp;<code>(kx, ky)</code>&nbsp;表示马所在的位置，同时还有一个二维数组&nbsp;<code>positions</code>&nbsp;，其中&nbsp;<code>positions[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;表示第 <code>i</code>&nbsp;个兵在棋盘上的位置。</p>

<p>Alice 和 Bob 玩一个回合制游戏，Alice 先手。玩家的一次操作中，可以执行以下操作：</p>

<ul>
	<li>玩家选择一个仍然在棋盘上的兵，然后移动马，通过 <strong>最少</strong>&nbsp;的 <strong>步数</strong> 吃掉这个兵。<strong>注意</strong>&nbsp;，玩家可以选择&nbsp;<strong>任意</strong>&nbsp;一个兵，<strong>不一定</strong>&nbsp;要选择从马的位置出发&nbsp;<strong>最少</strong>&nbsp;移动步数的兵。</li>
	<li><span>在马吃兵的过程中，马 <strong>可能</strong>&nbsp;会经过一些其他兵的位置，但这些兵 <strong>不会</strong>&nbsp;被吃掉。<strong>只有</strong>&nbsp;选中的兵在这个回合中被吃掉。</span></li>
</ul>

<p>Alice 的目标是 <strong>最大化</strong>&nbsp;两名玩家的 <strong>总</strong>&nbsp;移动次数，直到棋盘上不再存在兵，而 Bob 的目标是 <strong>最小化</strong>&nbsp;总移动次数。</p>

<p>假设两名玩家都采用 <strong>最优</strong>&nbsp;策略，请你返回 Alice 可以达到的 <strong>最大</strong>&nbsp;总移动次数。</p>

<p>在一次&nbsp;<strong>移动</strong>&nbsp;中，如下图所示，马有 8 个可以移动到的位置，每个移动位置都是沿着坐标轴的一个方向前进 2 格，然后沿着垂直的方向前进 1 格。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/images/chess_knight.jpg" style="width: 275px; height: 273px;" /></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>kx = 1, ky = 1, positions = [[0,0]]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/images/gif3.gif" style="width: 275px; height: 275px;" /></p>

<p>马需要移动 4 步吃掉&nbsp;<code>(0, 0)</code>&nbsp;处的兵。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>kx = 0, ky = 2, positions = [[1,1],[2,2],[3,3]]</span></p>

<p><span class="example-io"><b>输出：</b>8</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/images/gif4.gif" style="width: 320px; height: 320px;" /></strong></p>

<ul>
	<li>Alice 选择&nbsp;<code>(2, 2)</code>&nbsp;处的兵，移动马吃掉它需要 2 步：<code>(0, 2) -&gt; (1, 4) -&gt; (2, 2)</code>&nbsp;。</li>
	<li>Bob 选择&nbsp;<code>(3, 3)</code>&nbsp;处的兵，移动马吃掉它需要 2 步：<code>(2, 2) -&gt; (4, 1) -&gt; (3, 3)</code>&nbsp;。</li>
	<li>Alice 选择&nbsp;<code>(1, 1)</code>&nbsp;处的兵，移动马吃掉它需要 4 步：<code>(3, 3) -&gt; (4, 1) -&gt; (2, 2) -&gt; (0, 3) -&gt; (1, 1)</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>kx = 0, ky = 0, positions = [[1,2],[2,4]]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>Alice 选择&nbsp;<code>(2, 4)</code>&nbsp;处的兵，移动马吃掉它需要 2 步：<code>(0, 0) -&gt; (1, 2) -&gt; (2, 4)</code>&nbsp;。注意，<code>(1, 2)</code>&nbsp;处的兵不会被吃掉。</li>
	<li>Bob 选择&nbsp;<code>(1, 2)</code>&nbsp;处的兵，移动马吃掉它需要 1 步：<code>(2, 4) -&gt; (1, 2)</code>&nbsp;。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= kx, ky &lt;= 49</code></li>
	<li><code>1 &lt;= positions.length &lt;= 15</code></li>
	<li><code>positions[i].length == 2</code></li>
	<li><code>0 &lt;= positions[i][0], positions[i][1] &lt;= 49</code></li>
	<li><code>positions[i]</code>&nbsp;两两互不相同。</li>
	<li>输入保证对于所有&nbsp;<code>0 &lt;= i &lt; positions.length</code>&nbsp;，都有&nbsp;<code>positions[i] != [kx, ky]</code>&nbsp;。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
