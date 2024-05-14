---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2897.Apply%20Operations%20on%20Array%20to%20Maximize%20Sum%20of%20Squares/README.md
rating: 2301
tags:
    - 贪心
    - 位运算
    - 数组
    - 哈希表
---

# [2897. 对数组执行操作使平方和最大](https://leetcode.cn/problems/apply-operations-on-array-to-maximize-sum-of-squares)

[English Version](/solution/2800-2899/2897.Apply%20Operations%20on%20Array%20to%20Maximize%20Sum%20of%20Squares/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你可以对数组执行以下操作 <strong>任意次</strong>&nbsp;：</p>

<ul>
	<li>选择两个互不相同的下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;，<strong>同时</strong>&nbsp;将&nbsp;<code>nums[i]</code>&nbsp;更新为&nbsp;<code>(nums[i] AND nums[j])</code> 且将&nbsp;<code>nums[j]</code>&nbsp;更新为&nbsp;<code>(nums[i] OR nums[j])</code>&nbsp;，<code>OR</code>&nbsp;表示按位 <strong>或</strong>&nbsp;运算，<code>AND</code>&nbsp;表示按位 <strong>与</strong>&nbsp;运算。</li>
</ul>

<p>你需要从最终的数组里选择&nbsp;<code>k</code>&nbsp;个元素，并计算它们的 <strong>平方</strong>&nbsp;之和。</p>

<p>请你返回你可以得到的 <strong>最大</strong>&nbsp;平方和。</p>

<p>由于答案可能会很大，将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,6,5,8], k = 2
<b>输出：</b>261
<b>解释：</b>我们可以对数组执行以下操作：
- 选择 i = 0 和 j = 3 ，同时将 nums[0] 变为 (2 AND 8) = 0 且 nums[3] 变为 (2 OR 8) = 10 ，结果数组为 nums = [0,6,5,10] 。
- 选择 i = 2 和 j = 3 ，同时将 nums[2] 变为 (5 AND 10) = 0 且 nums[3] 变为 (5 OR 10) = 15 ，结果数组为 nums = [0,6,0,15] 。
从最终数组里选择元素 15 和 6 ，平方和为 15<sup>2</sup> + 6<sup>2</sup> = 261 。
261 是可以得到的最大结果。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [4,5,4,7], k = 3
<b>输出：</b>90
<b>解释：</b>不需要执行任何操作。
选择元素 7 ，5 和 4 ，平方和为 7<sup>2</sup> + 5<sup>2</sup> + 4<sup>2</sup> = 90 。
90 是可以得到的最大结果。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：位运算 + 贪心

根据题目描述，对于一个操作，我们可以将 $nums[i]$ 变为 $nums[i] \text{ AND } nums[j]$，将 $nums[j]$ 变为 $nums[i] \text{ OR } nums[j]$。我们不妨按位考虑，两个 $1$ 或两个 $0$ 进行这样的操作，结果都不会改变，如果是 $1$ 和 $0$ 进行这样的操作，结果会变成 $0$ 和 $1$，也即是说，我们可以将 $1$ 转移到 $0$ 上，而 $0$ 不会转移到 $1$ 上。

因此，我们可以用一个数组 $cnt$ 统计每个位置上 $1$ 的个数，然后从中选择 $k$ 个数。由于要使得平方和最大，每次选择的数要尽可能大。这是因为，假设两个数的平方和为 $a^2 + b^2$（其中 $a \gt b$），将两个数平方和变成 $(a + c)^2 + (b - c)^2 = a^2 + b^2 + 2c(a - b) + 2c^2 \gt a^2 + b^2$。因此，为了最大化平方和，我们应该让一个数字尽可能大。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(\log M)$，其中 $M$ 是数组中的最大值。

<!-- tabs:start -->

```python
class Solution:
    def maxSum(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        cnt = [0] * 31
        for x in nums:
            for i in range(31):
                if x >> i & 1:
                    cnt[i] += 1
        ans = 0
        for _ in range(k):
            x = 0
            for i in range(31):
                if cnt[i]:
                    x |= 1 << i
                    cnt[i] -= 1
            ans = (ans + x * x) % mod
        return ans
```

```java
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int mod = (int) 1e9 + 7;
        int[] cnt = new int[31];
        for (int x : nums) {
            for (int i = 0; i < 31; ++i) {
                if ((x >> i & 1) == 1) {
                    ++cnt[i];
                }
            }
        }
        long ans = 0;
        while (k-- > 0) {
            int x = 0;
            for (int i = 0; i < 31; ++i) {
                if (cnt[i] > 0) {
                    x |= 1 << i;
                    --cnt[i];
                }
            }
            ans = (ans + 1L * x * x) % mod;
        }
        return (int) ans;
    }
}
```

```cpp
class Solution {
public:
    int maxSum(vector<int>& nums, int k) {
        int cnt[31]{};
        for (int x : nums) {
            for (int i = 0; i < 31; ++i) {
                if (x >> i & 1) {
                    ++cnt[i];
                }
            }
        }
        long long ans = 0;
        const int mod = 1e9 + 7;
        while (k--) {
            int x = 0;
            for (int i = 0; i < 31; ++i) {
                if (cnt[i]) {
                    x |= 1 << i;
                    --cnt[i];
                }
            }
            ans = (ans + 1LL * x * x) % mod;
        }
        return ans;
    }
};
```

```go
func maxSum(nums []int, k int) (ans int) {
	cnt := [31]int{}
	for _, x := range nums {
		for i := 0; i < 31; i++ {
			if x>>i&1 == 1 {
				cnt[i]++
			}
		}
	}
	const mod int = 1e9 + 7
	for ; k > 0; k-- {
		x := 0
		for i := 0; i < 31; i++ {
			if cnt[i] > 0 {
				x |= 1 << i
				cnt[i]--
			}
		}
		ans = (ans + x*x) % mod
	}
	return
}
```

```ts
function maxSum(nums: number[], k: number): number {
    const cnt: number[] = Array(31).fill(0);
    for (const x of nums) {
        for (let i = 0; i < 31; ++i) {
            if ((x >> i) & 1) {
                ++cnt[i];
            }
        }
    }
    let ans = 0n;
    const mod = 1e9 + 7;
    while (k-- > 0) {
        let x = 0;
        for (let i = 0; i < 31; ++i) {
            if (cnt[i] > 0) {
                x |= 1 << i;
                --cnt[i];
            }
        }
        ans = (ans + BigInt(x) * BigInt(x)) % BigInt(mod);
    }
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- end -->
