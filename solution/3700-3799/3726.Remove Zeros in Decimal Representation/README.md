---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3726.Remove%20Zeros%20in%20Decimal%20Representation/README.md
rating: 1175
source: 第 473 场周赛 Q1
tags:
    - 数学
    - 模拟
---

<!-- problem:start -->

# [3726. 移除十进制表示中的所有零](https://leetcode.cn/problems/remove-zeros-in-decimal-representation)

[English Version](/solution/3700-3799/3726.Remove%20Zeros%20in%20Decimal%20Representation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个<strong>正整数</strong><code>n</code>。</p>

<p>返回一个整数，该整数是将 <code>n</code> 的十进制表示中所有的零都移除后得到的结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 1020030</span></p>

<p><strong>输出：</strong> <span class="example-io">123</span></p>

<p><strong>解释：</strong></p>

<p>从 1<strong><u>0</u></strong>2<strong><u>00</u></strong>3<strong><u>0</u></strong> 中移除所有的零后，得到 123。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>1 的十进制表示中没有零，因此结果为 1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们从 $n$ 的最低位开始，逐位检查每个数字。如果该数字不为零，则将其添加到结果中。我们还需要一个变量来跟踪当前的位数，以便正确地构建最终的整数。

具体来说，我们可以使用一个变量 $k$ 来表示当前的位数，然后从最低位开始逐位检查每个数字。如果该数字不为零，则将其乘以 $k$ 并加到结果中，同时将 $k$ 乘以 10，以便在下一位时使用。

最终，我们将得到一个不包含零的整数。

时间复杂度 $O(d)$，其中 $d$ 是整数 $n$ 的位数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeZeros(self, n: int) -> int:
        k = 1
        ans = 0
        while n:
            x = n % 10
            if x:
                ans = k * x + ans
                k *= 10
            n //= 10
        return ans
```

#### Java

```java
class Solution {
    public long removeZeros(long n) {
        long k = 1;
        long ans = 0;
        while (n > 0) {
            long x = n % 10;
            if (x > 0) {
                ans = k * x + ans;
                k *= 10;
            }
            n /= 10;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long removeZeros(long long n) {
        long long k = 1;
        long long ans = 0;
        while (n > 0) {
            long x = n % 10;
            if (x > 0) {
                ans = k * x + ans;
                k *= 10;
            }
            n /= 10;
        }
        return ans;
    }
};
```

#### Go

```go
func removeZeros(n int64) (ans int64) {
	k := int64(1)
	for n > 0 {
		x := n % 10
		if x > 0 {
			ans = k*x + ans
			k *= 10
		}
		n /= 10
	}
	return
}
```

#### TypeScript

```ts
function removeZeros(n: number): number {
    let k = 1;
    let ans = 0;
    while (n) {
        const x = n % 10;
        if (x) {
            ans = k * x + ans;
            k *= 10;
        }
        n = Math.floor(n / 10);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
