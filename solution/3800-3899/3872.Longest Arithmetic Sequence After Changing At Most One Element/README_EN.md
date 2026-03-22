---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3872.Longest%20Arithmetic%20Sequence%20After%20Changing%20At%20Most%20One%20Element/README_EN.md
rating: 2042
source: Weekly Contest 493 Q3
tags:
    - Array
    - Enumeration
---

<!-- problem:start -->

# [3872. Longest Arithmetic Sequence After Changing At Most One Element](https://leetcode.com/problems/longest-arithmetic-sequence-after-changing-at-most-one-element)

[中文文档](/solution/3800-3899/3872.Longest%20Arithmetic%20Sequence%20After%20Changing%20At%20Most%20One%20Element/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <span data-keyword="subarray">subarray</span> is <strong>arithmetic</strong> if the difference between consecutive elements in the subarray is constant.</p>

<p>You can replace <strong>at most one</strong> element in <code>nums</code> with any <strong>integer</strong>. Then, you select an arithmetic subarray from <code>nums</code>.</p>

<p>Return an integer denoting the <strong>maximum</strong> length of the arithmetic subarray you can select.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,7,5,10,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace <code>nums[3] = 10</code> with 3. The array becomes <code>[9, 7, 5, 3, 1]</code>.</li>
	<li>Select the subarray <code>[<u><strong>9, 7, 5, 3, 1</strong></u>]</code>, which is arithmetic because consecutive elements have a common difference of -2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,6,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace <code>nums[0] = 1</code> with -2. The array becomes <code>[-2, 2, 6, 7]</code>.</li>
	<li>Select the subarray <code>[<u><strong>-2, 2, 6</strong></u>, 7]</code>, which is arithmetic because consecutive elements have a common difference of 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix and Suffix Decomposition + Enumeration

We first compute the differences between adjacent elements of the array, stored as array $d$, where $d[i] = nums[i] - nums[i - 1]$.

Next, we define two arrays $f$ and $g$, where $f[i]$ represents the length of the longest arithmetic subarray ending at the $i$-th element, and $g[i]$ represents the length of the longest arithmetic subarray starting at the $i$-th element. Initially, $f[0] = 1$, $g[n - 1] = 1$, and all other elements are initialized to $2$.

We can compute the values of $f$ and $g$ in a single pass:

- For $f$: if $d[i] == d[i - 1]$, then $f[i] = f[i - 1] + 1$.
- For $g$: if $d[i + 1] == d[i + 2]$, then $g[i] = g[i + 1] + 1$.

Then we initialize the answer to $3$, since we can always form an arithmetic subarray of length $3$ by replacing one element. We then enumerate each element and try to replace it with a suitable value to form a longer arithmetic subarray:

- For each element $i$, we can directly use $f[i]$ or $g[i]$ to update the answer.
- If $i > 0$, we can replace $nums[i]$ with $nums[i - 1] + d[i - 1]$ to extend the arithmetic subarray ending at $i - 1$, updating the answer to $f[i - 1] + 1$.
- If $i + 1 < n$, we can replace $nums[i]$ with $nums[i + 1] - d[i + 1]$ to extend the arithmetic subarray starting at $i + 1$, updating the answer to $g[i + 1] + 1$.
- If $0 < i < n - 1$, we can replace $nums[i]$ with $nums[i - 1] + \frac{nums[i + 1] - nums[i - 1]}{2}$ to try to bridge $f[i - 1]$ and $g[i + 1]$. If this value is an integer and matches both $d[i - 1]$ and $d[i + 1]$, we update the answer to $3 + (f[i - 1] - 1) + (g[i + 1] - 1)$.

Finally, return the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestArithmetic(self, nums: List[int]) -> int:
        n = len(nums)
        d = [0] * n
        for i in range(1, n):
            d[i] = nums[i] - nums[i - 1]

        f = [2] * n
        g = [2] * n
        f[0] = g[n - 1] = 1
        for i in range(2, n):
            if d[i] == d[i - 1]:
                f[i] = f[i - 1] + 1
        for i in range(n - 3, -1, -1):
            if d[i + 1] == d[i + 2]:
                g[i] = g[i + 1] + 1

        ans = 3
        for i in range(n):
            ans = max(ans, f[i], g[i])
            if i > 0:
                ans = max(ans, f[i - 1] + 1)
            if i + 1 < n:
                ans = max(ans, g[i + 1] + 1)
            if 0 < i < n - 1:
                diff = nums[i + 1] - nums[i -1]
                if diff % 2 == 0:
                    diff //= 2
                    k = 3
                    if i > 1 and diff == d[i - 1]:
                        k += f[i - 1] - 1
                    if i < n - 2 and diff == d[i + 2]:
                        k += g[i + 1] - 1
                    ans = max(ans, k)
        return ans
```

#### Java

```java
class Solution {
    public int longestArithmetic(int[] nums) {
        int n = nums.length;
        int[] d = new int[n];
        for (int i = 1; i < n; i++) {
            d[i] = nums[i] - nums[i - 1];
        }

        int[] f = new int[n];
        int[] g = new int[n];
        Arrays.fill(f, 2);
        Arrays.fill(g, 2);
        f[0] = 1;
        g[n - 1] = 1;

        for (int i = 2; i < n; i++) {
            if (d[i] == d[i - 1]) {
                f[i] = f[i - 1] + 1;
            }
        }

        for (int i = n - 3; i >= 0; i--) {
            if (d[i + 1] == d[i + 2]) {
                g[i] = g[i + 1] + 1;
            }
        }

        int ans = 3;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.max(f[i], g[i]));
            if (i > 0) {
                ans = Math.max(ans, f[i - 1] + 1);
            }
            if (i + 1 < n) {
                ans = Math.max(ans, g[i + 1] + 1);
            }
            if (i > 0 && i < n - 1) {
                int diff = nums[i + 1] - nums[i - 1];
                if (diff % 2 == 0) {
                    diff /= 2;
                    int k = 3;
                    if (i > 1 && diff == d[i - 1]) {
                        k += f[i - 1] - 1;
                    }
                    if (i < n - 2 && diff == d[i + 2]) {
                        k += g[i + 1] - 1;
                    }
                    ans = Math.max(ans, k);
                }
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
    int longestArithmetic(vector<int>& nums) {
        int n = nums.size();
        vector<int> d(n);
        for (int i = 1; i < n; i++) {
            d[i] = nums[i] - nums[i - 1];
        }

        vector<int> f(n, 2), g(n, 2);
        f[0] = 1;
        g[n - 1] = 1;

        for (int i = 2; i < n; i++) {
            if (d[i] == d[i - 1]) {
                f[i] = f[i - 1] + 1;
            }
        }

        for (int i = n - 3; i >= 0; i--) {
            if (d[i + 1] == d[i + 2]) {
                g[i] = g[i + 1] + 1;
            }
        }

        int ans = 3;
        for (int i = 0; i < n; i++) {
            ans = max(ans, max(f[i], g[i]));
            if (i > 0) {
                ans = max(ans, f[i - 1] + 1);
            }
            if (i + 1 < n) {
                ans = max(ans, g[i + 1] + 1);
            }
            if (i > 0 && i < n - 1) {
                int diff = nums[i + 1] - nums[i - 1];
                if (diff % 2 == 0) {
                    diff /= 2;
                    int k = 3;
                    if (i > 1 && diff == d[i - 1]) {
                        k += f[i - 1] - 1;
                    }
                    if (i < n - 2 && diff == d[i + 2]) {
                        k += g[i + 1] - 1;
                    }
                    ans = max(ans, k);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestArithmetic(nums []int) int {
	n := len(nums)
	d := make([]int, n)
	for i := 1; i < n; i++ {
		d[i] = nums[i] - nums[i-1]
	}

	f := make([]int, n)
	g := make([]int, n)
	for i := range f {
		f[i], g[i] = 2, 2
	}
	f[0], g[n-1] = 1, 1

	for i := 2; i < n; i++ {
		if d[i] == d[i-1] {
			f[i] = f[i-1] + 1
		}
	}

	for i := n - 3; i >= 0; i-- {
		if d[i+1] == d[i+2] {
			g[i] = g[i+1] + 1
		}
	}

	ans := 3
	for i := 0; i < n; i++ {
		ans = max(ans, f[i], g[i])

		if i > 0 {
			ans = max(ans, f[i-1]+1)
		}
		if i+1 < n {
			ans = max(ans, g[i+1]+1)
		}

		if i > 0 && i < n-1 {
			diff := nums[i+1] - nums[i-1]
			if diff%2 == 0 {
				diff /= 2
				k := 3
				if i > 1 && diff == d[i-1] {
					k += f[i-1] - 1
				}
				if i < n-2 && diff == d[i+2] {
					k += g[i+1] - 1
				}
				ans = max(ans, k)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestArithmetic(nums: number[]): number {
    const n = nums.length;
    const d = new Array(n).fill(0);

    for (let i = 1; i < n; i++) {
        d[i] = nums[i] - nums[i - 1];
    }

    const f = new Array(n).fill(2);
    const g = new Array(n).fill(2);
    f[0] = 1;
    g[n - 1] = 1;

    for (let i = 2; i < n; i++) {
        if (d[i] === d[i - 1]) {
            f[i] = f[i - 1] + 1;
        }
    }

    for (let i = n - 3; i >= 0; i--) {
        if (d[i + 1] === d[i + 2]) {
            g[i] = g[i + 1] + 1;
        }
    }

    let ans = 3;
    for (let i = 0; i < n; i++) {
        ans = Math.max(ans, f[i], g[i]);
        if (i > 0) {
            ans = Math.max(ans, f[i - 1] + 1);
        }
        if (i + 1 < n) {
            ans = Math.max(ans, g[i + 1] + 1);
        }
        if (i > 0 && i < n - 1) {
            let diff = nums[i + 1] - nums[i - 1];
            if (diff % 2 === 0) {
                diff = Math.floor(diff / 2);
                let k = 3;
                if (i > 1 && diff === d[i - 1]) {
                    k += f[i - 1] - 1;
                }
                if (i < n - 2 && diff === d[i + 2]) {
                    k += g[i + 1] - 1;
                }
                ans = Math.max(ans, k);
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
