# [1138. Alphabet Board Path](https://leetcode.com/problems/alphabet-board-path)

[中文文档](/solution/1100-1199/1138.Alphabet%20Board%20Path/README.md)

## Description

<p>On an alphabet board, we start at position <code>(0, 0)</code>, corresponding to character&nbsp;<code>board[0][0]</code>.</p>

<p>Here, <code>board = [&quot;abcde&quot;, &quot;fghij&quot;, &quot;klmno&quot;, &quot;pqrst&quot;, &quot;uvwxy&quot;, &quot;z&quot;]</code>, as shown in the diagram below.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1138.Alphabet%20Board%20Path/images/azboard.png" style="width: 250px; height: 317px;" /></p>

<p>We may make the following moves:</p>

<ul>
    <li><code>&#39;U&#39;</code> moves our position up one row, if the position exists on the board;</li>
    <li><code>&#39;D&#39;</code> moves our position down one row, if the position exists on the board;</li>
    <li><code>&#39;L&#39;</code> moves our position left one column, if the position exists on the board;</li>
    <li><code>&#39;R&#39;</code> moves our position right one column, if the position exists on the board;</li>
    <li><code>&#39;!&#39;</code>&nbsp;adds the character <code>board[r][c]</code> at our current position <code>(r, c)</code>&nbsp;to the&nbsp;answer.</li>
</ul>

<p>(Here, the only positions that exist on the board are positions with letters on them.)</p>

<p>Return a sequence of moves that makes our answer equal to <code>target</code>&nbsp;in the minimum number of moves.&nbsp; You may return any path that does so.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> target = "leet"


<strong>Output:</strong> "DDR!UURRR!!DDD!"


</pre><p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> target = "code"


<strong>Output:</strong> "RR!DDRR!UUL!R!"


</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= target.length &lt;= 100</code></li>
    <li><code>target</code> consists only of English lowercase letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
