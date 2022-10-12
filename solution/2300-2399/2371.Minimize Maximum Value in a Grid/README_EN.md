# [2371. Minimize Maximum Value in a Grid](https://leetcode.com/problems/minimize-maximum-value-in-a-grid)

[中文文档](/solution/2300-2399/2371.Minimize%20Maximum%20Value%20in%20a%20Grid/README.md)

## Description

<p>You are given an <code>m x n</code> integer matrix <code>grid</code> containing <strong>distinct</strong> positive integers.</p>

<p>You have to replace each integer in the matrix with a positive integer satisfying the following conditions:</p>

<ul>
	<li>The <strong>relative</strong> order of every two elements that are in the same row or column should stay the <strong>same</strong> after the replacements.</li>
	<li>The <strong>maximum</strong> number in the matrix after the replacements should be as <strong>small</strong> as possible.</li>
</ul>

<p>The relative order stays the same if for all pairs of elements in the original matrix such that <code>grid[r<sub>1</sub>][c<sub>1</sub>] &gt; grid[r<sub>2</sub>][c<sub>2</sub>]</code> where either <code>r<sub>1</sub> == r<sub>2</sub></code> or <code>c<sub>1</sub> == c<sub>2</sub></code>, then it must be true that <code>grid[r<sub>1</sub>][c<sub>1</sub>] &gt; grid[r<sub>2</sub>][c<sub>2</sub>]</code> after the replacements.</p>

<p>For example, if <code>grid = [[2, 4, 5], [7, 3, 9]]</code> then a good replacement could be either <code>grid = [[1, 2, 3], [2, 1, 4]]</code> or <code>grid = [[1, 2, 3], [3, 1, 4]]</code>.</p>

<p>Return <em>the <strong>resulting</strong> matrix.</em> If there are multiple answers, return <strong>any</strong> of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2371.Minimize%20Maximum%20Value%20in%20a%20Grid/images/grid2drawio.png" style="width: 371px; height: 121px;" />
<pre>
<strong>Input:</strong> grid = [[3,1],[2,5]]
<strong>Output:</strong> [[2,1],[1,2]]
<strong>Explanation:</strong> The above diagram shows a valid replacement.
The maximum number in the matrix is 2. It can be shown that no smaller value can be obtained.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[10]]
<strong>Output:</strong> [[1]]
<strong>Explanation:</strong> We replace the only number in the matrix with 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
	<li><code>grid</code> consists of distinct integers.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minScore(self, grid: List[List[int]]) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        nums = [(v, i, j) for i, row in enumerate(grid)
                for j, v in enumerate(row)]
        nums.sort()
        row_max = [0] * m
        col_max = [0] * n
        ans = [[0] * n for _ in range(m)]
        for _, i, j in nums:
            ans[i][j] = max(row_max[i], col_max[j]) + 1
            row_max[i] = col_max[j] = ans[i][j]
        return ans
```

### **Java**

```java
class Solution {
    public int[][] minScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<int[]> nums = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                nums.add(new int[] {grid[i][j], i, j});
            }
        }
        Collections.sort(nums, (a, b) -> a[0] - b[0]);
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int[][] ans = new int[m][n];
        for (int[] num : nums) {
            int i = num[1], j = num[2];
            ans[i][j] = Math.max(rowMax[i], colMax[j]) + 1;
            rowMax[i] = ans[i][j];
            colMax[j] = ans[i][j];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> minScore(vector<vector<int>>& grid) {
        vector<tuple<int, int, int>> nums;
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                nums.push_back({grid[i][j], i, j});
            }
        }
        sort(nums.begin(), nums.end());
        vector<int> rowMax(m);
        vector<int> colMax(n);
        vector<vector<int>> ans(m, vector<int>(n));
        for (auto [_, i, j] : nums) {
            ans[i][j] = max(rowMax[i], colMax[j]) + 1;
            rowMax[i] = colMax[j] = ans[i][j];
        }
        return ans;
    }
};
```

### **Go**

```go
func minScore(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	nums := [][]int{}
	for i, row := range grid {
		for j, v := range row {
			nums = append(nums, []int{v, i, j})
		}
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i][0] < nums[j][0] })
	rowMax := make([]int, m)
	colMax := make([]int, n)
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for _, num := range nums {
		i, j := num[1], num[2]
		ans[i][j] = max(rowMax[i], colMax[j]) + 1
		rowMax[i] = ans[i][j]
		colMax[j] = ans[i][j]
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minScore(grid: number[][]): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const nums = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            nums.push([grid[i][j], i, j]);
        }
    }
    nums.sort((a, b) => a[0] - b[0]);
    const rowMax = new Array(m).fill(0);
    const colMax = new Array(n).fill(0);
    const ans = Array.from({ length: m }, _ => new Array(n));
    for (const [_, i, j] of nums) {
        ans[i][j] = Math.max(rowMax[i], colMax[j]) + 1;
        rowMax[i] = colMax[j] = ans[i][j];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
