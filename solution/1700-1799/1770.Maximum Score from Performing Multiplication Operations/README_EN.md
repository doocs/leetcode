---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1770.Maximum%20Score%20from%20Performing%20Multiplication%20Operations/README_EN.md
rating: 2068
source: Weekly Contest 229 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1770. Maximum Score from Performing Multiplication Operations](https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations)

[中文文档](/solution/1700-1799/1770.Maximum%20Score%20from%20Performing%20Multiplication%20Operations/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums</code> and <code>multipliers</code><strong> </strong>of size <code>n</code> and <code>m</code> respectively, where <code>n &gt;= m</code>.</p>

<p>You begin with a score of <code>0</code>. You want to perform <strong>exactly</strong> <code>m</code> operations. On the <code>i<sup>th</sup></code> operation (<strong>0-indexed</strong>) you will:</p>

<ul>
    <li>Choose one integer <code>x</code> from <strong>either the start or the end </strong>of the array <code>nums</code>.</li>
    <li>Add <code>multipliers[i] * x</code> to your score.
    <ul>
        <li>Note that <code>multipliers[0]</code> corresponds to the first operation, <code>multipliers[1]</code> to the second operation, and so on.</li>
    </ul>
    </li>
    <li>Remove <code>x</code> from <code>nums</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> score after performing </em><code>m</code> <em>operations.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], multipliers = [3,2,1]
