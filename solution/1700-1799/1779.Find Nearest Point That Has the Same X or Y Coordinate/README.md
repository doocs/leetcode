# [1779. 找到最近的有相同 X 或 Y 坐标的点](https://leetcode.cn/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate)

[English Version](/solution/1700-1799/1779.Find%20Nearest%20Point%20That%20Has%20the%20Same%20X%20or%20Y%20Coordinate/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;，表示你在一个笛卡尔坐标系下的&nbsp;<code>(x, y)</code>&nbsp;处。同时，在同一个坐标系下给你一个数组&nbsp;<code>points</code>&nbsp;，其中&nbsp;<code>points[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示在&nbsp;<code>(a<sub>i</sub>, b<sub>i</sub>)</code>&nbsp;处有一个点。当一个点与你所在的位置有相同的 <code>x</code> 坐标或者相同的 <code>y</code> 坐标时，我们称这个点是 <b>有效的</b>&nbsp;。</p>

<p>请返回距离你当前位置&nbsp;<strong>曼哈顿距离</strong>&nbsp;最近的&nbsp;<strong>有效</strong>&nbsp;点的下标（下标从 <strong>0</strong> 开始）。如果有多个最近的有效点，请返回下标&nbsp;<strong>最小</strong>&nbsp;的一个。如果没有有效点，请返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>两个点 <code>(x<sub>1</sub>, y<sub>1</sub>)</code>&nbsp;和 <code>(x<sub>2</sub>, y<sub>2</sub>)</code>&nbsp;之间的 <strong>曼哈顿距离</strong>&nbsp;为&nbsp;<code>abs(x<sub>1</sub> - x<sub>2</sub>) + abs(y<sub>1</sub> - y<sub>2</sub>)</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
<b>输出：</b>2
<b>解释：</b>所有点中，[3,1]，[2,4] 和 [4,4] 是有效点。有效点中，[2,4] 和 [4,4] 距离你当前位置的曼哈顿距离最小，都为 1 。[2,4] 的下标最小，所以返回 2 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>x = 3, y = 4, points = [[3,4]]
<b>输出：</b>0
<b>提示：</b>答案可以与你当前所在位置坐标相同。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>x = 3, y = 4, points = [[2,3]]
<b>输出：</b>-1
<b>解释：</b>没有 有效点。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10<sup>4</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>1 &lt;= x, y, a<sub>i</sub>, b<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接遍历**

直接遍历 `points` 数组，对于 $points[i]$，如果 $points[i][0] = x$ 或者 $points[i][1] = y$，则说明 $points[i]$ 是有效点，计算曼哈顿距离，更新最小距离和最小距离的下标。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为 `points` 数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
