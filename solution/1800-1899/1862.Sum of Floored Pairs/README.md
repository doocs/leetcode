---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1862.Sum%20of%20Floored%20Pairs/README.md
rating: 2170
source: 第 52 场双周赛 Q4
tags:
    - 数组
    - 数学
    - 二分查找
    - 前缀和
---

# [1862. 向下取整数对和](https://leetcode.cn/problems/sum-of-floored-pairs)

[English Version](/solution/1800-1899/1862.Sum%20of%20Floored%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，请你返回所有下标对 <code>0 &lt;= i, j &lt; nums.length</code> 的 <code>floor(nums[i] / nums[j])</code> 结果之和。由于答案可能会很大，请你返回答案对<code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p>函数 <code>floor()</code> 返回输入数字的整数部分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,5,9]
<b>输出：</b>10
<strong>解释：</strong>
floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
floor(5 / 2) = 2
floor(9 / 2) = 4
floor(9 / 5) = 1
我们计算每一个数对商向下取整的结果并求和得到 10 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [7,7,7,7,7,7,7]
<b>输出：</b>49
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：值域前缀和 + 优化枚举

我们先统计数组 $nums$ 中每个元素出现的次数，记录在数组 $cnt$ 中，然后计算数组 $cnt$ 的前缀和，记录在数组 $s$ 中，即 $s[i]$ 表示小于等于 $i$ 的元素的个数。

接下来，我们枚举分母 $y$ 以及商 $d$，利用前缀和数组计算得到分子的个数 $s[\min(mx, d \times y + y - 1)] - s[d \times y - 1]$，其中 $mx$ 表示数组 $nums$ 中的最大值。那么，我们将分子的个数，乘以分母的个数 $cnt[y]$，再乘以商 $d$，就可以得到所有满足条件的分式的值，将这些值累加起来，就可以得到答案。

时间复杂度 $O(M \times \log M)$，空间复杂度 $O(M)$，其中 $M$ 表示数组 $nums$ 中的最大值。

<!-- tabs:start -->

```python
class Solution:
    def sumOfFlooredPairs(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        cnt = Counter(nums)
        mx = max(nums)
        s = [0] * (mx + 1)
        for i in range(1, mx + 1):
            s[i] = s[i - 1] + cnt[i]
        ans = 0
        for y in range(1, mx + 1):
            if cnt[y]:
                d = 1
                while d * y <= mx:
                    ans += cnt[y] * d * (s[min(mx, d * y + y - 1)] - s[d * y - 1])
                    ans %= mod
                    d += 1
        return ans
```

```java
class Solution {
    public int sumOfFlooredPairs(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        int[] s = new int[mx + 1];
        for (int x : nums) {
            ++cnt[x];
        }
        for (int i = 1; i <= mx; ++i) {
            s[i] = s[i - 1] + cnt[i];
        }
        long ans = 0;
        for (int y = 1; y <= mx; ++y) {
            if (cnt[y] > 0) {
                for (int d = 1; d * y <= mx; ++d) {
                    ans += 1L * cnt[y] * d * (s[Math.min(mx, d * y + y - 1)] - s[d * y - 1]);
                    ans %= mod;
                }
            }
        }
        return (int) ans;
    }
}
```

```cpp
class Solution {
public:
    int sumOfFlooredPairs(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int mx = *max_element(nums.begin(), nums.end());
        vector<int> cnt(mx + 1);
        vector<int> s(mx + 1);
        for (int x : nums) {
            ++cnt[x];
        }
        for (int i = 1; i <= mx; ++i) {
            s[i] = s[i - 1] + cnt[i];
        }
        long long ans = 0;
        for (int y = 1; y <= mx; ++y) {
            if (cnt[y]) {
                for (int d = 1; d * y <= mx; ++d) {
                    ans += 1LL * cnt[y] * d * (s[min(mx, d * y + y - 1)] - s[d * y - 1]);
                    ans %= mod;
                }
            }
        }
        return ans;
    }
};
```

```go
func sumOfFlooredPairs(nums []int) (ans int) {
	mx := slices.Max(nums)
	cnt := make([]int, mx+1)
	s := make([]int, mx+1)
	for _, x := range nums {
		cnt[x]++
	}
	for i := 1; i <= mx; i++ {
		s[i] = s[i-1] + cnt[i]
	}
	const mod int = 1e9 + 7
	for y := 1; y <= mx; y++ {
		if cnt[y] > 0 {
			for d := 1; d*y <= mx; d++ {
				ans += d * cnt[y] * (s[min((d+1)*y-1, mx)] - s[d*y-1])
				ans %= mod
			}
		}
	}
	return
}
```

```ts
function sumOfFlooredPairs(nums: number[]): number {
    const mx = Math.max(...nums);
    const cnt: number[] = Array(mx + 1).fill(0);
    const s: number[] = Array(mx + 1).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let i = 1; i <= mx; ++i) {
        s[i] = s[i - 1] + cnt[i];
    }
    let ans = 0;
    const mod = 1e9 + 7;
    for (let y = 1; y <= mx; ++y) {
        if (cnt[y]) {
            for (let d = 1; d * y <= mx; ++d) {
                ans += cnt[y] * d * (s[Math.min((d + 1) * y - 1, mx)] - s[d * y - 1]);
                ans %= mod;
            }
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn sum_of_floored_pairs(nums: Vec<i32>) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut mx = 0;
        for &x in nums.iter() {
            mx = mx.max(x);
        }

        let mut cnt = vec![0; (mx + 1) as usize];
        let mut s = vec![0; (mx + 1) as usize];

        for &x in nums.iter() {
            cnt[x as usize] += 1;
        }

        for i in 1..=mx as usize {
            s[i] = s[i - 1] + cnt[i];
        }

        let mut ans = 0;
        for y in 1..=mx as usize {
            if cnt[y] > 0 {
                let mut d = 1;
                while d * y <= (mx as usize) {
                    ans +=
                        ((cnt[y] as i64) *
                            (d as i64) *
                            (s[std::cmp::min(mx as usize, d * y + y - 1)] - s[d * y - 1])) %
                        (MOD as i64);
                    ans %= MOD as i64;
                    d += 1;
                }
            }
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
