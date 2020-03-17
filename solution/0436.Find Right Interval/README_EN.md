# [436. Find Right Interval](https://leetcode.com/problems/find-right-interval)

## Description
<p>Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the &quot;right&quot; of i.</p>

<p>For any interval i, you need to store the minimum interval j&#39;s index, which means that the interval j has the minimum start point to build the &quot;right&quot; relationship for interval i. If the interval j doesn&#39;t exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.</p>

<p><b>Note:</b></p>

<ol>
	<li>You may assume the interval&#39;s end point is always bigger than its start point.</li>
	<li>You may assume none of these intervals have the same start point.</li>
</ol>

<p>&nbsp;</p>

<p><b>Example 1:</b></p>

<pre>
<b>Input:</b> [ [1,2] ]

<b>Output:</b> [-1]

<b>Explanation:</b> There is only one interval in the collection, so it outputs -1.
</pre>

<p>&nbsp;</p>

<p><b>Example 2:</b></p>

<pre>
<b>Input:</b> [ [3,4], [2,3], [1,2] ]

<b>Output:</b> [-1, 0, 1]

<b>Explanation:</b> There is no satisfied &quot;right&quot; interval for [3,4].
For [2,3], the interval [3,4] has minimum-&quot;right&quot; start point;
For [1,2], the interval [2,3] has minimum-&quot;right&quot; start point.
</pre>

<p>&nbsp;</p>

<p><b>Example 3:</b></p>

<pre>
<b>Input:</b> [ [1,4], [2,3], [3,4] ]

<b>Output:</b> [-1, 2, -1]

<b>Explanation:</b> There is no satisfied &quot;right&quot; interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-&quot;right&quot; start point.
</pre>

<p><strong>NOTE:</strong>&nbsp;input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.</p>



## Solution
<!-- Type common method here -->


### Python3
<!-- Type special method here -->

```python

```

### Java
<!-- Type special method here -->

```java

```

### ...
```

```

