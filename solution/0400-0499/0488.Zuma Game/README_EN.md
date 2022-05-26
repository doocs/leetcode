# [488. Zuma Game](https://leetcode.com/problems/zuma-game)

[中文文档](/solution/0400-0499/0488.Zuma%20Game/README.md)

## Description

<p>Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.</p>

<p>Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.</p>

<p>Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> board = &quot;WRRBBW&quot;, hand = &quot;RB&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> WRRBBW -&gt; WRR[R]BBW -&gt; WBBW -&gt; WBB[B]W -&gt; WW
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> board = &quot;WWRRBBWW&quot;, hand = &quot;WRBRW&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> WWRRBBWW -&gt; WWRR[R]BBWW -&gt; WWBBWW -&gt; WWBB[B]WW -&gt; WWWW -&gt; empty
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> board = &quot;G&quot;, hand = &quot;GGGGG&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> G -&gt; G[G] -&gt; GG[G] -&gt; empty 
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> board = &quot;RBYYBBRRB&quot;, hand = &quot;YRBGB&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> RBYYBBRRB -&gt; RBYY[Y]BBRRB -&gt; RBBBRRB -&gt; RRRB -&gt; B -&gt; B[B] -&gt; BB[B] -&gt; empty 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>You may assume that the initial row of balls on the table won&rsquo;t have any 3 or more consecutive balls with the same color.</li>
	<li>The number of balls on the table won&#39;t exceed 16, and the string represents these balls is called &quot;board&quot; in the input.</li>
	<li>The number of balls in your hand won&#39;t exceed 5, and the string represents these balls is called &quot;hand&quot; in the input.</li>
	<li>Both input strings will be non-empty and only contain characters &#39;R&#39;,&#39;Y&#39;,&#39;B&#39;,&#39;G&#39;,&#39;W&#39;.</li>
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
