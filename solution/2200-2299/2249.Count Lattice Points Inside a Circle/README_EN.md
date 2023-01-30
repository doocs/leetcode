# [2249. Count Lattice Points Inside a Circle](https://leetcode.com/problems/count-lattice-points-inside-a-circle)

[中文文档](/solution/2200-2299/2249.Count%20Lattice%20Points%20Inside%20a%20Circle/README.md)

## Description

<p>Given a 2D integer array <code>circles</code> where <code>circles[i] = [x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub>]</code> represents the center <code>(x<sub>i</sub>, y<sub>i</sub>)</code> and radius <code>r<sub>i</sub></code> of the <code>i<sup>th</sup></code> circle drawn on a grid, return <em>the <strong>number of lattice points</strong> </em><em>that are present inside <strong>at least one</strong> circle</em>.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>A <strong>lattice point</strong> is a point with integer coordinates.</li>
	<li>Points that lie <strong>on the circumference of a circle</strong> are also considered to be inside it.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2249.Count%20Lattice%20Points%20Inside%20a%20Circle/images/exa-11.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> circles = [[2,2,1]]
<strong>Output:</strong> 5
<strong>Explanation:</strong>
The figure above shows the given circle.
The lattice points present inside the circle are (1, 2), (2, 1), (2, 2), (2, 3), and (3, 2) and are shown in green.
Other points such as (1, 1) and (1, 3), which are shown in red, are not considered inside the circle.
Hence, the number of lattice points present inside at least one circle is 5.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2249.Count%20Lattice%20Points%20Inside%20a%20Circle/images/exa-22.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> circles = [[2,2,2],[3,4,1]]
<strong>Output:</strong> 16
<strong>Explanation:</strong>
The figure above shows the given circles.
There are exactly 16 lattice points which are present inside at least one circle. 
Some of them are (0, 2), (2, 0), (2, 4), (3, 2), and (4, 4).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= circles.length &lt;= 200</code></li>
	<li><code>circles[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 100</code></li>
	<li><code>1 &lt;= r<sub>i</sub> &lt;= min(x<sub>i</sub>, y<sub>i</sub>)</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countLatticePoints(self, circles: List[List[int]]) -> int:
        ans = 0
        mx = max(x + r for x, _, r in circles)
        my = max(y + r for _, y, r in circles)
        for i in range(mx + 1):
            for j in range(my + 1):
                for x, y, r in circles:
                    dx, dy = i - x, j - y
                    if dx * dx + dy * dy <= r * r:
                        ans += 1
                        break
        return ans
```

### **Java**

```java
class Solution {
    public int countLatticePoints(int[][] circles) {
        int mx = 0, my = 0;
        for (var c : circles) {
            mx = Math.max(mx, c[0] + c[2]);
            my = Math.max(my, c[1] + c[2]);
        }
        int ans = 0;
        for (int i = 0; i <= mx; ++i) {
            for (int j = 0; j <= my; ++j) {
                for (var c : circles) {
                    int dx = i - c[0], dy = j - c[1];
                    if (dx * dx + dy * dy <= c[2] * c[2]) {
                        ++ans;
                        break;
                    }
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
    int countLatticePoints(vector<vector<int>>& circles) {
        int mx = 0, my = 0;
        for (auto& c : circles) {
            mx = max(mx, c[0] + c[2]);
            my = max(my, c[1] + c[2]);
        }
        int ans = 0;
        for (int i = 0; i <= mx; ++i) {
            for (int j = 0; j <= my; ++j) {
                for (auto& c : circles) {
                    int dx = i - c[0], dy = j - c[1];
                    if (dx * dx + dy * dy <= c[2] * c[2]) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countLatticePoints(circles [][]int) (ans int) {
	mx, my := 0, 0
	for _, c := range circles {
		mx = max(mx, c[0]+c[2])
		my = max(my, c[1]+c[2])
	}
	for i := 0; i <= mx; i++ {
		for j := 0; j <= my; j++ {
			for _, c := range circles {
				dx, dy := i-c[0], j-c[1]
				if dx*dx+dy*dy <= c[2]*c[2] {
					ans++
					break
				}
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
