# [773. 滑动谜题](https://leetcode-cn.com/problems/sliding-puzzle)

[English Version](/solution/0700-0799/0773.Sliding%20Puzzle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个 2 x 3 的板上（<code>board</code>）有 5 块砖瓦，用数字 <code>1~5</code> 来表示, 以及一块空缺用&nbsp;<code>0</code>&nbsp;来表示.</p>

<p>一次移动定义为选择&nbsp;<code>0</code>&nbsp;与一个相邻的数字（上下左右）进行交换.</p>

<p>最终当板&nbsp;<code>board</code>&nbsp;的结果是&nbsp;<code>[[1,2,3],[4,5,0]]</code>&nbsp;谜板被解开。</p>

<p>给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>board = [[1,2,3],[4,0,5]]
<strong>输出：</strong>1
<strong>解释：</strong>交换 0 和 5 ，1 步完成
</pre>

<pre>
<strong>输入：</strong>board = [[1,2,3],[5,4,0]]
<strong>输出：</strong>-1
<strong>解释：</strong>没有办法完成谜板
</pre>

<pre>
<strong>输入：</strong>board = [[4,1,2],[5,0,3]]
<strong>输出：</strong>5
<strong>解释：</strong>
最少完成谜板的最少移动次数是 5 ，
一种移动路径:
尚未移动: [[4,1,2],[5,0,3]]
移动 1 次: [[4,1,2],[0,5,3]]
移动 2 次: [[0,1,2],[4,5,3]]
移动 3 次: [[1,0,2],[4,5,3]]
移动 4 次: [[1,2,0],[4,5,3]]
移动 5 次: [[1,2,3],[4,5,0]]
</pre>

<pre>
<strong>输入：</strong>board = [[3,2,4],[1,5,0]]
<strong>输出：</strong>14
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>board</code>&nbsp;是一个如上所述的 2 x 3 的数组.</li>
	<li><code>board[i][j]</code>&nbsp;是一个&nbsp;<code>[0, 1, 2, 3, 4, 5]</code>&nbsp;的排列.</li>
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
