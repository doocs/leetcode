---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2457.Minimum%20Addition%20to%20Make%20Integer%20Beautiful/README.md
rating: 1680
source: 第 317 场周赛 Q3
tags:
    - 贪心
    - 数学
---

<!-- problem:start -->

# [2457. 美丽整数的最小增量](https://leetcode.cn/problems/minimum-addition-to-make-integer-beautiful)

[English Version](/solution/2400-2499/2457.Minimum%20Addition%20to%20Make%20Integer%20Beautiful/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数 <code>n</code> 和 <code>target</code> 。</p>

<p>如果某个整数每一位上的数字相加小于或等于 <code>target</code> ，则认为这个整数是一个 <strong>美丽整数</strong> 。</p>

<p>找出并返回满足 <code>n + x</code> 是 <strong>美丽整数</strong> 的最小非负整数 <code>x</code> 。生成的输入保证总可以使 <code>n</code> 变成一个美丽整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 16, target = 6
<strong>输出：</strong>4
<strong>解释：</strong>最初，n 是 16 ，且其每一位数字的和是 1 + 6 = 7 。在加 4 之后，n 变为 20 且每一位数字的和变成 2 + 0 = 2 。可以证明无法加上一个小于 4 的非负整数使 n 变成一个美丽整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 467, target = 6
<strong>输出：</strong>33
<strong>解释：</strong>最初，n 是 467 ，且其每一位数字的和是 4 + 6 + 7 = 17 。在加 33 之后，n 变为 500 且每一位数字的和变成 5 + 0 + 0 = 5 。可以证明无法加上一个小于 33 的非负整数使 n 变成一个美丽整数。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 1, target = 1
<strong>输出：</strong>0
<strong>解释：</strong>最初，n 是 1 ，且其每一位数字的和是 1 ，已经小于等于 target 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>12</sup></code></li>
	<li><code>1 &lt;= target &lt;= 150</code></li>
	<li>生成的输入保证总可以使 <code>n</code> 变成一个美丽整数。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们定义函数 $f(x)$ 表示一个整数 $x$ 的每一位数字之和，那么题目求的是 $f(n + x) \leq target$ 的最小非负整数 $x$。

如果 $y = n+x$ 的每一位数字之和大于 $target$，那么我们可以循环通过以下操作，将 $y$ 的每一位数字之和减小到小于等于 $target$：

-   找到 $y$ 的最低位的非零数字，将其减小到 $0$，并将其高一位的数字加 $1$；
-   更新 $x$，继续上述操作，直到 $n+x$ 的每一位数字之和小于等于 $target$。

循环结束，返回 $x$ 即可。

我们可以举个例子，假设 $n=467$, $target=6$，那么 $n$ 的变化过程如下：

$$
\begin{aligned}
& 467 \rightarrow 470 \rightarrow 500 \\
\end{aligned}
$$

时间复杂度 $O(\log^2 n)$，其中 $n$ 给题目给定的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def makeIntegerBeautiful(self, n: int, target: int) -> int:
        def f(x: int) -> int:
            y = 0
            while x:
                y += x % 10
                x //= 10
            return y

        x = 0
        while f(n + x) > target:
            y = n + x
            p = 10
            while y % 10 == 0:
                y //= 10
                p *= 10
            x = (y // 10 + 1) * p - n
        return x
```

#### Java

```java
class Solution {
    public long makeIntegerBeautiful(long n, int target) {
        long x = 0;
        while (f(n + x) > target) {
            long y = n + x;
            long p = 10;
            while (y % 10 == 0) {
                y /= 10;
                p *= 10;
            }
            x = (y / 10 + 1) * p - n;
        }
        return x;
    }

    private int f(long x) {
        int y = 0;
        while (x > 0) {
            y += x % 10;
            x /= 10;
        }
        return y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long makeIntegerBeautiful(long long n, int target) {
        using ll = long long;
        auto f = [](ll x) {
            int y = 0;
            while (x) {
                y += x % 10;
                x /= 10;
            }
            return y;
        };

        ll x = 0;
        while (f(n + x) > target) {
            ll y = n + x;
            ll p = 10;
            while (y % 10 == 0) {
                y /= 10;
                p *= 10;
            }
            x = (y / 10 + 1) * p - n;
        }
        return x;
    }
};
```

#### Go

```go
func makeIntegerBeautiful(n int64, target int) (x int64) {
	f := func(x int64) (y int) {
		for ; x > 0; x /= 10 {
			y += int(x % 10)
		}
		return
	}
	for f(n+x) > target {
		y := n + x
		var p int64 = 10
		for y%10 == 0 {
			y /= 10
			p *= 10
		}
		x = (y/10+1)*p - n
	}
	return
}
```

#### TypeScript

```ts
function makeIntegerBeautiful(n: number, target: number): number {
    const f = (x: number): number => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y += x % 10;
        }
        return y;
    };

    let x = 0;
    while (f(n + x) > target) {
        let y = n + x;
        let p = 10;
        while (y % 10 === 0) {
            y = Math.floor(y / 10);
            p *= 10;
        }
        x = (Math.floor(y / 10) + 1) * p - n;
    }
    return x;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
