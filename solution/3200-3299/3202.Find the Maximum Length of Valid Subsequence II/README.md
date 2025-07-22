---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3202.Find%20the%20Maximum%20Length%20of%20Valid%20Subsequence%20II/README.md
rating: 1973
source: 第 404 场周赛 Q3
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3202. 找出有效子序列的最大长度 II](https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-ii)

[English Version](/solution/3200-3299/3202.Find%20the%20Maximum%20Length%20of%20Valid%20Subsequence%20II/README_EN.md)

## 题目描述

<!-- description:start -->

给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。

<p><code>nums</code>&nbsp;的一个&nbsp;<span data-keyword="subsequence-array">子序列</span> <code>sub</code>&nbsp;的长度为 <code>x</code>&nbsp;，如果其满足以下条件，则称其为 <strong>有效子序列</strong>&nbsp;：</p>

<ul>
	<li><code>(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k</code></li>
</ul>
返回 <code>nums</code>&nbsp;的 <strong>最长</strong><strong>有效子序列</strong>&nbsp;的长度。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4,5], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><b>解释：</b></p>

<p>最长有效子序列是&nbsp;<code>[1, 2, 3, 4, 5]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,4,2,3,1,4], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>最长有效子序列是&nbsp;<code>[1, 4, 1, 4]</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

根据题目描述，我们可以得知，对于子序列 $a_1, a_2, a_3, \cdots, a_x$，如果满足 $(a_1 + a_2) \bmod k = (a_2 + a_3) \bmod k$。那么 $a_1 \bmod k = a_3 \bmod k$。也即是说，所有奇数项元素对 $k$ 取模的结果相同，所有偶数项元素对 $k$ 取模的结果相同。

我们可以使用动态规划的方法解决这个问题。定义状态 $f[x][y]$ 表示最后一项对 $k$ 取模为 $x$，倒数第二项对 $k$ 取模为 $y$ 的最长有效子序列的长度。初始时 $f[x][y] = 0$。

遍历数组 $nums$，对于每一个数 $x$，我们得到 $x = x \bmod k$。然后我们可以枚举序列连续两个数对 $j$ 取模结果相同，其中 $j \in [0, k)$。那么 $x$ 的前一个数对 $k$ 取模结果为 $y = (j - x + k) \bmod k$。此时 $f[x][y] = f[y][x] + 1$。

答案为所有 $f[x][y]$ 中的最大值。

时间复杂度 $O(n \times k)$，空间复杂度 $O(k^2)$。其中 $n$ 为数组 $\textit{nums}$ 的长度，而 $k$ 为给定的正整数。

#### Python3

```python
class Solution:
    def maximumLength(self, nums: List[int], k: int) -> int:
        f = [[0] * k for _ in range(k)]
        ans = 0
        for x in nums:
            x %= k
            for j in range(k):
                y = (j - x + k) % k
                f[x][y] = f[y][x] + 1
                ans = max(ans, f[x][y])
        return ans
```

#### Java

```java
class Solution {
    public int maximumLength(int[] nums, int k) {
        int[][] f = new int[k][k];
        int ans = 0;
        for (int x : nums) {
            x %= k;
            for (int j = 0; j < k; ++j) {
                int y = (j - x + k) % k;
                f[x][y] = f[y][x] + 1;
                ans = Math.max(ans, f[x][y]);
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
    int maximumLength(vector<int>& nums, int k) {
        int f[k][k];
        memset(f, 0, sizeof(f));
        int ans = 0;
        for (int x : nums) {
            x %= k;
            for (int j = 0; j < k; ++j) {
                int y = (j - x + k) % k;
                f[x][y] = f[y][x] + 1;
                ans = max(ans, f[x][y]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumLength(nums []int, k int) (ans int) {
	f := make([][]int, k)
	for i := range f {
		f[i] = make([]int, k)
	}
	for _, x := range nums {
		x %= k
		for j := 0; j < k; j++ {
			y := (j - x + k) % k
			f[x][y] = f[y][x] + 1
			ans = max(ans, f[x][y])
		}
	}
	return
}
```

#### TypeScript

```ts
function maximumLength(nums: number[], k: number): number {
    const f: number[][] = Array.from({ length: k }, () => Array(k).fill(0));
    let ans: number = 0;
    for (let x of nums) {
        x %= k;
        for (let j = 0; j < k; ++j) {
            const y = (j - x + k) % k;
            f[x][y] = f[y][x] + 1;
            ans = Math.max(ans, f[x][y]);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_length(nums: Vec<i32>, k: i32) -> i32 {
        let k = k as usize;
        let mut f = vec![vec![0; k]; k];
        let mut ans = 0;
        for x in nums {
            let x = (x % k as i32) as usize;
            for j in 0..k {
                let y = (j + k - x) % k;
                f[x][y] = f[y][x] + 1;
                ans = ans.max(f[x][y]);
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int MaximumLength(int[] nums, int k) {
        int[,] f = new int[k, k];
        int ans = 0;
        foreach (int num in nums) {
            int x = num % k;
            for (int j = 0; j < k; ++j) {
                int y = (j - x + k) % k;
                f[x, y] = f[y, x] + 1;
                ans = Math.Max(ans, f[x, y]);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