<strong>Output:</strong> 14
<strong>Explanation:</strong>&nbsp;An optimal solution is as follows:
- Choose from the end, [1,2,<strong><u>3</u></strong>], adding 3 * 3 = 9 to the score.
- Choose from the end, [1,<strong><u>2</u></strong>], adding 2 * 2 = 4 to the score.
- Choose from the end, [<strong><u>1</u></strong>], adding 1 * 1 = 1 to the score.
The total score is 9 + 4 + 1 = 14.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
<strong>Output:</strong> 102
<strong>Explanation: </strong>An optimal solution is as follows:
- Choose from the start, [<u><strong>-5</strong></u>,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
- Choose from the start, [<strong><u>-3</u></strong>,-3,-2,7,1], adding -3 * -5 = 15 to the score.
- Choose from the start, [<strong><u>-3</u></strong>,-2,7,1], adding -3 * 3 = -9 to the score.
- Choose from the end, [-2,7,<strong><u>1</u></strong>], adding 1 * 4 = 4 to the score.
- Choose from the end, [-2,<strong><u>7</u></strong>], adding 7 * 6 = 42 to the score. 
The total score is 50 + 15 - 9 + 4 + 42 = 102.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == multipliers.length</code></li>
	<li><code>1 &lt;= m &lt;= 300</code></li>
	<li><code>m &lt;= n &lt;= 10<sup>5</sup></code><code> </code></li>
	<li><code>-1000 &lt;= nums[i], multipliers[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def maximumScore(self, nums: List[int], multipliers: List[int]) -> int:
        @cache
        def f(i, j, k):
            if k >= m or i >= n or j < 0:
                return 0
            a = f(i + 1, j, k + 1) + nums[i] * multipliers[k]
            b = f(i, j - 1, k + 1) + nums[j] * multipliers[k]
            return max(a, b)

        n = len(nums)
        m = len(multipliers)
        return f(0, n - 1, 0)
```

```java
class Solution {
    private Integer[][] f;
    private int[] multipliers;
    private int[] nums;
    private int n;
    private int m;

    public int maximumScore(int[] nums, int[] multipliers) {
        n = nums.length;
        m = multipliers.length;
        f = new Integer[m][m];
        this.nums = nums;
        this.multipliers = multipliers;
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m || j >= m || (i + j) >= m) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int k = i + j;
        int a = dfs(i + 1, j) + nums[i] * multipliers[k];
        int b = dfs(i, j + 1) + nums[n - 1 - j] * multipliers[k];
        f[i][j] = Math.max(a, b);
        return f[i][j];
    }
}
```

```cpp
class Solution {
public:
    int maximumScore(vector<int>& nums, vector<int>& multipliers) {
        int n = nums.size(), m = multipliers.size();
        int f[m][m];
        memset(f, 0x3f, sizeof f);
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= m || j >= m || (i + j) >= m) return 0;
            if (f[i][j] != 0x3f3f3f3f) return f[i][j];
            int k = i + j;
            int a = dfs(i + 1, j) + nums[i] * multipliers[k];
            int b = dfs(i, j + 1) + nums[n - j - 1] * multipliers[k];
            return f[i][j] = max(a, b);
        };
        return dfs(0, 0);
    }
};
```

```go
func maximumScore(nums []int, multipliers []int) int {
	n, m := len(nums), len(multipliers)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = 1 << 30
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m || j >= m || i+j >= m {
			return 0
		}
		if f[i][j] != 1<<30 {
			return f[i][j]
		}
		k := i + j
		a := dfs(i+1, j) + nums[i]*multipliers[k]
		b := dfs(i, j+1) + nums[n-j-1]*multipliers[k]
		f[i][j] = max(a, b)
		return f[i][j]
	}
	return dfs(0, 0)
}
```

```ts
function maximumScore(nums: number[], multipliers: number[]): number {
    const inf = 1 << 30;
    const n = nums.length;
    const m = multipliers.length;
    const f = new Array(m + 1).fill(0).map(() => new Array(m + 1).fill(-inf));
    f[0][0] = 0;
    let ans = -inf;
    for (let i = 0; i <= m; ++i) {
        for (let j = 0; j <= m - i; ++j) {
            const k = i + j - 1;
            if (i > 0) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j] + nums[i - 1] * multipliers[k]);
            }
            if (j > 0) {
                f[i][j] = Math.max(f[i][j], f[i][j - 1] + nums[n - j] * multipliers[k]);
            }
            if (i + j === m) {
                ans = Math.max(ans, f[i][j]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def maximumScore(self, nums: List[int], multipliers: List[int]) -> int:
        n, m = len(nums), len(multipliers)
        f = [[-inf] * (m + 1) for _ in range(m + 1)]
        f[0][0] = 0
        ans = -inf
        for i in range(m + 1):
            for j in range(m - i + 1):
                k = i + j - 1
                if i > 0:
                    f[i][j] = max(f[i][j], f[i - 1][j] + multipliers[k] * nums[i - 1])
                if j > 0:
                    f[i][j] = max(f[i][j], f[i][j - 1] + multipliers[k] * nums[n - j])
                if i + j == m:
                    ans = max(ans, f[i][j])
        return ans
```

```java
class Solution {
    public int maximumScore(int[] nums, int[] multipliers) {
        final int inf = 1 << 30;
        int n = nums.length, m = multipliers.length;
        int[][] f = new int[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(f[i], -inf);
        }
        f[0][0] = 0;
        int ans = -inf;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= m - i; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + multipliers[k] * nums[i - 1]);
                }
                if (j > 0) {
                    f[i][j] = Math.max(f[i][j], f[i][j - 1] + multipliers[k] * nums[n - j]);
                }
                if (i + j == m) {
                    ans = Math.max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumScore(vector<int>& nums, vector<int>& multipliers) {
        const int inf = 1 << 30;
        int n = nums.size(), m = multipliers.size();
        vector<vector<int>> f(m + 1, vector<int>(m + 1, -inf));
        f[0][0] = 0;
        int ans = -inf;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= m - i; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    f[i][j] = max(f[i][j], f[i - 1][j] + multipliers[k] * nums[i - 1]);
                }
                if (j > 0) {
                    f[i][j] = max(f[i][j], f[i][j - 1] + multipliers[k] * nums[n - j]);
                }
                if (i + j == m) {
                    ans = max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
};
```

```go
func maximumScore(nums []int, multipliers []int) int {
	const inf int = 1 << 30
	n, m := len(nums), len(multipliers)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, m+1)
		for j := range f {
			f[i][j] = -inf
		}
	}
	f[0][0] = 0
	ans := -inf
	for i := 0; i <= m; i++ {
		for j := 0; j <= m-i; j++ {
			k := i + j - 1
			if i > 0 {
				f[i][j] = max(f[i][j], f[i-1][j]+multipliers[k]*nums[i-1])
			}
			if j > 0 {
				f[i][j] = max(f[i][j], f[i][j-1]+multipliers[k]*nums[n-j])
			}
			if i+j == m {
				ans = max(ans, f[i][j])
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
