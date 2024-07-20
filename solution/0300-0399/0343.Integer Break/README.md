---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0343.Integer%20Break/README.md
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [343. 整数拆分](https://leetcode.cn/problems/integer-break)

[English Version](/solution/0300-0399/0343.Integer%20Break/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数&nbsp;<code>n</code>&nbsp;，将其拆分为 <code>k</code> 个 <strong>正整数</strong> 的和（&nbsp;<code>k &gt;= 2</code>&nbsp;），并使这些整数的乘积最大化。</p>

<p>返回 <em>你可以获得的最大乘积</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>n = 2
<strong>输出: </strong>1
<strong>解释: </strong>2 = 1 + 1, 1 × 1 = 1。</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入: </strong>n = 10
<strong>输出: </strong>36
<strong>解释: </strong>10 = 3 + 3 + 4, 3 ×&nbsp;3 ×&nbsp;4 = 36。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 58</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i]$ 表示正整数 $i$ 拆分后能获得的最大乘积，初始时 $f[1] = 1$。答案即为 $f[n]$。

考虑 $i$ 最后拆分出的数字 $j$，其中 $j \in [1, i)$。对于 $i$ 拆分出的数字 $j$，有两种情况：

1. 将 $i$ 拆分成 $i - j$ 和 $j$ 的和，不继续拆分，此时乘积为 $(i - j) \times j$；
2. 将 $i$ 拆分成 $i - j$ 和 $j$ 的和，继续拆分，此时乘积为 $f[i - j] \times j$。

因此，我们可以得到状态转移方程：

$$
f[i] = \max(f[i], f[i - j] \times j, (i - j) \times j) \quad (j \in [0, i))
$$

最后返回 $f[n]$ 即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为给定的正整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def integerBreak(self, n: int) -> int:
        f = [1] * (n + 1)
        for i in range(2, n + 1):
            for j in range(1, i):
                f[i] = max(f[i], f[i - j] * j, (i - j) * j)
        return f[n]
```

#### Java

```java
class Solution {
    public int integerBreak(int n) {
        int[] f = new int[n + 1];
        f[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                f[i] = Math.max(Math.max(f[i], f[i - j] * j), (i - j) * j);
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int integerBreak(int n) {
        vector<int> f(n + 1);
        f[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                f[i] = max({f[i], f[i - j] * j, (i - j) * j});
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func integerBreak(n int) int {
	f := make([]int, n+1)
	f[1] = 1
	for i := 2; i <= n; i++ {
		for j := 1; j < i; j++ {
			f[i] = max(max(f[i], f[i-j]*j), (i-j)*j)
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function integerBreak(n: number): number {
    const f = Array(n + 1).fill(1);
    for (let i = 3; i <= n; i++) {
        for (let j = 1; j < i; j++) {
            f[i] = Math.max(f[i], j * (i - j), j * f[i - j]);
        }
    }
    return f[n];
}
```

#### Rust

```rust
impl Solution {
    pub fn integer_break(n: i32) -> i32 {
        let n = n as usize;
        let mut f = vec![0; n + 1];
        f[1] = 1;
        for i in 2..=n {
            for j in 1..i {
                f[i] = f[i].max(f[i - j] * j).max((i - j) * j);
            }
        }
        f[n] as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var integerBreak = function (n) {
    const f = Array(n + 1).fill(1);
    for (let i = 2; i <= n; ++i) {
        for (let j = 1; j < i; ++j) {
            f[i] = Math.max(f[i], f[i - j] * j, (i - j) * j);
        }
    }
    return f[n];
};
```

#### C#

```cs
public class Solution {
    public int IntegerBreak(int n) {
        int[] f = new int[n + 1];
        f[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                f[i] = Math.Max(Math.Max(f[i], f[i - j] * j), (i - j) * j);
            }
        }
        return f[n];
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int integerBreak(int n) {
    int* f = (int*) malloc((n + 1) * sizeof(int));
    f[1] = 1;
    for (int i = 2; i <= n; ++i) {
        f[i] = 0;
        for (int j = 1; j < i; ++j) {
            f[i] = max(f[i], max(f[i - j] * j, (i - j) * j));
        }
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：数学

当 $n \lt 4$ 时，由于题目要求至少拆分成两个整数，因此 $n - 1$ 是最大乘积。当 $n \ge 4$ 时，我们尽可能多地拆分 $3$，当剩下的最后一段为 $4$ 时，我们将其拆分为 $2 + 2$，这样乘积最大。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def integerBreak(self, n: int) -> int:
        if n < 4:
            return n - 1
        if n % 3 == 0:
            return pow(3, n // 3)
        if n % 3 == 1:
            return pow(3, n // 3 - 1) * 4
        return pow(3, n // 3) * 2
```

#### Java

```java
class Solution {
    public int integerBreak(int n) {
        if (n < 4) {
            return n - 1;
        }
        if (n % 3 == 0) {
            return (int) Math.pow(3, n / 3);
        }
        if (n % 3 == 1) {
            return (int) Math.pow(3, n / 3 - 1) * 4;
        }
        return (int) Math.pow(3, n / 3) * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int integerBreak(int n) {
        if (n < 4) {
            return n - 1;
        }
        if (n % 3 == 0) {
            return pow(3, n / 3);
        }
        if (n % 3 == 1) {
            return pow(3, n / 3 - 1) * 4;
        }
        return pow(3, n / 3) * 2;
    }
};
```

#### Go

```go
func integerBreak(n int) int {
	if n < 4 {
		return n - 1
	}
	if n%3 == 0 {
		return int(math.Pow(3, float64(n/3)))
	}
	if n%3 == 1 {
		return int(math.Pow(3, float64(n/3-1))) * 4
	}
	return int(math.Pow(3, float64(n/3))) * 2
}
```

#### TypeScript

```ts
function integerBreak(n: number): number {
    if (n < 4) {
        return n - 1;
    }
    const m = Math.floor(n / 3);
    if (n % 3 == 0) {
        return 3 ** m;
    }
    if (n % 3 == 1) {
        return 3 ** (m - 1) * 4;
    }
    return 3 ** m * 2;
}
```

#### Rust

```rust
impl Solution {
    pub fn integer_break(n: i32) -> i32 {
        if n < 4 {
            return n - 1;
        }
        match n % 3 {
            0 => return (3 as i32).pow((n / 3) as u32),
            1 => return (3 as i32).pow((n / 3 - 1) as u32) * 4,
            _ => return (3 as i32).pow((n / 3) as u32) * 2,
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var integerBreak = function (n) {
    if (n < 4) {
        return n - 1;
    }
    const m = Math.floor(n / 3);
    if (n % 3 == 0) {
        return 3 ** m;
    }
    if (n % 3 == 1) {
        return 3 ** (m - 1) * 4;
    }
    return 3 ** m * 2;
};
```

#### C#

```cs
public class Solution {
    public int IntegerBreak(int n) {
        if (n < 4) {
            return n - 1;
        }
        if (n % 3 == 0) {
            return (int)Math.Pow(3, n / 3);
        }
        if (n % 3 == 1) {
            return (int)Math.Pow(3, n / 3 - 1) * 4;
        }
        return (int)Math.Pow(3, n / 3) * 2;
    }
}
```

#### C

```c
int integerBreak(int n) {
    if (n < 4) {
        return n - 1;
    }
    if (n % 3 == 0) {
        return (int) pow(3, n / 3);
    }
    if (n % 3 == 1) {
        return (int) pow(3, n / 3 - 1) * 4;
    }
    return (int) pow(3, n / 3) * 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
