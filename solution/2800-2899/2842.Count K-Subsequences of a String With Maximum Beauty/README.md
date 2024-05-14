# [2842. 统计一个字符串的 k 子序列美丽值最大的数目](https://leetcode.cn/problems/count-k-subsequences-of-a-string-with-maximum-beauty)

[English Version](/solution/2800-2899/2842.Count%20K-Subsequences%20of%20a%20String%20With%20Maximum%20Beauty/README_EN.md)

<!-- tags:贪心,哈希表,数学,字符串,组合数学 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p><strong>k 子序列</strong>指的是 <code>s</code>&nbsp;的一个长度为 <code>k</code>&nbsp;的 <strong>子序列</strong>&nbsp;，且所有字符都是 <strong>唯一</strong>&nbsp;的，也就是说每个字符在子序列里只出现过一次。</p>

<p>定义&nbsp;<code>f(c)</code>&nbsp;为字符 <code>c</code>&nbsp;在 <code>s</code>&nbsp;中出现的次数。</p>

<p>k 子序列的 <strong>美丽值</strong>&nbsp;定义为这个子序列中每一个字符 <code>c</code>&nbsp;的&nbsp;<code>f(c)</code>&nbsp;之 <strong>和</strong>&nbsp;。</p>

<p>比方说，<code>s = "abbbdd"</code>&nbsp;和&nbsp;<code>k = 2</code>&nbsp;，我们有：</p>

<ul>
	<li><code>f('a') = 1</code>, <code>f('b') = 3</code>, <code>f('d') = 2</code></li>
	<li><code>s</code>&nbsp;的部分 k 子序列为：
	<ul>
		<li><code>"<em><strong>ab</strong></em>bbdd"</code> -&gt; <code>"ab"</code>&nbsp;，美丽值为&nbsp;<code>f('a') + f('b') = 4</code></li>
		<li><code>"<em><strong>a</strong></em>bbb<em><strong>d</strong></em>d"</code> -&gt; <code>"ad"</code>&nbsp;，美丽值为&nbsp;<code>f('a') + f('d') = 3</code></li>
		<li><code>"a<em><strong>b</strong></em>bb<em><strong>d</strong></em>d"</code> -&gt; <code>"bd"</code>&nbsp;，美丽值为&nbsp;<code>f('b') + f('d') = 5</code></li>
	</ul>
	</li>
</ul>

<p>请你返回一个整数，表示所有 <strong>k 子序列&nbsp;</strong>里面 <strong>美丽值 </strong>是&nbsp;<strong>最大值</strong>&nbsp;的子序列数目。由于答案可能很大，将结果对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余后返回。</p>

<p>一个字符串的子序列指的是从原字符串里面删除一些字符（也可能一个字符也不删除），不改变剩下字符顺序连接得到的新字符串。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><code>f(c)</code> 指的是字符&nbsp;<code>c</code>&nbsp;在字符串&nbsp;<code>s</code>&nbsp;的出现次数，不是在 k 子序列里的出现次数。</li>
	<li>两个 k 子序列如果有任何一个字符在原字符串中的下标不同，则它们是两个不同的子序列。所以两个不同的 k 子序列可能产生相同的字符串。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s = "bcca", k = 2
<b>输出：</b>4
<b>解释：</b><span style="white-space: normal">s 中我们有 f('a') = 1 ，f('b') = 1 和 f('c') = 2 。</span>
s 的 k 子序列为：
<em><strong>bc</strong></em>ca ，美丽值为 f('b') + f('c') = 3
<em><strong>b</strong></em>c<em><strong>c</strong></em>a ，美丽值为 f('b') + f('c') = 3
<em><strong>b</strong></em>cc<em><strong>a</strong></em> ，美丽值为 f('b') + f('a') = 2
b<em><strong>c</strong></em>c<em><strong>a</strong></em><strong> </strong>，美丽值为 f('c') + f('a') = 3
bc<em><strong>ca</strong></em> ，美丽值为 f('c') + f('a') = 3
总共有 4 个 k 子序列美丽值为最大值 3 。
所以答案为 4 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s = "abbcd", k = 4
<b>输出：</b>2
<b>解释：</b><span style="white-space: normal">s 中我们有 f('a') = 1 ，f('b') = 2&nbsp;，f('c') = 1&nbsp;和</span> f('d') = 1 。
s 的 k 子序列为：
<em><strong>ab</strong></em>b<em><strong>cd</strong></em> ，美丽值为 f('a') + f('b') + f('c') + f('d') = 5
<span style="white-space: normal;"><b><i>a</i></b></span>b<em><strong>bcd</strong></em> ，美丽值为 f('a') + f('b') + f('c') + f('d') = 5 
总共有 2 个 k 子序列美丽值为最大值 5 。
所以答案为 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：贪心 + 组合数学

我们先用哈希表 $f$ 统计字符串 $s$ 中每个字符的出现次数，即 $f[c]$ 表示字符 $c$ 在字符串 $s$ 中出现的次数。

由于 $k$ 子序列是字符串 $s$ 中一个长度为 $k$ 的子序列，且字符唯一，因此，如果 $f$ 中不同字符的个数小于 $k$，那么不存在 $k$ 子序列，直接返回 $0$ 即可。

否则，要使得 $k$ 子序列的美丽值最大，我们需要尽可能地让美丽值大的字符尽可能地多地出现在 $k$ 子序列中。因此，我们可以对 $f$ 中的值进行倒序排序，得到一个数组 $vs$。

我们记数组 $vs$ 第 $k$ 个字符的出现次数为 $val$，一共有 $x$ 个字符出现的次数为 $val$。

