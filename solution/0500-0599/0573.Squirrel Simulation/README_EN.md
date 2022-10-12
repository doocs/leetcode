# [573. Squirrel Simulation](https://leetcode.com/problems/squirrel-simulation)

[中文文档](/solution/0500-0599/0573.Squirrel%20Simulation/README.md)

## Description

<p>You are given two integers <code>height</code> and <code>width</code> representing a garden of size <code>height x width</code>. You are also given:</p>

<ul>
	<li>an array <code>tree</code> where <code>tree = [tree<sub>r</sub>, tree<sub>c</sub>]</code> is the position of the tree in the garden,</li>
	<li>an array <code>squirrel</code> where <code>squirrel = [squirrel<sub>r</sub>, squirrel<sub>c</sub>]</code> is the position of the squirrel in the garden,</li>
	<li>and an array <code>nuts</code> where <code>nuts[i] = [nut<sub>i<sub>r</sub></sub>, nut<sub>i<sub>c</sub></sub>]</code> is the position of the <code>i<sup>th</sup></code> nut in the garden.</li>
</ul>

<p>The squirrel can only take at most one nut at one time and can move in four directions: up, down, left, and right, to the adjacent cell.</p>

<p>Return <em>the <strong>minimal distance</strong> for the squirrel to collect all the nuts and put them under the tree one by one</em>.</p>

<p>The <strong>distance</strong> is the number of moves.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0573.Squirrel%20Simulation/images/squirrel1-grid.jpg" style="width: 573px; height: 413px;" />
<pre>
<strong>Input:</strong> height = 5, width = 7, tree = [2,2], squirrel = [4,4], nuts = [[3,0], [2,5]]
<strong>Output:</strong> 12
<strong>Explanation:</strong> The squirrel should go to the nut at [2, 5] first to achieve a minimal distance.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0573.Squirrel%20Simulation/images/squirrel2-grid.jpg" style="width: 253px; height: 93px;" />
<pre>
<strong>Input:</strong> height = 1, width = 3, tree = [0,1], squirrel = [0,0], nuts = [[0,2]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= height, width &lt;= 100</code></li>
	<li><code>tree.length == 2</code></li>
	<li><code>squirrel.length == 2</code></li>
	<li><code>1 &lt;= nuts.length &lt;= 5000</code></li>
	<li><code>nuts[i].length == 2</code></li>
	<li><code>0 &lt;= tree<sub>r</sub>, squirrel<sub>r</sub>, nut<sub>i<sub>r</sub></sub> &lt;= height</code></li>
	<li><code>0 &lt;= tree<sub>c</sub>, squirrel<sub>c</sub>, nut<sub>i<sub>c</sub></sub> &lt;= width</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minDistance(
        self,
        height: int,
        width: int,
        tree: List[int],
        squirrel: List[int],
        nuts: List[List[int]],
    ) -> int:
        x, y, a, b = *tree, *squirrel
        s = sum(abs(i - x) + abs(j - y) for i, j in nuts) * 2
        ans = inf
        for i, j in nuts:
            c = abs(i - x) + abs(j - y)
            d = abs(i - a) + abs(j - b) + c
            ans = min(ans, s + d - c * 2)
        return ans
```

### **Java**

```java
class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int ans = Integer.MAX_VALUE;
        int s = 0;
        for (int[] a : nuts) {
            s += f(a, tree);
        }
        s *= 2;
        for (int[] a : nuts) {
            int c = f(a, tree);
            int d = f(a, squirrel) + c;
            ans = Math.min(ans, s + d - c * 2);
        }
        return ans;
    }

    private int f(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minDistance(int height, int width, vector<int>& tree, vector<int>& squirrel, vector<vector<int>>& nuts) {
        int ans = INT_MAX;
        int s = 0;
        for (auto& a : nuts) {
            s += f(a, tree);
        }
        s *= 2;
        for (auto& a : nuts) {
            int c = f(a, tree);
            int d = f(a, squirrel) + c;
            ans = min(ans, s + d - c * 2);
        }
        return ans;
    }

    int f(vector<int>& a, vector<int>& b) {
        return abs(a[0] - b[0]) + abs(a[1] - b[1]);
    }
};
```

### **Go**

```go
func minDistance(height int, width int, tree []int, squirrel []int, nuts [][]int) int {
	f := func(a, b []int) int {
		return abs(a[0]-b[0]) + abs(a[1]-b[1])
	}
	ans := math.MaxInt32
	s := 0
	for _, a := range nuts {
		s += f(a, tree)
	}
	s *= 2
	for _, a := range nuts {
		c := f(a, tree)
		d := f(a, squirrel) + c
		ans = min(ans, s+d-c*2)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
