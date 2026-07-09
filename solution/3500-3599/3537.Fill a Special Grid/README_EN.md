---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3537.Fill%20a%20Special%20Grid/README_EN.md
rating: 1541
source: Weekly Contest 448 Q2
tags:
    - Array
    - Divide and Conquer
    - Matrix
---

<!-- problem:start -->

# [3537. Fill a Special Grid](https://leetcode.com/problems/fill-a-special-grid)

[中文文档](/solution/3500-3599/3537.Fill%20a%20Special%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given a non-negative integer <code><font face="monospace">n</font></code> representing a <code>2<sup>n</sup> x 2<sup>n</sup></code> grid. You must fill the grid with integers from 0 to <code>2<sup>2n</sup> - 1</code> to make it <strong>special</strong>. A grid is <strong>special</strong> if it satisfies <strong>all</strong> the following conditions:</p>

<ul>
	<li>All numbers in the top-right quadrant are smaller than those in the bottom-right quadrant.</li>
	<li>All numbers in the bottom-right quadrant are smaller than those in the bottom-left quadrant.</li>
	<li>All numbers in the bottom-left quadrant are smaller than those in the top-left quadrant.</li>
	<li>Each of its quadrants is also a special grid.</li>
</ul>

<p>Return the <strong>special</strong> <code>2<sup>n</sup> x 2<sup>n</sup></code> grid.</p>

<p><strong>Note</strong>: Any 1x1 grid is special.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">[[0]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The only number that can be placed is 0, and there is only one possible position in the grid.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[[3,0],[2,1]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The numbers in each quadrant are:</p>

<ul>
	<li>Top-right: 0</li>
	<li>Bottom-right: 1</li>
	<li>Bottom-left: 2</li>
	<li>Top-left: 3</li>
</ul>

<p>Since <code>0 &lt; 1 &lt; 2 &lt; 3</code>, this satisfies the given constraints.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[[15,12,3,0],[14,13,2,1],[11,8,7,4],[10,9,6,5]]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3537.Fill%20a%20Special%20Grid/images/4123example3p1drawio.png" style="width: 161px; height: 161px;" /></p>

<p>The numbers in each quadrant are:</p>

<ul>
	<li>Top-right: 3, 0, 2, 1</li>
	<li>Bottom-right: 7, 4, 6, 5</li>
	<li>Bottom-left: 11, 8, 10, 9</li>
	<li>Top-left: 15, 12, 14, 13</li>
	<li><code>max(3, 0, 2, 1) &lt; min(7, 4, 6, 5)</code></li>
	<li><code>max(7, 4, 6, 5) &lt; min(11, 8, 10, 9)</code></li>
	<li><code>max(11, 8, 10, 9) &lt; min(15, 12, 14, 13)</code></li>
</ul>

<p>This satisfies the first three requirements. Additionally, each quadrant is also a special grid. Thus, this is a special grid.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursive Divide and Conquer

A special grid requires that in each quadrant, numbers satisfy: top-right < bottom-right < bottom-left < top-left, and each quadrant is also a special grid. We can construct it recursively: for a subgrid of size $k$, fill the four quadrants in order "top-right → bottom-right → bottom-left → top-left", ensuring smaller numbers are placed in the top-right quadrant first and larger numbers in the top-left quadrant last.

We start from the top-right corner $(0, m - 1)$ of the entire grid, where $m = 2^n$, with side length $m$. When $k = 1$, we fill the cell with the current value and increment; otherwise, we split into four quadrants and recurse.

The time complexity is $O(4^n)$, and the space complexity is $O(4^n)$, where $n$ is the input parameter.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def specialGrid(self, n: int) -> List[List[int]]:
        def dfs(x: int, y: int, k: int):
            if k == 1:
                nonlocal val
                ans[x][y] = val
                val += 1
                return

            dfs(x, y, k // 2)
            dfs(x + k // 2, y, k // 2)
            dfs(x + k // 2, y - k // 2, k // 2)
            dfs(x, y - k // 2, k // 2)

        m = 1 << n
        ans = [[0] * m for _ in range(m)]
        val = 0
        dfs(0, m - 1, m)
        return ans
```

#### Java

```java
class Solution {
    private int[][] ans;
    private int val;

    public int[][] specialGrid(int n) {
        int m = 1 << n;
        ans = new int[m][m];
        dfs(0, m - 1, m);
        return ans;
    }

    private void dfs(int x, int y, int k) {
        if (k == 1) {
            ans[x][y] = val++;
            return;
        }

        int h = k / 2;
        dfs(x, y, h);
        dfs(x + h, y, h);
        dfs(x + h, y - h, h);
        dfs(x, y - h, h);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> specialGrid(int n) {
        int m = 1 << n;
        vector<vector<int>> ans(m, vector<int>(m));
        int val = 0;

        auto dfs = [&](this auto&& dfs, int x, int y, int k) -> void {
            if (k == 1) {
                ans[x][y] = val++;
                return;
            }

            int h = k / 2;
            dfs(x, y, h);
            dfs(x + h, y, h);
            dfs(x + h, y - h, h);
            dfs(x, y - h, h);
        };

        dfs(0, m - 1, m);
        return ans;
    }
};
```

#### Go

```go
func specialGrid(n int) [][]int {
	m := 1 << n
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, m)
	}
	val := 0

	var dfs func(int, int, int)
	dfs = func(x, y, k int) {
		if k == 1 {
			ans[x][y] = val
			val++
			return
		}

		h := k / 2
		dfs(x, y, h)
		dfs(x+h, y, h)
		dfs(x+h, y-h, h)
		dfs(x, y-h, h)
	}

	dfs(0, m-1, m)
	return ans
}
```

#### TypeScript

```ts
function specialGrid(n: number): number[][] {
    const m = 1 << n;
    const ans = Array.from({ length: m }, () => Array(m).fill(0));
    let val = 0;

    const dfs = (x: number, y: number, k: number): void => {
        if (k === 1) {
            ans[x][y] = val++;
            return;
        }

        const h = k >> 1;
        dfs(x, y, h);
        dfs(x + h, y, h);
        dfs(x + h, y - h, h);
        dfs(x, y - h, h);
    };

    dfs(0, m - 1, m);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn special_grid(n: i32) -> Vec<Vec<i32>> {
        fn dfs(x: usize, y: usize, k: usize, ans: &mut Vec<Vec<i32>>, val: &mut i32) {
            if k == 1 {
                ans[x][y] = *val;
                *val += 1;
                return;
            }

            let h = k / 2;
            dfs(x, y, h, ans, val);
            dfs(x + h, y, h, ans, val);
            dfs(x + h, y - h, h, ans, val);
            dfs(x, y - h, h, ans, val);
        }

        let m = 1usize << n;
        let mut ans = vec![vec![0; m]; m];
        let mut val = 0;
        dfs(0, m - 1, m, &mut ans, &mut val);
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
