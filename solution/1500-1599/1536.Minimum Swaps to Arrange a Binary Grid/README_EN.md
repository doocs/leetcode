# [1536. Minimum Swaps to Arrange a Binary Grid](https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid)

[中文文档](/solution/1500-1599/1536.Minimum%20Swaps%20to%20Arrange%20a%20Binary%20Grid/README.md)

<!-- tags:Greedy,Array,Matrix -->

<!-- difficulty:Medium -->

## Description

<p>Given an <code>n x n</code> binary <code>grid</code>, in one step you can choose two <strong>adjacent rows</strong> of the grid and swap them.</p>

<p>A grid is said to be <strong>valid</strong> if all the cells above the main diagonal are <strong>zeros</strong>.</p>

<p>Return <em>the minimum number of steps</em> needed to make the grid valid, or <strong>-1</strong> if the grid cannot be valid.</p>

<p>The main diagonal of a grid is the diagonal that starts at cell <code>(1, 1)</code> and ends at cell <code>(n, n)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1536.Minimum%20Swaps%20to%20Arrange%20a%20Binary%20Grid/images/fw.jpg" style="width: 750px; height: 141px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,1],[1,1,0],[1,0,0]]
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1536.Minimum%20Swaps%20to%20Arrange%20a%20Binary%20Grid/images/e2.jpg" style="width: 270px; height: 270px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> All rows are similar, swaps have no effect on the grid.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1536.Minimum%20Swaps%20to%20Arrange%20a%20Binary%20Grid/images/e3.jpg" style="width: 200px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0],[1,1,0],[1,1,1]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code> <code>== grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code></li>
</ul>

## Solutions

### Solution 1: Greedy

We process row by row. For the $i$-th row, the position of the last '1' must be less than or equal to $i$. We find the first row that meets the condition in $[i, n)$, denoted as $k$. Then, starting from the $k$-th row, we swap the adjacent two rows upwards until the $i$-th row.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the side length of the grid.

<!-- tabs:start -->

```python
class Solution:
    def minSwaps(self, grid: List[List[int]]) -> int:
        n = len(grid)
        pos = [-1] * n
        for i in range(n):
            for j in range(n - 1, -1, -1):
                if grid[i][j] == 1:
                    pos[i] = j
                    break
        ans = 0
        for i in range(n):
            k = -1
            for j in range(i, n):
                if pos[j] <= i:
                    ans += j - i
                    k = j
                    break
            if k == -1:
                return -1
            while k > i:
                pos[k], pos[k - 1] = pos[k - 1], pos[k]
                k -= 1
        return ans
```

```java
class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] pos = new int[n];
        Arrays.fill(pos, -1);
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    pos[i] = j;
                    break;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int k = -1;
            for (int j = i; j < n; ++j) {
                if (pos[j] <= i) {
                    ans += j - i;
                    k = j;
                    break;
                }
            }
            if (k == -1) {
                return -1;
            }
            for (; k > i; --k) {
                int t = pos[k];
                pos[k] = pos[k - 1];
                pos[k - 1] = t;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minSwaps(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> pos(n, -1);
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    pos[i] = j;
                    break;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int k = -1;
            for (int j = i; j < n; ++j) {
                if (pos[j] <= i) {
                    ans += j - i;
                    k = j;
                    break;
                }
            }
            if (k == -1) {
                return -1;
            }
            for (; k > i; --k) {
                swap(pos[k], pos[k - 1]);
            }
        }
        return ans;
    }
};
```

```go
func minSwaps(grid [][]int) (ans int) {
	n := len(grid)
	pos := make([]int, n)
	for i := range pos {
		pos[i] = -1
	}
	for i := 0; i < n; i++ {
		for j := n - 1; j >= 0; j-- {
			if grid[i][j] == 1 {
				pos[i] = j
				break
			}
		}
	}
	for i := 0; i < n; i++ {
		k := -1
		for j := i; j < n; j++ {
			if pos[j] <= i {
				ans += j - i
				k = j
				break
			}
		}
		if k == -1 {
			return -1
		}
		for ; k > i; k-- {
			pos[k], pos[k-1] = pos[k-1], pos[k]
		}
	}
	return
}
```

```ts
function minSwaps(grid: number[][]): number {
    const n = grid.length;
    const pos: number[] = Array(n).fill(-1);
    for (let i = 0; i < n; ++i) {
        for (let j = n - 1; ~j; --j) {
            if (grid[i][j] === 1) {
                pos[i] = j;
                break;
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let k = -1;
        for (let j = i; j < n; ++j) {
            if (pos[j] <= i) {
                ans += j - i;
                k = j;
                break;
            }
        }
        if (k === -1) {
            return -1;
        }
        for (; k > i; --k) {
            [pos[k], pos[k - 1]] = [pos[k - 1], pos[k]];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
