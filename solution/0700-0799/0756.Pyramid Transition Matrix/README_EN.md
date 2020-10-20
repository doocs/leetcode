# [756. Pyramid Transition Matrix](https://leetcode.com/problems/pyramid-transition-matrix)

[中文文档](/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/README.md)

## Description

<p>We are stacking blocks to form a pyramid. Each block has a color which is a one letter string.</p>

<p>We are allowed to place any color block <code>C</code> on top of two adjacent blocks of colors <code>A</code> and <code>B</code>, if and only if <code>ABC</code> is an allowed triple.</p>

<p>We start with a bottom row of <code>bottom</code>, represented as a single string. We also start with a list of allowed triples <code>allowed</code>. Each allowed triple is represented as a string of length 3.</p>

<p>Return true if we can build the pyramid all the way to the top, otherwise false.</p>

<p><b>Example 1:</b></p>

<pre>

<b>Input:</b> bottom = &quot;BCD&quot;, allowed = [&quot;BCG&quot;, &quot;CDE&quot;, &quot;GEA&quot;, &quot;FFF&quot;]

<b>Output:</b> true

<b>Explanation:</b>

We can stack the pyramid like this:

    A

   / \

  G   E

 / \ / \

B   C   D



We are allowed to place G on top of B and C because BCG is an allowed triple.  Similarly, we can place E on top of C and D, then A on top of G and E.</pre>

<p>&nbsp;</p>

<p><b>Example 2:</b></p>

<pre>

<b>Input:</b> bottom = &quot;AABA&quot;, allowed = [&quot;AAA&quot;, &quot;AAB&quot;, &quot;ABA&quot;, &quot;ABB&quot;, &quot;BAC&quot;]

<b>Output:</b> false

<b>Explanation:</b>

We can&#39;t stack the pyramid to the top.

Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.

</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>

    <li><code>bottom</code> will be a string with length in range <code>[2, 8]</code>.</li>

    <li><code>allowed</code> will have length in range <code>[0, 200]</code>.</li>

    <li>Letters in all strings will be chosen from the set <code>{&#39;A&#39;, &#39;B&#39;, &#39;C&#39;, &#39;D&#39;, &#39;E&#39;, &#39;F&#39;, &#39;G&#39;}</code>.</li>

</ol>

<p>&nbsp;</p>

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
