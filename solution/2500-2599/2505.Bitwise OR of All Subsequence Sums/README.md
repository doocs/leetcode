---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2505.Bitwise%20OR%20of%20All%20Subsequence%20Sums/README.md
tags:
    - 位运算
    - 脑筋急转弯
    - 数组
    - 数学
---

<!-- problem:start -->

# [2505. 所有子序列和的按位或 🔒](https://leetcode.cn/problems/bitwise-or-of-all-subsequence-sums)

[English Version](/solution/2500-2599/2505.Bitwise%20OR%20of%20All%20Subsequence%20Sums/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> ，返回对数组中所有可能的 <strong>子序列</strong> 之和进行按位 <strong>或</strong> 运算后得到的值。</p>

<p>数组的<strong> 子序列 </strong>是从数组中删除零个或多个元素且不改变剩余元素的顺序得到的序列。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,0,3]
<b>输出：</b>7
<strong>解释：</strong>所有可能的子序列的和包括：0、1、2、3、4、5、6 。
由于 0 OR 1 OR 2 OR 3 OR 4 OR 5 OR 6 = 7，所以返回 7 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [0,0,0]
<b>输出：</b>0
<strong>解释：</strong>0 是唯一可能的子序列的和，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们先用数组 $cnt$ 统计每一位上 $1$ 的个数，然后从低位到高位，如果该位上 $1$ 的个数大于 $0$，则将该位所表示的数加入到答案中。然后判断是否可以进位，是则累加到下一位。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别为数组长度和数组中元素的最大值。

<!-- tabs:start -->

```python
class Solution:
    def subsequenceSumOr(self, nums: List[int]) -> int:
        cnt = [0] * 64
        ans = 0
        for v in nums:
            for i in range(31):
                if (v >> i) & 1:
                    cnt[i] += 1
        for i in range(63):
            if cnt[i]:
                ans |= 1 << i
            cnt[i + 1] += cnt[i] // 2
        return ans
```

```java
class Solution {
    public long subsequenceSumOr(int[] nums) {
        long[] cnt = new long[64];
        long ans = 0;
        for (int v : nums) {
            for (int i = 0; i < 31; ++i) {
                if (((v >> i) & 1) == 1) {
                    ++cnt[i];
                }
            }
        }
        for (int i = 0; i < 63; ++i) {
            if (cnt[i] > 0) {
                ans |= 1l << i;
            }
            cnt[i + 1] += cnt[i] / 2;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long subsequenceSumOr(vector<int>& nums) {
        vector<long long> cnt(64);
        long long ans = 0;
        for (int v : nums) {
            for (int i = 0; i < 31; ++i) {
                if (v >> i & 1) {
                    ++cnt[i];
                }
            }
        }
        for (int i = 0; i < 63; ++i) {
            if (cnt[i]) {
                ans |= 1ll << i;
            }
            cnt[i + 1] += cnt[i] / 2;
        }
        return ans;
    }
};
```

```go
func subsequenceSumOr(nums []int) int64 {
	cnt := make([]int, 64)
	ans := 0
	for _, v := range nums {
		for i := 0; i < 31; i++ {
			if v>>i&1 == 1 {
				cnt[i]++
			}
		}
	}
	for i := 0; i < 63; i++ {
		if cnt[i] > 0 {
			ans |= 1 << i
		}
		cnt[i+1] += cnt[i] / 2
	}
	return int64(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
