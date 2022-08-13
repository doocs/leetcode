# [2327. 知道秘密的人数](https://leetcode.cn/problems/number-of-people-aware-of-a-secret)

[English Version](/solution/2300-2399/2327.Number%20of%20People%20Aware%20of%20a%20Secret/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：差分数组**

差分数组 $d[i]$ 记录每天知道秘密的人数变化情况，$cnt[i]$ 记录第 $i$ 天新得知秘密的人数。那么从 $[i+delay,i+forget)$ 的这段时间内，$cnt[i]$ 个人每天都能分享给另外 $cnt[i]$ 个人。

最终 $sum(d[:n+1])$ 就是答案。

时间复杂度 $O(n^2)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int m = (n << 1) + 10;
        long[] d = new long[m];
        long[] cnt = new long[m];
        cnt[1] = 1;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] > 0) {
                d[i] = (d[i] + cnt[i]) % MOD;
                d[i + forget] = (d[i + forget] - cnt[i] + MOD) % MOD;
                int nxt = i + delay;
                while (nxt < i + forget) {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % MOD;
                    ++nxt;
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans = (ans + d[i]) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
using ll = long long;
const int mod = 1e9 + 7;

class Solution {
public:
    int peopleAwareOfSecret(int n, int delay, int forget) {
        int m = (n << 1) + 10;
        vector<ll> d(m);
        vector<ll> cnt(m);
        cnt[1] = 1;
        for (int i = 1; i <= n; ++i) {
            if (!cnt[i]) continue;
            d[i] = (d[i] + cnt[i]) % mod;
            d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
            int nxt = i + delay;
            while (nxt < i + forget) {
                cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                ++nxt;
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; ++i) ans = (ans + d[i]) % mod;
        return ans;
    }
};
```

### **Go**

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

### **TypeScript**

```ts
function peopleAwareOfSecret(n: number, delay: number, forget: number): number {
    let dp = new Array(n + 1).fill(0n);
    dp[1] = 1n;
    for (let i = 2; i <= n; i++) {
        let pre = 0n;
        for (let j = i - forget + 1; j <= i - delay; j++) {
            if (j > 0) {
                pre += dp[j];
            }
        }
        dp[i] = pre;
    }
    let pre = 0n;
    let i = n + 1;
    for (let j = i - forget; j < i; j++) {
        if (j > 0) {
            pre += dp[j];
        }
    }
    return Number(pre % BigInt(10 ** 9 + 7));
}
```

### **...**

```

```

<!-- tabs:end -->
