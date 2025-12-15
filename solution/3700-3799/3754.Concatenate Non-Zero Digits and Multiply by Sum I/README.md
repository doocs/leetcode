---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3754.Concatenate%20Non-Zero%20Digits%20and%20Multiply%20by%20Sum%20I/README.md
rating: 1247
source: 第 477 场周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [3754. 连接非零数字并乘以其数字和 I](https://leetcode.cn/problems/concatenate-non-zero-digits-and-multiply-by-sum-i)

[English Version](/solution/3700-3799/3754.Concatenate%20Non-Zero%20Digits%20and%20Multiply%20by%20Sum%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>。</p>

<p>将 <code>n</code> 中所有的&nbsp;<strong>非零数字&nbsp;</strong>按照它们的原始顺序连接起来，形成一个新的整数 <code>x</code>。如果不存在&nbsp;<strong>非零数字&nbsp;</strong>，则 <code>x = 0</code>。</p>

<p><code>sum</code> 为 <code>x</code> 中所有数字的&nbsp;<strong>数字和&nbsp;</strong>。</p>

<p>返回一个整数，表示 <code>x * sum</code> 的值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 10203004</span></p>

<p><strong>输出：</strong> <span class="example-io">12340</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>非零数字是 1、2、3 和 4。因此，<code>x = 1234</code>。</li>
	<li>数字和为 <code>sum = 1 + 2 + 3 + 4 = 10</code>。</li>
	<li>因此，答案是 <code>x * sum = 1234 * 10 = 12340</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 1000</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>非零数字是 1，因此 <code>x = 1</code> 且 <code>sum = 1</code>。</li>
	<li>因此，答案是 <code>x * sum = 1 * 1 = 1</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以通过对数字逐位处理来模拟题目要求的操作。在处理每一位数字时，我们将非零数字连接起来形成新的整数 $x$，同时计算数字和 $s$，最后返回 $x \times s$ 即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumAndMultiply(self, n: int) -> int:
        p = 1
        x = s = 0
        while n:
            v = n % 10
            s += v
            if v:
                x += p * v
                p *= 10
            n //= 10
        return x * s
```

#### Java

```java
class Solution {
    public long sumAndMultiply(int n) {
        int p = 1;
        int x = 0, s = 0;
        for (; n > 0; n /= 10) {
            int v = n % 10;
            s += v;
            if (v != 0) {
                x += p * v;
                p *= 10;
            }
        }
        return 1L * x * s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long sumAndMultiply(int n) {
        int p = 1;
        int x = 0, s = 0;
        for (; n > 0; n /= 10) {
            int v = n % 10;
            s += v;
            if (v != 0) {
                x += p * v;
                p *= 10;
            }
        }
        return 1LL * x * s;
    }
};
```

#### Go

```go
func sumAndMultiply(n int) int64 {
	p := 1
	x := 0
	s := 0
	for n > 0 {
		v := n % 10
		s += v
		if v != 0 {
			x += p * v
			p *= 10
		}
		n /= 10
	}
	return int64(x) * int64(s)
}
```

#### TypeScript

```ts
function sumAndMultiply(n: number): number {
    let p = 1;
    let x = 0;
    let s = 0;

    while (n > 0) {
        const v = n % 10;
        s += v;
        if (v !== 0) {
            x += p * v;
            p *= 10;
        }
        n = Math.floor(n / 10);
    }

    return x * s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