那么我们先找出出现次数大于 $val$ 的字符，将每个字符出现的次数相乘，得到初始答案 $ans$，剩余的需要选取的字符个数更新为 $k$。我们需要从 $x$ 个字符中选取 $k$ 个字符，因此答案需要乘上组合数 $C_x^k$，最后再乘上 $val^k$，即 $ans = ans \times C_x^k \times val^k$。

注意，这里需要用到快速幂，以及取模操作。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串的长度，而 $\Sigma$ 是字符集。本题中字符集为小写字母，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

```python
class Solution:
    def countKSubsequencesWithMaxBeauty(self, s: str, k: int) -> int:
        f = Counter(s)
        if len(f) < k:
            return 0
        mod = 10**9 + 7
        vs = sorted(f.values(), reverse=True)
        val = vs[k - 1]
        x = vs.count(val)
        ans = 1
        for v in vs:
            if v == val:
                break
            k -= 1
            ans = ans * v % mod
        ans = ans * comb(x, k) * pow(val, k, mod) % mod
        return ans
```

```java
class Solution {
    private final int mod = (int) 1e9 + 7;

    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int[] f = new int[26];
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (++f[s.charAt(i) - 'a'] == 1) {
                ++cnt;
            }
        }
        if (cnt < k) {
            return 0;
        }
        Integer[] vs = new Integer[cnt];
        for (int i = 0, j = 0; i < 26; ++i) {
            if (f[i] > 0) {
                vs[j++] = f[i];
            }
        }
        Arrays.sort(vs, (a, b) -> b - a);
        long ans = 1;
        int val = vs[k - 1];
        int x = 0;
        for (int v : vs) {
            if (v == val) {
                ++x;
            }
        }
        for (int v : vs) {
            if (v == val) {
                break;
            }
            --k;
            ans = ans * v % mod;
        }
        int[][] c = new int[x + 1][x + 1];
        for (int i = 0; i <= x; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
            }
        }
        ans = ((ans * c[x][k]) % mod) * qpow(val, k) % mod;
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
    int countKSubsequencesWithMaxBeauty(string s, int k) {
        int f[26]{};
        int cnt = 0;
        for (char& c : s) {
            if (++f[c - 'a'] == 1) {
                ++cnt;
            }
        }
        if (cnt < k) {
            return 0;
        }
        vector<int> vs(cnt);
        for (int i = 0, j = 0; i < 26; ++i) {
            if (f[i]) {
                vs[j++] = f[i];
            }
        }
        sort(vs.rbegin(), vs.rend());
        const int mod = 1e9 + 7;
        long long ans = 1;
        int val = vs[k - 1];
        int x = 0;
        for (int v : vs) {
            x += v == val;
        }
        for (int v : vs) {
            if (v == val) {
                break;
            }
            --k;
            ans = ans * v % mod;
        }
        int c[x + 1][x + 1];
        memset(c, 0, sizeof(c));
        for (int i = 0; i <= x; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        auto qpow = [&](long long a, int n) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return ans;
        };
        ans = (ans * c[x][k] % mod) * qpow(val, k) % mod;
        return ans;
    }
};
```

```go
func countKSubsequencesWithMaxBeauty(s string, k int) int {
	f := [26]int{}
	cnt := 0
	for _, c := range s {
		f[c-'a']++
		if f[c-'a'] == 1 {
			cnt++
		}
	}
	if cnt < k {
		return 0
	}
	vs := []int{}
	for _, x := range f {
		if x > 0 {
			vs = append(vs, x)
		}
	}
	sort.Slice(vs, func(i, j int) bool {
		return vs[i] > vs[j]
	})
	const mod int = 1e9 + 7
	ans := 1
	val := vs[k-1]
	x := 0
	for _, v := range vs {
		if v == val {
			x++
		}
	}
	for _, v := range vs {
		if v == val {
			break
		}
		k--
		ans = ans * v % mod
	}
	c := make([][]int, x+1)
	for i := range c {
		c[i] = make([]int, x+1)
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = (c[i-1][j-1] + c[i-1][j]) % mod
		}
	}
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
	ans = (ans * c[x][k] % mod) * qpow(val, k) % mod
	return ans
}
```

```ts
function countKSubsequencesWithMaxBeauty(s: string, k: number): number {
    const f: number[] = new Array(26).fill(0);
    let cnt = 0;
    for (const c of s) {
        const i = c.charCodeAt(0) - 97;
        if (++f[i] === 1) {
            ++cnt;
        }
    }
    if (cnt < k) {
        return 0;
    }
    const mod = BigInt(10 ** 9 + 7);
    const vs: number[] = f.filter(v => v > 0).sort((a, b) => b - a);
    const val = vs[k - 1];
    const x = vs.filter(v => v === val).length;
    let ans = 1n;
    for (const v of vs) {
        if (v === val) {
            break;
        }
        --k;
        ans = (ans * BigInt(v)) % mod;
    }
    const c: number[][] = new Array(x + 1).fill(0).map(() => new Array(k + 1).fill(0));
    for (let i = 0; i <= x; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= Math.min(i, k); ++j) {
            c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % Number(mod);
        }
    }
    const qpow = (a: bigint, n: number): bigint => {
        let ans = 1n;
        for (; n; n >>>= 1) {
            if (n & 1) {
                ans = (ans * a) % BigInt(mod);
            }
            a = (a * a) % BigInt(mod);
        }
        return ans;
    };
    ans = (((ans * BigInt(c[x][k])) % mod) * qpow(BigInt(val), k)) % mod;
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- end -->
