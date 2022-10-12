# [885. Spiral Matrix III](https://leetcode.com/problems/spiral-matrix-iii)

[中文文档](/solution/0800-0899/0885.Spiral%20Matrix%20III/README.md)

## Description

<p>You start at the cell <code>(rStart, cStart)</code> of an <code>rows x cols</code> grid facing east. The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.</p>

<p>You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid&#39;s boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all <code>rows * cols</code> spaces of the grid.</p>

<p>Return <em>an array of coordinates representing the positions of the grid in the order you visited them</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0885.Spiral%20Matrix%20III/images/example_1.png" style="width: 174px; height: 99px;" />
<pre>
<strong>Input:</strong> rows = 1, cols = 4, rStart = 0, cStart = 0
<strong>Output:</strong> [[0,0],[0,1],[0,2],[0,3]]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0885.Spiral%20Matrix%20III/images/example_2.png" style="width: 202px; height: 142px;" />
<pre>
<strong>Input:</strong> rows = 5, cols = 6, rStart = 1, cStart = 4
<strong>Output:</strong> [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rows, cols &lt;= 100</code></li>
	<li><code>0 &lt;= rStart &lt; rows</code></li>
	<li><code>0 &lt;= cStart &lt; cols</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def spiralMatrixIII(
        self, rows: int, cols: int, rStart: int, cStart: int
    ) -> List[List[int]]:
        ans = [[rStart, cStart]]
        if rows * cols == 1:
            return ans
        k = 1
        while True:
            for dr, dc, dk in [[0, 1, k], [1, 0, k], [0, -1, k + 1], [-1, 0, k + 1]]:
                for _ in range(dk):
                    rStart += dr
                    cStart += dc
                    if 0 <= rStart < rows and 0 <= cStart < cols:
                        ans.append([rStart, cStart])
                        if len(ans) == rows * cols:
                            return ans
            k += 2
```

### **Java**

```java
class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int cnt = rows * cols;
        int[][] ans = new int[cnt][2];
        ans[0] = new int[] {rStart, cStart};
        if (cnt == 1) {
            return ans;
        }
        for (int k = 1, idx = 1;; k += 2) {
            int[][] dirs = new int[][] {{0, 1, k}, {1, 0, k}, {0, -1, k + 1}, {-1, 0, k + 1}};
            for (int[] dir : dirs) {
                int r = dir[0], c = dir[1], dk = dir[2];
                while (dk-- > 0) {
                    rStart += r;
                    cStart += c;
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        ans[idx++] = new int[] {rStart, cStart};
                        if (idx == cnt) {
                            return ans;
                        }
                    }
                }
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int cnt = rows * cols;
        vector<vector<int>> ans;
        ans.push_back({rStart, cStart});
        if (cnt == 1) return ans;
        for (int k = 1;; k += 2) {
            vector<vector<int>> dirs = {{0, 1, k}, {1, 0, k}, {0, -1, k + 1}, {-1, 0, k + 1}};
            for (auto& dir : dirs) {
                int r = dir[0], c = dir[1], dk = dir[2];
                while (dk-- > 0) {
                    rStart += r;
                    cStart += c;
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        ans.push_back({rStart, cStart});
                        if (ans.size() == cnt) return ans;
                    }
                }
            }
        }
    }
};
```

### **Go**

```go
func spiralMatrixIII(rows int, cols int, rStart int, cStart int) [][]int {
	cnt := rows * cols
	ans := [][]int{[]int{rStart, cStart}}
	if cnt == 1 {
		return ans
	}
	for k := 1; ; k += 2 {
		dirs := [][]int{{0, 1, k}, {1, 0, k}, {0, -1, k + 1}, {-1, 0, k + 1}}
		for _, dir := range dirs {
			r, c, dk := dir[0], dir[1], dir[2]
			for dk > 0 {
				rStart += r
				cStart += c
				if rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols {
					ans = append(ans, []int{rStart, cStart})
					if len(ans) == cnt {
						return ans
					}
				}
				dk--
			}
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
