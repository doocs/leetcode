---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0995.Minimum%20Number%20of%20K%20Consecutive%20Bit%20Flips/README.md
tags:
    - 位运算
    - 队列
    - 数组
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [995. K 连续位的最小翻转次数](https://leetcode.cn/problems/minimum-number-of-k-consecutive-bit-flips)

[English Version](/solution/0900-0999/0995.Minimum%20Number%20of%20K%20Consecutive%20Bit%20Flips/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二进制数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p><strong>k位翻转</strong> 就是从 <code>nums</code> 中选择一个长度为 <code>k</code> 的 <strong>子数组</strong> ，同时把子数组中的每一个 <code>0</code> 都改成 <code>1</code> ，把子数组中的每一个 <code>1</code> 都改成 <code>0</code> 。</p>

<p>返回数组中不存在 <code>0</code> 所需的最小 <strong>k位翻转</strong> 次数。如果不可能，则返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><strong>子数组</strong> 是数组的 <strong>连续</strong> 部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,0], K = 1
<strong>输出：</strong>2
<strong>解释：</strong>先翻转 A[0]，然后翻转 A[2]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,0], K = 2
<strong>输出：</strong>-1
<strong>解释：</strong>无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,0,1,0,1,1,0], K = 3
<strong>输出：</strong>3
<strong>解释：</strong>
翻转 A[0],A[1],A[2]:&nbsp;A变成 [1,1,1,1,0,1,1,0]
翻转 A[4],A[5],A[6]:&nbsp;A变成 [1,1,1,1,1,0,0,0]
翻转 A[5],A[6],A[7]:&nbsp;A变成 [1,1,1,1,1,1,1,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

我们注意到，对于若干个连续的元素进行反转，其结果与对这些元素进行反转的次序无关。因此我们可以贪心地考虑每个位置需要进行反转的次数。

我们不妨从左到右处理数组。

假设当前我们需要处理位置 $i$，而位置 $i$ 左侧的元素已经被处理完毕，如果 $i$ 位置的元素为 $0$，那么我们必须进行反转操作，我们需要将 $[i,..i+k-1]$ 区间内的元素进行反转。这里我们用一个差分数组 $d$ 来维护每个位置的反转次数，那么判断当前位置 $i$ 是否需要反转，只需要看 $s = \sum_{j=0}^{i}d[j]$ 以及 $nums[i]$ 的奇偶性，如果 $s$ 与 $nums[i]$ 奇偶性相同，那么位置 $i$ 的元素仍然为 $0$，需要进行反转。此时我们判断一下 $i+k$ 是否超出了数组的长度，如果超出了数组的长度，那么就无法完成目标，返回 $-1$。否则我们令 $d[i]$ 增加 $1$，同时令 $d[i+k]$ 减少 $1$，然后将答案增加 $1$，并且 $s$ 增加 $1$。

这样当我们处理完数组中的所有元素时，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。这里 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minKBitFlips(self, nums: List[int], k: int) -> int:
        n = len(nums)
        d = [0] * (n + 1)
        ans = s = 0
        for i, x in enumerate(nums):
            s += d[i]
            if x % 2 == s % 2:
                if i + k > n:
                    return -1
                d[i] += 1
                d[i + k] -= 1
                s += 1
                ans += 1
        return ans
```

```java
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] d = new int[n + 1];
        int ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (nums[i] % 2 == s % 2) {
                if (i + k > n) {
                    return -1;
                }
                ++d[i];
                --d[i + k];
                ++s;
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minKBitFlips(vector<int>& nums, int k) {
        int n = nums.size();
        int d[n + 1];
        memset(d, 0, sizeof(d));
        int ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (s % 2 == nums[i] % 2) {
                if (i + k > n) {
                    return -1;
                }
                ++d[i];
                --d[i + k];
                ++s;
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func minKBitFlips(nums []int, k int) int {
	n := len(nums)
	d := make([]int, n+1)
	ans, s := 0, 0
	for i, x := range nums {
		s += d[i]
		if s%2 == x%2 {
			if i+k > n {
				return -1
			}
			d[i]++
			d[i+k]--
			s++
			ans++
		}
	}
	return ans
}
```

```ts
function minKBitFlips(nums: number[], k: number): number {
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    let [ans, s] = [0, 0];
    for (let i = 0; i < n; ++i) {
        s += d[i];
        if (s % 2 === nums[i] % 2) {
            if (i + k > n) {
                return -1;
            }
            d[i]++;
            d[i + k]--;
            s++;
            ans++;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn min_k_bit_flips(nums: Vec<i32>, k: i32) -> i32 {
        let n = nums.len();
        let mut d = vec![0; n + 1];
        let mut ans = 0;
        let mut s = 0;
        for i in 0..n {
            s += d[i];
            if nums[i] % 2 == s % 2 {
                if i + (k as usize) > n {
                    return -1;
                }
                d[i] += 1;
                d[i + (k as usize)] -= 1;
                s += 1;
                ans += 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
