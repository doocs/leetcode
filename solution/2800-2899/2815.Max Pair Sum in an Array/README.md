---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2815.Max%20Pair%20Sum%20in%20an%20Array/README.md
rating: 1295
source: 第 358 场周赛 Q1
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [2815. 数组中的最大数对和](https://leetcode.cn/problems/max-pair-sum-in-an-array)

[English Version](/solution/2800-2899/2815.Max%20Pair%20Sum%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。请你从 <code>nums</code> 中找出和 <strong>最大</strong> 的一对数，且这两个数数位上最大的数字相等。</p>

<p>返回最大和，如果不存在满足题意的数字对，返回 <code>-1</code><em> 。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [51,71,17,24,42]
<strong>输出：</strong>88
<strong>解释：</strong>
i = 1 和 j = 2 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 71 + 17 = 88 。 
i = 3 和 j = 4 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 24 + 42 = 66 。
可以证明不存在其他数对满足数位上最大的数字相等，所以答案是 88 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>-1
<strong>解释：</strong>不存在数对满足数位上最大的数字相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们先初始化答案变量 $ans = -1$。接下来，直接枚举所有的数对 $(nums[i], nums[j])$，其中 $i \lt j$，并计算它们的和 $v = nums[i] + nums[j]$。如果 $v$ 比 $ans$ 更大，并且 $nums[i]$ 和 $nums[j]$ 的最大数字相同，那么我们就用 $v$ 更新 $ans$。

时间复杂度 $O(n^2 \times \log M)$，其中 $n$ 是数组的长度，而 $M$ 是数组中的最大值。

<!-- tabs:start -->

```python
class Solution:
    def maxSum(self, nums: List[int]) -> int:
        ans = -1
        for i, x in enumerate(nums):
            for y in nums[i + 1 :]:
                v = x + y
                if ans < v and max(str(x)) == max(str(y)):
                    ans = v
        return ans
```

```java
class Solution {
    public int maxSum(int[] nums) {
        int ans = -1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int v = nums[i] + nums[j];
                if (ans < v && f(nums[i]) == f(nums[j])) {
                    ans = v;
                }
            }
        }
        return ans;
    }

    private int f(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = Math.max(y, x % 10);
        }
        return y;
    }
}
```

```cpp
class Solution {
public:
    int maxSum(vector<int>& nums) {
        int ans = -1;
        int n = nums.size();
        auto f = [](int x) {
            int y = 0;
            for (; x; x /= 10) {
                y = max(y, x % 10);
            }
            return y;
        };
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int v = nums[i] + nums[j];
                if (ans < v && f(nums[i]) == f(nums[j])) {
                    ans = v;
                }
            }
        }
        return ans;
    }
};
```

```go
func maxSum(nums []int) int {
	ans := -1
	f := func(x int) int {
		y := 0
		for ; x > 0; x /= 10 {
			y = max(y, x%10)
		}
		return y
	}
	for i, x := range nums {
		for _, y := range nums[i+1:] {
			if v := x + y; ans < v && f(x) == f(y) {
				ans = v
			}
		}
	}
	return ans
}
```

```ts
function maxSum(nums: number[]): number {
    const n = nums.length;
    let ans = -1;
    const f = (x: number): number => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = Math.max(y, x % 10);
        }
        return y;
    };
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            const v = nums[i] + nums[j];
            if (ans < v && f(nums[i]) === f(nums[j])) {
                ans = v;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
