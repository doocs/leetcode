# [2247. Maximum Cost of Trip With K Highways](https://leetcode.com/problems/maximum-cost-of-trip-with-k-highways)

[中文文档](/solution/2200-2299/2247.Maximum%20Cost%20of%20Trip%20With%20K%20Highways/README.md)

## Description

<p>A series of highways connect <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>highways</code> where <code>highways[i] = [city1<sub>i</sub>, city2<sub>i</sub>, toll<sub>i</sub>]</code> indicates that there is a highway that connects <code>city1<sub>i</sub></code> and <code>city2<sub>i</sub></code>, allowing a car to go from <code>city1<sub>i</sub></code> to <code>city2<sub>i</sub></code> and <strong>vice versa</strong> for a cost of <code>toll<sub>i</sub></code>.</p>

<p>You are also given an integer <code>k</code>. You are going on a trip that crosses <strong>exactly</strong> <code>k</code> highways. You may start at any city, but you may only visit each city <strong>at most</strong> once during your trip.</p>

<p>Return<em> the <strong>maximum</strong> cost of your trip. If there is no trip that meets the requirements, return </em><code>-1</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2247.Maximum%20Cost%20of%20Trip%20With%20K%20Highways/images/image-20220418173304-1.png" style="height: 200px; width: 327px;" />
<pre>
<strong>Input:</strong> n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], k = 3
<strong>Output:</strong> 17
<strong>Explanation:</strong>
One possible trip is to go from 0 -&gt; 1 -&gt; 4 -&gt; 3. The cost of this trip is 4 + 11 + 2 = 17.
Another possible trip is to go from 4 -&gt; 1 -&gt; 2 -&gt; 3. The cost of this trip is 11 + 3 + 3 = 17.
It can be proven that 17 is the maximum possible cost of any valid trip.

Note that the trip 4 -&gt; 1 -&gt; 0 -&gt; 1 is not allowed because you visit the city 1 twice.

</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2247.Maximum%20Cost%20of%20Trip%20With%20K%20Highways/images/image-20220418173342-2.png" style="height: 200px; width: 217px;" />
<pre>
<strong>Input:</strong> n = 4, highways = [[0,1,3],[2,3,2]], k = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no valid trips of length 2, so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>1 &lt;= highways.length &lt;= 50</code></li>
	<li><code>highways[i].length == 3</code></li>
	<li><code>0 &lt;= city1<sub>i</sub>, city2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>city1<sub>i</sub> != city2<sub>i</sub></code></li>
	<li><code>0 &lt;= toll<sub>i</sub> &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
	<li>There are no duplicate highways.</li>
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
