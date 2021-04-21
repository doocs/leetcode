# [746. Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs)

[中文文档](/solution/0700-0799/0746.Min%20Cost%20Climbing%20Stairs/README.md)

## Description

<p>You are given an integer array <code>cost</code> where <code>cost[i]</code> is the cost of <code>i<sup>th</sup></code> step on a staircase. Once you pay the cost, you can either climb one or two steps.</p>

<p>You can either start from the step with index <code>0</code>, or the step with index <code>1</code>.</p>

<p>Return <em>the minimum cost to reach the top of the floor</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> cost = [10,15,20]
<strong>Output:</strong> 15
<strong>Explanation:</strong> Cheapest is: start on cost[1], pay that cost, and go to the top.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> cost = [1,100,1,1,1,100,1,1,100,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Cheapest is: start on cost[0], and only step on 1s, skipping cost[3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= cost.length &lt;= 1000</code></li>
	<li><code>0 &lt;= cost[i] &lt;= 999</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        pre = cur = 0
        n = len(cost)
        for i in range(1, n):
            t = min(cost[i] + cur, cost[i - 1] + pre)
            pre, cur = cur, t
        return cur
```

### **Java**

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int pre = 0, cur = 0;
        for (int i = 1, n = cost.length; i < n; ++i) {
            int t = Math.min(cost[i] + cur, cost[i - 1] + pre);
            pre = cur;
            cur = t;
        }
        return cur;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
