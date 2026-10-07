---
comments: true
difficulty: 中等
rating: 1694
source: 第 91 场双周赛 Q2
tags:
    - 动态规划
---

<!-- problem:start -->

# [2466. 统计构造好字符串的方案数](https://leetcode.cn/problems/count-ways-to-build-good-strings)

[English Version](/solution/2400-2499/2466.Count%20Ways%20To%20Build%20Good%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你整数&nbsp;<code>zero</code>&nbsp;，<code>one</code>&nbsp;，<code>low</code>&nbsp;和&nbsp;<code>high</code>&nbsp;，我们从空字符串开始构造一个字符串，每一步执行下面操作中的一种：</p>

<ul>
	<li>将&nbsp;<code>'0'</code>&nbsp;在字符串末尾添加&nbsp;<code>zero</code>&nbsp; 次。</li>
	<li>将&nbsp;<code>'1'</code>&nbsp;在字符串末尾添加&nbsp;<code>one</code>&nbsp;次。</li>
</ul>

<p>以上操作可以执行任意次。</p>

<p>如果通过以上过程得到一个 <strong>长度</strong>&nbsp;在&nbsp;<code>low</code> 和&nbsp;<code>high</code>&nbsp;之间（包含上下边界）的字符串，那么这个字符串我们称为&nbsp;<strong>好</strong>&nbsp;字符串。</p>

<p>请你返回满足以上要求的 <strong>不同</strong>&nbsp;好字符串数目。由于答案可能很大，请将结果对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>low = 3, high = 3, zero = 1, one = 1
<b>输出：</b>8
<b>解释：</b>
一个可能的好字符串是 "011" 。
可以这样构造得到："" -&gt; "0" -&gt; "01" -&gt; "011" 。
从 "000" 到 "111" 之间所有的二进制字符串都是好字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>low = 2, high = 3, zero = 1, one = 2
<b>输出：</b>5
<b>解释：</b>好字符串为 "00" ，"11" ，"000" ，"110" 和 "011" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= low&nbsp;&lt;= high&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= zero, one &lt;= low</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 每次追加 $zero$ 个 $0$ 或 $one$ 个 $1$，长度落在 $[low,high]$ 即好串。 $high \le 10^5$，令 $dfs(i)$ 为已拼出长度 $i$ 时的方案：若 $i$ 已在区间内先计 $1$，再分支 $i+zero$ 与 $i+one$。越界为 $0$。

<!-- thinking:end -->

我们设计一个函数 $dfs(i)$ 表示从第 $i$ 位开始构造的好字符串的个数，答案即为 $dfs(0)$。

函数 $dfs(i)$ 的计算过程如下：

- 如果 $i \gt high$，返回 $0$；
- 如果 $ low \leq i \leq high$，答案累加 $1$，然后 $i$ 之后既可以添加 `zero` 个 $0$，也可以添加 `one` 个 $1$，因此答案累加上 $dfs(i + zero) + dfs(i + one)$。

过程中，我们需要对答案取模，并且可以使用记忆化搜索减少重复计算。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n = high$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodStrings(self, low: int, high: int, zero: int, one: int) -> int:
        @cache
        def dfs(i):
            if i > high:
                return 0
            ans = 0
            if low <= i <= high:
                ans += 1
            ans += dfs(i + zero) + dfs(i + one)
            return ans % mod

        mod = 10**9 + 7
        return dfs(0)
```

#### Java

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int[] f;
    private int lo;
    private int hi;
    private int zero;
    private int one;

    public int countGoodStrings(int low, int high, int zero, int one) {
        f = new int[high + 1];
        Arrays.fill(f, -1);
        lo = low;
        hi = high;
        this.zero = zero;
        this.one = one;
        return dfs(0);
    }

    private int dfs(int i) {
        if (i > hi) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        long ans = 0;
        if (i >= lo && i <= hi) {
            ++ans;
        }
        ans += dfs(i + zero) + dfs(i + one);
        ans %= MOD;
        f[i] = (int) ans;
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countGoodStrings(int low, int high, int zero, int one) {
        vector<int> f(high + 1, -1);
        function<int(int)> dfs = [&](int i) -> int {
            if (i > high) return 0;
            if (f[i] != -1) return f[i];
            long ans = i >= low && i <= high;
            ans += dfs(i + zero) + dfs(i + one);
            ans %= mod;
            f[i] = ans;
            return ans;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func countGoodStrings(low int, high int, zero int, one int) int {
	f := make([]int, high+1)
	for i := range f {
		f[i] = -1
	}
	const mod int = 1e9 + 7
	var dfs func(i int) int
	dfs = func(i int) int {
		if i > high {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		ans := 0
		if i >= low && i <= high {
			ans++
		}
		ans += dfs(i+zero) + dfs(i+one)
		ans %= mod
		f[i] = ans
		return ans
	}
	return dfs(0)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

<!-- thinking:start -->

> **思考**
>
> 方法一从长度 $high$ 往回填后缀表，$f[i]$ 是当前长度已为 $i$ 时还能得到的好串数。同一批字符串也可以按恰好拼到长度 $i$ 来数：$f[0]=1$，由 $f[i-zero]$ 与 $f[i-one]$ 转入，再把 $[low,high]$ 上的 $f$ 求和。
>
> 自左向右填表，时间仍为 $O(n)$。

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
function countGoodStrings(low: number, high: number, zero: number, one: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = new Array(high + 1).fill(0);
    f[0] = 1;

    for (let i = 1; i <= high; i++) {
        if (i >= zero) f[i] += f[i - zero];
        if (i >= one) f[i] += f[i - one];
        f[i] %= mod;
    }

    const ans = f.slice(low, high + 1).reduce((acc, cur) => acc + cur, 0);

    return ans % mod;
}
```

#### JavaScript

```js
/**
 * @param {number} low
 * @param {number} high
 * @param {number} zero
 * @param {number} one
 * @return {number}
 */
function countGoodStrings(low, high, zero, one) {
    const mod = 10 ** 9 + 7;
    const f = Array(high + 1).fill(0);
    f[0] = 1;

    for (let i = 1; i <= high; i++) {
        if (i >= zero) f[i] += f[i - zero];
        if (i >= one) f[i] += f[i - one];
        f[i] %= mod;
    }

    const ans = f.slice(low, high + 1).reduce((acc, cur) => acc + cur, 0);

    return ans % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：动态规划

<!-- thinking:start -->

> **思考**
>
> 每次追加 $zero$ 个 $0$ 或 $one$ 个 $1$，长度落在 $[low,high]$ 才是好串。$high \le 10^5$，按追加顺序展开会重复大量长度。
>
> 较短的那一档总是先递归。$zero$ 与 $one$ 都不小于 $1$，这次调用一定走向更大的长度，最坏调用链长度为 $high$，栈会溢出。
>
> 更长的长度在从 $high$ 往 $0$ 填时已经就绪。令 $f[i]$ 为当前长度已是 $i$ 时的好串数：区间内先计 $1$，再加上 $f[i+zero]$ 与 $f[i+one]$，越过 $high$ 的项视为 $0$。

<!-- thinking:end -->

令 $f[i]$ 表示当前长度已经是 $i$ 时，能够构造出的好字符串个数。答案为 $f[0]$。长度超过 $high$ 的贡献为 $0$。

从 $i = high$ 填到 $0$。若 $low \le i \le high$，当前字符串本身就是好串，先计入 $1$。之后仍可追加 $zero$ 个 $0$ 或 $one$ 个 $1$，把 $f[i + zero]$ 与 $f[i + one]$ 加进来；下标超过 $high$ 时该项为 $0$。每一步都对 $10^9 + 7$ 取模。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n = high$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodStrings(self, low: int, high: int, zero: int, one: int) -> int:
        mod = 10**9 + 7
        f = [0] * (high + 1)
        for i in range(high, -1, -1):
            ans = int(low <= i <= high)
            if i + zero <= high:
                ans += f[i + zero]
            if i + one <= high:
                ans += f[i + one]
            f[i] = ans % mod
        return f[0]
```

#### Java

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] f = new int[high + 1];
        for (int i = high; i >= 0; --i) {
            long ans = i >= low && i <= high ? 1 : 0;
            if (i + zero <= high) {
                ans += f[i + zero];
            }
            if (i + one <= high) {
                ans += f[i + one];
            }
            f[i] = (int) (ans % MOD);
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countGoodStrings(int low, int high, int zero, int one) {
        vector<int> f(high + 1);
        for (int i = high; i >= 0; --i) {
            long ans = i >= low && i <= high;
            if (i + zero <= high) {
                ans += f[i + zero];
            }
            if (i + one <= high) {
                ans += f[i + one];
            }
            f[i] = ans % mod;
        }
        return f[0];
    }
};
```

#### Go

```go
func countGoodStrings(low int, high int, zero int, one int) int {
	const mod int = 1e9 + 7
	f := make([]int, high+1)
	for i := high; i >= 0; i-- {
		ans := 0
		if i >= low && i <= high {
			ans++
		}
		if i+zero <= high {
			ans += f[i+zero]
		}
		if i+one <= high {
			ans += f[i+one]
		}
		f[i] = ans % mod
	}
	return f[0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
