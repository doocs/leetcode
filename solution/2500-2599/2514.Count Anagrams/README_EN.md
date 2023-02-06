# [2514. Count Anagrams](https://leetcode.com/problems/count-anagrams)

[中文文档](/solution/2500-2599/2514.Count%20Anagrams/README.md)

## Description

<p>You are given a string <code>s</code> containing one or more words. Every consecutive pair of words is separated by a single space <code>&#39; &#39;</code>.</p>

<p>A string <code>t</code> is an <strong>anagram</strong> of string <code>s</code> if the <code>i<sup>th</sup></code> word of <code>t</code> is a <strong>permutation</strong> of the <code>i<sup>th</sup></code> word of <code>s</code>.</p>

<ul>
	<li>For example, <code>&quot;acb dfe&quot;</code> is an anagram of <code>&quot;abc def&quot;</code>, but <code>&quot;def cab&quot;</code>&nbsp;and <code>&quot;adc bef&quot;</code> are not.</li>
</ul>

<p>Return <em>the number of <strong>distinct anagrams</strong> of </em><code>s</code>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;too hot&quot;
<strong>Output:</strong> 18
<strong>Explanation:</strong> Some of the anagrams of the given string are &quot;too hot&quot;, &quot;oot hot&quot;, &quot;oto toh&quot;, &quot;too toh&quot;, and &quot;too oht&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aa&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one anagram possible for the given string.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters and spaces <code>&#39; &#39;</code>.</li>
	<li>There is single space between consecutive words.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
