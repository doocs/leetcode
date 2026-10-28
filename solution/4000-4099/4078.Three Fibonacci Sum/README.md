---
comments: true
difficulty: 简单
rating: 1134
source: 第 523 场周赛 Q1
---

<!-- problem:start -->

# [4078. 连续三项斐波那契数之和](https://leetcode.cn/problems/three-fibonacci-sum)

[English Version](/solution/4000-4099/4078.Three%20Fibonacci%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>。</p>

<p><strong>斐波那契数列&nbsp;</strong>以 0 和 1 开始，之后的每个数都是前两个数之和。它的前几项为 <code>0, 1, 1, 2, 3, 5, 8, 13, ...</code>。</p>

<p>如果 <code>n</code> 可以表示为斐波那契数列中<strong>&nbsp;连续三项之和</strong>，则返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 16</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>连续三项 3、5 和 8 的和为 <code>3 + 5 + 8 = 16</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 8</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>虽然 <code>1 + 2 + 5 = 8</code>，但这三项在斐波那契数列中并不连续。不存在连续三项的和等于 8。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>斐波那契数列的前三项之和为 <code>0 + 1 + 1 = 2</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：斐波那契数判定

<!-- thinking:start -->

> **思考**
>
> $n$ 可以到 $10^9$，从 $1$ 累加到 $n$ 不现实。不超过这个上界的斐波那契数只有几十项，生成之后检查每一组连续三项就已经够用。
>
> 连续三项之间还有更紧的关系。由递推， $F_{i+2}=F_i+F_{i+1}$，三项之和正好是最后一项的两倍。
>
> 问题就变成判断 $n$ 是否为偶数，以及 $n/2$ 是否落在斐波那契数里。这些数可以事先放进哈希集合，查询时做一次成员判断。

<!-- thinking:end -->

斐波那契数列定义为 $F_0=0$、 $F_1=1$，以及 $F_{i+2}=F_i+F_{i+1}$。连续三项之和满足

$$
F_i+F_{i+1}+F_{i+2}=2F_{i+2}.
$$

当 $i\ge 0$ 时， $F_{i+2}$ 取遍所有不小于 $1$ 的斐波那契数。预先生成不超过 $10^9$ 的斐波那契数，放入哈希集合 $f$。 $n$ 为偶数且 $n/2\in f$ 时返回 $\text{true}$，否则返回 $\text{false}$。

预处理的时间复杂度与空间复杂度均为 $O(\log M)$，单次查询的时间复杂度为 $O(1)$。其中 $M=10^9$。

<!-- tabs:start -->

#### Python3

```python
a, b = 0, 1
mx = 10**9 + 1
f = set()
while b < mx:
    a, b = b, a + b
    f.add(b)


class Solution:
    def threeFibonacciSum(self, n: int) -> bool:
        return n % 2 == 0 and n // 2 in f
```

#### Java

```java
class Solution {
    static Set<Integer> f = new HashSet<>();

    static {
        int a = 0, b = 1;
        while (b < 1000000001) {
            int c = a + b;
            a = b;
            b = c;
            f.add(b);
        }
    }

    public boolean threeFibonacciSum(int n) {
        return n % 2 == 0 && f.contains(n / 2);
    }
}
```

#### C++

```cpp
class Solution {
    static unordered_set<int> f;

public:
    bool threeFibonacciSum(int n) {
        return n % 2 == 0 && f.count(n / 2);
    }
};

unordered_set<int> Solution::f = [] {
    unordered_set<int> f;
    int a = 0, b = 1;
    while (b < 1000000001) {
        int c = a + b;
        a = b;
        b = c;
        f.insert(b);
    }
    return f;
}();
```

#### Go

```go
var f = func() map[int]bool {
	f := map[int]bool{}
	a, b := 0, 1
	for b < 1000000001 {
		a, b = b, a+b
		f[b] = true
	}
	return f
}()

func threeFibonacciSum(n int) bool {
	return n%2 == 0 && f[n/2]
}
```

#### TypeScript

```ts
const f = new Set<number>();

for (let a = 0, b = 1; b < 1000000001;) {
    [a, b] = [b, a + b];
    f.add(b);
}

function threeFibonacciSum(n: number): boolean {
    return n % 2 === 0 && f.has(n / 2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
