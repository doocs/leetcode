---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3287.Find%20the%20Maximum%20Sequence%20Value%20of%20Array/README_EN.md
rating: 2545
source: Biweekly Contest 139 Q3
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3287. Find the Maximum Sequence Value of Array](https://leetcode.com/problems/find-the-maximum-sequence-value-of-array)

[中文文档](/solution/3200-3299/3287.Find%20the%20Maximum%20Sequence%20Value%20of%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>The <strong>value</strong> of a sequence <code>seq</code> of size <code>2 * x</code> is defined as:</p>

<ul>
	<li><code>(seq[0] OR seq[1] OR ... OR seq[x - 1]) XOR (seq[x] OR seq[x + 1] OR ... OR seq[2 * x - 1])</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> <strong>value</strong> of any <span data-keyword="subsequence-array">subsequence</span> of <code>nums</code> having size <code>2 * k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,6,7], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The subsequence <code>[2, 7]</code> has the maximum value of <code>2 XOR 7 = 5</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,2,5,6,7], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The subsequence <code>[4, 5, 6, 7]</code> has the maximum value of <code>(4 OR 5) XOR (6 OR 7) = 2</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 400</code></li>
	<li><code>1 &lt;= nums[i] &lt; 2<sup>7</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length / 2</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Prefix and Suffix Decomposition + Enumeration

We consider dividing the sequence into two parts, the first $k$ elements and the last $k$ elements, and calculate all possible XOR values for the prefixes and suffixes.

Define $f[i][j][x]$ to represent whether there exists a subset with an XOR value of $x$ by taking $j$ elements from the first $i$ elements. Define $g[i][j][y]$ to represent whether there exists a subset with an XOR value of $y$ by taking $j$ elements starting from index $i$.

Consider the transition equation for $f[i][j][x]$. For the $i$-th element (starting from $0$), we can choose to take it or not, so we have:

$$
f[i + 1][j][x] = f[i + 1][j][x] \lor f[i][j][x] \\
f[i + 1][j + 1][x \lor \text{nums}[i]] = f[i + 1][j + 1][x \lor \text{nums}[i]] \lor f[i][j][x]
$$

For the transition equation of $g[i][j][y]$, similarly for the $i$-th element (starting from $n - 1$), we can choose to take it or not, so we have:

$$
g[i - 1][j][y] = g[i - 1][j][y] \lor g[i][j][y] \\
g[i - 1][j + 1][y \lor \text{nums}[i - 1]] = g[i - 1][j + 1][y \lor \text{nums}[i - 1]] \lor g[i][j][y]
$$

Finally, we enumerate $i$ in the range $[k, n - k]$. For each $i$, we enumerate $x$ and $y$, where $0 \leq x, y < 2^7$. If both $f[i][k][x]$ and $g[i][k][y]$ are true, we update the answer $\text{ans} = \max(\text{ans}, x \oplus y)$.

The time complexity is $O(n \times m \times k)$, and the space complexity is $O(n \times m \times k)$, where $n$ is the length of the array, and $m = 2^7$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValue(self, nums: List[int], k: int) -> int:
        m = 1 << 7
        n = len(nums)
        f = [[[False] * m for _ in range(k + 2)] for _ in range(n + 1)]
        f[0][0][0] = True
        for i in range(n):
            for j in range(k + 1):
                for x in range(m):
                    f[i + 1][j][x] |= f[i][j][x]
                    f[i + 1][j + 1][x | nums[i]] |= f[i][j][x]

        g = [[[False] * m for _ in range(k + 2)] for _ in range(n + 1)]
        g[n][0][0] = True
        for i in range(n, 0, -1):
            for j in range(k + 1):
                for y in range(m):
                    g[i - 1][j][y] |= g[i][j][y]
                    g[i - 1][j + 1][y | nums[i - 1]] |= g[i][j][y]

        ans = 0
        for i in range(k, n - k + 1):
            for x in range(m):
                if f[i][k][x]:
                    for y in range(m):
                        if g[i][k][y]:
                            ans = max(ans, x ^ y)
        return ans
