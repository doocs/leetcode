# [296. Best Meeting Point](https://leetcode.com/problems/best-meeting-point)

[中文文档](/solution/0200-0299/0296.Best%20Meeting%20Point/README.md)

## Description

<p>Given an <code>m x n</code> binary grid <code>grid</code> where each <code>1</code> marks the home of one friend, return <em>the minimal <strong>total travel distance</strong></em>.</p>

<p>The <strong>total travel distance</strong> is the sum of the distances between the houses of the friends and the meeting point.</p>

<p>The distance is calculated using <a href="http://en.wikipedia.org/wiki/Taxicab_geometry" target="_blank">Manhattan Distance</a>, where <code>distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0296.Best%20Meeting%20Point/images/meetingpoint-grid.jpg" style="width: 413px; height: 253px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Given three friends living at (0,0), (0,4), and (2,2).
The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
So return 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li>There will be <strong>at least two</strong> friends in the <code>grid</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minTotalDistance(self, grid: List[List[int]]) -> int:
        def f(arr, x):
            return sum(abs(v - x) for v in arr)

        rows, cols = [], []
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v:
                    rows.append(i)
                    cols.append(j)
        cols.sort()
        i = rows[len(rows) >> 1]
        j = cols[len(cols) >> 1]
        return f(rows, i) + f(cols, j)
```

### **Java**

```java
class Solution {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(cols);
        int i = rows.get(rows.size() >> 1);
        int j = cols.get(cols.size() >> 1);
        return f(rows, i) + f(cols, j);
    }

    private int f(List<Integer> arr, int x) {
        int s = 0;
        for (int v : arr) {
            s += Math.abs(v - x);
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minTotalDistance(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> rows;
        vector<int> cols;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    rows.emplace_back(i);
                    cols.emplace_back(j);
                }
            }
        }
        sort(cols.begin(), cols.end());
        int i = rows[rows.size() / 2];
        int j = cols[cols.size() / 2];
        auto f = [](vector<int>& arr, int x) {
            int s = 0;
            for (int v : arr) {
                s += abs(v - x);
            }
            return s;
        };
        return f(rows, i) + f(cols, j);
    }
};
```

### **Go**

```go
func minTotalDistance(grid [][]int) int {
	rows, cols := []int{}, []int{}
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				rows = append(rows, i)
				cols = append(cols, j)
			}
		}
	}
	sort.Ints(cols)
	i := rows[len(rows)>>1]
	j := cols[len(cols)>>1]
	f := func(arr []int, x int) int {
		s := 0
		for _, v := range arr {
			s += abs(v - x)
		}
		return s
	}
	return f(rows, i) + f(cols, j)
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
