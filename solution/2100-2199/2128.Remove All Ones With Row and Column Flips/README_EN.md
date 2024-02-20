# [2128. Remove All Ones With Row and Column Flips](https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips)

[中文文档](/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/README.md)

<!-- tags:Bit Manipulation,Array,Math,Matrix -->

## Description

<p>You are given an <code>m x n</code> binary matrix <code>grid</code>.</p>

<p>In one operation, you can choose <strong>any</strong> row or column and flip each value in that row or column (i.e., changing all <code>0</code>&#39;s to <code>1</code>&#39;s, and all <code>1</code>&#39;s to <code>0</code>&#39;s).</p>

<p>Return <code>true</code><em> if it is possible to remove all </em><code>1</code><em>&#39;s from </em><code>grid</code> using <strong>any</strong> number of operations or <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/images/image-20220103191300-1.png" style="width: 756px; height: 225px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,0],[1,0,1],[0,1,0]]
<strong>Output:</strong> true
<strong>Explanation:</strong> One possible way to remove all 1&#39;s from grid is to:
- Flip the middle row
- Flip the middle column
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/images/image-20220103181204-7.png" style="width: 237px; height: 225px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,0],[0,0,0],[0,0,0]]
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to remove all 1&#39;s from grid.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/images/image-20220103181224-8.png" style="width: 114px; height: 100px;" />
<pre>
<strong>Input:</strong> grid = [[0]]
<strong>Output:</strong> true
<strong>Explanation:</strong> There are no 1&#39;s in grid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def removeOnes(self, grid: List[List[int]]) -> bool:
        s = set()
        for row in grid:
            t = tuple(row) if row[0] == grid[0][0] else tuple(x ^ 1 for x in row)
            s.add(t)
        return len(s) == 1
```

```java
class Solution {
    public boolean removeOnes(int[][] grid) {
        Set<String> s = new HashSet<>();
        int n = grid[0].length;
        for (var row : grid) {
            var cs = new char[n];
            for (int i = 0; i < n; ++i) {
                cs[i] = (char) (row[0] ^ row[i]);
            }
            s.add(String.valueOf(cs));
        }
        return s.size() == 1;
    }
}
```

```cpp
class Solution {
public:
    bool removeOnes(vector<vector<int>>& grid) {
        unordered_set<string> s;
        for (auto& row : grid) {
            string t;
            for (int x : row) {
                t.push_back('0' + (row[0] == 0 ? x : x ^ 1));
            }
            s.insert(t);
        }
        return s.size() == 1;
    }
};
```

```go
func removeOnes(grid [][]int) bool {
	s := map[string]bool{}
	for _, row := range grid {
		t := []byte{}
		for _, x := range row {
			if row[0] == 1 {
				x ^= 1
			}
			t = append(t, byte(x)+'0')
		}
		s[string(t)] = true
	}
	return len(s) == 1
}
```

```ts
function removeOnes(grid: number[][]): boolean {
    const s = new Set<string>();
    for (const row of grid) {
        if (row[0] === 1) {
            for (let i = 0; i < row.length; i++) {
                row[i] ^= 1;
            }
        }
        const t = row.join('');
        s.add(t);
    }
    return s.size === 1;
}
```

<!-- tabs:end -->

<!-- end -->
