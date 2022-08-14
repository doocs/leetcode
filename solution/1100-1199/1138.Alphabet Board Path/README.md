# [1138. 字母板上的路径](https://leetcode.cn/problems/alphabet-board-path)

[English Version](/solution/1100-1199/1138.Alphabet%20Board%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们从一块字母板上的位置&nbsp;<code>(0, 0)</code>&nbsp;出发，该坐标对应的字符为&nbsp;<code>board[0][0]</code>。</p>

<p>在本题里，字母板为<code>board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]</code>，如下所示。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1138.Alphabet%20Board%20Path/images/azboard.png" style="width: 300px;" /></p>

<p>我们可以按下面的指令规则行动：</p>

<ul>
	<li>如果方格存在，<code>'U'</code>&nbsp;意味着将我们的位置上移一行；</li>
	<li>如果方格存在，<code>'D'</code>&nbsp;意味着将我们的位置下移一行；</li>
	<li>如果方格存在，<code>'L'</code>&nbsp;意味着将我们的位置左移一列；</li>
	<li>如果方格存在，<code>'R'</code>&nbsp;意味着将我们的位置右移一列；</li>
	<li><code>'!'</code>&nbsp;会把在我们当前位置 <code>(r, c)</code> 的字符&nbsp;<code>board[r][c]</code>&nbsp;添加到答案中。</li>
</ul>

<p>（注意，字母板上只存在有字母的位置。）</p>

<p>返回指令序列，用最小的行动次数让答案和目标&nbsp;<code>target</code>&nbsp;相同。你可以返回任何达成目标的路径。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = "leet"
<strong>输出：</strong>"DDR!UURRR!!DDD!"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = "code"
<strong>输出：</strong>"RR!DDRR!UUL!R!"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 100</code></li>
	<li><code>target</code>&nbsp;仅含有小写英文字母。</li>
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
