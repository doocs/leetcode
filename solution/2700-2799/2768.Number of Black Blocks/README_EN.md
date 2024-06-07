---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2768.Number%20of%20Black%20Blocks/README_EN.md
rating: 2175
source: Biweekly Contest 108 Q4
tags:
    - Array
    - Hash Table
    - Enumeration
---

<!-- problem:start -->

# [2768. Number of Black Blocks](https://leetcode.com/problems/number-of-black-blocks)

[中文文档](/solution/2700-2799/2768.Number%20of%20Black%20Blocks/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>m</code> and <code>n</code> representing the dimensions of a&nbsp;<strong>0-indexed</strong>&nbsp;<code>m x n</code> grid.</p>

<p>You are also given a <strong>0-indexed</strong> 2D integer matrix <code>coordinates</code>, where <code>coordinates[i] = [x, y]</code> indicates that the cell with coordinates <code>[x, y]</code> is colored <strong>black</strong>. All cells in the grid that do not appear in <code>coordinates</code> are <strong>white</strong>.</p>

<p>A block is defined as a <code>2 x 2</code> submatrix of the grid. More formally, a block with cell <code>[x, y]</code> as its top-left corner where <code>0 &lt;= x &lt; m - 1</code> and <code>0 &lt;= y &lt; n - 1</code> contains the coordinates <code>[x, y]</code>, <code>[x + 1, y]</code>, <code>[x, y + 1]</code>, and <code>[x + 1, y + 1]</code>.</p>

<p>Return <em>a <strong>0-indexed</strong> integer array</em> <code>arr</code> <em>of size</em> <code>5</code> <em>such that</em> <code>arr[i]</code> <em>is the number of blocks that contains exactly</em> <code>i</code> <em><strong>black</strong> cells</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> m = 3, n = 3, coordinates = [[0,0]]
<strong>Output:</strong> [3,1,0,0,0]
<strong>Explanation:</strong> The grid looks like this:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2768.Number%20of%20Black%20Blocks/images/screen-shot-2023-06-18-at-44656-am.png" style="width: 150px; height: 128px;" />
There is only 1 block with one black cell, and it is the block starting with cell [0,0].
The other 3 blocks start with cells [0,1], [1,0] and [1,1]. They all have zero black cells. 
Thus, we return [3,1,0,0,0]. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> m = 3, n = 3, coordinates = [[0,0],[1,1],[0,2]]
<strong>Output:</strong> [0,2,2,0,0]
<strong>Explanation:</strong> The grid looks like this:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2768.Number%20of%20Black%20Blocks/images/screen-shot-2023-06-18-at-45018-am.png" style="width: 150px; height: 128px;" />
There are 2 blocks with two black cells (the ones starting with cell coordinates [0,0] and [0,1]).
The other 2 blocks have starting cell coordinates of [1,0] and [1,1]. They both have 1 black cell.
Therefore, we return [0,2,2,0,0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= coordinates.length &lt;= 10<sup>4</sup></code></li>
	<li><code>coordinates[i].length == 2</code></li>
	<li><code>0 &lt;= coordinates[i][0] &lt; m</code></li>
	<li><code>0 &lt;= coordinates[i][1] &lt; n</code></li>
	<li>It is guaranteed that <code>coordinates</code> contains pairwise distinct coordinates.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

For each $2 \times 2$ submatrix, we can use its upper-left corner coordinate $(x, y)$ to represent it.

For each black cell $(x, y)$, its contribution to the 4 submatrices is $1$, namely the matrices $(x - 1, y - 1)$, $(x - 1, y)$, $(x, y - 1)$, $(x, y)$.

Therefore, we traverse all the black cells, and then accumulate the number of black cells in each submatrix, recorded in the hash table $cnt$.

Finally, we traverse all the values in $cnt$ (greater than $0$), count the number of times they appear, and record them in the answer array $ans$, while $ans[0]$ represents the number of submatrices without black cells, the value is $(m - 1) \times (n - 1) - \sum_{i = 1}^4 ans[i]$.

Time complexity $O(l)$, space complexity $O(l)$, where $l$ is the length of $coordinates$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countBlackBlocks(
        self, m: int, n: int, coordinates: List[List[int]]
    ) -> List[int]:
        cnt = Counter()
        for x, y in coordinates:
            for a, b in pairwise((0, 0, -1, -1, 0)):
                i, j = x + a, y + b
                if 0 <= i < m - 1 and 0 <= j < n - 1:
                    cnt[(i, j)] += 1
        ans = [0] * 5
        for x in cnt.values():
            ans[x] += 1
        ans[0] = (m - 1) * (n - 1) - len(cnt.values())
        return ans
```

#### Java

```java
class Solution {
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        Map<Long, Integer> cnt = new HashMap<>(coordinates.length);
        int[] dirs = {0, 0, -1, -1, 0};
        for (var e : coordinates) {
            int x = e[0], y = e[1];
            for (int k = 0; k < 4; ++k) {
                int i = x + dirs[k], j = y + dirs[k + 1];
                if (i >= 0 && i < m - 1 && j >= 0 && j < n - 1) {
                    cnt.merge(1L * i * n + j, 1, Integer::sum);
                }
            }
        }
        long[] ans = new long[5];
        ans[0] = (m - 1L) * (n - 1);
        for (int x : cnt.values()) {
            ++ans[x];
            --ans[0];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> countBlackBlocks(int m, int n, vector<vector<int>>& coordinates) {
        unordered_map<long long, int> cnt;
        int dirs[5] = {0, 0, -1, -1, 0};
        for (auto& e : coordinates) {
            int x = e[0], y = e[1];
            for (int k = 0; k < 4; ++k) {
                int i = x + dirs[k], j = y + dirs[k + 1];
                if (i >= 0 && i < m - 1 && j >= 0 && j < n - 1) {
                    ++cnt[1LL * i * n + j];
                }
            }
        }
        vector<long long> ans(5);
        ans[0] = (m - 1LL) * (n - 1);
        for (auto& [_, x] : cnt) {
            ++ans[x];
            --ans[0];
        }
        return ans;
    }
};
```

#### Go

```go
func countBlackBlocks(m int, n int, coordinates [][]int) []int64 {
	cnt := map[int64]int{}
	dirs := [5]int{0, 0, -1, -1, 0}
	for _, e := range coordinates {
		x, y := e[0], e[1]
		for k := 0; k < 4; k++ {
			i, j := x+dirs[k], y+dirs[k+1]
			if i >= 0 && i < m-1 && j >= 0 && j < n-1 {
				cnt[int64(i*n+j)]++
			}
		}
	}
	ans := make([]int64, 5)
	ans[0] = int64((m - 1) * (n - 1))
	for _, x := range cnt {
		ans[x]++
		ans[0]--
	}
	return ans
}
```

#### TypeScript

```ts
function countBlackBlocks(m: number, n: number, coordinates: number[][]): number[] {
    const cnt: Map<number, number> = new Map();
    const dirs: number[] = [0, 0, -1, -1, 0];
    for (const [x, y] of coordinates) {
        for (let k = 0; k < 4; ++k) {
            const [i, j] = [x + dirs[k], y + dirs[k + 1]];
            if (i >= 0 && i < m - 1 && j >= 0 && j < n - 1) {
                const key = i * n + j;
                cnt.set(key, (cnt.get(key) || 0) + 1);
            }
        }
    }
    const ans: number[] = Array(5).fill(0);
    ans[0] = (m - 1) * (n - 1);
    for (const [_, x] of cnt) {
        ++ans[x];
        --ans[0];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
