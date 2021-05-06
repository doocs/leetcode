# [447. Number of Boomerangs](https://leetcode.com/problems/number-of-boomerangs)

[中文文档](/solution/0400-0499/0447.Number%20of%20Boomerangs/README.md)

## Description

<p>You are given <code>n</code> <code>points</code> in the plane that are all <strong>distinct</strong>, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>. A <strong>boomerang</strong> is a tuple of points <code>(i, j, k)</code> such that the distance between <code>i</code> and <code>j</code> equals the distance between <code>i</code> and <code>k</code> <strong>(the order of the tuple matters)</strong>.</p>

<p>Return <em>the number of boomerangs</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> points = [[0,0],[1,0],[2,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[1,1],[2,2],[3,3]]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> points = [[1,1]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == points.length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>All the points are <strong>unique</strong>.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfBoomerangs(self, points: List[List[int]]) -> int:
        n = len(points)
        if n < 3:
            return 0
        number = 0
        for i in range(n):
            distance_counter = collections.Counter()
            for j in range(n):
                if i == j:
                    continue
                x1, y1 = points[i][0], points[i][1]
                x2, y2 = points[j][0], points[j][1]
                distance = (x1 - x2) ** 2 + (y1 - y2) ** 2
                distance_counter[distance] += 1
            number += sum([val * (val - 1) for val in distance_counter.values()])
        return number
```

### **Java**

```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        if (n < 3) {
            return 0;
        }
        int number = 0;
        for (int i = 0; i < n; ++i) {
            Map<Integer, Integer> distanceCounter = new HashMap<>();
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                int distance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                distanceCounter.put(distance, distanceCounter.getOrDefault(distance, 0) + 1);
            }
            for (int val : distanceCounter.values()) {
                number += val * (val - 1);
            }
        }
        return number;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
