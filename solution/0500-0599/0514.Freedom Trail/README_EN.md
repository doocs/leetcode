# [514. Freedom Trail](https://leetcode.com/problems/freedom-trail)

[中文文档](/solution/0500-0599/0514.Freedom%20Trail/README.md)

## Description

<p>In the video game Fallout 4, the quest <strong>&quot;Road to Freedom&quot;</strong> requires players to reach a metal dial called the <strong>&quot;Freedom Trail Ring&quot;</strong> and use the dial to spell a specific keyword to open the door.</p>

<p>Given a string <code>ring</code> that represents the code engraved on the outer ring and another string <code>key</code> that represents the keyword that needs to be spelled, return <em>the minimum number of steps to spell all the characters in the keyword</em>.</p>

<p>Initially, the first character of the ring is aligned at the <code>&quot;12:00&quot;</code> direction. You should spell all the characters in <code>key</code> one by one by rotating <code>ring</code> clockwise or anticlockwise to make each character of the string key aligned at the <code>&quot;12:00&quot;</code> direction and then by pressing the center button.</p>

<p>At the stage of rotating the ring to spell the key character <code>key[i]</code>:</p>

<ol>
	<li>You can rotate the ring clockwise or anticlockwise by one place, which counts as <strong>one step</strong>. The final purpose of the rotation is to align one of <code>ring</code>&#39;s characters at the <code>&quot;12:00&quot;</code> direction, where this character must equal <code>key[i]</code>.</li>
	<li>If the character <code>key[i]</code> has been aligned at the <code>&quot;12:00&quot;</code> direction, press the center button to spell, which also counts as <strong>one step</strong>. After the pressing, you could begin to spell the next character in the key (next stage). Otherwise, you have finished all the spelling.</li>
</ol>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0514.Freedom%20Trail/images/ring.jpg" style="width: 450px; height: 450px;" />
<pre>
<strong>Input:</strong> ring = &quot;godding&quot;, key = &quot;gd&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong>
For the first key character &#39;g&#39;, since it is already in place, we just need 1 step to spell this character. 
For the second key character &#39;d&#39;, we need to rotate the ring &quot;godding&quot; anticlockwise by two steps to make it become &quot;ddinggo&quot;.
Also, we need 1 more step for spelling.
So the final output is 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> ring = &quot;godding&quot;, key = &quot;godding&quot;
<strong>Output:</strong> 13
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= ring.length, key.length &lt;= 100</code></li>
	<li><code>ring</code> and <code>key</code> consist of only lower case English letters.</li>
	<li>It is guaranteed that <code>key</code> could always be spelled by rotating <code>ring</code>.</li>
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
