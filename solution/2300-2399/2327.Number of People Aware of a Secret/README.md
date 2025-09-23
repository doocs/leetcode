---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2327.Number%20of%20People%20Aware%20of%20a%20Secret/README.md
rating: 1893
source: 第 300 场周赛 Q3
tags:
    - 队列
    - 动态规划
    - 模拟
---

<!-- problem:start -->

# [2327. 知道秘密的人数](https://leetcode.cn/problems/number-of-people-aware-of-a-secret)

[English Version](/solution/2300-2399/2327.Number%20of%20People%20Aware%20of%20a%20Secret/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在第 <code>1</code>&nbsp;天，有一个人发现了一个秘密。</p>

<p>给你一个整数&nbsp;<code>delay</code>&nbsp;，表示每个人会在发现秘密后的 <code>delay</code>&nbsp;天之后，<strong>每天</strong>&nbsp;给一个新的人&nbsp;<strong>分享</strong>&nbsp;秘密。同时给你一个整数&nbsp;<code>forget</code>&nbsp;，表示每个人在发现秘密&nbsp;<code>forget</code>&nbsp;天之后会&nbsp;<strong>忘记</strong>&nbsp;这个秘密。一个人&nbsp;<strong>不能</strong>&nbsp;在忘记秘密那一天及之后的日子里分享秘密。</p>

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，请你返回在第 <code>n</code>&nbsp;天结束时，知道秘密的人数。由于答案可能会很大，请你将结果对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 6, delay = 2, forget = 4
<b>输出：</b>5
<strong>解释：</strong>
第 1 天：假设第一个人叫 A 。（一个人知道秘密）
第 2 天：A 是唯一一个知道秘密的人。（一个人知道秘密）
第 3 天：A 把秘密分享给 B 。（两个人知道秘密）
第 4 天：A 把秘密分享给一个新的人 C 。（三个人知道秘密）
第 5 天：A 忘记了秘密，B 把秘密分享给一个新的人 D 。（三个人知道秘密）
第 6 天：B 把秘密分享给 E，C 把秘密分享给 F 。（五个人知道秘密）
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 4, delay = 1, forget = 3
<b>输出：</b>6
<strong>解释：</strong>
第 1 天：第一个知道秘密的人为 A 。（一个人知道秘密）
第 2 天：A 把秘密分享给 B 。（两个人知道秘密）
第 3 天：A 和 B 把秘密分享给 2 个新的人 C 和 D 。（四个人知道秘密）
第 4 天：A 忘记了秘密，B、C、D 分别分享给 3 个新的人。（六个人知道秘密）
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= delay &lt; forget &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

我们用一个差分数组 $d[i]$ 来记录第 $i$ 天知道秘密的人数变化情况，用一个数组 $cnt[i]$ 来记录第 $i$ 天新得知秘密的人数。

那么，对于第 $i$ 天新得知秘密的 $cnt[i]$ 个人来说，他们会在 $[i+\text{delay}, i+\text{forget})$ 这段时间内每天都能分享给另外 $cnt[i]$ 个人。

答案为 $\sum_{i=1}^{n} d[i]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为题目给定的整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def peopleAwareOfSecret(self, n: int, delay: int, forget: int) -> int:
        m = (n << 1) + 10
        d = [0] * m
        cnt = [0] * m
        cnt[1] = 1
        for i in range(1, n + 1):
            if cnt[i]:
                d[i] += cnt[i]
                d[i + forget] -= cnt[i]
                nxt = i + delay
                while nxt < i + forget:
                    cnt[nxt] += cnt[i]
                    nxt += 1
        mod = 10**9 + 7
        return sum(d[: n + 1]) % mod
```

#### Java

```java
class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int mod = (int) 1e9 + 7;
        int m = (n << 1) + 10;
        long[] d = new long[m];
        long[] cnt = new long[m];
        cnt[1] = 1;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] > 0) {
                d[i] = (d[i] + cnt[i]) % mod;
                d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
                int nxt = i + delay;
                while (nxt < i + forget) {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                    ++nxt;
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans = (ans + d[i]) % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int peopleAwareOfSecret(int n, int delay, int forget) {
        const int mod = 1e9 + 7;
        int m = (n << 1) + 10;
        vector<long long> d(m), cnt(m);
        cnt[1] = 1;
        for (int i = 1; i <= n; i++) {
            if (cnt[i]) {
                d[i] = (d[i] + cnt[i]) % mod;
                d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
                int nxt = i + delay;
                while (nxt < i + forget) {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                    nxt++;
                }
            }
        }
        long long ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += d[i];
        }
        return ans % mod;
    }
};
```

#### Go

```go
func peopleAwareOfSecret(n int, delay int, forget int) int {
	m := (n << 1) + 10
	d := make([]int, m)
	cnt := make([]int, m)
	mod := int(1e9) + 7
	cnt[1] = 1
	for i := 1; i <= n; i++ {
		if cnt[i] == 0 {
			continue
		}
		d[i] = (d[i] + cnt[i]) % mod
		d[i+forget] = (d[i+forget] - cnt[i] + mod) % mod
		nxt := i + delay
		for nxt < i+forget {
			cnt[nxt] = (cnt[nxt] + cnt[i]) % mod
			nxt++
		}
	}
	ans := 0
	for i := 1; i <= n; i++ {
		ans = (ans + d[i]) % mod
	}
	return ans
}
```

#### TypeScript

```ts
function peopleAwareOfSecret(n: number, delay: number, forget: number): number {
    const mod = 1e9 + 7;
    const m = (n << 1) + 10;
    const d: number[] = Array(m).fill(0);
    const cnt: number[] = Array(m).fill(0);

    cnt[1] = 1;
    for (let i = 1; i <= n; ++i) {
        if (cnt[i] > 0) {
            d[i] = (d[i] + cnt[i]) % mod;
            d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
            let nxt = i + delay;
            while (nxt < i + forget) {
                cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                ++nxt;
            }
        }
    }

    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        ans = (ans + d[i]) % mod;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn people_aware_of_secret(n: i32, delay: i32, forget: i32) -> i32 {
        let n = n as usize;
        let delay = delay as usize;
        let forget = forget as usize;
        let m = (n << 1) + 10;
        let modulo: i64 = 1_000_000_007;

        let mut d = vec![0i64; m];
        let mut cnt = vec![0i64; m];

        cnt[1] = 1;
        for i in 1..=n {
            if cnt[i] > 0 {
                d[i] = (d[i] + cnt[i]) % modulo;
                d[i + forget] = (d[i + forget] - cnt[i] + modulo) % modulo;
                let mut nxt = i + delay;
                while nxt < i + forget {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % modulo;
                    nxt += 1;
                }
            }
        }

        let mut ans: i64 = 0;
        for i in 1..=n {
            ans = (ans + d[i]) % modulo;
        }
        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
