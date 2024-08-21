---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3247.Number%20of%20Subsequences%20with%20Odd%20Sum/README.md
tags:
    - 数组
    - 数学
    - 动态规划
    - 组合数学
---

<!-- problem:start -->

# [3247. 奇数和子序列的数量 🔒](https://leetcode.cn/problems/number-of-subsequences-with-odd-sum)

[English Version](/solution/3200-3299/3247.Number%20of%20Subsequences%20with%20Odd%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个数组&nbsp;<code>nums</code>，返回元素和为奇数的 <span data-keyword="subsequence-array">子序列</span> 的数量。</p>

<p>由于答案可能很大，返回答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取模</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">nums = [1,1,1]</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>奇数和子序列为：<code>[<u><strong>1</strong></u>, 1, 1]</code>, <code>[1, <u><strong>1</strong></u>, 1],</code> <code>[1, 1, <u><strong>1</strong></u>]</code>, <code>[<u><strong>1, 1, 1</strong></u>]</code>.</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,2]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>奇数和子序列为：<code>[<u><strong>1</strong></u>, 2, 2]</code>, <code>[<u><strong>1, 2</strong></u>, 2],</code> <code>[<u><strong>1</strong></u>, 2, <b><u>2</u></b>]</code>, <code>[<u><strong>1, 2, 2</strong></u>]</code>.</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[0]$ 表示目前为止的子序列中，和为偶数的子序列个数，而 $f[1]$ 表示目前为止的子序列中，和为奇数的子序列个数。初始时 $f[0] = 0$, $f[1] = 0$。

遍历数组 $\textit{nums}$，对于每个数 $x$：

如果 $x$ 为奇数，那么 $f[0]$ 和 $f[1]$ 的更新方式为：

$$
\begin{aligned}
f[0] & = (f[0] + f[1]) \bmod 10^9 + 7, \\
f[1] & = (f[0] + f[1] + 1) \bmod 10^9 + 7.
\end{aligned}
$$

即，当前的和为偶数的子序列个数等于之前的和为偶数的子序列个数，加上之前的和为奇数的子序列拼上当前数 $x$ 的子序列个数；当前的和为奇数的子序列个数等于之前的和为偶数的子序列拼上当前数 $x$ 的子序列个数，加上之前的和为奇数的子序列个数，再加上一个只包含当前数 $x$ 的子序列。

如果 $x$ 为偶数，那么 $f[0]$ 和 $f[1]$ 的更新方式为：

$$
\begin{aligned}
f[0] & = (f[0] + f[0] + 1) \bmod 10^9 + 7, \\
f[1] & = (f[1] + f[1]) \bmod 10^9 + 7.
\end{aligned}
$$

即，当前的和为偶数的子序列个数等于之前的和为偶数的子序列个数，加上之前的和为偶数的子序列拼上当前数 $x$ 的子序列个数，再加上一个只包含当前数 $x$ 的子序列；当前的和为奇数的子序列个数等于之前的和为奇数的子序列拼上当前数 $x$ 的子序列个数，加上之前的和为奇数的子序列个数。

最终，返回 $f[1]$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsequenceCount(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        f = [0] * 2
        for x in nums:
            if x % 2:
                f[0], f[1] = (f[0] + f[1]) % mod, (f[0] + f[1] + 1) % mod
            else:
                f[0], f[1] = (f[0] + f[0] + 1) % mod, (f[1] + f[1]) % mod
        return f[1]
```

#### Java

```java
class Solution {
    public int subsequenceCount(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[2];
        for (int x : nums) {
            int[] g = new int[2];
            if (x % 2 == 1) {
                g[0] = (f[0] + f[1]) % mod;
                g[1] = (f[0] + f[1] + 1) % mod;
            } else {
                g[0] = (f[0] + f[0] + 1) % mod;
                g[1] = (f[1] + f[1]) % mod;
            }
            f = g;
        }
        return f[1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subsequenceCount(vector<int>& nums) {
        const int mod = 1e9 + 7;
        vector<int> f(2);
        for (int x : nums) {
            vector<int> g(2);
            if (x % 2 == 1) {
                g[0] = (f[0] + f[1]) % mod;
                g[1] = (f[0] + f[1] + 1) % mod;
            } else {
                g[0] = (f[0] + f[0] + 1) % mod;
                g[1] = (f[1] + f[1]) % mod;
            }
            f = g;
        }
        return f[1];
    }
};
```

#### Go

```go
func subsequenceCount(nums []int) int {
	mod := int(1e9 + 7)
	f := [2]int{}
	for _, x := range nums {
		g := [2]int{}
		if x%2 == 1 {
			g[0] = (f[0] + f[1]) % mod
			g[1] = (f[0] + f[1] + 1) % mod
		} else {
			g[0] = (f[0] + f[0] + 1) % mod
			g[1] = (f[1] + f[1]) % mod
		}
		f = g
	}
	return f[1]
}
```

#### TypeScript

```ts
function subsequenceCount(nums: number[]): number {
    const mod = 1e9 + 7;
    let f = [0, 0];
    for (const x of nums) {
        const g = [0, 0];
        if (x % 2 === 1) {
            g[0] = (f[0] + f[1]) % mod;
            g[1] = (f[0] + f[1] + 1) % mod;
        } else {
            g[0] = (f[0] + f[0] + 1) % mod;
            g[1] = (f[1] + f[1]) % mod;
        }
        f = g;
    }
    return f[1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
