# [1728. 猫和老鼠 II](https://leetcode.cn/problems/cat-and-mouse-ii)

[English Version](/solution/1700-1799/1728.Cat%20and%20Mouse%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。</p>

<p>它们所处的环境设定是一个 <code>rows x cols</code> 的方格 <code>grid</code> ，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。</p>

<ul>
	<li>玩家由字符 <code>'C'</code> （代表猫）和 <code>'M'</code> （代表老鼠）表示。</li>
	<li>地板由字符 <code>'.'</code> 表示，玩家可以通过这个格子。</li>
	<li>墙用字符 <code>'#'</code> 表示，玩家不能通过这个格子。</li>
	<li>食物用字符 <code>'F'</code> 表示，玩家可以通过这个格子。</li>
	<li>字符 <code>'C'</code> ， <code>'M'</code> 和 <code>'F'</code> 在 <code>grid</code> 中都只会出现一次。</li>
</ul>

<p>猫和老鼠按照如下规则移动：</p>

<ul>
	<li>老鼠 <strong>先移动</strong> ，然后两名玩家轮流移动。</li>
	<li>每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出 <code>grid</code> 。</li>
	<li><code>catJump</code> 和 <code>mouseJump</code> 是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。</li>
	<li>它们可以停留在原地。</li>
	<li>老鼠可以跳跃过猫的位置。</li>
</ul>

<p>游戏有 4 种方式会结束：</p>

<ul>
	<li>如果猫跟老鼠处在相同的位置，那么猫获胜。</li>
	<li>如果猫先到达食物，那么猫获胜。</li>
	<li>如果老鼠先到达食物，那么老鼠获胜。</li>
	<li>如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。</li>
</ul>

<p>给你 <code>rows x cols</code> 的矩阵 <code>grid</code> 和两个整数 <code>catJump</code> 和 <code>mouseJump</code> ，双方都采取最优策略，如果老鼠获胜，那么请你返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1728.Cat%20and%20Mouse%20II/images/sample_111_1955.png" style="width: 580px; height: 239px;" /></strong></p>

<pre>
<b>输入：</b>grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
<b>输出：</b>true
<b>解释：</b>猫无法抓到老鼠，也没法比老鼠先到达食物。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1728.Cat%20and%20Mouse%20II/images/sample_2_1955.png" style="width: 580px; height: 175px;" /></p>

<pre>
<b>输入：</b>grid = ["M.C...F"], catJump = 1, mouseJump = 4
<b>输出：</b>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>grid = ["M.C...F"], catJump = 1, mouseJump = 3
<b>输出：</b>false
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
<b>输出：</b>false
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<b>输入：</b>grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 1
<b>输出：</b>true
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>rows == grid.length</code></li>
	<li><code>cols = grid[i].length</code></li>
	<li><code>1 <= rows, cols <= 8</code></li>
	<li><code>grid[i][j]</code> 只包含字符 <code>'C'</code> ，<code>'M'</code> ，<code>'F'</code> ，<code>'.'</code> 和 <code>'#'</code> 。</li>
	<li><code>grid</code> 中只包含一个 <code>'C'</code> ，<code>'M'</code> 和 <code>'F'</code> 。</li>
	<li><code>1 <= catJump, mouseJump <= 8</code></li>
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
