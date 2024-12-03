---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3366.Minimum%20Array%20Sum/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3366. Minimum Array Sum](https://leetcode.com/problems/minimum-array-sum)

[中文文档](/solution/3300-3399/3366.Minimum%20Array%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and three integers <code>k</code>, <code>op1</code>, and <code>op2</code>.</p>

<p>You can perform the following operations on <code>nums</code>:</p>

<ul>
	<li><strong>Operation 1</strong>: Choose an index <code>i</code> and divide <code>nums[i]</code> by 2, <strong>rounding up</strong> to the nearest whole number. You can perform this operation at most <code>op1</code> times, and not more than <strong>once</strong> per index.</li>
	<li><strong>Operation 2</strong>: Choose an index <code>i</code> and subtract <code>k</code> from <code>nums[i]</code>, but only if <code>nums[i]</code> is greater than or equal to <code>k</code>. You can perform this operation at most <code>op2</code> times, and not more than <strong>once</strong> per index.</li>
</ul>

<p><strong>Note:</strong> Both operations can be applied to the same index, but at most once each.</p>

<p>Return the <strong>minimum</strong> possible <strong>sum</strong> of all elements in <code>nums</code> after performing any number of operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,8,3,19,3], k = 3, op1 = 1, op2 = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">23</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply Operation 2 to <code>nums[1] = 8</code>, making <code>nums[1] = 5</code>.</li>
	<li>Apply Operation 1 to <code>nums[3] = 19</code>, making <code>nums[3] = 10</code>.</li>
	<li>The resulting array becomes <code>[2, 5, 3, 10, 3]</code>, which has the minimum possible sum of 23 after applying the operations.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,3], k = 3, op1 = 2, op2 = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply Operation 1 to <code>nums[0] = 2</code>, making <code>nums[0] = 1</code>.</li>
	<li>Apply Operation 1 to <code>nums[1] = 4</code>, making <code>nums[1] = 2</code>.</li>
	<li>Apply Operation 2 to <code>nums[2] = 3</code>, making <code>nums[2] = 0</code>.</li>
	<li>The resulting array becomes <code>[1, 2, 0]</code>, which has the minimum possible sum of 3 after applying the operations.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code><font face="monospace">0 &lt;= nums[i] &lt;= 10<sup>5</sup></font></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= op1, op2 &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

For convenience, we denote the given $k$ as $d$.

Next, we define $f[i][j][k]$ to represent the minimum sum of the first $i$ numbers using $j$ operations of type 1 and $k$ operations of type 2. Initially, $f[0][0][0] = 0$, and the rest $f[i][j][k] = +\infty$.

Consider how to transition the state for $f[i][j][k]$. We can enumerate the $i$-th number $x$ and then consider the impact of $x$ on $f[i][j][k]$:

-   If $x$ does not use operation 1 or operation 2, then $f[i][j][k] = f[i-1][j][k] + x$;
-   If $j \gt 0$, then we can use operation 1. In this case, $f[i][j][k] = \min(f[i][j][k], f[i-1][j-1][k] + \lceil \frac{x+1}{2} \rceil)$;
-   If $k \gt 0$ and $x \geq d$, then we can use operation 2. In this case, $f[i][j][k] = \min(f[i][j][k], f[i-1][j][k-1] + (x - d))$;
-   If $j \gt 0$ and $k \gt 0$, then we can use both operation 1 and operation 2. If we use operation 1 first, then $x$ becomes $\lceil \frac{x+1}{2} \rceil$. If $x \geq d$, then we can use operation 2. In this case, $f[i][j][k] = \min(f[i][j][k], f[i-1][j-1][k-1] + \lceil \frac{x+1}{2} \rceil - d)$. If we use operation 2 first, then $x$ becomes $x - d$. If $x \geq d$, then we can use operation 1. In this case, $f[i][j][k] = \min(f[i][j][k], f[i-1][j-1][k-1] + \lceil \frac{x-d+1}{2} \rceil)$.

The final answer is $\min_{j=0}^{op1} \min_{k=0}^{op2} f[n][j][k]$. If it is $+\infty$, then output $-1$.

