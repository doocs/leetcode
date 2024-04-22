# [1714. Sum Of Special Evenly-Spaced Elements In Array 🔒](https://leetcode.com/problems/sum-of-special-evenly-spaced-elements-in-array)

[中文文档](/solution/1700-1799/1714.Sum%20Of%20Special%20Evenly-Spaced%20Elements%20In%20Array/README.md)

<!-- tags:Array,Dynamic Programming -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> consisting of <code>n</code> non-negative integers.</p>

<p>You are also given an array <code>queries</code>, where <code>queries[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>. The answer to the <code>i<sup>th</sup></code> query is the sum of all <code>nums[j]</code> where <code>x<sub>i</sub> &lt;= j &lt; n</code> and <code>(j - x<sub>i</sub>)</code> is divisible by <code>y<sub>i</sub></code>.</p>

<p>Return <em>an array </em><code>answer</code><em> where </em><code>answer.length == queries.length</code><em> and </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query <b>modulo</b> </em><code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,3,4,5,6,7], queries = [[0,3],[5,1],[4,2]]
<strong>Output:</strong> [9,18,10]
<strong>Explanation:</strong> The answers of the queries are as follows:
1) The j indices that satisfy this query are 0, 3, and 6. nums[0] + nums[3] + nums[6] = 9
2) The j indices that satisfy this query are 5, 6, and 7. nums[5] + nums[6] + nums[7] = 18
3) The j indices that satisfy this query are 4 and 6. nums[4] + nums[6] = 10
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [100,200,101,201,102,202,103,203], queries = [[0,7]]
<strong>Output:</strong> [303]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 1.5 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= x<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= y<sub>i</sub> &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1: Block Decomposition

This problem is a typical block decomposition problem. For queries with a large step size, we can directly brute force the solution; for queries with a small step size, we can preprocess the suffix sum of each position and then directly query.

In this problem, we limit the step size of the large step size query to $\sqrt{n}$, which can ensure that the time complexity of each query is $O(\sqrt{n})$.

We define a two-dimensional array $suf$, where $suf[i][j]$ represents the suffix sum starting from position $j$ with a step size of $i$. Then for each query $[x, y]$, we can divide it into two cases:

-   If $y \le \sqrt{n}$, then we can directly query $suf[y][x]$;
-   If $y > \sqrt{n}$, then we can directly brute force the solution.

The time complexity is $O((n +  m) \times \sqrt{n})$, and the space complexity is $O(n \times \sqrt{n})$. Here, $n$ is the length of the array, and $m$ is the number of queries.

<!-- tabs:start -->

```python
class Solution:
    def solve(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        mod = 10**9 + 7
        n = len(nums)
        m = int(sqrt(n))
        suf = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(n - 1, -1, -1):
                suf[i][j] = suf[i][min(n, j + i)] + nums[j]
        ans = []
        for x, y in queries:
            if y <= m:
                ans.append(suf[y][x] % mod)
            else:
                ans.append(sum(nums[x::y]) % mod)
        return ans
```

```java
class Solution {
    public int[] solve(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = (int) Math.sqrt(n);
        final int mod = (int) 1e9 + 7;
        int[][] suf = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                suf[i][j] = (suf[i][Math.min(n, j + i)] + nums[j]) % mod;
            }
        }
        int k = queries.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            int x = queries[i][0];
            int y = queries[i][1];
            if (y <= m) {
                ans[i] = suf[y][x];
            } else {
                int s = 0;
                for (int j = x; j < n; j += y) {
                    s = (s + nums[j]) % mod;
                }
                ans[i] = s;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> solve(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        int m = (int) sqrt(n);
        const int mod = 1e9 + 7;
        int suf[m + 1][n + 1];
        memset(suf, 0, sizeof(suf));
        for (int i = 1; i <= m; ++i) {
            for (int j = n - 1; ~j; --j) {
                suf[i][j] = (suf[i][min(n, j + i)] + nums[j]) % mod;
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int x = q[0], y = q[1];
            if (y <= m) {
                ans.push_back(suf[y][x]);
            } else {
                int s = 0;
                for (int i = x; i < n; i += y) {
                    s = (s + nums[i]) % mod;
                }
                ans.push_back(s);
            }
        }
        return ans;
    }
};
```

```go
func solve(nums []int, queries [][]int) (ans []int) {
	n := len(nums)
	m := int(math.Sqrt(float64(n)))
	const mod int = 1e9 + 7
	suf := make([][]int, m+1)
	for i := range suf {
		suf[i] = make([]int, n+1)
		for j := n - 1; j >= 0; j-- {
			suf[i][j] = (suf[i][min(n, j+i)] + nums[j]) % mod
		}
	}
	for _, q := range queries {
		x, y := q[0], q[1]
		if y <= m {
			ans = append(ans, suf[y][x])
		} else {
			s := 0
			for i := x; i < n; i += y {
				s = (s + nums[i]) % mod
			}
			ans = append(ans, s)
		}
	}
	return
}
```

```ts
function solve(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const m = Math.floor(Math.sqrt(n));
    const mod = 10 ** 9 + 7;
    const suf: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = n - 1; j >= 0; --j) {
            suf[i][j] = (suf[i][Math.min(n, j + i)] + nums[j]) % mod;
        }
    }
    const ans: number[] = [];
    for (const [x, y] of queries) {
        if (y <= m) {
            ans.push(suf[y][x]);
        } else {
            let s = 0;
            for (let i = x; i < n; i += y) {
                s = (s + nums[i]) % mod;
            }
            ans.push(s);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
