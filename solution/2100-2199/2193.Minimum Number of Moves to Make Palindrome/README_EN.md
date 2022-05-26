# [2193. Minimum Number of Moves to Make Palindrome](https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome)

[中文文档](/solution/2100-2199/2193.Minimum%20Number%20of%20Moves%20to%20Make%20Palindrome/README.md)

## Description

<p>You are given a string <code>s</code> consisting only of lowercase English letters.</p>

<p>In one <strong>move</strong>, you can select any two <strong>adjacent</strong> characters of <code>s</code> and swap them.</p>

<p>Return <em>the <strong>minimum number of moves</strong> needed to make</em> <code>s</code> <em>a palindrome</em>.</p>

<p><strong>Note</strong> that the input will be generated such that <code>s</code> can always be converted to a palindrome.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabb&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong>
We can obtain two palindromes from s, &quot;abba&quot; and &quot;baab&quot;. 
- We can obtain &quot;abba&quot; from s in 2 moves: &quot;a<u><strong>ab</strong></u>b&quot; -&gt; &quot;ab<u><strong>ab</strong></u>&quot; -&gt; &quot;abba&quot;.
- We can obtain &quot;baab&quot; from s in 2 moves: &quot;a<u><strong>ab</strong></u>b&quot; -&gt; &quot;<u><strong>ab</strong></u>ab&quot; -&gt; &quot;baab&quot;.
Thus, the minimum number of moves needed to make s a palindrome is 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;letelt&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong>
One of the palindromes we can obtain from s in 2 moves is &quot;lettel&quot;.
One of the ways we can obtain it is &quot;lete<u><strong>lt</strong></u>&quot; -&gt; &quot;let<u><strong>et</strong></u>l&quot; -&gt; &quot;lettel&quot;.
Other palindromes such as &quot;tleelt&quot; can also be obtained in 2 moves.
It can be shown that it is not possible to obtain a palindrome in less than 2 moves.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li><code>s</code> can be converted to a palindrome using a finite number of moves.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
