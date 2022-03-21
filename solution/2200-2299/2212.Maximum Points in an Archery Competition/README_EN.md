# [2212. Maximum Points in an Archery Competition](https://leetcode.com/problems/maximum-points-in-an-archery-competition)

[中文文档](/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/README.md)

## Description

<p>Alice and Bob are opponents in an archery competition. The competition has set the following rules:</p>

<ol>
	<li>Alice first shoots <code>numArrows</code> arrows and then Bob shoots <code>numArrows</code> arrows.</li>
	<li>The points are then calculated as follows:
	<ol>
		<li>The target has integer scoring sections ranging from <code>0</code> to <code>11</code> <strong>inclusive</strong>.</li>
		<li>For <strong>each</strong> section of the target with score <code>k</code> (in between <code>0</code> to <code>11</code>), say Alice and Bob have shot <code>a<sub>k</sub></code> and <code>b<sub>k</sub></code> arrows on that section respectively. If <code>a<sub>k</sub> &gt;= b<sub>k</sub></code>, then Alice takes <code>k</code> points. If <code>a<sub>k</sub> &lt; b<sub>k</sub></code>, then Bob takes <code>k</code> points.</li>
		<li>However, if <code>a<sub>k</sub> == b<sub>k</sub> == 0</code>, then <strong>nobody</strong> takes <code>k</code> points.</li>
	</ol>
	</li>
</ol>

<ul>
	<li>
	<p>For example, if Alice and Bob both shot <code>2</code> arrows on the section with score <code>11</code>, then Alice takes <code>11</code> points. On the other hand, if Alice shot <code>0</code> arrows on the section with score <code>11</code> and Bob shot <code>2</code> arrows on that same section, then Bob takes <code>11</code> points.</p>
	</li>
</ul>

<p>You are given the integer <code>numArrows</code> and an integer array <code>aliceArrows</code> of size <code>12</code>, which represents the number of arrows Alice shot on each scoring section from <code>0</code> to <code>11</code>. Now, Bob wants to <strong>maximize</strong> the total number of points he can obtain.</p>

<p>Return <em>the array </em><code>bobArrows</code><em> which represents the number of arrows Bob shot on <strong>each</strong> scoring section from </em><code>0</code><em> to </em><code>11</code>. The sum of the values in <code>bobArrows</code> should equal <code>numArrows</code>.</p>

<p>If there are multiple ways for Bob to earn the maximum total points, return <strong>any</strong> one of them.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/images/ex1.jpg" style="width: 600px; height: 120px;" />
<pre>
<strong>Input:</strong> numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
<strong>Output:</strong> [0,0,0,0,1,1,0,0,1,2,3,1]
<strong>Explanation:</strong> The table above shows how the competition is scored. 
Bob earns a total point of 4 + 5 + 8 + 9 + 10 + 11 = 47.
It can be shown that Bob cannot obtain a score higher than 47 points.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/images/ex2new.jpg" style="width: 600px; height: 117px;" />
<pre>
<strong>Input:</strong> numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
<strong>Output:</strong> [0,0,0,0,0,0,0,0,1,1,1,0]
<strong>Explanation:</strong> The table above shows how the competition is scored.
Bob earns a total point of 8 + 9 + 10 = 27.
It can be shown that Bob cannot obtain a score higher than 27 points.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numArrows &lt;= 10<sup>5</sup></code></li>
	<li><code>aliceArrows.length == bobArrows.length == 12</code></li>
	<li><code>0 &lt;= aliceArrows[i], bobArrows[i] &lt;= numArrows</code></li>
	<li><code>sum(aliceArrows[i]) == numArrows</code></li>
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
