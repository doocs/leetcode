# [256. Paint House](https://leetcode.com/problems/paint-house)

[中文文档](/solution/0200-0299/0256.Paint%20House/README.md)

## Description

<p>There is a row of <code>n</code> houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.</p>

<p>The cost of painting each house with a certain color is represented by an <code>n x 3</code> cost matrix <code>costs</code>.</p>

<ul>
	<li>For example, <code>costs[0][0]</code> is the cost of painting house <code>0</code> with the color red; <code>costs[1][2]</code> is the cost of painting house 1 with color green, and so on...</li>
</ul>

<p>Return <em>the minimum cost to paint all houses</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> costs = [[17,2,17],[16,16,5],[14,3,19]]
<strong>Output:</strong> 10
<strong>Explanation:</strong> Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> costs = [[7,6,2]]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>costs.length == n</code></li>
	<li><code>costs[i].length == 3</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= costs[i][j] &lt;= 20</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCost(self, costs: List[List[int]]) -> int:
        a = b = c = 0
        for ca, cb, cc in costs:
            a, b, c = min(b, c) + ca, min(a, c) + cb, min(a, b) + cc
        return min(a, b, c)
```

### **Java**

```java
class Solution {
    public int minCost(int[][] costs) {
        int r = 0, g = 0, b = 0;
        for (int[] cost : costs) {
            int _r = r, _g = g, _b = b;
            r = Math.min(_g, _b) + cost[0];
            g = Math.min(_r, _b) + cost[1];
            b = Math.min(_r, _g) + cost[2];
        }
        return Math.min(r, Math.min(g, b));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCost(vector<vector<int>>& costs) {
        int r = 0, g = 0, b = 0;
        for (auto& cost : costs) {
            int _r = r, _g = g, _b = b;
            r = min(_g, _b) + cost[0];
            g = min(_r, _b) + cost[1];
            b = min(_r, _g) + cost[2];
        }
        return min(r, min(g, b));
    }
};
```

### **Go**

```go
func minCost(costs [][]int) int {
	r, g, b := 0, 0, 0
	for _, cost := range costs {
		_r, _g, _b := r, g, b
		r = min(_g, _b) + cost[0]
		g = min(_r, _b) + cost[1]
		b = min(_r, _g) + cost[2]
	}
	return min(r, min(g, b))
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} costs
 * @return {number}
 */
var minCost = function (costs) {
    let [a, b, c] = [0, 0, 0];
    for (let [ca, cb, cc] of costs) {
        [a, b, c] = [
            Math.min(b, c) + ca,
            Math.min(a, c) + cb,
            Math.min(a, b) + cc,
        ];
    }
    return Math.min(a, b, c);
};
```

### **...**

```

```

<!-- tabs:end -->
