---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2999.Count%20the%20Number%20of%20Powerful%20Integers/README.md
rating: 2351
source: 第 121 场双周赛 Q4
tags:
    - 数学
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [2999. 统计强大整数的数目](https://leetcode.cn/problems/count-the-number-of-powerful-integers)

[English Version](/solution/2900-2999/2999.Count%20the%20Number%20of%20Powerful%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个整数&nbsp;<code>start</code>&nbsp;，<code>finish</code>&nbsp;和&nbsp;<code>limit</code>&nbsp;。同时给你一个下标从&nbsp;<strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;，表示一个 <strong>正</strong>&nbsp;整数。</p>

<p>如果一个 <strong>正</strong>&nbsp;整数&nbsp;<code>x</code> 末尾部分是&nbsp;<code>s</code>&nbsp;（换句话说，<code>s</code>&nbsp;是 <code>x</code>&nbsp;的 <strong>后缀</strong>），且 <code>x</code>&nbsp;中的每个数位至多是 <code>limit</code>&nbsp;，那么我们称 <code>x</code>&nbsp;是 <strong>强大的</strong>&nbsp;。</p>

<p>请你返回区间&nbsp;<code>[start..finish]</code>&nbsp;内强大整数的&nbsp;<strong>总数目</strong>&nbsp;。</p>

<p>如果一个字符串 <code>x</code>&nbsp;是 <code>y</code>&nbsp;中某个下标开始（<strong>包括</strong>&nbsp;<code>0</code>&nbsp;），到下标为&nbsp;<code>y.length - 1</code>&nbsp;结束的子字符串，那么我们称&nbsp;<code>x</code>&nbsp;是&nbsp;<code>y</code>&nbsp;的一个后缀。比方说，<code>25</code>&nbsp;是&nbsp;<code>5125</code>&nbsp;的一个后缀，但不是&nbsp;<code>512</code>&nbsp;的后缀。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>start = 1, finish = 6000, limit = 4, s = "124"
<b>输出：</b>5
<b>解释：</b>区间 [1..6000] 内的强大数字为 124 ，1124 ，2124 ，3124 和 4124 。这些整数的各个数位都 &lt;= 4 且 "124" 是它们的后缀。注意 5124 不是强大整数，因为第一个数位 5 大于 4 。
这个区间内总共只有这 5 个强大整数。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>start = 15, finish = 215, limit = 6, s = "10"
<b>输出：</b>2
<b>解释：</b>区间 [15..215] 内的强大整数为 110 和 210 。这些整数的各个数位都 &lt;= 6 且 "10" 是它们的后缀。
这个区间总共只有这 2 个强大整数。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>start = 1000, finish = 2000, limit = 4, s = "3000"
<b>输出：</b>0
<b>解释：</b>区间 [1000..2000] 内的整数都小于 3000 ，所以 "3000" 不可能是这个区间内任何整数的后缀。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= start &lt;= finish &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= limit &lt;= 9</code></li>
	<li><code>1 &lt;= s.length &lt;= floor(log<sub>10</sub>(finish)) + 1</code></li>
	<li><code>s</code>&nbsp;数位中每个数字都小于等于&nbsp;<code>limit</code>&nbsp;。</li>
	<li><code>s</code>&nbsp;不包含任何前导 0 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数位 DP

这道题实际上是求在给定区间 $[l,..r]$ 中，满足条件的数的个数。个数与数的位数以及每一位上的数字有关。我们可以用数位 DP 的思路来解决这道题。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

对于本题而言，我们求出 $[1, \textit{finish}]$ 中满足条件的数的个数，然后减去 $[1, \textit{start} - 1]$ 中满足条件的数的个数，即可得到答案。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

1. 先将 $\textit{start}$ 和 $\textit{finish}$ 转化为字符串，方便后续的数位 DP。
2. 设计一个函数 $\textit{dfs}(\textit{pos}, \textit{lim})$，表示从第 $\textit{pos}$ 位开始搜索，当前的限制条件为 $\textit{lim}$。
3. 如果最大的数字位数小于 $\textit{s}$ 的长度，返回 0。
4. 如果当前剩余的数字位数等于 $\textit{s}$ 的长度，判断当前的数字是否满足条件，返回 1 或 0。
5. 否则，我们计算当前位的上限 $\textit{up} = \min(\textit{lim} ? \textit{t}[\textit{pos}] : 9, \textit{limit})$。然后遍历当前位的数字 $i$，从 0 到 $\textit{up}$，递归调用 $\textit{dfs}(\textit{pos} + 1, \textit{lim} \&\& i == \textit{t}[\textit{pos}])$，将结果累加到答案中。
6. 如果当前的 $\textit{lim}$ 为 false，则将当前的答案存入缓存中，避免重复计算。
7. 最后返回答案。

答案为区间 $[1, \textit{finish}]$ 中满足条件的数的个数减去区间 $[1, \textit{start} - 1]$ 中满足条件的数的个数。

时间复杂度 $O(\log M \times D)$，空间复杂度 $O(\log M)$，其中 $M$ 为数字的上限，而 $D = 10$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPowerfulInt(self, start: int, finish: int, limit: int, s: str) -> int:
        @cache
        def dfs(pos: int, lim: int) -> int:
            if len(t) < n:
                return 0
            if len(t) - pos == n:
                return int(s <= t[pos:]) if lim else 1
            up = min(int(t[pos]) if lim else 9, limit)
            ans = 0
            for i in range(up + 1):
                ans += dfs(pos + 1, lim and i == int(t[pos]))
            return ans

        n = len(s)
        t = str(start - 1)
        a = dfs(0, True)
        dfs.cache_clear()
        t = str(finish)
        b = dfs(0, True)
        return b - a
```

#### Java

```java
class Solution {
    private String s;
    private String t;
    private Long[] f;
    private int limit;

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        this.s = s;
        this.limit = limit;
        t = String.valueOf(start - 1);
        f = new Long[20];
        long a = dfs(0, true);
        t = String.valueOf(finish);
        f = new Long[20];
        long b = dfs(0, true);
        return b - a;
    }

    private long dfs(int pos, boolean lim) {
        if (t.length() < s.length()) {
            return 0;
        }
        if (!lim && f[pos] != null) {
            return f[pos];
        }
        if (t.length() - pos == s.length()) {
            return lim ? (s.compareTo(t.substring(pos)) <= 0 ? 1 : 0) : 1;
        }
        int up = lim ? t.charAt(pos) - '0' : 9;
        up = Math.min(up, limit);
        long ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos + 1, lim && i == (t.charAt(pos) - '0'));
        }
        if (!lim) {
            f[pos] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numberOfPowerfulInt(long long start, long long finish, int limit, string s) {
        string t = to_string(start - 1);
        long long f[20];
        memset(f, -1, sizeof(f));

        auto dfs = [&](this auto&& dfs, int pos, int lim) -> long long {
            if (t.size() < s.size()) {
                return 0;
            }
            if (!lim && f[pos] != -1) {
                return f[pos];
            }
            if (t.size() - pos == s.size()) {
                return lim ? s <= t.substr(pos) : 1;
            }
            long long ans = 0;
            int up = min(lim ? t[pos] - '0' : 9, limit);
            for (int i = 0; i <= up; ++i) {
                ans += dfs(pos + 1, lim && i == (t[pos] - '0'));
            }
            if (!lim) {
                f[pos] = ans;
            }
            return ans;
        };

        long long a = dfs(0, true);
        t = to_string(finish);
        memset(f, -1, sizeof(f));
        long long b = dfs(0, true);
        return b - a;
    }
};
```

#### Go

```go
func numberOfPowerfulInt(start, finish int64, limit int, s string) int64 {
	t := strconv.FormatInt(start-1, 10)
	f := make([]int64, 20)
	for i := range f {
		f[i] = -1
	}

	var dfs func(int, bool) int64
	dfs = func(pos int, lim bool) int64 {
		if len(t) < len(s) {
			return 0
		}
		if !lim && f[pos] != -1 {
			return f[pos]
		}
		if len(t)-pos == len(s) {
			if lim {
				if s <= t[pos:] {
					return 1
				}
				return 0
			}
			return 1
		}

		ans := int64(0)
		up := 9
		if lim {
			up = int(t[pos] - '0')
		}
		up = min(up, limit)
		for i := 0; i <= up; i++ {
			ans += dfs(pos+1, lim && i == int(t[pos]-'0'))
		}
		if !lim {
			f[pos] = ans
		}
		return ans
	}

	a := dfs(0, true)
	t = strconv.FormatInt(finish, 10)
	for i := range f {
		f[i] = -1
	}
	b := dfs(0, true)
	return b - a
}
```

#### TypeScript

```ts
function numberOfPowerfulInt(start: number, finish: number, limit: number, s: string): number {
    let t: string = (start - 1).toString();
    let f: number[] = Array(20).fill(-1);

    const dfs = (pos: number, lim: boolean): number => {
        if (t.length < s.length) {
            return 0;
        }
        if (!lim && f[pos] !== -1) {
            return f[pos];
        }
        if (t.length - pos === s.length) {
            if (lim) {
                return s <= t.substring(pos) ? 1 : 0;
            }
            return 1;
        }

        let ans: number = 0;
        const up: number = Math.min(lim ? +t[pos] : 9, limit);
        for (let i = 0; i <= up; i++) {
            ans += dfs(pos + 1, lim && i === +t[pos]);
        }

        if (!lim) {
            f[pos] = ans;
        }
        return ans;
    };

    const a: number = dfs(0, true);
    t = finish.toString();
    f = Array(20).fill(-1);
    const b: number = dfs(0, true);

    return b - a;
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_powerful_int(start: i64, finish: i64, limit: i32, s: String) -> i64 {
        fn count(x: i64, limit: i32, s: &str) -> i64 {
            let t = x.to_string();
            if t.len() < s.len() {
                return 0;
            }

            let t_bytes: Vec<u8> = t.bytes().collect();
            let mut f = [-1_i64; 20];

            fn dfs(
                pos: usize,
                lim: bool,
                t: &[u8],
                s: &str,
                limit: i32,
                f: &mut [i64; 20],
            ) -> i64 {
                if t.len() < s.len() {
                    return 0;
                }

                if !lim && f[pos] != -1 {
                    return f[pos];
                }

                if t.len() - pos == s.len() {
                    if lim {
                        let suffix = &t[pos..];
                        let suffix_str = String::from_utf8_lossy(suffix);
                        return if suffix_str.as_ref() >= s { 1 } else { 0 };
                    } else {
                        return 1;
                    }
                }

                let mut ans = 0;
                let up = if lim {
                    (t[pos] - b'0').min(limit as u8)
                } else {
                    limit as u8
                };

                for i in 0..=up {
                    let next_lim = lim && i == t[pos] - b'0';
                    ans += dfs(pos + 1, next_lim, t, s, limit, f);
                }

                if !lim {
                    f[pos] = ans;
                }

                ans
            }

            dfs(0, true, &t_bytes, s, limit, &mut f)
        }

        let a = count(start - 1, limit, &s);
        let b = count(finish, limit, &s);
        b - a
    }
}
```

#### C#

```cs
public class Solution {
    private string s;
    private string t;
    private long?[] f;
    private int limit;

    public long NumberOfPowerfulInt(long start, long finish, int limit, string s) {
        this.s = s;
        this.limit = limit;
        t = (start - 1).ToString();
        f = new long?[20];
        long a = Dfs(0, true);
        t = finish.ToString();
        f = new long?[20];
        long b = Dfs(0, true);
        return b - a;
    }

    private long Dfs(int pos, bool lim) {
        if (t.Length < s.Length) {
            return 0;
        }
        if (!lim && f[pos].HasValue) {
            return f[pos].Value;
        }
        if (t.Length - pos == s.Length) {
            return lim ? (string.Compare(s, t.Substring(pos)) <= 0 ? 1 : 0) : 1;
        }
        int up = lim ? t[pos] - '0' : 9;
        up = Math.Min(up, limit);
        long ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += Dfs(pos + 1, lim && i == (t[pos] - '0'));
        }
        if (!lim) {
            f[pos] = ans;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
