# [2514. 统计同位异构字符串数目](https://leetcode.cn/problems/count-anagrams)

[English Version](/solution/2500-2599/2514.Count%20Anagrams/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，它包含一个或者多个单词。单词之间用单个空格&nbsp;<code>' '</code>&nbsp;隔开。</p>

<p>如果字符串 <code>t</code>&nbsp;中第 <code>i</code>&nbsp;个单词是 <code>s</code>&nbsp;中第 <code>i</code>&nbsp;个单词的一个&nbsp;<strong>排列</strong>&nbsp;，那么我们称字符串&nbsp;<code>t</code>&nbsp;是字符串&nbsp;<code>s</code>&nbsp;的同位异构字符串。</p>

<ul>
	<li>比方说，<code>"acb dfe"</code>&nbsp;是&nbsp;<code>"abc def"</code>&nbsp;的同位异构字符串，但是&nbsp;<code>"def cab"</code>&nbsp;和&nbsp;<code>"adc bef"</code>&nbsp;不是。</li>
</ul>

<p>请你返回<em>&nbsp;</em><code>s</code>&nbsp;的同位异构字符串的数目，由于答案可能很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong> 后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "too hot"
<b>输出：</b>18
<b>解释：</b>输入字符串的一些同位异构字符串为 "too hot" ，"oot hot" ，"oto toh" ，"too toh" 以及 "too oht" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "aa"
<b>输出：</b>1
<strong>解释：</strong>输入字符串只有一个同位异构字符串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 只包含小写英文字母和空格&nbsp;<code>' '</code>&nbsp;。</li>
	<li>相邻单词之间由单个空格隔开。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
mod = 10**9 + 7
f = [1]
for i in range(1, 10**5 + 1):
    f.append(f[-1] * i % mod)


class Solution:
    def countAnagrams(self, s: str) -> int:
        ans = 1
        for w in s.split():
            cnt = Counter(w)
            ans *= f[len(w)]
            ans %= mod
            for v in cnt.values():
                ans *= pow(f[v], -1, mod)
                ans %= mod
        return ans
```

```python
class Solution:
    def countAnagrams(self, s: str) -> int:
        mod = 10**9 + 7
        ans = mul = 1
        for w in s.split():
            cnt = Counter()
            for i, c in enumerate(w, 1):
                cnt[c] += 1
                mul = mul * cnt[c] % mod
                ans = ans * i % mod
        return ans * pow(mul, -1, mod) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
import java.math.BigInteger;

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countAnagrams(String s) {
        int n = s.length();
        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i - 1] * i % MOD;
        }
        long p = 1;
        for (String w : s.split(" ")) {
            int[] cnt = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                ++cnt[w.charAt(i) - 'a'];
            }
            p = p * f[w.length()] % MOD;
            for (int v : cnt) {
                p = p * BigInteger.valueOf(f[v]).modInverse(BigInteger.valueOf(MOD)).intValue()
                    % MOD;
            }
        }
        return (int) p;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countAnagrams(string s) {
        stringstream ss(s);
        string w;
        long ans = 1, mul = 1;
        while (ss >> w) {
            int cnt[26] = {0};
            for (int i = 1; i <= w.size(); ++i) {
                int c = w[i - 1] - 'a';
                ++cnt[c];
                ans = ans * i % mod;
                mul = mul * cnt[c] % mod;
            }
        }
        return ans * pow(mul, mod - 2) % mod;
    }

    long pow(long x, int n) {
        long res = 1L;
        for (; n; n /= 2) {
            if (n % 2) res = res * x % mod;
            x = x * x % mod;
        }
        return res;
    }
};
```

### **Go**

```go
const mod int = 1e9 + 7

func countAnagrams(s string) int {
	ans, mul := 1, 1
	for _, w := range strings.Split(s, " ") {
		cnt := [26]int{}
		for i, c := range w {
			i++
			cnt[c-'a']++
			ans = ans * i % mod
			mul = mul * cnt[c-'a'] % mod
		}
	}
	return ans * pow(mul, mod-2) % mod
}

func pow(x, n int) int {
	res := 1
	for ; n > 0; n >>= 1 {
		if n&1 > 0 {
			res = res * x % mod
		}
		x = x * x % mod
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
