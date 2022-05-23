# [2018. 判断单词是否能放入填字游戏内](https://leetcode.cn/problems/check-if-word-can-be-placed-in-crossword)

[English Version](/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的矩阵&nbsp;<code>board</code>&nbsp;，它代表一个填字游戏&nbsp;<strong>当前</strong>&nbsp;的状态。填字游戏格子中包含小写英文字母（已填入的单词），表示&nbsp;<strong>空</strong>&nbsp;格的&nbsp;<code>' '</code>&nbsp;和表示&nbsp;<strong>障碍</strong>&nbsp;格子的&nbsp;<code>'#'</code>&nbsp;。</p>

<p>如果满足以下条件，那么我们可以 <strong>水平</strong>&nbsp;（从左到右 <strong>或者</strong>&nbsp;从右到左）或 <strong>竖直</strong>&nbsp;（从上到下 <strong>或者</strong>&nbsp;从下到上）填入一个单词：</p>

<ul>
	<li>该单词不占据任何&nbsp;<code>'#'</code>&nbsp;对应的格子。</li>
	<li>每个字母对应的格子要么是&nbsp;<code>' '</code>&nbsp;（空格）要么与 <code>board</code>&nbsp;中已有字母 <strong>匹配</strong>&nbsp;。</li>
	<li>如果单词是 <strong>水平</strong>&nbsp;放置的，那么该单词左边和右边 <strong>相邻</strong>&nbsp;格子不能为&nbsp;<code>' '</code>&nbsp;或小写英文字母。</li>
	<li>如果单词是&nbsp;<strong>竖直</strong>&nbsp;放置的，那么该单词上边和下边&nbsp;<strong>相邻</strong><strong>&nbsp;</strong>格子不能为&nbsp;<code>' '</code>&nbsp;或小写英文字母。</li>
</ul>

<p>给你一个字符串&nbsp;<code>word</code>&nbsp;，如果&nbsp;<code>word</code>&nbsp;可以被放入&nbsp;<code>board</code>&nbsp;中，请你返回&nbsp;<code>true</code>&nbsp;，否则请返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-1.png" style="width: 170px; height: 150px;" /></p>

<pre>
<b>输入：</b>board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word = "abc"
<b>输出：</b>true
<b>解释：</b>单词 "abc" 可以如上图放置（从上往下）。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/c2.png" style="width: 170px; height: 151px;" /></p>

<pre>
<b>输入：</b>board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word = "ac"
<b>输出：</b>false
<b>解释：</b>无法放置单词，因为放置该单词后上方或者下方相邻格会有空格。</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-2.png" style="width: 171px; height: 146px;" /></p>

<pre>
<b>输入：</b>board = [["#", " ", "#"], [" ", " ", "#"], ["#", " ", "c"]], word = "ca"
<b>输出：</b>true
<b>解释：</b>单词 "ca" 可以如上图放置（从右到左）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m * n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>board[i][j]</code>&nbsp;可能为&nbsp;<code>' '</code>&nbsp;，<code>'#'</code>&nbsp;或者一个小写英文字母。</li>
	<li><code>1 &lt;= word.length &lt;= max(m, n)</code></li>
	<li><code>word</code>&nbsp;只包含小写英文字母。</li>
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
