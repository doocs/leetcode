---
comments: true
difficulty: 中等
rating: 1559
source: 第 319 场周赛 Q2
tags:
    - 数组
    - 数学
    - 数论
---

<!-- problem:start -->

# [2470. 最小公倍数等于 K 的子数组数目](https://leetcode.cn/problems/number-of-subarrays-with-lcm-equal-to-k)

[English Version](/solution/2400-2499/2470.Number%20of%20Subarrays%20With%20LCM%20Equal%20to%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你统计并返回 <code>nums</code> 的 <strong>子数组</strong> 中满足 <em>元素最小公倍数为 <code>k</code> </em>的子数组数目。</p>

<p><strong>子数组</strong> 是数组中一个连续非空的元素序列。</p>

<p><strong>数组的最小公倍数</strong> 是可被所有数组元素整除的最小正整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre><strong>输入：</strong>nums = [3,6,2,7,1], k = 6
<strong>输出：</strong>4
<strong>解释：</strong>以 6 为最小公倍数的子数组是：
- [<em><strong>3</strong></em>,<em><strong>6</strong></em>,2,7,1]
- [<em><strong>3</strong></em>,<em><strong>6</strong></em>,<em><strong>2</strong></em>,7,1]
- [3,<em><strong>6</strong></em>,2,7,1]
- [3,<em><strong>6</strong></em>,<em><strong>2</strong></em>,7,1]
</pre>

<p><strong>示例 2 ：</strong></p>

<pre><strong>输入：</strong>nums = [3], k = 2
<strong>输出：</strong>0
<strong>解释：</strong>不存在以 2 为最小公倍数的子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

<!-- thinking:start -->

> **思考**
>
> $n \le 1000$，可以从每个左端点向右扫描。某个元素不能整除 $k$ 时，包含它的连续段的最小公倍数也不可能是 $k$，因此立刻停止。停止之前，运行中的最小公倍数始终整除 $k$，从而不超过 $k$。若先把两数相乘再除以最大公约数，$a \times b$ 会在真正的最小公倍数仍不超过 $2^{31}-1$ 时溢出，把本不是 $k$ 的段误判为答案。

<!-- thinking:end -->

枚举每个下标作为左端点并向右延伸。遇到不能整除 $k$ 的元素就停止。延伸过程中运行的最小公倍数始终整除 $k$，等于 $k$ 时答案加一。

时间复杂度 $O(n^2)$。

<!-- tabs:start -->

#### Python3

```python
from math import lcm


class Solution:
    def subarrayLCM(self, nums: List[int], k: int) -> int:
        ans = 0
        for i in range(len(nums)):
            a = 1
            for b in nums[i:]:
                if k % b:
                    break
                a = lcm(a, b)
                ans += a == k
        return ans
```

#### Java

```java
class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int a = 1;
            for (int j = i; j < nums.length; ++j) {
                if (k % nums[j] != 0) {
                    break;
                }
                a = lcm(a, nums[j]);
                if (a == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subarrayLCM(vector<int>& nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            int a = 1;
            for (int j = i; j < nums.size(); ++j) {
                if (k % nums[j] != 0) {
                    break;
                }
                a = lcm(a, nums[j]);
                ans += a == k;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func subarrayLCM(nums []int, k int) (ans int) {
	for i := range nums {
		a := 1
		for _, b := range nums[i:] {
			if k%b != 0 {
				break
			}
			a = lcm(a, b)
			if a == k {
				ans++
			}
		}
	}
	return
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func lcm(a, b int) int {
	return a / gcd(a, b) * b
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
