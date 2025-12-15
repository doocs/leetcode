---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3466.Maximum%20Coin%20Collection/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3466. Maximum Coin Collection 🔒](https://leetcode.com/problems/maximum-coin-collection)

[中文文档](/solution/3400-3499/3466.Maximum%20Coin%20Collection/README.md)

## Description

<!-- description:start -->

<p>Mario drives on a two-lane freeway with coins every mile. You are given two integer arrays, <code>lane1</code> and <code>lane2</code>, where the value at the <code>i<sup>th</sup></code> index represents the number of coins he <em>gains or loses</em> in the <code>i<sup>th</sup></code> mile in that lane.</p>

<ul>
	<li>If Mario is in lane 1 at mile <code>i</code> and <code>lane1[i] &gt; 0</code>, Mario gains <code>lane1[i]</code> coins.</li>
	<li>If Mario is in lane 1 at mile <code>i</code> and <code>lane1[i] &lt; 0</code>, Mario pays a toll and loses <code>abs(lane1[i])</code> coins.</li>
	<li>The same rules apply for <code>lane2</code>.</li>
</ul>

<p>Mario can enter the freeway anywhere and exit anytime after traveling <strong>at least</strong> one mile. Mario always enters the freeway on lane 1 but can switch lanes <strong>at most</strong> 2 times.</p>

<p>A <strong>lane switch</strong> is when Mario goes from lane 1 to lane 2 or vice versa.</p>

<p>Return the <strong>maximum</strong> number of coins Mario can earn after performing <strong>at most 2 lane switches</strong>.</p>

<p><strong>Note:</strong> Mario can switch lanes immediately upon entering or just before exiting the freeway.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">lane1 = [1,-2,-10,3], lane2 = [-5,10,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Mario drives the first mile on lane 1.</li>
	<li>He then changes to lane 2 and drives for two miles.</li>
	<li>He changes back to lane 1 for the last mile.</li>
</ul>

<p>Mario collects <code>1 + 10 + 0 + 3 = 14</code> coins.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">lane1 = [1,-1,-1,-1], lane2 = [0,3,4,-5]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Mario starts at mile 0 in lane 1 and drives one mile.</li>
	<li>He then changes to lane 2 and drives for two more miles. He exits the freeway before mile 3.</li>
</ul>

<p>He collects <code>1 + 3 + 4 = 8</code> coins.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">lane1 = [-5,-4,-3], lane2 = [-1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Mario enters at mile 1 and immediately switches to lane 2. He stays here the entire way.</li>
</ul>

<p>He collects a total of <code>2 + 3 = 5</code> coins.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">lane1 = [-3,-3,-3], lane2 = [9,-2,4]</span></p>

<p><strong>Output:</strong> 11</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Mario starts at the beginning of the freeway and immediately switches to lane 2. He stays here the whole way.</li>
</ul>

<p>He collects a total of <code>9 + (-2) + 4 = 11</code> coins.</p>
</div>

<p><strong class="example">Example 5:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">lane1 = [-10], lane2 = [-2]</span></p>

<p><strong>Output:</strong> <span class="example-io">-2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since Mario must ride on the freeway for at least one mile, he rides just one mile in lane 2.</li>
</ul>

<p>He collects a total of -2 coins.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= lane1.length == lane2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= lane1[i], lane2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoized Search

We design a function $\textit{dfs}(i, j, k)$, which represents the maximum number of coins Mario can collect starting from position $i$, currently on lane $j$, with $k$ lane changes remaining. The answer is the maximum value of $\textit{dfs}(i, 0, 2)$ for all $i$.

The function $\textit{dfs}(i, j, k)$ is calculated as follows:

- If $i \geq n$, it means Mario has reached the end, return 0;
- If no lane change is made, Mario can drive 1 mile, then exit, or continue driving, taking the maximum of the two, i.e., $\max(x, \textit{dfs}(i + 1, j, k) + x)$;
- If a lane change is possible, there are two choices: drive 1 mile and then change lanes, or change lanes directly, taking the maximum of these two cases, i.e., $\max(\textit{dfs}(i + 1, j \oplus 1, k - 1) + x, \textit{dfs}(i, j \oplus 1, k - 1))$.
- Where $x$ represents the number of coins at the current position.

To avoid repeated calculations, we use memoized search to store the results that have already been computed.

Time complexity is $O(n)$, and space complexity is $O(n)$. Where $n$ represents the length of the lanes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCoins(self, lane1: List[int], lane2: List[int]) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i >= n:
                return 0
            x = lane1[i] if j == 0 else lane2[i]
            ans = max(x, dfs(i + 1, j, k) + x)
            if k > 0:
                ans = max(ans, dfs(i + 1, j ^ 1, k - 1) + x)
                ans = max(ans, dfs(i, j ^ 1, k - 1))
            return ans

        n = len(lane1)
        ans = -inf
        for i in range(n):
            ans = max(ans, dfs(i, 0, 2))
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private int[] lane1;
    private int[] lane2;
    private Long[][][] f;

    public long maxCoins(int[] lane1, int[] lane2) {
        n = lane1.length;
        this.lane1 = lane1;
        this.lane2 = lane2;
        f = new Long[n][2][3];
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dfs(i, 0, 2));
        }
        return ans;
    }

    private long dfs(int i, int j, int k) {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int x = j == 0 ? lane1[i] : lane2[i];
        long ans = Math.max(x, dfs(i + 1, j, k) + x);
        if (k > 0) {
            ans = Math.max(ans, dfs(i + 1, j ^ 1, k - 1) + x);
            ans = Math.max(ans, dfs(i, j ^ 1, k - 1));
        }
        return f[i][j][k] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxCoins(vector<int>& lane1, vector<int>& lane2) {
        int n = lane1.size();
        long long ans = -1e18;
        vector<vector<vector<long long>>> f(n, vector<vector<long long>>(2, vector<long long>(3, -1e18)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> long long {
            if (i >= n) {
                return 0LL;
            }
            if (f[i][j][k] != -1e18) {
                return f[i][j][k];
            }
            int x = j == 0 ? lane1[i] : lane2[i];
            long long ans = max((long long) x, dfs(i + 1, j, k) + x);
            if (k > 0) {
                ans = max(ans, dfs(i + 1, j ^ 1, k - 1) + x);
                ans = max(ans, dfs(i, j ^ 1, k - 1));
            }
            return f[i][j][k] = ans;
        };
        for (int i = 0; i < n; ++i) {
            ans = max(ans, dfs(i, 0, 2));
        }
        return ans;
    }
};
```

#### Go

```go
func maxCoins(lane1 []int, lane2 []int) int64 {
	n := len(lane1)
	f := make([][2][3]int64, n)
	for i := range f {
		for j := range f[i] {
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var dfs func(int, int, int) int64
	dfs = func(i, j, k int) int64 {
		if i >= n {
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		x := int64(lane1[i])
		if j == 1 {
			x = int64(lane2[i])
		}
		ans := max(x, dfs(i+1, j, k)+x)
		if k > 0 {
			ans = max(ans, dfs(i+1, j^1, k-1)+x)
			ans = max(ans, dfs(i, j^1, k-1))
		}
		f[i][j][k] = ans
		return ans
	}
	ans := int64(-1e18)
	for i := range lane1 {
		ans = max(ans, dfs(i, 0, 2))
	}
	return ans
}
```

#### TypeScript

```ts
function maxCoins(lane1: number[], lane2: number[]): number {
    const n = lane1.length;
    const NEG_INF = -1e18;
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: 2 }, () => Array(3).fill(NEG_INF)),
    );
    const dfs = (dfs: Function, i: number, j: number, k: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] !== NEG_INF) {
            return f[i][j][k];
        }
        const x = j === 0 ? lane1[i] : lane2[i];
        let ans = Math.max(x, dfs(dfs, i + 1, j, k) + x);
        if (k > 0) {
            ans = Math.max(ans, dfs(dfs, i + 1, j ^ 1, k - 1) + x);
            ans = Math.max(ans, dfs(dfs, i, j ^ 1, k - 1));
        }
        f[i][j][k] = ans;
        return ans;
    };
    let ans = NEG_INF;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, dfs(dfs, i, 0, 2));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
