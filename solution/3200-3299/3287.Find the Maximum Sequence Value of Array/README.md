---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3287.Find%20the%20Maximum%20Sequence%20Value%20of%20Array/README.md
rating: 2545
source: 第 139 场双周赛 Q3
tags:
    - 位运算
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3287. 求出数组中最大序列值](https://leetcode.cn/problems/find-the-maximum-sequence-value-of-array)

[English Version](/solution/3200-3299/3287.Find%20the%20Maximum%20Sequence%20Value%20of%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p>定义长度为 <code>2 * x</code>&nbsp;的序列 <code>seq</code>&nbsp;的 <strong>值</strong>&nbsp;为：</p>

<ul>
	<li><code>(seq[0] OR seq[1] OR ... OR seq[x - 1]) XOR (seq[x] OR seq[x + 1] OR ... OR seq[2 * x - 1])</code>.</li>
</ul>

<p>请你求出 <code>nums</code>&nbsp;中所有长度为 <code>2 * k</code>&nbsp;的 <span data-keyword="subsequence-array">子序列</span> 的 <strong>最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,6,7], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong></p>

<p>子序列&nbsp;<code>[2, 7]</code>&nbsp;的值最大，为&nbsp;<code>2 XOR 7 = 5</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,2,5,6,7], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>子序列&nbsp;<code>[4, 5, 6, 7]</code>&nbsp;的值最大，为&nbsp;<code>(4 OR 5) XOR (6 OR 7) = 2</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 400</code></li>
	<li><code>1 &lt;= nums[i] &lt; 2<sup>7</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length / 2</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划 + 前后缀分解 + 枚举

我们考虑将序列分成两部分，前 $k$ 个元素和后 $k$ 个元素，分别计算前后缀的所有可能的异或值。

定义 $f[i][j][x]$ 表示前 $i$ 个元素中取 $j$ 个元素，是否存在一个子集的异或值为 $x$，定义 $g[i][j][y]$ 表示从下标 $i$ 开始取 $j$ 个元素，是否存在一个子集的异或值为 $y$。

考虑 $f[i][j][x]$ 的转移方程，对于第 $i$ 个元素（从 $0$ 开始），我们可以选择不取，也可以选择取，因此有：

$$
f[i + 1][j][x] = f[i + 1][j][x] \lor f[i][j][x] \\
f[i + 1][j + 1][x \lor \text{nums}[i]] = f[i + 1][j + 1][x \lor \text{nums}[i]] \lor f[i][j][x]
$$

对于 $g[i][j][y]$ 的转移方程，同样对于第 $i$ 个元素（从 $n - 1$ 开始），我们可以选择不取，也可以选择取，因此有：

$$
g[i - 1][j][y] = g[i - 1][j][y] \lor g[i][j][y] \\
g[i - 1][j + 1][y \lor \text{nums}[i - 1]] = g[i - 1][j + 1][y \lor \text{nums}[i - 1]] \lor g[i][j][y]
$$

最后，我们在 $[k, n - k]$ 的范围内枚举 $i$，对于每一个 $i$，我们枚举 $x$ 和 $y$，其中 $0 \leq x, y < 2^7$，如果 $f[i][k][x]$ 和 $g[i][k][y]$ 均为真，那么我们更新答案 $\text{ans} = \max(\text{ans}, x \oplus y)$。

时间复杂度 $O(n \times m \times k)$，空间复杂度 $O(n \times m \times k)$，其中 $n$ 为数组长度，而 $m = 2^7$。

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
