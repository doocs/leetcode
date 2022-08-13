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
        ans = 0
        for p in points:
            counter = Counter()
            for q in points:
                distance = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1])
                counter[distance] += 1
            ans += sum([val * (val - 1) for val in counter.values()])
        return ans
```

### **Java**

```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            Map<Integer, Integer> counter = new HashMap<>();
            for (int[] q : points) {
                int distance = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                counter.put(distance, counter.getOrDefault(distance, 0) + 1);
            }
            for (int val : counter.values()) {
                ans += val * (val - 1);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function numberOfBoomerangs(points: number[][]): number {
    let ans = 0;
    for (let p1 of points) {
        let hashMap: Map<number, number> = new Map();
        for (let p2 of points) {
            const distance = (p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2;
            hashMap.set(distance, (hashMap.get(distance) || 0) + 1);
        }
        for (let [, v] of [...hashMap]) {
            ans += v * (v - 1);
        }
    }
    return ans;
}
```

### **Go**

```go
func numberOfBoomerangs(points [][]int) int {
	ans := 0
	for _, p := range points {
		cnt := make(map[int]int)
		for _, q := range points {
			cnt[(p[0]-q[0])*(p[0]-q[0])+(p[1]-q[1])*(p[1]-q[1])]++
		}
		for _, v := range cnt {
			ans += v * (v - 1)
		}
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfBoomerangs(vector<vector<int>>& points) {
        int ans = 0;
        for (const auto& p : points) {
            unordered_map<int, int> cnt;
            for (const auto& q : points) {
                ++cnt[(p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1])];
            }
            for (const auto& [_, v] : cnt) {
                ans += v * (v - 1);
            }
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
