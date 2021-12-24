# [1320. Minimum Distance to Type a Word Using Two Fingers](https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers)

[中文文档](/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/README.md)

## Description

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/images/leetcode_keyboard.png" style="width: 417px; height: 250px;" /></p>

<p>You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, for example, the letter <strong>A</strong> is located at coordinate <strong>(0,0)</strong>, the letter <strong>B</strong> is located at coordinate <strong>(0,1)</strong>, the letter <strong>P</strong> is located at coordinate <strong>(2,3)</strong> and the letter <strong>Z</strong> is located at coordinate <strong>(4,1)</strong>.</p>

<p>Given the string <code>word</code>, return the minimum total distance to type such string using only two&nbsp;fingers. The distance between coordinates <strong>(x<sub>1</sub>,y<sub>1</sub>)</strong> and <strong>(x<sub>2</sub>,y<sub>2</sub>)</strong> is <strong>|x<sub>1</sub> - x<sub>2</sub>| + |y<sub>1</sub> - y<sub>2</sub>|</strong>.&nbsp;</p>

<p>Note that the initial positions of your two&nbsp;fingers are considered free so don&#39;t count towards your total distance, also your two&nbsp;fingers do not have to start at the first letter or the first two&nbsp;letters.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;CAKE&quot;

<strong>Output:</strong> 3

<strong>Explanation: 

</strong>Using two fingers, one optimal way to type &quot;CAKE&quot; is: 

Finger 1 on letter &#39;C&#39; -&gt; cost = 0 

Finger 1 on letter &#39;A&#39; -&gt; cost = Distance from letter &#39;C&#39; to letter &#39;A&#39; = 2 

Finger 2 on letter &#39;K&#39; -&gt; cost = 0 

Finger 2 on letter &#39;E&#39; -&gt; cost = Distance from letter &#39;K&#39; to letter &#39;E&#39; = 1 

Total distance = 3

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;HAPPY&quot;

<strong>Output:</strong> 6

<strong>Explanation: </strong>

Using two fingers, one optimal way to type &quot;HAPPY&quot; is:

Finger 1 on letter &#39;H&#39; -&gt; cost = 0

Finger 1 on letter &#39;A&#39; -&gt; cost = Distance from letter &#39;H&#39; to letter &#39;A&#39; = 2

Finger 2 on letter &#39;P&#39; -&gt; cost = 0

Finger 2 on letter &#39;P&#39; -&gt; cost = Distance from letter &#39;P&#39; to letter &#39;P&#39; = 0

Finger 1 on letter &#39;Y&#39; -&gt; cost = Distance from letter &#39;A&#39; to letter &#39;Y&#39; = 4

Total distance = 6

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;NEW&quot;

<strong>Output:</strong> 3

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;YEAR&quot;

<strong>Output:</strong> 7

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= word.length &lt;= 300</code></li>
	<li>Each <code data-stringify-type="code">word[i]</code>&nbsp;is an English uppercase letter.</li>
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
