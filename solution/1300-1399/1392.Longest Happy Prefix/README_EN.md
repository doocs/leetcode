# [1392. Longest Happy Prefix](https://leetcode.com/problems/longest-happy-prefix)

[中文文档](/solution/1300-1399/1392.Longest%20Happy%20Prefix/README.md)

## Description

<p>A string is called a <strong>happy prefix</strong> if is a <strong>non-empty</strong> prefix which is also a suffix (excluding itself).</p>

<p>Given a string <code>s</code>, return <em>the <strong>longest happy prefix</strong> of</em> <code>s</code>. Return an empty string <code>&quot;&quot;</code> if no such prefix exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;level&quot;
<strong>Output:</strong> &quot;l&quot;
<strong>Explanation:</strong> s contains 4 prefix excluding itself (&quot;l&quot;, &quot;le&quot;, &quot;lev&quot;, &quot;leve&quot;), and suffix (&quot;l&quot;, &quot;el&quot;, &quot;vel&quot;, &quot;evel&quot;). The largest prefix which is also suffix is given by &quot;l&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ababab&quot;
<strong>Output:</strong> &quot;abab&quot;
<strong>Explanation:</strong> &quot;abab&quot; is the largest prefix which is also suffix. They can overlap in the original string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: String Hashing

**String Hashing** is a method to map a string of any length to a non-negative integer, with the probability of collision being almost zero. String hashing is used to calculate the hash value of a string, which allows for quick determination of whether two strings are equal.

We choose a fixed value BASE, and consider the string as a number in BASE radix, assigning a value greater than 0 to represent each character. Generally, the values we assign are much smaller than BASE. For example, for strings composed of lowercase letters, we can assign a=1, b=2, ..., z=26. We choose a fixed value MOD, and calculate the remainder of the BASE radix number divided by MOD, which is used as the hash value of the string.

Generally, we choose BASE=131 or BASE=13331, at which point the probability of hash value collision is extremely low. As long as the hash values of two strings are the same, we consider the two strings to be equal. Usually, MOD is chosen as $2^{64}$. In C++, we can directly use the unsigned long long type to store this hash value. When calculating, we do not handle arithmetic overflow. When overflow occurs, it is equivalent to automatically taking the modulus of $2^{64}$, which can avoid inefficient modulus operations.

Except for extremely specially constructed data, the above hash algorithm is unlikely to produce collisions. In general, the above hash algorithm can appear in the standard answers of the problem. We can also choose some appropriate values of BASE and MOD (such as large prime numbers), and perform several groups of hash operations. Only when the results are all the same do we consider the original strings to be equal, making it even more difficult to construct data that causes this hash to produce errors.

<!-- tabs:start -->

```python
class Solution:
    def longestPrefix(self, s: str) -> str:
        for i in range(1, len(s)):
            if s[:-i] == s[i:]:
                return s[i:]
        return ''
```

```java
class Solution {
    private long[] p;
    private long[] h;

    public String longestPrefix(String s) {
        int base = 131;
        int n = s.length();
        p = new long[n + 10];
        h = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s.charAt(i);
        }
        for (int l = n - 1; l > 0; --l) {
            if (get(1, l) == get(n - l + 1, n)) {
                return s.substring(0, l);
            }
        }
        return "";
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
```

```cpp
typedef unsigned long long ULL;

class Solution {
public:
    string longestPrefix(string s) {
        int base = 131;
        int n = s.size();
        ULL p[n + 10];
        ULL h[n + 10];
        p[0] = 1;
        h[0] = 0;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s[i];
        }
        for (int l = n - 1; l > 0; --l) {
            ULL prefix = h[l];
            ULL suffix = h[n] - h[n - l] * p[l];
            if (prefix == suffix) return s.substr(0, l);
        }
        return "";
    }
};
```

```go
func longestPrefix(s string) string {
	base := 131
	n := len(s)
	p := make([]int, n+10)
	h := make([]int, n+10)
	p[0] = 1
	for i, c := range s {
		p[i+1] = p[i] * base
		h[i+1] = h[i]*base + int(c)
	}
	for l := n - 1; l > 0; l-- {
		prefix, suffix := h[l], h[n]-h[n-l]*p[l]
		if prefix == suffix {
			return s[:l]
		}
	}
	return ""
}
```

```ts
function longestPrefix(s: string): string {
    const n = s.length;
    for (let i = n - 1; i >= 0; i--) {
        if (s.slice(0, i) === s.slice(n - i, n)) {
            return s.slice(0, i);
        }
    }
    return '';
}
```

```rust
impl Solution {
    pub fn longest_prefix(s: String) -> String {
        let n = s.len();
        for i in (0..n).rev() {
            if s[0..i] == s[n - i..n] {
                return s[0..i].to_string();
            }
        }
        String::new()
    }
}
```

<!-- tabs:end -->

<!-- end -->
