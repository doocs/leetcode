# [2101. Detonate the Maximum Bombs](https://leetcode.com/problems/detonate-the-maximum-bombs)

[中文文档](/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/README.md)

## Description

<p>You are given a list of bombs. The <strong>range</strong> of a bomb is defined as the area where its effect can be felt. This area is in the shape of a <strong>circle</strong> with the center as the location of the bomb.</p>

<p>The bombs are represented by a <strong>0-indexed</strong> 2D integer array <code>bombs</code> where <code>bombs[i] = [x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub>]</code>. <code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> denote the X-coordinate and Y-coordinate of the location of the <code>i<sup>th</sup></code> bomb, whereas <code>r<sub>i</sub></code> denotes the <strong>radius</strong> of its range.</p>

<p>You may choose to detonate a <strong>single</strong> bomb. When a bomb is detonated, it will detonate <strong>all bombs</strong> that lie in its range. These bombs will further detonate the bombs that lie in their ranges.</p>

<p>Given the list of <code>bombs</code>, return <em>the <strong>maximum</strong> number of bombs that can be detonated if you are allowed to detonate <strong>only one</strong> bomb</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg-3.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> bombs = [[2,1,3],[6,1,4]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg-2.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> bombs = [[1,1,5],[10,10,5]]
<strong>Output:</strong> 1
<strong>Explanation:
</strong>Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg1.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
<strong>Output:</strong> 5
<strong>Explanation:</strong>
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= bombs.length&nbsp;&lt;= 100</code></li>
	<li><code>bombs[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
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
