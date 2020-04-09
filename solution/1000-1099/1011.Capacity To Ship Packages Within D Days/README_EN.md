# [1011. Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days)

## Description
<p>A conveyor belt has packages that must be shipped from one port to another within <code>D</code> days.</p>

<p>The <code>i</code>-th package on the conveyor belt has a weight of <code>weights[i]</code>.&nbsp; Each day, we load the ship with packages on the conveyor belt (in the order given by <code>weights</code>). We may not load more weight than the maximum weight capacity of the ship.</p>

<p>Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within <code>D</code> days.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>weights = <span id="example-input-1-1">[1,2,3,4,5,6,7,8,9,10]</span>, D = <span id="example-input-1-2">5</span>
<strong>Output: </strong><span id="example-output-1">15</span>
<strong>Explanation: </strong>
A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed. 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong>weights = <span id="example-input-2-1">[3,2,2,4,1,4]</span>, D = <span id="example-input-2-2">3</span>
<strong>Output: </strong><span id="example-output-2">6</span>
<strong>Explanation: </strong>
A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong>weights = <span id="example-input-3-1">[1,2,3,1,1]</span>, D = 4
<strong>Output: </strong><span id="example-output-3">3</span>
<strong>Explanation: </strong>
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= D &lt;= weights.length &lt;= 50000</code></li>
	<li><code>1 &lt;= weights[i] &lt;= 500</code></li>
</ol>


## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