The time complexity is $O(n \times \textit{op1} \times \textit{op2})$, and the space complexity is $O(n \times \textit{op1} \times \textit{op2})$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minArraySum(self, nums: List[int], d: int, op1: int, op2: int) -> int:
        n = len(nums)
        f = [[[inf] * (op2 + 1) for _ in range(op1 + 1)] for _ in range(n + 1)]
        f[0][0][0] = 0
        for i, x in enumerate(nums, 1):
            for j in range(op1 + 1):
                for k in range(op2 + 1):
                    f[i][j][k] = f[i - 1][j][k] + x
                    if j > 0:
                        f[i][j][k] = min(f[i][j][k], f[i - 1][j - 1][k] + (x + 1) // 2)
                    if k > 0 and x >= d:
                        f[i][j][k] = min(f[i][j][k], f[i - 1][j][k - 1] + (x - d))
                    if j > 0 and k > 0:
                        y = (x + 1) // 2
                        if y >= d:
                            f[i][j][k] = min(f[i][j][k], f[i - 1][j - 1][k - 1] + y - d)
                        if x >= d:
                            f[i][j][k] = min(
                                f[i][j][k], f[i - 1][j - 1][k - 1] + (x - d + 1) // 2
                            )
        ans = inf
        for j in range(op1 + 1):
            for k in range(op2 + 1):
                ans = min(ans, f[n][j][k])
        return ans
```

#### Java

```java
class Solution {
    public int minArraySum(int[] nums, int d, int op1, int op2) {
        int n = nums.length;
        int[][][] f = new int[n + 1][op1 + 1][op2 + 1];
        final int inf = 1 << 29;
        for (var g : f) {
            for (var h : g) {
                Arrays.fill(h, inf);
            }
        }
        f[0][0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];
            for (int j = 0; j <= op1; ++j) {
                for (int k = 0; k <= op2; ++k) {
                    f[i][j][k] = f[i - 1][j][k] + x;
                    if (j > 0) {
                        f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j - 1][k] + (x + 1) / 2);
                    }
                    if (k > 0 && x >= d) {
                        f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k - 1] + (x - d));
                    }
                    if (j > 0 && k > 0) {
                        int y = (x + 1) / 2;
                        if (y >= d) {
                            f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j - 1][k - 1] + (y - d));
                        }
                        if (x >= d) {
                            f[i][j][k]
                                = Math.min(f[i][j][k], f[i - 1][j - 1][k - 1] + (x - d + 1) / 2);
                        }
                    }
                }
            }
        }
        int ans = inf;
        for (int j = 0; j <= op1; ++j) {
            for (int k = 0; k <= op2; ++k) {
                ans = Math.min(ans, f[n][j][k]);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minArraySum(vector<int>& nums, int d, int op1, int op2) {
        int n = nums.size();
        int f[n + 1][op1 + 1][op2 + 1];
        memset(f, 0x3f, sizeof f);
        f[0][0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];
            for (int j = 0; j <= op1; ++j) {
                for (int k = 0; k <= op2; ++k) {
                    f[i][j][k] = f[i - 1][j][k] + x;
                    if (j > 0) {
                        f[i][j][k] = min(f[i][j][k], f[i - 1][j - 1][k] + (x + 1) / 2);
                    }
                    if (k > 0 && x >= d) {
                        f[i][j][k] = min(f[i][j][k], f[i - 1][j][k - 1] + (x - d));
                    }
                    if (j > 0 && k > 0) {
                        int y = (x + 1) / 2;
                        if (y >= d) {
                            f[i][j][k] = min(f[i][j][k], f[i - 1][j - 1][k - 1] + (y - d));
                        }
                        if (x >= d) {
                            f[i][j][k] = min(f[i][j][k], f[i - 1][j - 1][k - 1] + (x - d + 1) / 2);
                        }
                    }
                }
            }
        }
        int ans = INT_MAX;
        for (int j = 0; j <= op1; ++j) {
            for (int k = 0; k <= op2; ++k) {
                ans = min(ans, f[n][j][k]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minArraySum(nums []int, d int, op1 int, op2 int) int {
	n := len(nums)
	const inf = int(1e9)
	f := make([][][]int, n+1)
	for i := range f {
		f[i] = make([][]int, op1+1)
		for j := range f[i] {
			f[i][j] = make([]int, op2+1)
			for k := range f[i][j] {
				f[i][j][k] = inf
			}
		}
	}
	f[0][0][0] = 0
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		for j := 0; j <= op1; j++ {
			for k := 0; k <= op2; k++ {
				f[i][j][k] = f[i-1][j][k] + x
				if j > 0 {
					f[i][j][k] = min(f[i][j][k], f[i-1][j-1][k]+(x+1)/2)
				}
				if k > 0 && x >= d {
					f[i][j][k] = min(f[i][j][k], f[i-1][j][k-1]+(x-d))
				}
				if j > 0 && k > 0 {
					y := (x + 1) / 2
					if y >= d {
						f[i][j][k] = min(f[i][j][k], f[i-1][j-1][k-1]+(y-d))
					}
					if x >= d {
						f[i][j][k] = min(f[i][j][k], f[i-1][j-1][k-1]+(x-d+1)/2)
					}
				}
			}
		}
	}
	ans := inf
	for j := 0; j <= op1; j++ {
		for k := 0; k <= op2; k++ {
			ans = min(ans, f[n][j][k])
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minArraySum(nums: number[], d: number, op1: number, op2: number): number {
    const n = nums.length;
    const inf = Number.MAX_SAFE_INTEGER;

    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: op1 + 1 }, () => Array(op2 + 1).fill(inf)),
    );
    f[0][0][0] = 0;

    for (let i = 1; i <= n; i++) {
        const x = nums[i - 1];
        for (let j = 0; j <= op1; j++) {
            for (let k = 0; k <= op2; k++) {
                f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k] + x);
                if (j > 0) {
                    f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j - 1][k] + Math.floor((x + 1) / 2));
                }
                if (k > 0 && x >= d) {
                    f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k - 1] + (x - d));
                }
                if (j > 0 && k > 0) {
                    const y = Math.floor((x + 1) / 2);
                    if (y >= d) {
                        f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j - 1][k - 1] + (y - d));
                    }
                    if (x >= d) {
                        f[i][j][k] = Math.min(
                            f[i][j][k],
                            f[i - 1][j - 1][k - 1] + Math.floor((x - d + 1) / 2),
                        );
                    }
                }
            }
        }
    }

    let ans = inf;
    for (let j = 0; j <= op1; j++) {
        for (let l = 0; l <= op2; l++) {
            ans = Math.min(ans, f[n][j][l]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
