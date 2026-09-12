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

<p>给定一个正整数 <code>num</code>，找出最小的正整数 <code>x</code> 使得 <code>x</code> 的所有数位相乘恰好等于 <code>num</code>。</p>

<p>如果不存在这样的结果或者结果不是 32 位有符号整数，返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 48
<strong>输出：</strong>68
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 15
<strong>输出：</strong>35
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 因式分解

<!-- thinking:start -->

> **思考**
>
> 要把 $num$ 拆成若干个 $2..9$ 的乘积，并使拼成的整数最小。拆法很多，穷举位数不可取。
>
> 位数越少、高位越小则数越小，故从 $9$ 往 $2$ 尽量整除，把因子从低位写入。若最后剩 $num>1$ 或溢出 $32$ 位有符号范围，则无法构成。

<!-- thinking:end -->


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
