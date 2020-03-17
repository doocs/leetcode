# [806. Number of Lines To Write String](https://leetcode.com/problems/number-of-lines-to-write-string)

## Description
<p>We are to write the letters of a given string <code>S</code>, from left to right into lines. Each line has maximum width 100 units, and if writing a letter would cause the width of the line to exceed 100 units, it is written on the next line. We are given an array&nbsp;<code>widths</code>, an array where widths[0] is the width of &#39;a&#39;, widths[1] is the width of &#39;b&#39;, ..., and widths[25] is the width of &#39;z&#39;.</p>

<p>Now answer two questions: how many lines have at least one character from <code>S</code>, and what is the width used by the last such line? Return your answer as an integer list of length 2.</p>

<p>&nbsp;</p>

<pre>
<strong>Example :</strong>
<strong>Input:</strong> 
widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = &quot;abcdefghijklmnopqrstuvwxyz&quot;
<strong>Output:</strong> [3, 60]
<strong>Explanation: </strong>
All letters have the same length of 10. To write all 26 letters,
we need two full lines and one line with 60 units.
</pre>

<pre>
<strong>Example :</strong>
<strong>Input:</strong> 
widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = &quot;bbbcccdddaaa&quot;
<strong>Output:</strong> [2, 4]
<strong>Explanation: </strong>
All letters except &#39;a&#39; have the same length of 10, and 
&quot;bbbcccdddaa&quot; will cover 9 * 10 + 2 * 4 = 98 units.
For the last &#39;a&#39;, it is written on the second line because
there is only 2 units left in the first line.
So the answer is 2 lines, plus 4 units in the second line.
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The length of <code>S</code> will be in the range&nbsp;[1, 1000].</li>
	<li><code>S</code> will only contain lowercase letters.</li>
	<li><code>widths</code> is&nbsp;an array of length <code>26</code>.</li>
	<li><code>widths[i]</code> will be in the range of <code>[2, 10]</code>.</li>
</ul>



## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
