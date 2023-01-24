# [1830. Minimum Number of Operations to Make String Sorted](https://leetcode.com/problems/minimum-number-of-operations-to-make-string-sorted)

[中文文档](/solution/1800-1899/1830.Minimum%20Number%20of%20Operations%20to%20Make%20String%20Sorted/README.md)

## Description

<p>You are given a string <code>s</code> (<strong>0-indexed</strong>)​​​​​​. You are asked to perform the following operation on <code>s</code>​​​​​​ until you get a sorted string:</p>

<ol>
	<li>Find <strong>the largest index</strong> <code>i</code> such that <code>1 &lt;= i &lt; s.length</code> and <code>s[i] &lt; s[i - 1]</code>.</li>
	<li>Find <strong>the largest index</strong> <code>j</code> such that <code>i &lt;= j &lt; s.length</code> and <code>s[k] &lt; s[i - 1]</code> for all the possible values of <code>k</code> in the range <code>[i, j]</code> inclusive.</li>
	<li>Swap the two characters at indices <code>i - 1</code>​​​​ and <code>j</code>​​​​​.</li>
	<li>Reverse the suffix starting at index <code>i</code>​​​​​​.</li>
</ol>

<p>Return <em>the number of operations needed to make the string sorted.</em> Since the answer can be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cba&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The simulation goes as follows:
Operation 1: i=2, j=2. Swap s[1] and s[2] to get s=&quot;cab&quot;, then reverse the suffix starting at 2. Now, s=&quot;cab&quot;.
Operation 2: i=1, j=2. Swap s[0] and s[2] to get s=&quot;bac&quot;, then reverse the suffix starting at 1. Now, s=&quot;bca&quot;.
Operation 3: i=2, j=2. Swap s[1] and s[2] to get s=&quot;bac&quot;, then reverse the suffix starting at 2. Now, s=&quot;bac&quot;.
Operation 4: i=1, j=1. Swap s[0] and s[1] to get s=&quot;abc&quot;, then reverse the suffix starting at 1. Now, s=&quot;acb&quot;.
Operation 5: i=2, j=2. Swap s[1] and s[2] to get s=&quot;abc&quot;, then reverse the suffix starting at 2. Now, s=&quot;abc&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabaa&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The simulation goes as follows:
Operation 1: i=3, j=4. Swap s[2] and s[4] to get s=&quot;aaaab&quot;, then reverse the substring starting at 3. Now, s=&quot;aaaba&quot;.
Operation 2: i=4, j=4. Swap s[3] and s[4] to get s=&quot;aaaab&quot;, then reverse the substring starting at 4. Now, s=&quot;aaaab&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3000</code></li>
	<li><code>s</code>​​​​​​ consists only of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
n = 3010
mod = 10**9 + 7
f = [1] + [0] * n
g = [1] + [0] * n

for i in range(1, n):
    f[i] = f[i - 1] * i % mod
    g[i] = pow(f[i], mod - 2, mod)


class Solution:
    def makeStringSorted(self, s: str) -> int:
        cnt = Counter(s)
        ans, n = 0, len(s)
        for i, c in enumerate(s):
            m = sum(v for a, v in cnt.items() if a < c)
            t = f[n - i - 1] * m
            for v in cnt.values():
                t = t * g[v] % mod
            ans = (ans + t) % mod
            cnt[c] -= 1
            if cnt[c] == 0:
                cnt.pop(c)
        return ans
```

### **Java**

```java
class Solution {
    private static final int N = 3010;
    private static final int MOD = (int) 1e9 + 7;
    private static final long[] f = new long[N];
    private static final long[] g = new long[N];

    static {
        f[0] = 1;
        g[0] = 1;
        for (int i = 1; i < N; ++i) {
            f[i] = f[i - 1] * i % MOD;
            g[i] = qmi(f[i], MOD - 2);
        }
    }

    public static long qmi(long a, int k) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % MOD;
            }
            k >>= 1;
            a = a * a % MOD;
        }
        return res;
    }

    public int makeStringSorted(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int m = 0;
            for (int j = s.charAt(i) - 'a' - 1; j >= 0; --j) {
                m += cnt[j];
            }
            long t = m * f[n - i - 1] % MOD;
            for (int v : cnt) {
                t = t * g[v] % MOD;
            }
            --cnt[s.charAt(i) - 'a'];
            ans = (ans + t + MOD) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
const int N = 3010;
const int MOD = 1e9 + 7;
long f[N];
long g[N];

long qmi(long a, int k) {
    long res = 1;
    while (k != 0) {
        if ((k & 1) == 1) {
            res = res * a % MOD;
        }
        k >>= 1;
        a = a * a % MOD;
    }
    return res;
}

int init = []() {
    f[0] = g[0] = 1;
    for (int i = 1; i < N; ++i) {
        f[i] = f[i - 1] * i % MOD;
        g[i] = qmi(f[i], MOD - 2);
    }
    return 0;
}();


class Solution {
public:
    int makeStringSorted(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int n = s.size();
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int m = 0;
            for (int j = s[i] - 'a' - 1; ~j; --j) {
                m += cnt[j];
            }
            long t = m * f[n - i - 1] % MOD;
            for (int& v : cnt) {
                t = t * g[v] % MOD;
            }
            ans = (ans + t + MOD) % MOD;
            --cnt[s[i] - 'a'];
        }
        return ans;
    }
};
```

### **Go**

```go
const n = 3010
const mod = 1e9 + 7

var f = make([]int, n)
var g = make([]int, n)

func qmi(a, k int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % mod
		}
		k >>= 1
		a = a * a % mod
	}
	return res
}

func init() {
	f[0], g[0] = 1, 1
	for i := 1; i < n; i++ {
		f[i] = f[i-1] * i % mod
		g[i] = qmi(f[i], mod-2)
	}
}

func makeStringSorted(s string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for i, c := range s {
		m := 0
		for j := int(c-'a') - 1; j >= 0; j-- {
			m += cnt[j]
		}
		t := m * f[len(s)-i-1] % mod
		for _, v := range cnt {
			t = t * g[v] % mod
		}
		ans = (ans + t + mod) % mod
		cnt[c-'a']--
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
