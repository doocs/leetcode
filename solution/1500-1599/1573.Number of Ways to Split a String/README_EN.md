# [1573. Number of Ways to Split a String](https://leetcode.com/problems/number-of-ways-to-split-a-string)

[中文文档](/solution/1500-1599/1573.Number%20of%20Ways%20to%20Split%20a%20String/README.md)

## Description

<p>Given a binary string <code>s</code>, you can split <code>s</code> into 3 <strong>non-empty</strong> strings <code>s1</code>, <code>s2</code>, and <code>s3</code> where <code>s1 + s2 + s3 = s</code>.</p>

<p>Return the number of ways <code>s</code> can be split such that the number of ones is the same in <code>s1</code>, <code>s2</code>, and <code>s3</code>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10101&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are four ways to split s in 3 parts where each part contain the same number of letters &#39;1&#39;.
&quot;1|010|1&quot;
&quot;1|01|01&quot;
&quot;10|10|1&quot;
&quot;10|1|01&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1001&quot;
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0000&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are three ways to split s in 3 parts.
&quot;0|0|00&quot;
&quot;0|00|0&quot;
&quot;00|0|0&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numWays(self, s: str) -> int:
        def find(x):
            t = 0
            for i, c in enumerate(s):
                t += int(c == '1')
                if t == x:
                    return i
        cnt, m = divmod(sum(c == '1' for c in s), 3)
        if m:
            return 0
        n = len(s)
        mod = 10**9 + 7
        if cnt == 0:
            return ((n - 1) * (n - 2) // 2) % mod
        i1, i2 = find(cnt), find(cnt + 1)
        j1, j2 = find(cnt * 2), find(cnt * 2 + 1)
        return (i2 - i1) * (j2 - j1) % (10**9 + 7)
```

### **Java**

```java
class Solution {
    private String s;

    public int numWays(String s) {
        this.s = s;
        int cnt = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++cnt;
            }
        }
        int m = cnt % 3;
        if (m != 0) {
            return 0;
        }
        final int mod = (int) 1e9 + 7;
        if (cnt == 0) {
            return (int) (((n - 1L) * (n - 2) / 2) % mod);
        }
        cnt /= 3;
        long i1 = find(cnt), i2 = find(cnt + 1);
        long j1 = find(cnt * 2), j2 = find(cnt * 2 + 1);
        return (int) ((i2 - i1) * (j2 - j1) % mod);
    }

    private int find(int x) {
        int t = 0;
        for (int i = 0;; ++i) {
            t += s.charAt(i) == '1' ? 1 : 0;
            if (t == x) {
                return i;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numWays(string s) {
        int cnt = 0;
        for (char& c : s) {
            cnt += c == '1';
        }
        int m = cnt % 3;
        if (m) {
            return 0;
        }
        const int mod = 1e9 + 7;
        int n = s.size();
        if (cnt == 0) {
            return (n - 1LL) * (n - 2) / 2 % mod;
        }
        cnt /= 3;
        auto find = [&](int x) {
            int t = 0;
            for (int i = 0; ; ++i) {
                t += s[i] == '1';
                if (t == x) {
                    return i;
                }
            }
        };
        int i1 = find(cnt), i2 = find(cnt + 1);
        int j1 = find(cnt * 2), j2 = find(cnt * 2 + 1);
        return (1LL * (i2 - i1) * (j2 - j1)) % mod;
    }
};
```

### **Go**

```go
func numWays(s string) int {
	cnt := 0
	for _, c := range s {
		if c == '1' {
			cnt++
		}
	}
	m := cnt % 3
	if m != 0 {
		return 0
	}
	const mod = 1e9 + 7
	n := len(s)
	if cnt == 0 {
		return (n - 1) * (n - 2) / 2 % mod
	}
	cnt /= 3
	find := func(x int) int {
		t := 0
		for i := 0; ; i++ {
			if s[i] == '1' {
				t++
				if t == x {
					return i
				}
			}
		}
	}
	i1, i2 := find(cnt), find(cnt+1)
	j1, j2 := find(cnt*2), find(cnt*2+1)
	return (i2 - i1) * (j2 - j1) % mod
}
```

### **...**

```

```

<!-- tabs:end -->
