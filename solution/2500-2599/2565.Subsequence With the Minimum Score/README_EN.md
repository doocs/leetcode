# [2565. Subsequence With the Minimum Score](https://leetcode.com/problems/subsequence-with-the-minimum-score)

[中文文档](/solution/2500-2599/2565.Subsequence%20With%20the%20Minimum%20Score/README.md)

## Description

<p>You are given two strings <code>s</code> and <code>t</code>.</p>

<p>You are allowed to remove any number of characters from the string <code>t</code>.</p>

<p>The score of the string is <code>0</code> if no characters are removed from the string <code>t</code>, otherwise:</p>

<ul>
	<li>Let <code>left</code> be the minimum index among all removed characters.</li>
	<li>Let <code>right</code> be the maximum index among all removed characters.</li>
</ul>

<p>Then the score of the string is <code>right - left + 1</code>.</p>

<p>Return <em>the minimum possible score to make </em><code>t</code><em>&nbsp;a subsequence of </em><code>s</code><em>.</em></p>

<p>A <strong>subsequence</strong> of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., <code>&quot;ace&quot;</code> is a subsequence of <code>&quot;<u>a</u>b<u>c</u>d<u>e</u>&quot;</code> while <code>&quot;aec&quot;</code> is not).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abacaba&quot;, t = &quot;bzaa&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> In this example, we remove the character &quot;z&quot; at index 1 (0-indexed).
The string t becomes &quot;baa&quot; which is a subsequence of the string &quot;abacaba&quot; and the score is 1 - 1 + 1 = 1.
It can be proven that 1 is the minimum score that we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cde&quot;, t = &quot;xyz&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example, we remove characters &quot;x&quot;, &quot;y&quot; and &quot;z&quot; at indices 0, 1, and 2 (0-indexed).
The string t becomes &quot;&quot; which is a subsequence of the string &quot;cde&quot; and the score is 2 - 0 + 1 = 3.
It can be proven that 3 is the minimum score that we can achieve.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumScore(self, s: str, t: str) -> int:
        def check(x):
            for k in range(n):
                i, j = k - 1, k + x
                l = f[i] if i >= 0 else -1
                r = g[j] if j < n else m + 1
                if l < r:
                    return True
            return False

        m, n = len(s), len(t)
        f = [inf] * n
        g = [-1] * n
        i, j = 0, 0
        while i < m and j < n:
            if s[i] == t[j]:
                f[j] = i
                j += 1
            i += 1
        i, j = m - 1, n - 1
        while i >= 0 and j >= 0:
            if s[i] == t[j]:
                g[j] = i
                j -= 1
            i -= 1

        return bisect_left(range(n + 1), True, key=check)
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private int[] f;
    private int[] g;

    public int minimumScore(String s, String t) {
        m = s.length();
        n = t.length();
        f = new int[n];
        g = new int[n];
        for (int i = 0; i < n; ++i) {
            f[i] = 1 << 30;
            g[i] = -1;
        }
        for (int i = 0, j = 0; i < m && j < n; ++i) {
            if (s.charAt(i) == t.charAt(j)) {
                f[j] = i;
                ++j;
            }
        }
        for (int i = m - 1, j = n - 1; i >= 0 && j >= 0; --i) {
            if (s.charAt(i) == t.charAt(j)) {
                g[j] = i;
                --j;
            }
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int len) {
        for (int k = 0; k < n; ++k) {
            int i = k - 1, j = k + len;
            int l = i >= 0 ? f[i] : -1;
            int r = j < n ? g[j] : m + 1;
            if (l < r) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumScore(string s, string t) {
        int m = s.size(), n = t.size();
        vector<int> f(n, 1e6);
        vector<int> g(n, -1);
        for (int i = 0, j = 0; i < m && j < n; ++i) {
            if (s[i] == t[j]) {
                f[j] = i;
                ++j;
            }
        }
        for (int i = m - 1, j = n - 1; i >= 0 && j >= 0; --i) {
            if (s[i] == t[j]) {
                g[j] = i;
                --j;
            }
        }

        auto check = [&](int len) {
            for (int k = 0; k < n; ++k) {
                int i = k - 1, j = k + len;
                int l = i >= 0 ? f[i] : -1;
                int r = j < n ? g[j] : m + 1;
                if (l < r) {
                    return true;
                }
            }
            return false;
        };

        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

### **Go**

```go
func minimumScore(s string, t string) int {
	m, n := len(s), len(t)
	f := make([]int, n)
	g := make([]int, n)
	for i := range f {
		f[i] = 1 << 30
		g[i] = -1
	}
	for i, j := 0, 0; i < m && j < n; i++ {
		if s[i] == t[j] {
			f[j] = i
			j++
		}
	}
	for i, j := m-1, n-1; i >= 0 && j >= 0; i-- {
		if s[i] == t[j] {
			g[j] = i
			j--
		}
	}
	return sort.Search(n+1, func(x int) bool {
		for k := 0; k < n; k++ {
			i, j := k-1, k+x
			l, r := -1, m+1
			if i >= 0 {
				l = f[i]
			}
			if j < n {
				r = g[j]
			}
			if l < r {
				return true
			}
		}
		return false
	})
}
```

### **...**

```

```

<!-- tabs:end -->
