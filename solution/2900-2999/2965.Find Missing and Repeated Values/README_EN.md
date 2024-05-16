---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2965.Find%20Missing%20and%20Repeated%20Values/README_EN.md
rating: 1244
source: Weekly Contest 376 Q1
tags:
    - Array
    - Hash Table
    - Math
    - Matrix
---

# [2965. Find Missing and Repeated Values](https://leetcode.com/problems/find-missing-and-repeated-values)

[中文文档](/solution/2900-2999/2965.Find%20Missing%20and%20Repeated%20Values/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer matrix <code><font face="monospace">grid</font></code> of size <code>n * n</code> with values in the range <code>[1, n<sup>2</sup>]</code>. Each integer appears <strong>exactly once</strong> except <code>a</code> which appears <strong>twice</strong> and <code>b</code> which is <strong>missing</strong>. The task is to find the repeating and missing numbers <code>a</code> and <code>b</code>.</p>

<p>Return <em>a <strong>0-indexed </strong>integer array </em><code>ans</code><em> of size </em><code>2</code><em> where </em><code>ans[0]</code><em> equals to </em><code>a</code><em> and </em><code>ans[1]</code><em> equals to </em><code>b</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,3],[2,2]]
<strong>Output:</strong> [2,4]
<strong>Explanation:</strong> Number 2 is repeated and number 4 is missing so the answer is [2,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[9,1,7],[8,9,2],[3,4,6]]
<strong>Output:</strong> [9,5]
<strong>Explanation:</strong> Number 9 is repeated and number 5 is missing so the answer is [9,5].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == grid.length == grid[i].length &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= n * n</code></li>
	<li>For all <code>x</code> that <code>1 &lt;= x &lt;= n * n</code> there is exactly one <code>x</code> that is not equal to any of the grid members.</li>
	<li>For all <code>x</code> that <code>1 &lt;= x &lt;= n * n</code> there is exactly one <code>x</code> that is equal to exactly two of the grid members.</li>
	<li>For all <code>x</code> that <code>1 &lt;= x &lt;= n * n</code> except two of them there is exatly one pair of <code>i, j</code> that <code>0 &lt;= i, j &lt;= n - 1</code> and <code>grid[i][j] == x</code>.</li>
</ul>

## Solutions

### Solution 1: Counting

We create an array $cnt$ of length $n^2 + 1$ to count the frequency of each number in the matrix.

Next, we traverse $i \in [1, n^2]$. If $cnt[i] = 2$, then $i$ is the duplicated number, and we set the first element of the answer to $i$. If $cnt[i] = 0$, then $i$ is the missing number, and we set the second element of the answer to $i$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the side length of the matrix.

<!-- tabs:start -->

```python
class Solution:
    def findMissingAndRepeatedValues(self, grid: List[List[int]]) -> List[int]:
        n = len(grid)
        cnt = [0] * (n * n + 1)
        for row in grid:
            for v in row:
                cnt[v] += 1
        ans = [0] * 2
        for i in range(1, n * n + 1):
            if cnt[i] == 2:
                ans[0] = i
            if cnt[i] == 0:
                ans[1] = i
        return ans
```

```java
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] cnt = new int[n * n + 1];
        int[] ans = new int[2];
        for (int[] row : grid) {
            for (int x : row) {
                if (++cnt[x] == 2) {
                    ans[0] = x;
                }
            }
        }
        for (int x = 1;; ++x) {
            if (cnt[x] == 0) {
                ans[1] = x;
                return ans;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    vector<int> findMissingAndRepeatedValues(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> cnt(n * n + 1);
        vector<int> ans(2);
        for (auto& row : grid) {
            for (int x : row) {
                if (++cnt[x] == 2) {
                    ans[0] = x;
                }
            }
        }
        for (int x = 1;; ++x) {
            if (cnt[x] == 0) {
                ans[1] = x;
                return ans;
            }
        }
    }
};
```

```go
func findMissingAndRepeatedValues(grid [][]int) []int {
	n := len(grid)
	ans := make([]int, 2)
	cnt := make([]int, n*n+1)
	for _, row := range grid {
		for _, x := range row {
			cnt[x]++
			if cnt[x] == 2 {
				ans[0] = x
			}
		}
	}
	for x := 1; ; x++ {
		if cnt[x] == 0 {
			ans[1] = x
			return ans
		}
	}
}
```

```ts
function findMissingAndRepeatedValues(grid: number[][]): number[] {
    const n = grid.length;
    const cnt: number[] = Array(n * n + 1).fill(0);
    const ans: number[] = Array(2).fill(0);
    for (const row of grid) {
        for (const x of row) {
            if (++cnt[x] === 2) {
                ans[0] = x;
            }
        }
    }
    for (let x = 1; ; ++x) {
        if (cnt[x] === 0) {
            ans[1] = x;
            return ans;
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
