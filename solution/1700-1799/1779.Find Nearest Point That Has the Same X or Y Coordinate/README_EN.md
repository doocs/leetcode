# [1779. Find Nearest Point That Has the Same X or Y Coordinate](https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate)

[中文文档](/solution/1700-1799/1779.Find%20Nearest%20Point%20That%20Has%20the%20Same%20X%20or%20Y%20Coordinate/README.md)

## Description

<p>You are given two integers, <code>x</code> and <code>y</code>, which represent your current location on a Cartesian grid: <code>(x, y)</code>. You are also given an array <code>points</code> where each <code>points[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> represents that a point exists at <code>(a<sub>i</sub>, b<sub>i</sub>)</code>. A point is <strong>valid</strong> if it shares the same x-coordinate or the same y-coordinate as your location.</p>

<p>Return <em>the index <strong>(0-indexed)</strong> of the <strong>valid</strong> point with the smallest <strong>Manhattan distance</strong> from your current location</em>. If there are multiple, return <em>the valid point with the <strong>smallest</strong> index</em>. If there are no valid points, return <code>-1</code>.</p>

<p>The <strong>Manhattan distance</strong> between two points <code>(x<sub>1</sub>, y<sub>1</sub>)</code> and <code>(x<sub>2</sub>, y<sub>2</sub>)</code> is <code>abs(x<sub>1</sub> - x<sub>2</sub>) + abs(y<sub>1</sub> - y<sub>2</sub>)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Of all the points, only [3,1], [2,4] and [4,4] are valid. Of the valid points, [2,4] and [4,4] have the smallest Manhattan distance from your current location, with a distance of 1. [2,4] has the smallest index, so return 2.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 3, y = 4, points = [[3,4]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The answer is allowed to be on the same location as your current location.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> x = 3, y = 4, points = [[2,3]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no valid points.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10<sup>4</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>1 &lt;= x, y, a<sub>i</sub>, b<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def nearestValidPoint(self, x: int, y: int, points: List[List[int]]) -> int:
        ans, mi = -1, inf
        for i, (a, b) in enumerate(points):
            if a == x or b == y:
                d = abs(a - x) + abs(b - y)
                if mi > d:
                    ans, mi = i, d
        return ans
```

### **Java**

```java
class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int ans = -1, mi = 1000000;
        for (int i = 0; i < points.length; ++i) {
            int a = points[i][0], b = points[i][1];
            if (a == x || b == y) {
                int d = Math.abs(a - x) + Math.abs(b - y);
                if (d < mi) {
                    mi = d;
                    ans = i;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int nearestValidPoint(int x, int y, vector<vector<int>>& points) {
        int ans = -1, mi = 1e6;
        for (int i = 0; i < points.size(); ++i) {
            int a = points[i][0], b = points[i][1];
            if (a == x || b == y) {
                int d = abs(a - x) + abs(b - y);
                if (d < mi) {
                    mi = d;
                    ans = i;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func nearestValidPoint(x int, y int, points [][]int) int {
	ans, mi := -1, 1000000
	for i, p := range points {
		a, b := p[0], p[1]
		if a == x || b == y {
			d := abs(a-x) + abs(b-y)
			if d < mi {
				ans, mi = i, d
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function nearestValidPoint(x: number, y: number, points: number[][]): number {
    let res = -1;
    let midDif = Infinity;
    points.forEach(([px, py], i) => {
        if (px != x && py != y) {
            return;
        }
        const dif = Math.abs(px - x) + Math.abs(py - y);
        if (dif < midDif) {
            midDif = dif;
            res = i;
        }
    });
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn nearest_valid_point(x: i32, y: i32, points: Vec<Vec<i32>>) -> i32 {
        let n = points.len();
        let mut min_dif = i32::MAX;
        let mut res = -1;
        for i in 0..n {
            let (p_x, p_y) = (points[i][0], points[i][1]);
            if p_x != x && p_y != y {
                continue;
            }
            let dif = (p_x - x).abs() + (p_y - y).abs();
            if dif < min_dif {
                min_dif = dif;
                res = i as i32;
            }
        }
        res
    }
}
```

### **C**

```c
int nearestValidPoint(int x, int y, int **points, int pointsSize, int *pointsColSize) {
    int ans = -1;
    int min = INT_MAX;
    for (int i = 0; i < pointsSize; i++) {
        int *point = points[i];
        if (point[0] != x && point[1] != y) {
            continue;
        }
        int d = abs(x - point[0]) + abs(y - point[1]);
        if (d < min) {
            min = d;
            ans = i;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
