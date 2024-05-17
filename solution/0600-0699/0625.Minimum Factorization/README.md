---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0625.Minimum%20Factorization/README.md
tags:
    - 贪心
    - 数学
---

<!-- problem:start -->

# [625. 最小因式分解 🔒](https://leetcode.cn/problems/minimum-factorization)

[English Version](/solution/0600-0699/0625.Minimum%20Factorization/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数 <code>a</code>，找出最小的正整数 <code>b</code> 使得 <code>b</code> 的所有数位相乘恰好等于 <code>a</code>。</p>

<p>如果不存在这样的结果或者结果不是 32 位有符号整数，返回 0。</p>

<p>&nbsp;</p>

<p><strong>样例 1</strong></p>

<p>输入：</p>

<pre>48 
</pre>

<p>输出：</p>

<pre>68</pre>

<p>&nbsp;</p>

<p><strong>样例 2</strong></p>

<p>输入：</p>

<pre>15
</pre>

<p>输出：</p>

<pre>35</pre>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 因式分解

我们先判断 $num$ 是否小于 $2$，如果是，直接返回 $num$。然后从 $9$ 开始，尽可能多地将数字分解为 $9$，然后分解为 $8$，以此类推，直到分解为 $2$。如果最后剩下的数字不是 $1$，或者结果超过了 $2^{31} - 1$，则返回 $0$。否则，我们返回结果。

> 注意，分解后的数字，应该依次填充到结果的个位、十位、百位、千位……上，因此我们需要维护一个变量 $mul$，表示当前的位数。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为 $num$ 的值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestFactorization(self, num: int) -> int:
        if num < 2:
            return num
        ans, mul = 0, 1
        for i in range(9, 1, -1):
            while num % i == 0:
                num //= i
                ans = mul * i + ans
                mul *= 10
        return ans if num < 2 and ans <= 2**31 - 1 else 0
```

#### Java

```java
class Solution {
    public int smallestFactorization(int num) {
        if (num < 2) {
            return num;
        }
        long ans = 0, mul = 1;
        for (int i = 9; i >= 2; --i) {
            if (num % i == 0) {
                while (num % i == 0) {
                    num /= i;
                    ans = mul * i + ans;
                    mul *= 10;
                }
            }
        }
        return num < 2 && ans <= Integer.MAX_VALUE ? (int) ans : 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestFactorization(int num) {
        if (num < 2) {
            return num;
        }
        long long ans = 0, mul = 1;
        for (int i = 9; i >= 2; --i) {
            if (num % i == 0) {
                while (num % i == 0) {
                    num /= i;
                    ans = mul * i + ans;
                    mul *= 10;
                }
            }
        }
        return num < 2 && ans <= INT_MAX ? ans : 0;
    }
};
```

#### Go

```go
func smallestFactorization(num int) int {
	if num < 2 {
		return num
	}
	ans, mul := 0, 1
	for i := 9; i >= 2; i-- {
		if num%i == 0 {
			for num%i == 0 {
				num /= i
				ans = mul*i + ans
				mul *= 10
			}
		}
	}
	if num < 2 && ans <= math.MaxInt32 {
		return ans
	}
	return 0
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
