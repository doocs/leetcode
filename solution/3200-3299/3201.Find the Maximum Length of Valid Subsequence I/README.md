---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3201.Find%20the%20Maximum%20Length%20of%20Valid%20Subsequence%20I/README.md
rating: 1663
source: 第 404 场周赛 Q2
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3201. 找出有效子序列的最大长度 I](https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-i)

[English Version](/solution/3200-3299/3201.Find%20the%20Maximum%20Length%20of%20Valid%20Subsequence%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p><code>nums</code> 的子序列 <code>sub</code> 的长度为 <code>x</code> ，如果其满足以下条件，则称其为 <strong>有效子序列</strong>：</p>

<ul>
	<li><code>(sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2</code></li>
</ul>

<p>返回 <code>nums</code> 的 <strong>最长的有效子序列</strong> 的长度。</p>

<p>一个&nbsp;<strong>子序列</strong>&nbsp;指的是从原数组中删除一些元素（也可以不删除任何元素），剩余元素保持原来顺序组成的新数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>最长的有效子序列是 <code>[1, 2, 3, 4]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,1,1,2,1,2]</span></p>

<p><strong>输出：</strong> 6</p>

<p><strong>解释：</strong></p>

<p>最长的有效子序列是 <code>[1, 2, 1, 2, 1, 2]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>最长的有效子序列是 <code>[1, 3]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们令 $k = 2$。

根据题目描述，我们可以得知，对于子序列 $a_1, a_2, a_3, \cdots, a_x$，如果满足 $(a_1 + a_2) \bmod k = (a_2 + a_3) \bmod k$。那么 $a_1 \bmod k = a_3 \bmod k$。也即是说，所有奇数项元素对 $k$ 取模的结果相同，所有偶数项元素对 $k$ 取模的结果相同。

我们可以使用动态规划的方法解决这个问题。定义状态 $f[x][y]$ 表示最后一项对 $k$ 取模为 $x$，倒数第二项对 $k$ 取模为 $y$ 的最长有效子序列的长度。初始时 $f[x][y] = 0$。

遍历数组 $nums$，对于每一个数 $x$，我们得到 $x = x \bmod k$。然后我们可以枚举序列连续两个数对 $j$ 取模结果相同，其中 $j \in [0, k)$。那么 $x$ 的前一个数对 $k$ 取模结果为 $y = (j - x + k) \bmod k$。此时 $f[x][y] = f[y][x] + 1$。

答案为所有 $f[x][y]$ 中的最大值。

时间复杂度 $O(n \times k)$，空间复杂度 $O(k^2)$。其中 $n$ 为数组 $\textit{nums}$ 的长度，而 $k=2$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        k = 2
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
    public int maximumLength(int[] nums) {
        int k = 2;
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
    int maximumLength(vector<int>& nums) {
        int k = 2;
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
func maximumLength(nums []int) (ans int) {
	k := 2
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
function maximumLength(nums: number[]): number {
    const k = 2;
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
    pub fn maximum_length(nums: Vec<i32>) -> i32 {
        let mut f = [[0; 2]; 2];
        let mut ans = 0;
        for x in nums {
            let x = (x % 2) as usize;
            for j in 0..2 {
                let y = ((j + 2 - x) % 2) as usize;
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
    public int MaximumLength(int[] nums) {
        int k = 2;
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
