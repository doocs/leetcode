---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/README.md
tags:
    - 数学
    - 双指针
    - 二分查找
---

<!-- problem:start -->

# [633. 平方数之和](https://leetcode.cn/problems/sum-of-square-numbers)

[English Version](/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个非负整数&nbsp;<code>c</code>&nbsp;，你要判断是否存在两个整数 <code>a</code> 和 <code>b</code>，使得&nbsp;<code>a<sup>2</sup> + b<sup>2</sup> = c</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>c = 5
<strong>输出：</strong>true
<strong>解释：</strong>1 * 1 + 2 * 2 = 5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>c = 3
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= c &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学 + 双指针

我们可以使用双指针的方法来解决这个问题，定义两个指针 $a$ 和 $b$，分别指向 $0$ 和 $\sqrt{c}$。在每一步中，我们会计算 $s = a^2 + b^2$ 的值，然后比较 $s$ 与 $c$ 的大小。如果 $s = c$，我们就找到了两个整数 $a$ 和 $b$，使得 $a^2 + b^2 = c$。如果 $s < c$，我们将 $a$ 的值增加 $1$，如果 $s > c$，我们将 $b$ 的值减小 $1$。我们不断进行这个过程直到找到答案，或者 $a$ 的值大于 $b$ 的值，返回 `false`。

时间复杂度 $O(\sqrt{c})$，其中 $c$ 是给定的非负整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def judgeSquareSum(self, c: int) -> bool:
        a, b = 0, int(sqrt(c))
        while a <= b:
            s = a**2 + b**2
            if s == c:
                return True
            if s < c:
                a += 1
            else:
                b -= 1
        return False
```

#### Java

```java
class Solution {
    public boolean judgeSquareSum(int c) {
        long a = 0, b = (long) Math.sqrt(c);
        while (a <= b) {
            long s = a * a + b * b;
            if (s == c) {
                return true;
            }
            if (s < c) {
                ++a;
            } else {
                --b;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool judgeSquareSum(int c) {
        long long a = 0, b = sqrt(c);
        while (a <= b) {
            long long s = a * a + b * b;
            if (s == c) {
                return true;
            }
            if (s < c) {
                ++a;
            } else {
                --b;
            }
        }
        return false;
    }
};
```

#### Go

```go
func judgeSquareSum(c int) bool {
	a, b := 0, int(math.Sqrt(float64(c)))
	for a <= b {
		s := a*a + b*b
		if s == c {
			return true
		}
		if s < c {
			a++
		} else {
			b--
		}
	}
	return false
}
```

#### TypeScript

```ts
function judgeSquareSum(c: number): boolean {
    let [a, b] = [0, Math.floor(Math.sqrt(c))];
    while (a <= b) {
        const s = a * a + b * b;
        if (s === c) {
            return true;
        }
        if (s < c) {
            ++a;
        } else {
            --b;
        }
    }
    return false;
}
```

#### Rust

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn judge_square_sum(c: i32) -> bool {
        let mut a: i64 = 0;
        let mut b: i64 = (c as f64).sqrt() as i64;
        while a <= b {
            let s = a * a + b * b;
            match s.cmp(&(c as i64)) {
                Ordering::Equal => {
                    return true;
                }
                Ordering::Less => {
                    a += 1;
                }
                Ordering::Greater => {
                    b -= 1;
                }
            }
        }
        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：数学

这个问题实际上是关于一个数能否表示为两个平方数之和的条件。这个定理可以追溯到费马（Fermat）和欧拉（Euler），它在数论中是一个经典结果。

具体来说，这个定理可以表述为：

**一个正整数 \( n \) 能表示为两个平方数之和的充要条件是：\( n \) 的所有形如 \( 4k + 3 \) 的素数因子的幂次均为偶数。**

这意味着，如果我们将 $n$ 分解成素数因子乘积的形式，即 $n = p_1^{e_1} p_2^{e_2} \cdots p_k^{e_k}$，其中 $p_i$ 是素数且 $e_i$ 是它们对应的幂次，那么 $n$ 可以表示为两个平方数之和，当且仅当所有 $4k + 3$ 形式的素数因子 $p_i$ 的幂次 $e_i$ 都是偶数。

更正式地，假设 $p_i$ 是形如 $4k + 3$ 的素数，则对于每一个这样的 $p_i$，要求 $e_i$ 是偶数。

例如：

-   数字 $13$ 是素数，且 $13 \equiv 1 \pmod{4}$，因此它可以表示为两个平方数之和，即 $13 = 2^2 + 3^2$。
-   数字 $21$ 分解为 $3 \times 7$，其中 $3$ 和 $7$ 都是形如 $4k + 3$ 的素数因子，并且它们的幂次都是 $1$（奇数），因此 $21$ 不能表示为两个平方数之和。

总结起来，这个定理在数论中非常重要，用于判断一个数是否可以表示为两个平方数之和。

时间复杂度 $O(\sqrt{c})$，其中 $c$ 是给定的非负整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def judgeSquareSum(self, c: int) -> bool:
        for i in range(2, int(sqrt(c)) + 1):
            if c % i == 0:
                exp = 0
                while c % i == 0:
                    c //= i
                    exp += 1
                if i % 4 == 3 and exp % 2 != 0:
                    return False
        return c % 4 != 3
```

#### Java

```java
class Solution {
    public boolean judgeSquareSum(int c) {
        int n = (int) Math.sqrt(c);
        for (int i = 2; i <= n; ++i) {
            if (c % i == 0) {
                int exp = 0;
                while (c % i == 0) {
                    c /= i;
                    ++exp;
                }
                if (i % 4 == 3 && exp % 2 != 0) {
                    return false;
                }
            }
        }
        return c % 4 != 3;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool judgeSquareSum(int c) {
        int n = sqrt(c);
        for (int i = 2; i <= n; ++i) {
            if (c % i == 0) {
                int exp = 0;
                while (c % i == 0) {
                    c /= i;
                    ++exp;
                }
                if (i % 4 == 3 && exp % 2 != 0) {
                    return false;
                }
            }
        }
        return c % 4 != 3;
    }
};
```

#### Go

```go
func judgeSquareSum(c int) bool {
	n := int(math.Sqrt(float64(c)))
	for i := 2; i <= n; i++ {
		if c%i == 0 {
			exp := 0
			for c%i == 0 {
				c /= i
				exp++
			}
			if i%4 == 3 && exp%2 != 0 {
				return false
			}
		}
	}
	return c%4 != 3
}
```

#### TypeScript

```ts
function judgeSquareSum(c: number): boolean {
    const n = Math.floor(Math.sqrt(c));
    for (let i = 2; i <= n; ++i) {
        if (c % i === 0) {
            let exp = 0;
            while (c % i === 0) {
                c /= i;
                ++exp;
            }
            if (i % 4 === 3 && exp % 2 !== 0) {
                return false;
            }
        }
    }
    return c % 4 !== 3;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
