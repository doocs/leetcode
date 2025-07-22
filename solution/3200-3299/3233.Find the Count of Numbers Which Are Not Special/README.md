---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3233.Find%20the%20Count%20of%20Numbers%20Which%20Are%20Not%20Special/README.md
rating: 1509
source: 第 408 场周赛 Q2
tags:
    - 数组
    - 数学
    - 数论
---

<!-- problem:start -->

# [3233. 统计不是特殊数字的数字数量](https://leetcode.cn/problems/find-the-count-of-numbers-which-are-not-special)

[English Version](/solution/3200-3299/3233.Find%20the%20Count%20of%20Numbers%20Which%20Are%20Not%20Special/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个<strong> 正整数 </strong><code>l</code> 和 <code>r</code>。对于任何数字 <code>x</code>，<code>x</code> 的所有正因数（除了 <code>x</code> 本身）被称为 <code>x</code> 的 <strong>真因数</strong>。</p>

<p><span class="text-only" data-eleid="13" style="white-space: pre;">如果一个数字恰好仅有两个</span> <strong>真因数</strong>，则称该数字为 <strong>特殊数字</strong>。例如：</p>

<ul>
	<li>数字 4 是<strong> 特殊数字</strong>，因为它的真因数为 1 和 2。</li>
	<li>数字 6 不是 <strong>特殊数字</strong>，因为它的真因数为 1、2 和 3。</li>
</ul>

<p>返回区间 <code>[l, r]</code> 内<strong> 不是 特殊数字 </strong>的数字数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">l = 5, r = 7</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>区间 <code>[5, 7]</code> 内不存在特殊数字。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">l = 4, r = 16</span></p>

<p><strong>输出：</strong> <span class="reset-io">11</span></p>

<p><strong>解释：</strong></p>

<p>区间 <code>[4, 16]</code> 内的特殊数字为 4 和 9。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= l &lt;= r &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

根据题目描述，我们可以发现，只有质数的平方才是特殊数字。因此，我们可以先预处理出小于等于 $\sqrt{10^9}$ 的所有质数，然后遍历区间 $[\lceil\sqrt{l}\rceil, \lfloor\sqrt{r}\rfloor]$，统计出区间内的质数个数 $\textit{cnt}$，最后返回 $r - l + 1 - \textit{cnt}$ 即可。

时间复杂度 $O(\sqrt{m})$，空间复杂度 $O(\sqrt{m})$。其中 $m = 10^9$。

<!-- tabs:start -->

#### Python3

```python
m = 31623
primes = [True] * (m + 1)
primes[0] = primes[1] = False
for i in range(2, m + 1):
    if primes[i]:
        for j in range(i + i, m + 1, i):
            primes[j] = False


class Solution:
    def nonSpecialCount(self, l: int, r: int) -> int:
        lo = ceil(sqrt(l))
        hi = floor(sqrt(r))
        cnt = sum(primes[i] for i in range(lo, hi + 1))
        return r - l + 1 - cnt
```

#### Java

```java
class Solution {
    static int m = 31623;
    static boolean[] primes = new boolean[m + 1];

    static {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 2; i <= m; i++) {
            if (primes[i]) {
                for (int j = i + i; j <= m; j += i) {
                    primes[j] = false;
                }
            }
        }
    }

    public int nonSpecialCount(int l, int r) {
        int lo = (int) Math.ceil(Math.sqrt(l));
        int hi = (int) Math.floor(Math.sqrt(r));
        int cnt = 0;
        for (int i = lo; i <= hi; i++) {
            if (primes[i]) {
                cnt++;
            }
        }
        return r - l + 1 - cnt;
    }
}
```

#### C++

```cpp
const int m = 31623;
bool primes[m + 1];

auto init = [] {
    memset(primes, true, sizeof(primes));
    primes[0] = primes[1] = false;
    for (int i = 2; i <= m; ++i) {
        if (primes[i]) {
            for (int j = i * 2; j <= m; j += i) {
                primes[j] = false;
            }
        }
    }
    return 0;
}();

class Solution {
public:
    int nonSpecialCount(int l, int r) {
        int lo = ceil(sqrt(l));
        int hi = floor(sqrt(r));
        int cnt = 0;
        for (int i = lo; i <= hi; ++i) {
            if (primes[i]) {
                ++cnt;
            }
        }
        return r - l + 1 - cnt;
    }
};
```

#### Go

```go
const m = 31623

var primes [m + 1]bool

func init() {
	for i := range primes {
		primes[i] = true
	}
	primes[0] = false
	primes[1] = false
	for i := 2; i <= m; i++ {
		if primes[i] {
			for j := i * 2; j <= m; j += i {
				primes[j] = false
			}
		}
	}
}

func nonSpecialCount(l int, r int) int {
	lo := int(math.Ceil(math.Sqrt(float64(l))))
	hi := int(math.Floor(math.Sqrt(float64(r))))
	cnt := 0
	for i := lo; i <= hi; i++ {
		if primes[i] {
			cnt++
		}
	}
	return r - l + 1 - cnt
}
```

#### TypeScript

```ts
const m = 31623;
const primes: boolean[] = Array(m + 1).fill(true);

(() => {
    primes[0] = primes[1] = false;
    for (let i = 2; i <= m; ++i) {
        if (primes[i]) {
            for (let j = i * 2; j <= m; j += i) {
                primes[j] = false;
            }
        }
    }
})();

function nonSpecialCount(l: number, r: number): number {
    const lo = Math.ceil(Math.sqrt(l));
    const hi = Math.floor(Math.sqrt(r));
    let cnt = 0;
    for (let i = lo; i <= hi; ++i) {
        if (primes[i]) {
            ++cnt;
        }
    }
    return r - l + 1 - cnt;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