```

#### Java

```java
class Solution {
    public int maxValue(int[] nums, int k) {
        int m = 1 << 7;
        int n = nums.length;
        boolean[][][] f = new boolean[n + 1][k + 2][m];
        f[0][0][0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int x = 0; x < m; x++) {
                    if (f[i][j][x]) {
                        f[i + 1][j][x] = true;
                        f[i + 1][j + 1][x | nums[i]] = true;
                    }
                }
            }
        }

        boolean[][][] g = new boolean[n + 1][k + 2][m];
        g[n][0][0] = true;

        for (int i = n; i > 0; i--) {
            for (int j = 0; j <= k; j++) {
                for (int y = 0; y < m; y++) {
                    if (g[i][j][y]) {
                        g[i - 1][j][y] = true;
                        g[i - 1][j + 1][y | nums[i - 1]] = true;
                    }
                }
            }
        }

        int ans = 0;

        for (int i = k; i <= n - k; i++) {
            for (int x = 0; x < m; x++) {
                if (f[i][k][x]) {
                    for (int y = 0; y < m; y++) {
                        if (g[i][k][y]) {
                            ans = Math.max(ans, x ^ y);
                        }
                    }
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
    int maxValue(vector<int>& nums, int k) {
        int m = 1 << 7;
        int n = nums.size();

        vector<vector<vector<bool>>> f(n + 1, vector<vector<bool>>(k + 2, vector<bool>(m, false)));
        f[0][0][0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int x = 0; x < m; x++) {
                    if (f[i][j][x]) {
                        f[i + 1][j][x] = true;
                        f[i + 1][j + 1][x | nums[i]] = true;
                    }
                }
            }
        }

        vector<vector<vector<bool>>> g(n + 1, vector<vector<bool>>(k + 2, vector<bool>(m, false)));
        g[n][0][0] = true;

        for (int i = n; i > 0; i--) {
            for (int j = 0; j <= k; j++) {
                for (int y = 0; y < m; y++) {
                    if (g[i][j][y]) {
                        g[i - 1][j][y] = true;
                        g[i - 1][j + 1][y | nums[i - 1]] = true;
                    }
                }
            }
        }

        int ans = 0;

        for (int i = k; i <= n - k; i++) {
            for (int x = 0; x < m; x++) {
                if (f[i][k][x]) {
                    for (int y = 0; y < m; y++) {
                        if (g[i][k][y]) {
                            ans = max(ans, x ^ y);
                        }
                    }
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maxValue(nums []int, k int) int {
	m := 1 << 7
	n := len(nums)

	f := make([][][]bool, n+1)
	for i := range f {
		f[i] = make([][]bool, k+2)
		for j := range f[i] {
			f[i][j] = make([]bool, m)
		}
	}
	f[0][0][0] = true

	for i := 0; i < n; i++ {
		for j := 0; j <= k; j++ {
			for x := 0; x < m; x++ {
				if f[i][j][x] {
					f[i+1][j][x] = true
					f[i+1][j+1][x|nums[i]] = true
				}
			}
		}
	}

	g := make([][][]bool, n+1)
	for i := range g {
		g[i] = make([][]bool, k+2)
		for j := range g[i] {
			g[i][j] = make([]bool, m)
		}
	}
	g[n][0][0] = true

	for i := n; i > 0; i-- {
		for j := 0; j <= k; j++ {
			for y := 0; y < m; y++ {
				if g[i][j][y] {
					g[i-1][j][y] = true
					g[i-1][j+1][y|nums[i-1]] = true
				}
			}
		}
	}

	ans := 0

	for i := k; i <= n-k; i++ {
		for x := 0; x < m; x++ {
			if f[i][k][x] {
				for y := 0; y < m; y++ {
					if g[i][k][y] {
						ans = max(ans, x^y)
					}
				}
			}
		}
	}

	return ans
}
```

#### TypeScript

```ts
function maxValue(nums: number[], k: number): number {
    const m = 1 << 7;
    const n = nums.length;

    const f: boolean[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 2 }, () => Array(m).fill(false)),
    );
    f[0][0][0] = true;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j <= k; j++) {
            for (let x = 0; x < m; x++) {
                if (f[i][j][x]) {
                    f[i + 1][j][x] = true;
                    f[i + 1][j + 1][x | nums[i]] = true;
                }
            }
        }
    }

    const g: boolean[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 2 }, () => Array(m).fill(false)),
    );
    g[n][0][0] = true;

    for (let i = n; i > 0; i--) {
        for (let j = 0; j <= k; j++) {
            for (let y = 0; y < m; y++) {
                if (g[i][j][y]) {
                    g[i - 1][j][y] = true;
                    g[i - 1][j + 1][y | nums[i - 1]] = true;
                }
            }
        }
    }

    let ans = 0;

    for (let i = k; i <= n - k; i++) {
        for (let x = 0; x < m; x++) {
            if (f[i][k][x]) {
                for (let y = 0; y < m; y++) {
                    if (g[i][k][y]) {
                        ans = Math.max(ans, x ^ y);
                    }
                }
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
