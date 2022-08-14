# [2018. Check if Word Can Be Placed In Crossword](https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword)

[中文文档](/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/README.md)

## Description

<p>You are given an <code>m x n</code> matrix <code>board</code>, representing the<strong> current </strong>state of a crossword puzzle. The crossword contains lowercase English letters (from solved words), <code>&#39; &#39;</code> to represent any <strong>empty </strong>cells, and <code>&#39;#&#39;</code> to represent any <strong>blocked</strong> cells.</p>

<p>A word can be placed<strong> horizontally</strong> (left to right <strong>or</strong> right to left) or <strong>vertically</strong> (top to bottom <strong>or</strong> bottom to top) in the board if:</p>

<ul>
	<li>It does not occupy a cell containing the character <code>&#39;#&#39;</code>.</li>
	<li>The cell each letter is placed in must either be <code>&#39; &#39;</code> (empty) or <strong>match</strong> the letter already on the <code>board</code>.</li>
	<li>There must not be any empty cells <code>&#39; &#39;</code> or other lowercase letters <strong>directly left or right</strong><strong> </strong>of the word if the word was placed <strong>horizontally</strong>.</li>
	<li>There must not be any empty cells <code>&#39; &#39;</code> or other lowercase letters <strong>directly above or below</strong> the word if the word was placed <strong>vertically</strong>.</li>
</ul>

<p>Given a string <code>word</code>, return <code>true</code><em> if </em><code>word</code><em> can be placed in </em><code>board</code><em>, or </em><code>false</code><em> <strong>otherwise</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-ex1-1.png" style="width: 478px; height: 180px;" />
<pre>
<strong>Input:</strong> board = [[&quot;#&quot;, &quot; &quot;, &quot;#&quot;], [&quot; &quot;, &quot; &quot;, &quot;#&quot;], [&quot;#&quot;, &quot;c&quot;, &quot; &quot;]], word = &quot;abc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The word &quot;abc&quot; can be placed as shown above (top to bottom).
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-ex2-1.png" style="width: 180px; height: 180px;" />
<pre>
<strong>Input:</strong> board = [[&quot; &quot;, &quot;#&quot;, &quot;a&quot;], [&quot; &quot;, &quot;#&quot;, &quot;c&quot;], [&quot; &quot;, &quot;#&quot;, &quot;a&quot;]], word = &quot;ac&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to place the word because there will always be a space/letter above or below it.</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2018.Check%20if%20Word%20Can%20Be%20Placed%20In%20Crossword/images/crossword-ex3-1.png" style="width: 478px; height: 180px;" />
<pre>
<strong>Input:</strong> board = [[&quot;#&quot;, &quot; &quot;, &quot;#&quot;], [&quot; &quot;, &quot; &quot;, &quot;#&quot;], [&quot;#&quot;, &quot; &quot;, &quot;c&quot;]], word = &quot;ca&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The word &quot;ca&quot; can be placed as shown above (right to left). 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m * n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>board[i][j]</code> will be <code>&#39; &#39;</code>, <code>&#39;#&#39;</code>, or a lowercase English letter.</li>
	<li><code>1 &lt;= word.length &lt;= max(m, n)</code></li>
	<li><code>word</code> will contain only lowercase English letters.</li>
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
