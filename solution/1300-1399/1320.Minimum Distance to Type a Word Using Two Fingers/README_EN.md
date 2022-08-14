# [1320. Minimum Distance to Type a Word Using Two Fingers](https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers)

[中文文档](/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/README.md)

## Description

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/images/leetcode_keyboard.png" style="width: 349px; height: 209px;" />
<p>You have a keyboard layout as shown above in the <strong>X-Y</strong> plane, where each English uppercase letter is located at some coordinate.</p>

<ul>
	<li>For example, the letter <code>&#39;A&#39;</code> is located at coordinate <code>(0, 0)</code>, the letter <code>&#39;B&#39;</code> is located at coordinate <code>(0, 1)</code>, the letter <code>&#39;P&#39;</code> is located at coordinate <code>(2, 3)</code> and the letter <code>&#39;Z&#39;</code> is located at coordinate <code>(4, 1)</code>.</li>
</ul>

<p>Given the string <code>word</code>, return <em>the minimum total <strong>distance</strong> to type such string using only two fingers</em>.</p>

<p>The <strong>distance</strong> between coordinates <code>(x<sub>1</sub>, y<sub>1</sub>)</code> and <code>(x<sub>2</sub>, y<sub>2</sub>)</code> is <code>|x<sub>1</sub> - x<sub>2</sub>| + |y<sub>1</sub> - y<sub>2</sub>|</code>.</p>

<p><strong>Note</strong> that the initial positions of your two fingers are considered free so do not count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;CAKE&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Using two fingers, one optimal way to type &quot;CAKE&quot; is: 
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
<strong>Explanation:</strong> Using two fingers, one optimal way to type &quot;HAPPY&quot; is:
Finger 1 on letter &#39;H&#39; -&gt; cost = 0
Finger 1 on letter &#39;A&#39; -&gt; cost = Distance from letter &#39;H&#39; to letter &#39;A&#39; = 2
Finger 2 on letter &#39;P&#39; -&gt; cost = 0
Finger 2 on letter &#39;P&#39; -&gt; cost = Distance from letter &#39;P&#39; to letter &#39;P&#39; = 0
Finger 1 on letter &#39;Y&#39; -&gt; cost = Distance from letter &#39;A&#39; to letter &#39;Y&#39; = 4
Total distance = 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= word.length &lt;= 300</code></li>
	<li><code>word</code> consists of uppercase English letters.</li>
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
