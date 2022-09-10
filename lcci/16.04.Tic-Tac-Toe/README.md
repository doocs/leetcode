# [面试题 16.04. 井字游戏](https://leetcode.cn/problems/tic-tac-toe-lcci)

[English Version](/lcci/16.04.Tic-Tac-Toe/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符&quot; &quot;，&quot;X&quot;和&quot;O&quot;组成，其中字符&quot; &quot;代表一个空位。</p>
<p>以下是井字游戏的规则：</p>
<ul>
	<li>玩家轮流将字符放入空位（&quot; &quot;）中。</li>
	<li>第一个玩家总是放字符&quot;O&quot;，且第二个玩家总是放字符&quot;X&quot;。</li>
	<li>&quot;X&quot;和&quot;O&quot;只允许放置在空位中，不允许对已放有字符的位置进行填充。</li>
	<li>当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。</li>
	<li>当所有位置非空时，也算为游戏结束。</li>
	<li>如果游戏结束，玩家不允许再放置字符。</li>
</ul>
<p>如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（&quot;X&quot;或&quot;O&quot;）；如果游戏以平局结束，则返回 &quot;Draw&quot;；如果仍会有行动（游戏未结束），则返回 &quot;Pending&quot;。</p>
<p><strong>示例 1：</strong></p>
<pre><strong>输入：</strong> board = [&quot;O X&quot;,&quot; XO&quot;,&quot;X O&quot;]
<strong>输出：</strong> &quot;X&quot;
</pre>
<p><strong>示例 2：</strong></p>
<pre><strong>输入：</strong> board = [&quot;OOX&quot;,&quot;XXO&quot;,&quot;OXO&quot;]
<strong>输出：</strong> &quot;Draw&quot;
<strong>解释：</strong> 没有玩家获胜且不存在空位
</pre>
<p><strong>示例 3：</strong></p>
<pre><strong>输入：</strong> board = [&quot;OOX&quot;,&quot;XXO&quot;,&quot;OX &quot;]
<strong>输出：</strong> &quot;Pending&quot;
<strong>解释：</strong> 没有玩家获胜且仍存在空位
</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>1 &lt;= board.length == board[i].length &lt;= 100</code></li>
	<li>输入一定遵循井字棋规则</li>
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
