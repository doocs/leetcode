---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0829.Consecutive%20Numbers%20Sum/README.md
tags:
    - 数学
    - 枚举
---

<!-- problem:start -->

# [829. 连续整数求和](https://leetcode.cn/problems/consecutive-numbers-sum)

[English Version](/solution/0800-0899/0829.Consecutive%20Numbers%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数 <code>n</code>，返回 <em>连续正整数满足所有数字之和为 <code>n</code>&nbsp;的组数</em> 。&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示</strong><strong>例 1:</strong></p>

<pre>
<strong>输入: </strong>n = 5
<strong>输出: </strong>2
<strong>解释: </strong>5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>n = 9
<strong>输出: </strong>3
<strong>解释: </strong>9 = 4 + 5 = 2 + 3 + 4</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>n = 15
<strong>输出: </strong>4
<strong>解释: </strong>15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学推导

连续正整数构成一个公差 $d = 1$ 的等差数列。我们假设等差数列的第一项为 $a$，项数为 $k$，那么 $n = (a + a + k - 1) \times k / 2$，即 $n \times 2 = (a \times 2 + k - 1) \times k$。这里我们可以得出 $k$ 一定能整除 $n \times 2$，并且 $(n \times 2) / k - k + 1$ 一定是偶数。

由于 $a \geq 1$，所以 $n \times 2 = (a \times 2 + k - 1) \times k \geq k \times (k + 1)$。

综上，我们可以得出：

1. $k$ 一定能整除 $n \times 2$；
2. $k \times (k + 1) \leq n \times 2$；
3. $(n \times 2) / k - k + 1$ 一定是偶数。

我们从 $k = 1$ 开始枚举，当 $k \times (k + 1) > n \times 2$ 时，我们可以结束枚举。在枚举的过程中，我们判断 $k$ 是否能整除 $n \times 2$，并且 $(n \times 2) / k - k + 1$ 是否是偶数，如果是则满足条件，答案加一。

枚举结束后，返回答案即可。

时间复杂度 $O(\sqrt{n})$，其中 $n$ 为给定的正整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def consecutiveNumbersSum(self, n: int) -> int:
        n <<= 1
        ans, k = 0, 1
        while k * (k + 1) <= n:
            if n % k == 0 and (n // k + 1 - k) % 2 == 0:
                ans += 1
            k += 1
        return ans
```

#### Java

```java
class Solution {

    public int consecutiveNumbersSum(int n) {
        n <<= 1;
        int ans = 0;
        for (int k = 1; k * (k + 1) <= n; ++k) {
            if (n % k == 0 && (n / k + 1 - k) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int consecutiveNumbersSum(int n) {
        n <<= 1;
        int ans = 0;
        for (int k = 1; k * (k + 1) <= n; ++k) {
            if (n % k == 0 && (n / k + 1 - k) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func consecutiveNumbersSum(n int) int {
	n <<= 1
	ans := 0
	for k := 1; k*(k+1) <= n; k++ {
		if n%k == 0 && (n/k+1-k)%2 == 0 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function consecutiveNumbersSum(n: number): number {
    let ans = 0;
    n <<= 1;
    for (let k = 1; k * (k + 1) <= n; ++k) {
        if (n % k === 0 && (Math.floor(n / k) + 1 - k) % 2 === 0) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
