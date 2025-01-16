---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3097.Shortest%20Subarray%20With%20OR%20at%20Least%20K%20II/README.md
rating: 1891
source: 第 127 场双周赛 Q3
tags:
    - 位运算
    - 数组
    - 滑动窗口
---

<!-- problem:start -->

# [3097. 或值至少为 K 的最短子数组 II](https://leetcode.cn/problems/shortest-subarray-with-or-at-least-k-ii)

[English Version](/solution/3000-3099/3097.Shortest%20Subarray%20With%20OR%20at%20Least%20K%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>非负</strong>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>如果一个数组中所有元素的按位或运算 <code>OR</code>&nbsp;的值 <strong>至少</strong>&nbsp;为 <code>k</code>&nbsp;，那么我们称这个数组是 <strong>特别的</strong>&nbsp;。</p>

<p>请你返回&nbsp;<code>nums</code>&nbsp;中&nbsp;<strong>最短特别非空</strong>&nbsp;<span data-keyword="subarray-nonempty">子数组</span>的长度，如果特别子数组不存在，那么返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>子数组&nbsp;<code>[3]</code>&nbsp;的按位&nbsp;<code>OR</code> 值为&nbsp;<code>3</code>&nbsp;，所以我们返回 <code>1</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,1,8], k = 10</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>子数组&nbsp;<code>[2,1,8]</code> 的按位&nbsp;<code>OR</code>&nbsp;值为 <code>11</code>&nbsp;，所以我们返回 <code>3</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2], k = 0</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p>子数组&nbsp;<code>[1]</code>&nbsp;的按位&nbsp;<code>OR</code>&nbsp;值为&nbsp;<code>1</code>&nbsp;，所以我们返回&nbsp;<code>1</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup><font size="1">9</font></sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针 + 计数

我们可以发现，如果我们固定子数组的左端点，随着右端点向右移动，子数组的按位或值只会增大，不会减小。因此我们可以使用双指针的方法，维护一个满足条件的子数组。

具体地，我们使用两个指针 $i$ 和 $j$ 分别表示子数组的左右端点，初始时两个指针都位于数组的第一个元素。用一个变量 $s$ 表示子数组的按位或值，初始时 $s$ 的值为 $0$。我们还需要维护一个长度为 $32$ 的数组 $cnt$，表示子数组中每个元素的二进制表示中每一位的出现次数。

在每一步操作中，我们将 $j$ 向右移动一位，更新 $s$ 和 $cnt$。如果 $s$ 的值大于等于 $k$，我们不断更新子数组的最小长度，并将 $i$ 向右移动一位，直到 $s$ 的值小于 $k$。在这个过程中，我们也需要更新 $s$ 和 $cnt$。

最后，我们返回最小长度，如果不存在满足条件的子数组，则返回 $-1$。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(\log M)$，其中 $n$ 和 $M$ 分别是数组的长度和数组中元素的最大值。

相似题目：

-   [3171. 找到按位与最接近 K 的子数组](https://github.com/doocs/leetcode/blob/main/solution/3100-3199/3171.Find%20Subarray%20With%20Bitwise%20AND%20Closest%20to%20K/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSubarrayLength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        cnt = [0] * 32
        ans = n + 1
        s = i = 0
        for j, x in enumerate(nums):
            s |= x
            for h in range(32):
                if x >> h & 1:
                    cnt[h] += 1
            while s >= k and i <= j:
                ans = min(ans, j - i + 1)
                y = nums[i]
                for h in range(32):
                    if y >> h & 1:
                        cnt[h] -= 1
                        if cnt[h] == 0:
                            s ^= 1 << h
                i += 1
        return -1 if ans > n else ans
```

#### Java

```java
class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[32];
        int ans = n + 1;
        for (int i = 0, j = 0, s = 0; j < n; ++j) {
            s |= nums[j];
            for (int h = 0; h < 32; ++h) {
                if ((nums[j] >> h & 1) == 1) {
                    ++cnt[h];
                }
            }
            for (; s >= k && i <= j; ++i) {
                ans = Math.min(ans, j - i + 1);
                for (int h = 0; h < 32; ++h) {
                    if ((nums[i] >> h & 1) == 1) {
                        if (--cnt[h] == 0) {
                            s ^= 1 << h;
                        }
                    }
                }
            }
        }
        return ans > n ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSubarrayLength(vector<int>& nums, int k) {
        int n = nums.size();
        int cnt[32]{};
        int ans = n + 1;
        for (int i = 0, j = 0, s = 0; j < n; ++j) {
            s |= nums[j];
            for (int h = 0; h < 32; ++h) {
                if ((nums[j] >> h & 1) == 1) {
                    ++cnt[h];
                }
            }
            for (; s >= k && i <= j; ++i) {
                ans = min(ans, j - i + 1);
                for (int h = 0; h < 32; ++h) {
                    if ((nums[i] >> h & 1) == 1) {
                        if (--cnt[h] == 0) {
                            s ^= 1 << h;
                        }
                    }
                }
            }
        }
        return ans > n ? -1 : ans;
    }
};
```

#### Go

```go
func minimumSubarrayLength(nums []int, k int) int {
	n := len(nums)
	cnt := [32]int{}
	ans := n + 1
	s, i := 0, 0
	for j, x := range nums {
		s |= x
		for h := 0; h < 32; h++ {
			if x>>h&1 == 1 {
				cnt[h]++
			}
		}
		for ; s >= k && i <= j; i++ {
			ans = min(ans, j-i+1)
			for h := 0; h < 32; h++ {
				if nums[i]>>h&1 == 1 {
					cnt[h]--
					if cnt[h] == 0 {
						s ^= 1 << h
					}
				}
			}
		}
	}
	if ans == n+1 {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minimumSubarrayLength(nums: number[], k: number): number {
    const n = nums.length;
    let ans = n + 1;
    const cnt: number[] = new Array<number>(32).fill(0);
    for (let i = 0, j = 0, s = 0; j < n; ++j) {
        s |= nums[j];
        for (let h = 0; h < 32; ++h) {
            if (((nums[j] >> h) & 1) === 1) {
                ++cnt[h];
            }
        }
        for (; s >= k && i <= j; ++i) {
            ans = Math.min(ans, j - i + 1);
            for (let h = 0; h < 32; ++h) {
                if (((nums[i] >> h) & 1) === 1 && --cnt[h] === 0) {
                    s ^= 1 << h;
                }
            }
        }
    }
    return ans === n + 1 ? -1 : ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_subarray_length(nums: Vec<i32>, k: i32) -> i32 {
        let n = nums.len();
        let mut cnt = vec![0; 32];
        let mut ans = n as i32 + 1;
        let mut s = 0;
        let mut i = 0;

        for (j, &x) in nums.iter().enumerate() {
            s |= x;
            for h in 0..32 {
                if (x >> h) & 1 == 1 {
                    cnt[h] += 1;
                }
            }

            while s >= k && i <= j {
                ans = ans.min((j - i + 1) as i32);
                let y = nums[i];
                for h in 0..32 {
                    if (y >> h) & 1 == 1 {
                        cnt[h] -= 1;
                        if cnt[h] == 0 {
                            s ^= 1 << h;
                        }
                    }
                }
                i += 1;
            }
        }
        if ans > n as i32 { -1 } else { ans }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
