---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3040.Maximum%20Number%20of%20Operations%20With%20the%20Same%20Score%20II/README_EN.md
rating: 1708
source: Biweekly Contest 124 Q3
tags:
    - Memoization
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3040. Maximum Number of Operations With the Same Score II](https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-ii)

[中文文档](/solution/3000-3099/3040.Maximum%20Number%20of%20Operations%20With%20the%20Same%20Score%20II/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers called <code>nums</code>, you can perform <strong>any</strong> of the following operation while <code>nums</code> contains <strong>at least</strong> <code>2</code> elements:</p>

<ul>
	<li>Choose the first two elements of <code>nums</code> and delete them.</li>
	<li>Choose the last two elements of <code>nums</code> and delete them.</li>
	<li>Choose the first and the last elements of <code>nums</code> and delete them.</li>
</ul>

<p>The<strong> score</strong> of the operation is the sum of the deleted elements.</p>

<p>Your task is to find the <strong>maximum</strong> number of operations that can be performed, such that <strong>all operations have the same score</strong>.</p>

<p>Return <em>the <strong>maximum</strong> number of operations possible that satisfy the condition mentioned above</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,2,3,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We perform the following operations:
- Delete the first two elements, with score 3 + 2 = 5, nums = [1,2,3,4].
- Delete the first and the last elements, with score 1 + 4 = 5, nums = [2,3].
- Delete the first and the last elements, with score 2 + 3 = 5, nums = [].
We are unable to perform any more operations as nums is empty.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,6,1,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We perform the following operations:
- Delete the first two elements, with score 3 + 2 = 5, nums = [6,1,4].
- Delete the last two elements, with score 1 + 4 = 5, nums = [6].
It can be proven that we can perform at most 2 operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memorization Search

There are three possible values for the score $s$, which are $s = nums[0] + nums[1]$, $s = nums[0] + nums[n-1]$, and $s = nums[n-1] + nums[n-2]$. We can perform memorization search for these three cases separately.

We design a function $dfs(i, j)$, which represents the maximum number of operations from index $i$ to index $j$ when the score is $s$. The execution process of function $dfs(i, j)$ is as follows:

- If $j - i < 1$, it means that the length of the interval $[i, j]$ is less than $2$, and no operation can be performed, so return $0$.
- If $nums[i] + nums[i+1] = s$, it means that the elements at index $i$ and index $i+1$ can be deleted. In this case, the maximum number of operations is $1 + dfs(i+2, j)$.
- If $nums[i] + nums[j] = s$, it means that the elements at index $i$ and index $j$ can be deleted. In this case, the maximum number of operations is $1 + dfs(i+1, j-1)$.
- If $nums[j-1] + nums[j] = s$, it means that the elements at index $j-1$ and index $j$ can be deleted. In this case, the maximum number of operations is $1 + dfs(i, j-2)$.
- Return the maximum of the above values.

Finally, we calculate the maximum number of operations for the three cases separately, and return the maximum value.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxOperations(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int, j: int, s: int) -> int:
            if j - i < 1:
                return 0
            ans = 0
            if nums[i] + nums[i + 1] == s:
                ans = max(ans, 1 + dfs(i + 2, j, s))
            if nums[i] + nums[j] == s:
                ans = max(ans, 1 + dfs(i + 1, j - 1, s))
            if nums[j - 1] + nums[j] == s:
                ans = max(ans, 1 + dfs(i, j - 2, s))
            return ans

        n = len(nums)
        a = dfs(2, n - 1, nums[0] + nums[1])
        b = dfs(0, n - 3, nums[-1] + nums[-2])
        c = dfs(1, n - 2, nums[0] + nums[-1])
        return 1 + max(a, b, c)
```

#### Java

```java
class Solution {
    private Integer[][] f;
    private int[] nums;
    private int s;
    private int n;

    public int maxOperations(int[] nums) {
        this.nums = nums;
        n = nums.length;
        int a = g(2, n - 1, nums[0] + nums[1]);
        int b = g(0, n - 3, nums[n - 2] + nums[n - 1]);
        int c = g(1, n - 2, nums[0] + nums[n - 1]);
        return 1 + Math.max(a, Math.max(b, c));
    }

    private int g(int i, int j, int s) {
        f = new Integer[n][n];
        this.s = s;
        return dfs(i, j);
    }

    private int dfs(int i, int j) {
        if (j - i < 1) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0;
        if (nums[i] + nums[i + 1] == s) {
            ans = Math.max(ans, 1 + dfs(i + 2, j));
        }
        if (nums[i] + nums[j] == s) {
            ans = Math.max(ans, 1 + dfs(i + 1, j - 1));
        }
        if (nums[j - 1] + nums[j] == s) {
            ans = Math.max(ans, 1 + dfs(i, j - 2));
        }
        return f[i][j] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxOperations(vector<int>& nums) {
        int n = nums.size();
        int f[n][n];
        auto g = [&](int i, int j, int s) -> int {
            memset(f, -1, sizeof(f));
            function<int(int, int)> dfs = [&](int i, int j) -> int {
                if (j - i < 1) {
                    return 0;
                }
                if (f[i][j] != -1) {
                    return f[i][j];
                }
                int ans = 0;
                if (nums[i] + nums[i + 1] == s) {
                    ans = max(ans, 1 + dfs(i + 2, j));
                }
                if (nums[i] + nums[j] == s) {
                    ans = max(ans, 1 + dfs(i + 1, j - 1));
                }
                if (nums[j - 1] + nums[j] == s) {
                    ans = max(ans, 1 + dfs(i, j - 2));
                }
                return f[i][j] = ans;
            };
            return dfs(i, j);
        };
        int a = g(2, n - 1, nums[0] + nums[1]);
        int b = g(0, n - 3, nums[n - 2] + nums[n - 1]);
        int c = g(1, n - 2, nums[0] + nums[n - 1]);
        return 1 + max({a, b, c});
    }
};
```

#### Go

```go
func maxOperations(nums []int) int {
	n := len(nums)
	var g func(i, j, s int) int
	g = func(i, j, s int) int {
		f := make([][]int, n)
		for i := range f {
			f[i] = make([]int, n)
			for j := range f {
				f[i][j] = -1
			}
		}
		var dfs func(i, j int) int
		dfs = func(i, j int) int {
			if j-i < 1 {
				return 0
			}
			if f[i][j] != -1 {
				return f[i][j]
			}
			ans := 0
			if nums[i]+nums[i+1] == s {
				ans = max(ans, 1+dfs(i+2, j))
			}

			if nums[i]+nums[j] == s {
				ans = max(ans, 1+dfs(i+1, j-1))
			}

			if nums[j-1]+nums[j] == s {
				ans = max(ans, 1+dfs(i, j-2))
			}
			f[i][j] = ans
			return ans
		}
		return dfs(i, j)
	}
	a := g(2, n-1, nums[0]+nums[1])
	b := g(0, n-3, nums[n-1]+nums[n-2])
	c := g(1, n-2, nums[0]+nums[n-1])
	return 1 + max(a, b, c)
}
```

#### TypeScript

```ts
function maxOperations(nums: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n));
    const g = (i: number, j: number, s: number): number => {
        f.forEach(row => row.fill(-1));
        const dfs = (i: number, j: number): number => {
            if (j - i < 1) {
                return 0;
            }
            if (f[i][j] !== -1) {
                return f[i][j];
            }
            let ans = 0;
            if (nums[i] + nums[i + 1] === s) {
                ans = Math.max(ans, 1 + dfs(i + 2, j));
            }
            if (nums[i] + nums[j] === s) {
                ans = Math.max(ans, 1 + dfs(i + 1, j - 1));
            }
            if (nums[j - 1] + nums[j] === s) {
                ans = Math.max(ans, 1 + dfs(i, j - 2));
            }
            return (f[i][j] = ans);
        };
        return dfs(i, j);
    };
    const a = g(2, n - 1, nums[0] + nums[1]);
    const b = g(0, n - 3, nums[n - 2] + nums[n - 1]);
    const c = g(1, n - 2, nums[0] + nums[n - 1]);
    return 1 + Math.max(a, b, c);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
