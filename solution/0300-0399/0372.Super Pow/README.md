---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0372.Super%20Pow/README.md
tags:
    - 数学
    - 分治
---

<!-- problem:start -->

# [372. 超级次方](https://leetcode.cn/problems/super-pow)

[English Version](/solution/0300-0399/0372.Super%20Pow/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你的任务是计算 <code>a<sup>b</sup></code> 对 <code>1337</code> 取模，<code>a</code> 是一个正整数，<code>b</code> 是一个非常大的正整数且会以数组形式给出。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 2, b = [3]
<strong>输出：</strong>8
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 2, b = [1,0]
<strong>输出：</strong>1024
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = [4,3,3,8,5,2]
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>a = 2147483647, b = [2,0,0]
<strong>输出：</strong>1198
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= a <= 2<sup>31</sup> - 1</code></li>
	<li><code>1 <= b.length <= 2000</code></li>
	<li><code>0 <= b[i] <= 9</code></li>
	<li><code>b</code> 不含前导 0</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：快速幂

我们初始化答案变量 $ans = 1$。

接下来，倒序遍历数组 $b$，每次遍历到一个元素 $e$，我们将答案变量 $ans$ 自乘 $a^e$ 并对 $1337$ 取模，然后将 $a$ 自乘 $10$ 次并对 $1337$ 取模。这里需要用到快速幂。

遍历完数组后，返回答案即可。

时间复杂度 $O(\sum_{i=0}^{n-1} \log b_i)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def superPow(self, a: int, b: List[int]) -> int:
        mod = 1337
        ans = 1
        for e in b[::-1]:
            ans = ans * pow(a, e, mod) % mod
            a = pow(a, 10, mod)
        return ans
```

```java
class Solution {
    private final int mod = 1337;

    public int superPow(int a, int[] b) {
        long ans = 1;
        for (int i = b.length - 1; i >= 0; --i) {
            ans = ans * qpow(a, b[i]) % mod;
            a = qpow(a, 10);
        }
        return (int) ans;
    }

    private long qpow(long a, int n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int superPow(int a, vector<int>& b) {
        using ll = long long;
        const int mod = 1337;
        ll ans = 1;
        auto qpow = [&](ll a, int n) {
            ll ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return (int) ans;
        };
        for (int i = b.size() - 1; ~i; --i) {
            ans = ans * qpow(a, b[i]) % mod;
            a = qpow(a, 10);
        }
        return ans;
    }
};
```

```go
func superPow(a int, b []int) int {
	const mod int = 1337
	ans := 1
	qpow := func(a, n int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	for i := len(b) - 1; i >= 0; i-- {
		ans = ans * qpow(a, b[i]) % mod
		a = qpow(a, 10)
	}
	return ans
}
```

```ts
function superPow(a: number, b: number[]): number {
    let ans = 1;
    const mod = 1337;
    const qpow = (a: number, n: number): number => {
        let ans = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                ans = Number((BigInt(ans) * BigInt(a)) % BigInt(mod));
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return ans;
    };
    for (let i = b.length - 1; ~i; --i) {
        ans = Number((BigInt(ans) * BigInt(qpow(a, b[i]))) % BigInt(mod));
        a = qpow(a, 10);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
