# [1178. Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle)

[中文文档](/solution/1100-1199/1178.Number%20of%20Valid%20Words%20for%20Each%20Puzzle/README.md)

## Description

With respect to a given <code>puzzle</code> string, a <code>word</code> is <em>valid</em>&nbsp;if both the following conditions are satisfied:

<ul>
	<li><code>word</code> contains the first letter of <code>puzzle</code>.</li>
	<li>For each letter in <code>word</code>, that letter is in <code>puzzle</code>.<br />
	For example, if the puzzle is &quot;abcdefg&quot;, then valid words are &quot;faced&quot;, &quot;cabbage&quot;, and &quot;baggage&quot;; while invalid words are &quot;beefed&quot; (doesn&#39;t include &quot;a&quot;) and &quot;based&quot; (includes &quot;s&quot; which isn&#39;t in the puzzle).</li>
</ul>
Return an array <code>answer</code>, where <code>answer[i]</code> is the number of words in the given word list&nbsp;<code>words</code> that are valid with respect to the puzzle <code>puzzles[i]</code>.
<p>&nbsp;</p>
<p><strong>Example :</strong></p>

<pre>
<strong>Input:</strong> 
words = [&quot;aaaa&quot;,&quot;asas&quot;,&quot;able&quot;,&quot;ability&quot;,&quot;actt&quot;,&quot;actor&quot;,&quot;access&quot;], 
puzzles = [&quot;aboveyz&quot;,&quot;abrodyz&quot;,&quot;abslute&quot;,&quot;absoryz&quot;,&quot;actresz&quot;,&quot;gaswxyz&quot;]
<strong>Output:</strong> [1,1,3,2,4,0]
<strong>Explanation:</strong>
1 valid word&nbsp;for &quot;aboveyz&quot; : &quot;aaaa&quot; 
1 valid word&nbsp;for &quot;abrodyz&quot; : &quot;aaaa&quot;
3 valid words for &quot;abslute&quot; : &quot;aaaa&quot;, &quot;asas&quot;, &quot;able&quot;
2 valid words for&nbsp;&quot;absoryz&quot; : &quot;aaaa&quot;, &quot;asas&quot;
4 valid words for&nbsp;&quot;actresz&quot; : &quot;aaaa&quot;, &quot;asas&quot;, &quot;actt&quot;, &quot;access&quot;
There&#39;re&nbsp;no valid words for&nbsp;&quot;gaswxyz&quot; cause none of the words in the list contains letter &#39;g&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10^5</code></li>
	<li><code>4 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>1 &lt;= puzzles.length &lt;= 10^4</code></li>
	<li><code>puzzles[i].length == 7</code></li>
	<li><code>words[i][j]</code>, <code>puzzles[i][j]</code> are English lowercase letters.</li>
	<li>Each <code>puzzles[i] </code>doesn&#39;t contain repeated characters.</li>
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
