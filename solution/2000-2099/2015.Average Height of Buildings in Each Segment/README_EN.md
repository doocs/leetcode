# [2015. Average Height of Buildings in Each Segment](https://leetcode.com/problems/average-height-of-buildings-in-each-segment)

[中文文档](/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/README.md)

## Description

<p>A perfectly straight street is represented by a number line. The street has building(s) on it and is represented by a 2D integer array <code>buildings</code>, where <code>buildings[i] = [start<sub>i</sub>, end<sub>i</sub>, height<sub>i</sub>]</code>. This means that there is a building with <code>height<sub>i</sub></code> in the <strong>half-closed segment</strong> <code>[start<sub>i</sub>, end<sub>i</sub>)</code>.</p>

<p>You want to <strong>describe</strong> the heights of the buildings on the street with the <strong>minimum</strong> number of non-overlapping <strong>segments</strong>. The street can be represented by the 2D integer array <code>street</code> where <code>street[j] = [left<sub>j</sub>, right<sub>j</sub>, average<sub>j</sub>]</code> describes a <strong>half-closed segment</strong> <code>[left<sub>j</sub>, right<sub>j</sub>)</code> of the road where the <strong>average</strong> heights of the buildings in the<strong> segment</strong> is <code>average<sub>j</sub></code>.</p>

<ul>
	<li>For example, if <code>buildings = [[1,5,2],[3,10,4]],</code> the street could be represented by <code>street = [[1,3,2],[3,5,3],[5,10,4]]</code> because:
    <ul>
    	<li>From 1 to 3, there is only the first building with an average height of <code>2 / 1 = 2</code>.</li>
    	<li>From 3 to 5, both the first and the second building are there with an average height of <code>(2+4) / 2 = 3</code>.</li>
    	<li>From 5 to 10, there is only the second building with an average height of <code>4 / 1 = 4</code>.</li>
    </ul>
    </li>
</ul>

<p>Given <code>buildings</code>, return <em>the 2D integer array </em><code>street</code><em> as described above (<strong>excluding</strong> any areas of the street where there are no buldings). You may return the array in <strong>any order</strong></em>.</p>

<p>The <strong>average</strong> of <code>n</code> elements is the <strong>sum</strong> of the <code>n</code> elements divided (<strong>integer division</strong>) by <code>n</code>.</p>

<p>A <strong>half-closed segment</strong> <code>[a, b)</code> is the section of the number line between points <code>a</code> and <code>b</code> <strong>including</strong> point <code>a</code> and <strong>not including</strong> point <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/images/image-20210921224001-2.png" style="width: 500px; height: 349px;" />
<pre>
<strong>Input:</strong> buildings = [[1,4,2],[3,9,4]]
<strong>Output:</strong> [[1,3,2],[3,4,3],[4,9,4]]
<strong>Explanation:</strong>
From 1 to 3, there is only the first building with an average height of 2 / 1 = 2.
From 3 to 4, both the first and the second building are there with an average height of (2+4) / 2 = 3.
From 4 to 9, there is only the second building with an average height of 4 / 1 = 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> buildings = [[1,3,2],[2,5,3],[2,8,3]]
<strong>Output:</strong> [[1,3,2],[3,8,3]]
<strong>Explanation:</strong>
From 1 to 2, there is only the first building with an average height of 2 / 1 = 2.
From 2 to 3, all three buildings are there with an average height of (2+3+3) / 3 = 2.
From 3 to 5, both the second and the third building are there with an average height of (3+3) / 2 = 3.
From 5 to 8, there is only the last building with an average height of 3 / 1 = 3.
The average height from 1 to 3 is the same so we can group them into one segment.
The average height from 3 to 8 is the same so we can group them into one segment.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> buildings = [[1,2,1],[5,6,1]]
<strong>Output:</strong> [[1,2,1],[5,6,1]]
<strong>Explanation:</strong>
From 1 to 2, there is only the first building with an average height of 1 / 1 = 1.
From 2 to 5, there are no buildings, so it is not included in the output.
From 5 to 6, there is only the second building with an average height of 1 / 1 = 1.
We cannot group the segments together because an empty space with no buildings seperates the segments.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= buildings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>buildings[i].length == 3</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= height<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
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
