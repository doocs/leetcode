# [909. 蛇梯棋](https://leetcode-cn.com/problems/snakes-and-ladders)

[English Version](/solution/0900-0999/0909.Snakes%20and%20Ladders/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>在一块 N x N 的棋盘&nbsp;<code>board</code>&nbsp;上，<strong>从棋盘的左下角开始</strong>，每一行交替方向，按从&nbsp;<code>1</code> 到 <code>N*N</code>&nbsp;的数字给方格编号。例如，对于一块 6 x 6 大小的棋盘，可以编号如下：</p>

<pre><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/31/snakes.png" style="height: 200px; width: 254px;">
</pre>

<p>玩家从棋盘上的方格&nbsp;<code>1</code> （总是在最后一行、第一列）开始出发。</p>

<p>每一次从方格&nbsp;<code>x</code>&nbsp;起始的移动都由以下部分组成：</p>

<ul>
	<li>你选择一个目标方块 <code>S</code>，它的编号是 <code>x+1</code>，<code>x+2</code>，<code>x+3</code>，<code>x+4</code>，<code>x+5</code>，或者 <code>x+6</code>，只要这个数字&nbsp;<code>&lt;= N*N</code>。</li>
	<li>如果 <code>S</code> 有一个蛇或梯子，你就移动到那个蛇或梯子的目的地。否则，你会移动到 <code>S</code>。&nbsp;</li>
</ul>

<p>在 <code>r</code> 行 <code>c</code> 列上的方格里有 &ldquo;蛇&rdquo; 或 &ldquo;梯子&rdquo;；如果 <code>board[r][c] != -1</code>，那个蛇或梯子的目的地将会是 <code>board[r][c]</code>。</p>

<p>注意，你每次移动最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。</p>

<p>返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,35,-1,-1,13,-1],
[-1,-1,-1,-1,-1,-1],
[-1,15,-1,-1,-1,-1]]
<strong>输出：</strong>4
<strong>解释：</strong>
首先，从方格 1 [第 5 行，第 0 列] 开始。
你决定移动到方格 2，并必须爬过梯子移动到到方格 15。
然后你决定移动到方格 17 [第 3 行，第 5 列]，必须爬过蛇到方格 13。
然后你决定移动到方格 14，且必须通过梯子移动到方格 35。
然后你决定移动到方格 36, 游戏结束。
可以证明你需要至少 4 次移动才能到达第 N*N 个方格，所以答案是 4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>2 &lt;= board.length = board[0].length&nbsp;&lt;= 20</code></li>
	<li><code>board[i][j]</code>&nbsp;介于&nbsp;<code>1</code>&nbsp;和&nbsp;<code>N*N</code>&nbsp;之间或者等于&nbsp;<code>-1</code>。</li>
	<li>编号为&nbsp;<code>1</code>&nbsp;的方格上没有蛇或梯子。</li>
	<li>编号为&nbsp;<code>N*N</code>&nbsp;的方格上没有蛇或梯子。</li>
</ol>



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