# [1936. Add Minimum Number of Rungs](https://leetcode.com/problems/add-minimum-number-of-rungs)

[中文文档](/solution/1900-1999/1936.Add%20Minimum%20Number%20of%20Rungs/README.md)

## Description

<p>You are given a <strong>strictly increasing</strong> integer array <code>rungs</code> that represents the <strong>height</strong> of rungs on a ladder. You are currently on the <strong>floor</strong> at height <code>0</code>, and you want to reach the last rung.</p>

<p>You are also given an integer <code>dist</code>. You can only climb to the next highest rung if the distance between where you are currently at (the floor or on a rung) and the next rung is <strong>at most</strong> <code>dist</code>. You are able to insert rungs at any positive <strong>integer</strong> height if a rung is not already there.</p>

<p>Return <em>the <strong>minimum</strong> number of rungs that must be added to the ladder in order for you to climb to the last rung.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rungs = [1,3,5,10], dist = 2
<strong>Output:</strong> 2
<strong>Explanation:
</strong>You currently cannot reach the last rung.
Add rungs at heights 7 and 8 to climb this ladder. 
The ladder will now have rungs at [1,3,5,<u>7</u>,<u>8</u>,10].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rungs = [3,6,8,10], dist = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong>
This ladder can be climbed without adding additional rungs.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> rungs = [3,4,6,7], dist = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong>
You currently cannot reach the first rung from the ground.
Add a rung at height 1 to climb this ladder.
The ladder will now have rungs at [<u>1</u>,3,4,6,7].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rungs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rungs[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= dist &lt;= 10<sup>9</sup></code></li>
	<li><code>rungs</code> is <strong>strictly increasing</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def addRungs(self, rungs: List[int], dist: int) -> int:
        prev = res = 0
        for rung in rungs:
            res += (rung - prev - 1) // dist
            prev = rung
        return res
```

### **Java**

```java
class Solution {
    public int addRungs(int[] rungs, int dist) {
        int res = 0;
        for (int i = 0, prev = 0; i < rungs.length; ++i) {
            res += (rungs[i] - prev - 1) / dist;
            prev = rungs[i];
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
