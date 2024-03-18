# [2684. Maximum Number of Moves in a Grid](https://leetcode.com/problems/maximum-number-of-moves-in-a-grid)

[中文文档](/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/README.md)

<!-- tags:Array,Dynamic Programming,Matrix -->

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> matrix <code>grid</code> consisting of <strong>positive</strong> integers.</p>

<p>You can start at <strong>any</strong> cell in the first column of the matrix, and traverse the grid in the following way:</p>

<ul>
	<li>From a cell <code>(row, col)</code>, you can move to any of the cells: <code>(row - 1, col + 1)</code>, <code>(row, col + 1)</code> and <code>(row + 1, col + 1)</code> such that the value of the cell you move to, should be <strong>strictly</strong> bigger than the value of the current cell.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of <strong>moves</strong> that you can perform.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/images/yetgriddrawio-10.png" style="width: 201px; height: 201px;" />
<pre>
<strong>Input:</strong> grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can start at the cell (0, 0) and make the following moves:
- (0, 0) -&gt; (0, 1).
- (0, 1) -&gt; (1, 2).
- (1, 2) -&gt; (2, 3).
It can be shown that it is the maximum number of moves that can be made.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/images/yetgrid4drawio.png" />
<strong>Input:</strong> grid = [[3,2,4],[2,1,9],[1,1,7]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Starting from any cell in the first column we cannot perform any moves.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: BFS

We define a queue $q$, and initially add all the row coordinates of the first column to the queue.

Next, we start from the first column and traverse column by column. For each column, we take out all the row coordinates in the queue one by one. For each row coordinate $i$, we get all possible row coordinates $k$ of the next column, and satisfy $grid[i][j] < grid[k][j + 1]$, and add these row coordinates to a new set $t$. If $t$ is empty, it means that we cannot continue to move, so we return the current column number. Otherwise, we assign $t$ to $q$ and continue to traverse the next column.

Finally, if we have traversed all the columns, it means that we can move to the last column, so we return $n - 1$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m)$. Where $m$ and $n$ are the number of rows and columns in the matrix, respectively.

<!-- tabs:start -->

```python
class Solution:
    def maxMoves(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        q = set(range(m))
        for j in range(n - 1):
            t = set()
            for i in q:
                for k in range(i - 1, i + 2):
                    if 0 <= k < m and grid[i][j] < grid[k][j + 1]:
                        t.add(k)
            if not t:
                return j
            q = t
        return n - 1
```

```java
class Solution {
    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<Integer> q = IntStream.range(0, m).boxed().collect(Collectors.toSet());
        for (int j = 0; j < n - 1; ++j) {
            Set<Integer> t = new HashSet<>();
            for (int i : q) {
                for (int k = i - 1; k <= i + 1; ++k) {
                    if (k >= 0 && k < m && grid[i][j] < grid[k][j + 1]) {
                        t.add(k);
                    }
                }
            }
            if (t.isEmpty()) {
                return j;
            }
            q = t;
        }
        return n - 1;
    }
}
```

```cpp
class Solution {
public:
    int maxMoves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        unordered_set<int> q, t;
        for (int i = 0; i < m; ++i) {
            q.insert(i);
        }
        for (int j = 0; j < n - 1; ++j) {
            t.clear();
            for (int i : q) {
                for (int k = i - 1; k <= i + 1; ++k) {
                    if (k >= 0 && k < m && grid[i][j] < grid[k][j + 1]) {
                        t.insert(k);
                    }
                }
            }
            if (t.empty()) {
                return j;
            }
            q.swap(t);
        }
        return n - 1;
    }
};
```

```go
func maxMoves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	q := map[int]bool{}
	for i := range grid {
		q[i] = true
	}
	for j := 0; j < n-1; j++ {
		t := map[int]bool{}
		for i := range q {
			for k := i - 1; k <= i+1; k++ {
				if k >= 0 && k < m && grid[i][j] < grid[k][j+1] {
					t[k] = true
				}
			}
		}
		if len(t) == 0 {
			return j
		}
		q = t
	}
	return n - 1
}
```

```ts
function maxMoves(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let q = new Set<number>(Array.from({ length: m }, (_, i) => i));
    for (let j = 0; j < n - 1; ++j) {
        const t = new Set<number>();
        for (const i of q) {
            for (let k = i - 1; k <= i + 1; ++k) {
                if (k >= 0 && k < m && grid[i][j] < grid[k][j + 1]) {
                    t.add(k);
                }
            }
        }
        if (t.size === 0) {
            return j;
        }
        q = t;
    }
    return n - 1;
}
```

<!-- tabs:end -->

<!-- end -->
