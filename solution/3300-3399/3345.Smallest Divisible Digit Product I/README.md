---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3345.Smallest%20Divisible%20Digit%20Product%20I/README.md
rating: 1235
source: 第 143 场双周赛 Q1
tags:
    - 数学
    - 枚举
---

<!-- problem:start -->

# [3345. 最小可整除数位乘积 I](https://leetcode.cn/problems/smallest-divisible-digit-product-i)

[English Version](/solution/3300-3399/3345.Smallest%20Divisible%20Digit%20Product%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数&nbsp;<code>n</code> 和&nbsp;<code>t</code>&nbsp;。请你返回大于等于&nbsp;<code>n</code>&nbsp;的&nbsp;<strong>最小</strong>&nbsp;整数，且该整数的&nbsp;<strong>各数位之积</strong>&nbsp;能被&nbsp;<code>t</code>&nbsp;整除。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 10, t = 2</span></p>

<p><span class="example-io"><b>输出：</b>10</span></p>

<p><strong>解释：</strong></p>

<p>10 的数位乘积为 0 ，可以被 2 整除，所以它是大于等于 10 且满足题目要求的最小整数。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 15, t = 3</span></p>

<p><span class="example-io"><b>输出：</b>16</span></p>

<p><strong>解释：</strong></p>

<p>16 的数位乘积为 6 ，可以被 3 整除，所以它是大于等于 15 且满足题目要求的最小整数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= t &lt;= 10</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们注意到，每 $10$ 个数里一定会出现数位乘积为 $0$ 的整数，因此我们可以直接枚举大于等于 $n$ 的整数，直到找到一个数位乘积能被 $t$ 整除的整数。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestNumber(self, n: int, t: int) -> int:
        for i in count(n):
            p = 1
            x = i
            while x:
                p *= x % 10
                x //= 10
            if p % t == 0:
                return i
```

#### Java

```java
class Solution {
    public int smallestNumber(int n, int t) {
        for (int i = n;; ++i) {
            int p = 1;
            for (int x = i; x > 0; x /= 10) {
                p *= (x % 10);
            }
            if (p % t == 0) {
                return i;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestNumber(int n, int t) {
        for (int i = n;; ++i) {
            int p = 1;
            for (int x = i; x > 0; x /= 10) {
                p *= (x % 10);
            }
            if (p % t == 0) {
                return i;
            }
        }
    }
};
```

#### Go

```go
func smallestNumber(n int, t int) int {
	for i := n; ; i++ {
		p := 1
		for x := i; x > 0; x /= 10 {
			p *= x % 10
		}
		if p%t == 0 {
			return i
		}
	}
}
```

#### TypeScript

```ts
function smallestNumber(n: number, t: number): number {
    for (let i = n; ; ++i) {
        let p = 1;
        for (let x = i; x; x = Math.floor(x / 10)) {
            p *= x % 10;
        }
        if (p % t === 0) {
            return i;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
