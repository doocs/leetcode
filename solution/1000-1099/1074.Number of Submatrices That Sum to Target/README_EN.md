---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1074.Number%20of%20Submatrices%20That%20Sum%20to%20Target/README_EN.md
rating: 2189
tags:
    - Array
    - Hash Table
    - Matrix
    - Prefix Sum
---

# [1074. Number of Submatrices That Sum to Target](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target)

[中文文档](/solution/1000-1099/1074.Number%20of%20Submatrices%20That%20Sum%20to%20Target/README.md)

## Description

<p>Given a <code>matrix</code>&nbsp;and a <code>target</code>, return the number of non-empty submatrices that sum to <font face="monospace">target</font>.</p>

<p>A submatrix <code>x1, y1, x2, y2</code> is the set of all cells <code>matrix[x][y]</code> with <code>x1 &lt;= x &lt;= x2</code> and <code>y1 &lt;= y &lt;= y2</code>.</p>

<p>Two submatrices <code>(x1, y1, x2, y2)</code> and <code>(x1&#39;, y1&#39;, x2&#39;, y2&#39;)</code> are different if they have some coordinate&nbsp;that is different: for example, if <code>x1 != x1&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1074.Number%20of%20Submatrices%20That%20Sum%20to%20Target/images/mate1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
<strong>Output:</strong> 4
<strong>Explanation:</strong> The four 1x1 submatrices that only contain 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[1,-1],[-1,1]], target = 0
<strong>Output:</strong> 5
<strong>Explanation:</strong> The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[904]], target = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= matrix.length &lt;= 100</code></li>
	<li><code>1 &lt;= matrix[0].length &lt;= 100</code></li>
	<li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li>
	<li><code>-10^8 &lt;= target &lt;= 10^8</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def numSubmatrixSumTarget(self, matrix: List[List[int]], target: int) -> int:
        def f(nums: List[int]) -> int:
            d = defaultdict(int)
            d[0] = 1
            cnt = s = 0
            for x in nums:
                s += x
                cnt += d[s - target]
                d[s] += 1
            return cnt

        m, n = len(matrix), len(matrix[0])
        ans = 0
        for i in range(m):
            col = [0] * n
            for j in range(i, m):
                for k in range(n):
                    col[k] += matrix[j][k]
                ans += f(col)
        return ans
```

```java
class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int[] col = new int[n];
            for (int j = i; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    col[k] += matrix[j][k];
                }
                ans += f(col, target);
            }
        }
        return ans;
    }

    private int f(int[] nums, int target) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, 1);
        int s = 0, cnt = 0;
        for (int x : nums) {
            s += x;
            cnt += d.getOrDefault(s - target, 0);
            d.merge(s, 1, Integer::sum);
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int numSubmatrixSumTarget(vector<vector<int>>& matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            vector<int> col(n);
            for (int j = i; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    col[k] += matrix[j][k];
                }
                ans += f(col, target);
            }
        }
        return ans;
    }

    int f(vector<int>& nums, int target) {
        unordered_map<int, int> d{{0, 1}};
        int cnt = 0, s = 0;
        for (int& x : nums) {
            s += x;
            if (d.count(s - target)) {
                cnt += d[s - target];
            }
            ++d[s];
        }
        return cnt;
    }
};
```

```go
func numSubmatrixSumTarget(matrix [][]int, target int) (ans int) {
	m, n := len(matrix), len(matrix[0])
	for i := 0; i < m; i++ {
		col := make([]int, n)
		for j := i; j < m; j++ {
			for k := 0; k < n; k++ {
				col[k] += matrix[j][k]
			}
			ans += f(col, target)
		}
	}
	return
}

func f(nums []int, target int) (cnt int) {
	d := map[int]int{0: 1}
	s := 0
	for _, x := range nums {
		s += x
		if v, ok := d[s-target]; ok {
			cnt += v
		}
		d[s]++
	}
	return
}
```

```ts
function numSubmatrixSumTarget(matrix: number[][], target: number): number {
    const m = matrix.length;
    const n = matrix[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        const col: number[] = new Array(n).fill(0);
        for (let j = i; j < m; ++j) {
            for (let k = 0; k < n; ++k) {
                col[k] += matrix[j][k];
            }
            ans += f(col, target);
        }
    }
    return ans;
}

function f(nums: number[], target: number): number {
    const d: Map<number, number> = new Map();
    d.set(0, 1);
    let cnt = 0;
    let s = 0;
    for (const x of nums) {
        s += x;
        if (d.has(s - target)) {
            cnt += d.get(s - target)!;
        }
        d.set(s, (d.get(s) || 0) + 1);
    }
    return cnt;
}
```

<!-- tabs:end -->

<!-- end -->
