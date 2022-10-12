# [1037. Valid Boomerang](https://leetcode.com/problems/valid-boomerang)

[中文文档](/solution/1000-1099/1037.Valid%20Boomerang/README.md)

## Description

<p>Given an array <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents a point on the <strong>X-Y</strong> plane, return <code>true</code> <em>if these points are a <strong>boomerang</strong></em>.</p>

<p>A <strong>boomerang</strong> is a set of three points that are <strong>all distinct</strong> and <strong>not in a straight line</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> points = [[1,1],[2,3],[3,2]]
<strong>Output:</strong> true
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> points = [[1,1],[2,2],[3,3]]
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>points.length == 3</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isBoomerang(self, points: List[List[int]]) -> bool:
        (x1, y1), (x2, y2), (x3, y3) = points
        return (y2 - y1) * (x3 - x2) != (y3 - y2) * (x2 - x1)
```

### **Java**

```java
class Solution {
    public boolean isBoomerang(int[][] points) {
        int x1 = points[0][0], y1 = points[0][1];
        int x2 = points[1][0], y2 = points[1][1];
        int x3 = points[2][0], y3 = points[2][1];
        return (y2 - y1) * (x3 - x2) != (y3 - y2) * (x2 - x1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isBoomerang(vector<vector<int>>& points) {
        int x1 = points[0][0], y1 = points[0][1];
        int x2 = points[1][0], y2 = points[1][1];
        int x3 = points[2][0], y3 = points[2][1];
        return (y2 - y1) * (x3 - x2) != (y3 - y2) * (x2 - x1);
    }
};
```

### **Go**

```go
func isBoomerang(points [][]int) bool {
	x1, y1 := points[0][0], points[0][1]
	x2, y2 := points[1][0], points[1][1]
	x3, y3 := points[2][0], points[2][1]
	return (y2-y1)*(x3-x2) != (y3-y2)*(x2-x1)
}
```

### **TypeScript**

```ts
function isBoomerang(points: number[][]): boolean {
    const [x1, y1] = points[0];
    const [x2, y2] = points[1];
    const [x3, y3] = points[2];
    return (x1 - x2) * (y2 - y3) !== (x2 - x3) * (y1 - y2);
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_boomerang(points: Vec<Vec<i32>>) -> bool {
        let (x1, y1) = (points[0][0], points[0][1]);
        let (x2, y2) = (points[1][0], points[1][1]);
        let (x3, y3) = (points[2][0], points[2][1]);
        (x1 - x2) * (y2 - y3) != (x2 - x3) * (y1 - y2)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
