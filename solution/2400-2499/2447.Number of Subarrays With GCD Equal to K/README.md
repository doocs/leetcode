# [2447. 最大公因数等于 K 的子数组数目](https://leetcode.cn/problems/number-of-subarrays-with-gcd-equal-to-k)

[English Version](/solution/2400-2499/2447.Number%20of%20Subarrays%20With%20GCD%20Equal%20to%20K/README_EN.md)

<!-- tags:数组,数学,数论 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code> ，请你统计并返回 <code>nums</code>&nbsp;的子数组中元素的最大公因数等于 <code>k</code>&nbsp;的子数组数目。</p>

<p><strong>子数组</strong> 是数组中一个连续的非空序列。</p>

<p><strong>数组的最大公因数</strong>&nbsp;是能整除数组中所有元素的最大整数。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre><b>输入：</b>nums = [9,3,1,2,6,3], k = 3
<b>输出：</b>4
<b>解释：</b>nums 的子数组中，以 3 作为最大公因数的子数组如下：
- [9,<strong><em>3</em></strong>,1,2,6,3]
- [9,3,1,2,6,<em><strong>3</strong></em>]
- [<strong><em>9,3</em></strong>,1,2,6,3]
- [9,3,1,2,<em><strong>6,3</strong></em>]
</pre>

<p><b>示例 2：</b></p>

<pre><b>输入：</b>nums = [4], k = 7
<b>输出：</b>0
<b>解释：</b>不存在以 7 作为最大公因数的子数组。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：直接枚举

我们可以枚举 $nums[i]$ 作为子数组的左端点，然后枚举 $nums[j]$ 作为子数组的右端点，其中 $i \le j$。在枚举右端点的过程中，我们可以用一个变量 $g$ 来维护当前子数组的最大公因数，每次枚举到一个新的右端点时，我们更新最大公因数 $g = \gcd(g, nums[j])$。如果 $g=k$，那么当前子数组的最大公因数等于 $k$，我们就将答案增加 $1$。

枚举结束后，返回答案即可。

时间复杂度 $O(n \times (n + \log M))$，其中 $n$ 和 $M$ 分别是数组 $nums$ 的长度和数组 $nums$ 中的最大值。

<!-- tabs:start -->

```python
class Solution:
    def subarrayGCD(self, nums: List[int], k: int) -> int:
        ans = 0
        for i in range(len(nums)):
            g = 0
            for x in nums[i:]:
                g = gcd(g, x)
                ans += g == k
        return ans
```

```java
class Solution {
    public int subarrayGCD(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int g = 0;
            for (int j = i; j < n; ++j) {
                g = gcd(g, nums[j]);
                if (g == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

```cpp
class Solution {
public:
    int subarrayGCD(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int g = 0;
            for (int j = i; j < n; ++j) {
                g = gcd(g, nums[j]);
                ans += g == k;
            }
        }
        return ans;
    }
};
```

```go
func subarrayGCD(nums []int, k int) (ans int) {
	for i := range nums {
		g := 0
		for _, x := range nums[i:] {
			g = gcd(g, x)
			if g == k {
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
```

```ts
function subarrayGCD(nums: number[], k: number): number {
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let g = 0;
        for (let j = i; j < n; ++j) {
            g = gcd(g, nums[j]);
            if (g === k) {
                ++ans;
            }
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- end -->
